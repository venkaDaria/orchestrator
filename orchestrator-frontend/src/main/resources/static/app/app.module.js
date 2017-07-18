"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var app_component_1 = require("./app.component");
var nav_component_1 = require("./page/nav.component");
var testing_1 = require("@angular/http/testing");
var wizard_service_1 = require("./service/wizard.service");
var http_1 = require("@angular/http");
var platform_browser_1 = require("@angular/platform-browser");
var app_routing_1 = require("./app.routing");
var mock_backend_service_1 = require("./backend/mock-backend.service");
var step_component_1 = require("./page/step.component");
var footer_component_1 = require("./page/footer.component");
var forms_1 = require("@angular/forms");
var base_component_1 = require("./page/base.component");
var security_service_1 = require("./service/security.service");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [platform_browser_1.BrowserModule, forms_1.FormsModule, app_routing_1.routing],
        declarations: [app_component_1.AppComponent, nav_component_1.NavComponent, base_component_1.BaseComponent, step_component_1.StepComponent1, footer_component_1.FooterComponent1,
            step_component_1.StepComponent2, footer_component_1.FooterComponent2, step_component_1.StepComponent3, footer_component_1.FooterComponent3,
            step_component_1.StepComponent4, footer_component_1.FooterComponent4, step_component_1.FinalStepComponent, footer_component_1.FinalFooterComponent
        ],
        providers: [
            wizard_service_1.StepValidationService1,
            wizard_service_1.StepValidationService2,
            wizard_service_1.StepValidationService3,
            wizard_service_1.StepValidationService4,
            security_service_1.AuthGuard,
            mock_backend_service_1.MockBackendService,
            testing_1.MockBackend,
            http_1.BaseRequestOptions,
            {
                provide: http_1.Http,
                deps: [testing_1.MockBackend, http_1.BaseRequestOptions],
                useFactory: function (backend, options) {
                    return new http_1.Http(backend, options);
                }
            }
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map