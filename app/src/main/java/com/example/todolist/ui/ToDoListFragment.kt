package com.example.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapter.TodoAdapter
import com.example.todolist.databinding.FragmentToDoListBinding
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListFragment : Fragment() {
private lateinit var binding :FragmentToDoListBinding
private val viewModel:TodoViewModel by viewModels()
private  lateinit var todoAdapter : TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddTodo.setOnClickListener {
            view.findNavController().navigate(R.id.action_toDoListFragment_to_addToDoFragment)
        }
  binding.toolbar.title="Yapılacaklar Listesi"
        setupRv()
    }
private fun setupRv(){
    todoAdapter = TodoAdapter(viewModel)
    binding.rvListTodo.apply {
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
    setHasFixedSize(true)
    }

    viewModel.getAllToDos.observe(viewLifecycleOwner,{list->
         update(list)
        todoAdapter.differ.submitList(list)

    })

}
    private fun update(list: List<Todo>){
        if(list.isNotEmpty()){
            binding.rvListTodo.visibility =View.VISIBLE
            binding.cardViewTvNoTodo.visibility =View.GONE

        }else{
            binding.rvListTodo.visibility =View.GONE
            binding.cardViewTvNoTodo.visibility =View.VISIBLE
        }
    }
}