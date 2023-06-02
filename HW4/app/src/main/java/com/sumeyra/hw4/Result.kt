package com.sumeyra.hw4

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class Result {

    fun news () : List<NewsModel>  {
        val arr = mutableListOf<NewsModel>()
        val url = "https://www.haberler.com/"
        val doc: Document = Jsoup.connect(url).timeout(15000).get()
        val elements: Elements = doc.getElementsByAttribute("data-headlinenumber")
        for ( item in elements) {

            val img = item.getElementsByTag("img")
            val href = item.attr("abs:href")
            val src = img.attr("data-src")
            val title = img.attr("alt")

            if ( title != "" && href != "" && src != "" ) {
                val news = NewsModel(title, src, href)
                arr.add(news)
            }
        }
        return arr
    }
}