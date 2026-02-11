package com.ravecodesolutions.rickandmorty.data.network

import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.data.network.model.Welcome

interface NetworkDataSource {
    suspend fun getCharacters(): Welcome
}