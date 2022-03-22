package transform

import com.squareup.moshi.JsonQualifier

/**
 * Author : greensunliao
 * Date   : 2022/3/10
 * Email  : liao962381394@sina.cn
 * Blog   : https://juejin.cn/user/3263006244363095
 * Desc   :
 */

/**
 * 屏蔽词注解
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class IllegalFilter

/**
 * 时间戳转日期字符串
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class DateText

/**
 * 颜色值互转注解
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class HexColor
