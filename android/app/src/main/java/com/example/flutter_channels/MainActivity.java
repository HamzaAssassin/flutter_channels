package com.example.flutter_channels;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "flutter.native/helper";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(),CHANNEL).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {

                switch (call.method) {

                    case "showToast":
                        Map<String,String> args= ((Map<String,String>) call.arguments);
                        String title=args.get("title");
                        Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                        break;
                    case "startService":
                        Context startContext = MainActivity.this.getApplicationContext();
                        Intent startServiceIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                        startContext.startService(startServiceIntent);

                        break;
                    case "stopService":
                        Context stopContext = MainActivity.this.getApplicationContext();
                        Intent stopServiceIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                        stopContext.stopService(stopServiceIntent);
                        break;
                }
            }
        });
    }
}
