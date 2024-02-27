import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Member } from 'src/app/Models/Member';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/member"
  public members = new BehaviorSubject<Member[]>([]);

  getAllMember(): Observable<Member[]> {
    return this.http.get<any>(this.url)
  }


  update(id: number, role: string) {
    this.http.put<Member>(this.url + `/role/${id}`, role).subscribe(
      (response: any) => {
        this.members.next(response.member);
      }
    );
  }
}
