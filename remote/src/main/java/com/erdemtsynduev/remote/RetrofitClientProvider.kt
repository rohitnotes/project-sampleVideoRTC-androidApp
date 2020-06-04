package com.erdemtsynduev.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val BASE_URL = "https://appr.tc"

internal fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(CleanGsonConverter.create(gson))
        .build()
}
