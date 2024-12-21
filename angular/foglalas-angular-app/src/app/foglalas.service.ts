import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Felhasznalo } from './felhasznalo.service';
import { Szoba } from './szoba.service';

export interface Foglalas {
  id?: number;
  felhasznaloId?: number;
  szobaId?: number;
  elfoglalasDatum: Date;
  elhagyasDatum: Date;

  felhasznalo?: Felhasznalo;
  szoba?: Szoba;
}

@Injectable({
  providedIn: 'root'
})
export class FoglalasService {
  private readonly baseUrl = 'http://localhost:8080/api/foglalas';

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

  update(foglalas: Foglalas): Observable<any> {
    return this.http.put(`${this.baseUrl}/update?id=${foglalas.id}`, foglalas, { headers: this.getHeaders() });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete?id=${id}`, { headers: this.getHeaders() });
  }
}
