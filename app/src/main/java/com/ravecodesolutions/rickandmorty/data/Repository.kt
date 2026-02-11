package com.ravecodesolutions.rickandmorty.data

import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter

interface Repository {
    suspend fun getCharacters(): List<ResultCharacter>
}