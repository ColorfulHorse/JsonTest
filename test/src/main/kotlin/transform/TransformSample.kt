package transform

import com.squareup.moshi.JsonClass
import toBean

/**
 * Author : greensunliao
 * Date   : 2022/3/10
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */

@JsonClass(generateAdapter = true)
data class Article(@IllegalFilter val content: String, @DateText val time: String, @HexColor val textColor: Int)

fun main() {
    val json = """{"content": "屏蔽词1，屏蔽词2", "time": 1647846873, "textColor": "#ffffff"}"""
    val article = json.toBean<Article>()
    println("article: $article")
}