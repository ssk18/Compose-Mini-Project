package com.ssk.composeminiprojects.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.ssk.composeminiprojects.study_app_details.StudyAppDetailsScreen
import com.ssk.composeminiprojects.study_app_screen.StudyAppScreenRoot
import com.ssk.composeminiprojects.study_app_screen.StudyAppViewModel

@Composable
fun StudyAppNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(StudyTopics)
    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is StudyTopics -> {
                    NavEntry(
                        key = key
                    ) {
                        val viewModel: StudyAppViewModel = viewModel()
                        StudyAppScreenRoot(
                            viewModel = viewModel,
                            navigateToDetails = { title: String, category: String ->
                                backStack.add(StudyDetails(title, category))
                            }
                        )
                    }
                }

                is StudyDetails -> {
                    NavEntry(
                        key = key
                    ) {
                        StudyAppDetailsScreen(
                            onBackClick = {
                                backStack.removeLastOrNull()
                            },
                            title = key.title,
                            topic = key.category,
                            modifier = modifier
                        )
                    }
                }

                else -> {}
            } as NavEntry<NavKey>
        }
    )
}