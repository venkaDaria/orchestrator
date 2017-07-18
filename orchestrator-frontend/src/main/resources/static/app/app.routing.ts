import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {
    FinalFooterComponent,
    FooterComponent1,
    FooterComponent2,
    FooterComponent3,
    FooterComponent4
} from "./page/footer.component";
import {
    FinalStepComponent,
    StepComponent1,
    StepComponent2,
    StepComponent3,
    StepComponent4
} from "./page/step.component";
import {NavComponent} from "./page/nav.component";
import {BaseComponent} from "./page/base.component";
import {AuthGuard} from "./service/security.service";

const appRoutes: Routes = [
    {path: '', redirectTo: 'step1', pathMatch: 'full'},

    {
        path: 'step1', children: [
        {path: '', component: NavComponent, outlet: 'nav', pathMatch: 'full'},
        {
            path: '', component: BaseComponent, children: [
            {path: '', component: StepComponent1, pathMatch: 'full'},
        ]
        },
        {path: '', component: FooterComponent1, outlet: 'footer', pathMatch: 'full'},
    ]
    },

    {
        path: 'step2', canActivate: [AuthGuard], children: [
        {path: '', component: NavComponent, outlet: 'nav', pathMatch: 'full'},
        {
            path: '', component: BaseComponent, children: [
            {path: '', component: StepComponent2, pathMatch: 'full'},
        ]
        },
        {path: '', component: FooterComponent2, outlet: 'footer', pathMatch: 'full'},
    ]
    },

    {
        path: 'step3', canActivate: [AuthGuard], children: [
        {path: '', component: NavComponent, outlet: 'nav', pathMatch: 'full'},
        {
            path: '', component: BaseComponent, children: [
            {path: '', component: StepComponent3, pathMatch: 'full'},
        ]
        },
        {path: '', component: FooterComponent3, outlet: 'footer', pathMatch: 'full'},
    ]
    },

    {
        path: 'step4', canActivate: [AuthGuard], children: [
        {path: '', component: NavComponent, outlet: 'nav', pathMatch: 'full'},
        {
            path: '', component: BaseComponent, children: [
            {path: '', component: StepComponent4, pathMatch: 'full'},
        ]
        },
        {path: '', component: FooterComponent4, outlet: 'footer', pathMatch: 'full'},
    ]
    },

    {
        path: 'final', canActivate: [AuthGuard], children: [
        {path: '', component: NavComponent, outlet: 'nav', pathMatch: 'full'},
        {
            path: '', component: BaseComponent, children: [
            {path: '', component: FinalStepComponent, pathMatch: 'full'},
        ]
        },
        {path: '', component: FinalFooterComponent, outlet: 'footer', pathMatch: 'full'},
    ]
    },

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);