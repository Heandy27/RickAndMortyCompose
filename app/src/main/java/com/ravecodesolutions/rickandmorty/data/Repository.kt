package com.ravecodesolutions.rickandmorty.data

import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.domain.Character

interface Repository {
    suspend fun getCharacters(): List<Character>
    suspend fun fetchCharacterById(id: Long): CharacterSingleResponse
}