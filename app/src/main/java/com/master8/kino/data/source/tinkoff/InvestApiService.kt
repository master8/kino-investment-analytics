package com.master8.kino.data.source.tinkoff

import com.master8.kino.data.source.tinkoff.dto.CandlesResponseDto
import com.master8.kino.data.source.tinkoff.dto.OperationsResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface InvestApiService {

    @Headers("Authorization: Bearer ${TinkoffApiToken.API_TOKEN}")
    @GET("market/candles")
    suspend fun getCandles(
        @Query("figi") figi: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("interval") internal: String
    ): CandlesResponseDto

    @Headers("Authorization: Bearer ${TinkoffApiToken.API_TOKEN}")
    @GET("operations")
    suspend fun getOperations(
        @Query("figi") figi: String,
        @Query("from") from: String = "2020-01-01T00:00:00.0+03:00",
        @Query("to") to: String = "2020-12-04T21:00:00.0+03:00"
    ): OperationsResponseDto
}