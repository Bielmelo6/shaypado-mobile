package com.ufape.shaypado.data.repositories.interfaces

interface IUserRepository {
    fun fetchWorkouts()
    fun fetchWorkout()

    fun concludeWorkout()
    fun concludeExercise()

    fun fetchProfessionals()

    fun fetchProfessional()
}