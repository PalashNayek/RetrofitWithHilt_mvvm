package com.palash.retrofitwithhilt_mvvm.api_service

import com.palash.retrofitwithhilt_mvvm.models.auth_user_response.AuthUserResponse
import com.palash.retrofitwithhilt_mvvm.models.refresh_token.RefreshTokenRequest
import com.palash.retrofitwithhilt_mvvm.models.refresh_token.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthorisedAPI {

    @GET("auth/me")
    suspend fun getAuthUser(): Response<AuthUserResponse>

    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Response<RefreshTokenResponse>
}