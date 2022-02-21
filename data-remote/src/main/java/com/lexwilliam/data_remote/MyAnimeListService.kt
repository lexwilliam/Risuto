package com.lexwilliam.data_remote

import com.lexwilliam.data_remote.model.ApiConstants
import com.lexwilliam.data_remote.model.UserAnimeSortType
import com.lexwilliam.data_remote.model.auth.AccessTokenResponse
import com.lexwilliam.data_remote.model.auth.OAuthGrantType
import com.lexwilliam.data_remote.model.detail.AnimeDetailResponse
import com.lexwilliam.data_remote.model.detail.MyAnimeStatusResponse
import com.lexwilliam.data_remote.model.user.UserAnimeListResponse
import com.lexwilliam.data_remote.model.user.UserInfoResponse
import retrofit2.Response
import retrofit2.http.*

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
    suspend fun refreshToken(
        @Field("client_id") clientId: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = OAuthGrantType.refresh_token.name
    ): Response<AccessTokenResponse>

    @POST("$AUTH_BASE_URL/oauth2/token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("code") code: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("grant_type") grantType: String = OAuthGrantType.authorization_code.name,
        @Field("redirect_uri") redirectUri: String = "risuto://auth"
    ): Response<AccessTokenResponse>

    @GET("anime/{mal_id}")
    suspend fun getMyAnimeStatus(
        @Header("Authorization") authHeader: String,
        @Path("mal_id") malId: Int,
        @Query("fields") fields: String = "my_list_status"
    ): Response<MyAnimeStatusResponse>

    @GET("users/{username}/animelist")
    suspend fun getAnimeListOfUser(
        @Header("Authorization") authHeader: String,
        @Path("username") username: String = ApiConstants.ME_IDENTIFIER,
        @Query("status") status: String? = null,
        @Query("sort") sort: String = UserAnimeSortType.list_updated_at.name,
        @Query("limit") limit: Int = ApiConstants.API_PAGE_LIMIT,
        @Query("offset") offset: Int = ApiConstants.API_START_OFFSET,
        @Query("nsfw") nsfw: Int = ApiConstants.NSFW_ALSO,
        @Query("fields") fields: String = ApiConstants.USER_ANIME_EXTRA_FIELDS
    ): Response<UserAnimeListResponse>

    @GET("users/{username}")
    suspend fun getUserInfo(
        @Header("Authorization") authHeader: String,
        @Path("username") username: String = "@me"
    ): Response<UserInfoResponse>
}