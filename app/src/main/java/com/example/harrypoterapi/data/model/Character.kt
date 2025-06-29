package com.example.harrypoterapi.data.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: String,
    val name: String,
    val species: String? = null,
    val gender: String? = null,
    val house: String? = null,
    val wizard: Boolean? = null,
    val ancestry: String? = null,
    val eyeColour: String? = null,
    val hairColour: String? = null,
    val patronus: String? = null,
    val actor: String? = null,
    val alive: Boolean? = null,
    val image: String? = null,
    @SerializedName("alternate_names")
    val alternateNames: List<String> = emptyList(),
)