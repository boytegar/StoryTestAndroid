package id.boytegar.storytest.model
import com.google.gson.annotations.SerializedName


data class DetailStory(
    @SerializedName("by")
    var by: String?,
    @SerializedName("descendants")
    var descendants: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("kids")
    var kids: List<Int?>?,
    @SerializedName("score")
    var score: Int?,
    @SerializedName("time")
    var time: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("text")
    var text: String?
)