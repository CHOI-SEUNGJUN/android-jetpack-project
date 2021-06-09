package c.june.learning.util

import java.text.SimpleDateFormat
import java.util.*

val BASE_DATE_FORMAT = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA)

fun getNowDateTimeToString(): String = BASE_DATE_FORMAT.format(Date())