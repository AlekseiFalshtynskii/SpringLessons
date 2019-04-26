import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import * as config from '../config';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  path = 'login';

  constructor(private httpClient: HttpClient) {
  }

  save(data): Observable<{}> {
    const formData: FormData = new FormData();
    formData.append('username', data.username);
    formData.append('password', data.password);
    return this.httpClient.post(config.BASE_URL + this.path, formData, {responseType: 'text'});
  }
}
