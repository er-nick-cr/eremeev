package com.example.eremeev.data.datasource.network

import com.example.eremeev.BuildConfig.TOKEN
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class FilmsTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .header("X-API-KEY", TOKEN)
            .build()

        return chain.proceed(request)
    }
}