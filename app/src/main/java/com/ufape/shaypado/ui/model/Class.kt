package com.ufape.shaypado.ui.model

import com.ufape.shaypado.data.model.CreateClassRequest
import com.ufape.shaypado.data.model.UpdateClassRequest


data class ClassState(
    val id: String = "",
    val name: String = "",
    val daysOfWeek: List<Boolean> = listOf(false, false, false, false, false, false, false),
    val startTime: String = "",
    val endTime: String = "",
    val students: List<FriendState> = listOf(),
    val workouts: List<ClassWorkoutState> = listOf()
)

data class ClassWorkoutState(
    val id: String,
    val categoryId: String,
    val category: String,
    val title : String,
)

fun ClassState.toCreateRequest() = CreateClassRequest(
    name = name,
    daysOfWeek = daysOfWeek,
    startTime = startTime,
    endTime = endTime,
    students = students.map { it.friendshipCode },
    workouts = workouts.map { it.id }
)

fun ClassState.toUpdateRequest() = UpdateClassRequest(
    id = id,
    name = name,
    daysOfWeek = daysOfWeek,
    startTime = startTime,
    endTime = endTime,
    students = students.map { it.friendshipCode },
    workouts = workouts.map { it.id }
)