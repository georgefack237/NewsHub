package com.health13.newshub.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.health13.newshub.data.models.Article
import com.health13.newshub.data.repository.database.NewsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val newsDao: NewsDao
) : ViewModel() {



    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text


    fun addNewsBookmark(article: Article){
      CoroutineScope(Dispatchers.IO).launch {
          newsDao.insertNews(article)
      }
    }

}