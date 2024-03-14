package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.IaApi
import com.ufape.shaypado.data.model.BodyFatRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IIaRepository
import com.ufape.shaypado.ui.model.BodyFatState
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getApiError
import com.ufape.shaypado.util.toImageMultiPartBodyPart
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class IaRepository (
    private val api : IaApi
) : IIaRepository {
    override suspend fun fetchBodyFat(request: BodyFatRequest): Result<BodyFatState> {

        val imageFile = File(request.image)
        val imageRequestFile = imageFile.toImageMultiPartBodyPart("image")

        val tempGender = if (request.gender == "M") "male" else "female"

        val height = request.height.toRequestBody(MultipartBody.FORM)
        val gender = tempGender.toRequestBody(MultipartBody.FORM)
        val result = api.fetchBodyFat(
            height = height,
            gender  = gender,
            image = imageRequestFile
        )
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }
}