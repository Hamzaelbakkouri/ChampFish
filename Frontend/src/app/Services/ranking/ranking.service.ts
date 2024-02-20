import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, tap } from 'rxjs';
import { ranking } from 'src/app/Models/Ranking';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private url = 'http://localhost:8080/api/ranking';
  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<ranking> {
    return this.http.get<any>(this.url);
  }

  sendrankingData = new Subject<ranking>();

  public save(ranking: ranking): Observable<ranking> {
    return this.http.post<ranking>(this.url, ranking).pipe(
      tap((data: any) => {
        this.sendrankingData.next(data.ranking);
      })
    );
  }

  public delete(id: number): Observable<ranking> {
    return this.http.delete<ranking>(this.url + "/" + id);
  }

  public getAll(): Observable<ranking[]> {
    return this.http.get<ranking[]>(this.url);
  }

  public getRapport(competitionCode: string): Observable<ranking[]> {
    return this.http.get<ranking[]>(this.url + `/rapport/${competitionCode}`);
  }

  public Count(competitionCode: string): Observable<ranking[]> {
    return this.http.get<any[]>(this.url + `/done/${competitionCode}`);
  }

}
