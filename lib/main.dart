import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = MethodChannel('flutter.native/helper');

  Future<void> responseFromNative() async {
    try {
      await platform.invokeMethod('showToast', {"title": "Hello Brother"});
    } on Exception catch (e) {
      print("Failed to Invoke: '${e.toString()}'.");
    }
  }

  Future<void> staryService() async {
    try {
      await platform.invokeMethod('startService');
    } on Exception catch (e) {
      print("Failed to Invoke: '${e.toString()}'.");
    }
  }

  Future<void> stopService() async {
    try {
      await platform.invokeMethod('stopService');
    } on Exception catch (e) {
      print("Failed to Invoke: '${e.toString()}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
          child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          ElevatedButton(
            onPressed: responseFromNative,
            child: const Text('Show Toast'),
          ),
          ElevatedButton(
            onPressed: staryService,
            child: const Text('Start Service'),
          ),
          ElevatedButton(
            onPressed: stopService,
            child: const Text('Stop Service'),
          ),
        ],
      )),
    );
  }
}
