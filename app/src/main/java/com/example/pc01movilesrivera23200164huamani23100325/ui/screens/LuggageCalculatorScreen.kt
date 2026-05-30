package com.example.pc01movilesrivera23200164huamani23100325.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuggageCalculatorScreen(navController: NavController) {
    var weight by remember { mutableStateOf("") }
    var flightType by remember { mutableStateOf("Nacional") }
    var result by remember { mutableStateOf<CalculationResult?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val flightOptions = listOf("Nacional", "Internacional")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Equipaje", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = weight,
                onValueChange = { input ->
                    // Only allow digits and at most one decimal point
                    if (input.isEmpty() || input.all { it.isDigit() || it == '.' }) {
                        val decimalCount = input.count { it == '.' }
                        if (decimalCount <= 1) {
                            weight = input
                            error = null
                        }
                    }
                },
                label = { Text("Peso de la maleta (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = error != null
            )

            if (error != null) {
                Text(error!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }

            Text("Tipo de vuelo:", fontWeight = FontWeight.SemiBold)
            
            Column(Modifier.selectableGroup()) {
                flightOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == flightType),
                                onClick = { flightType = text },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == flightType),
                            onClick = null // null recommended for accessibility with selectable modifier
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    val weightVal = weight.toDoubleOrNull()
                    if (weightVal == null || weightVal <= 0) {
                        error = "Ingrese un peso válido mayor a cero"
                        result = null
                    } else {
                        val limit = if (flightType == "Nacional") 23.0 else 32.0
                        val excess = if (weightVal > limit) weightVal - limit else 0.0
                        result = CalculationResult(
                            isCompliant = weightVal <= limit,
                            excess = excess
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Calcular", fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(16.dp))

            result?.let { res ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (res.isCompliant) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                    )
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            if (res.isCompliant) "Cumple el límite permitido" else "Excede el límite permitido",
                            fontWeight = FontWeight.Bold,
                            color = if (res.isCompliant) Color(0xFF2E7D32) else Color(0xFFC62828)
                        )
                        if (!res.isCompliant) {
                            Text(
                                "Cantidad de kg excedidos: ${"%.2f".format(res.excess)} kg",
                                color = Color(0xFFC62828)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class CalculationResult(val isCompliant: Boolean, val excess: Double)
