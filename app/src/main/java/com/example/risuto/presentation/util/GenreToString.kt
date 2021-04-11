package com.example.risuto.presentation.util

import com.example.risuto.data.remote.model.Genre

internal fun genresToString(genres: List<Genre>): String {
    val genresNames = genres.map { genre -> genre.name }
    var string = ""
    genresNames.forEach { genre ->
        string += "$genre, "
    }
    return string.dropLast(2)
}

internal fun spaceToNextLine(string: String): String {
    return string.replace(" ", "\n ")
}