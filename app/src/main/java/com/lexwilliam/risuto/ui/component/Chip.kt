package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Chip(
    selected: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    // define properties to the chip
    // such as color, shape, width
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> MaterialTheme.colors.primary
        },
        shape = CircleShape,
        border = BorderStroke(
            width = when {
                selected -> 0.dp
                else -> 1.dp
            },
            color = when {
                selected -> Color.Transparent
                else -> MaterialTheme.colors.primary
            }
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Composable
fun ChipGroup(
    texts: List<String>,
    selectedText: String,
    onSelectedTextChanged: (Int) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(texts.indexOf(selectedText)) }
    FlowRow(
        mainAxisSpacing = 16.dp,
        crossAxisSpacing = 8.dp
    ) {
        texts.forEachIndexed { index, text ->
            Chip(
                modifier = Modifier
                    .clickable {
                        selectedIndex = index
                        onSelectedTextChanged(selectedIndex)
                    },
                selected = selectedIndex == index,
                text = text
            )
        }
    }
}