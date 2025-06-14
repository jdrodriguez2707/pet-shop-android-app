package com.example.petshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.petshop.model.Product
import com.example.petshop.model.ProductCategory
import java.text.NumberFormat
import java.util.Locale

/**
 * Pantalla que muestra los detalles de un producto específico
 *
 * @param product El producto del que se mostrarán los detalles
 * @param onBackClick Callback cuando se presiona el botón para volver
 * @param onAddToCart Callback cuando se agrega el producto al carrito
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit,
    onAddToCart: (Product) -> Unit
) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "CO"))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalles del producto") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                product.imageRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = product.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Etiqueta de categoría
                Surface(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart),
                    shape = RoundedCornerShape(16.dp),
                    color = product.category.color.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = product.category.displayName,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            // Información del producto
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Nombre del producto
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Precio
                Text(
                    text = currencyFormat.format(product.price),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción ampliada
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = generateDetailedDescription(product),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Información adicional simulada
                Text(
                    text = "Información adicional",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = generateAdditionalInfo(product),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botón para agregar al carrito
                Button(
                    onClick = { onAddToCart(product) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Agregar al carrito",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

/**
 * Genera una descripción detallada basada en el tipo de producto
 */
private fun generateDetailedDescription(product: Product): String {
    val baseDescription = product.description

    return when {
        product.category == ProductCategory.DOGS && product.name.contains("alimento", ignoreCase = true) ->
            "$baseDescription\n\nAlimento premium para perros formulado específicamente para mantener una salud óptima. " +
                    "Contiene proteínas de alta calidad, vitaminas esenciales y minerales que promueven un pelaje brillante, " +
                    "dientes fuertes y un sistema inmunológico saludable. Sin colorantes ni conservantes artificiales, " +
                    "para cuidar la digestión de tu mascota."

        product.category == ProductCategory.CATS && product.name.contains("alimento", ignoreCase = true) ->
            "$baseDescription\n\nAlimento completo para gatos con delicioso sabor a pescado que les encanta. " +
                    "Enriquecido con taurina para mantener una visión saludable y ácidos grasos omega para un pelaje sedoso. " +
                    "Su fórmula especial ayuda a reducir la formación de bolas de pelo y promueve un tracto urinario sano."

        product.name.contains("juguete", ignoreCase = true) ->
            "$baseDescription\n\nJuguete duradero diseñado para proporcionar horas de diversión y ejercicio. " +
                    "Fabricado con materiales no tóxicos y seguros para mascotas. Ayuda a reducir el estrés y la ansiedad " +
                    "manteniendo a tu mascota activa y estimulada mentalmente. Ideal para juegos interactivos que fortalecen " +
                    "el vínculo entre mascota y dueño."

        product.name.contains("collar", ignoreCase = true) ->
            "$baseDescription\n\nCollar ajustable de alta calidad que combina comodidad y durabilidad. " +
                    "El material resistente al agua y de secado rápido lo hace perfecto para usar en cualquier clima. " +
                    "Su sistema de ajuste fácil permite un ajuste personalizado a medida que tu mascota crece. " +
                    "Diseñado para minimizar la irritación de la piel mientras mantiene a tu mascota segura."

        product.category == ProductCategory.FISH ->
            "$baseDescription\n\nProducto especialmente formulado para el ambiente acuático, manteniendo el equilibrio " +
                    "del ecosistema de tu acuario. Diseñado por expertos en cuidado de peces para garantizar los mejores " +
                    "resultados. Contribuye a crear un entorno saludable donde tus peces puedan prosperar."

        product.category == ProductCategory.BIRDS ->
            "$baseDescription\n\nDiseñado específicamente para aves, este producto considera las necesidades únicas " +
                    "de las mascotas voladoras. Elaborado con materiales seguros y componentes naturales que promueven " +
                    "comportamientos saludables. Perfecto para mantener a tus aves activas, felices y saludables."

        product.category == ProductCategory.SMALL_PETS ->
            "$baseDescription\n\nPensado para las necesidades específicas de pequeños roedores y mascotas similares. " +
                    "Su diseño compacto se adapta perfectamente a jaulas y espacios reducidos, maximizando el confort de tu " +
                    "pequeño amigo. Proporciona el ambiente adecuado para el desarrollo natural de estas mascotas."

        else ->
            "$baseDescription\n\nProducto de alta calidad diseñado específicamente para el cuidado integral de tu mascota. " +
                    "Elaborado con los mejores materiales y siguiendo estrictos estándares de calidad para garantizar la " +
                    "seguridad y bienestar de tu compañero. Este artículo esencial en el cuidado de mascotas te ayudará " +
                    "a mantener a tu amigo peludo feliz y saludable por mucho tiempo."
    }
}

/**
 * Genera información adicional basada en el producto
 */
private fun generateAdditionalInfo(product: Product): String {
    val stockInfo = if (product.inStock) "• Disponible en stock" else "• Agotado temporalmente"

    val categorySpecificInfo = when (product.category) {
        ProductCategory.FOOD -> "• Almacenar en lugar fresco y seco\n• Fecha de caducidad en el empaque"
        ProductCategory.ACCESSORIES -> "• Material: Nylon y poliéster resistente\n• Lavable a máquina"
        ProductCategory.DOGS -> "• Recomendado para perros de todas las edades\n• Made in Colombia"
        ProductCategory.CATS -> "• Específico para gatos\n• Testado dermatológicamente"
        ProductCategory.BIRDS -> "• Seguro para todas las especies de aves\n• Libre de componentes tóxicos"
        ProductCategory.FISH -> "• Seguro para todos los tipos de acuarios\n• No altera el pH del agua"
        ProductCategory.SMALL_PETS -> "• Adecuado para hámsters, conejos y roedores\n• Tamaño compacto"
    }

    return "$stockInfo\n• Envío en 24-48 horas\n• Garantía de calidad\n$categorySpecificInfo"
}
