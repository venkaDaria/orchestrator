import {Component, Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {
    StepValidationService1,
    StepValidationService2,
    StepValidationService3,
    StepValidationService4
} from "../service/wizard.service";
import {StepComponent} from "./base.step.component";
import {BaseComponent} from "./base.component";

@Component({
    templateUrl: 'templates/step/step1.html'
})
@Injectable()
export class StepComponent1 extends StepComponent {

    constructor(service: StepValidationService1, router: Router) {
        super(service, router);
    }
}

@Component({
    templateUrl: 'templates/step/step2.html'
})
@Injectable()
export class StepComponent2 extends StepComponent {

    constructor(service: StepValidationService2, router: Router) {
        super(service, router);
    }
}

@Component({
    templateUrl: 'templates/step/step3.html'
})
@Injectable()
export class StepComponent3 extends StepComponent {

    constructor(service: StepValidationService3, router: Router) {
        super(service, router);
    }
}

@Component({
    templateUrl: 'templates/step/step4.html'
})
@Injectable()
export class StepComponent4 extends StepComponent {

    constructor(service: StepValidationService4, router: Router) {
        super(service, router);
    }
}

@Component({
    templateUrl: 'templates/step/final.html'
})
export class FinalStepComponent extends BaseComponent {
}
