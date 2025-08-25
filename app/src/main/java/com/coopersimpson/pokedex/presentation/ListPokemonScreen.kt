package com.coopersimpson.pokedex.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coopersimpson.pokedex.components.PokeTile

@Composable
fun ListPokemonScreen() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp)
    ) {
        // Example full-width header spanning all columns
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Pokémon",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        // 150 placeholder pokemon
        items(150) { _ ->
            PokeTile()
        }
    }
}