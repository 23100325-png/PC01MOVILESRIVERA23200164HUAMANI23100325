package com.example.pc01movilesrivera23200164huamani23100325.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun LocationPermissionScreen() {
    val context = LocalContext.current

    // Determinar el estado inicial del permiso
    val hasFineLocation = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
    val hasCoarseLocation = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    var permissionStatus by remember {
        mutableStateOf(
            if (hasFineLocation || hasCoarseLocation) "Permiso concedido"
            else "Permiso pendiente de solicitud"
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                        permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)

        permissionStatus = if (isGranted) {
            "Permiso concedido"
        } else {
            "Permiso denegado"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Asistencia de Viaje",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Esta pantalla permite gestionar los permisos necesarios para ofrecerte asistencia durante tu viaje basada en tu ubicación.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Text(
            text = "Estado actual:",
            style = MaterialTheme.typography.labelLarge
        )

        Text(
            text = permissionStatus,
            style = MaterialTheme.typography.headlineSmall,
            color = when (permissionStatus) {
                "Permiso concedido" -> MaterialTheme.colorScheme.primary
                "Permiso denegado" -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.secondary
            },
            modifier = Modifier.padding(top = 8.dp, bottom = 48.dp)
        )

        Button(
            onClick = {
                launcher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = permissionStatus != "Permiso concedido"
        ) {
            Text(
                text = if (permissionStatus == "Permiso concedido") "Permiso ya otorgado"
                       else "Solicitar Permiso de Ubicación"
            )
        }
    }
}
