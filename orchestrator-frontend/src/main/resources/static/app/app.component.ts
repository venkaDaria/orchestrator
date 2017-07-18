import {Component, Injectable} from "@angular/core";
import {MockBackendService} from "./backend/mock-backend.service";

@Component({
    selector: 'app-component',
    templateUrl: 'templates/app.html'
})
@Injectable()
export class AppComponent {

    constructor(private mockBackendService: MockBackendService) {
        this.mockBackendService.start(1000);
    }
}