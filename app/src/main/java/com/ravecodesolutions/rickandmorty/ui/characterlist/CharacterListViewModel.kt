package com.ravecodesolutions.rickandmorty.ui.characterlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravecodesolutions.rickandmorty.data.Repository
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.domain.Character
import com.ravecodesolutions.rickandmorty.domain.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){
    private val _heroes = MutableStateFlow<UIState<List<Character>>>(UIState.Loading)
    val heroes: StateFlow<UIState<List<Character>>> = _heroes.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _heroes.value = UIState.Loading

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.getCharacters()
                }
            }

            result.onSuccess { characters ->
                _heroes.value = UIState.Success(characters)
            }

            result.onFailure { error ->
                _heroes.value = UIState.Error(error.message ?: "Unkown Error")
            }
        }
    }
}