package com.example.safekiddo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.safekiddo.model.PhoneData
import com.example.safekiddo.repository.local.PhoneDatabase
import com.example.safekiddo.repository.local.dao.PhoneDao
import com.example.safekiddo.repository.remote.Api
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MainActivityViewModelTest {

    @Rule
    @JvmField
    var activityTestRule = InstantTaskExecutorRule()


    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var api: Api
    lateinit var phoneDataBase: PhoneDatabase
    var phoneDao: PhoneDao = mock()
    lateinit var observer: Observer<List<PhoneData>>

    @Before
    fun setUp() {
        api = mock(Api::class.java)
        phoneDataBase = mock(PhoneDatabase::class.java)
        observer = mock()
        mainActivityViewModel = MainActivityViewModel(api, phoneDataBase)
    }


    @Test
    fun getData_onSuccess() {
        val expectedData = provideData()
        `when`(phoneDataBase.phoneDao()).thenReturn(phoneDao)
        `when`(phoneDataBase.phoneDao().getAll()).thenReturn(provideData())
        mainActivityViewModel.phoneDataLiveData.observeForever(observer)
        mainActivityViewModel.getData()
        assertEquals(expectedData, mainActivityViewModel.phoneDataLiveData.value)
    }

    fun provideData(): List<PhoneData> {
        return listOf(
            PhoneData(
                0,
                "S8",
                "Samsung",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
    }

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}