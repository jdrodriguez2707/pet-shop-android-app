package com.example.petshop.data

import com.example.petshop.R
import com.example.petshop.model.Pet
import com.example.petshop.model.PetType

/**
 * Datos de ejemplo para las mascotas en la galería
 */
object SamplePetsData {
    val petsList = listOf(
        // Perros
        Pet(
            id = "dog1",
            name = "Max",
            type = PetType.DOG,
            breed = "Labrador Retriever",
            age = "2 años",
            description = "Max es un Labrador muy juguetón y cariñoso. Le encanta correr y es excelente con los niños.",
            detailedDescription = "Max llegó a nuestro refugio hace 6 meses. Es un Labrador Retriever de pelaje dorado con una personalidad excepcional. Max fue rescatado de una situación de abandono, pero a pesar de su difícil pasado, es extremadamente sociable y confiado. Le encanta jugar a buscar la pelota y disfruta de largos paseos. Es increíblemente paciente con los niños y se lleva bien con otros perros, aunque no ha tenido mucha exposición a gatos.",
            imageRes = R.drawable.max,
            videoUrl = "https://www.pexels.com/download/video/8746813/?fps=29.969999313354492&h=720&w=1366",
            personality = "Juguetón, cariñoso, sociable y lleno de energía. Obediente y aprende rápido.",
            healthStatus = "Vacunas al día, desparasitado y microchip implantado. Castrado. Buena salud general.",
            requirements = "Hogar con espacio para correr y jugar. Ideal para familia con niños. Requiere ejercicio diario y estimulación mental.",
            isAvailable = true
        ),
        Pet(
            id = "dog2",
            name = "Luna",
            type = PetType.DOG,
            breed = "Golden Retriever",
            age = "1 año y medio",
            description = "Luna es una Golden muy inteligente y leal. Está entrenada para obedecer comandos básicos.",
            detailedDescription = "Luna es una Golden Retriever de pelaje crema con ojos expresivos y dulces. Llegó a nuestro refugio cuando era una cachorra de apenas 3 meses. Ha crecido aquí y ha recibido entrenamiento básico de obediencia, conoce comandos como sentarse, quedarse y dar la pata. Luna es extremadamente leal y busca constantemente la aprobación de las personas. Es especialmente buena con los ancianos y tiene un temperamento tranquilo que la hace ideal como compañera.",
            imageRes = R.drawable.luna,
            videoUrl = "https://www.pexels.com/download/video/3191251/?fps=25.0&h=720&w=1366",
            personality = "Inteligente, leal, tranquila y afectuosa. Le gusta complacer a sus humanos.",
            healthStatus = "Todas las vacunas al día, esterilizada. Tuvo una leve infección de oído que ya está completamente curada.",
            requirements = "Hogar tranquilo donde reciba atención y cariño. No necesita tanto ejercicio como otras razas pero sí paseos diarios.",
            isAvailable = false
        ),

        // Gatos
        Pet(
            id = "cat1",
            name = "Mía",
            type = PetType.CAT,
            breed = "Siamés",
            age = "3 años",
            description = "Mía es una gata siamesa muy independiente pero cariñosa. Le encanta observar por la ventana.",
            detailedDescription = "Mía es una hermosa gata siamés con característicos ojos azules y pelaje beige con puntos oscuros. Fue rescatada de la calle cuando tenía aproximadamente un año de edad. Al principio era muy tímida, pero con paciencia y cuidados adecuados, se ha convertido en una compañera afectuosa aunque conserva su independencia típica de la raza. Le encanta sentarse en las ventanas para observar pájaros y disfrutar del sol. Por las noches, suele buscar compañía y ronronear junto a sus humanos.",
            imageRes = R.drawable.mia,
            videoUrl = "https://www.pexels.com/download/video/4459765/?fps=23.97599983215332&h=720&w=1280",
            personality = "Independiente pero afectuosa. Observadora y tranquila. Ocasionalmente juguetona.",
            healthStatus = "Esterilizada, vacunada y desparasitada. Excelente salud.",
            requirements = "Hogar tranquilo, preferiblemente sin niños pequeños. Necesita lugares elevados para trepar y observar.",
            isAvailable = true
        ),
        Pet(
            id = "cat2",
            name = "Oliver",
            type = PetType.CAT,
            breed = "Ragdoll",
            age = "8 meses",
            description = "Oliver es un gatito muy juguetón y sociable. Le encanta estar cerca de las personas.",
            detailedDescription = "Oliver es un joven gato Ragdoll de pelaje bicolor (blanco y gris) con ojos azules brillantes. Llegó a nuestro refugio junto con sus hermanos cuando apenas tenían unas semanas de vida. De la camada, Oliver siempre fue el más sociable y afectuoso. Como es típico en su raza, tiende a relajarse completamente cuando se le levanta (de ahí el nombre 'ragdoll' o muñeco de trapo). Es extremadamente cariñoso y disfruta siguiendo a las personas por toda la casa. Le encanta jugar con juguetes interactivos y aprender nuevos trucos.",
            imageRes = R.drawable.oliver,
            videoUrl = "https://www.pexels.com/download/video/10407809/?fps=24.0&h=720&w=1280",
            personality = "Juguetón, cariñoso, adaptable y extremadamente sociable. Le encanta la compañía.",
            healthStatus = "Vacunas al día, castrado y microchip implantado. Salud perfecta.",
            requirements = "Puede vivir en cualquier tipo de hogar, incluso apartamentos pequeños. Necesita compañía humana diaria y juguetes para estimularlo.",
            isAvailable = true
        ),

        // Ave
        Pet(
            id = "bird1",
            name = "Kiwi",
            type = PetType.BIRD,
            breed = "Periquito",
            age = "1 año",
            description = "Kiwi es un periquito muy colorido y cantarín. Puede aprender a repetir palabras.",
            detailedDescription = "Kiwi es un periquito australiano macho de colores verde y amarillo brillantes. Es un ave muy activa que disfruta cantando, especialmente por las mañanas y al atardecer. Ha sido socializado desde joven y es bastante manso, permitiendo que se le manipule con facilidad. Aunque todavía no ha aprendido a hablar, muestra interés cuando se le habla y con paciencia podría aprender a repetir algunas palabras. Le encanta investigar juguetes nuevos y es muy curioso por naturaleza.",
            imageRes = R.drawable.kiwi,
            videoUrl = "https://www.pexels.com/download/video/4229269/?fps=30.913999557495117&h=720&w=1280",
            personality = "Cantarín, curioso, activo y relativamente sociable. Tiene un carácter alegre.",
            healthStatus = "Excelente salud. Revisado por veterinario especializado en aves exóticas.",
            requirements = "Jaula espaciosa con juguetes y perchas. Necesita tiempo diario fuera de la jaula en un ambiente seguro. Ambiente limpio y sin corrientes de aire.",
            isAvailable = true
        ),

        // Pez
        Pet(
            id = "fish1",
            name = "Nemo",
            type = PetType.FISH,
            breed = "Pez payaso",
            age = "6 meses",
            description = "Nemo es un pez payaso muy activo y colorido. Ideal para acuarios pequeños.",
            detailedDescription = "Nemo es un vibrante pez payaso (Amphiprion ocellaris) con las características franjas naranjas y blancas. Ha sido criado en cautiverio, lo que significa que está bien adaptado a la vida en acuario. Es bastante activo y curioso, explora constantemente su entorno. Los peces payaso son conocidos por su comportamiento interesante, especialmente su relación simbiótica con las anémonas en la naturaleza. Aunque en un acuario doméstico no es necesaria una anémona, Nemo disfruta de espacios pequeños donde pueda sentirse seguro, como pequeñas cuevas o decoraciones.",
            imageRes = R.drawable.nemo,
            videoUrl = "https://www.pexels.com/download/video/5548246/?fps=25.0&h=720&w=1280",
            personality = "Activo, curioso y relativamente resistente comparado con otros peces marinos.",
            healthStatus = "Saludable. Criado en cautiverio, lo que lo hace más adaptable a entornos de acuario.",
            requirements = "Acuario de agua salada de al menos 20 galones, bien establecido y con parámetros de agua estables. Temperatura entre 24-26°C. Preferiblemente con otros peces payaso o especies compatibles.",
            isAvailable = true
        )
    )
}
