package basic

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import transform.DateText
import transform.HexColor
import transform.IllegalFilter

/**
 * Author : greensunliao
 * Date   : 2022/3/28
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */

/**
 * 内置类型为空
 */
@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "_name")
    val name: String,
    val age: Int
)


@JsonClass(generateAdapter = true)
data class DefaultAll(
    val name: String = "me",
    val age: Int = 25
)


@JsonClass(generateAdapter = true)
data class DefaultPart(
    val name: String = "me",
    val gender: String = "male",
    val age: Int
)




@JsonClass(generateAdapter = true)
data class People(
    val name: String,
    val friends: List<People>
)


@JsonClass(generateAdapter = true)
data class Team(
    val name: String? = "default",
    val leader: People = People("default", emptyList())
)


@JsonClass(generateAdapter = true)
data class Article(@IllegalFilter val content: String, @DateText val time: String, @HexColor val textColor: Int)