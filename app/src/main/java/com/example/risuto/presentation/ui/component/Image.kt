package com.example.risuto.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    if (imageUrl.isNotEmpty()) {
        Image(
            modifier = modifier
                .background(MaterialTheme.colors.surface),
            contentScale = ContentScale.Crop,
            painter = rememberCoilPainter(
                request = imageUrl.replace("\\\\", "/"),
                fadeIn = true
            ),
            contentDescription = "Anime Picture"
        )
    }
}

//@Composable
//fun NetworkImageWithAddOn(
//    modifier: Modifier = Modifier,
//    imageUrl: String,
//    addOn: @Composable () -> Unit
//) {
//    Box(
//        modifier = modifier
//            .background(color = MaterialTheme.colors.surface),
//        contentAlignment = Alignment.TopEnd
//    ) {
//        if (imageUrl.isNotEmpty()) {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = rememberGlidePainter(
//                    request = imageUrl.replace("\\\\", "/"),
//                    fadeIn = true,
//                    requestBuilder = {
//                        val options = RequestOptions()
//                        options
//                            .centerCrop()
//                        apply(options)
//                    }
//                ),
//                contentDescription = "Anime Picture"
//            )
//            addOn()
//        }
//    }
//}