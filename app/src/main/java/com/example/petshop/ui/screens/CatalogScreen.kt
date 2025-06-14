package com.example.petshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.petshop.data.SampleProductsData
import com.example.petshop.model.Product
import com.example.petshop.model.ProductCategory
import com.example.petshop.ui.components.CategorySelector
import com.example.petshop.ui.components.HighlightedGalleryButton
import com.example.petshop.ui.components.HighlightedTipsButton
import com.example.petshop.ui.components.ProductCard

/**
 * Pantalla principal del catálogo de productos
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    onProductClick: (Product) -> Unit = {},
    onCartClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    onGalleryClick: () -> Unit = {},
    onTipsClick: () -> Unit = {}
) {
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    val products = remember(selectedCategory) {
        if (selectedCategory != null) {
            SampleProductsData.getProductsByCategory(selectedCategory!!)
        } else {
            SampleProductsData.productsList
        }
    }

    Scaffold(
        topBar = {
            CatalogTopAppBar(
                cartItemsCount = 0, // Se reemplazará con el tamaño real del carrito
                onCartClick = onCartClick,
                onSearchClick = onSearchClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Botón destacado de Galería de Mascotas
            Spacer(modifier = Modifier.height(16.dp))
            HighlightedGalleryButton(
                onClick = onGalleryClick,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Botón destacado de Tips para cuidar tu mascota
            HighlightedTipsButton(
                onClick = onTipsClick,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Selector de categorías
            CategorySelector(
                categories = SampleProductsData.allCategories,
                selectedCategory = selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = if (selectedCategory == category) null else category
                },
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Título de la sección actual
            Text(
                text = selectedCategory?.displayName ?: "Todos los productos",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Cuadrícula de productos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onAddToCart = { onAddToCart(product) },
                        onProductClick = { onProductClick(product) }
                    )
                }
            }
        }
    }
}

/**
 * Barra superior de la pantalla del catálogo
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogTopAppBar(
    cartItemsCount: Int,
    onCartClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Pet Shop")
        },
        actions = {
            // Botón de búsqueda
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            }

            // Botón del carrito con badge para mostrar cantidad
            BadgedBox(
                badge = {
                    if (cartItemsCount > 0) {
                        Badge {
                            Text(cartItemsCount.toString())
                        }
                    }
                }
            ) {
                IconButton(onClick = onCartClick) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Ver carrito"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
