package com.example.jetweatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.navigation.WeatherScreens
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.utils.formatDecimals
import com.example.jetweatherforecast.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
){

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = city.toString())
    }.value

    if(weatherData.loading == true){
        CircularProgressIndicator()
    }
    else if(weatherData != null){
        Text(text = "Main Screen ${weatherData.data?.city?.country}")
        weatherData.data?.let { MainScaffold(weather = it, navController = navController) }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather,
                 navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(title = weather.city.name + " ,${weather.city.country}",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            onAddActionClicked = {
                                 navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp
            ){
            Log.d("TAG", "MainScaffold: Button Clicked")
        }

    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {

    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"

    Column(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = formatDate( data.list[0].dt ),
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSecondary,
        fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
        shape = CircleShape,
        color = Color(0xFFFFC400)
        ) {

            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

                WeatherStateImage(imageUrl = imageUrl)
                
                Text(text = formatDecimals( data.list[0].temp.day ) + "°",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold)

                Text(text = data.list[0].weather[0].main,
                fontStyle = FontStyle.Italic
                )

            }
        }
        HumidityWindPressureRow(weather = data.list[0])
        Divider()
        SunriseSunsetRow(weather = data.list[0])
        Text(text = "This Week",
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold
        )

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(size = 14.dp),
            color = Color(0xFFEEF1EF)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)){

                items(data.list){ item ->
                    WeatherDetail(item)
                }
            }

        }
    }
}
