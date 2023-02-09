package com.example.ginitask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ginitask.model.GiniNumber
import com.example.ginitask.repository.Repository
import com.example.ginitask.ui.GiniNumberViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiniViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(
        UiState(
            status = Status.LOADING,
            giniNumbersList = arrayListOf()
        )
    )
    val uiState : StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getGiniNumbers()?.let { list ->
                _uiState.update { state ->
                    state.copy(
                        status = Status.DONE,
                        giniNumbersList = list
                    )
                }
            } ?: kotlin.run {
                _uiState.update { state ->
                    state.copy(
                        status = Status.ERROR
                    )
                }
            }

        }
    }

    data class UiState(
        val status: Status,
        val giniNumbersList: ArrayList<GiniNumberViewItem>
    )

    enum class Status {
        LOADING,
        ERROR,
        DONE
    }

}