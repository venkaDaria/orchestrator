var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { Component } from '@angular/core';
import { bootstrap } from '@angular/platform-browser-dynamic';
var AppComponent = (function () {
    function AppComponent() {
        this.name = 'World';
    }
    return AppComponent;
}());
AppComponent = __decorate([
    Component({ selector: 'greeting', template: 'Hello, {{name}}!' })
], AppComponent);
bootstrap(AppComponent);
//# sourceMappingURL=/Users/dariapydorenko/Documents/workspace/orchestrator/orchestrator-frontend/src/main/resources/static/app.js.map