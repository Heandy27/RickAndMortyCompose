package com.ravecodesolutions.rickandmorty.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal


@Database(entities = [CharacterLocal::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract  fun characterDao(): CharacterDao
}