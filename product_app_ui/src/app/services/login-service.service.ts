import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private client: HttpClient) { }

  public login( username : string, password : string) {
    const options = {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    };

    let params: HttpParams = new HttpParams();
    params = params.set('grant_type', "password");
    params = params.append('client_id', "login-app-keycloak-client");
    params = params.append('username', username);
    params = params.append('password', password);

    console.log(params.has('client_id'));

    this.client.post("http://localhost:30181/auth/realms/login-app-keycloak/protocol/openid-connect/token", 
    params, options)
    .subscribe( (data) => console.log(JSON.stringify(data)));
  }
}
