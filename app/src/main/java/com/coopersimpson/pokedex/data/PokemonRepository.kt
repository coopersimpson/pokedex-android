package com.coopersimpson.pokedex.data

import com.coopersimpson.pokedex.data.network.PokeApi
import java.util.Locale

// Layer between network data source and the ViewModel

class PokemonRepository(private val api: PokeApi) {
    suspend fun fetchFirstGenNames(): List<String> =
        api.listPokemon(limit = 151, offset = 0)
            .results
            .map { it.name.replaceFirstChar { c -> c.titlecase(Locale.ROOT) } }
}
