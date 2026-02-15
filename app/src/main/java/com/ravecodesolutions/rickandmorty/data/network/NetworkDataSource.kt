package com.ravecodesolutions.rickandmorty.data.network

import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.data.network.model.Welcome
import com.ravecodesolutions.rickandmorty.domain.Character

interface NetworkDataSource {
    suspend fun getCharacters(): Welcome
    suspend fun fetchHeroById(id: Long): CharacterSingleResponse
}