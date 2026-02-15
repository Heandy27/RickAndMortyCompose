package com.ravecodesolutions.rickandmorty.ui.characterdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravecodesolutions.rickandmorty.data.Repository
import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.data.network.model.Welcome
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

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _stateDetail = MutableStateFlow<UIState<CharacterSingleResponse>>(UIState.Loading)

    val stateDetail: StateFlow<UIState<CharacterSingleResponse>> = _stateDetail.asStateFlow()

    fun fetchCharacterById(id: Long) {
        viewModelScope.launch {
            _stateDetail.value = UIState.Loading

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.fetchCharacterById(id)
                }
            }

            result.onSuccess { character ->
                Log.d("CharacterDetailVM", "Character recibido: ${character.name}")
                _stateDetail.value = UIState.Success(data = character)
            }

            result.onFailure { error ->
                Log.e("CharacterDetailVM", "Error: ${error.message}")
                _stateDetail.value = UIState.Error(error.message ?: "Unkown Error")
            }
        }
    }
}