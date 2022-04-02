package com.example.countriesapp.data.remote.dto

import com.example.countriesapp.data.remote.dto.utilDto.*
import com.example.countriesapp.domain.model.CountryDetail

data class CountryDetailDto(
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val capital: List<String>,
    val capitalInfo: CapitalInfo,
    val car: Car,
    val cca2: String,
    val cca3: String,
    val ccn3: String,
    val cioc: String,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    val currencies: Currencies,
    val demonyms: Demonyms,
    val fifa: String,
    val flag: String,
    val flags: Flags,
    val gini: Gini,
    val idd: Idd,
    val independent: Boolean,
    val landlocked: Boolean,
    val languages: Languages,
    val latlng: List<Double>,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val postalCode: PostalCode,
    val region: String,
    val startOfWeek: String,
    val status: String,
    val subregion: String,
    val timezones: List<String>,
    val tld: List<String>,
    val translations: Translations,
    val unMember: Boolean
)

fun CountryDetailDto.toCountryDetail() : CountryDetail {
    return CountryDetail(
        altSpellings = altSpellings,
        area = area,
        borders = borders,
        capital = capital,
        capitalInfo = capitalInfo,
        car = car,
        cca2 = cca2,
        cca3 = cca3,
        ccn3 = ccn3,
        cioc = cioc,
        coatOfArms = coatOfArms,
        continents = continents,
        currencies = currencies,
        demonyms = demonyms,
        fifa = fifa,
        flag = flag,
        flags = flags,
        gini = gini,
        idd = idd,
        independent = independent,
        landlocked = landlocked,
        languages = languages,
        latlng = latlng,
        maps = maps,
        name = name,
        population = population,
        postalCode = postalCode,
        region = region,
        startOfWeek = startOfWeek,
        status = status,
        subregion = subregion,
        timezones = timezones,
        tld = tld,
        translations = translations,
        unMember = unMember

    )

}