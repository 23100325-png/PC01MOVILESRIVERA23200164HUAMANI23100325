package com.example.pc01movilesrivera23200164huamani23100325
//Romario Huamani Paccaya
//Alvaro Rivera Tarque
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pc01movilesrivera23200164huamani23100325.navigation.AppNavigation
import com.example.pc01movilesrivera23200164huamani23100325.ui.theme.PC01MOVILESRIVERA23200164HUAMANI23100325Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC01MOVILESRIVERA23200164HUAMANI23100325Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
