package com.example.myapplication

import com.example.myapplication.data.repository.MockRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MockRepositoryTest {

    @Test
    fun testStudentProfile() {
        val student = MockRepository.getStudentProfile()
        assertNotNull(student)
        assertEquals("Alex", student.firstName)
        assertEquals("Kowalczyk", student.lastName)
        assertEquals("12345", student.albumNumber)
        assertEquals("Informatyka", student.fieldOfStudy)
    }

    @Test
    fun testScheduleItems() {
        val items = MockRepository.getScheduleItems()
        assertTrue(items.isNotEmpty())
        assertTrue(items.any { it.title.contains("Programowanie") })
        assertTrue(items.any { it.title.contains("Bazy danych") })
        assertTrue(items.any { it.title.contains("Sieci") })
    }

    @Test
    fun testFinanceOverview() {
        val finance = MockRepository.getFinanceOverview()
        assertEquals(2400.0, finance.totalToPay, 0.01)
        assertTrue(finance.installments.isNotEmpty())
    }

    @Test
    fun testAffairsAndFaq() {
        val affairs = MockRepository.getAffairItems()
        val faqs = MockRepository.getFaqItems()
        assertTrue(affairs.isNotEmpty())
        assertTrue(faqs.isNotEmpty())
        assertTrue(affairs.any { it.title.contains("stypendium") })
    }
}
