package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.ClassResponse
import com.ufape.shaypado.data.model.CreateClassRequest
import com.ufape.shaypado.data.model.UpdateClassRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ClassApi {
    @Headers("Content-Type: application/json")
    @POST("/classes")
    suspend fun addClasses(@Body data : List<CreateClassRequest>) : Response<Unit>

    @Headers("Content-Type: application/json")
    @PUT("/classes/{id}")
    suspend fun updateClass(@Path("id") id : String, @Body data : UpdateClassRequest) : Response<ClassResponse>

    @Headers("Content-Type: application/json")
    @DELETE("/classes/{id}")
    suspend fun deleteClass(@Path("id") id : String) : Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/classes")
    suspend fun getClasses() : Response<List<ClassResponse>>

    @Headers("Content-Type: application/json")
    @GET("/classes/{id}")
    suspend fun getClass(@Path("id") id: String) : Response<ClassResponse>
}

