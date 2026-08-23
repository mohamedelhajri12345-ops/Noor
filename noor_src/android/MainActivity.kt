package com.comporx.noor

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val channelName = "noor/native"
    private lateinit var bridge: NoorNativeBridge

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        bridge = NoorNativeBridge(this)
        bridge.attach(MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelName))
    }
}
