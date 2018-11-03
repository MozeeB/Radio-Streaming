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

public class BandungActivity extends AppCompatActivity implements OnClickListener {

    private String url_radio5= "http://125.160.17.86:8022/;";
    private ProgressBar playSeekBar5;

    private TextView tvRadioUrl5;
    private Button buttonPlay5;

    private Button buttonStopPlay5;

    private MediaPlayer player5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandung);

        initializeUIElements();
        initializeMediaPlayer();
    }

    private void initializeUIElements() {

        playSeekBar5 = (ProgressBar) findViewById(R.id.progressBar5);
        playSeekBar5.setMax(100);
        playSeekBar5.setVisibility(View.INVISIBLE);
        playSeekBar5.setIndeterminate(true);
        buttonPlay5 = (Button) findViewById(R.id.buttonPlay5);
        buttonPlay5.setOnClickListener(this);

        buttonStopPlay5 = (Button) findViewById(R.id.buttonStop5);
        buttonStopPlay5.setEnabled(false);
        buttonStopPlay5.setOnClickListener(this);
        tvRadioUrl5 = (TextView) findViewById(R.id.textViewRadioUrl5);
        tvRadioUrl5.setText("Radio url : "+url_radio5);
    }

    public void onClick(View v) {
        if (v == buttonPlay5) {
            startPlaying();
        } else if (v == buttonStopPlay5) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        buttonStopPlay5.setEnabled(true);
        buttonPlay5.setEnabled(false);

        playSeekBar5.setVisibility(View.VISIBLE);

        player5.prepareAsync();

        player5.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {

                player5.start();

            }
        });

    }

    private void stopPlaying() {
        if (player5.isPlaying()) {
            player5.stop();
            player5.release();
            initializeMediaPlayer();
        }

        buttonPlay5.setEnabled(true);
        buttonStopPlay5.setEnabled(false);
        playSeekBar5.setIndeterminate(true);
        playSeekBar5.setVisibility(View.INVISIBLE);

    }

    private void initializeMediaPlayer() {
        player5 = new MediaPlayer();
        try {
            player5.setDataSource(url_radio5);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player5.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playSeekBar5.setIndeterminate(false);
                playSeekBar5.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player5.isPlaying()) {
            //  player.stop();
        }
    }
}