import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Author : greensunliao
 * Date   : 2022/3/21
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */

data class Person(@Json(name = "_name")val name: String, val age: Int)

fun main() {
    val moshi: Moshi = Moshi.Builder()
        // KotlinJsonAdapterFactory基于kotlin-reflection反射创建自定义类型的JsonAdapter
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val json = """{"_name": "xxx", "age": 20}"""
    val person = moshi.adapter(Person::class.java).fromJson(json)
    println(person)
}