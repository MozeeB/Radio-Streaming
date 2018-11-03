package com.example.mozeeb.radiostreaming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.judul)
    TextView judul;
    @BindView(R.id.bandung)
    Button bandung;
    @BindView(R.id.rodja)
    Button rodja;
    @BindView(R.id.muslim)
    Button muslim;
    @BindView(R.id.cirebon)
    Button cirebon;
    @BindView(R.id.hidayah)
    Button hidayah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bandung, R.id.rodja, R.id.muslim, R.id.cirebon, R.id.hidayah})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bandung:
                startActivity(new Intent(Dashboard.this, BandungActivity.class));
                break;
            case R.id.rodja:
                startActivity(new Intent(Dashboard.this, Roadja.class));
                break;
            case R.id.muslim:
                startActivity(new Intent(Dashboard.this, MuslimActivity.class));
                break;
            case R.id.cirebon:
                startActivity(new Intent(Dashboard.this, CirebonActivity.class));
                break;
            case R.id.hidayah:
                startActivity(new Intent(Dashboard.this, HidayahActivity.class));
                break;
        }
    }
}
