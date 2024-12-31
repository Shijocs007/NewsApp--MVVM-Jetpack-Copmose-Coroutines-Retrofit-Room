package com.shijo.newsapp.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.lifecycle.ViewModel
import com.shijo.newsapp.utils.BottomNavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(HomeState(
        isBottomBarEnabled = true,
        selectedItemIndex = 0,
        bottomNavItems = bottomNavigationItems
    ))
    val state: StateFlow<HomeState>  = _state


    fun onEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.OnBottomNavClick -> {
                _state.update { currentState->
                    currentState.copy(selectedItemIndex = event.index)
                }
            }
        }
    }

}


val bottomNavigationItems = listOf(
        BottomNavigationItem(label = "Home", icon = Icons.Default.Home),
        BottomNavigationItem(label = "Search", icon = Icons.Default.Search),
        BottomNavigationItem(label = "Bookmarks", icon = Icons.Rounded.Star)
    )
