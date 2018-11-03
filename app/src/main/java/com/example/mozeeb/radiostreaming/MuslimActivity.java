package com.example.mozeeb.radiostreaming;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MuslimActivity extends AppCompatActivity implements OnClickListener {

    private String url_radio3= "http://128.199.156.6/;stream/1";
    private ProgressBar playSeekBar3;

    private TextView tvRadioUrl3;
    private Button buttonPlay3;

    private Button buttonStopPlay3;

    private MediaPlayer player3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muslim);

        initializeUIElements();
        initializeMediaPlayer();
    }

    private void initializeUIElements() {

        playSeekBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        playSeekBar3.setMax(100);
        playSeekBar3.setVisibility(View.INVISIBLE);
        playSeekBar3.setIndeterminate(true);
        buttonPlay3 = (Button) findViewById(R.id.buttonPlay3);
        buttonPlay3.setOnClickListener(this);

        buttonStopPlay3 = (Button) findViewById(R.id.buttonStop3);
        buttonStopPlay3.setEnabled(false);
        buttonStopPlay3.setOnClickListener(this);
        tvRadioUrl3 = (TextView) findViewById(R.id.textViewRadioUrl3);
        tvRadioUrl3.setText("Radio url : "+url_radio3);
    }

    public void onClick(View v) {
        if (v == buttonPlay3) {
            startPlaying();
        } else if (v == buttonStopPlay3) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        buttonStopPlay3.setEnabled(true);
        buttonPlay3.setEnabled(false);

        playSeekBar3.setVisibility(View.VISIBLE);

        player3.prepareAsync();

        player3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {

                player3.start();

            }
        });

    }

    private void stopPlaying() {
        if (player3.isPlaying()) {
            player3.stop();
            player3.release();
            initializeMediaPlayer();
        }

        buttonPlay3.setEnabled(true);
        buttonStopPlay3.setEnabled(false);
        playSeekBar3.setIndeterminate(true);
        playSeekBar3.setVisibility(View.INVISIBLE);

    }

    private void initializeMediaPlayer() {
        player3 = new MediaPlayer();
        try {
            player3.setDataSource(url_radio3);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player3.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playSeekBar3.setIndeterminate(false);
                playSeekBar3.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player3.isPlaying()) {
            //  player.stop();
        }
    }
}