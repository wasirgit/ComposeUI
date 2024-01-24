package com.example.jetpackui.screens.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackui.common.onFailure
import com.example.jetpackui.common.onLoading
import com.example.jetpackui.common.onSuccess
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.screens.domain.usecase.FeaturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FeaturesUseCase
) : ViewModel() {
    private val _result: MutableState<FeatureState> = mutableStateOf(FeatureState())
    val result: State<FeatureState> = _result

    init {
        viewModelScope.launch {
            useCase.getFeatures()
                .onSuccess {
                    _result.value = FeatureState(data = it)
                }.onFailure {
                    _result.value = FeatureState(
                        error = it.message!!
                    )
                }.onLoading {
                    _result.value = FeatureState(isLoading = true)
                }.collect()
        }
    }
}


data class FeatureState(
    val data: List<Features.Results> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)