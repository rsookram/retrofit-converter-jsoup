package io.github.rsookram.retrofit.converter.jsoup

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

class JsoupConverterFactoryTest {

    interface Service {
        @GET("/")
        fun html(): Call<Document>

        @GET("/")
        fun any(): Call<Any>
    }

    @get:Rule val server = MockWebServer()

    private lateinit var service: Service

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(JsoupConverterFactory())
            .build()
        service = retrofit.create()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unsupportedRequestTypeNotMatched() {
        service.any()
    }

    @Test
    fun supportedRequestType() {
        val html = """
            <html>
              <head></head>
              <body></body>
            </html>
            """.trimIndent()
        val document = Jsoup.parse(html)
        server.enqueue(MockResponse().setBody(html))

        val response = service.html().execute()

        assertEquals(document.outerHtml(), response.body()!!.outerHtml())
    }
}
