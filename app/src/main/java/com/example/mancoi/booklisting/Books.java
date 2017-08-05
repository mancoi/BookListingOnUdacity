package com.example.mancoi.booklisting;

/**
 * Created by mancoi on 03/08/2017.
 */

public class Books {

    private String mTitle;

    private String mAuthor;

    public Books(String title, String author) {
        mAuthor = author;
        mTitle = title;
    }

    public String getmAuthor()
    {
        return mAuthor;
    }

    public String getmTitle()
    {
        return mTitle;
    }
}
