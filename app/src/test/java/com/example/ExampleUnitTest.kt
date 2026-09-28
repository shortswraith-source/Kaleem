package com.example

import com.example.data.datasource.CctvLessonsDataSource
import com.example.data.datasource.HardwareLessonsDataSource
import com.example.data.datasource.NetworkingLessonsDataSource
import com.example.data.datasource.PracticeScenariosDataSource
import com.example.data.datasource.PrinterLessonsDataSource
import com.example.data.datasource.QuizDataSource
import com.example.data.datasource.SoftwareLessonsDataSource
import com.example.data.datasource.ToolsDataSource
import com.example.data.datasource.WindowsLessonsDataSource
import com.example.data.model.DifficultyLevel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testAllCategoriesHaveLessons() {
        val winLessons = WindowsLessonsDataSource.getLessons()
        val hwLessons = HardwareLessonsDataSource.getLessons()
        val printLessons = PrinterLessonsDataSource.getLessons()
        val cctvLessons = CctvLessonsDataSource.getLessons()
        val netLessons = NetworkingLessonsDataSource.getLessons()
        val softLessons = SoftwareLessonsDataSource.getLessons()

        assertTrue(winLessons.size >= 12)
        assertTrue(hwLessons.size >= 13)
        assertTrue(printLessons.size >= 10)
        assertTrue(cctvLessons.size >= 10)
        assertTrue(netLessons.size >= 11)
        assertTrue(softLessons.size >= 9)

        val totalLessons = winLessons.size + hwLessons.size + printLessons.size +
                cctvLessons.size + netLessons.size + softLessons.size
        assertTrue(totalLessons >= 65)
    }

    @Test
    fun testEveryLessonHasRequiredComponents() {
        val allLessons = WindowsLessonsDataSource.getLessons() +
                HardwareLessonsDataSource.getLessons() +
                PrinterLessonsDataSource.getLessons() +
                CctvLessonsDataSource.getLessons() +
                NetworkingLessonsDataSource.getLessons() +
                SoftwareLessonsDataSource.getLessons()

        for (lesson in allLessons) {
            assertTrue("Lesson title should not be blank: ${lesson.id}", lesson.title.isNotBlank())
            assertTrue("Explanation should be thorough: ${lesson.id}", lesson.explanation.length > 30)
            assertTrue("Step-by-step instructions required: ${lesson.id}", lesson.stepByStep.isNotEmpty())
            assertTrue("Required tools list should not be empty: ${lesson.id}", lesson.requiredTools.isNotEmpty())
            assertTrue("Troubleshooting or warnings required: ${lesson.id}", lesson.troubleshootingTips.isNotEmpty() || lesson.importantWarnings.isNotEmpty())
        }
    }

    @Test
    fun testPracticeScenariosIntegrity() {
        val scenarios = PracticeScenariosDataSource.getScenarios()
        assertEquals(10, scenarios.size)

        for (scenario in scenarios) {
            assertTrue("Complaint should not be blank", scenario.customerComplaint.isNotBlank())
            assertTrue("Symptoms should be present", scenario.availableSymptoms.isNotEmpty())
            assertTrue("Possible causes should be present", scenario.possibleCauses.isNotEmpty())
            assertTrue("Choices should exist", scenario.diagnosticChoices.size >= 2)
            assertNotNull("Correct choice ID must match a choice", scenario.diagnosticChoices.find { it.id == scenario.correctChoiceId })
            assertTrue("Systematic path must exist", scenario.systematicDiagnosticPath.isNotBlank())
            assertTrue("Final solution must exist", scenario.finalSolution.isNotBlank())
        }
    }

    @Test
    fun testQuizSystem() {
        for (level in DifficultyLevel.entries) {
            val questions = QuizDataSource.getQuestions(level)
            assertTrue("Questions should exist for $level", questions.isNotEmpty())
            for (q in questions) {
                assertTrue("Question text should not be blank", q.question.isNotBlank())
                assertTrue("Options should be at least 4", q.options.size >= 4)
                assertTrue("Correct index must be within range", q.correctIndex in q.options.indices)
                assertTrue("Explanation must be present", q.explanation.isNotBlank())
            }
        }
    }

    @Test
    fun testToolsReference() {
        val tools = ToolsDataSource.getTools()
        assertTrue(tools.size >= 10)
        for (tool in tools) {
            assertTrue("Name must be present", tool.name.isNotBlank())
            assertTrue("What it does must be present", tool.whatItDoes.isNotBlank())
            assertTrue("When to use it must be present", tool.whenToUseIt.isNotBlank())
            assertTrue("Instructions must be present", tool.basicInstructions.isNotEmpty())
            assertTrue("Safety notes must be present", tool.safetyNotes.isNotBlank())
        }
    }
}
