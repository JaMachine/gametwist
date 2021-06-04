package com.twist.gametwist.store.app;

import android.app.Application;

import com.onesignal.OneSignal;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MainApplication extends Application {
    private static final String ONESIGNAL_APP_ID = "775f2753-4993-4c30-b65b-8db337054b32";

    @Override
    public void onCreate() {
        super.onCreate();

        yandexMetric();
        startOneSignal();
    }

    void yandexMetric() {
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder("6396a14f-cd04-4143-8e16-4ffdddded15d").build();
        YandexMetrica.activate(getApplicationContext(), config);
        YandexMetrica.enableActivityAutoTracking(this);
    }

    void startOneSignal() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
