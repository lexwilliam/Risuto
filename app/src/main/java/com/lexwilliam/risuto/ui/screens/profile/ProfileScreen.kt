package com.lexwilliam.risuto.ui.screens.profile

import android.content.res.Configuration
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.model.UserProfilePresentation
import com.lexwilliam.risuto.ui.component.Header
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.ui.component.StatusBarSpacer
import com.lexwilliam.risuto.ui.screens.person.PersonSubtitle
import com.lexwilliam.risuto.ui.theme.*
import com.lexwilliam.risuto.util.FakeItems

@Composable
fun ProfileScreen(
    state: ProfileContract.State
) {
    if(state.isLoading) {
        LoadingScreen()
    } else {
        ProfileContent(
            userProfile = state.userProfile
        )
    }

}

@Composable
fun ProfileContent(
    userProfile: UserProfilePresentation.Data
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .navigationBarsWithImePadding()
    ) {
        StatusBarSpacer()
        ProfileInfo(
            imageUrl = userProfile.images.jpg.image_url,
            username = userProfile.username,
            joined = userProfile.joined
        )
        ProfileStatistics(statistics = userProfile.statistics)
    }

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

data class PieChartData(
    val name: String,
    val count: Float,
    val color: Color
)

@Composable
fun ProfileStatistics(
    statistics: UserProfilePresentation.Data.Statistics
) {

    val pieChartData = listOf(
        PieChartData("Completed", statistics.anime.completed.toFloat(), completedColor),
        PieChartData("Watching", statistics.anime.watching.toFloat(), watchingColor),
        PieChartData("Dropped", statistics.anime.dropped.toFloat(), droppedColor),
        PieChartData("On Hold", statistics.anime.on_hold.toFloat(), onHoldColor),
        PieChartData("Plan To Watch", statistics.anime.plan_to_watch.toFloat(), planToWatchColor)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PersonSubtitle(title = "Activity")
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp),
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
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = statistics.anime.total_entries.toString(), style = MaterialTheme.typography.h4, fontWeight = FontWeight.Black)
                    Text(text = "Total Entries", style = MaterialTheme.typography.caption)
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                pieChartData.forEach {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Box(modifier = Modifier.size(16.dp).background(it.color))
                        Text(text = it.name, style = MaterialTheme.typography.subtitle2)
                    }
                }
            }
        }
    }
}

fun updatePieChartWithData(
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

@Preview(
    showBackground = true
)
@Composable
fun ProfileContentPreview() {
    RisutoTheme {
        ProfileContent(userProfile = FakeItems.fakeUserProfile.data)
    }

}