package com.ravecodesolutions.rickandmorty

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import com.ravecodesolutions.rickandmorty.domain.UIState
import com.ravecodesolutions.rickandmorty.ui.characterdetail.CharacterDetailScreen
import com.ravecodesolutions.rickandmorty.ui.characterlist.CharacterGridItem
import com.ravecodesolutions.rickandmorty.ui.characterlist.CharacterGridList
import com.ravecodesolutions.rickandmorty.ui.characterlist.CharacterListViewModel
import com.ravecodesolutions.rickandmorty.ui.theme.NavigationScreenSealed
import com.ravecodesolutions.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                NavHost(navController, NavigationScreenSealed.CharacterList.route) {
                    // Pantalla Lista Grid
                    composable(NavigationScreenSealed.CharacterList.route) {
                        MainScreen(navController)
                    }

                    // Pantalla Detalle
                    composable(
                        route = NavigationScreenSealed.CharacterDetail.route,
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.LongType
                            }
                        )
                    ) { backStackEntry ->

                        val id = backStackEntry.arguments?.getLong("id")

                        if (id != null) {
                            CharacterDetailScreen(id, navController)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    navcontroller: NavHostController,
    characterListViewModel: CharacterListViewModel = hiltViewModel()
) {
    val state by characterListViewModel.heroes.collectAsState()

    Scaffold() { innerPadding ->
        when (state) {
            is UIState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text("❌ Error loading heroes")

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = { characterListViewModel.getCharacters() }) {
                            Text("Retry")
                        }
                    }
                }
            }
            UIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
            }
            is UIState.Success -> {
                CharacterGridList(
                    (state as UIState.Success).data,
                    modifier = Modifier.padding(innerPadding),
                    onCharacterClick = { id ->
                        navcontroller.navigate(
                            NavigationScreenSealed.CharacterDetail.createRoute(id)
                        )
                    }
                )
            }
        }
    }
}