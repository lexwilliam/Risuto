package com.lexwilliam.risuto.util

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lexwilliam.risuto.model.AnimeCharactersPresentation
import com.lexwilliam.risuto.model.AnimeDetailPresentation
import com.lexwilliam.risuto.model.WatchStatusPresentation
import org.joda.time.LocalDate
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

data class WatchStatusUi(val watchStatus: WatchStatusPresentation, val text: String)

val bottomNavGap = 56.dp

val watchStatusStrList = listOf("Watching", "On Hold", "Plan To Watch", "Completed", "Dropped")

fun toMalFormat(text: String): String =
    when(text) {
        "Plan To Watch" -> "plan_to_watch"
        "On Hold" -> "on_hold"
        "Completed" -> "completed"
        "Watching" -> "watching"
        "Dropped" -> "dropped"
        else -> ""
    }

fun toTextFormat(text: String): String =
    when(text) {
        "plan_to_watch" -> "Plan To Watch"
        "dropped" -> "Dropped"
        "on_hold" -> "On Hold"
        "watching" -> "Watching"
        "completed" -> "Completed"
        else -> ""
    }

val watchStatusList = listOf(
    WatchStatusPresentation.Watching,
    WatchStatusPresentation.OnHold,
    WatchStatusPresentation.PlanToWatch,
    WatchStatusPresentation.Completed,
    WatchStatusPresentation.Dropped
)

fun watchStatusToString(watchStatus: WatchStatusPresentation): String {
    return when(watchStatus) {
        WatchStatusPresentation.PlanToWatch -> "Plan To Watch"
        WatchStatusPresentation.OnHold -> "On Hold"
        WatchStatusPresentation.Completed -> "Completed"
        WatchStatusPresentation.Watching -> "Watching"
        WatchStatusPresentation.Dropped -> "Dropped"
        else -> "Error"
    }
}

fun getWatchStatusColor(watchStatus: WatchStatusPresentation): Color {
    return when(watchStatus) {
        WatchStatusPresentation.PlanToWatch -> Color.LightGray
        WatchStatusPresentation.OnHold -> Color.Yellow
        WatchStatusPresentation.Completed -> Color.Blue
        WatchStatusPresentation.Watching -> Color.Green
        WatchStatusPresentation.Dropped -> Color.Red
        else -> Color.Transparent
    }

}

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

fun getCurrentDate(): String {
    return LocalDate().dayOfWeek().getAsText(Locale.ROOT)
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

fun getJpnVoiceActor(voiceActors: List<AnimeCharactersPresentation.Data.VoiceActor>): AnimeCharactersPresentation.Data.VoiceActor {
    voiceActors.forEach { voiceActor ->
        if(voiceActor.language == "Japanese"){
            return voiceActor
        }
    }
    return AnimeCharactersPresentation.Data.VoiceActor("", AnimeCharactersPresentation.Data.VoiceActor.Person(
        AnimeCharactersPresentation.Data.VoiceActor.Person.Images(AnimeCharactersPresentation.Data.VoiceActor.Person.Images.Jpg("")), -1, "Not Found", ""))
}

fun getInitialAnimeDetails() =
    AnimeDetailPresentation(
        AnimeDetailPresentation.AlternativeTitles("", "", emptyList()), -1,
        "", AnimeDetailPresentation.Broadcast("", ""), "", "", emptyList(),
        -1, AnimeDetailPresentation.MainPicture("", ""), -1.0, "", AnimeDetailPresentation.MyListStatus(false, -1, -1, "", ""),
        "", -1, -1, -1, emptyList(), -1, -1, "", emptyList(),
        emptyList(), emptyList(), "", "", AnimeDetailPresentation.StartSeason("", -1), AnimeDetailPresentation.Statistics(-1, AnimeDetailPresentation.Status("", "", "", "", "")),
        "", emptyList(), "", "", "")