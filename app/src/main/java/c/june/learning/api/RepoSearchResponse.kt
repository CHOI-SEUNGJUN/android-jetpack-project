package c.june.learning.api

import c.june.learning.data.Repo
import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @field:SerializedName("total_count") val total: Int = 0,
    @field:SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)