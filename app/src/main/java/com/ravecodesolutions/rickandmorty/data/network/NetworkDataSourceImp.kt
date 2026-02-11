package com.ravecodesolutions.rickandmorty.data.network

import com.ravecodesolutions.rickandmorty.data.network.api.CharacterApi
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.data.network.model.Welcome
import javax.inject.Inject

class NetworkDataSourceImp @Inject constructor(private val api: CharacterApi): NetworkDataSource {

    override suspend fun getCharacters(): Welcome {
        return api.getCharacters()
    }

}