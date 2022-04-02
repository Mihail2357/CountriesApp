package com.example.countriesapp.presentation.country_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countriesapp.domain.model.Country


@Composable
fun CountryListItem(
    country: Country,
    onItemClick: (Country) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(country) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
            ) {
        Text(
            text = "${country.name}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )


    }
}