package com.example.listofimages.data.api.services

import com.example.listofimages.data.api.constants.ApiConstants
import com.example.listofimages.data.model.ImageWithTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {
    @GET(ApiConstants.Urls.PHOTO_URL)
    suspend fun listImagesByAlbumId(
        @Query(ApiConstants.Parameters.PAGE) albumId: Int,
        @Query(ApiConstants.Parameters.LIMIT) limit: Int = 10
    ): Response<List<ImageWithTitle>>
}