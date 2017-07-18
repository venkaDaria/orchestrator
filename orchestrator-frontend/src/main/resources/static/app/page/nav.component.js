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
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var base_component_1 = require("./base.component");
var session_1 = require("../util/session");
var constants_1 = require("../util/constants");
var NavComponent = (function (_super) {
    __extends(NavComponent, _super);
    function NavComponent() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.steps = Array.from({ length: constants_1.STEPS.length }, function (v, k) { return k; });
        return _this;
    }
    NavComponent.prototype.urlEquals = function (idx) {
        return this.router.url === constants_1.STEPS[idx];
    };
    NavComponent.prototype.isDisabled = function (idx) {
        return idx != 0 && !this.isValid(idx - 1);
    };
    NavComponent.prototype.isSuccess = function (idx) {
        return idx < constants_1.STEPS.length - 1 && this.isValid(idx);
    };
    NavComponent.prototype.isValid = function (idx) {
        return session_1.Session.get(constants_1.STEPS[idx]);
    };
    NavComponent.prototype.isFinal = function (idx) {
        return idx == constants_1.STEPS.length - 1;
    };
    return NavComponent;
}(base_component_1.BaseComponent));
NavComponent = __decorate([
    core_1.Component({
        templateUrl: 'templates/page/nav.html'
    })
], NavComponent);
exports.NavComponent = NavComponent;
//# sourceMappingURL=nav.component.js.map