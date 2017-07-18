import {Component} from "@angular/core";
import {BaseComponent} from "./base.component";
import {Session} from "../util/session";
import {STEPS} from "../util/constants";

@Component({
    templateUrl: 'templates/page/nav.html'
})
export class NavComponent extends BaseComponent {

    private steps = Array.from({length: STEPS.length}, (v, k) => k);

    urlEquals(idx: number): boolean {
        return this.router.url === STEPS[idx];
    }

    isDisabled(idx: number) {
        return idx != 0 && !this.isValid(idx - 1);
    }

    isSuccess(idx: number) {
        return idx < STEPS.length - 1 && this.isValid(idx);
    }

    isValid(idx: number): boolean {
        return Session.get(STEPS[idx]);
    }

    isFinal(idx: number): boolean {
        return idx == STEPS.length - 1;
    }
}