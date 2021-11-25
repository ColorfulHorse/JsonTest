import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


val gson: Gson = GsonBuilder()
    .registerTypeAdapterFactory(GsonDefaultAdapterFactory())
    .create()

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(MoshiDefaultAdapterFactory.FACTORY)
    .add(MoshiDefaultCollectionJsonAdapter.FACTORY)
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
    println("gson parse json: $p1")
    val p2 = moshi.adapter(DefaultAll::class.java).fromJson(json)
    println("com.squareup.moshi parse json: $p2")
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
    println("gson parse json: $p1")
    val p2 = moshi.adapter(DefaultPart::class.java).fromJson(json)
    println("com.squareup.moshi parse json: $p2")
}


@JsonClass(generateAdapter = true)
data class Person(
    val name: String,
    val friends: List<Person>
)

fun testGsonNullValue() {
    // 这里必须要有age字段，moshi为了保持空安全不允许age为null
    val json = """{"name":null, "friends":null}"""
    val p1 = gson.fromJson(json, Person::class.java)
    println("gson parse json: $p1")
}

fun testMoshiNullValue() {
    // 这里必须要有age字段，moshi为了保持空安全不允许age为null
    val json = """{"name":null, "friends":null}"""
    val p2 = moshi.adapter(Person::class.java).fromJson(json)
    println("com.squareup.moshi parse json: $p2")
}

fun main() {
    testGsonNullValue()
}