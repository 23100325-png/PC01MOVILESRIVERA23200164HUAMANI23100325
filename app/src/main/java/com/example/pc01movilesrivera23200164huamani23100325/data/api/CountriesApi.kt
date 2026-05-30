package com.example.pc01movilesrivera23200164huamani23100325.data.api

import com.example.pc01movilesrivera23200164huamani23100325.data.model.Country
import retrofit2.http.GET

interface CountriesApi {
    @GET("all?fields=name,flags,capital,region,population,cca2")
    suspend fun getAllCountries(): List<Country>
}

