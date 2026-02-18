package com.ravecodesolutions.rickandmorty.data.local

import com.ravecodesolutions.rickandmorty.data.local.model.CharacterDetailLocal
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal

interface LocalDataSource {
    suspend fun getCharacters(): List<CharacterLocal>
    suspend fun insertCharacters(characters: List<CharacterLocal>)

    suspend fun getCharacterDetail(id: Long): CharacterDetailLocal
    suspend fun insertCharacterDetail(detail: CharacterDetailLocal)
}