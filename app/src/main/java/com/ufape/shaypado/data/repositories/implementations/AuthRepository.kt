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
import com.ufape.shaypado.ui.model.UploadData
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
        val res = api.registerTrainer(data)
        return if (res.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun updateTrainer(data: TrainerRequest): Result<Unit> {
        val res = api.updateTrainer(data)
        return if (res.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun updateUser(data: UserRequest): Result<Unit> {
        val res = api.updateUser(data)
        return if (res.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun uploadProfilePicture(file: String): Result<UploadData> {
        val imageFile = File(file)
        val imageRequestFile = imageFile.toImageMultiPartBodyPart("profilePicture")

        val res = api.uploadProfilePicture(imageRequestFile)
        return if (res.isSuccessful) {
            Result.Success(res.body()!!.toUiModel())
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun uploadPlansDocument(file: String): Result<UploadData> {
        val plansFile = File(file)
        val plansRequestFile = plansFile.toPdfMultiPartBodyPart("plansDocument")

        val res = api.uploadPlansDocument(plansRequestFile)
        return if (res.isSuccessful) {
            Result.Success(res.body()!!.toUiModel())
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