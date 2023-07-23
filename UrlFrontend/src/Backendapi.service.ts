
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Url } from './Url';


//mport 'rxjs/add/operator/toPromise';
interface UserResponse
{
status: string;
}
@Injectable()
export class Backendapi {

  baseUrl = 'http://localhost:8087/';
  str: any;
  ur!: string;

  constructor(private http: HttpClient) {
  }
  postLink( url: Url): Observable<Url> {
    this.ur=this.baseUrl+'url';
    return this.http.post<Url>( this.ur,  url );
  }
}
