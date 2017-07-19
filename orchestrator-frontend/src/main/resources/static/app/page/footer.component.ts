import {Component} from "@angular/core";

@Component({
    template: '<h1>{{title}}</h1>'
})
export class FooterComponent1 {
    title = "I am Step1"
}

@Component({
    templateUrl: 'templates/page/footer.html'
})
export class FooterComponent2 {
    title = "I am Step2"
}

@Component({
    templateUrl: 'templates/page/footer.html'
})
export class FooterComponent3 {
    title = "I am Step3"
}

@Component({
    templateUrl: 'templates/page/footer.html'
})
export class FooterComponent4 {
    title = "I am Step4"
}

@Component({
    templateUrl: 'templates/page/footer.html'
})
export class FinalFooterComponent {
    title = "Successful"
}