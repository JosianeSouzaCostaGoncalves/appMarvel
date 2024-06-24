package com.example.appmarvel.data.model.mock

import com.example.appmarvel.R
import com.example.appmarvel.data.model.model.CharacterLocalModel
import com.example.appmarvel.data.model.model.CharacterModel
import com.example.appmarvel.data.model.thumbnail.Thumbnail
import com.example.appmarvel.data.remote.service.DataClass

private val IronMan = CharacterLocalModel(
    characterModel = CharacterModel(
        id = 1,
        name = "IronMan",
        description = "Description IronMan Mock",
        thumbnail = Thumbnail(
            path = "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/3/35/IronMan-EndgameProfile.jpg/revision/latest/scale-to-width-down/1000?cb=20231025163251",
            extension = ".jpg"
        ),
    ),
    isFavorite = false
)

private val Hulk = CharacterLocalModel(
    characterModel = CharacterModel(
        id = 2,
        name = "Hulk",
        description = "Description Hulk Mock",
        thumbnail = Thumbnail(
            path = "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/3/35/IronMan-EndgameProfile.jpg/revision/latest/scale-to-width-down/1000?cb=20231025163251",
            extension = ".jpg"
        ),
    ),
    isFavorite = false
)

private val BlackWidow = CharacterLocalModel(
    characterModel = CharacterModel(
        id = 3,
        name = "Black Widow",
        description = "Description BlackWidow Mock",
        thumbnail = Thumbnail(
            path = "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/3/35/IronMan-EndgameProfile.jpg/revision/latest/scale-to-width-down/1000?cb=20231025163251",
            extension = ".jpg"
        ),
    ),
    isFavorite = false,

    )

val imageList = listOf(
    R.drawable.homem_de_ferro,
    R.drawable.hulk,
    R.drawable.black_window
)

val HeroesList = listOf<CharacterLocalModel>(
    IronMan,
    Hulk,
    BlackWidow
)

fun createMockCards(dataList: ArrayList<DataClass>) {
    HeroesList.forEach {
        if (it.characterModel.name == "IronMan") {
            dataList.add(
                DataClass(
                    id = it.characterModel.id,
                    dataUrl = it.characterModel.thumbnail.path,
                    extension = it.characterModel.thumbnail.extension,
                    dataTitle = it.characterModel.name,
                    dataFavorite = it.isFavorite,
                    dataDescription = it.characterModel.description
                )
            )
        }
        if (it.characterModel.name == "Hulk") {
            dataList.add(
                DataClass(
                    id = it.characterModel.id,
                    dataUrl = it.characterModel.thumbnail.path,
                    extension = it.characterModel.thumbnail.extension,
                    dataTitle = it.characterModel.name,
                    dataFavorite = it.isFavorite,
                    dataDescription = it.characterModel.description
                )
            )
        }
        if (it.characterModel.name == "Black Widow") {
            dataList.add(
                DataClass(
                    id = it.characterModel.id,
                    dataUrl = it.characterModel.thumbnail.path,
                    extension = it.characterModel.thumbnail.extension,
                    dataTitle = it.characterModel.name,
                    dataFavorite = it.isFavorite,
                    dataDescription = it.characterModel.description
                )
            )
        }
    }
}