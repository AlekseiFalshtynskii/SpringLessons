import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import * as config from '../config';

export interface Author {
  id: number;
  firstName: string;
  lastName: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  path = 'authors';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<Array<Author>> {
    return this.httpClient.get<Array<Author>>(config.BASE_URL + this.path);
  }

  getById(id: number): Observable<Author> {
    return this.httpClient.get<Author>(config.BASE_URL + this.path + '/' + id);
  }

  save(author: Author): Observable<Author> {
    return this.httpClient.post<Author>(config.BASE_URL + this.path, author, config.httpOptions);
  }

  deleteById(id: number): Observable<{}> {
    return this.httpClient.delete<{}>(config.BASE_URL + this.path + '/' + id);
  }

  deleteAll(): Observable<{}> {
    return this.httpClient.delete<{}>(config.BASE_URL + this.path);
  }
}
