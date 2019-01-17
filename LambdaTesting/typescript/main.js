"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
exports.__esModule = true;
var message = 'welcome back';
console.log(message);
var x = 10;
var y = 20;
console.log(x + y);
x = 60;
console.log(x + y);
var isBeginner = true;
var total = 0;
console.log(++total);
var name = true;
console.log(name);
var isNew = null;
var list1 = [1, 2, 4];
console.log(list1[1]);
var Color;
(function (Color) {
    Color[Color["Red"] = 5] = "Red";
    Color[Color["Green"] = 6] = "Green";
    Color[Color["Blue"] = 7] = "Blue";
})(Color || (Color = {}));
;
var c = Color.Green;
console.log(Color.Red);
var a = 10;
var multiType;
multiType = 10;
multiType = false;
console.log(multiType.toString());
function add(num1, num2) {
    if (num2 === void 0) { num2 = 88; }
    return num1 + num2;
}
var xx = add(2, 3);
console.log(xx);
console.log(add(3));
var logger = console.log;
var p = { firstName: 'Al', lastName: 'Heitkamp' };
var p1 = { firstName: 'Al' };
function fullName(person) {
    console.log(person.firstName + " " + person.lastName);
}
fullName(p1);
var Employee = /** @class */ (function () {
    function Employee(name) {
        this.employeeName = name;
    }
    Employee.prototype.greet = function () {
        console.log("Good morning " + this.employeeName);
    };
    return Employee;
}());
var emp1 = new Employee('Sarah');
emp1.greet();
var Manager = /** @class */ (function (_super) {
    __extends(Manager, _super);
    function Manager(managerName) {
        return _super.call(this, managerName) || this;
    }
    Manager.prototype.delegateWork = function () {
        console.log("manager delegating work - " + this.employeeName);
    };
    return Manager;
}(Employee));
var man1 = new Manager('Al H');
man1.greet();
man1.delegateWork();
