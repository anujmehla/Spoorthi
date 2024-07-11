import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterrComponent } from './footerr/footerr.component';
import { HeaderComponent } from './header/header.component';
import { ContactUsPageComponent } from './contact-us-page/contact-us-page.component';

const routes: Routes = [
 {
  path:'',component:HomePageComponent
 },
 {
  path:'footer',component:FooterrComponent
 },
 {
  path:'contactus',component:ContactUsPageComponent
 }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
