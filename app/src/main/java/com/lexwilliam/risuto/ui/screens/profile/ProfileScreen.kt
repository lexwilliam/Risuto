package com.lexwilliam.risuto.ui.screens.profile

import android.content.res.Configuration
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.lexwilliam.risuto.model.UserProfilePresentation
import com.lexwilliam.risuto.ui.component.*
import com.lexwilliam.risuto.ui.screens.person.Subtitle
import com.lexwilliam.risuto.ui.theme.*
import com.lexwilliam.risuto.util.FakeItems
import com.lexwilliam.risuto.util.gradientBackground
import com.google.accompanist.insets.ui.TopAppBar

@Composable
fun ProfileScreen(
    state: ProfileContract.State,
    navToDetail: (Int) -> Unit,
    navToPerson: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        ProfileContent(
            userProfile = state.userProfile,
            navToDetail = navToDetail,
            navToPerson = navToPerson,
            onBackPressed = onBackPressed
        )
    }

}

@Composable
fun ProfileContent(
    userProfile: UserProfilePresentation.Data,
    navToDetail: (Int) -> Unit,
    navToPerson: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .navigationBarsWithImePadding()
            .verticalScroll(rememberScrollState())
    ) {
        ProfileToolbar(onBackPressed = onBackPressed)
        ProfileInfo(
            imageUrl = userProfile.images.jpg.image_url,
            username = userProfile.username,
            joined = userProfile.joined
        )
        ProfileStatistics(statistics = userProfile.statistics)
        ProfileUpdates(updates = userProfile.updates, navToDetail = navToDetail)
        ProfileFavorites(favorites = userProfile.favorites, navToDetail = navToDetail, navToPerson = navToPerson)
    }
}

@Composable
fun ProfileToolbar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyBottom = false,
        ),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        title = { Text("") },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.background)
                    .clickable { onBackPressed() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colors.onBackground)
            }
        }
    )
}

@Composable
fun ProfileInfo(
    imageUrl: String,
    username: String,
    joined: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(100.dp)
                .shadow(4.dp, CircleShape, true)
        )
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = username, style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)
            Text(text = joined, style = MaterialTheme.typography.caption)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileStatistics(
    statistics: UserProfilePresentation.Data.Statistics
) {
    val pagerState = rememberPagerState()
    Column {
        Subtitle(title = "Statistics")
        ProfilePieChart(statistics = statistics)
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
    }
}

@Composable
fun ProfilePieChart(
    statistics: UserProfilePresentation.Data.Statistics
) {
    val pieChartData = listOf(
        PieChartData("Completed", statistics.anime.completed.toFloat(), completedColor),
        PieChartData("Watching", statistics.anime.watching.toFloat(), watchingColor),
        PieChartData("Dropped", statistics.anime.dropped.toFloat(), droppedColor),
        PieChartData("On Hold", statistics.anime.on_hold.toFloat(), onHoldColor),
        PieChartData("Plan To Watch", statistics.anime.plan_to_watch.toFloat(), planToWatchColor)
    )
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(240.dp)
            .shadow(4.dp, MaterialTheme.shapes.large, true)
            .gradientBackground(
                listOf(
                    MaterialTheme.colors.background,
                    MaterialTheme.colors.primary
                ), 325f
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Activity", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(156.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AndroidView(
                        factory = { context ->
                            PieChart(context).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                this.description.isEnabled = false
                                this.isDrawHoleEnabled = true
                                this.legend.isEnabled = false
                                this.holeRadius = 80f
                                this.setHoleColor(if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) 0x151E29 else 0xFFFFFF)
                            }
                        },
                        update = {
                            updatePieChartWithData(it, pieChartData)
                        }
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = statistics.anime.total_entries.toString(), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Black)
                        Text(text = "Total Entries", style = MaterialTheme.typography.subtitle2)
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    pieChartData.forEach {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier
                                .size(12.dp)
                                .background(it.color)
                                .clip(CircleShape)
                            )
                            Text(text = it.name, style = MaterialTheme.typography.subtitle2)
                        }
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    pieChartData.forEach {
                        Row {
                            Text(text = it.count.toInt().toString(), style = MaterialTheme.typography.subtitle2, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

data class PieChartData(
    val name: String,
    val count: Float,
    val color: Color
)

private fun updatePieChartWithData(
    chart: PieChart,
    data: List<PieChartData>
) {
    val entries = ArrayList<PieEntry>()
    data.forEach {
        entries.add(PieEntry(it.count))
    }

    val ds = PieDataSet(entries, "")
    ds.colors = data.map { it.color.toArgb() }

    ds.setDrawValues(false)
    ds.sliceSpace = 2f

    val d = PieData(ds)
    chart.data = d
    chart.invalidate()
}

//@Composable
//fun ProfileBarChart(
//    statistics: UserProfilePresentation.Data.Statistics
//) {
//    statistics
//}
@Composable
fun ProfileFavorites(
    favorites: UserProfilePresentation.Data.Favorites,
    navToDetail: (Int) -> Unit,
    navToPerson: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Subtitle(title = "Favorite Animes")
        FavoriteAnimeHorizontalGridList(items = favorites.anime, navToDetail = navToDetail)
        Subtitle(title = "Favorite Characters")
        FavoriteCharacterHorizontalGridList(items = favorites.characters)
        Subtitle(title = "Favorite People")
        FavoritePeopleHorizontalGridList(items = favorites.people, navToPerson = navToPerson)
    }
}

@Composable
fun ProfileUpdates(
    updates: UserProfilePresentation.Data.Updates,
    navToDetail: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Subtitle(title = "Your Updates")
        UpdatesHorizontalGridList(items = updates, navToDetail = navToDetail)
    }

}

@Preview(
    showBackground = true
)
@Composable
fun ProfileContentPreview() {
    RisutoTheme {
        ProfileContent(
            userProfile = FakeItems.fakeUserProfile,
            navToDetail = {},
            navToPerson = {},
            onBackPressed = {}
        )
    }

}