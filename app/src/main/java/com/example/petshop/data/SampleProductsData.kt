package com.example.petshop.data

import com.example.petshop.R
import com.example.petshop.model.Product
import com.example.petshop.model.ProductCategory

/**
 * Datos de muestra para poblado inicial de productos en la app.
 */
object SampleProductsData {
    val productsList = listOf(
        // Productos para perros
        Product(
            id = "dog_food_1",
            name = "Premium Dog Chow",
            description = "Alimento completo y balanceado para perros adultos",
            price = 25.99,
            imageRes = R.drawable.alimento_perro,
            category = ProductCategory.DOGS
        ),
        Product(
            id = "dog_toy_1",
            name = "Pelota de juguete",
            description = "Pelota resistente para horas de diversión",
            price = 9.99,
            imageRes = R.drawable.pelota_perro,
            category = ProductCategory.DOGS,
        ),
        Product(
            id = "dog_acc_1",
            name = "Collar ajustable",
            description = "Collar de nylon resistente y ajustable",
            price = 12.99,
            imageRes = R.drawable.collar,
            category = ProductCategory.ACCESSORIES
        ),

        // Productos para gatos
        Product(
            id = "cat_food_1",
            name = "Whiskas Sabor Pescado",
            description = "Alimento húmedo para gatos con sabor a pescado",
            price = 15.50,
            imageRes = R.drawable.alimento_gato,
            category = ProductCategory.CATS
        ),
        Product(
            id = "cat_toy_1",
            name = "Ratón de juguete",
            description = "Juguete interactivo con sonido para gatos",
            price = 5.99,
            imageRes = R.drawable.raton_juguete,
            category = ProductCategory.CATS,
        ),

        // Productos para aves
        Product(
            id = "bird_food_1",
            name = "Semillas mixtas",
            description = "Mezcla premium de semillas para canarios y periquitos",
            price = 8.99,
            imageRes = R.drawable.semillas_canario,
            category = ProductCategory.BIRDS
        )
        /*
        Product(
            id = "bird_acc_1",
            name = "Columpio para jaula",
            description = "Columpio de madera natural para aves",
            price = 6.50,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.BIRDS
        ),

        // Productos para peces
        Product(
            id = "fish_food_1",
            name = "TetraMix escamas",
            description = "Alimento completo en escamas para peces tropicales",
            price = 7.99,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.FISH,
            rating = 4.2f
        ),
        Product(
            id = "fish_acc_1",
            name = "Planta artificial",
            description = "Decoración de acuario con aspecto natural",
            price = 10.99,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.FISH
        ),

        // Productos para pequeñas mascotas
        Product(
            id = "small_food_1",
            name = "Mezcla para hámster",
            description = "Alimento balanceado para hámsters y pequeños roedores",
            price = 6.99,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.SMALL_PETS
        ),
        Product(
            id = "small_acc_1",
            name = "Rueda de ejercicio",
            description = "Rueda silenciosa para actividad física",
            price = 14.99,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.SMALL_PETS,
            rating = 4.7f
        ),

        // Accesorios generales
        Product(
            id = "acc_carrier_1",
            name = "Transportadora universal",
            description = "Transportadora segura para mascotas pequeñas y medianas",
            price = 29.99,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.ACCESSORIES,
            rating = 4.0f
        ),

        // Alimentos
        Product(
            id = "food_vitamin_1",
            name = "Suplemento vitamínico",
            description = "Suplemento vitamínico para todo tipo de mascotas",
            price = 18.99,
            imageRes = R.drawable.placeholder_image,
            category = ProductCategory.FOOD
        )
        */
    )

    /**
     * Obtiene productos filtrados por categoría
     */
    fun getProductsByCategory(category: ProductCategory): List<Product> {
        return productsList.filter { it.category == category }
    }

    /**
     * Obtiene todas las categorías presentes en los productos
     */
    val allCategories: List<ProductCategory> get() = ProductCategory.values().toList()
}
