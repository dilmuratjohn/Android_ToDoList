package com.murat.todolist;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Todo {

    private UUID mId;
    private String mTitle;
    private String mDate;
    private boolean mFinished;

    public Todo() {
        mId = UUID.randomUUID();
        mDate = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + Calendar.getInstance().get(Calendar.DATE) + ", " + Calendar.getInstance().get(Calendar.YEAR);
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public boolean isFinished() {
        return mFinished;
    }

    public void setFinished(boolean mFinished) {
        this.mFinished = mFinished;
    }
}
