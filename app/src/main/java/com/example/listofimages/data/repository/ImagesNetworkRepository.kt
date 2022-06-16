package com.example.listofimages.data.repository

import com.example.listofimages.data.api.services.ImagesService
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val imagesService: ImagesService
) {
    suspend fun listImagesByAlbumId(page: Int) = imagesService.listImagesByAlbumId(page)
}
