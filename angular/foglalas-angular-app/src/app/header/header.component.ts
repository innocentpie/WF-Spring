import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Felhasznalo, FelhasznaloService, isAdmin } from '../felhasznalo.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatToolbar,
    MatButtonModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  felhasznalo: Felhasznalo | null = null;

  constructor(private authService: AuthService, private felhasznaloService: FelhasznaloService, private router: Router) {
  }

  ngOnInit() {
    this.loadFelhasznalo();
  }

  logout() {
    this.router.navigate(['/']);
  }

  loadFelhasznalo() {
    this.felhasznaloService.getFelhasznalo().subscribe(data => {
      this.felhasznalo = data;
    }, error => {
      this.router.navigate(['/']);
    });
  }

  navigateToReservations() {
    this.router.navigate(['/foglalasok']);
  }

  navigateToRooms() {
    this.router.navigate(['/szobak']);
  }
}
