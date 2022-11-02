package transform

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

/**
 * Author : greensunliao
 * Date   : 2022/3/10
 * Email  : liao962381394@sina.cn
 * Desc   :
 */

class IllegalTextAdapter {

    companion object {
        val array = arrayOf(
            "屏蔽词1",
            "屏蔽词2"
        )
    }

    @ToJson
    fun toJson(@IllegalFilter text: String): String {
        // 什么都不做
        return text
    }

    @FromJson
    @IllegalFilter
    fun fromJson(text: String): String {
        // 屏蔽词替换为***
        var res = text
        array.forEach {
            res = res.replace(it, "***")
        }
        return res
    }
}