package com.example.petshop.model

import androidx.annotation.RawRes

/**
 * Clase que representa un episodio de audio con consejos para mascotas
 */
data class AudioEpisode(
    val id: String,
    val title: String,
    val description: String,
    val duration: Int, // duración en segundos
    @RawRes val audioRes: Int,
    val petType: PetType
)

/**
 * Repositorio de episodios de audio
 */
object AudioEpisodesRepository {
    fun getEpisodesForPetType(petType: PetType): List<AudioEpisode> {
        return allEpisodes.filter { it.petType == petType }
    }

    val allEpisodes = listOf(
        // Episodios para perros
        AudioEpisode(
            id = "dog_ep1",
            title = "Cuidados esenciales para perros",
            description = "Aprende sobre alimentación, ejercicio, higiene y salud para mantener a tu perro feliz y saludable.",
            duration = 180, // 3 minutos
            audioRes = com.example.petshop.R.raw.big_dog_barking,
            petType = PetType.DOG
        ),
        AudioEpisode(
            id = "dog_ep2",
            title = "Entrenamiento básico para perros",
            description = "Técnicas efectivas para enseñar comandos básicos y establecer una buena comunicación con tu perro.",
            duration = 240, // 4 minutos
            audioRes = com.example.petshop.R.raw.big_dog_barking,
            petType = PetType.DOG
        ),

        // Episodios para gatos
        AudioEpisode(
            id = "cat_ep1",
            title = "Guía completa para cuidar a tu gato",
            description = "Descubre los secretos para entender el comportamiento felino y proporcionarle los mejores cuidados.",
            duration = 210, // 3.5 minutos
            audioRes = com.example.petshop.R.raw.cat_98721,
            petType = PetType.CAT
        ),
        AudioEpisode(
            id = "cat_ep2",
            title = "Salud y bienestar felino",
            description = "Consejos importantes sobre vacunas, visitas al veterinario y señales de alarma en la salud de tu gato.",
            duration = 195, // 3.25 minutos
            audioRes = com.example.petshop.R.raw.cat_98721,
            petType = PetType.CAT
        ),

        // Episodios para aves
        AudioEpisode(
            id = "bird_ep1",
            title = "Todo sobre el cuidado de aves",
            description = "Consejos especializados sobre nutrición, hábitat y enriquecimiento para aves como mascota.",
            duration = 165, // 2.75 minutos
            audioRes = com.example.petshop.R.raw.parakeets_77058,
            petType = PetType.BIRD
        ),

        // Episodios para peces
        AudioEpisode(
            id = "fish_ep1",
            title = "Mantenimiento del acuario y salud de los peces",
            description = "Aprende a crear y mantener un ambiente acuático ideal para tus peces.",
            duration = 150, // 2.5 minutos
            audioRes = com.example.petshop.R.raw.fish_in_river_6114,
            petType = PetType.FISH
        )
    )
}
