package br.unifor.tomatti.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import br.unifor.tomatti.listener.CounterListener;

/**
 * Created by chico on 03/12/16.
 */

public class ClockService extends Service {

    private final IBinder binder = new LocalBinder();
    private int counter;
    private boolean stop;
    private Thread clockThread;
    private CounterListener listener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setListener(CounterListener listener){
        this.listener = listener;
    }

    public void startClock(){
        clockThread = new Thread(new Runnable() {
            @Override
            public void run() {

                counter = 0;
                stop = false;

                while(!stop){
                    try {
                        //Log.i("App", "Contador: " + contador);
                        Thread.sleep(1000);
                        counter += 1;
                        listener.notifyNewValue(counter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        clockThread.start();
    }

    public void startClock(final int value){
        clockThread = new Thread(new Runnable() {
            @Override
            public void run() {

                counter = value;
                stop = false;

                while(!stop && counter > 0){
                    try {
                        Thread.sleep(1000);
                        counter -= 1;
                        listener.notifyNewValue(counter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        clockThread.start();
    }

    public void stopClock(){
        try {
            stop = true;
            clockThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ClockService getService(){
        return ClockService.this;
    }

    public class LocalBinder extends Binder {
        public ClockService getService(){
            return ClockService.this;
        }
    }

}