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
      "bgsColor": "red",
      "bgsOpacity": 0.5,
      "bgsPosition": "bottom-right",
      "bgsSize": 60,
      "bgsType": "ball-spin-clockwise",
      "blur": 5,
      "delay": 0,
      "fastFadeOut": true,
      "fgsColor": "#1ff736",
      "fgsPosition": "center-center",
      "fgsSize": 60,
      "fgsType": "three-strings",
      "gap": 24,
      "logoPosition": "center-center",
      "logoSize": 120,
      "logoUrl": "../../../../assets/images/logo.png",
      "masterLoaderId": "master",
      "overlayBorderRadius": "0",
      "overlayColor": "rgba(40, 40, 40, 0.8)",
      "pbColor": "red",
      "pbDirection": "ltr",
      "pbThickness": 3,
      "hasProgressBar": true,
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
