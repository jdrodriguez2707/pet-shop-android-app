package com.example.petshop.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.petshop.R
import com.example.petshop.model.PetType
import com.example.petshop.ui.components.PetCareCard

/**
 * Pantalla que muestra tips en audio para el cuidado de mascotas
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetTipsScreen(
    onBackClick: () -> Unit,
    onPetCareSelected: (PetType) -> Unit
) {
    LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tips para mascotas") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
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
                .padding(horizontal = 16.dp)
        ) {
            // Texto explicativo
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Podcast de consejos para tu mascota",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Explora nuestra colección de audios con consejos profesionales sobre el cuidado de cada tipo de mascota. Selecciona una categoría para encontrar todos los episodios disponibles.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta de cuidado para Perros
            PetCareCard(
                petType = PetType.DOG,
                imageRes = R.drawable.max,
                onClick = { onPetCareSelected(PetType.DOG) },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tarjeta de cuidado para Gatos
            PetCareCard(
                petType = PetType.CAT,
                imageRes = R.drawable.oliver,
                onClick = { onPetCareSelected(PetType.CAT) },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tarjeta de cuidado para Aves
            PetCareCard(
                petType = PetType.BIRD,
                imageRes = R.drawable.kiwi,
                onClick = { onPetCareSelected(PetType.BIRD) },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tarjeta de cuidado para Peces
            PetCareCard(
                petType = PetType.FISH,
                imageRes = R.drawable.nemo,
                onClick = { onPetCareSelected(PetType.FISH) },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

