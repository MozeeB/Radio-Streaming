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

public class CirebonActivity extends AppCompatActivity implements OnClickListener {

    private String url_radio4= "http://live.radiosunnah.net/;";
    private ProgressBar playSeekBar4;

    private TextView tvRadioUrl4;
    private Button buttonPlay4;

    private Button buttonStopPlay4;

    private MediaPlayer player4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cirebon);

        initializeUIElements();
        initializeMediaPlayer();
    }

    private void initializeUIElements() {

        playSeekBar4 = (ProgressBar) findViewById(R.id.progressBar4);
        playSeekBar4.setMax(100);
        playSeekBar4.setVisibility(View.INVISIBLE);
        playSeekBar4.setIndeterminate(true);
        buttonPlay4 = (Button) findViewById(R.id.buttonPlay4);
        buttonPlay4.setOnClickListener(this);

        buttonStopPlay4 = (Button) findViewById(R.id.buttonStop4);
        buttonStopPlay4.setEnabled(false);
        buttonStopPlay4.setOnClickListener(this);
        tvRadioUrl4 = (TextView) findViewById(R.id.textViewRadioUrl4);
        tvRadioUrl4.setText("Radio url : "+url_radio4);
    }

    public void onClick(View v) {
        if (v == buttonPlay4) {
            startPlaying();
        } else if (v == buttonStopPlay4) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        buttonStopPlay4.setEnabled(true);
        buttonPlay4.setEnabled(false);

        playSeekBar4.setVisibility(View.VISIBLE);

        player4.prepareAsync();

        player4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {

                player4.start();

            }
        });

    }

    private void stopPlaying() {
        if (player4.isPlaying()) {
            player4.stop();
            player4.release();
            initializeMediaPlayer();
        }

        buttonPlay4.setEnabled(true);
        buttonStopPlay4.setEnabled(false);
        playSeekBar4.setIndeterminate(true);
        playSeekBar4.setVisibility(View.INVISIBLE);

    }

    private void initializeMediaPlayer() {
        player4 = new MediaPlayer();
        try {
            player4.setDataSource(url_radio4);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player4.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playSeekBar4.setIndeterminate(false);
                playSeekBar4.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player4.isPlaying()) {
            //  player.stop();
        }
    }
}