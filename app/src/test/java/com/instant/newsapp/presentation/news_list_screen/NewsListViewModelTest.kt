package com.instant.newsapp.presentation.news_list_screen

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.instant.newsapp.domain.usecases.GetNewsUseCase
import com.instant.newsapp.util.Resource
import com.instant.newsapp.domain.model.Article
import io.mockk.coEvery
import io.mockk.mockk

@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)

        getNewsUseCase = mockk()
    }

    @Test
    fun `test getNews success`() = runTest(testDispatcher) {
        val fakeArticles = listOf(
            Article("Description 1","Titre 1" ,"url1", "urlImage1"),
            Article( "Description 2", "Titre 2","url2", "urlImage2")
        )

        coEvery { getNewsUseCase.invoke() } returns flow {
            emit(Resource.Success(fakeArticles))
        }

        val viewModel = NewsListViewModel(getNewsUseCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.news.isNotEmpty())
        assertEquals("Titre 1", state.news[0].title)
    }

    @Test
    fun `test getNews error`() = runTest(testDispatcher) {
        // Configuration de MockK pour retourner une erreur
        coEvery { getNewsUseCase.invoke() } returns flow {
            emit(Resource.Error("Erreur lors de la récupération des articles"))
        }

        // Création du ViewModel avec le cas d'utilisation mocké
        val viewModel = NewsListViewModel(getNewsUseCase)

        // Avance toutes les coroutines
        advanceUntilIdle()

        // Vérifiez l'état final
        val state = viewModel.state.value
        assertTrue(state.news.isEmpty())
        assertEquals("Erreur lors de la récupération des articles", state.error)
    }

}
