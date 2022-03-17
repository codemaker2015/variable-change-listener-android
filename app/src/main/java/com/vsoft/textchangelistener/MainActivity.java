package com.vsoft.textchangelistener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MutableLiveData object creation
        MutableLiveData<String> listen = new MutableLiveData<>();
        listen.setValue("Initial value: 0");

        //Listener for listening the changes
        listen.observe(MainActivity.this,new Observer<String>() {
            @Override
            public void onChanged(String changedValue) {
                Toast.makeText(MainActivity.this, listen.getValue(),Toast.LENGTH_LONG).show();
            }
        });

        //Changing values randomly
        new CountDownTimer(30000, 3000) {
            public void onTick(long millisUntilFinished) {
                listen.setValue("Changed value: " + new Random().nextInt(61));
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setText(listen.getValue());
            }

            public void onFinish() {
                listen.setValue("Final value: 100");
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setText(listen.getValue());
            }
        }.start();
    }
}