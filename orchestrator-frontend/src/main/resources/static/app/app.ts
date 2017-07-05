import { Component } from '@angular/core';
import { bootstrap } from '@angular/platform-browser-dynamic';

@Component({selector: 'greeting', template: 'Hello, {{name}}!'})
class AppComponent {
    name: string = 'World';
}

bootstrap(AppComponent);