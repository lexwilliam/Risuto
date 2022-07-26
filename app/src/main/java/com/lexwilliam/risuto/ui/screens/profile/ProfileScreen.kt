package com.lexwilliam.risuto.ui.screens.profile

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
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
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.model.UserProfilePresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.ui.component.StatusBarSpacer
import com.lexwilliam.risuto.ui.theme.*
import timber.log.Timber

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
    val count: Float
)

@Composable
fun ProfileStatistics(
    statistics: UserProfilePresentation.Data.Statistics
) {
    val pieChartData = listOf(
        PieChartData("Completed", statistics.anime.completed.toFloat()),
        PieChartData("Watching", statistics.anime.watching.toFloat()),
        PieChartData("Dropped", statistics.anime.dropped.toFloat()),
        PieChartData("On Hold", statistics.anime.on_hold.toFloat()),
        PieChartData("Plan To Watch", statistics.anime.plan_to_watch.toFloat())
    )
    Column(
        modifier = Modifier
            .padding(18.dp)
            .size(320.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AndroidView(
            factory = { context ->
                PieChart(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    this.description.isEnabled = false
                    this.isDrawHoleEnabled = false
                    this.legend.isEnabled = true
                    this.legend.textSize = 14F
                    this.legend.horizontalAlignment =
                        Legend.LegendHorizontalAlignment.CENTER
                    this.setEntryLabelColor(Color.Black.hashCode())
                }
            },
            update = {
                updatePieChartWithData(it, pieChartData)
            }
        )
    }
}

fun updatePieChartWithData(
    chart: PieChart,
    data: List<PieChartData>
) {
    val entries = ArrayList<PieEntry>()
    data.forEach {
        entries.add(PieEntry(it.count, it.name))
    }

    val ds = PieDataSet(entries, "")

    ds.colors = arrayListOf(
        primary.toArgb(),
        primaryVariant.toArgb(),
        secondary.toArgb(),
        secondaryVariant.toArgb(),
        surface.toArgb()
    )

    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    ds.sliceSpace = 2f
    ds.valueTextColor = Color.Black.toArgb()
    ds.valueTextSize = 18f
    ds.valueTypeface = Typeface.DEFAULT_BOLD
    val d = PieData(ds)
    chart.data = d
    chart.invalidate()
}

@Preview
@Composable
fun ProfileContentPreview() {
    RisutoTheme {
        ProfileInfo(imageUrl = "", username = "Testing username", joined = "Testing joined")
    }

}