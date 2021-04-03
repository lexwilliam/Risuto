package com.example.risuto.presentation.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
import com.example.risuto.presentation.model.QuerySearch
import com.example.risuto.presentation.ui.component.ChipGroupList
import com.example.risuto.presentation.ui.component.ColumnList

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    navToDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    SearchContent(
        items = viewState.searchAnimes,
        onQueryChange = viewModel::onSearchAnime,
        navToDetail = { navToDetail(it) }
    )
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchContent(
    items: List<AnimeListPresentation>,
    onQueryChange: (QuerySearch) -> Unit,
    navToDetail: (Int) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    var resultType by remember { mutableStateOf(ResultType.Filter) }
    if(text.isEmpty()){
        resultType = ResultType.Filter
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            onValueChange = {
                text = it
                onQueryChange(QuerySearch(it, null, null, null, 5))
                if(isFocused){
                    resultType = ResultType.TitleResult
                }
            },
            interactionSource = interactionSource,
            textStyle = TextStyle(color = MaterialTheme.colors.primaryVariant, fontWeight = FontWeight.Bold),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions( onDone = {
                onQueryChange(QuerySearch(text, null, null, null, null))
                resultType = ResultType.FullResult
                keyboardController?.hideSoftwareKeyboard()
            }),
            decorationBox = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .clip(MaterialTheme.shapes.large)
                        .size(width = 120.dp, height = 40.dp)
                ) {
                    Row(
                        modifier = Modifier.background(color = MaterialTheme.colors.primary),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primaryVariant
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        it()
                    }
                }
            }
        )
        when(resultType){
            ResultType.TitleResult ->
                SearchList(items = items)
            ResultType.FullResult ->
                ColumnList(items = items, navToDetail = { navToDetail(it) })
            ResultType.Filter ->
                ChipGroupList()
        }
    }
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}

enum class ResultType{
    TitleResult, FullResult, Filter, Loading
}

@Composable
fun SearchList(
    items: List<AnimeListPresentation>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(items = items){ item ->
            SearchRowItem(item = item)
        }
    }
}

@Composable
fun SearchRowItem(
    item: AnimeListPresentation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(text = item.title)
    }
}