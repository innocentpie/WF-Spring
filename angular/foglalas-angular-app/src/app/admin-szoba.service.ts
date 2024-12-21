import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Szoba } from './szoba.service';

@Injectable({
  providedIn: 'root'
})
export class AdminSzobaService {
  private readonly baseUrl = 'http://localhost:8080/admin/api/szoba';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    return new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` , 'Access-Control-Allow-Origin': '*'});
  }

  create(szoba: Szoba): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, szoba, { headers: this.getHeaders() });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete?id=${id}`, { headers: this.getHeaders() });
  }

  update(szoba: Szoba): Observable<any> {
    return this.http.put(`${this.baseUrl}/update?id=${szoba.id}`, szoba, { headers: this.getHeaders() });
  }
}
