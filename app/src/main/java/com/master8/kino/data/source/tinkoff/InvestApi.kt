package com.master8.kino.data.source.tinkoff

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat

private const val BASE_URL = "https://api-invest.tinkoff.ru/openapi/"

fun createInvestApiService(): InvestApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(InvestApiService::class.java)
}