package com.ufape.shaypado.ui.model

import com.ufape.shaypado.data.model.CreateClassRequest


data class ClassState(
    val id: String = "",
    val name: String = "",
    val daysOfWeek: List<Boolean> = listOf(false, false, false, false, false, false, false),
    val startTime: String = "",
    val endTime: String = "",
    val students: List<FriendState> = listOf(),
    val workouts: List<ClassWorkoutState> = listOf()
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

fun ClassState.toCreateRequest() = CreateClassRequest(
    name = name,
    daysOfWeek = daysOfWeek,
    startTime = startTime,
    endTime = endTime,
    students = students.map { it.friendshipCode },
    workouts = workouts.map { it.id }
)