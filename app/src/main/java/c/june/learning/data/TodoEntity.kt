package c.june.learning.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import c.june.learning.util.getNowDateTimeToString

@Entity(tableName = "TODO")
data class Todo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "t_id") val tId: Int = 0,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "is_checked") var isChecked: Boolean = false,
    @ColumnInfo(name = "created_at") val createdAt: String = getNowDateTimeToString()
)