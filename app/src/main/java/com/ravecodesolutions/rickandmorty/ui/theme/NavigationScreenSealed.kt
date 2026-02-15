package com.ravecodesolutions.rickandmorty.ui.theme

sealed class NavigationScreenSealed(val route: String) {
    object  CharacterList: NavigationScreenSealed("characterList")
    object  CharacterDetail: NavigationScreenSealed("characterDetail/{id}") {

        fun createRoute(id: Long): String {
            return "characterDetail/$id"
        }
    }

}