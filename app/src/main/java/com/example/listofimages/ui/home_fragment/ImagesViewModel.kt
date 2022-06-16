package com.example.listofimages.ui.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.listofimages.data.paging.ImagesPagingDataSource
import com.example.listofimages.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {
    val images = Pager(
        PagingConfig(pageSize = 1)
    ) {
        ImagesPagingDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}
