import {ComponentFixture, TestBed} from "@angular/core/testing";
import {FooterComponent1} from "../app/page/footer.component";
import {DebugElement} from "@angular/core";
import {By} from "@angular/platform-browser";
import {BrowserDynamicTestingModule, platformBrowserDynamicTesting} from "@angular/platform-browser-dynamic/testing";

describe('FooterComponent1 test', () => {
    let comp: FooterComponent1;
    let fixture: ComponentFixture<FooterComponent1>;
    let de: DebugElement;
    let el: HTMLElement;

    beforeEach((done: any) => {
        before().then(done).catch(error);

        function error(err: any) {
            console.log(err);
            done();
        }

        async function before() {
            await TestBed.configureTestingModule({
                declarations: [FooterComponent1],
            }).compileComponents();

            fixture = TestBed.createComponent(FooterComponent1);

            comp = fixture.componentInstance;

            de = fixture.debugElement.query(By.css('h1'));
            el = de.nativeElement;
        }
    });

    it('no title in the DOM until manually call `detectChanges`', () => expect(el.textContent).toEqual(''));

    it('should display original title', () => {
        fixture.detectChanges();
        expect(el.textContent).toContain(comp.title);
    });

    it('should display a different test title', () => {
        comp.title = 'Test Title';
        fixture.detectChanges();
        expect(el.textContent).toContain('Test Title');
    });
});