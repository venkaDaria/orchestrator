import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {NavComponent} from "./page/nav.component";
import {MockBackend} from "@angular/http/testing";
import {
    StepValidationService1,
    StepValidationService2,
    StepValidationService3,
    StepValidationService4
} from "./service/wizard.service";
import {BaseRequestOptions, Http} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {routing} from "./app.routing";
import {MockBackendService} from "./backend/mock-backend.service";
import {
    FinalStepComponent,
    StepComponent1,
    StepComponent2,
    StepComponent3,
    StepComponent4
} from "./page/step.component";
import {
    FinalFooterComponent,
    FooterComponent1,
    FooterComponent2,
    FooterComponent3,
    FooterComponent4
} from "./page/footer.component";
import {FormsModule} from "@angular/forms";
import {BaseComponent} from "./page/base.component";
import {AuthGuard} from "./service/security.service";

@NgModule({
    imports: [BrowserModule, FormsModule, routing],
    declarations: [AppComponent, NavComponent, BaseComponent, StepComponent1, FooterComponent1,
        StepComponent2, FooterComponent2, StepComponent3, FooterComponent3,
        StepComponent4, FooterComponent4, FinalStepComponent, FinalFooterComponent
    ],
    providers: [
        StepValidationService1,
        StepValidationService2,
        StepValidationService3,
        StepValidationService4,

        AuthGuard,

        MockBackendService,
        MockBackend,
        BaseRequestOptions,
        {
            provide: Http,
            deps: [MockBackend, BaseRequestOptions],
            useFactory: (backend: MockBackend, options: BaseRequestOptions) => {
                return new Http(backend, options);
            }
        }],
    bootstrap: [AppComponent]
})
export class AppModule {
}