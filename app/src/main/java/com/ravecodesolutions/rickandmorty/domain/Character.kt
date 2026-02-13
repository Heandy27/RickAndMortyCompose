package com.ravecodesolutions.rickandmorty.domain

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationCharacter,
    val location: LocationCharacter,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)


data class LocationCharacter(
    val name: String,
    val url: String
)