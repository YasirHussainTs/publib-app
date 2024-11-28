import 'package:flutter/material.dart';
import 'package:publibapp/screens/detail_book.dart';
import 'package:publibapp/screens/view_book.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Public Library App',
      theme: ThemeData.light().copyWith(
        primaryColor: Colors.blue,
        scaffoldBackgroundColor: Colors.white,
        appBarTheme: AppBarTheme(
          backgroundColor: Colors.blue,
          elevation: 0,
          iconTheme: IconThemeData(color: Colors.white),
          toolbarTextStyle: TextTheme(
            headline6: TextStyle(
              color: Colors.white,
              fontSize: 20.0,
              fontWeight: FontWeight.bold,
            ),
          ).bodyText2,
          titleTextStyle: TextTheme(
            headline6: TextStyle(
              color: Colors.white,
              fontSize: 20.0,
              fontWeight: FontWeight.bold,
            ),
          ).headline6,
        ),
      ),
      initialRoute: '/booklist',
      routes: {
        '/booklist': (context) => BookListScreen(),
      },
      onGenerateRoute: (settings) {
        if (settings.name == '/bookdetail') {
          final args = settings.arguments as int;
          return MaterialPageRoute(
            builder: (context) => BookDetailScreen(args),
          );
        }
        return null;
      },
    );
  }
}
