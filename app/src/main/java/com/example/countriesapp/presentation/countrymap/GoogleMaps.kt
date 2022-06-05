package com.example.countriesapp.presentation.countrymap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.countriesapp.presentation.country_detail.CountryDetailViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*


@Composable
fun GoogleMaps(
    viewModel: CountryDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val l= state.country?.latlng
    val latitude=l?.get(0) ?:22.5726
    val longitude=l?.get(1) ?:88.3639
    val country_position = LatLng(latitude, longitude )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(country_position, 2f)
    }

    Box(Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        )
        {
            Marker(
                position = country_position
            )
        }


        Button(
            onClick = {
                cameraPositionState.move(CameraUpdateFactory.newLatLng(country_position))
            }
        ) {
            Text(text = "Animate camera to "+state.country?.name)
        }
    }


}