import { Observable } from 'rxjs';
import { Component } from '@angular/core';
import { TUser } from 'src/app/Models/TUser';
import { AuthService } from 'src/app/Services/auth/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  user : Observable<TUser>;
  constructor(private authService : AuthService){
    this.user = authService.authenticatedUser;
  }
  logout(){
    this.authService.logout();
  }
}
