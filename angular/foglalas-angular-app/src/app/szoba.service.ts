import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Szoba {
  id?: number;
  szobaszam?: number;
  maxFerohely?: number;
  ftPerEjszaka?: number;
}

@Injectable({
  providedIn: 'root'
})
export class SzobaService {
  private readonly baseUrl = 'http://localhost:8080/api/szoba';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    return new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` , 'Access-Control-Allow-Origin': '*'});
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAll`, { headers: this.getHeaders() });
  }

  get(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/get?id=${id}`, { headers: this.getHeaders() });
  }
}
