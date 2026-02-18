package com.ravecodesolutions.rickandmorty.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterDetailLocal
import com.ravecodesolutions.rickandmorty.data.local.model.CharacterLocal


@Database(entities = [
    CharacterLocal::class,
    CharacterDetailLocal::class
                     ], version = 2)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterDetailDao(): CharacterDetailDao
}