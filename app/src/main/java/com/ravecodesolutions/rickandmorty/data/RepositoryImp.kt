package com.ravecodesolutions.rickandmorty.data

import android.util.Log
import com.ravecodesolutions.rickandmorty.data.local.LocalDataSource
import com.ravecodesolutions.rickandmorty.data.local.model.toUI
import com.ravecodesolutions.rickandmorty.data.network.NetworkDataSource
import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.data.network.model.toLocal
import com.ravecodesolutions.rickandmorty.domain.Character
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
): Repository {
    override suspend fun getCharacters(): List<Character> {
        val localCharacters = localDataSource.getCharacters()

        if (localCharacters.isEmpty()) {
            val remoteCharacters = networkDataSource.getCharacters().results
            localDataSource.insertCharacters(remoteCharacters.toLocal())
        }
        return localDataSource.getCharacters().toUI()
    }

    override suspend fun fetchCharacterById(id: Long): CharacterSingleResponse {

        return networkDataSource.fetchHeroById(id)
    }
}