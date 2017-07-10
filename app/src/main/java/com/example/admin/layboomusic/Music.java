package com.example.admin.layboomusic;

/**
 * Created by Admin on 7/7/2017.
 */

public class Music {

    /** Default translation for the word */
    private String mSongTitle;

    /** Miwok translation for the word */
    private String mArtistName;

    /** Audio resource ID for the word*/
    private int mAudioResourceId;

    /** Image resource ID for the word */
//    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
//    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     *
     * @param songTitle is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param artistName is the word in the Miwok language
     */
    public Music(String songTitle, String artistName, int audioResourceId) {
        mArtistName = artistName;
        mSongTitle = songTitle;
        mAudioResourceId = audioResourceId;
    }


    /**
     * Get the default translation of the word.
     */
    public String getmSongTitle()
    {
        return mSongTitle;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getmArtistName() {
        return mArtistName;
    }

    /**
     * Return the image resource ID of the word.
     */


    public int getmAudioResourceId() {
        return mAudioResourceId;
    }



}
