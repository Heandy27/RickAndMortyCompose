package com.ravecodesolutions.rickandmorty.data.network.model

import com.ravecodesolutions.rickandmorty.data.local.model.CharacterDetailLocal

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

fun CharacterSingleResponse.toLocal(): CharacterDetailLocal = with(this) {
    CharacterDetailLocal(id,name,status,species,type,gender,origin.name,location.name,image,episode.joinToString(","),url,created)
}