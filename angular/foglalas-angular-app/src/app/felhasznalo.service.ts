import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Felhasznalo {
  id?: number;
  nev: string;
  email: string;
  telefonszam: string;
  jogosultsagok: Jogosultsag[];
}

export function isAdmin(felhasznalo: Felhasznalo): boolean {
  return felhasznalo.jogosultsagok.findIndex(x => x.nev === "ADMIN") !== -1;
}

export interface Jogosultsag {
  id?: number;
  nev: string;
}

@Injectable({
  providedIn: 'root'
})
export class FelhasznaloService {
  private readonly baseUrl = 'http://localhost:8080/api/felhasznalo';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    return new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` , 'Access-Control-Allow-Origin': '*'});
  }

  getFelhasznalo(): Observable<any> {
    return this.http.get(`${this.baseUrl}/get`, { headers: this.getHeaders() });
  }
}
