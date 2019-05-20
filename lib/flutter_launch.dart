import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

class FlutterLaunch {
  static const MethodChannel _channel = const MethodChannel('flutter_launch');

  static Future<Null> launchWathsApp(
      {@required String phone, @required String message}) async {
    final Map<String, dynamic> params = <String, dynamic>{
      'phone': phone,
      'message': message
    };
    await _channel.invokeMethod('launchWathsApp', params);
  }

  static Future<bool> hasApp({@required String name}) async {
    final Map<String, dynamic> params = <String, dynamic>{
      'name': name,
    };
    return await _channel.invokeMethod('hasApp', params);
  }
}
