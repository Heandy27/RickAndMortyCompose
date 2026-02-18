package com.ravecodesolutions.rickandmorty.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterDetailLocal

@Dao
interface CharacterDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterDetail(detail: CharacterDetailLocal)

    @Query("SELECT * FROM character_detail WHERE id = :id")
    suspend fun getCharacterDetail(id: Long): CharacterDetailLocal
}