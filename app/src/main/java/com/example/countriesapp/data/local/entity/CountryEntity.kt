package com.example.countriesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    val name: String?,
    @PrimaryKey val id: Int? = null
)

