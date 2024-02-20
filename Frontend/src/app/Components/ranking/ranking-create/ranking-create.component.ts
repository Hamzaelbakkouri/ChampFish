import { Component, EventEmitter, Output, OnInit, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Member } from 'src/app/Models/Member';
import { ranking } from 'src/app/Models/Ranking';
import { MemberService } from 'src/app/Services/Member/member.service';
import { RankingService } from 'src/app/Services/ranking/ranking.service';


interface AutoCompleteCompleteEvent {
  originalEvent: Event;
  query: string;
}

@Component({
  selector: 'app-ranking-create',
  templateUrl: './ranking-create.component.html',
  styleUrls: ['./ranking-create.component.css']
})
export class RankingCreateComponent implements OnInit {
  @Output() closeRankingPopup: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() CompetitionCode: string = '';
  Members: any[] = []
  constructor(private MemberService: MemberService, private rankingService: RankingService, private snackBar: MatSnackBar) { }

  checkisClosed() {
    this.closeRankingPopup.emit(false);
  }

  ngOnInit(): void {
    this.MemberService.getAllMember().subscribe((data: any) => {
      this.Members.push(...data.Members)
      console.log(this.Members);
    })
  }

 

  formdate: ranking = {
    id: {
      competition_code: '',
      member_num: 0
    },
    rank: 0,
    score: 0
  }

  onSubmit(id: number) {
    this.formdate.id.competition_code = this.CompetitionCode
    this.formdate.id.member_num = id
    this.rankingService.save(this.formdate).subscribe((data) => {
      this.snackBar.open('Ranking created successfully', 'OK')
    })
  }
}
