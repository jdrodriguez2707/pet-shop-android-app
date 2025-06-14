package com.example.petshop.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.petshop.ui.theme.*

/**
 * Clase que representa un producto en la tienda de mascotas.
 *
 * @property id Identificador único del producto
 * @property name Nombre del producto
 * @property description Descripción breve del producto
 * @property price Precio del producto
 * @property imageUrl URL de la imagen del producto
 * @property category Categoría a la que pertenece el producto
 * @property rating Valoración del producto (0-5)
 * @property inStock Disponibilidad del producto
 */
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String? = null,
    @DrawableRes val imageRes: Int? = null,
    val category: ProductCategory,
    val rating: Float = 0f,
    val inStock: Boolean = true
)

/**
 * Enum que define las categorías de productos disponibles.
 *
 * @property color Color asociado a la categoría para la UI
 * @property displayName Nombre visible para el usuario
 */
enum class ProductCategory(val color: Color, val displayName: String) {
    DOGS(DogColor, "Perros"),
    CATS(CatColor, "Gatos"),
    BIRDS(BirdColor, "Aves"),
    FISH(FishColor, "Peces"),
    SMALL_PETS(SmallPetColor, "Pequeñas Mascotas"),
    ACCESSORIES(Secondary, "Accesorios"),
    FOOD(Primary, "Alimentos")
}
