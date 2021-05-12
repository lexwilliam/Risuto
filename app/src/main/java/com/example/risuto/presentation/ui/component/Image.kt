package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    imageUrl: String
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface
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

@Composable
fun NetworkImageWithAddOn(
    modifier: Modifier = Modifier,
    imageUrl: String,
    addOn: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.surface),
        contentAlignment = Alignment.TopEnd
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
            addOn()
        }
    }
}