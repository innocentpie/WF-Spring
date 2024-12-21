import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import {provideHttpClient} from '@angular/common/http'; // A routing konfiguráció
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MAT_DATE_LOCALE } from '@angular/material/core';

bootstrapApplication(AppComponent, {
  providers: [provideRouter(routes), provideHttpClient(), importProvidersFrom([BrowserAnimationsModule]), {provide: MAT_DATE_LOCALE, useValue: 'hu-HU'}]
});