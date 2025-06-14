package com.example.petshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.petshop.model.Pet

/**
 * Componente que muestra una mascota en forma de tarjeta
 *
 * @param pet La mascota a mostrar
 * @param onAdoptClick Callback cuando se presiona el botón de adoptar
 */
@Composable
fun PetCard(
    pet: Pet,
    onAdoptClick: (Pet) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp), // Aumentando la altura para acomodar el botón
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen de la mascota
            Box(
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = pet.imageRes),
                    contentDescription = pet.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Etiqueta con el tipo de mascota
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    shape = RoundedCornerShape(12.dp),
                    color = pet.type.color.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = pet.type.displayName,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                // Etiqueta de disponibilidad
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(12.dp),
                    color = if (pet.isAvailable)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
                    else
                        Color.Gray.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = if (pet.isAvailable) "Disponible" else "Adoptado",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            // Información de la mascota
            Column(
                modifier = Modifier
                    .weight(0.9f)
                    .padding(16.dp)
            ) {
                // Nombre y edad en la misma línea
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = pet.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = pet.age,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }

                // Espaciado después del nombre/edad
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = pet.breed,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Espaciado entre la raza y la descripción
                Spacer(modifier = Modifier.height(8.dp))

                // Descripción breve de la mascota
                Text(
                    text = pet.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Mayor espaciado antes del botón
                Spacer(modifier = Modifier.height(12.dp))
                Spacer(modifier = Modifier.weight(1f)) // Empuja el botón hacia abajo

                // Botón de adoptar centrado horizontalmente
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onAdoptClick(pet) },
                        enabled = pet.isAvailable,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = if (pet.isAvailable) "Adoptar" else "Adoptado")
                    }
                }
            }
        }
    }
}
