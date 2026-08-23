package com.example.islamic

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val channelName = "noor/native"
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        NoorNativeBridge(this).attach(MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelName))
    }
}
