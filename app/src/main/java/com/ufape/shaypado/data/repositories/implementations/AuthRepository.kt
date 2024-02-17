package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.local.ISessionManager
import com.ufape.shaypado.data.model.LoginRequest
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.TrainerRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.ui.model.LoginData
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getApiError
import com.ufape.shaypado.util.toImageMultiPartBodyPart
import com.ufape.shaypado.util.toPdfMultiPartBodyPart
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class AuthRepository(
    private val api: AuthApi,
    private val local: ISessionManager
) : IAuthRepository {
    override suspend fun login(data: LoginRequest): Result<LoginData> {
        val res = api.login(data)
        return if (res.isSuccessful) {
            saveUser(res.body()!!)
            Result.Success(res.body()!!.toUiModel())
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun registerUser(data: UserRequest): Result<Unit> {
        val res = api.registerUser(data)
        return if (res.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun registerTrainer(data: TrainerRequest): Result<Unit> {
        val imageFile = data.profilePicture?.let { File(it) }
        val imageRequestFile = imageFile?.toImageMultiPartBodyPart("profile_picture")

        val plansFile = File(data.plansDocument)
        val plansRequestFile = plansFile.toPdfMultiPartBodyPart("plans_document")

        val res = api.registerTrainer(
            profilePicture = imageRequestFile,
            name = data.name.toRequestBody("text/plain".toMediaTypeOrNull()),
            email = data.email.toRequestBody("text/plain".toMediaTypeOrNull()),
            password = data.password.toRequestBody("text/plain".toMediaTypeOrNull()),
            userType = data.userType.toRequestBody("text/plain".toMediaTypeOrNull()),
            fullName = data.fullName.toRequestBody("text/plain".toMediaTypeOrNull()),
            contactEmail = data.contactEmail.toRequestBody("text/plain".toMediaTypeOrNull()),
            contactPhone = data.contactPhone.toRequestBody("text/plain".toMediaTypeOrNull()),
            specialties = data.specialties.toRequestBody("text/plain".toMediaTypeOrNull()),
            age = data.age.toRequestBody("text/plain".toMediaTypeOrNull()),
            state = data.state.toRequestBody("text/plain".toMediaTypeOrNull()),
            city = data.city.toRequestBody("text/plain".toMediaTypeOrNull()),
            workLocation = data.workLocation?.toRequestBody("text/plain".toMediaTypeOrNull()),
            plansDocument = plansRequestFile
        )
        return if (res.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(res.getApiError())
        }
    }

    override fun logout() {
        local.removeUser()
    }

    override fun saveUser(user: LoginResponse) {
        local.saveUser(user)
    }

    override fun fetchUser(): LoginData? {
        return local.fetchUser()?.toUiModel()
    }

    override fun fetchAuthToken(): String? {
        return local.fetchAuthToken()
    }
}