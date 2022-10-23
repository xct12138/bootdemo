package com.xct.bootdemo.util

import com.xct.bootdemo.bean.Cartoon
import com.xct.bootdemo.bean.CartoonNewChapter
import com.xct.bootdemo.service.CartoonService
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URL

object CtData {
    val Log: Logger = LoggerFactory.getLogger(CtData::class.java)
}
fun insert(start:Int,ctService: CartoonService){
    var index = start
    while (index<2028){
        try {
            val url = "https://www.qqmanhua.top/manhua/$index.html"
            val document = getDocument(url = URL(url), 10)
            val cartoon = Cartoon()
            cartoon.cid = index
            val newChapters = ArrayList<CartoonNewChapter>()

            val info = document?.getElementsByClass("info")?.first()!!
            val nameTag = info.getElementsByTag("h2").first()!!
            cartoon.ctname = nameTag.text()
            val intr = document.getElementsByClass("cdes1").first()!!
            cartoon.introduction = intr.text()

            val chapters = document.getElementById("detail-list-select")?.getElementsByTag("a")
            var href = chapters?.first()?.attr("href")!!
            val i:Int = href.substring(href.lastIndexOf('/')+1).toInt()
            cartoon.firstchapter = i
            cartoon.chaptercount = 0
            chapters.forEachIndexed { elIndex, element ->
                href = element.attr("href")
                val current = href.substring(href.lastIndexOf('/') + 1).toInt()
                if (current-i != elIndex){
                    newChapters.add(CartoonNewChapter(current,index))
                }else cartoon.chaptercount++
            }
            if (!cartoon.isEmpty()){
                ctService.add(cartoon,newChapters)
            }
        }catch (e:Exception){
            CtData.Log.error("解析错误：",e)
        }
        index++
    }
}

fun getDocument(url: URL,maxRequest:Int):Document?{
    var document:Document
    for (i in 1..maxRequest){
        try {
            document = Jsoup.parse(url,5000)
            return document
        }catch (e:IOException){
            CtData.Log.error(url.path+"\t\t请求失败")
        }
    }
    return null
}