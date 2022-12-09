package com.health13.newshub.domain

import com.health13.newshub.data.repository.NewsRepository

class GetBreakingNewsUseCase(private val repository: NewsRepository =  NewsRepository()) {
     fun execute(countryCode: String, pageNumber: Int) = repository.getBreakingNews(countryCode, pageNumber)
}
