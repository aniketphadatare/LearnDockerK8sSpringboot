import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginServiceService } from './services/login-service.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
[x: string]: any;

  title = 'product_app_ui';

  constructor(private loginservice: LoginServiceService) {

  }

  LoginClick() {
    console.log("LoginClick button is clicked")
    this.loginservice.login("user1", "user1");
 }

 login(data : FormData){
  console.log("Login button is clicked")
  //console.log(data.value)
 }

}

var req = {
  method: 'POST',
  url: 'http://example.com',
  headers: {
    'Content-Type': undefined
  },
  data: { test: 'test' }
 }
