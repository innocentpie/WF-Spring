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

@Component({
  selector: 'app-foglalasok',
  standalone: true,
  imports: [MatTableModule,
    HeaderComponent,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    FormsModule],
  templateUrl: './foglalasok.component.html',
  styleUrl: './foglalasok.component.css'
})
export class FoglalasokComponent {
  foglalasok: any[] = [];
  displayedColumns: string[] = ['szoba szám', 'elfoglalás dátuma', 'elhagyás dátuma'];
  dataSource = new MatTableDataSource<Foglalas>(this.foglalasok);

  constructor(private foglalasService: FoglalasService) {
  }

  ngOnInit() {
    this.loadFoglalasok();
  }

  loadFoglalasok() {
    this.foglalasService.getAll().subscribe(data => {
      this.foglalasok = data;
      this.dataSource.data = data;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
