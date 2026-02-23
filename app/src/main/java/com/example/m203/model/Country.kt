package com.example.m203.model

data class Country(
    val code : String,
    val name: String,
    val flag: String
)
data class CountryInfo(
    val code : String,
    val name: CountryName,
    val flag: CountryFlag
){
    override fun toString(): String {
        return name.common
    }

    fun toCountry(): Country {
        return Country(code, name.common, flag.png)
    }
}

data class CountryName(
    val common: String,
    val official: String
)
data class CountryFlag(
    val png: String,
    val svg: String,
    val alt: String
)

val countriesInfos = mutableListOf<CountryInfo>(
    CountryInfo("FR", CountryName("France", "French Republic"), CountryFlag("https://flagcdn.com/w320/fr.png", "https://flagcdn.com/fr.svg", "The flag of France is composed of three vertical bands of equal width, displaying the national colors of France: blue, white, and red.")),
    CountryInfo("DE", CountryName("Germany", "Federal Republic of Germany"), CountryFlag("https://flagcdn.com/w320/de.png", "https://flagcdn.com/de.svg", "The flag of Germany consists of three horizontal bands of equal width, displaying the national colors of Germany: black, red, and gold.")),
    CountryInfo("IT", CountryName("Italy", "Italian Republic"), CountryFlag("https://flagcdn.com/w320/it.png", "https://flagcdn.com/it.svg", "The flag of Italy is composed of three vertical bands of equal width, displaying the national colors of Italy: green, white, and red."))
)
val countries = countriesInfos.map { it.toCountry() }.toMutableList()

val countryInfo = CountryInfo("FR", CountryName("France", "French Republic"), CountryFlag("https://flagcdn.com/w320/fr.png", "https://flagcdn.com/fr.svg", "The flag of France is composed of three vertical bands of equal width, displaying the national colors of France: blue, white, and red."))
val name = countryInfo.name.common
val flag = countryInfo.flag.png