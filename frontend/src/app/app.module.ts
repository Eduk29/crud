import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AngularMaterialModule } from './angular-material-module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationControlComponent } from './components/authentication-control/authentication-control.component';
import { MastheadModule } from './components/masthead/masthead.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PessoaFormModule } from './components/pessoa-form/pessoa-form.module';
import { HomeComponent } from './pages/home/home.component';
import { PessoaModule } from './pages/pessoa/pessoa.module';
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
    MastheadModule,
    PessoaFormModule,
    PessoaModule
  ],
  providers: [PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
