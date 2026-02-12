package com.ravecodesolutions.rickandmorty.ui.characterlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ravecodesolutions.rickandmorty.data.network.model.Location
import com.ravecodesolutions.rickandmorty.data.network.model.ResultCharacter
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun CharacterGridList(
    characters: List<ResultCharacter>,
    modifier: Modifier = Modifier,
    onCharacterClick: (Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize().padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(characters) { character ->
            CharacterGridItem(
                character,
                onCharacterClick = { onCharacterClick(character.id)}
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun My_Preview_GridList() {
    CharacterGridList(
        characters = generateCharacters(),
        onCharacterClick = {}
    )
}

@Composable
fun CharacterGridItem(
    character: ResultCharacter,
    onCharacterClick: (Long) -> Unit,
    modifier: Modifier = Modifier

){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCharacterClick(character.id)},
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {


            val isInPreview = LocalInspectionMode.current

            AsyncImage(
                model = if (isInPreview) null else character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(width = 175.dp, height = 175.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Text(character.name, fontSize = 20.sp, maxLines = 1)
        }

    }
}

@Preview
@Composable
fun CharacterGridItem_Preview() {
    CharacterGridItem(
        ResultCharacter(
            id = 1L,
            name = "Zyron Vega",
            status = "Alive",
            species = "Human",
            type = "Cyber-Enhanced",
            gender = "Male",
            origin = Location(
                name = "Neo Earth",
                url = "https://api.fakeuniverse.com/location/1"
            ),
            location = Location(
                name = "Mars Colony Alpha",
                url = "https://api.fakeuniverse.com/location/23"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://api.fakeuniverse.com/episode/1",
                "https://api.fakeuniverse.com/episode/5",
                "https://api.fakeuniverse.com/episode/12"
            ),
            url = "https://api.fakeuniverse.com/character/1",
            created = "2026-02-12T10:15:30.000Z"
        ),
        onCharacterClick = {}
    )
}



private fun generateCharacters() = (0 until 10).map {
    ResultCharacter(
        id = 1L,
        name = "Zyron Vega",
        status = "Alive",
        species = "Human",
        type = "Cyber-Enhanced",
        gender = "Male",
        origin = Location(
            name = "Neo Earth",
            url = "https://api.fakeuniverse.com/location/1"
        ),
        location = Location(
            name = "Mars Colony Alpha",
            url = "https://api.fakeuniverse.com/location/23"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(
            "https://api.fakeuniverse.com/episode/1",
            "https://api.fakeuniverse.com/episode/5",
            "https://api.fakeuniverse.com/episode/12"
        ),
        url = "https://api.fakeuniverse.com/character/1",
        created = "2026-02-12T10:15:30.000Z"
    )

}

