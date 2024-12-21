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
  displayedColumns: string[] = ['id', 'szobaSzam', 'elfoglalasDatum', 'elhagyasDatum', 'actions'];
  dataSource = new MatTableDataSource<FoglalasV>(this.foglalasok);

  constructor(private foglalasService: FoglalasService, 
    private felhasznaloService: FelhasznaloService,
    private szobaService: SzobaService,
    private router: Router) {
  }

  ngOnInit() {
    this.loadFelhasznalo();
    this.loadSzobak();
    this.loadFoglalask();
  }

  loadFoglalask() {
    this.foglalasService.getAll().subscribe(data => {
      let a: FoglalasV[] = (data as Foglalas[]).map(x => { return {...x, isEditing: false, isNew: false } });
      this.foglalasok = a;
      this.dataSource.data = a;
    });
  }

  loadSzobak() {
    this.szobaService.getAll().subscribe(data => {
      this.szobak = data;
    })
  }

  loadFelhasznalo() {
    this.felhasznaloService.getFelhasznalo().subscribe(data => {
      this.felhasznalo = data;
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

  deleteFoglalas(id: number): void {
    this.foglalasService.delete(id).subscribe(data => {
      this.loadFoglalask();
    }, error => {
      if(error.status === 403)
        this.router.navigate(['/']);
      else
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
    console.log(foglalas);
    let o;
    if(foglalas.isNew) {
      o = this.foglalasService.create(foglalas);
    }
    else {
      o = this.foglalasService.update(foglalas);
    }
    
    o.subscribe(data => {
      this.loadFoglalask();
    }, error => {
      if(error.status === 403)
        this.router.navigate(['/']);
      else
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
