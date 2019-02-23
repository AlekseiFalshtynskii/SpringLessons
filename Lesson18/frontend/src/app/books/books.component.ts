import { Component, OnInit } from '@angular/core';
import { Book, BookService } from '../book/book.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {
  books: Array<Book>;
  displayedColumns: string[] = ['id', 'name', 'description', 'authors', 'genres', 'action'];

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.bookService.getAll().subscribe(response => {
      this.books = response;
    });
  }

  deleteById(id: string) {
    this.bookService.deleteById(id).subscribe(response => this.getAll());
  }

  deleteAll() {
    this.bookService.deleteAll().subscribe(response => this.getAll());
  }
}
