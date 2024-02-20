import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FishService {

  private url = "http://localhost:8080/api/fish"

  constructor(private http: HttpClient) { }

  getAllFish(): Observable<any> {
    return this.http.get<any>(this.url);
  }
}
