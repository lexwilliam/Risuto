package com.lexwilliam.risuto.ui.screens.profile

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.lexwilliam.risuto.model.UserProfilePresentation
import com.lexwilliam.risuto.ui.component.LoadingScreen
import com.lexwilliam.risuto.ui.component.NetworkImage
import com.lexwilliam.risuto.ui.component.StatusBarSpacer
import com.lexwilliam.risuto.ui.theme.RisutoTheme

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

@Composable
fun ProfileStatistics() {
    AndroidView(factory = { context ->
        PieChart(context).apply {

        }
    })
}

@Preview
@Composable
fun ProfileContentPreview() {
    RisutoTheme {
        ProfileInfo(imageUrl = "", username = "Testing username", joined = "Testing joined")
    }

}