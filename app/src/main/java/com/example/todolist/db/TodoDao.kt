package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist.model.Todo
import kotlinx.coroutines.flow.Flow
import androidx.room.Delete




@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserTodo(todo : Todo)

    @Delete
   suspend fun deletetodo(todo: Todo)
           //Flow  eşzamansız olarak hesaplanabilen bir veri akışıdır .
    // Yayılan değerler aynı türden olmalıdır. Örneğin a Flow<Int>, tamsayı değerleri yayan bir akıştır.
    @Query("SELECT * FROM todo ORDER BY todoTitle ASC")
    fun  getAllToDos():Flow<List<Todo>>
}