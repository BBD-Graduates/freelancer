import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root',
})
export class AuthGuardService {
  [x: string]: any;
  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (localStorage.getItem('userId') != null) {
      return true;
    } else {
      Swal.fire({
        title: 'Sorry for the inconvenience!',
        html: `
            <div>
              <p>You need to sign in with google to continue</p>
              <div id="google-signin-button"></div>
            </div>
          `,
        focusConfirm: false,
        confirmButtonText: '<i class="fa fa-thumbs-up"></i> OK',
        confirmButtonColor: '#14b8a6',
      });
      return false;
    }
  }
}
