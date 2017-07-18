"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Session = (function () {
    function Session() {
    }
    Session.set = function (key, value) {
        window.sessionStorage.setItem(key, JSON.stringify(value));
    };
    Session.get = function (key) {
        return Session.has(key) ? JSON.parse(window.sessionStorage[key]) : null;
    };
    Session.has = function (key) {
        return !!window.sessionStorage[key];
    };
    Session.remove = function (key) {
        window.sessionStorage.removeItem(key);
    };
    Session.clear = function () {
        window.sessionStorage.clear();
    };
    Session.getAll = function () {
        return window.sessionStorage;
    };
    return Session;
}());
exports.Session = Session;
//# sourceMappingURL=session.js.map