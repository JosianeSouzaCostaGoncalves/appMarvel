package com.example.appmarvel.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appmarvel.data.model.model.CharacterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(character: CharacterModel)

    @Query("SELECT * FROM CHARACTER ORDER BY name")
    fun getAll(): Flow<List<CharacterModel>>

    @Delete
    suspend fun delete(character: CharacterModel)
}