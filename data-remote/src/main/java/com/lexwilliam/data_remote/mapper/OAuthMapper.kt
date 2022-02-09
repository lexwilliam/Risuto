package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.auth.AccessTokenRepo
import com.lexwilliam.data_remote.model.auth.AccessTokenResponse
import javax.inject.Inject

interface OAuthMapper {
    fun toRepo(token: AccessTokenResponse): AccessTokenRepo
}

class OAuthMapperImpl @Inject constructor(): OAuthMapper {
    override fun toRepo(token: AccessTokenResponse): AccessTokenRepo =
        AccessTokenRepo(token.accessToken, token.expiresIn, token.refreshToken, token.tokenType)

}