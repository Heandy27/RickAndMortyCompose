package com.ravecodesolutions.rickandmorty.data.network.model

import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal

data class Welcome (
    val info: Info,
    val results: List<ResultCharacter>
)

data class Info (
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any? = null
)

data class ResultCharacter (
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class Location (
    val name: String,
    val url: String
)


fun List<ResultCharacter>.toLocal(): List<CharacterLocal> = this.map {
    it.toLocal()
}

fun ResultCharacter.toLocal(): CharacterLocal = with(this) {
    CharacterLocal(id, name, status, species, type, gender, origin.name, location.name, image, episode.joinToString(","), url, created )
}
