import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.instant.newsapp.domain.model.Article
import com.instant.newsapp.domain.usecases.NewUseCase
import com.instant.newsapp.presentation.favorite_news_list_screen.FavoriteNewsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import com.instant.newsapp.R

import io.mockk.mockk
import com.instant.newsapp.domain.usecases.FakeGetFavoriteNews

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteNewsListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteNewsListViewModel
    private lateinit var mockNewUseCase: NewUseCase
    private lateinit var mockContext: Context

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        mockNewUseCase = mockk()
        mockContext = mockk {
            every { getString(R.string.no_favorite_news_founded) } returns "Aucun favori trouvé"
        }
    }

    @Test
    fun `getSavedNews should update state with favorite news`() = runTest {
        val articles = listOf(
            Article(title = "Article 1", description = "Description 1", url = "http://example.com/1", urlToImage = ""),
            Article(title = "Article 2", description = "Description 2", url = "http://example.com/2", urlToImage = "")
        )

        val fakeGetFavoriteNews = FakeGetFavoriteNews(articles)

        viewModel = FavoriteNewsListViewModel(mockNewUseCase, fakeGetFavoriteNews, mockContext)

        advanceUntilIdle()

        val state = viewModel.state.value

        println("State news: ${state.news}")
        println("State error: ${state.error}")

        assert(state.news == articles) { "Les articles ne correspondent pas à ceux simulés." }
        assert(false) { "Il ne devrait pas y avoir d'erreur." }
    }

    @Test
    fun `getSavedNews should update state when no news are found`() = runTest {
        val fakeGetFavoriteNews = FakeGetFavoriteNews(emptyList())

        viewModel = FavoriteNewsListViewModel(mockNewUseCase, fakeGetFavoriteNews, mockContext)

        advanceUntilIdle()

        val state = viewModel.state.value
        assert(state.news.isEmpty())
        assert(state.error == "Aucun favori trouvé")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}


