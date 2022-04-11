package com.example.countriesapp.presentation.country_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.countriesapp.presentation.Screen
import com.example.countriesapp.presentation.country_detail.components.CapitalListItem
import com.example.countriesapp.presentation.country_detail.components.CountryBorders
import com.example.countriesapp.presentation.country_list.components.CountryListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CountryDetailScreen(
    navController: NavController,
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.country?.let { country ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${country.name} ",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if(country.unMember == true) "UN member" else "not UN member",
                            color = if(country.unMember == true) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    country.status?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Borders",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        country.borders?.forEach { border ->
                            CountryBorders(
                                border = border,
                                onItemClick = {
                                    navController.navigate(Screen.CountryDetailScreen.route + "/alpha/${border}")
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Population",
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = country.population.toString(),
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Capital",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                }
                items(country.capital) { capital ->
                    CapitalListItem(
                        capital = capital,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }
        if(state.error.isNotBlank()) {
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
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}