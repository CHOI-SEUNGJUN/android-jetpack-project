package c.june.learning.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import c.june.learning.R
import c.june.learning.adapters.TodoAdapter
import c.june.learning.databinding.FragmentTodoBinding
import c.june.learning.viewmodels.TodoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private val viewModel: TodoViewModel by viewModel()

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

        val todoAdapter = TodoAdapter(
            onUpdate = { viewModel.updateValue(it) }
        )

        binding.rvTodoList.adapter = todoAdapter

        viewModel.setTestValue()
    }
}