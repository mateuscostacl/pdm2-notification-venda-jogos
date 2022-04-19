package com.example.pdm2_notification_venda_jogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pdm2_notification_venda_jogos.model.Jogo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String CANAL_ID = "2";
    private final static int NOTIFICACAO_ID = 1;

    private List<Jogo> jogos;
    EditText etNome;
    EditText etPreco;
    EditText etGenero;
    EditText etPlataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        criarCanal();
    }

    public void notificar(String texto) {

        Intent intent = new Intent(this, SegundaActivity.class);
        intent.putExtra("media", texto);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.alerta);

        Notification builder = new NotificationCompat.Builder(this, CANAL_ID).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("AVISO!")
                .setContentText("O limite já foi atingido.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Devido a promoção em todos os jogos lançamentos, limitamos a compra em 3 itens por pessooa. Caso queira comprar mais, aguarde até amanhã")).build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(NOTIFICACAO_ID,builder);
    }

    private void criarCanal(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            CharSequence nome = "canal1";
            String descricao = "descrição do canal 1";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CANAL_ID, nome,importancia);
            canal.setDescription(descricao);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(canal);
        }
    }

    private void initLayout() {
        jogos = new ArrayList<>();
        etNome = findViewById(R.id.et_nome);
        etPreco = findViewById(R.id.et_preco);
        etGenero = findViewById(R.id.et_genero);
        etPlataforma = findViewById(R.id.et_plataforma);

        Button btCadastrar = findViewById(R.id.bt_cadastrar);
        btCadastrar.setOnClickListener(view -> {

            Button self = (Button) view;

            Jogo jogo = new Jogo();
            jogo.setNome(etNome.getText().toString());
            jogo.setPreco(Double.parseDouble(etPreco.getText().toString()));
            jogo.setGenero(etGenero.getText().toString());
            jogo.setPlataforma(etPlataforma.getText().toString());

            jogos.add(jogo);

            boolean isMax = jogos.size() >= 3;
            if(isMax) {
                self.setEnabled(false);
                notificar(calcularMedia(jogos));
            }

            resetFields();
        });
    }

    private String calcularMedia(List<Jogo> jogos){
        Double soma = 0.0;

        for (int i = 0; i < jogos.size(); i++) {
            soma += jogos.get(i).getPreco();
        }

        return String.format("%.2f", soma/jogos.size());
    }

    private void resetFields() {
        etNome.setText("");
        etPreco.setText("");
        etGenero.setText("");
        etPlataforma.setText("");
        etNome  .requestFocus();
    }
}