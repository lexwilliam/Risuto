package com.lexwilliam.risuto.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold
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
