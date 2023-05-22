// import { Injectable, inject } from '@angular/core';
// import {
//   ActivatedRouteSnapshot,
//   CanActivateChildFn,
//   CanActivateFn,
//   Router,
//   RouterStateSnapshot,
// } from '@angular/router';

// export const canActivate: CanActivateFn = (
//   route: ActivatedRouteSnapshot,
//   state: RouterStateSnapshot
// ) => {
//   const router = inject(Router);
//   console.log('canactivate');
//   if (sessionStorage.getItem('userEmail') == null) {
//     router.navigate(['/home']);
//     console.log('canactivate/home');
//     return true;
//   } else {
//     router.navigate(['/home/dashboard']);
//     console.log('canactivate/home/dashboard ');
//     return false;
//   }
// };

// export const canActivateChild: CanActivateChildFn = (
//   route: ActivatedRouteSnapshot,
//   state: RouterStateSnapshot
// ) => canActivate(route, state);
