package com.example.appmarvel.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appmarvel.data.model.model.DataClassEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(character: DataClassEntity)

    @Query("SELECT * FROM DATACLASSENTITY ORDER BY id")
    fun getAllFavorite(): List<DataClassEntity>

    @Delete
    suspend fun deleteFavorite(character: DataClassEntity)

    @Query("DELETE FROM DATACLASSENTITY")
    fun deleteAll()
}