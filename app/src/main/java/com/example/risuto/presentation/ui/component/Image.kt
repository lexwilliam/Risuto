package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    if (imageUrl.isNotEmpty()) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            painter = rememberGlidePainter(
                request = imageUrl.replace("\\\\", "/"),
                fadeIn = true,
                requestBuilder = {
                    val options = RequestOptions()
                    options.centerCrop()
                    apply(options)
                }
            ),
            contentDescription = "Anime Picture"
        )
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
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberGlidePainter(
                    request = imageUrl.replace("\\\\", "/"),
                    fadeIn = true,
                    requestBuilder = {
                        val options = RequestOptions()
                        options
                            .centerCrop()
                        apply(options)
                    }
                ),
                contentDescription = "Anime Picture"
            )
            addOn()
        }
    }
}