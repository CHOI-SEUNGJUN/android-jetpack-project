package c.june.learning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import c.june.learning.R
import c.june.learning.data.model.Repo
import c.june.learning.databinding.ItemGithubRepoBinding

class GithubAdapter: PagingDataAdapter<Repo, GithubAdapter.GithubViewHolder>(GITHUB_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder
        = GithubViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_github_repo, parent, false))

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class GithubViewHolder(private val binding: ItemGithubRepoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repo) {
            binding.model = item
            binding.executePendingBindings()
        }
    }

    companion object {
        private val GITHUB_DIFF = object: DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }
}