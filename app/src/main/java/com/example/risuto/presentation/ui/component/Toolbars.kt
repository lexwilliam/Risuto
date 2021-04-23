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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    title: String,
) {
    Text(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        text = title,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Black
    )
}


//@Composable
//fun NavSearchBar(
//    navToSearch: () -> Unit
//) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp, vertical = 8.dp)
//            .clip(MaterialTheme.shapes.large)
//            .size(width = 120.dp, height = 40.dp)
//            .clickable { navToSearch() }
//    ) {
//        Row(
//            modifier = Modifier.background(color = MaterialTheme.colors.primary),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Spacer(modifier = Modifier.padding(4.dp))
//            Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colors.primaryVariant)
//            Spacer(modifier = Modifier.padding(4.dp))
//            Text(
//                modifier = Modifier.padding(horizontal = 4.dp),
//                text = "Search Anime and Manga",
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colors.primaryVariant
//            )
//        }
//    }
//}
