import com.instant.newsapp.data.remote.ApiService
import com.instant.newsapp.util.MockResponseFileReader
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.Assert.*

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        mockWebServer.start()

        val baseUrl = mockWebServer.url("/").toString()

        apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetNewsList() = runBlocking {
        val mockData = MockResponseFileReader("mock_data.json").content

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockData)

        mockWebServer.enqueue(mockResponse)

        val response = apiService.getNewsList()

        assertNotNull(response)
        assertEquals("Example Article Title", response.articles.first().title)
    }
}
