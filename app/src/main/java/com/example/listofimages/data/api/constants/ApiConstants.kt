package com.example.listofimages.data.api.constants

sealed class ApiConstants {
    object Urls {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val PHOTO_URL = "photos"
    }

    object Parameters {
        const val PAGE = "_page"
        const val LIMIT = "_LIMIT"
    }
}