package com.example.todolist.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.FragmentAddToDoBinding
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : Fragment() {

    private lateinit var binding : FragmentAddToDoBinding

    private val viewmodel:TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = FragmentAddToDoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            view.findNavController().navigate(R.id.action_addToDoFragment_to_toDoListFragment)

        }

        binding.btnAddTodo.setOnClickListener { view->
            saveTodo(view)
        }

    }
    private fun saveTodo(mView:View){


        val todoName =  binding.etTodoTitle.text.toString()
        if(todoName.isNotEmpty()){
val todo = Todo(0,todoName)
            viewmodel.insertTodo(todo)
            Snackbar.make(
                mView,"Todo added",
                Snackbar.LENGTH_SHORT
            ).show()
mView.findNavController().navigate(R.id.action_addToDoFragment_to_toDoListFragment)
        }else{
          val toast=  Toast.makeText(activity,"Todo title cannot be empty",Toast.LENGTH_SHORT)
               toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }
    }


}