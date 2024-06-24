package com.example.appmarvel.view.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.domain.model.useCase.DeleteAllFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.DeleteCharacterFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.GetCharacterUseCase
import com.example.appmarvel.domain.model.useCase.GetFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.SaveFavoriteUseCase
import com.example.appmarvel.util.base.ResourceState
import com.example.appmarvel.view.model.mapper.CharacterViewMapper
import com.example.appmarvel.view.model.model.CharacterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val characterViewMapper: CharacterViewMapper,
    private val deleteCharacterUseCase: DeleteCharacterFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val saveCharacterUseCase: SaveFavoriteUseCase,
    private val deleteAllFavoriteUseCase: DeleteAllFavoriteUseCase,
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<CharacterView>>>(
        ResourceState.Load()
    )
    val list: StateFlow<ResourceState<List<CharacterView>>> = _list

    private val _search =
        MutableStateFlow<ResourceState<List<CharacterView>>>(ResourceState.Empty())
    val search: StateFlow<ResourceState<List<CharacterView>>> = _search

    private var listOfFavorite: MutableList<DataClass> = mutableListOf()

    private val _listGetFavorite = MutableStateFlow<List<DataClass>>(listOf())
    val listGetFavorite: StateFlow<List<DataClass>> = _listGetFavorite

    fun addOnListOfFavorite(character: DataClass) {
        listOfFavorite.add(character)
        viewModelScope.launch {
            saveCharacterUseCase(character)
        }
    }

    fun getAllFromListFavorite() = viewModelScope.launch {
        _listGetFavorite.value = getFavoriteUseCase()
    }


    fun deleteFromListOfFavorite(character: DataClass) {
        listOfFavorite.remove(character)
        viewModelScope.launch {
            deleteCharacterUseCase(character)
        }
    }

    init {
        getCharacter()
    }

    fun getCharacter() = viewModelScope.launch {
        val resource = getCharacterUseCase()
        _list.value = validaResource(resource)
    }

    fun getCharacterSearch(searchNameList: String) = viewModelScope.launch {
        val resource = getCharacterUseCase(searchNameList)
        _search.value = validaResource(resource)
    }

    private fun validaResource(
        resource: ResourceState<List<CharacterDomain>>
    ): ResourceState<List<CharacterView>> {
        resource.data?.let { values ->
            return ResourceState.Success(mapToView(values))
        }
        return ResourceState.Error(resource.message)
    }

    private fun mapToView(values: List<CharacterDomain>): List<CharacterView> {
        return characterViewMapper.mapToDomainNonNull(values)
    }

    fun deleteAll() = viewModelScope.launch {
       deleteAllFavoriteUseCase()
    }
}