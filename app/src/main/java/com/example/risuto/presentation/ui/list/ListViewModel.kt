package com.example.risuto.presentation.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject constructor(
    private val savedState: SavedStateHandle
): ViewModel() {

    val gridListFromArgs = savedState
}