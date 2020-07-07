package com.example.kotlintest

import com.example.kotlintest.entities.ChangeCoinItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
        @GET("latest?base=EUR")
        fun getEUR(): Call<ChangeCoinItem>

        @GET("latest?base=USD")
        fun getUSD(): Call<ChangeCoinItem>
}