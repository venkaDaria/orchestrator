import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Session} from "../util/session";
import {LOADING, STEPS} from "../util/constants";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let prevStep = STEPS[STEPS.indexOf(state.url) - 1];

        if (Session.get(prevStep) || Session.get(LOADING)) {
            return true;
        }

        this.router.navigateByUrl(this.router.url)
            .then((success: any) => console.log('Access denied'))
            .catch((err: any) => console.error(err));

        return false;
    }
}