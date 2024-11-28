import 'package:flutter/material.dart';
import '../screens/view_book.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Public Library Dashboard'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: GridView.count(
          crossAxisCount: 2, // 2 items per row
          crossAxisSpacing: 10,
          mainAxisSpacing: 10,
          children: [
            _buildFeatureCard(
              context,
              title: 'Books',
              icon: Icons.book,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => BookListScreen()),
                );
              },
            ),
            _buildFeatureCard(
              context,
              title: 'Patrons',
              icon: Icons.people,
              onTap: () {
                // Navigate to PatronListScreen
              },
            ),
            _buildFeatureCard(
              context,
              title: 'Borrowing Records',
              icon: Icons.history,
              onTap: () {
                // Navigate to BorrowingRecordScreen
              },
            ),
            _buildFeatureCard(
              context,
              title: 'Add Book',
              icon: Icons.add_box,
              onTap: () {
                // Navigate to AddBookScreen
              },
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildFeatureCard(BuildContext context, {required String title, required IconData icon, required VoidCallback onTap}) {
    return GestureDetector(
      onTap: onTap,
      child: Card(
        elevation: 4,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(12),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(icon, size: 48, color: Theme.of(context).primaryColor),
            SizedBox(height: 8),
            Text(title, style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}
