import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { UserModule } from './user/user.module';
import { AdminModule } from './admin/admin.module';
import { SharedModule } from './shared/shared.module';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from 'ngx-ui-loader';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    UserModule,
    AdminModule,
    SharedModule,
    NgxUiLoaderModule.forRoot({
      "bgsColor": "#5aca81",
      "bgsOpacity": 0.5,
      "bgsPosition": "bottom-left",
      "bgsSize": 60,
      "bgsType": "chasing-dots",
      "blur": 5,
      "delay": 0,
      "fastFadeOut": true,
      "fgsColor": "#10b981",
      "fgsPosition": "center-center",
      "fgsSize": 60,
      "fgsType": "cube-grid",
      "gap": 24,
      "logoPosition": "top-left",
      "logoSize": 120,
      "logoUrl": "",
      "masterLoaderId": "master",
      "overlayBorderRadius": "0",
      "overlayColor": "rgba(40, 40, 40, 0.8)",
      "pbColor": "red",
      "pbDirection": "rtl",
      "pbThickness": 3,
      "hasProgressBar": false,
      "text": "",
      "textColor": "#FFFFFF",
      "textPosition": "center-center",
      "maxTime": -1,
      "minTime": 300
    }),

    NgxUiLoaderHttpModule.forRoot({ showForeground: true })
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
