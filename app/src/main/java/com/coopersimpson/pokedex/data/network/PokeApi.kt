package com.coopersimpson.pokedex.data.network

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

// @SerializedName uses gson library to map JSON keys to Kotlin properties
data class NamedApiResource(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<NamedApiResource>
)

// Retrofit service interface, use a suspend function so we can run off the main thread
// using coroutines
interface PokeApi {
    // Gen 1 = first 151
    @GET("pokemon")
    suspend fun listPokemon(
        @Query("limit") limit: Int = 151,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse
}