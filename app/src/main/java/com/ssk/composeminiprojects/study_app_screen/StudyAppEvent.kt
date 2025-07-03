package com.ssk.composeminiprojects.study_app_screen

sealed interface StudyAppEvent {
    data class ShowSnackBar(val message: String) : StudyAppEvent
    data class NavigateToStudyDetails(val title: String, val category: String) : StudyAppEvent
}