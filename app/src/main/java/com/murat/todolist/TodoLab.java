package com.murat.todolist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoLab {
    private static TodoLab sTodoLab;

    public static TodoLab get(Context context) {
        if (sTodoLab == null) {
            sTodoLab = new TodoLab(context);
        }
        return sTodoLab;
    }

    private TodoLab(Context context) {
        mTodoList = new ArrayList<>();
        for(int i = 0; i < 200; i++){
            Todo todo = new Todo();
            todo.setTitle("Todo #" + i);
            todo.setFinished(i% 5 == 0);
            mTodoList.add(todo);
        }
    }

    private List<Todo> mTodoList;

    public Todo getTodo(UUID id) {
        for (Todo todo : mTodoList)
            if (todo.getId().equals(id))
                return todo;
        return null;
    }

    public List<Todo> getTodoList(){
        return mTodoList;
    }

}
