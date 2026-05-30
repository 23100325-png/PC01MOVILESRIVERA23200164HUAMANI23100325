package com.example.pc01movilesrivera23200164huamani23100325.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pc01movilesrivera23200164huamani23100325.data.api.RetrofitClient
import com.example.pc01movilesrivera23200164huamani23100325.data.model.Country
import kotlinx.coroutines.launch

@Composable
fun CountriesScreen() {
    val scope = rememberCoroutineScope()
    var countries by remember { mutableStateOf<List<Country>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            countries = RetrofitClient.countriesApi.getAllCountries()
                .sortedBy { it.name?.common ?: "Z" }
            isLoading = false
        } catch (e: Exception) {
            errorMessage = "Error: ${e.message}"
            isLoading = false
            android.util.Log.e("CountriesScreen", "Error loading countries", e)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errorMessage ?: "Error desconocido",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = {
                            isLoading = true
                            errorMessage = null
                            scope.launch {
                                try {
                                    countries = RetrofitClient.countriesApi.getAllCountries()
                                        .sortedBy { it.name?.common ?: "Z" }
                                    isLoading = false
                                } catch (e: Exception) {
                                    errorMessage = "Error: ${e.message}"
                                    isLoading = false
                                }
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Reintentar")
                    }
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(countries) { country ->
                        CountryCard(country)
                    }
                }
            }
        }
    }
}

@Composable
fun CountryCard(country: Country) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Bandera
            if (!country.flags?.png.isNullOrEmpty()) {
                AsyncImage(
                    model = country.flags?.png,
                    contentDescription = "Bandera de ${country.name?.common}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )
            }

            // Información del país
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Nombre
                Text(
                    text = country.name?.common ?: "Desconocido",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Región
                if (!country.region.isNullOrEmpty()) {
                    Text(
                        text = "🌍 ${country.region}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Capital
                if (!country.capital.isNullOrEmpty()) {
                    Text(
                        text = "🏛️ ${country.capital?.firstOrNull() ?: "N/A"}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Población
                if (country.population != null && country.population!! > 0) {
                    Text(
                        text = "👥 ${formatPopulation(country.population!!)}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

fun formatPopulation(population: Long): String {
    return when {
        population >= 1_000_000 -> "${population / 1_000_000}M"
        population >= 1_000 -> "${population / 1_000}K"
        else -> population.toString()
    }
}

