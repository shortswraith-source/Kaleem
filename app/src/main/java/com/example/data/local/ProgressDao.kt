package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.BookmarkedItemEntity
import com.example.data.local.entity.CompletedLessonEntity
import com.example.data.local.entity.QuizScoreEntity
import com.example.data.local.entity.SolvedScenarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM completed_lessons ORDER BY completedAt DESC")
    fun getAllCompletedLessons(): Flow<List<CompletedLessonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedLesson(lesson: CompletedLessonEntity)

    @Query("DELETE FROM completed_lessons WHERE lessonId = :lessonId")
    suspend fun deleteCompletedLesson(lessonId: String)

    @Query("SELECT * FROM solved_scenarios ORDER BY completedAt DESC")
    fun getAllSolvedScenarios(): Flow<List<SolvedScenarioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSolvedScenario(scenario: SolvedScenarioEntity)

    @Query("SELECT * FROM quiz_scores ORDER BY takenAt DESC")
    fun getAllQuizScores(): Flow<List<QuizScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizScore(score: QuizScoreEntity)

    @Query("SELECT * FROM bookmarked_items ORDER BY savedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkedItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(item: BookmarkedItemEntity)

    @Query("DELETE FROM bookmarked_items WHERE itemId = :itemId")
    suspend fun deleteBookmark(itemId: String)

    @Query("DELETE FROM completed_lessons")
    suspend fun clearCompletedLessons()

    @Query("DELETE FROM solved_scenarios")
    suspend fun clearSolvedScenarios()

    @Query("DELETE FROM quiz_scores")
    suspend fun clearQuizScores()

    @Query("DELETE FROM bookmarked_items")
    suspend fun clearBookmarks()
}
