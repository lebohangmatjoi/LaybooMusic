package com.example.admin.layboomusic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;
    private ImageButton play;
    private ImageButton pause;
     private ImageButton next;
     ImageButton previous;
    int previousBtn;
    int nextBtn;
    Music music;



    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ImageButton play = (ImageButton)findViewById(R.id.play);
        play.setVisibility(View.INVISIBLE);
        final ImageButton pause = (ImageButton)findViewById(R.id.pause);
        pause.setVisibility(View.INVISIBLE);

        final ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);
        final ImageButton previous = (ImageButton)findViewById(R.id.previous);
        previous.setVisibility(View.INVISIBLE);





        // Create a list of words
        final ArrayList<Music> songs = new ArrayList<Music>();
        songs.add(new Music("Amanda Black","Amazulu",R.raw.amazulu));
        songs.add(new Music("Davido","If",R.raw.davidoif));
        songs.add(new Music("Joyous Celebration","Jesu rato lahao",R.raw.jesuratolahao));
        songs.add(new Music("Lejwe la motheo","Ke moeti",R.raw.moeti));
        songs.add(new Music("Rebecca Malope","Nkarabe",R.raw.nkarabe));
        songs.add(new Music("Spirit Of Praise","Nkateko",R.raw.nkateko));
        songs.add(new Music("Lebo Sekgobela","Theko ya lona",R.raw.theko));
        songs.add(new Music("Thina Zungu","Jesu ufika ekuseni",R.raw.jesufikekusen));
        songs.add(new Music("AKA","The world is yours",R.raw.theworldisyours));
        songs.add(new Music("Dj Clock","Sabakufa",R.raw.sabakufa));


        songs.add(new Music("","",R.raw.sabakufa));
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_song);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music song = songs.get(position);
                previousBtn=position;
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(SongsActivity.this, song.getmAudioResourceId());
                    mMediaPlayer.start();
                    play.setVisibility(View.VISIBLE);
                    pause.setVisibility(View.VISIBLE);
                    previous.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);


                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Start the audio file
                mMediaPlayer.start();

            }
        });



        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Start the audio file
                mMediaPlayer.pause();
            }

        });


      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


              if(mMediaPlayer !=null && mMediaPlayer.isPlaying()){
                  mMediaPlayer.stop();
              }
              Music song = songs.get(nextBtn++);
              mMediaPlayer = mMediaPlayer.create(SongsActivity.this,song.getmAudioResourceId());

              mMediaPlayer.start();
          }
      });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMediaPlayer !=null && mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }

                Music song = songs.get(previousBtn--);
                mMediaPlayer = mMediaPlayer.create(SongsActivity.this,song.getmAudioResourceId());

                mMediaPlayer.start();
            }
        });





    }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
