package com.example.countriesapp.presentation.country_detail
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesapp.presentation.Screen
import com.example.countriesapp.presentation.country_detail.components.CapitalListItem
import com.example.countriesapp.presentation.country_detail.components.CountryBorders
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
                        Image(
                            painter = rememberAsyncImagePainter(country.flags?.png),
                            contentDescription = null,
                            modifier = Modifier.size(128.dp)
                        )

                    Text(
                        text = if(country.independent == true) "Independent" else "not Independent",
                        color = if(country.independent == true) Color.Green else Color.Red,

                    )
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
                }

                item{
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Area",
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = country.area.toString()+" km2",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                }

                item{
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Continent",
                        style = MaterialTheme.typography.h3
                    )
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        country.continents?.forEach { continent ->
                            CapitalListItem(capital = continent )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "TimeZone",
                        style = MaterialTheme.typography.h3
                    )

                    Text(
                        text = country.timezones?.get(0) ?: "",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    /*
                    Text(
                        text = "Currency",
                        style = MaterialTheme.typography.h3
                    )

                    Text(
                        text = (country.currencies?.EUR?.name) + " "
                                + (country.currencies?.EUR?.symbol),
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                     */

                }

                item {


                    Image(
                        painter = rememberAsyncImagePainter(country.flags?.png),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Open Map",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            navController.navigate("Map"+"/name/${state.country.name}")
                        },
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            top = 12.dp,
                            end = 20.dp,
                            bottom = 12.dp
                        )
                    ) {
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription = "Open Map",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Open Map")
                    }

                }


                item{
                    Divider()
                    Spacer(modifier = Modifier.height(40.dp))
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