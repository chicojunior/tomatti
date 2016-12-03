package br.unifor.tomatti.activities;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.unifor.tomatti.R;
import br.unifor.tomatti.fragments.TomattiButtonFragment;
import br.unifor.tomatti.fragments.TomattiClockFragment;
import br.unifor.tomatti.fragments.TomattiListFragment;
import br.unifor.tomatti.listener.CounterListener;
import br.unifor.tomatti.service.ClockService;

public class MainActivity extends AppCompatActivity implements CounterListener {

    private FragmentManager mFragmentManager;
    private ClockService clockService;
    private boolean isServiceBound;
    private boolean isCounterStarted;
    private Button mainButton;
    private TextView mainCounter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_clock, new TomattiClockFragment());
        transaction.replace(R.id.fragment_task, new TomattiListFragment());
        transaction.replace(R.id.fragment_button, new TomattiButtonFragment());
        transaction.commit();

        this.isServiceBound = false;
        this.isCounterStarted = false;

        mainCounter = (TextView) findViewById(R.id.clock_counter);
        mainButton = (Button) findViewById(R.id.task_item_btnStart);

        /*mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCounterStarted) {
                    if (isServiceBound) {
                        clockService.stopClock();
                        isCounterStarted = false;
                        mainButton.setText("Iniciar");
                    }
                } else {
                    if (isServiceBound) {
                        clockService.startClock(5);
                        isCounterStarted = true;
                        mainButton.setText("Parar");
                    }
                }
            }
        });*/

        //handler = new Handler();

    }

    /*public void startClick(View view) {
        if (isCounterStarted) {
            if (isServiceBound) {
                clockService.stopClock();
                isCounterStarted = false;
                mainButton.setText("Iniciar");
            }
        } else {
            if (isServiceBound) {
                clockService.startClock(5);
                isCounterStarted = true;
                mainButton.setText("Parar");
            }
        }

        *//*mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCounterStarted) {
                    if (isServiceBound) {
                        clockService.stopClock();
                        isCounterStarted = false;
                        mainButton.setText("Iniciar");
                    }
                } else {
                    if (isServiceBound) {
                        clockService.startClock(5);
                        isCounterStarted = true;
                        mainButton.setText("Parar");
                    }
                }
            }
        });*//*
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        Intent clockIntent = new Intent(this, ClockService.class);
        startService(clockIntent);
        bindService(clockIntent, serviceConnection, Service.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            clockService = clockService.getService();
            clockService.setListener(MainActivity.this);
            isServiceBound = true;
            Log.i("App", "Conectado ao ContadorService");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceBound = false;
            Log.i("App", "Desconectado ao ContadorService");
        }
    };

    @Override
    public void notifyNewValue(final int value) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = value / 60;
                int seconds = value % 60;


                String min = String.valueOf(minutes);
                String seg = String.valueOf(seconds);

                if(min.length() < 2){
                    min = "0" + min;
                }

                if(seg.length() < 2){
                    seg = "0" + seg;
                }

                mainCounter.setText(min + ":" + seg);
            }
        });

    }

}
