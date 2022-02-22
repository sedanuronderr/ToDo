package com.example.todolist.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoCardBinding
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel

class TodoAdapter(  private val viewmodel:TodoViewModel):RecyclerView.Adapter<TodoAdapter.TodoView>() {

    inner class TodoView(val binding:TodoCardBinding):RecyclerView.ViewHolder(binding.root){

    }

    private val differCallback = object : DiffUtil.ItemCallback<Todo>() {

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

     val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoView {
    return  TodoView(TodoCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoView, position: Int) {
      val todo = differ.currentList[position]

        holder.binding.apply {
            textView2.text= todo.todoTitle
        }
        holder.binding.deleteItem.apply {
            setOnClickListener {
                holder.binding.apply {
                   viewmodel.deleteTodo(todo)
                }
            }
        }

        holder.binding.checkBox.apply {
            setOnClickListener {
                holder.binding.apply {
                    if(isChecked){
                      textView2.paintFlags= textView2.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    }else{
                        textView2.paintFlags= textView2.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}