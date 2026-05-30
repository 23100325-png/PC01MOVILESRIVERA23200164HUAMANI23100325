package com.example.pc01movilesrivera23200164huamani23100325

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pc01movilesrivera23200164huamani23100325.ui.screens.CatalogScreen
import com.example.pc01movilesrivera23200164huamani23100325.ui.screens.LocationPermissionScreen
import com.example.pc01movilesrivera23200164huamani23100325.ui.theme.PC01MOVILESRIVERA23200164HUAMANI23100325Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC01MOVILESRIVERA23200164HUAMANI23100325Theme {
                var currentScreen by remember { mutableStateOf(3) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(onClick = { currentScreen = 3 }) {
                                    Text("Catálogo")
                                }
                                Button(onClick = { currentScreen = 4 }) {
                                    Text("Ubicación")
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            3 -> CatalogScreen()
                            4 -> LocationPermissionScreen()
                        }
                    }
                }
            }
        }
    }
}
