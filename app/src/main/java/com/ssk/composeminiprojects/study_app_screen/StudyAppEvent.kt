package com.ssk.composeminiprojects.study_app_screen

sealed interface StudyAppEvent {
    data class ShowSnackBar(val message: String) : StudyAppEvent
}