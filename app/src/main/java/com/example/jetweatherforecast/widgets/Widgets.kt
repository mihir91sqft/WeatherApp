package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.WeatherItem
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.utils.formatDateTime
import com.example.jetweatherforecast.utils.formatDecimals

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier.size(75.dp))
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.weatherforecasthumidity),
                contentDescription = "humidity icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp))
            Text(text = "${weather.humidity}%",
                style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.weatherforecastwind),
                contentDescription = "wind icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp))
            Text(text = "${weather.speed}mph",
                style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.weatherforecastpressure),
                contentDescription = "pressure icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp))
            Text(text = "${weather.pressure}psi",
                style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun SunriseSunsetRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(top = 15.dp, bottom = 6.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier.padding(4.dp)) {
            Image(painter = painterResource(id = R.drawable.weatherforecastsunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(2.dp))
            Text(text = "${formatDateTime( weather.sunrise )}",
                style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "${formatDateTime( weather.sunset )}",
                style = MaterialTheme.typography.caption)
            Image(painter = painterResource(id = R.drawable.weatherforecastsunset),
                contentDescription = "sunset icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(2.dp))
        }

    }
}

@Composable
fun WeatherDetail(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White) {

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                formatDate(weather.dt)
                .split(",")[0],
                modifier = Modifier.padding(start = 5.dp))

            WeatherStateImage(imageUrl = imageUrl)

            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption)
            }

            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                ){
                    append(formatDecimals(weather.temp.max) + "°")
                }

                withStyle(style = SpanStyle(
                    color = Color.LightGray)
                ){
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })
        }

    }
}