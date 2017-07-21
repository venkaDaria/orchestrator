export class Session {

    static set(key: string, value: any) {
        window.sessionStorage.setItem(key, JSON.stringify(value));
    }

    static get(key: string): any {
        return Session.has(key) ? JSON.parse(window.sessionStorage[key]) : null;
    }

    static has(key: string): boolean {
        return !!window.sessionStorage[key];
    }

    static remove(key: string) {
        window.sessionStorage.removeItem(key);
    }

    static clear() {
        window.sessionStorage.clear();
    }

    static getAll() {
        return window.sessionStorage;
    }
}