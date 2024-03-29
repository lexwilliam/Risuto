package com.lexwilliam.data_remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class RateLimitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())

        if (!response.isSuccessful && response.code == 429) {
            try {
                Timber.e("You are being rate limited or Jikan is being rate limited by MyAnimeList, retrying in 2 seconds...")
                Thread.sleep(2000L)
            } catch (e: InterruptedException) {
                Timber.e("Rate Limiting Error")
            }

            response = chain.proceed(chain.request())
        }

        return response
    }
}