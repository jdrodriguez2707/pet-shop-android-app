package com.example.petshop.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.petshop.ui.theme.*

/**
 * Clase que representa una mascota en la galería
 */
data class Pet(
    val id: String,
    val name: String,
    val type: PetType,
    val breed: String,
    val age: String,
    val description: String,
    val detailedDescription: String, // Descripción ampliada para la vista de detalles
    @DrawableRes val imageRes: Int,
    val videoUrl: String, // URL del video para ExoPlayer
    val personality: String, // Personalidad de la mascota
    val healthStatus: String, // Estado de salud
    val requirements: String, // Requisitos para adopción
    val isAvailable: Boolean = true
)

/**
 * Tipos de mascotas disponibles
 */
enum class PetType(val displayName: String, val color: Color) {
    DOG("Perro", DogColor),
    CAT("Gato", CatColor),
    BIRD("Ave", BirdColor),
    FISH("Pez", FishColor)
}
