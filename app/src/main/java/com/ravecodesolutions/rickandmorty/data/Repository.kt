package com.ravecodesolutions.rickandmorty.data

import com.ravecodesolutions.rickandmorty.domain.Character

interface Repository {
    suspend fun getCharacters(): List<Character>
}