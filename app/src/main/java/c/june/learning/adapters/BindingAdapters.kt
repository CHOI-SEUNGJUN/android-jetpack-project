package c.june.learning.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import c.june.learning.util.RecyclerViewDividerDecoration

@SuppressLint("SetTextI18n")
@BindingAdapter("app:setLanguageText")
fun setLanguageText(textView: TextView, language: String?) {
    if (!language.isNullOrEmpty()) {
        textView.text = "Language: $language"
    }
}

@BindingAdapter(
    value = ["dividerHeight", "dividerPadding", "dividerColor"],
    requireAll = false
)
fun RecyclerView.setDivider(dividerHeight: Float?, dividerPadding: Float?, @ColorInt dividerColor: Int?) {
    val decoration = RecyclerViewDividerDecoration(dividerHeight ?: 0f, dividerPadding ?: 0f, dividerColor ?: Color.TRANSPARENT)
    addItemDecoration(decoration)
}