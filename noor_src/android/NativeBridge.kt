package com.example.islamic

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import io.flutter.plugin.common.MethodChannel

class NoorNativeBridge(private val context: Context) {
    private var player: MediaPlayer? = null
    private val channelId = "noor_adhan"

    fun attach(channel: MethodChannel) {
        createChannel()
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                "playQuran" -> play(call.argument<String>("url") ?: "https://download.quranicaudio.com/quran/mishary_rashid_alafasy/001.mp3", result)
                "stopAudio" -> { stop(); result.success(null) }
                "showAdhanNotification" -> { showNotification(call.argument<String>("prayer") ?: "الصلاة"); result.success(null) }
                else -> result.notImplemented()
            }
        }
    }

    private fun play(url: String, result: MethodChannel.Result) {
        stop()
        try {
            player = MediaPlayer().apply {
                setAudioAttributes(AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
                setDataSource(url)
                setOnPreparedListener { it.start(); result.success(true) }
                setOnCompletionListener { stop() }
                setOnErrorListener { _, _, _ -> result.error("AUDIO_ERROR", "Unable to play audio", null); true }
                prepareAsync()
            }
        } catch (e: Exception) { result.error("AUDIO_ERROR", e.message, null) }
    }

    private fun stop() { player?.runCatching { stop() }; player?.release(); player = null }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(NotificationChannel(channelId, "أذان Noor", NotificationManager.IMPORTANCE_HIGH).apply {
                description = "تنبيهات مواقيت الصلاة في Noor"
                setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI, AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build())
            })
        }
    }

    private fun showNotification(prayer: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        val flags = PendingIntent.FLAG_UPDATE_CURRENT or if (Build.VERSION.SDK_INT >= 23) PendingIntent.FLAG_IMMUTABLE else 0
        val pending = PendingIntent.getActivity(context, 10, intent, flags)
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(context, channelId) else Notification.Builder(context)
        val notification = builder.setSmallIcon(android.R.drawable.ic_lock_idle_alarm).setContentTitle("حان وقت الصلاة").setContentText("حان الآن وقت صلاة $prayer").setAutoCancel(true).setContentIntent(pending).setPriority(Notification.PRIORITY_HIGH).build()
        manager.notify(prayer.hashCode(), notification)
    }
}
