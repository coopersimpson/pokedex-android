package com.coopersimpson.pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.coopersimpson.pokedex.data.PokemonRepository
import kotlinx.coroutines.launch

data class PokemonUiState(
    val loading: Boolean = true,
    val names: List<String> = emptyList(),
    val error: String? = null
)

class ListPokemonViewModel(private val repo: PokemonRepository) : ViewModel() {
    var uiState by mutableStateOf(PokemonUiState())
        private set // UI can read but not write

    init { refresh() }

    fun refresh() = viewModelScope.launch { // start a co-routine
        uiState = PokemonUiState(loading = true)
        runCatching { repo.fetchFirstGenNames() }
            .onSuccess { uiState = PokemonUiState(loading = false, names = it) }
            .onFailure { uiState = PokemonUiState(loading = false, error = it.message) }
    }
}

// VM Factory is used to instantiate the ViewModel, this is required for ViewModels with arguments
// in their constructors. Tells Android how to construct the ViewModel
// TODO: use @HiltViewModel instead to generate the factory
class PokemonVMFactory(private val repo: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListPokemonViewModel(repo) as T
        }
        error("Unknown ViewModel class")
    }
}
