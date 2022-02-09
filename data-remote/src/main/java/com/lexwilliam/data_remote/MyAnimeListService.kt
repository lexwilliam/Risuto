package com.lexwilliam.data_remote

import com.lexwilliam.data_remote.model.auth.AccessTokenResponse
import com.lexwilliam.data_remote.model.auth.OAuthGrantType
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyAnimeListService {

    companion object {
        const val AUTH_BASE_URL: String = "https://myanimelist.net/v1"

        fun getAuthTokenLink(
            clientId: String,
            state: String,
            codeChallenge: String,
            redirectUri: String
        ) = "$AUTH_BASE_URL/oauth2/authorize?response_type=code" +
                "&client_id=$clientId&state=$state&code_challenge=$codeChallenge&redirect_uri=$redirectUri"
    }

    @POST("$AUTH_BASE_URL/oauth2/token")
    @FormUrlEncoded
    fun refreshTokenAsync(
        @Field("client_id") clientId: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = OAuthGrantType.refresh_token.name
    ): AccessTokenResponse

    @POST("$AUTH_BASE_URL/oauth2/token")
    @FormUrlEncoded
    suspend fun getAccessTokenAsync(
        @Field("client_id") clientId: String,
        @Field("code") code: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("grant_type") grantType: String = OAuthGrantType.authorization_code.name,
        @Field("redirect_uri") redirectUri: String = "risuto://auth"
    ): Response<AccessTokenResponse>
}