import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Foglalas, FoglalasService } from '../foglalas.service';
import { HeaderComponent } from '../header/header.component';
import { Felhasznalo, FelhasznaloService, isAdmin } from '../felhasznalo.service';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { Szoba, SzobaService } from '../szoba.service';
import { MatSelectModule } from '@angular/material/select';
import { AdminFoglalasService } from '../admin-foglalas.service';
import { AdminFelhasznaloService } from '../admin-felhasznalo.service';

interface FoglalasV extends Foglalas{
  isEditing: boolean;
  isNew: boolean;
}

@Component({
  selector: 'app-foglalasok',
  standalone: true,
  imports: [MatTableModule,
    CommonModule,
    HeaderComponent,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatDatepickerModule,
    MatSelectModule,
    FormsModule],
  providers: [provideNativeDateAdapter()],
  templateUrl: './foglalasok.component.html',
  styleUrl: './foglalasok.component.css'
})
export class FoglalasokComponent {
  felhasznalo: Felhasznalo | null = null;
  foglalasok: FoglalasV[] = [];
  szobak: Szoba[] = [];
  felhasznalok: Felhasznalo[] = [];

  displayedColumns: string[] = ['id', 'szobaszam', 'elfoglalasDatum', 'elhagyasDatum', 'actions'];
  adminDisplayedColumns: string[] = ['id', 'felhasznalo', 'szobaszam', 'elfoglalasDatum', 'elhagyasDatum', 'actions'];

  dataSource = new MatTableDataSource<FoglalasV>(this.foglalasok);

  constructor(private foglalasService: FoglalasService, 
    private adminFoglalasService: AdminFoglalasService,
    private felhasznaloService: FelhasznaloService,
    private adminFelhasznaloService: AdminFelhasznaloService,
    private szobaService: SzobaService,
    private router: Router) {
  }

  ngOnInit() {
    this.loadFelhasznalo(() => {
      this.loadSzobak();
      this.loadFelhasznalok();
      this.loadFoglalask();
    });
  }

  isAdmin() {
    if(this.felhasznalo)
      return isAdmin(this.felhasznalo);
    return false;
  }

  loadFoglalask() {
    if(this.isAdmin()) {
      this.displayedColumns = this.adminDisplayedColumns;
      this.adminFoglalasService.getAll().subscribe(data => {
        let a: FoglalasV[] = (data as Foglalas[]).map(x => { return {...x, isEditing: false, isNew: false } });
        this.foglalasok = a;
        this.dataSource.data = a;
      });
    }
    else{
      this.foglalasService.getAll().subscribe(data => {
        let a: FoglalasV[] = (data as Foglalas[]).map(x => { return {...x, isEditing: false, isNew: false } });
        this.foglalasok = a;
        this.dataSource.data = a;
      });
    }
  }

  loadSzobak() {
    this.szobaService.getAll().subscribe(data => {
      this.szobak = data;
    })
  }

  loadFelhasznalok() {
    if(this.isAdmin()) {
      this.adminFelhasznaloService.getAll().subscribe(data => {
        this.felhasznalok = data;
      })
    }
  }

  loadFelhasznalo(callback: Function) {
    this.felhasznaloService.getFelhasznalo().subscribe(data => {
      this.felhasznalo = data;

      callback();
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    let filterFunction = function(data: FoglalasV, filter: string) {
      return data.szoba?.szobaszam?.toString().toLowerCase().indexOf(filter.toLowerCase()) !== -1
        || data.id?.toString().toLowerCase().indexOf(filter.toLowerCase()) !== -1
        || data.felhasznalo?.email?.toString().toLowerCase().indexOf(filter.toLowerCase()) !== -1
        || new Date(data.elfoglalasDatum).toLocaleDateString().toLowerCase().indexOf(filter.toLowerCase()) !== -1
        || new Date(data.elhagyasDatum).toLocaleDateString().toLowerCase().indexOf(filter.toLowerCase()) !== -1
    }

    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.filterPredicate = filterFunction;

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteFoglalas(id: number): void {
    let o;
    if(this.isAdmin()) {
      o = this.adminFoglalasService.delete(id);
    }
    else {
      o = this.foglalasService.delete(id);
    }
    o.subscribe(data => {
      this.loadFoglalask();
    }, error => {
      this.loadFoglalask();
    });
  }

  editFoglalas(id: number): void {
    let s = this.foglalasok.find(x => x.id === id);
    if(s)
      s.isEditing = true;
  }

  saveFoglalas(foglalas: FoglalasV): void {
    foglalas.isEditing = false;
    let o;
    if(foglalas.isNew) {
      if(this.isAdmin()) {
        o = this.adminFoglalasService.create(foglalas);
      }
      else {
        o = this.foglalasService.create(foglalas);
      }
    }
    else {
      if(this.isAdmin()) {
        o = this.adminFoglalasService.update(foglalas);
      }
      else {
        o = this.foglalasService.update(foglalas);
      }
    }
    
    o.subscribe(data => {
      this.loadFoglalask();
    }, error => {
      this.loadFoglalask();
    });
  }

  addFoglalas(): void {
    if(this.foglalasok.length > 0
      && this.foglalasok[this.foglalasok.length - 1].isNew)
      return;

    let a: FoglalasV = {
      isEditing: true,
      isNew: true,
      elfoglalasDatum: new Date(),
      elhagyasDatum: new Date(),
      szoba: {
        szobaszam: 0,
      }
    };
    this.foglalasok.push(a);
    this.dataSource.data = this.foglalasok;
  }

  cancelAddFoglalas(): void {
    this.foglalasok.pop();
    this.dataSource.data = this.foglalasok;
  }

  cancelEditFoglalas(foglalas: FoglalasV): void {
    foglalas.isEditing = false;
  }
}
