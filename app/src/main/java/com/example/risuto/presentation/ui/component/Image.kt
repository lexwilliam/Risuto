package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    width: Dp,
    height: Dp
) {
    Surface(
        modifier
            .size(width = width, height = height)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp), true)
    ) {
        if (imageUrl.isNotEmpty()) {
            GlideImage(
                data = imageUrl.replace("\\\\", "/"),
                contentDescription = "Anime Picture",
                fadeIn = true,
                modifier = Modifier.fillMaxSize(),
                requestBuilder = {
                    val options = RequestOptions()
                    options
                        .centerCrop()
                    apply(options)
                }
            )
        }
    }
}