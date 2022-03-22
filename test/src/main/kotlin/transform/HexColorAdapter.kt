package transform

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.awt.Color

/**
 * Author : greensunliao
 * Date   : 2022/3/10
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */

/**
 *  rgb 16进制int颜色和string颜色互转
 *  #000000 = 0
 */
class HexColorAdapter {

    @ToJson
    fun toJson(@HexColor color: Int): String {
        return String.format("#%06x", color)
    }

    @FromJson
    @HexColor
    fun fromJson(color: String): Int {
        return Integer.parseInt(color.substring(1), 16)
    }
}