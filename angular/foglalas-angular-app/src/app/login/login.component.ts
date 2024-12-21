import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Felhasznalo, FelhasznaloService } from '../felhasznalo.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  jelszo = '';

  constructor(private authService: AuthService, private felhasznaloService: FelhasznaloService, private router: Router) {}

  onLogin() {
    this.authService.login(this.email, this.jelszo).subscribe(
      (response: any) => {
        localStorage.setItem('token', response);
        console.log('Sikeres bejelentkezés');
        //this.router.navigate(['/foglalasok']);

        this.felhasznaloService.getFelhasznalo().subscribe(
          (response: Felhasznalo) => {
            if(response.jogosultsagok.findIndex(x => x.nev === "ADMIN") !== -1) {
              this.router.navigate(['/admin']);
            }
            else {
              this.router.navigate(['/foglalasok'])
            }
          }
        )
      },
      error => {
        console.error('Hiba történt a bejelentkezés során', error);
      }
    );
  }
}
