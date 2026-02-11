package com.ravecodesolutions.rickandmorty.data

import android.util.Log
import com.ravecodesolutions.rickandmorty.data.network.NetworkDataSource
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val networkDataSource: NetworkDataSource
): Repository {
    override suspend fun getCharacters(): List<ResultCharacter> {
        val characters = networkDataSource.getCharacters()

        Log.d("HERO_DEBUG", "Cantidad recibida: ${characters.results.size}")

        characters.results.forEach {
            Log.d("HERO_DEBUG", "Hero: ${it.name}")
        }

        return characters.results
    }
}