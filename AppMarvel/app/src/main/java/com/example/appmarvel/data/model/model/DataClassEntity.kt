package com.example.appmarvel.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DATACLASSENTITY")
data class DataClassEntity (
    @PrimaryKey
    val id: Int,
    val dataUrl: String,
    val extension: String,
    val dataTitle: String,
    val dataFavorite: Boolean,
    val dataDescription: String
)