package c.june.learning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import c.june.learning.R
import c.june.learning.data.Todo
import c.june.learning.databinding.ItemTodoBinding

class TodoAdapter(private val onUpdate: (Todo) -> (Unit)):
    ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallBack()) {

    inner class TodoViewHolder(
        private val binding: ItemTodoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Todo) {
            with(binding) {
                entity = item
                executePendingBindings()
            }

            binding.checkbox.setOnCheckedChangeListener { _, b ->
                onUpdate(item.copy(isChecked = b))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class TodoDiffCallBack: DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.tId == newItem.tId
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}