package com.example.harrypoterapi.data.remote

import com.example.harrypoterapi.data.model.Spell
import com.example.harrypoterapi.data.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {

    @GET("characters")
    suspend fun getAllCharacters(): List<Character>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: String): List<Character>

    @GET("characters/staff")
    suspend fun getHogwartsStaff(): List<Character>

    @GET("characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): List<Character>

    @GET("spells")
    suspend fun getAllSpells(): List<Spell>
}