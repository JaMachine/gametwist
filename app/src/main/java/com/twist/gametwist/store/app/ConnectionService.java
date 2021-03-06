package com.twist.gametwist.store.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import static com.twist.gametwist.store.app.V.BroadcastStringForAction;


public class ConnectionService  extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(networkStateUpdate);
        return START_STICKY;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) return true;
        else return false;
    }

    Handler handler = new Handler();
    private Runnable networkStateUpdate = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(networkStateUpdate, 1 * 1000 - SystemClock.elapsedRealtime() % 1000);
            Intent intent = new Intent();
            intent.setAction(BroadcastStringForAction);
            intent.putExtra("online_status", "" + isOnline(ConnectionService.this));
            sendBroadcast(intent);
        }
    };
}
