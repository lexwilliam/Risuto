package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NavSearchBar(
    navToSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .clip(MaterialTheme.shapes.large)
            .size(width = 120.dp, height = 32.dp)
            .clickable { navToSearch() }
    ) {
        Row(
            modifier = Modifier.background(color = MaterialTheme.colors.primary),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.primaryVariant)
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = "Search Anime and Manga",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}