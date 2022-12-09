package com.health13.newshub.presentation.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.health13.newshub.data.models.Article
import com.health13.newshub.data.models.NewsResponse
import com.health13.newshub.domain.GetBreakingNewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _breakingNews = MutableLiveData<List<Article>>()
    val breakingNews: LiveData<List<Article>> =  _breakingNews

    private val getBreakingNewsUseCase = GetBreakingNewsUseCase()

    init {
        getBreakingNews("us",2)
    }

    private fun getBreakingNews(countryCode: String, pageNumber: Int){
        CoroutineScope(Dispatchers.IO).launch {

            getBreakingNewsUseCase.execute(countryCode, pageNumber).enqueue(object :
                    Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                    Log.i(ContentValues.TAG, "onResponse ${response.body()!!.articles}")
                    val body = response.body()

                    _breakingNews.value = response.body()!!.articles

                    if (body == null) {
                        Log.w(ContentValues.TAG, "Did not receive valid response body from Yelp API... exiting")
                        return
                    }


                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.i(ContentValues.TAG, "onFailure $t")

                }
            })

        }


    }


}