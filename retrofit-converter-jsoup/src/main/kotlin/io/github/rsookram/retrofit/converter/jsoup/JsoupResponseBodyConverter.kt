package io.github.rsookram.retrofit.converter.jsoup

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Converter

internal class JsoupResponseBodyConverter(
    private val charsetName: String?,
    private val baseUri: String,
) : Converter<ResponseBody, Document> {

    override fun convert(value: ResponseBody): Document? =
        value.use {
            Jsoup.parse(value.byteStream(), charsetName, baseUri)
        }
}
