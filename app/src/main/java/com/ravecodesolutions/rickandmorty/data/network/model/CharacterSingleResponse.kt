package com.ravecodesolutions.rickandmorty.data.network.model

data class CharacterSingleResponse (
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationSingleResponse,
    val location: LocationSingleResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class LocationSingleResponse(
    val name: String,
    val url: String
)