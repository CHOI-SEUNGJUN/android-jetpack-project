package c.june.learning.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.june.learning.databinding.FragmentTodoBinding
import c.june.learning.viewmodels.TodoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private val model: TodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.setTestValue()

        model.contents.observe(viewLifecycleOwner) {
            Log.e("todoFragment", it.toString())
        }
    }
}