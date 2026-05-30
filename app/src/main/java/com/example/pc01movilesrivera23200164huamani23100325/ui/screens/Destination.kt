package com.example.pc01movilesrivera23200164huamani23100325.ui.screens

data class Destination(
    val country: String,
    val city: String,
    val averageCost: Double,
    val imageUrl: String,
    val flagUrl: String
)

val mockDestinations = listOf(
    Destination("Perú", "Cusco", 500.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6A6A5C2r5Jm2G-5R8Q7I7Z9p_L-r7u_6r-Q&s", "https://flagcdn.com/w320/pe.png"),
    Destination("Francia", "París", 1200.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6-6R9n_f-2u-V_8_3-u-v-W-G-R-v-w-G-w&s", "https://flagcdn.com/w320/fr.png"),
    Destination("Japón", "Tokio", 1500.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_6u-2-p-v-G-R-v-w-G-R-v-w-G-w&s", "https://flagcdn.com/w320/jp.png"),
    Destination("Italia", "Roma", 900.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_6u-2-p-v-G-R-v-w-G-R-v-w-G-w&s", "https://flagcdn.com/w320/it.png"),
    Destination("México", "Cancún", 700.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_6u-2-p-v-G-R-v-w-G-R-v-w-G-w&s", "https://flagcdn.com/w320/mx.png"),
    Destination("España", "Madrid", 1100.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6-6R9n_f-2u-V_8_3-u-v-W-G-R-v-w-G-w&s", "https://flagcdn.com/w320/es.png"),
    Destination("Brasil", "Rio de Janeiro", 850.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_6u-2-p-v-G-R-v-w-G-R-v-w-G-w&s", "https://flagcdn.com/w320/br.png")
)
