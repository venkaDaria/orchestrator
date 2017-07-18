"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [0, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var testing_1 = require("@angular/http/testing");
var http_1 = require("@angular/http");
var router_1 = require("@angular/router");
var constants_1 = require("../util/constants");
var MockBackendService = (function () {
    function MockBackendService(backend, router) {
        this.backend = backend;
        this.router = router;
    }
    MockBackendService.prototype.start = function (ms) {
        console.log('MockBackendService start');
        var router = this.router;
        this.backend.connections.subscribe(callMock);
        function delay() {
            return new Promise(function (resolve) { return setTimeout(resolve, ms); });
        }
        function addErrorMessage(answer, msg) {
            answer[constants_1.ERROR_MESSAGE] = !answer[constants_1.ERROR_MESSAGE] ? msg : answer[constants_1.ERROR_MESSAGE] + '. ' + msg;
        }
        function callMock(c) {
            return __awaiter(this, void 0, void 0, function () {
                var parsedUrl, params, urlArray, url, answer;
                return __generator(this, function (_a) {
                    switch (_a.label) {
                        case 0: return [4 /*yield*/, delay()];
                        case 1:
                            _a.sent();
                            console.log('mockConnection URL:: ' + c.request.url);
                            parsedUrl = router.parseUrl(c.request.url);
                            params = parsedUrl.queryParams;
                            urlArray = parsedUrl.root.children.primary.segments;
                            url = urlArray[urlArray.length - 1].toString();
                            answer = {};
                            switch (url) {
                                case constants_1.STEP_1:
                                    if (!params.param1a || params.param1a.length < 7) {
                                        addErrorMessage(answer, 'Param1a must be at least 7');
                                    }
                                    if (!params.param1b || params.param1b.length < 6) {
                                        addErrorMessage(answer, 'Param1b must be at least 6');
                                    }
                                    break;
                                case constants_1.STEP_2:
                                    if (params.param2 !== 'hello') {
                                        addErrorMessage(answer, 'Param2 must be exactly "hello"');
                                    }
                                    break;
                                case constants_1.STEP_3:
                                    if (!params.param3 || params.param3.length < 5) {
                                        addErrorMessage(answer, 'Param3 must be at least 5');
                                    }
                                    if (!params.param3 || !params.param3.match('^\\d+$')) {
                                        addErrorMessage(answer, 'Param3 must contain only digits');
                                    }
                                    break;
                                case constants_1.STEP_4:
                                    if (!params.param4) {
                                        addErrorMessage(answer, 'Params must not be empty');
                                    }
                                    break;
                                default:
                                    addErrorMessage(answer, 'Request is incorrect');
                                    break;
                            }
                            console.log(JSON.stringify(answer));
                            c.mockRespond(new http_1.Response(new http_1.ResponseOptions({
                                body: JSON.stringify(answer)
                            })));
                            return [2 /*return*/];
                    }
                });
            });
        }
    };
    MockBackendService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [testing_1.MockBackend, router_1.Router])
    ], MockBackendService);
    return MockBackendService;
}());
exports.MockBackendService = MockBackendService;
//# sourceMappingURL=mock-backend.service.js.map