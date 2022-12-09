package com.health13.newshub.data.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.health13.newshub.data.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao() : NewsDao

    companion object{

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase{

            return NewsDatabase.INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_db"
                ).fallbackToDestructiveMigration()
                    .build()

                NewsDatabase.INSTANCE = instance

                instance
            }
        }
    }

}

