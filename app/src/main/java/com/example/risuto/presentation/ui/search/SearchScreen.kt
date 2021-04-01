package com.example.risuto.presentation.ui.search

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.risuto.presentation.model.AnimeListPresentation
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
        onValueChange = viewModel::onSearchAnime,
        navToDetail = { navToDetail(it) }
    )
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun SearchContent(
    items: List<AnimeListPresentation>,
    onValueChange: (String) -> Unit,
    navToDetail: (Int) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isResultShowed by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(MaterialTheme.shapes.large)
                .size(width = 120.dp, height = 32.dp)
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
                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(color = MaterialTheme.colors.secondaryVariant, fontWeight = FontWeight.Bold),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions( onDone = {
                        onValueChange(text)
                        isResultShowed = true
                        Log.d("TAG", "Pressed")
                        keyboardController?.hideSoftwareKeyboard()
                    })
                )

            }
        }
        if(isResultShowed) {
            ColumnList(items = items, navToDetail = { navToDetail(it) })
        }
    }
}

@Composable
fun SearchList(
    query: String,
    items: List<AnimeListPresentation>,
    onValueChange: (String) -> Unit
) {

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