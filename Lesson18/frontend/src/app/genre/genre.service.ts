import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import * as config from '../config';

export interface Genre {
  id: string;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  path = 'genres';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<Array<Genre>> {
    return this.httpClient.get<Array<Genre>>(config.BASE_URL + this.path);
  }

  getById(id: string): Observable<Genre> {
    return this.httpClient.get<Genre>(config.BASE_URL + this.path + '/' + id);
  }

  save(genre: Genre): Observable<Genre> {
    return this.httpClient.post<Genre>(config.BASE_URL + this.path, genre, config.httpOptions);
  }

  deleteById(id: string): Observable<{}> {
    return this.httpClient.delete<{}>(config.BASE_URL + this.path + '/' + id);
  }

  deleteAll(): Observable<{}> {
    return this.httpClient.delete<{}>(config.BASE_URL + this.path);
  }
}
