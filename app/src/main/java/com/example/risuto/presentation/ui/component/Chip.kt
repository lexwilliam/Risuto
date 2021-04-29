package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    size: Dp = 16.dp,
    onClick: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .clickable { onClick(text) },
        shape = RoundedCornerShape(size),
        color = MaterialTheme.colors.primary
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            maxLines = 1,
            style = MaterialTheme.typography.button,
            color = Color.White
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