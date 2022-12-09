package com.health13.newshub.data.repository

import com.health13.newshub.data.models.NewsResponse
import com.health13.newshub.data.repository.network.NetworkInstance
import retrofit2.Call


class NewsRepository(private val network: NetworkInstance = NetworkInstance) {

   fun getBreakingNews(countryCode: String, pageNumber: Int): Call<NewsResponse> {
        return  network.api.getBreakingNews(countryCode, pageNumber)
    }

    fun searchNews(searchQuery: String, pageNumber: Int) =
        network.api.searchForNews(searchQuery, pageNumber)
}