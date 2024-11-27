package com.instant.newsapp.presentation.news_list_screen

import CoroutineTestRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.util.MockResponseFileReader
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

import io.mockk.*
import kotlinx.coroutines.test.*
import kotlin.test.assertEquals


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.instant.newsapp.domain.usecases.NewUseCase
import com.instant.newsapp.presentation.news_list_screen.NewsListState
import com.instant.newsapp.presentation.news_list_screen.NewsListViewModel
import com.instant.newsapp.util.CommonMethods
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


@Suppress("DEPRECATION")
@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var mockNewUseCase: NewUseCase
    private lateinit var mockContext: Context
    private lateinit var mockConnectivityManager: ConnectivityManager
    private lateinit var mockNetworkInfo: NetworkInfo

    @Before
    fun setUp() {
        mockNewUseCase = mockk()
        mockContext = mockk()
        mockConnectivityManager = mockk()
        mockNetworkInfo = mockk()

        every { mockContext.getSystemService(Context.CONNECTIVITY_SERVICE) } returns mockConnectivityManager

        every { mockConnectivityManager.activeNetworkInfo } returns mockNetworkInfo

        viewModel = NewsListViewModel(mockNewUseCase, mockContext)

        mockkStatic(CommonMethods::class)
    }

    @Test
    fun `checkConnectionAndSync should call syncNews when online`() = runTest {
        every { mockNetworkInfo.isConnected } returns true
        every { CommonMethods.isOnline(mockConnectivityManager) } returns true

        coEvery { mockNewUseCase.syncNews() } returns Unit

        viewModel.checkConnectionAndSync()

        coVerify { mockNewUseCase.syncNews() }

        assertEquals(viewModel.state.value.isLoading, true)
    }
/*
    @Test
    fun `checkConnectionAndSync should call getNewsFromDb when offline`() = runTest {
        // Simuler une connexion hors ligne
        every { mockNetworkInfo.isConnected } returns false // Simuler une connexion absente
        every { CommonMethods.isOnline(mockConnectivityManager) } returns false

        // Simuler que getNewsFromDb retourne des données
        val mockNewsList = listOf("News1", "News2")
        coEvery { mockNewUseCase.getNewsFromDb() } returns mockNewsList

        // Appeler checkConnectionAndSync
        viewModel.checkConnectionAndSync()

        // Vérifier que getNewsFromDb a été appelé
        coVerify { mockNewUseCase.getNewsFromDb() }

        // Vérifier que l'état a été mis à jour avec les nouvelles données
        assertEquals(viewModel.state.value.news, mockNewsList)
    }

    @Test
    fun `syncNews should update state on success`() = runTest {
        // Simuler une connexion en ligne
        every { mockNetworkInfo.isConnected } returns true // Simuler un réseau connecté
        every { CommonMethods.isOnline(mockConnectivityManager) } returns true

        // Simuler que syncNews ne fait rien (Unit)
        coEvery { mockNewUseCase.syncNews() } returns Unit

        // Simuler que getNewsFromDb retourne des données
        val mockNewsList = listOf("News1", "News2")
        coEvery { mockNewUseCase.getNewsFromDb() } returns mockNewsList

        // Appeler checkConnectionAndSync
        viewModel.checkConnectionAndSync()

        // Vérifier que syncNews a été appelé
        coVerify { mockNewUseCase.syncNews() }

        // Vérifier que l'état a été mis à jour avec les nouvelles données
        assertEquals(viewModel.state.value.news, mockNewsList)
        assertEquals(viewModel.state.value.isLoading, false)
    }
*/
    @Test
    fun `syncNews should update state on error`() = runTest {
        // Simuler une connexion en ligne
        every { mockNetworkInfo.isConnected } returns true // Simuler un réseau connecté
        every { CommonMethods.isOnline(mockConnectivityManager) } returns true

        // Simuler que syncNews lance une exception
        coEvery { mockNewUseCase.syncNews() } throws Exception("Sync failed")

        // Appeler checkConnectionAndSync
        viewModel.checkConnectionAndSync()

        // Vérifier que syncNews a été appelé
        coVerify { mockNewUseCase.syncNews() }

        // Vérifier que l'état a été mis à jour avec une erreur
        assertEquals(viewModel.state.value.error, "Sync failed")
        assertEquals(viewModel.state.value.isLoading, false)
    }
}





/*@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val getNewsUseCase: GetNewsUseCase = mockk()

    private val newUseCase: NewUseCase = mockk()

    private lateinit var viewModel: NewsListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = NewsListViewModel(getNewsUseCase, newUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getNews success`() = runTest(testDispatcher) {
        val fakeArticles = MockResponseFileReader("mock_news_list.json").loadArticlesFromFile()

        coEvery { getNewsUseCase.invoke() } returns flow {
            emit(Resource.Success(fakeArticles))
        }

        val viewModel = NewsListViewModel(getNewsUseCase, newUseCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.news.isNotEmpty())
        assertEquals("Example Article Title1", state.news[0].title)
    }

    @Test
    fun `test getNews error`() = runTest(testDispatcher) {
        coEvery { getNewsUseCase.invoke() } returns flow {
            emit(Resource.Error("Erreur lors de la récupération des articles"))
        }

        val viewModel = NewsListViewModel(getNewsUseCase, newUseCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.news.isEmpty())
        assertEquals("Erreur lors de la récupération des articles", state.error)
    }

}
*/