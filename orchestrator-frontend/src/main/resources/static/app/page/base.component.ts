import {Session} from "../util/session";
import {Router} from "@angular/router";
import {Component, Injectable} from "@angular/core";
import {ERROR_MESSAGE, LOADING, STEPS} from "../util/constants";

@Component({
    templateUrl: 'templates/page/main.html'
})
@Injectable()
export class BaseComponent {
    constructor(protected router: Router) {
    }

    protected go(idx: number, asyncCall: () => Promise<any>) {
        Session.set(LOADING, true);
        Session.remove(ERROR_MESSAGE);

        asyncCall()
            .then(() => {
                console.log('Try to go to ' + STEPS[idx]);
                Session.remove(LOADING);
            })
            .catch((err: any) => console.error(err));
    }

    goTo(idx: number) {
        this.go(idx, async () => await this.router.navigateByUrl(STEPS[idx]));
    }

    hasValue(key: string): boolean {
        return Session.has(key);
    }

    getValue(key: string): string {
        return Session.get(key);
    }

    clear() {
        Session.clear();

        this.router.navigateByUrl('/')
            .then((success: any) => console.log('Go to first page'))
            .catch((err: any) => console.error(err));
    }
}