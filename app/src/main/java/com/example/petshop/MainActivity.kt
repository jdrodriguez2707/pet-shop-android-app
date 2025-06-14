package com.example.petshop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.petshop.model.Pet
import com.example.petshop.model.PetType
import com.example.petshop.model.Product
import com.example.petshop.ui.screens.CatalogScreen
import com.example.petshop.ui.screens.PetAdoptionDetailScreen
import com.example.petshop.ui.screens.PetAudioPlayerScreen
import com.example.petshop.ui.screens.PetGalleryScreen
import com.example.petshop.ui.screens.PetTipsScreen
import com.example.petshop.ui.screens.ProductDetailScreen
import com.example.petshop.ui.theme.PetShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PetShopApp()
                }
            }
        }
    }
}

/**
 * Composable principal de la aplicación que maneja la navegación
 */
@Composable
fun PetShopApp() {
    val context = LocalContext.current

    // Estado para controlar qué pantalla se muestra
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Catalog) }

    // Estado para almacenar los productos del carrito
    val cartItems = remember { mutableStateListOf<Product>() }

    when (val screen = currentScreen) {
        is Screen.Catalog -> {
            CatalogScreen(
                onProductClick = { product ->
                    currentScreen = Screen.ProductDetail(product)
                },
                onCartClick = {
                    Toast.makeText(context, "Ver carrito: ${cartItems.size} productos", Toast.LENGTH_SHORT).show()
                },
                onSearchClick = {
                    Toast.makeText(context, "Buscar productos", Toast.LENGTH_SHORT).show()
                },
                onAddToCart = { product ->
                    cartItems.add(product)
                    Toast.makeText(context, "${product.name} añadido al carrito", Toast.LENGTH_SHORT).show()
                },
                onGalleryClick = {
                    currentScreen = Screen.PetGallery
                },
                onTipsClick = {
                    currentScreen = Screen.PetTips
                }
            )
        }
        is Screen.ProductDetail -> {
            ProductDetailScreen(
                product = screen.product,
                onBackClick = {
                    currentScreen = Screen.Catalog
                },
                onAddToCart = { product ->
                    cartItems.add(product)
                    Toast.makeText(context, "${product.name} añadido al carrito", Toast.LENGTH_SHORT).show()
                }
            )
        }
        is Screen.PetGallery -> {
            PetGalleryScreen(
                onBackClick = {
                    currentScreen = Screen.Catalog
                },
                onAdoptClick = { pet ->
                    Toast.makeText(context, "¡Has iniciado el proceso de adopción de ${pet.name}!", Toast.LENGTH_SHORT).show()
                    currentScreen = Screen.PetAdoptionDetail(pet)
                }
            )
        }
        is Screen.PetAdoptionDetail -> {
            PetAdoptionDetailScreen(
                pet = screen.pet,
                onBackClick = {
                    currentScreen = Screen.PetGallery
                },
                onFinalizeAdoption = {
                    Toast.makeText(context, "¡Felicidades! Has completado la solicitud de adopción de ${screen.pet.name}. Te contactaremos pronto.", Toast.LENGTH_LONG).show()
                    currentScreen = Screen.PetGallery
                }
            )
        }
        is Screen.PetTips -> {
            PetTipsScreen(
                onBackClick = {
                    currentScreen = Screen.Catalog
                },
                onPetCareSelected = { petType ->
                    currentScreen = Screen.PetAudioPlayer(petType)
                }
            )
        }
        is Screen.PetAudioPlayer -> {
            PetAudioPlayerScreen(
                petType = screen.petType,
                onBackClick = {
                    currentScreen = Screen.PetTips
                }
            )
        }
    }
}

/**
 * Representa las diferentes pantallas de la aplicación
 */
sealed class Screen {
    object Catalog : Screen()
    data class ProductDetail(val product: Product) : Screen()
    object PetGallery : Screen()
    data class PetAdoptionDetail(val pet: Pet) : Screen()
    object PetTips : Screen()
    data class PetAudioPlayer(val petType: PetType) : Screen()
}

@Preview(showBackground = true)
@Composable
fun PetShopAppPreview() {
    PetShopTheme {
        PetShopApp()
    }
}