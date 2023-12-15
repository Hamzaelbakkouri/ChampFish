import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Member } from 'src/app/Models/Member';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/member"

  getAllMember(): Observable<Member[]> {
    return this.http.get<any>(this.url)
  }

}
