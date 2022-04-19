package com.example.pdm2_notification_venda_jogos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    TextView tvMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        initLayout();
    }

    private void initLayout(){
        tvMedia = findViewById(R.id.tv_media);

        tvMedia.setText(getIntent().getStringExtra("media"));
    }
}