package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Toolbar(
    title: String,
    navIcon: NavIcon,
    action: ActionIcon
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.padding(4.dp)
        ) {
            when(navIcon) {
                NavIcon.Menu -> Icon(Icons.Filled.Menu, contentDescription = null, modifier = Modifier.size(32.dp))
                NavIcon.Back -> Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(32.dp))
            }
        }
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 120.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Surface(
            modifier = Modifier.padding(4.dp)
        ) {
            when (action) {
                ActionIcon.Search -> Icon(
                    Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

enum class NavIcon {
    Menu, Back
}

enum class ActionIcon {
    Search
}

@Preview
@Composable
fun ToolBarPreview() {
    Toolbar(title = "Home", navIcon = NavIcon.Menu, action = ActionIcon.Search)
}
