import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import * as config from '../config';
import { Author } from '../author/author.service';
import { Genre } from '../genre/genre.service';

export interface Book {
  id: number;
  name: string;
  description: string;
  authors: Array<Author>;
  genres: Array<Genre>;
}

@Injectable({
  providedIn: 'root'
})
export class BookService {
  path = 'books';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<Array<Book>> {
    return this.httpClient.get<Array<Book>>(config.BASE_URL + this.path);
  }

  getById(id: number): Observable<Book> {
    return this.httpClient.get<Book>(config.BASE_URL + this.path + '/' + id);
  }

  save(book: Book): Observable<Book> {
    return this.httpClient.post<Book>(config.BASE_URL + this.path, book, config.httpOptions);
  }

  deleteById(id: number): Observable<{}> {
    return this.httpClient.delete<{}>(config.BASE_URL + this.path + '/' + id);
  }

  deleteAll(): Observable<{}> {
    return this.httpClient.delete<{}>(config.BASE_URL + this.path);
  }
}
