package com.example.risuto.presentation.ui.list
//
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.GridCells
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyVerticalGrid
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Icon
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.bumptech.glide.request.RequestOptions
//import com.chun2maru.risutomvvm.presentation.model.SearchAnimePresentation
//import com.example.risuto.presentation.model.TopAnimePresentation
//import com.example.risuto.presentation.model.generateDummyAnime
//import dev.chrisbanes.accompanist.glide.GlideImage
//import java.text.NumberFormat
//import java.util.*
//
//@ExperimentalFoundationApi
//@Composable
//fun ListScreen(
//    animes: List<SearchAnimePresentation>,
//    anime: List<TopAnimePresentation>,
//    executeAnimeSearch: (String) -> Unit,
//    executeTopResult: (String, Int, String) -> Unit
//) {
//    executeAnimeSearch("Wonder Egg Priority")
//    Column {
//        ListToolBar()
//        SearchGridList(animes = animes)
//    }
//}
//
//@ExperimentalFoundationApi
//@Composable
//fun SearchGridList(animes: List<SearchAnimePresentation>) {
//    LazyVerticalGrid(
//        cells = GridCells.Adaptive(minSize = 180.dp),
//        modifier = Modifier
//            .padding(16.dp)
//    ) {
//        var count = 0
//        items(items = animes) { anime ->
//            count++
//            if(count % 2 == 0){
//                SearchGrid(anime = anime, modifier = Modifier.padding(end = 0.dp))
//            } else {
//                SearchGrid(anime = anime, modifier = Modifier.padding(end = 16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun SearchGrid(
//    anime: SearchAnimePresentation,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier
//            .fillMaxSize()
//            .padding(bottom = 16.dp)
//            .shadow(elevation = 4.dp, shape = RectangleShape, true)
//            .background(Color.White)
//    ) {
//        Box(modifier = Modifier
//            .size(width = 180.dp, height = 216.dp)
//            .background(Color.Gray)
//        ) {
//            GlideImage(
//                data = anime.image_url.replace("\\\\", "/"),
//                contentDescription = "Anime Picture",
//                fadeIn = true,
//                modifier = Modifier.fillMaxSize(),
//                requestBuilder = {
//                    val options = RequestOptions()
//                    options
//                        .centerCrop()
//                    apply(options)
//                }
//            )
//        }
//        Column(modifier = Modifier.padding(8.dp)){
//            Text(
//                text = anime.title,
//                fontWeight = FontWeight.Bold,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis
//            )
//            Column {
//                Row {
//                    Icon(Icons.Filled.Star, contentDescription = null)
//                    Text(
//                        text = anime.score.toString()
//                    )
//                }
//                Row {
//                    Icon(Icons.Filled.Person, contentDescription = null)
//                    val members = NumberFormat.getNumberInstance(Locale.ENGLISH).format(anime.members)
//                    Text(text = members, maxLines = 1)
//                }
//            }
//        }
//    }
//}
//
//@ExperimentalFoundationApi
//@Preview
//@Composable
//fun DummyListScreen() {
//    Column {
//        ListToolBar()
//        DummyAnimeList(dummyModels = generateDummyAnime())
//    }
//}
//
//@Composable
//fun ListToolBar() {
//    TopAppBar(
//        title = { Text("Risuto") },
//    )
//}
//
//@Preview
//@Composable
//fun ListToolBarPreview(){
//    ListToolBar()
//}