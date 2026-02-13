package com.ravecodesolutions.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.ravecodesolutions.rickandmorty.data.local.LocalDataSource
import com.ravecodesolutions.rickandmorty.data.local.LocalDataSourceImp
import com.ravecodesolutions.rickandmorty.data.local.db.CharacterDao
import com.ravecodesolutions.rickandmorty.data.local.db.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideSuperDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "database-name"
        )
            // Borra los datos y creas de nuevo asi cuando modifiques la tabla se borre y cree la nueva
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideSuperheroDao(db: CharacterDatabase): CharacterDao {
        return db.characterDao()
    }

    @Provides
    fun provideLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource {
        return localDataSourceImp
    }
}