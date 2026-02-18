package com.ravecodesolutions.rickandmorty.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.domain.CharacterDetail
import com.ravecodesolutions.rickandmorty.domain.LocationCharacterSingle
import com.ravecodesolutions.rickandmorty.ui.theme.NavigationScreenSealed

@Entity(tableName = "character_detail")
data class CharacterDetailLocal(
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

fun CharacterDetailLocal.toUI(): CharacterDetail = with(this) {
    CharacterDetail(id, name, status, species, type, gender, LocationCharacterSingle(originName, ""),
        LocationCharacterSingle(locationName, ""), image, episodes.split(","), episodes, created)
}