import 'package:flutter/material.dart';
import 'package:flutter_launch/flutter_launch.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool err = false;
  String msgErr = '';

  @override
  void initState() {
    super.initState();
  }

  void whatsAppOpen() async {
    bool whatsapp = await FlutterLaunch.hasApp(name: "whatsapp");

    if (whatsapp) {
      await FlutterLaunch.launchWhatsapp(
          phone: "5534992016100", message: "Hello, flutter_launch");
    } else {
      setState(() {
        err = false;
        msgErr = '';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin sample app'),
        ),
        body: Center(
            child: TextButton(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text(
                "Whatsapp",
              ),
              err ? Text(msgErr) : const Text('')
            ],
          ),
          onPressed: () {
            whatsAppOpen();
          },
        )),
      ),
    );
  }
}
