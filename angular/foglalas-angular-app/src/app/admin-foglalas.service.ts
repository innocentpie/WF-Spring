import { Injectable } from '@angular/core';
import { Foglalas } from './foglalas.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminFoglalasService {
  private readonly baseUrl = 'http://localhost:8080/admin/api/foglalas';

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

  create(foglalas: Foglalas): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, foglalas, { headers: this.getHeaders() });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete?id=${id}`, { headers: this.getHeaders() });
  }

  update(foglalas: Foglalas): Observable<any> {
    return this.http.put(`${this.baseUrl}/update?id=${foglalas.id}`, foglalas, { headers: this.getHeaders() });
  }
}
