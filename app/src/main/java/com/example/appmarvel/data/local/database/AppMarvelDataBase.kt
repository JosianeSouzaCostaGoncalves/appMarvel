package com.example.appmarvel.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appmarvel.data.local.converter.ThumbnailConverter
import com.example.appmarvel.data.local.dao.FavoriteDao
import com.example.appmarvel.data.local.dao.MarvelDao
import com.example.appmarvel.data.model.model.CharacterModel
import com.example.appmarvel.data.model.model.DataClassEntity
import com.example.appmarvel.data.remote.service.DATA_BASE_NAME

@Database(
    entities = [CharacterModel::class, DataClassEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(
    ThumbnailConverter::class
)
abstract class AppMarvelDataBase : RoomDatabase() {

    abstract fun getMarvelDao(): MarvelDao
    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        fun getInstance(context: Context): AppMarvelDataBase {
            return Room.databaseBuilder(
                context,
                AppMarvelDataBase::class.java,
                DATA_BASE_NAME
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}