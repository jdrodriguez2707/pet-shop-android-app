package com.example.petshop.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.petshop.R
import com.example.petshop.audio.AudioPlayerManager
import com.example.petshop.model.AudioEpisode
import com.example.petshop.model.AudioEpisodesRepository
import com.example.petshop.model.PetType
import com.example.petshop.ui.components.AudioPlayerComponent

/**
 * Pantalla que muestra el reproductor de audio con tips para un tipo específico de mascota
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetAudioPlayerScreen(
    petType: PetType,
    onBackClick: () -> Unit
) {
    // Obtener episodios para este tipo de mascota
    val episodes = AudioEpisodesRepository.getEpisodesForPetType(petType)

    // Si no hay episodios, mostrar mensaje
    if (episodes.isEmpty()) {
        NoPodcastsAvailableScreen(petType, onBackClick)
        return
    }

    // Estado para el episodio seleccionado y progreso
    var selectedEpisode by remember { mutableStateOf(episodes.first()) }
    var isPlaying by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }

    // Configurar reproductor de audio
    val context = LocalContext.current
    val audioPlayerManager = remember { AudioPlayerManager(context) }

    // Efecto para manejar ciclo de vida del reproductor
    DisposableEffect(key1 = selectedEpisode) {
        // Preparar el episodio seleccionado
        audioPlayerManager.prepareEpisode(selectedEpisode)

        // Configurar callback de progreso
        audioPlayerManager.setProgressCallback { newProgress ->
            progress = newProgress
        }

        // Limpiar cuando cambie el episodio o se destruya la composición
        onDispose {
            audioPlayerManager.releaseMediaPlayer()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { if (petType.displayName == "Pez") Text("Tips para Peces") else Text("Tips para ${petType.displayName}s") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = petType.color,
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
        ) {
            // Reproductor de audio
            AudioPlayerComponent(
                title = selectedEpisode.title,
                description = selectedEpisode.description,
                imageRes = getPetTypeImage(petType),
                accentColor = petType.color,
                isPlaying = isPlaying,
                progress = progress,
                duration = audioPlayerManager.getFormattedDuration(),
                currentTime = audioPlayerManager.formatTime(progress * selectedEpisode.duration),
                onPlayPauseClick = {
                    isPlaying = audioPlayerManager.togglePlayback()
                },
                onSeek = { newProgress ->
                    progress = newProgress
                    audioPlayerManager.seekTo(newProgress)
                },
                onSkipForward = {
                    audioPlayerManager.skipForward()
                },
                onSkipBackward = {
                    audioPlayerManager.skipBackward()
                }
            )

            // Texto "Descubre más contenido"
            Text(
                text = "Descubre más contenido",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp)
            )

            // Episodios de otras categorías
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                // Obtener un episodio de cada una de las siguientes categorías
                val otherCategoryEpisodes = getRelatedEpisodes(petType)

                items(otherCategoryEpisodes) { episode ->
                    EpisodeItemSimple(
                        episode = episode,
                        onClick = {
                            // Navegar a la categoría del episodio seleccionado estaría aquí
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(
    episode: AudioEpisode,
    isSelected: Boolean,
    accentColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                accentColor.copy(alpha = 0.1f)
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = episode.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) accentColor else MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = episode.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${episode.duration / 60}:${episode.duration % 60}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun EpisodeItemSimple(
    episode: AudioEpisode,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de categoría (círculo de color)
            Surface(
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(20.dp),
                color = episode.petType.color.copy(alpha = 0.8f)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = episode.petType.displayName.first().toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = episode.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1
                )

                Text(
                    text = "Consejos para ${episode.petType.displayName.lowercase()}s",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "${episode.duration / 60}:${episode.duration % 60}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoPodcastsAvailableScreen(
    petType: PetType,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { if (petType.displayName == "Pez") Text("Tips para Peces") else Text("Tips para ${petType.displayName}s") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = petType.color,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No hay podcasts disponibles",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Estamos trabajando en crear nuevos contenidos para ${petType.displayName.lowercase()}s",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Obtiene la imagen correspondiente para cada tipo de mascota
 */
private fun getPetTypeImage(petType: PetType): Int {
    return when (petType) {
        PetType.DOG -> R.drawable.max
        PetType.CAT -> R.drawable.oliver
        PetType.BIRD -> R.drawable.kiwi
        PetType.FISH -> R.drawable.nemo
    }
}

/**
 * Obtiene episodios relacionados de otras categorías
 */
private fun getRelatedEpisodes(currentPetType: PetType): List<AudioEpisode> {
    val allTypes = PetType.values().toList()
    val result = mutableListOf<AudioEpisode>()

    // Encontrar el índice del tipo actual
    val currentIndex = allTypes.indexOf(currentPetType)

    // Obtener episodios de las siguientes categorías (con wrapping circular)
    for (i in 1 until allTypes.size) {
        val nextIndex = (currentIndex + i) % allTypes.size
        val nextType = allTypes[nextIndex]

        // Obtener el primer episodio de esta categoría, si existe
        val episodesOfType = AudioEpisodesRepository.getEpisodesForPetType(nextType)
        if (episodesOfType.isNotEmpty()) {
            result.add(episodesOfType.first())
        }
    }

    return result
}
