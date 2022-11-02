import basic.Article
import com.squareup.moshi.*
import transform.DateTextAdapter
import transform.HexColorAdapter
import transform.IllegalTextAdapter

/**
 * Author : greensunliao
 * Date   : 2022/3/10
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */

val moshi: Moshi by lazy {
    Moshi.Builder()
        .add(IllegalTextAdapter())
        .add(HexColorAdapter())
        .add(DateTextAdapter())
//        .addAdapter(object : JsonAdapter<Article>() {
//            override fun fromJson(reader: JsonReader): Article? {
//
//            }
//
//            override fun toJson(writer: JsonWriter, value: Article?) {
//
//            }
//
//        })
        .build()
}

inline fun <reified T> T.toJson(): String = moshi.adapter(T::class.java)
    .toJson(this)

inline fun <reified T> String.toBean() = moshi.adapter(T::class.java)
    .fromJson(this)


fun printlnR(content: Any?) {
    println("\u001b[1;31m$content")
}