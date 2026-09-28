package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.entity.BookmarkedItemEntity
import com.example.data.local.entity.CompletedLessonEntity
import com.example.data.local.entity.QuizScoreEntity
import com.example.data.local.entity.SolvedScenarioEntity

@Database(
    entities = [
        CompletedLessonEntity::class,
        SolvedScenarioEntity::class,
        QuizScoreEntity::class,
        BookmarkedItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "it_master_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
