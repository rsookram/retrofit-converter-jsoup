package io.github.rsookram.retrofit.converter.jsoup

import java.lang.reflect.Type
import okhttp3.ResponseBody
import org.jsoup.nodes.Document
import retrofit2.Converter
import retrofit2.Retrofit

class JsoupConverterFactory(
    private val charsetName: String? = null,
    private val baseUri: String = "",
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        if (type == Document::class.java) {
            return JsoupResponseBodyConverter(charsetName, baseUri)
        } else {
            return null
        }
    }
}
