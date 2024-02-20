import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hunting } from 'src/app/Models/Hunting';

@Injectable({
  providedIn: 'root'
})
export class HuntingService {

  private url = "http://localhost:8080/api/hunting"
  constructor(private http: HttpClient) { }

  saveHunting(hunting: Hunting): Observable<Hunting> {
    return this.http.post<Hunting>(this.url, hunting)
  }

}