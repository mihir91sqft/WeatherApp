package com.example.jetweatherforecast.repository

import com.example.jetweatherforecast.data.WeatherDao
import com.example.jetweatherforecast.model.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()
    suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite)
    suspend fun updateFavourite(favourite: Favourite) = weatherDao.updateFavourite(favourite)
    suspend fun deleteAllFavourites() = weatherDao.deleteAllFavourites()
    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite)
    suspend fun getFavById(city: String): Favourite = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)
}