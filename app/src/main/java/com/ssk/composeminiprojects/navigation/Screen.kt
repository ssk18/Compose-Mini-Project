package com.ssk.composeminiprojects.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object StudyTopics: NavKey

@Serializable
data class StudyDetails(val title: String, val category: String): NavKey