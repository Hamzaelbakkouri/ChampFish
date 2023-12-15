import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, catchError, tap, throwError } from 'rxjs';
import { competition } from 'src/app/Models/Competition';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private url = 'http://localhost:8080/api/competition';
  constructor(private http: HttpClient) {
  }

  public paginate(page: number, size: number): Observable<competition> {
    return this.http.get<any>(this.url + "/paginate?page=" + page + "&size=" + size);
  }

  sendcompetitionData = new Subject<competition>();
  sendUpdatecompetitionData = new Subject<competition>();


  public save(competition: competition): Observable<competition> {
    return this.http.post<competition>(this.url, competition).pipe(
      tap((data: any) => {
        this.sendcompetitionData.next(data.competition);
      })
    );
  }

  public delete(id: number): Observable<competition> {
    return this.http.delete<competition>(this.url + "/" + id);
  }

  public getAll(): Observable<competition[]> {
    return this.http.get<competition[]>(this.url);
  }

}
