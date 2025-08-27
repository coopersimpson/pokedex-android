package com.coopersimpson.pokedex.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coopersimpson.pokedex.components.PokeTile
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coopersimpson.pokedex.data.PokemonRepository
import com.coopersimpson.pokedex.data.network.NetworkModule

@Composable
fun ListPokemonScreen(modifier: Modifier = Modifier) {
    val vm: ListPokemonViewModel = viewModel(
        factory = PokemonVMFactory(PokemonRepository(NetworkModule.api))
    )
    val state = vm.uiState

    // Stores the value inside the composition so it survive recomposition
    // It is observable, meaning we trigger a recomposition of any composables
    // that read it.
    var selectedName by remember { mutableStateOf<String?>(null) }

    Box(modifier.fillMaxSize()) {
        when {
            state.loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            state.error != null -> Text(
                text = "Error: ${state.error}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.names) { name ->
                        var isSelected = selectedName == name
                        PokeTile(
                            name = name,
                            selected = isSelected,
                            onClick = {
                                selectedName = if (isSelected) null else name
                            }
                        )
                    }
                }
            }
        }
    }
}