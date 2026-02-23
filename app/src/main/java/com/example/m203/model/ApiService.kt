package com.example.m203.model

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val  BASE_URL = "https://restcountries.com/v3.1/all/"
const val FIELDS = "fields=cca2,name,flags"
interface ApiService {
@GET("/")
suspend fun getCountries(@Query("fields") fields : String = FIELDS): List<CountryInfo>

}

object RetrofitClient {
    val apiService: ApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java) // ApiService : le nom de l'interface que tu as créée pour définir les endpoints de ton API
    }
}