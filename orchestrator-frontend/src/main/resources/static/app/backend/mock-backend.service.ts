import {Injectable} from "@angular/core";
import {MockBackend, MockConnection} from "@angular/http/testing";
import {Response, ResponseOptions} from "@angular/http";
import {Router, UrlSegment} from "@angular/router";
import {ERROR_MESSAGE, STEP_1, STEP_2, STEP_3, STEP_4} from "../util/constants";

@Injectable()
export class MockBackendService {
    constructor(private backend: MockBackend, private router: Router) {
    }

    start(ms: number) {
        console.log('MockBackendService start');

        let router = this.router;
        this.backend.connections.subscribe(callMock);

        function delay(): Promise<any> {
            return new Promise(resolve => setTimeout(resolve, ms));
        }

        function addErrorMessage(answer: {}, msg: string) {
            answer[ERROR_MESSAGE] = !answer[ERROR_MESSAGE] ? msg : answer[ERROR_MESSAGE] + '. ' + msg;
        }

        async function callMock(c: MockConnection) {
            await delay();

            console.log('mockConnection URL:: ' + c.request.url);

            const parsedUrl = router.parseUrl(c.request.url);

            const params = parsedUrl.queryParams;

            const urlArray: UrlSegment[] = parsedUrl.root.children.primary.segments;
            const url: string = urlArray[urlArray.length - 1].toString();

            let answer = {};

            switch (url) {
                case STEP_1:
                    if (!params.param1a || params.param1a.length < 7) {
                        addErrorMessage(answer, 'Param1a must be at least 7');
                    }
                    if (!params.param1b || params.param1b.length < 6) {
                        addErrorMessage(answer, 'Param1b must be at least 6');
                    }
                    break;
                case STEP_2:
                    if (params.param2 !== 'hello') {
                        addErrorMessage(answer, 'Param2 must be exactly "hello"');
                    }
                    break;
                case STEP_3:
                    if (!params.param3 || params.param3.length < 5) {
                        addErrorMessage(answer, 'Param3 must be at least 5');
                    }
                    if (!params.param3 || !params.param3.match('^\\d+$')) {
                        addErrorMessage(answer, 'Param3 must contain only digits');
                    }
                    break;
                case STEP_4:
                    if (!params.param4) {
                        addErrorMessage(answer, 'Params must not be empty');
                    }
                    break;
                default:
                    addErrorMessage(answer, 'Request is incorrect');
                    break;
            }

            console.log(JSON.stringify(answer));

            c.mockRespond(new Response(new ResponseOptions({
                body: JSON.stringify(answer)
            })));
        }
    }
}