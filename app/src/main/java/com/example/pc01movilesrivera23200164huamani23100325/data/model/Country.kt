package com.example.pc01movilesrivera23200164huamani23100325.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "name")
    val name: CountryName?,

    @Json(name = "flags")
    val flags: Flags?,

    @Json(name = "capital")
    val capital: List<String>?,

    @Json(name = "region")
    val region: String?,

    @Json(name = "population")
    val population: Long?,

    @Json(name = "cca2")
    val cca2: String?
)

@JsonClass(generateAdapter = true)
data class CountryName(
    @Json(name = "common")
    val common: String?,

    @Json(name = "official")
    val official: String?
)

@JsonClass(generateAdapter = true)
data class Flags(
    @Json(name = "png")
    val png: String?,

    @Json(name = "svg")
    val svg: String?,

    @Json(name = "alt")
    val alt: String?
)

