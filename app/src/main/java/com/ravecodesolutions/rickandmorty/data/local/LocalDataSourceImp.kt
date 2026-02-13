package com.ravecodesolutions.rickandmorty.data.local

import com.ravecodesolutions.rickandmorty.data.local.db.CharacterDao
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal
import jakarta.inject.Inject

class LocalDataSourceImp @Inject constructor(private val dao: CharacterDao): LocalDataSource {
    override suspend fun getCharacters(): List<CharacterLocal> {
        return dao.getCharacters()
    }

    override suspend fun insertCharacters(characters: List<CharacterLocal>) {
        dao.insertCharacters(characters)
    }
}