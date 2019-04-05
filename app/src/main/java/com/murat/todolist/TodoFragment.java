package com.murat.todolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class TodoFragment extends Fragment {
    private final String Tag = "TodoList";
    private static final String ARG_TODO_ID = "todo_id";

    private Todo mTodo;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mFinishedCheckBox;

    public static TodoFragment newInstance(UUID todoId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TODO_ID, todoId);

        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag, "TodoFragment onCreate");
        UUID todoId = (UUID) getArguments().getSerializable(ARG_TODO_ID);
        mTodo = TodoLab.get(getActivity()).getTodo(todoId);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Tag, "TodoFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Tag, "TodoFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "TodoFragment onDestroy");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Tag, "TodoFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        mTitleField = view.findViewById(R.id.todo_title);
        mTitleField.setText(mTodo.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTodo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = view.findViewById(R.id.todo_date);
        mDateButton.setText(mTodo.getDate());
        mDateButton.setEnabled(false);

        mFinishedCheckBox = view.findViewById(R.id.todo_finished);
        mFinishedCheckBox.setChecked(mTodo.isFinished());
        mFinishedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTodo.setFinished(isChecked);
            }
        });

        return view;
    }

}
