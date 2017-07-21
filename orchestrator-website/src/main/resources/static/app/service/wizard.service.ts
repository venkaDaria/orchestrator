import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {MOCK_URL, STEP_1, STEP_2, STEP_3, STEP_4} from "../util/constants";

export abstract class ValidationService {
    constructor(private http: Http, private url: string) {
    }

    isValid(form: any): Promise<string> {
        return this.http.get(MOCK_URL + this.url, new RequestOptions({params: form}))
            .toPromise()
            .then((response: any) => response.json())
            .catch((err: any) => console.error(err));
    }
}

@Injectable()
export class StepValidationService1 extends ValidationService {

    constructor(http: Http) {
        super(http, STEP_1);
    }
}

@Injectable()
export class StepValidationService2 extends ValidationService {

    constructor(http: Http) {
        super(http, STEP_2);
    }
}

@Injectable()
export class StepValidationService3 extends ValidationService {

    constructor(http: Http) {
        super(http, STEP_3);
    }
}

@Injectable()
export class StepValidationService4 extends ValidationService {

    constructor(http: Http) {
        super(http, STEP_4);
    }
}