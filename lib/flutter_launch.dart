import 'dart:async';

import 'package:flutter/services.dart';

class FlutterLaunch {
  static const MethodChannel _channel =
      MethodChannel('br.com.thyagoluciano/flutter_launch');

  static Future<void> launchWhatsapp({
    required String phone,
    required String message,
  }) async {
    final Map<String, dynamic> params = <String, dynamic>{
      'phone': phone,
      'message': message
    };
    await _channel.invokeMethod('launchWhatsapp', params);
  }

  static Future<bool> hasApp({required String name}) async {
    final Map<String, dynamic> params = <String, dynamic>{
      'name': name,
    };
    return await _channel.invokeMethod('hasApp', params);
  }
}
