package com.lexwilliam.risuto.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.lexwilliam.risuto.ui.theme.RisutoTheme

@Composable
fun PosterGridListShimmerLoading() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .height(24.dp)
                .width(140.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(brush)
        )
        Row(
            modifier = Modifier.padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(4) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .width(105.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(brush)
                    )
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridListShimmerLoading() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp),
        cells = GridCells.Adaptive(minSize = 136.dp),
    ) {
        repeat(6) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(bottom = 16.dp, end = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 180.dp, height = 240.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush)
                    )
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(140.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush)
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderShimmerLoading() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(
        modifier = Modifier
            .padding(16.dp)
            .height(48.dp)
            .width(240.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(brush)
    )
}

@Composable
fun RowListShimmerLoading() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(4) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(height = 180.dp, width = 120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush)
                )
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(
                        modifier = Modifier
                            .size(height = 24.dp, width = 120.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(brush)
                    )
                    Box(
                        modifier = Modifier
                            .size(height = 16.dp, width = 90.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(brush)
                    )
                }
            }
        }
    }
}

@Composable
fun MyAnimeListShimmerLoading() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column {
        TopAppBar(
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                applyBottom = false,
            ),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            title = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(brush)
                    )
                    Box(
                        modifier = Modifier
                            .height(36.dp)
                            .width(240.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(brush)
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(4) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(height = 180.dp, width = 120.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush)
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Box(
                            modifier = Modifier
                                .size(height = 24.dp, width = 120.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(brush)
                        )
                        Box(
                            modifier = Modifier
                                .size(height = 16.dp, width = 90.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(brush)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeScreenShimmerLoading() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(start = 40.dp)
                .clip(RoundedCornerShape(bottomStart = 32.dp))
                .background(brush)
        )
        FlowRow(
            modifier = Modifier
                .padding(start = 40.dp),
            mainAxisSpacing = 16.dp,
            crossAxisSpacing = 8.dp
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .size(width = if (it % 2 == 0) 70.dp else 100.dp, height = 20.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(brush)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 40.dp)
                .padding(start = 40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(brush)
        )
        Box(
            modifier = Modifier
                .size(width = 240.dp, height = 40.dp)
                .padding(start = 40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(brush)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush)
                    )
                    Box(
                        modifier = Modifier
                            .height(36.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(brush)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PosterGridListShimmerLoadingPreview() {
    RisutoTheme {
        PosterGridListShimmerLoading()
    }
}

@Preview
@Composable
fun GridListShimmerLoadingPreview() {
    RisutoTheme {
        GridListShimmerLoading()
    }
}

@Preview
@Composable
fun SeasonScreenShimmerLoadingPreview() {
    RisutoTheme {
        Column {
            HeaderShimmerLoading()
            GridListShimmerLoading()
        }
    }
}

@Preview
@Composable
fun RowListShimmerLoadingPreview() {
    RisutoTheme {
        RowListShimmerLoading()
    }
}

@Preview
@Composable
fun MyAnimeListShimmerLoadingPreview() {
    RisutoTheme {
        MyAnimeListShimmerLoading()
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeScreenShimmerLoadingPreview() {
    RisutoTheme {
        AnimeScreenShimmerLoading()
    }
}