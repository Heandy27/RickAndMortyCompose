package com.ravecodesolutions.rickandmorty.ui.characterdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.ravecodesolutions.rickandmorty.data.network.model.CharacterSingleResponse
import com.ravecodesolutions.rickandmorty.domain.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    id: Long,
    navController: NavHostController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
val state by viewModel.stateDetail.collectAsState()

    LaunchedEffect(id) {
        viewModel.fetchCharacterById(id)
    }


    Scaffold() { innerPadding ->
        when (state) {
            is UIState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text("❌ Error loading hero")

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = { viewModel.fetchCharacterById(id = id) }) {
                            Text("Retry")
                        }
                    }
                }
            }
            UIState.Loading ->  {
                Box(modifier = Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
            }
            is UIState.Success -> {

                val character = (state as UIState.Success<CharacterSingleResponse>).data
                AsyncImage(character.image, character.name)
                Text(character.name)

            }
        }
    }
}

@Preview
@Composable
fun CharacterDetailScreen_Preview() {

}
