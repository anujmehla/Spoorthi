import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterrComponent } from './footerr/footerr.component';
import { HomePageComponent } from './home-page/home-page.component';
import { TestimonalsComponent } from './testimonals/testimonals.component';
import { ImageAnimationsComponent } from './image-animations/image-animations.component';
import { CardsComponent } from './cards/cards.component';
import { HelptodyComponent } from './helptody/helptody.component';
import { ContactUsPageComponent } from './contact-us-page/contact-us-page.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterrComponent,
    HomePageComponent,
    TestimonalsComponent,
    ImageAnimationsComponent,
    CardsComponent,
    HelptodyComponent,
    ContactUsPageComponent,
    HeaderComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
