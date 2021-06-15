package c.june.learning.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import c.june.learning.data.Todo

@BindingAdapter("app:todoItems")
fun setTodoItems(recyclerView: RecyclerView, items: List<Todo>?) {
    with(recyclerView.adapter as? TodoAdapter) {
        items?.let { this?.submitList(items) }
    }
}