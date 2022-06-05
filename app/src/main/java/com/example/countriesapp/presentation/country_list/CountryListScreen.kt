package com.example.countriesapp.presentation.country_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.countriesapp.presentation.Screen
import com.example.countriesapp.presentation.country_list.components.CountryListItem
import com.example.countriesapp.ui.theme.DarkBlue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun CountryListScreen(
    navController: NavController,
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state2.isRefreshing
    )
    val state = viewModel.state.value
    val state2=viewModel.state2

    Column(modifier=Modifier.fillMaxSize())
    {
        OutlinedTextField(
            value = state2.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CountryListEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state2.countries) { country ->
                    val saveCountry = SwipeAction(
                        icon = {
                            Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "add_button")
                        },
                        background = Color.Green,
                        onSwipe = { viewModel.onEvent(CountryListEvent.SaveCountry(country = country.name)) }
                    )
                    SwipeableActionsBox(
                        swipeThreshold = 60.dp,
                        startActions = listOf(saveCountry)
                    ) {

                        Card(modifier = Modifier.fillMaxWidth(),
                            backgroundColor = DarkBlue
                        ) {

                            CountryListItem(
                                country = country,
                                onItemClick = {
                                    navController.navigate(Screen.CountryDetailScreen.route + "/name/${country.name}")
                                }
                            )
                        }
                    }
                }
                item{
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}