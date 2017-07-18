"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var wizard_service_1 = require("../service/wizard.service");
var base_step_component_1 = require("./base.step.component");
var base_component_1 = require("./base.component");
var StepComponent1 = (function (_super) {
    __extends(StepComponent1, _super);
    function StepComponent1(service, router) {
        return _super.call(this, service, router) || this;
    }
    return StepComponent1;
}(base_step_component_1.StepComponent));
StepComponent1 = __decorate([
    core_1.Component({
        templateUrl: 'templates/step/step1.html'
    }),
    core_1.Injectable(),
    __metadata("design:paramtypes", [wizard_service_1.StepValidationService1, router_1.Router])
], StepComponent1);
exports.StepComponent1 = StepComponent1;
var StepComponent2 = (function (_super) {
    __extends(StepComponent2, _super);
    function StepComponent2(service, router) {
        return _super.call(this, service, router) || this;
    }
    return StepComponent2;
}(base_step_component_1.StepComponent));
StepComponent2 = __decorate([
    core_1.Component({
        templateUrl: 'templates/step/step2.html'
    }),
    core_1.Injectable(),
    __metadata("design:paramtypes", [wizard_service_1.StepValidationService2, router_1.Router])
], StepComponent2);
exports.StepComponent2 = StepComponent2;
var StepComponent3 = (function (_super) {
    __extends(StepComponent3, _super);
    function StepComponent3(service, router) {
        return _super.call(this, service, router) || this;
    }
    return StepComponent3;
}(base_step_component_1.StepComponent));
StepComponent3 = __decorate([
    core_1.Component({
        templateUrl: 'templates/step/step3.html'
    }),
    core_1.Injectable(),
    __metadata("design:paramtypes", [wizard_service_1.StepValidationService3, router_1.Router])
], StepComponent3);
exports.StepComponent3 = StepComponent3;
var StepComponent4 = (function (_super) {
    __extends(StepComponent4, _super);
    function StepComponent4(service, router) {
        return _super.call(this, service, router) || this;
    }
    return StepComponent4;
}(base_step_component_1.StepComponent));
StepComponent4 = __decorate([
    core_1.Component({
        templateUrl: 'templates/step/step4.html'
    }),
    core_1.Injectable(),
    __metadata("design:paramtypes", [wizard_service_1.StepValidationService4, router_1.Router])
], StepComponent4);
exports.StepComponent4 = StepComponent4;
var FinalStepComponent = (function (_super) {
    __extends(FinalStepComponent, _super);
    function FinalStepComponent() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return FinalStepComponent;
}(base_component_1.BaseComponent));
FinalStepComponent = __decorate([
    core_1.Component({
        templateUrl: 'templates/step/final.html'
    })
], FinalStepComponent);
exports.FinalStepComponent = FinalStepComponent;
//# sourceMappingURL=step.component.js.map