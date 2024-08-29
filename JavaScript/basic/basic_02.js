//function 01
function  print(s) {
    console.log(s);
}

//function 02
var print1 = function (s) {
    console.log(s);
}

//function 03
var add = new Function(
    'x',
    'y',
    'return x + y'
);

//等同于
function add(x, y) {
    return x + y;
}

//name属性
var myFunc = function () {
    
}

function test(f) {
    console.log(f.name);
}

test(myFunc);

//length属性
function f(a, b) {
    
}

console.log(f.length);

//toString方法
function f() {
    a();
    b();
    c();
}

console.log(f.toString());
