package com.example.countriesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countriesapp.data.local.entity.CountryEntity
import com.example.countriesapp.domain.model.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM CountryEntity")
    suspend fun getAllCountries(): List<CountryEntity>

    suspend fun upsert(country:CountryEntity) {
        if(country.name?.let { getDuplicates(it).isEmpty() } == true) {
            upsertUtil(country)
        }
    }

    @Query("SELECT name FROM CountryEntity WHERE name = :name")
    suspend fun getDuplicates(name: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUtil(country: CountryEntity): Long

    suspend fun deleteCountry(country : String?) {
        country?.let { deletehelper(it) }
    }

    @Query("DELETE FROM CountryEntity WHERE name = :name")
    suspend fun deletehelper( name:String)
}