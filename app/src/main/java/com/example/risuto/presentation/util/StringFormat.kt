package com.example.risuto.presentation.util

import com.example.risuto.data.remote.model.detail.Genre

internal fun genresToString(genres: List<Genre>): String {
    val genresNames = genres.map { genre -> genre.name }
    var string = ""
    genresNames.forEach { genre ->
        string += "$genre, "
    }
    return string.dropLast(2)
}
