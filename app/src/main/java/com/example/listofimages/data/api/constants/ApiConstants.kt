package com.example.listofimages.data.api.constants

sealed class ApiConstants {
    object Urls {
        const val BASE_URL = "http://my-json-server.typicode.com/AAli9400/list-of-images/"
        const val PHOTO_URL = "images"
    }

    object Parameters {
        const val PAGE = "_page"
        const val LIMIT = "_LIMIT"
    }
}