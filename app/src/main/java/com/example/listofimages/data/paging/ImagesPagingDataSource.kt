package com.example.listofimages.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.listofimages.data.model.ImageWithTitle
import com.example.listofimages.data.repository.NetworkRepository

class ImagesPagingDataSource(
    private val networkRepository: NetworkRepository
) : PagingSource<Int, ImageWithTitle>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageWithTitle> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = networkRepository.listImagesByAlbumId(nextPageNumber)

            return when (response.isSuccessful) {
                true -> LoadResult.Page(
                    data = response.body()!!,
                    prevKey = null,
                    nextKey = nextPageNumber + 1
                )
                else -> LoadResult.Error(
                    Exception(
                        response.errorBody()?.toString() ?: "Unknown error"
                    )
                )
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageWithTitle>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}