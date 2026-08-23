import 'package:flutter/services.dart';

class NoorAudio {
  static const _channel = MethodChannel('noor/native');

  static Future<void> playQuran({String? url}) async {
    await _channel.invokeMethod('playQuran', {
      'url': url ?? 'https://download.quranicaudio.com/quran/mishary_rashid_alafasy/001.mp3',
    });
  }

  static Future<void> stop() async => _channel.invokeMethod('stopAudio');

  static Future<void> notifyPrayer(String prayer) async =>
      _channel.invokeMethod('showAdhanNotification', {'prayer': prayer});
}
