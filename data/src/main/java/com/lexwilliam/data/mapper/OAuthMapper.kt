package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.auth.AccessTokenRepo
import com.lexwilliam.domain.model.remote.auth.AccessToken
import javax.inject.Inject

interface OAuthMapper {
    fun toDomain(token: AccessTokenRepo): AccessToken
    fun toRepo(token: AccessToken): AccessTokenRepo
}

class OAuthMapperImpl @Inject constructor(): OAuthMapper {
    override fun toDomain(token: AccessTokenRepo): AccessToken =
        AccessToken(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            expiresIn = token.expiresIn,
            tokenType = token.tokenType
        )

    override fun toRepo(token: AccessToken): AccessTokenRepo =
        AccessTokenRepo(
            accessToken = token.accessToken?:"",
            refreshToken = token.refreshToken?:"",
            expiresIn = token.expiresIn?:-1,
            tokenType = token.tokenType
        )

}