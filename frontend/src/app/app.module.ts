import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AngularMaterialModule } from './angular-material-module';
import { AuthenticationControlComponent } from './authentication-control/authentication-control.component';
import { HomeComponent } from './home/home.component';
import { PessoaService } from './services/pessoa.service';
import { PessoaModule } from './pessoa/pessoa.module';
import { MastheadModule } from './masthead/masthead.module';

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
    MastheadModule,
    PessoaModule
  ],
  providers: [PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
