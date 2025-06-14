package com.example.petshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.petshop.model.ProductCategory

/**
 * Componente que muestra una fila horizontal de categorías seleccionables
 *
 * @param categories Lista de categorías para mostrar
 * @param selectedCategory Categoría actualmente seleccionada
 * @param onCategorySelected Callback invocado cuando una categoría es seleccionada
 */
@Composable
fun CategorySelector(
    categories: List<ProductCategory>,
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        categories.forEach { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onCategorySelected = { onCategorySelected(category) }
            )
        }
    }
}

/**
 * Elemento individual para cada categoría
 */
@Composable
fun CategoryItem(
    category: ProductCategory,
    isSelected: Boolean,
    onCategorySelected: () -> Unit
) {
    val backgroundColor = if (isSelected) category.color else Color.Transparent
    val contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
    val borderColor = if (isSelected) Color.Transparent else category.color

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .clickable(onClick = onCategorySelected)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.displayName,
            color = contentColor,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}
