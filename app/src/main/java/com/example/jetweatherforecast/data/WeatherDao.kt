package com.example.jetweatherforecast.data

import androidx.room.*
import com.example.jetweatherforecast.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("Select * from fav_tbl")
    fun getFavourites(): Flow<List<Favourite>>

    @Query("Select * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("Delete from fav_tbl")
    suspend fun  deleteAllFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    //Unit table
    @Query("Select * from fav_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Delete
    suspend fun deleteUnit(unit: Unit)
}