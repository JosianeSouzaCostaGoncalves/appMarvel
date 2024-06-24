package com.example.appmarvel.data.remote.service

import com.example.appmarvel.data.model.model.CharacterDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("characters")
    suspend fun getCharacterList(
    @Query("nameStartsWith") searchNameList: String? = null
): Response<CharacterDataResponse>
}