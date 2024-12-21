import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminFelhasznaloService {
  private readonly baseUrl = 'http://localhost:8080/admin/api/felhasznalo';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    return new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` , 'Access-Control-Allow-Origin': '*'});
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAll`, { headers: this.getHeaders() });
  }
}
