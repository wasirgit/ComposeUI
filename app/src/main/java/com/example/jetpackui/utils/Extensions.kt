package com.example.jetpackui.utils

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.jetpackui.data.model.Movie

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
@Composable
fun <T : Any> LazyPagingItems<T>.PagingLoadingState(isLoaded: (pagingState: Boolean) -> Unit) {
    this.apply {
        when {
            // data is loading for first time
            loadState.refresh is LoadState.Loading -> {
                isLoaded(true)
            }
            // data is loading for second time or pagination
            loadState.append is LoadState.Loading -> {
                isLoaded(true)
            }

            loadState.refresh is LoadState.NotLoading -> {
                isLoaded(false)
            }

            loadState.append is LoadState.NotLoading -> {
                isLoaded(false)
            }
        }
    }
}
