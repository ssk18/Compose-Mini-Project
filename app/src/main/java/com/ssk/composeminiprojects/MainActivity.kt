package com.ssk.composeminiprojects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ssk.composeminiprojects.study_app_screen.StudyAppScreen
import com.ssk.composeminiprojects.study_app_screen.StudyAppScreenRoot
import com.ssk.composeminiprojects.study_app_screen.StudyAppViewModel
import com.ssk.composeminiprojects.study_app_screen.components.TopicsRow
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.utils.Topic

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<StudyAppViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMiniProjectsTheme {
                StudyAppScreenRoot(
                    viewModel = viewModel
                )
            }
        }
    }
}