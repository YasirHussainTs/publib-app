import 'dart:convert';
import 'package:http/http.dart' as http;

class BookService {
  final String baseUrl = "http://localhost:8080/api/books";

  Future<List<dynamic>> getAllBooks() async {
    final response = await http.get(Uri.parse(baseUrl));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception("Failed to load books");
    }
  }

  Future<Map<String, dynamic>> getBookById(int id) async {
    final response = await http.get(Uri.parse('$baseUrl/$id'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception("Failed to load book details");
    }
  }

  Future<Map<String, dynamic>> addBook(Map<String, dynamic> book) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {"Content-Type": "application/json"},
      body: json.encode(book),
    );
    return json.decode(response.body);
  }

  // Implement updateBook and deleteBook similarly
}

