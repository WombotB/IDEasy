package com.example.ideasy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    item item = new item();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonsave = (Button)findViewById(R.id.button2);
        Button buttonupload = (Button)findViewById(R.id.button3);
        EditText text = (EditText) findViewById(R.id.editTextText2);
        TextView codeF = (TextView) findViewById(R.id.codeF);
//        text.setText(getCode());

        buttonsave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String code = text.getText().toString();
                //saveCode in item!!!!
            }
        });

        buttonupload.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    String code = text.getText().toString();
                    sendPOST(code);
                }
            });
    }

    public void sendPOST(String code) {

        // создаем singleton объект клиента
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://10.0.2.2:8080").newBuilder();
        urlBuilder.addQueryParameter("codeSTR", code);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .cacheControl(new CacheControl.Builder().maxStale(30, TimeUnit.DAYS).build())
                .build();
        // выполняем запрос
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // читаем данные в отдельном потоке
                    final String responseData = response.body().string();

                    // выполняем операции по обновлению UI
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            codeF.setText(responseData);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
}

