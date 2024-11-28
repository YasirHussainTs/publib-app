import 'package:flutter/material.dart';
import '../services/book_service.dart';

class BookDetailScreen extends StatelessWidget {
  final int bookId;
  final BookService bookService = BookService();

  BookDetailScreen(this.bookId);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Book Details')),
      body: FutureBuilder<Map<String, dynamic>>(
        future: bookService.getBookById(bookId),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else {
            final book = snapshot.data!;
            return Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Title: ${book['title']}', style: TextStyle(fontSize: 20)),
                  SizedBox(height: 8),
                  Text('Author: ${book['author']}', style: TextStyle(fontSize: 16)),
                  SizedBox(height: 8),
                  Text('ISBN: ${book['isbn']}', style: TextStyle(fontSize: 16)),
                ],
              ),
            );
          }
        },
      ),
    );
  }
}

