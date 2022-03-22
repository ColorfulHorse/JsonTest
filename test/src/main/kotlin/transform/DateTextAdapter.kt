package transform

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author : greensunliao
 * Date   : 2022/3/10
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   : 时间戳字符串互转
 */

class DateTextAdapter {

    companion object {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    }

    @ToJson
    fun toJson(@DateText text: String): Long {
        return sdf.parse(text).time
    }

    @FromJson
    @DateText
    fun fromJson(timestamp: Long): String {
        return sdf.format(timestamp)
    }
}