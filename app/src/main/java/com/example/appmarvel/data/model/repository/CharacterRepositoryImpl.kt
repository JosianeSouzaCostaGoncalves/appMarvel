package com.example.appmarvel.data.model.repository

import android.content.Context
import com.example.appmarvel.R
import com.example.appmarvel.data.local.dao.FavoriteDao
import com.example.appmarvel.data.local.dao.MarvelDao
import com.example.appmarvel.data.model.mapper.CharacterDataMapper
import com.example.appmarvel.data.model.model.CharacterDataResponse
import com.example.appmarvel.data.model.model.DataClassEntity
import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.data.remote.service.ServiceApi
import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.domain.model.repository.CharacterRepository
import com.example.appmarvel.util.base.ResourceState
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class CharacterRepositoryImpl(
    private val api: ServiceApi,
    private val mapper: CharacterDataMapper,
    private val daoMarvel: MarvelDao,
    private val daoFavorites: FavoriteDao,
    private val context: Context
) : CharacterRepository {

    override suspend fun getCharacter(searchNameList: String?):
            ResourceState<List<CharacterDomain>> {
        return try {
            val response = api.getCharacterList(searchNameList)
            validaResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("CharacterRepositoryImpl").e("Error -> $t")
                    ResourceState.Error(context.getString(R.string.erro_conexao))
                }

                else -> {
                    Timber.tag("CharacterRepositoryImpl").e("Error -> $t")
                    ResourceState.Error(context.getString(R.string.erro_conversao))
                }
            }
        }
    }

    private fun validaResponse(response: Response<CharacterDataResponse>): ResourceState<List<CharacterDomain>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val listDomain = mapToDomain(values)
                return ResourceState.Success(listDomain)
            }
        }
        return ResourceState.Error(response.message())
    }

    private fun mapToDomain(values: CharacterDataResponse): List<CharacterDomain> {
        val results = values.data.results
        return mapper.mapFromDomainNonNull(results)
    }

    override suspend fun saveFavorite(character: DataClass) {
        val dataSave = DataClassEntity(
            id = character.id,
            dataUrl = character.dataUrl,
            extension = character.extension,
            dataTitle = character.dataTitle,
            dataFavorite = character.dataFavorite,
            dataDescription = character.dataDescription
        )
        daoFavorites.saveFavorite(dataSave)
    }

    override suspend fun getAllFavorite(): List<DataClass> {
        val listCharacter = daoFavorites.getAllFavorite()
        val dataGetAll: MutableList<DataClass> = mutableListOf()
        listCharacter.forEach {
            dataGetAll.add(
                DataClass(
                    id = it.id,
                    dataUrl = it.dataUrl,
                    extension = it.extension,
                    dataTitle = it.dataTitle,
                    dataFavorite = it.dataFavorite,
                    dataDescription = it.dataDescription
                )
            )
        }
        return dataGetAll.toList()
    }

    override suspend fun delete(character: DataClass) {
        val dataDelete = DataClassEntity(
            id = character.id,
            dataUrl = character.dataUrl,
            extension = character.extension,
            dataTitle = character.dataTitle,
            dataFavorite = character.dataFavorite,
            dataDescription = character.dataDescription
        )
        daoFavorites.deleteFavorite(dataDelete)
    }

    override suspend fun getAllDelete() {
       daoFavorites.deleteAll()
    }
}