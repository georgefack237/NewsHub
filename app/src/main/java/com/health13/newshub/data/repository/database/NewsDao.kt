package com.health13.newshub.data.repository.database

import androidx.room.*
import com.health13.newshub.data.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * from articles ORDER BY title ASC")
    fun getBookmarkedNews(): Flow<List<Article>>

    @Query("SELECT * from articles WHERE id= :id")
    fun getNews(id: Int): Flow<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(article: Article)

}