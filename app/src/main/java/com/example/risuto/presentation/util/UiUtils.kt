package com.example.risuto.presentation.util

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.risuto.data.local.model.WatchStatus
import com.example.risuto.data.remote.model.detail.VoiceActor
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private val typeList= arrayListOf("TV", "OVA", "Movie", "Special", "ONA", "Music")
private val statusList = arrayListOf("Airing", "Completed", "Upcoming")
val genreList = arrayListOf("Action", "Adventure", "Cars", "Comedy", "Dementia", "Demons", "Mystery", "Drama", "Ecchi", "Fantasy", "Game", "Hentai", "Historical", "Horror", "Kids", "Magic", "Martial Arts", "Sci-fi", "Music", "Parody", "Samurai", "Romance", "School", "Sci-Fi", "Shoujo", "Shoujo Ai", "Shounen", "Shounen Ai", "Space", "Sport", "Super Power", "Vampire", "Yaoi", "Yuri", "Harem", "Slice of Life", "Supernatural", "Military", "Police", "Psychological", "Thriller", "Seinen", "Josei")
private val orderByList = arrayListOf("Title", "Score", "Type", "Members", "Episodes", "Rating")
private val sortList = arrayListOf("asc", "desc")

private val seasons = arrayListOf(
    "winter", "winter", "spring","spring", "spring", "summer",
    "summer", "summer", "fall", "fall", "fall", "winter"
)

data class WatchStatusUi(val watchStatus: WatchStatus, val text: String)

val bottomNavGap = 48.dp

val watchStatusList = listOf(
    WatchStatus.PlanToWatch,
    WatchStatus.Completed,
    WatchStatus.Watching,
    WatchStatus.Dropped,
    WatchStatus.OnHold
)

fun watchStatusToString(watchStatus: WatchStatus): String {
    return when(watchStatus) {
        WatchStatus.PlanToWatch -> "Plan To Watch"
        WatchStatus.OnHold -> "On Hold"
        WatchStatus.Completed -> "Completed"
        WatchStatus.Watching -> "Watching"
        WatchStatus.Dropped -> "Dropped"
        else -> "Default"
    }
}

fun getWatchStatusColor(watchStatus: WatchStatus): Color {
    return when(watchStatus) {
        WatchStatus.PlanToWatch -> Color.LightGray
        WatchStatus.OnHold -> Color.Yellow
        WatchStatus.Completed -> Color.Blue
        WatchStatus.Watching -> Color.Green
        WatchStatus.Dropped -> Color.Red
        else -> Color.Transparent
    }

}

val allSeason = arrayListOf("Winter", "Spring", "Summer", "Fall")

fun seasonYearFormat(season: String, year: Int): String {
    return season.capitalize(Locale.ROOT) + " " + year.toString()
}

fun getCurrentSeason(): String {
    return seasons[getCurrentMonth() - 1]
}

fun getGenre(genre: String): Int {
    return genreList.indexOf(genre) + 1
}

internal fun intToCurrency(int: Int): String {
    return NumberFormat.getNumberInstance(Locale.ENGLISH).format(int)
}

@SuppressLint("SimpleDateFormat")
fun getCurrentMonth(): Int {
    val sdf = SimpleDateFormat("MM")
    val currentMonth = sdf.format(Date())
    return currentMonth.toInt()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentYear(): Int {
    val sdf = SimpleDateFormat("yyyy")
    val currentYear = sdf.format(Date())
    return currentYear.toInt()
}

fun getJpnVoiceActor(voiceActors: List<VoiceActor>): VoiceActor {
    voiceActors.forEach { voiceActor ->
        if(voiceActor.language == "Japanese"){
            return voiceActor
        }
    }
    return VoiceActor("", "", 0, "Not Found", "")
}