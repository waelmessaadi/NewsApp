package com.instant.newsapp.data.repository

import com.instant.newsapp.data.db.ArticleDao
import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.data.remote.dto.ArticleDto
import com.instant.newsapp.data.remote.dto.NewsResponseDto
import com.instant.newsapp.util.MockResponseFileReader
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var articleDao: ArticleDao

    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        articleDao = mockk()
        repository = RepositoryImpl(apiService, articleDao)
    }

    @Test
    fun `should return a list of articles when the API call is successful`() {
        runBlocking {
            val mockResponse = MockResponseFileReader("mock_articles.json").loadArticlesFromFile()

            `when`(apiService.getNewsList()).thenReturn(NewsResponseDto(
                status = "ok",
                totalResults = mockResponse.size,
                articles = mockResponse.map {
                    ArticleDto(
                        it.title,
                        null,
                        it.description,
                        null,
                        null,
                        it.title,
                        null,
                        it.url
                    )
                }
            ))


            val result = repository.getNewsFromApi()

            assert(result.size == mockResponse.size)
            assert(result[0].title == mockResponse[0].title)
            assert(result[1].title == mockResponse[1].title)
        }
    }

    @Test
    fun `should throw an exception when the API call fails`(): Unit {
        runBlocking {
            `when`(apiService.getNewsList()).thenThrow(RuntimeException("API call failed"))

            try {
                repository.getNewsFromApi()
            } catch (e: Exception) {
                assert(e is RuntimeException)
                assert(e.message == "API call failed")
            }
        }
    }

    @Test
    fun `should return an empty list when the API returns an empty list of articles`() {
        runBlocking {

            `when`(apiService.getNewsList()).thenReturn(
                NewsResponseDto(
                    status = "ok",
                    totalResults = 0,
                    articles = emptyList()
                )
            )

            val result = repository.getNewsFromApi()

            assert(result.isEmpty())
        }
    }
}
//@ExtendWith(MockitoExtension::class)
//class RepositoryImplTest {
//
//    @Mock
//    private lateinit var apiService: ApiService
//
//    private lateinit var repository: RepositoryImpl
//
//    @Before
//    fun setUp() {
//        repository = RepositoryImpl(apiService)
//    }
//
//    @Test
//    fun `should return a list of articles when the API call is successful`() = runBlocking {
//        val mockResponse = MockResponseFileReader("mock_articles.json").loadArticlesFromFile()
//
//        `when`(apiService.getNewsList()).thenReturn(
//            NewsResponseDto(
//                status = "ok",
//                totalResults = mockResponse.size,
//                articles = mockResponse.map { ArticleDto(it.title, null, it.description, null, null, it.title, null, it.url) }
//            )
//        )
//
//        val result = repository.getNews()
//
//        verify(apiService).getNewsList()
//        assert(result.size == mockResponse.size)
//        assert(result[0].title == mockResponse[0].title)
//        assert(result[1].title == mockResponse[1].title)
//    }
//
//    @Test
//    fun `should throw an exception when the API call fails`(): Unit = runBlocking {
//        `when`(apiService.getNewsList()).thenThrow(RuntimeException("API call failed"))
//
//        try {
//            repository.getNews()
//        } catch (e: Exception) {
//            assert(e is RuntimeException)
//            assert(e.message == "API call failed")
//        }
//    }
//
//    @Test
//    fun `should return an empty list when the API returns an empty list of articles`() = runBlocking {
//        `when`(apiService.getNewsList()).thenReturn(
//            NewsResponseDto(
//                status = "ok",
//                totalResults = 0,
//                articles = emptyList()
//            )
//        )
//
//        val result = repository.getNews()
//
//        assert(result.isEmpty())
//    }
//}
