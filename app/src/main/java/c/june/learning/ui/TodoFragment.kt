package c.june.learning.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import c.june.learning.R
import c.june.learning.adapters.TodoAdapter
import c.june.learning.databinding.FragmentTodoBinding
import c.june.learning.util.setOnEditCompleteListener
import c.june.learning.viewmodels.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    private var todoJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_todo, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            model = viewModel
            lifecycleOwner = this@TodoFragment
            executePendingBindings()
        }

        initAdapter()
        initTodo()
    }

    private fun initTodo() {
        binding.edtMemo.setOnEditCompleteListener { updateTodoListFromInput() }

        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvTodoList.scrollToPosition(0) }
        }
    }

    private fun initTodoJob() {
        todoJob?.cancel()

        todoJob = lifecycleScope.launch {
            viewModel.getContent().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun updateTodoListFromInput() {
        binding.edtMemo.text.trim().let {
            if (it.isNotEmpty()) {
                viewModel.insertValue(it.toString())
                initTodoJob()
                binding.edtMemo.setText("")
            }
        }
    }

    private fun initAdapter() {
        adapter = TodoAdapter { viewModel.updateValue(it) }
        binding.rvTodoList.adapter = adapter
        initTodoJob()
    }
}