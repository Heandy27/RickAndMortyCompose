package com.ravecodesolutions.rickandmorty.ui.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        AsyncImage(
                            character.image,
                            character.name,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            character.name,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.White
                        )
                        Text(
                            "Specie: ${character.species}",
                            Modifier.padding(horizontal = 10.dp),
                            Color.White,
                            fontSize = 15.sp
                        )
                        Text(
                            "Origin: ${character.origin.name}",
                            Modifier.padding(horizontal = 10.dp),
                            Color.White,
                            fontSize = 15.sp
                        )
                        Text(
                            "Location: ${character.location.name}",
                            Modifier.padding(horizontal = 10.dp),
                            Color.White,
                            fontSize = 15.sp
                        )
                        Text(
                            "Gender: ${character.gender}",
                            Modifier.padding(horizontal = 10.dp),
                            Color.White,
                            fontSize = 15.sp
                        )
                        Text(
                            "Status: ${character.status}",
                            Modifier.padding(horizontal = 10.dp),
                            Color.White,
                            fontSize = 15.sp
                        )
                    }
                }


            }
        }
    }
}
