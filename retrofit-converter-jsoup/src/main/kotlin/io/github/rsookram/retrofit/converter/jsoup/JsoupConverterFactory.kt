package io.github.rsookram.retrofit.converter.jsoup

import java.lang.reflect.Type
import okhttp3.ResponseBody
import org.jsoup.nodes.Document
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * A [Converter.Factory] which adds support for returning jsoup [Document]s in a Retrofit
 * interface.
 *
 * @param charsetName character set of response body. Set to `null` to determine from `http-equiv`
 * meta tag if present, falling back to UTF-8.
 * @param baseUri The URL where the HTML was retrieved from, to resolve relative links against.
 */
public class JsoupConverterFactory(
    private val charsetName: String? = "UTF-8",
    private val baseUri: String = "",
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? =
        if (type == Document::class.java) {
            JsoupResponseBodyConverter(charsetName, baseUri)
        } else {
            null
        }
}
