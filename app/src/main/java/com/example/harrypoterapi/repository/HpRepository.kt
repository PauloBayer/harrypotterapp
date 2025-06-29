package com.example.harrypoterapi.repository

import com.example.harrypoterapi.data.remote.ApiClient

class HpRepository {
    private val api = ApiClient.api

    suspend fun character(id: Int) = api.getCharacterById(id)
    suspend fun staff() = api.getHogwartsStaff()
    suspend fun students(house: String) = api.getStudentsByHouse(house)
    suspend fun spells() = api.getAllSpells()
}