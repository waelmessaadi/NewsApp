package com.instant.newsapp.presentation.new_detail_screen

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.usecases.NewUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import com.instant.newsapp.R

@OptIn(ExperimentalCoroutinesApi::class)
class NewDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewDetailViewModel
    private lateinit var mockNewUseCase: NewUseCase
    private lateinit var mockContext: Context

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        mockNewUseCase = mockk()
        mockContext = mockk()

        viewModel = NewDetailViewModel(mockNewUseCase, mockContext)
    }

    @Test
    fun `toggleFavorite should update state on success`() = runTest {
        val mockArticle = mockk<Article>()

        coEvery { mockNewUseCase.insertFavoriteNew(mockArticle) } returns Unit

        every { mockContext.getString(R.string.new_favorite_successfully) } returns "Article ajouté aux favoris"

        viewModel.toggleFavorite(mockArticle)

        advanceUntilIdle()

        val state = viewModel.state.value

        assert(!state.isLoading)
        assert(state.successMessage == "Article ajouté aux favoris")
        assert(state.isFavorite)

        coVerify { mockNewUseCase.insertFavoriteNew(mockArticle) }
    }

    @Test
    fun `toggleFavorite should update state on failure`() = runTest {
        val mockArticle = mockk<Article>()

        val exceptionMessage = "Database error"
        coEvery { mockNewUseCase.insertFavoriteNew(mockArticle) } throws Exception(exceptionMessage)

        viewModel.toggleFavorite(mockArticle)

        advanceUntilIdle()

        val state = viewModel.state.value
        assert(!state.isLoading)

        assert(state.errorMessage == "Failed to favorite article: $exceptionMessage")

        assert(!state.isLoading)
        assert(!state.isFavorite)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
