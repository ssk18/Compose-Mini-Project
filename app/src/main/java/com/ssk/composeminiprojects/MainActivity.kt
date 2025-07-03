package com.ssk.composeminiprojects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ssk.composeminiprojects.navigation.StudyAppNavigation
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMiniProjectsTheme {
                StudyAppNavigation()
            }
        }
    }
}