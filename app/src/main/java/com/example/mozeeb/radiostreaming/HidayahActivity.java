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

public class HidayahActivity extends AppCompatActivity implements OnClickListener {

    private String url_radio2= "http://radio.hidayahfm.com:9988/;stream.mp3";
    private ProgressBar playSeekBar2;

    private TextView tvRadioUrl2;
    private Button buttonPlay2;

    private Button buttonStopPlay2;

    private MediaPlayer player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidayah);

        initializeUIElements();
        initializeMediaPlayer();
    }

    private void initializeUIElements() {

        playSeekBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        playSeekBar2.setMax(100);
        playSeekBar2.setVisibility(View.INVISIBLE);
        playSeekBar2.setIndeterminate(true);
        buttonPlay2 = (Button) findViewById(R.id.buttonPlay2);
        buttonPlay2.setOnClickListener(this);

        buttonStopPlay2 = (Button) findViewById(R.id.buttonStop2);
        buttonStopPlay2.setEnabled(false);
        buttonStopPlay2.setOnClickListener(this);
        tvRadioUrl2 = (TextView) findViewById(R.id.textViewRadioUrl2);
        tvRadioUrl2.setText("Radio url : "+url_radio2);
    }

    public void onClick(View v) {
        if (v == buttonPlay2) {
            startPlaying();
        } else if (v == buttonStopPlay2) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        buttonStopPlay2.setEnabled(true);
        buttonPlay2.setEnabled(false);

        playSeekBar2.setVisibility(View.VISIBLE);

        player2.prepareAsync();

        player2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {

                player2.start();

            }
        });

    }

    private void stopPlaying() {
        if (player2.isPlaying()) {
            player2.stop();
            player2.release();
            initializeMediaPlayer();
        }

        buttonPlay2.setEnabled(true);
        buttonStopPlay2.setEnabled(false);
        playSeekBar2.setIndeterminate(true);
        playSeekBar2.setVisibility(View.INVISIBLE);

    }

    private void initializeMediaPlayer() {
        player2 = new MediaPlayer();
        try {
            player2.setDataSource(url_radio2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player2.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playSeekBar2.setIndeterminate(false);
                playSeekBar2.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player2.isPlaying()) {
            //  player.stop();
        }
    }
}