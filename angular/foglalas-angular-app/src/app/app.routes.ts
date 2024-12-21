import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FoglalasokComponent } from './foglalasok/foglalasok.component';
import { SzobaListComponent } from './szoba-list/szoba-list.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'foglalasok', component: FoglalasokComponent},
    { path: 'szobak', component: SzobaListComponent},
];