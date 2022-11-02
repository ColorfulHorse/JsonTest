package basic

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.Feature
import com.alibaba.fastjson.parser.ParserConfig
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.DefaultIfNullFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test
import printlnR
import toBean
import toJson
import kotlin.system.measureTimeMillis

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
//        .registerTypeAdapter()
        .create()


    val moshi: Moshi = Moshi.Builder()
        // KotlinJsonAdapterFactory基于kotlin-reflection反射创建自定义类型的JsonAdapter
        .addLast(KotlinJsonAdapterFactory())
//        .add(MoshiDefaultAdapterFactory.FACTORY)
//        .add(MoshiDefaultCollectionJsonAdapter.FACTORY)
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
        val p3 = JSON.parseObject(json, DefaultAll::class.java)
        printlnR("fastjson parse json: $p3")

//        val j1 = gson.toJson(DefaultAll())
//        printlnR("gson to json $j1")
//        val j2 = moshi.adapter(DefaultAll::class.java).toJson(DefaultAll())
//        printlnR("moshi to json $j2")
//        val j3 = JSON.toJSON(DefaultAll())
//        printlnR("fastjson to json $j3")
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
        val p3 = JSON.parseObject(json, DefaultPart::class.java)
        printlnR("fastjson parse json: $p3")
    }



    /**
     * 内置类型为空
     */
    @Test
    fun testBuildInNullValue() {
        val json = """{"name":null, "friends":null}"""
//        val p1 = gson.fromJson(json, People::class.java)
//        printlnR("Gson parse json: $p1")
//        p1.name.trim()

        val p3 = JSON.parseObject(json, People::class.java)
        printlnR("fastjson parse json: $p3")

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
    fun testCustomNullValue() {
        val json = """{"name":null, "leader":null}"""
        val p1 = gson.fromJson(json, Team::class.java)
        // gson没有空安全检查，编译器都无法推断
        if (p1.leader == null) {
            printlnR("Gson parse json p1 leader was null")
        }
        printlnR("Gson parse json: $p1")


        val p2 = case3Moshi.adapter(Team::class.java).fromJson(json)
        printlnR("Moshi parse json: $p2")


        val p3 = JSON.parseObject(json, Team::class.java)
        printlnR("fastjson parse json: $p3")
    }


    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testSpeedMoshi() {
        val list = buildList {
            repeat(100000) {
                add(Speed(
                    Math.random().toInt(),
                    (Math.random() * 21).toString(),
                    false,
                    (Math.random() * 33).toInt().toShort(),
                    (Math.random() * 41).toFloat()
                ).toJson())
            }
        }
        val adapter = moshi.adapter(Speed::class.java)
        repeat(10) {
            val time = measureTimeMillis {
                list.forEach {
                    val obj = adapter.fromJson(it)
                }
            }
            printlnR(time)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testSpeedGson() {
        val list = buildList {
            repeat(100000) {
                add(Speed(
                    Math.random().toInt(),
                    (Math.random() * 21).toString(),
                    false,
                    (Math.random() * 33).toInt().toShort(),
                    (Math.random() * 41).toFloat()
                ).toJson())
            }
        }
        repeat(10) {
            val time = measureTimeMillis {
                list.forEach {
                    val obj = gson.fromJson(it, Speed::class.java)
                }
            }
            printlnR(time)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testSpeedFast() {
        val list = buildList {
            repeat(100000) {
                add(Speed(
                    Math.random().toInt(),
                    (Math.random() * 21).toString(),
                    false,
                    (Math.random() * 33).toInt().toShort(),
                    (Math.random() * 41).toFloat()
                ).toJson())
            }
        }
        repeat(10) {
            val time = measureTimeMillis {
                list.forEach {
                    val obj = JSON.parseObject(it, Speed::class.java)
                }
            }
            printlnR(time)
        }
    }
}
