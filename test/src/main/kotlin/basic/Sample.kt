package basic

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.DefaultIfNullFactory
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi


val gson: Gson = GsonBuilder()
//    .registerTypeAdapterFactory(default.GsonDefaultAdapterFactory())
    .create()


val moshi: Moshi = Moshi.Builder()
    // KotlinJsonAdapterFactory基于kotlin-reflection反射创建自定义类型的JsonAdapter
//    .addLast(KotlinJsonAdapterFactory())
//    .add(MoshiDefaultAdapterFactory.FACTORY)
//    .add(MoshiDefaultCollectionJsonAdapter.FACTORY)
    .add(DefaultIfNullFactory())
    .build()


@JsonClass(generateAdapter = true)
data class DefaultAll(
    val name: String = "me",
    val age: Int = 17
)

/**
 * 所有字段都有默认值的情况
 */
fun testDefaultAll() {
    val json = """{}"""
    val p1 = gson.fromJson(json, DefaultAll::class.java)
    println("Gson parse json: $p1")
    val p2 = moshi.adapter(DefaultAll::class.java).fromJson(json)
    println("Moshi parse json: $p2")
}


@JsonClass(generateAdapter = true)
data class DefaultPart(
    val name: String = "me",
    val gender: String = "male",
    val age: Int
)

/**
 * 部分字段都有默认值的情况
 */
fun testDefaultPart() {
    // 这里必须要有age字段，moshi为了保持空安全不允许age为null
    val json = """{"age": 17}"""
    val p1 = gson.fromJson(json, DefaultPart::class.java)
    println("Gson parse json: $p1")
    val p2 = moshi.adapter(DefaultPart::class.java).fromJson(json)
    println("Moshi parse json: $p2")
}


/**
 * 内置类型为空
 */
@JsonClass(generateAdapter = true)
data class Person(
    val name: String,
    val friends: List<Person>
)

fun testGsonBuildInNullValue() {
    val json = """{"name":null, "friends":null}"""
    val p1 = gson.fromJson(json, Person::class.java)
    println("Gson parse json: $p1")
}

fun testMoshiBuildInNullValue() {
    val json = """{"name":null, "friends":null}"""
    val p2 = moshi.adapter(Person::class.java).fromJson(json)
    println("Moshi parse json: $p2")
}



/**
 * 自定义类型为空
 */
@JsonClass(generateAdapter = true)
data class Team(
    val name: String? = "default",
    val leader: Person = Person("default", emptyList())
)

fun testGsonCustomNullValue() {
    val json = """{"name":null, "leader":null}"""
    val p1 = gson.fromJson(json, Team::class.java)
    // gson没有空安全检查，编译器都无法推断
    if (p1.name == null) {
        println("Gson parse json p1 name was null")
    }
    println("Gson parse json: $p1")
}

fun testMoshiCustomNullValue() {
    val json = """{"name":null, "leader":null}"""
    val p2 = moshi.adapter(Team::class.java).fromJson(json)
    println("Moshi parse json: $p2")
}

fun main() {
    testMoshiCustomNullValue()
}