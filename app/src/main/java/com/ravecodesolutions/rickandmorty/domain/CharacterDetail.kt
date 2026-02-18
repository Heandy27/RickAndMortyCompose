package com.ravecodesolutions.rickandmorty.domain

data class CharacterDetail(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationCharacterSingle,
    val location: LocationCharacterSingle,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class LocationCharacterSingle(
    val name: String,
    val url: String
)