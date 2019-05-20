# Flutter launch WhatsApp

A Flutter plugin for iOS and Android to open whatsApp.

[![pub package](https://img.shields.io/badge/pub-0.2.0-blue.svg)](https://pub.dartlang.org/packages/flutter_launch)

## Installation

First, add flutter_launch_whatsapp as a dependency in your pubspec.yaml file.

## iOS

Add the following entry to your Info.plist file, located in <project root>/ios/Runner/Info.plist:

````xml
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>whatsapp</string>
</array>
````

## Android

n/a

## Example

````dart
import 'package:flutter/material.dart';
import 'package:flutter_launch/flutter_launch.dart';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  initState() {
    super.initState();
  }

  void whatsAppOpen() async {
    await FlutterLaunch.launchWathsApp(phone: "5534992016545", message: "Hello");
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text('Plugin example app'),
        ),
        body: new Center(
          child: FlatButton(
            child: Text("Open WhatsApp"),
            onPressed: () {
              whatsAppOpen();
            },
          )
        ),
      ),
    );
  }
}

````