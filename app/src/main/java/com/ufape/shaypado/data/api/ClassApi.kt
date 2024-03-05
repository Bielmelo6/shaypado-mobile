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
    @POST("/create_class")
    suspend fun addClasses(@Body data : List<CreateClassRequest>) : Response<Unit>

    @Headers("Content-Type: application/json")
    @PUT("/update_class")
    suspend fun updateClass(@Body data : UpdateClassRequest) : Response<ClassResponse>

    @Headers("Content-Type: application/json")
    @DELETE("/delete_class")
    suspend fun deleteClass(@Path("id") id : String) : Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/get_classes")
    suspend fun getClasses() : Response<List<ClassResponse>>

    @Headers("Content-Type: application/json")
    @GET("/get_class/{id}")
    suspend fun getClass(@Path("id") id: String) : Response<ClassResponse>
}

