package c.june.learning.adapters

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import c.june.learning.data.Todo

@BindingAdapter("app:todoItems")
fun setTodoItems(recyclerView: RecyclerView, items: List<Todo>?) {
    with(recyclerView.adapter as? TodoAdapter) {
        items?.let { this?.submitList(items) }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("app:setLanguageText")
fun setLanguageText(textView: TextView, language: String?) {
    if (!language.isNullOrEmpty()) {
        textView.text = "Language: $language"
    }
}