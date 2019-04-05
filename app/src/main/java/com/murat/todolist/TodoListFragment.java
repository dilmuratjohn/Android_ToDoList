package com.murat.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TodoListFragment extends Fragment {
    private final String Tag = "TodoList";

    private RecyclerView mTodoRecyclerView;
    private TodoAdapter mTodoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Tag, "TodoFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        mTodoRecyclerView = view.findViewById(R.id.todo_recycler_view);
        mTodoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        TodoLab todoLab = TodoLab.get(getActivity());
        List<Todo> todoList = todoLab.getTodoList();
        if(mTodoAdapter ==null ) {
            mTodoAdapter = new TodoAdapter(todoList);
            mTodoRecyclerView.setAdapter(mTodoAdapter);
        }else {
            mTodoAdapter.notifyDataSetChanged();
        }
    }

    private class TodoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Todo mTodo;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mFinishedImageView;

        public TodoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_todo, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.todo_title);
            mDateTextView = itemView.findViewById(R.id.todo_date);
            mFinishedImageView = itemView.findViewById(R.id.todo_finished);
        }

        public void bind(Todo todo) {
            mTodo = todo;
            mTitleTextView.setText(mTodo.getTitle());
            mDateTextView.setText(mTodo.getDate().toString());
            mFinishedImageView.setVisibility(todo.isFinished() ? View.VISIBLE : View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mTodo.getTitle() + "clicked!", Toast.LENGTH_SHORT).show();
//            Intent intent = new TodoActivity().newIntent(getActivity(), mTodo.getId());
            Intent intent = new TodoPagerActivity().newIntent(getActivity(), mTodo.getId());
            startActivity(intent);

        }
    }

    private class TodoAdapter extends RecyclerView.Adapter<TodoHolder> {
        private List<Todo> mTodoList;

        private TodoAdapter(List<Todo> todoList) {
            mTodoList = todoList;
        }

        @NonNull
        @Override
        public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TodoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
            Todo todo = mTodoList.get(position);
            holder.bind(todo);
        }

        @Override
        public int getItemCount() {
            return mTodoList.size();
        }
    }

}
