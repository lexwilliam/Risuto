package com.example.risuto.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.ui.home.HomeTopBar

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Log.d("TAG", "ONLOADING")
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    HomeTopBar()
    LoadingScreen()
}