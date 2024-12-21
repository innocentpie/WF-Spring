import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { Szoba, SzobaService } from '../szoba.service';
import { CommonModule } from '@angular/common';
import { Felhasznalo, FelhasznaloService, isAdmin } from '../felhasznalo.service';
import { AdminSzobaService } from '../admin-szoba.service';
import { HeaderComponent } from '../header/header.component';
import { Router } from '@angular/router';

interface SzobaV extends Szoba{
  isEditing: boolean;
  isNew: boolean;
}

@Component({
  selector: 'app-szoba-list',
  standalone: true,
  imports: [MatTableModule,
    CommonModule,
    HeaderComponent,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    FormsModule],
  templateUrl: './szoba-list.component.html',
  styleUrl: './szoba-list.component.css'
})
export class SzobaListComponent {
  felhasznalo: Felhasznalo | null = null;
  szobak: SzobaV[] = [];
  displayedColumns: string[] = ['id', 'szobaSzam', 'maxFerohely', 'ftPerEjszaka'];
  dataSource = new MatTableDataSource<SzobaV>(this.szobak);

  constructor(private szobaService: SzobaService, 
    private felhasznaloService: FelhasznaloService,
    private adminSzobaService: AdminSzobaService,
    private router: Router) {
  }

  ngOnInit() {
    this.loadFelhasznalo();
    this.loadSzobak();
  }

  loadSzobak() {
    this.szobaService.getAll().subscribe(data => {
      let a = (data as Szoba[]).map(x => { return {...x, isEditing: false, isNew: false } });
      this.szobak = a;
      this.dataSource.data = a;

      console.log(a);
    });
  }

  loadFelhasznalo() {
    this.felhasznaloService.getFelhasznalo().subscribe(data => {
      this.felhasznalo = data;

      if(this.felhasznalo && isAdmin(this.felhasznalo))
        this.displayedColumns.push('actions');
    });
  }

  isAdmin() {
    if(this.felhasznalo)
      return isAdmin(this.felhasznalo);
    return false;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteSzoba(id: number): void {
    this.adminSzobaService.delete(id).subscribe(data => {
      this.loadSzobak();
    }, error => {
      if(error.status === 403)
        this.router.navigate(['/']);
      else
        this.loadSzobak();
    });
  }

  editSzoba(id: number): void {
    let s = this.szobak.find(x => x.id === id);
    if(s)
      s.isEditing = true;
  }

  saveSzoba(szoba: SzobaV): void {
    szoba.isEditing = false;
    let o;
    if(szoba.isNew) {
      o = this.adminSzobaService.create(szoba);
    }
    else {
      o = this.adminSzobaService.update(szoba);
    }
    
    o.subscribe(data => {
      this.loadSzobak();
    }, error => {
      if(error.status === 403)
        this.router.navigate(['/']);
      else
        this.loadSzobak();
    });
  }

  addSzoba(): void {
    if(this.szobak.length > 0
      && this.szobak[this.szobak.length - 1].isNew)
      return;

    let a: SzobaV = {
      id: undefined,
      szobaszam: 0,
      maxFerohely: 0,
      ftPerEjszaka: 0,
      isEditing: true,
      isNew: true,
    };
    this.szobak.push(a);
    this.dataSource.data = this.szobak;
  }

  cancelAddSzoba(): void {
    this.szobak.pop();
    this.dataSource.data = this.szobak;
  }

  cancelEditSzoba(szoba: SzobaV): void {
    szoba.isEditing = false;
  }
}
