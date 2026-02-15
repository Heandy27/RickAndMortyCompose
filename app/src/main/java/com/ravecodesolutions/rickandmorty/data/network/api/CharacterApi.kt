package com.ravecodesolutions.rickandmorty.data.network.api

import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.data.network.model.Welcome
import com.ravecodesolutions.rickandmorty.domain.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {

    @GET("api/character")
    suspend fun getCharacters(): Welcome

    @GET("api/character/{id}")
    suspend fun fetchCharacterById(@Path("id") id: Long): CharacterSingleResponse
}