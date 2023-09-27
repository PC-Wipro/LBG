package com.lbg.project.lbgTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lbg.project.data.services.CatsService
import com.lbg.project.models.CatsDataModel
import com.lbg.project.models.catsMock.MocksCatsDataModel
import com.lbg.project.models.catsMock.toResponseCats
import com.lbg.project.repo.CatsRepository
import com.lbg.project.viewModel.MainViewModel
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var mViewModel: MainViewModel

    @get:Rule
    val testInstantTaskExecuterRules: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catService: CatsService

    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val mCatsRepo = CatsRepository(catService)
        Dispatchers.setMain(testDispatcher)

        mViewModel = MainViewModel(mCatsRepo)
    }

    @Test
    fun testGetEmptyData() = runTest(UnconfinedTestDispatcher()) {
        val expectedRepositories = Response.success(listOf<CatsDataModel>())
        // Mock the API response
        Mockito.`when`(catService.fetchCatsImages(0)).thenReturn(expectedRepositories)
        // Call the method under test
        val result = catService.fetchCatsImages(0)
        // Verify that the API method is called with the correct username
        Mockito.verify(catService).fetchCatsImages(0)
        // Verify that the result matches the expected repositories
        assert(result == expectedRepositories)
    }

    @Test
    fun testGetApiData() = runTest(UnconfinedTestDispatcher()) {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val mockCatsData = MocksCatsDataModel()
        val response = toResponseCats(mockCatsData)
        Mockito.`when`(catService.fetchCatsImages(25)).thenReturn(response)// Mock the API response
        mViewModel.getCatsData()
        val call=verify(catService).fetchCatsImages(10)
        val verifyData = if(call?.isSuccessful == true) call.body() else null
        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutine complete and changes propagate
        val result = mViewModel.catsData.value
        assertEquals(verifyData, result,"Not equal")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }


}