package com.lexwilliam.data_remote

import com.lexwilliam.data.model.remote.user.UserInfoRepo
import com.lexwilliam.data_remote.model.constants.ApiConstants
import com.lexwilliam.data_remote.model.constants.UserAnimeSortType
import com.lexwilliam.data_remote.model.anime.AnimeDetailResponse
import com.lexwilliam.data_remote.model.auth.AccessTokenResponse
import com.lexwilliam.data_remote.model.auth.OAuthGrantType
import com.lexwilliam.data_remote.model.user.UserAnimeListResponse
import com.lexwilliam.data_remote.model.user.UserAnimeUpdateResponse
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

    @PATCH("anime/{id}/my_list_status")
    @FormUrlEncoded
    suspend fun updateUserAnimeStatus(
        @Header("Authorization") authHeader: String,
        @Path("id") animeId: Int,
        @Field("status") animeStatus: String? = null,
        @Field("is_rewatching") isReWatching: Boolean? = null,
        @Field("score") score: Int? = null,
        @Field("num_watched_episodes") numWatchedEps: Int? = null,
        @Field("priority") priority: Int? = null,
        @Field("num_times_rewatched") numReWatchedEps: Int? = null,
        @Field("rewatch_value") reWatchValue: Int? = null,
        @Field("tags") tags: String? = null,
        @Field("comments") comments: String? = null
    ): Response<UserAnimeUpdateResponse>

    @DELETE("anime/{anime_id}/my_list_status")
    suspend fun deleteUserAnimeStatus(
        @Header("Authorization") authHeader: String,
        @Path("anime_id") animeId: Int
    )

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Header("Authorization") authHeader: String,
        @Path("anime_id") malId: Int,
        @Query("fields") fields: String? = ApiConstants.ANIME_ALL_FIELDS
    ): Response<AnimeDetailResponse>

    @GET("users/{username}/animelist")
    suspend fun getUserAnimeList(
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
    ): Response<UserInfoRepo>
}