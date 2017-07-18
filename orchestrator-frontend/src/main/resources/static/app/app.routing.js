"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var footer_component_1 = require("./page/footer.component");
var step_component_1 = require("./page/step.component");
var nav_component_1 = require("./page/nav.component");
var base_component_1 = require("./page/base.component");
var security_service_1 = require("./service/security.service");
var appRoutes = [
    { path: '', redirectTo: 'step1', pathMatch: 'full' },
    {
        path: 'step1', children: [
            { path: '', component: nav_component_1.NavComponent, outlet: 'nav', pathMatch: 'full' },
            {
                path: '', component: base_component_1.BaseComponent, children: [
                    { path: '', component: step_component_1.StepComponent1, pathMatch: 'full' },
                ]
            },
            { path: '', component: footer_component_1.FooterComponent1, outlet: 'footer', pathMatch: 'full' },
        ]
    },
    {
        path: 'step2', canActivate: [security_service_1.AuthGuard], children: [
            { path: '', component: nav_component_1.NavComponent, outlet: 'nav', pathMatch: 'full' },
            {
                path: '', component: base_component_1.BaseComponent, children: [
                    { path: '', component: step_component_1.StepComponent2, pathMatch: 'full' },
                ]
            },
            { path: '', component: footer_component_1.FooterComponent2, outlet: 'footer', pathMatch: 'full' },
        ]
    },
    {
        path: 'step3', canActivate: [security_service_1.AuthGuard], children: [
            { path: '', component: nav_component_1.NavComponent, outlet: 'nav', pathMatch: 'full' },
            {
                path: '', component: base_component_1.BaseComponent, children: [
                    { path: '', component: step_component_1.StepComponent3, pathMatch: 'full' },
                ]
            },
            { path: '', component: footer_component_1.FooterComponent3, outlet: 'footer', pathMatch: 'full' },
        ]
    },
    {
        path: 'step4', canActivate: [security_service_1.AuthGuard], children: [
            { path: '', component: nav_component_1.NavComponent, outlet: 'nav', pathMatch: 'full' },
            {
                path: '', component: base_component_1.BaseComponent, children: [
                    { path: '', component: step_component_1.StepComponent4, pathMatch: 'full' },
                ]
            },
            { path: '', component: footer_component_1.FooterComponent4, outlet: 'footer', pathMatch: 'full' },
        ]
    },
    {
        path: 'final', canActivate: [security_service_1.AuthGuard], children: [
            { path: '', component: nav_component_1.NavComponent, outlet: 'nav', pathMatch: 'full' },
            {
                path: '', component: base_component_1.BaseComponent, children: [
                    { path: '', component: step_component_1.FinalStepComponent, pathMatch: 'full' },
                ]
            },
            { path: '', component: footer_component_1.FinalFooterComponent, outlet: 'footer', pathMatch: 'full' },
        ]
    },
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map