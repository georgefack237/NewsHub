package com.health13.newshub.data.models

import com.health13.newshub.data.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)