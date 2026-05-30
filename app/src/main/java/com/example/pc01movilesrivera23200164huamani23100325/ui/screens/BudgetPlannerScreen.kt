package com.example.pc01movilesrivera23200164huamani23100325.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetPlannerScreen(navController: NavController) {
    var days by remember { mutableStateOf("") }
    var dailyBudget by remember { mutableStateOf("") }
    var accommodationType by remember { mutableStateOf("Estándar") }
    var expanded by remember { mutableStateOf(false) }
    var totalBudget by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val factors = mapOf(
        "Económico" to 0.8,
        "Estándar" to 1.0,
        "Premium" to 1.5
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planificador de Presupuesto", fontWeight = FontWeight.Bold) },
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
                value = days,
                onValueChange = { input ->
                    // Only allow digits for integer days
                    if (input.isEmpty() || input.all { it.isDigit() }) {
                        days = input
                        error = null
                    }
                },
                label = { Text("Cantidad de días") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = dailyBudget,
                onValueChange = { input ->
                    // Only allow digits and at most one decimal point
                    if (input.isEmpty() || input.all { it.isDigit() || it == '.' }) {
                        val decimalCount = input.count { it == '.' }
                        if (decimalCount <= 1) {
                            dailyBudget = input
                            error = null
                        }
                    }
                },
                label = { Text("Presupuesto diario ($)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = accommodationType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de alojamiento") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    factors.keys.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                accommodationType = type
                                expanded = false
                            }
                        )
                    }
                }
            }

            if (error != null) {
                Text(error!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }

            Button(
                onClick = {
                    val daysVal = days.toIntOrNull()
                    val budgetVal = dailyBudget.toDoubleOrNull()
                    
                    if (daysVal == null || daysVal <= 0 || budgetVal == null || budgetVal <= 0) {
                        error = "Ingrese valores válidos mayores a cero"
                        totalBudget = null
                    } else {
                        val factor = factors[accommodationType] ?: 1.0
                        totalBudget = daysVal * budgetVal * factor
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Calcular Presupuesto", fontWeight = FontWeight.Bold)
            }

            totalBudget?.let { total ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Presupuesto Total", fontWeight = FontWeight.Bold)
                        Text(
                            "$ ${"%.2f".format(total)}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            "Escenario: $accommodationType para $days días",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
