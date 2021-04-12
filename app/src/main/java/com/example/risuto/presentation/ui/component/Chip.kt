package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.risuto.presentation.util.filterList

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    size: Dp = 16.dp,
    onClick: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .background(color = Color.White)
            .clickable { onClick(text) },
        shape = RoundedCornerShape(size),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primary
        )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            maxLines = 1,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun ChipGroup(
    texts: ArrayList<String>,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        val remain = texts.size % 4
        val temp = texts.size - remain
        var count = 0
        while(count < temp) {
            Row {
                for(i in 0..3) {
                    Chip(modifier = Modifier.padding(end = 8.dp, bottom = 8.dp), text = texts[count + i], onClick = { onClick(it) })
                }
            }
            count+=4
        }
        Row {
            while (count < texts.size){
                Chip(modifier = Modifier.padding(end = 8.dp, bottom = 8.dp), text = texts[count], onClick = { onClick(it) })
                count++
            }
        }
    }
}

@Composable
fun ChipGroupList(onClick: (String) -> Unit){
    Column {
        filterList.forEach { texts ->
            ChipGroup(texts = texts, onClick = { onClick(it) })
        }
    }
}

@Preview
@Composable
fun ChipGroupListPreview() {
    ChipGroupList(onClick = {})
}