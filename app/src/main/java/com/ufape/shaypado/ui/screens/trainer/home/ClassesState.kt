package com.ufape.shaypado.ui.screens.trainer.home

data class ClassesState(
    val classes: List<Class> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class Class(
    val id: String,
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val duration: String,
    val students: List<Student>
)

data class Student(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val weight: String,
    val height: String,
    val age: String
)
