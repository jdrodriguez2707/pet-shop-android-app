package com.example.petshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.example.petshop.model.Pet
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView

/**
 * Pantalla que muestra los detalles de adopción de una mascota
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetAdoptionDetailScreen(
    pet: Pet,
    onBackClick: () -> Unit,
    onFinalizeAdoption: () -> Unit
) {
    val context = LocalContext.current

    // Configuración del reproductor de video
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    // Efecto para inicializar y liberar el reproductor
    DisposableEffect(context) {
        // Inicializar el reproductor
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            // Usar la URL del video especificada en el modelo de datos
            val mediaItem = MediaItem.fromUri(pet.videoUrl.toUri())
            setMediaItem(mediaItem)
            playWhenReady = false // No reproducir automáticamente
            repeatMode = Player.REPEAT_MODE_ONE // Repetir el video
            prepare() // Preparar el reproductor
        }

        // Liberar recursos cuando el componente se destruya
        onDispose {
            exoPlayer?.release()
            exoPlayer = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adoptar a ${pet.name}") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = pet.type.color,
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
            // Reproductor de video con ExoPlayer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                exoPlayer?.let { player ->
                    AndroidView(
                        factory = { ctx ->
                            // Crear la vista del reproductor de ExoPlayer
                            StyledPlayerView(ctx).apply {
                                this.player = player
                                useController = true // Mostrar controles de reproducción
                                setShowNextButton(false) // Ocultar botones innecesarios
                                setShowPreviousButton(false)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Información de la mascota
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Cabecera con nombre, tipo y edad
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${pet.name} (${pet.breed})",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = pet.type.color.copy(alpha = 0.8f)
                    ) {
                        Text(
                            text = "${pet.age}",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sección: Descripción
                SectionTitle(title = "Sobre ${pet.name}")
                Text(
                    text = pet.detailedDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sección: Personalidad
                SectionTitle(title = "Personalidad")
                Text(
                    text = pet.personality,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sección: Estado de salud
                SectionTitle(title = "Estado de salud")
                Text(
                    text = pet.healthStatus,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sección: Requisitos para adopción
                SectionTitle(title = "Requisitos para adopción")
                Text(
                    text = pet.requirements,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botón para finalizar adopción
                Button(
                    onClick = onFinalizeAdoption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Finalizar adopción",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 4.dp)
    )
}
