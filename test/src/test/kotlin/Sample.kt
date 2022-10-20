package basic

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.DefaultIfNullFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test
import printlnR
import toBean

class Sample {

    @Test
    fun testBasic() {
        val moshi: Moshi = Moshi.Builder()
            // KotlinJsonAdapterFactory基于kotlin-reflection反射创建自定义类型的JsonAdapter
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val json = """{"_name": "xxx", "age": 20}"""
        val person = moshi.adapter(Person::class.java).fromJson(json)
        printlnR(person)
    }


    @Test
    fun testQualifier() {
        val json = """{"content": "屏蔽词1，屏蔽词2", "time": 1647846873, "textColor": "#ffffff"}"""
        val article = json.toBean<Article>()
        printlnR("article: $article")
    }




    val gson: Gson = GsonBuilder()
        .registerTypeAdapterFactory(GsonDefaultAdapterFactory())
        .create()


    val moshi: Moshi = Moshi.Builder()
        // KotlinJsonAdapterFactory基于kotlin-reflection反射创建自定义类型的JsonAdapter
        .addLast(KotlinJsonAdapterFactory())
        .add(MoshiDefaultAdapterFactory.FACTORY)
        .add(MoshiDefaultCollectionJsonAdapter.FACTORY)
        .build()

    /**
     * 所有字段都有默认值的情况
     */
    @Test
    fun testDefaultAll() {
        val json = """{}"""
        val p1 = gson.fromJson(json, DefaultAll::class.java)
        printlnR("Gson parse json: $p1")
        val p2 = moshi.adapter(DefaultAll::class.java).fromJson(json)
        printlnR("Moshi parse json: $p2")
    }

    /**
     * 部分字段都有默认值的情况
     */
    @Test
    fun testDefaultPart() {
        // 这里必须要有age字段，moshi为了保持空安全不允许age为null
        val json = """{"age": 17}"""
        val p1 = gson.fromJson(json, DefaultPart::class.java)
        printlnR("Gson parse json: $p1")
        val p2 = moshi.adapter(DefaultPart::class.java).fromJson(json)
        printlnR("Moshi parse json: $p2")
    }



    /**
     * 内置类型为空
     */
    @Test
    fun testGsonBuildInNullValue() {
        val json = """{"name":null, "friends":null}"""
        val p1 = gson.fromJson(json, People::class.java)
        printlnR("Gson parse json: $p1")
    }


    @Test
    fun testMoshiBuildInNullValue() {
        val json = """{"name":null, "friends":null}"""
        val p2 = moshi.adapter(People::class.java).fromJson(json)
        printlnR("Moshi parse json: $p2")
    }

    val case3Moshi: Moshi = Moshi.Builder()
        .add(DefaultIfNullFactory())
        .build()

    /**
     * 自定义类型为空
     */
    @Test
    fun testGsonCustomNullValue() {
        val json = """{"name":null, "leader":null}"""
        val p1 = gson.fromJson(json, Team::class.java)
        // gson没有空安全检查，编译器都无法推断
        if (p1.leader == null) {
            printlnR("Gson parse json p1 leader was null")
        }
        printlnR("Gson parse json: $p1")
    }

    @Test
    fun testMoshiCustomNullValue() {
        val json = """{"name":null, "leader":null}"""
        val p2 = case3Moshi.adapter(Team::class.java).fromJson(json)
        printlnR("Moshi parse json: $p2")
    }
}
