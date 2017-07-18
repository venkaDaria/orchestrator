"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var platform_browser_dynamic_1 = require("@angular/platform-browser-dynamic");
require("rxjs/add/operator/map");
var ContainerService = (function () {
    function ContainerService(http) {
        this.http = http;
    }
    ContainerService.prototype.getContainers = function () {
        return this.http.get('/container')
            .map(function (res) { return res.json(); });
    };
    return ContainerService;
}());
ContainerService = __decorate([
    core_1.Injectable()
], ContainerService);
exports.ContainerService = ContainerService;
var HomeComponent = (function () {
    function HomeComponent(containerService) {
        this.containerService = containerService;
        this.containers = {};
    }
    HomeComponent.prototype.loadContainers = function () {
        var _this = this;
        this.containerService.getContainers().subscribe(function (data) { return _this.containers = data; });
    };
    return HomeComponent;
}());
HomeComponent = __decorate([
    core_1.Component({
        selector: 'containers',
        template: '<div> <button (click)="loadContainers()">Load containers</button>{{ containers | json }} </div>'
    })
], HomeComponent);
exports.HomeComponent = HomeComponent;
platform_browser_dynamic_1.bootstrap(HomeComponent);
