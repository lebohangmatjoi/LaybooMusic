package com.example.admin.layboomusic;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 7/7/2017.
 */

public class SongAdapter extends ArrayAdapter<Music> {


    private  int mColorResourceId;

    /**
     * Create a new {@link SongAdapter} object.
     *  @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words is the list of {@link Music}s to be displayed.
     * @param colorResourceId
     */
    public SongAdapter(Context context, ArrayList<Music> words, int colorResourceId) {
        super(context, 0, words);

        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Music currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        miwokTextView.setText(currentWord.getmArtistName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        defaultTextView.setText(currentWord.getmSongTitle());
//        // Find the ImageView in the list_item.xml layout with the ID image.
//        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
//        // Check if an image is provided for this word or not
//        if (currentWord.hasImage()) {
//            // If an image is available, display the provided image based on the resource ID
//            imageView.setImageResource(currentWord.getImageResourceId());
//            // Make sure the view is visible
//            imageView.setVisibility(View.VISIBLE);
//        } else {
//            // Otherwise hide the ImageView (set visibility to GONE)
//            imageView.setVisibility(View.GONE);
//        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}