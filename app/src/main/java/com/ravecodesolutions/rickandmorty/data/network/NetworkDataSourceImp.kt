package com.ravecodesolutions.rickandmorty.data.network

import com.ravecodesolutions.rickandmorty.data.network.api.CharacterApi
import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.data.network.model.Welcome
import com.ravecodesolutions.rickandmorty.domain.Character
import javax.inject.Inject

class NetworkDataSourceImp @Inject constructor(private val api: CharacterApi): NetworkDataSource {

    override suspend fun getCharacters(): Welcome {
        return api.getCharacters()
    }

    override suspend fun fetchHeroById(id: Long): CharacterSingleResponse {
       return api.fetchCharacterById(id)
    }

}