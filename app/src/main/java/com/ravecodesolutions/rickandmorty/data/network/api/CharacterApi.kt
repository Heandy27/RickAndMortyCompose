package com.ravecodesolutions.rickandmorty.data.network.api

import com.ravecodesolutions.rickandmorty.data.network.model.Welcome
import retrofit2.http.GET

interface CharacterApi {

    @GET("api/character")
    suspend fun getCharacters(): Welcome
}