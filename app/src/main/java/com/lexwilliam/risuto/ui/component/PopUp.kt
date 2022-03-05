package com.lexwilliam.risuto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.lexwilliam.risuto.model.WatchStatusPresentation

@Composable
fun MyAnimePopUp(
    setScore: (Int) -> Unit,
    setWatchStatus: (WatchStatusPresentation) -> Unit,
    onDoneClicked: () -> Unit
) {
    Popup(alignment = Alignment.Center) {
        Box(modifier = Modifier
            .wrapContentSize()
            .background(MaterialTheme.colors.background)
        ) {
            Column(modifier = Modifier.padding(16.dp)){
                var score by remember { mutableStateOf(-1) }
                var watchState by remember { mutableStateOf(WatchStatusPresentation.PlanToWatch) }
                var expandedWatchStatus by remember { mutableStateOf(false) }
                var expandedScore by remember { mutableStateOf(false) }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { expandedScore = true }) {
                    DropdownMenu(expanded = expandedScore, onDismissRequest = { expandedScore = false }) {
                        for(i in 10 downTo 1) {
                            DropdownMenuItem(onClick = {
                                score = i
                                expandedScore = false
                            }) {
                                Text(i.toString())
                            }
                        }
                    }
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { expandedWatchStatus = true }) {
                    DropdownMenu(expanded = expandedWatchStatus, onDismissRequest = { expandedWatchStatus = false }) {
                        DropdownMenuItem(onClick = {
                            watchState = WatchStatusPresentation.PlanToWatch
                            expandedWatchStatus = false
                        }) {
                            Text("Plan To Watch")
                        }
                        DropdownMenuItem(onClick = {
                            watchState = WatchStatusPresentation.Completed
                            expandedWatchStatus = false
                        }) {
                            Text("Completed")
                        }
                        DropdownMenuItem(onClick = {
                            watchState = WatchStatusPresentation.Watching
                            expandedWatchStatus = false
                        }) {
                            Text("Watching")
                        }
                        DropdownMenuItem(onClick = {
                            watchState = WatchStatusPresentation.Dropped
                            expandedWatchStatus = false
                        }) {
                            Text("Dropped")
                        }
                        DropdownMenuItem(onClick = {
                            watchState = WatchStatusPresentation.OnHold
                            expandedWatchStatus = false
                        }) {
                            Text("On Hold")
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .clickable {
                            setScore(score)
                            setWatchStatus(watchState)
                            onDoneClicked()
                        },
                    text = "Done"
                )
            }
        }

    }
}

//@ExperimentalMaterialApi
//@Composable
//fun BottomSheetPopUp(
//    content: @Composable () -> Unit,
//) {
//    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
//    )
//    val coroutineScope = rememberCoroutineScope()
//    BottomSheetScaffold(
//        scaffoldState = bottomSheetScaffoldState,
//        sheetContent = {
//            Box(
//                Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            ) {
//                content()
//            }
//        },
//        sheetPeekHeight = 0.dp
//    ) {
//        coroutineScope.launch {
//            if (isBotSheetShown) {
//                bottomSheetScaffoldState.bottomSheetState.expand()
//            } else {
//                bottomSheetScaffoldState.bottomSheetState.collapse()
//                setBotSheetState(false)
//            }
//        }
//    }
//}

//        Button(onClick = {
//            coroutineScope.launch {
//                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
//                    bottomSheetScaffoldState.bottomSheetState.expand()
//                } else {
//                    bottomSheetScaffoldState.bottomSheetState.collapse()
//                }
//            }
//        }) {
//            Text(text = "Expand/Collapse Bottom Sheet")
//        }