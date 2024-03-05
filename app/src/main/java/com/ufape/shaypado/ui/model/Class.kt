package com.ufape.shaypado.ui.model


data class ClassState(
    val id: String,
    val name: String,
    val daysOfWeek: List<Boolean>,
    val startTime: String,
    val endTime: String,
    val students: List<ClassStudentState>,
    val workouts: List<ClassWorkoutState>
)

data class ClassStudentState(
    val id: String,
    val name: String
)

data class ClassWorkoutState(
    val id: String,
    val categoryId: String,
    val category: String,
)