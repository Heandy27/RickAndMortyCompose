package com.ravecodesolutions.rickandmorty.data.local

import com.ravecodesolutions.rickandmorty.data.local.db.CharacterDao
import com.ravecodesolutions.rickandmorty.data.local.db.CharacterDetailDao
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterDetailLocal
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal
import jakarta.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val dao: CharacterDao,
    private val daoDetail: CharacterDetailDao
): LocalDataSource {

    override suspend fun getCharacters(): List<CharacterLocal> {
        return dao.getCharacters()
    }

    override suspend fun insertCharacters(characters: List<CharacterLocal>) {
        dao.insertCharacters(characters)
    }

    override suspend fun getCharacterDetail(id: Long): CharacterDetailLocal {
        return daoDetail.getCharacterDetail(id)
    }

    override suspend fun insertCharacterDetail(detail: CharacterDetailLocal) {
        daoDetail.insertCharacterDetail(detail)
    }
}