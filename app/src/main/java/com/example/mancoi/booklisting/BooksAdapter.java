package com.example.mancoi.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mancoi on 03/08/2017.
 */

public class BooksAdapter extends ArrayAdapter<Books> {


    public BooksAdapter(@NonNull Context context, ArrayList<Books> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        //Get current Books item
        Books currentBook = getItem(position);

        //Find the TextView with id title_tv and set the title for it
        TextView title = (TextView) listItemView.findViewById(R.id.title_tv);
        title.setText(currentBook.getmTitle());

        //Find the TextView with id author_tv and set the author for it
        TextView author = (TextView) listItemView.findViewById(R.id.author_tv);
        author.setText(currentBook.getmAuthor());

        return listItemView;
    }

}
