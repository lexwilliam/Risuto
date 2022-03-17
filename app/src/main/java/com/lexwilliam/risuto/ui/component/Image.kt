package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    if (imageUrl.isNotEmpty()) {

        AsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl.replace("\\\\", "/"))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = modifier
                .background(MaterialTheme.colors.surface)
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