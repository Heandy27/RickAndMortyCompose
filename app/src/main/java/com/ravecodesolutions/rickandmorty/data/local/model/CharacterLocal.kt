package com.ravecodesolutions.rickandmorty.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ravecodesolutions.rickandmorty.domain.Character
import com.ravecodesolutions.rickandmorty.domain.LocationCharacter

@Entity(tableName = "characters")
data class CharacterLocal(

    @PrimaryKey
    val id: Long,

    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,

    // Guardamos solo el nombre en vez del objeto Location
    val originName: String,
    val locationName: String,

    val image: String,

    // Guardamos la lista como String (json o separado por coma)
    val episodes: String,

    val url: String,
    val created: String
)

fun List<CharacterLocal>.toUI(): List<Character> = this.map {
    it.toUI()
}

fun CharacterLocal.toUI(): Character = with(this) {
    Character(id, name, status, species, type, gender,
        LocationCharacter(originName, ""), LocationCharacter(originName, ""), image, episodes.split(","), url, created)
}

