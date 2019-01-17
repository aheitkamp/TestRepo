export {}
let message = 'welcome back';
console.log(message);

let x = 10;
const y = 20
console.log(x + y);
x = 60;
console.log(x + y);

let isBeginner: boolean = true;
let total: number = 0;

console.log(++total);

let name: boolean = true;

console.log(name);

let isNew: boolean = null;

let list1: number[] = [1, 2, 4];
console.log(list1[1]);

enum Color {Red = 5, Green, Blue};

let c: Color = Color.Green;
console.log(Color.Red);

let a = 10;

let multiType: number | boolean;
multiType = 10;

multiType = false;

console.log(multiType.toString());

function add(num1: number , num2: number = 88): number {
    return num1 + num2;
}

let xx: number= add(2,3);
console.log(xx);
console.log(add(3));

let logger = console.log;



let p = {firstName: 'Al', lastName: 'Heitkamp'};
let p1 = {firstName: 'Al'};

interface Person {
    firstName: string;
    lastName?: string;
}

function fullName(person: Person) {
    console.log(`${person.firstName} ${person.lastName}`)
    }


fullName(p1);

class Employee {
    protected employeeName: string;
    
    constructor(name: string) {
        this.employeeName = name;
    }
    
    greet() {
        console.log(`Good morning ${this.employeeName}`);
        }
    }

let emp1 = new Employee('Sarah');
emp1.greet();

class Manager extends Employee {
    constructor(managerName: string) {
        super(managerName);
    }
    
    delegateWork() {
        console.log(`manager delegating work - ${this.employeeName}`);
    }
}

let man1 = new Manager('Al H');
man1.greet();
man1.delegateWork();
