package com.example.todolist.repository

import com.example.todolist.db.TodoDao
import com.example.todolist.model.Todo
import javax.inject.Inject

class TodoRepository
@Inject
constructor(private  val dao: TodoDao){

    suspend fun insertTodo(todo:Todo)= dao.inserTodo(todo)

    suspend fun delete(todo: Todo)=dao.deletetodo(todo)
    fun getAllToDos()= dao.getAllToDos()

}