package com.ravecodesolutions.rickandmorty.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal

@Dao
interface CharacterDao {
    @Query("Select * from characters")
    fun getCharacters(): List<CharacterLocal>

    @Insert
    fun insertCharacters(characters: List<CharacterLocal>)
}