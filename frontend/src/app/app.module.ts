import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AngularMaterialModule } from './angular-material-module';
import { AuthenticationControlComponent } from './components/authentication-control/authentication-control.component';
import { HomeComponent } from './components/home/home.component';
import { PessoaService } from './services/pessoa.service';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AuthenticationControlComponent,
    HomeComponent
  ],
  imports: [
    AngularMaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
  ],
  providers: [PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
