package com.ravecodesolutions.rickandmorty.di

import com.ravecodesolutions.rickandmorty.data.network.NetworkDataSource
import com.ravecodesolutions.rickandmorty.data.network.NetworkDataSourceImp
import com.ravecodesolutions.rickandmorty.data.network.api.CharacterApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // let decoder: JSONDecoder = {
    //    let decoder = JSONDecoder()
    //    return decoder
    //}()
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    // let session: URLSession = {
    //    let config = URLSessionConfiguration.default
    //    return URLSession(configuration: config)
    //}()
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
// var request = URLRequest(url: url)
//request.addValue("Bearer TOKEN", forHTTPHeaderField: "Authorization")
//                .addInterceptor { chain ->
//                    val original = chain.request()
//                    val newRequest = original.newBuilder()
//                        .addHeader("Authorization", "Bearer AquiElToken")
//                        .build()
//                    chain.proceed(newRequest)
//                }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(superheroOkHttpClient: OkHttpClient ,moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(superheroOkHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    // protocol SuperheroesAPI {
    //    func getHeroes() async throws -> [Hero]
    //}
    @Provides
    fun provideSuperheroApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }


    @Provides
    fun provideNetworkModule(networkDataSourceImp: NetworkDataSourceImp): NetworkDataSource {
        return networkDataSourceImp
    }
}