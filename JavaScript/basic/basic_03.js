//test closure
function f1() {
    var n = 99;
    function  f2() {
        console.log(n);
    }
}

function createIncrementor(start) {
    return function () {
        return start++;
    };
}

var inc = createIncrementor(5);
console.log(inc());
console.log(inc());
console.log(inc());


//test private property and private function
function Person(name) {
    var age;
    function setAge(n) {
        age = n;
    }
    function getAge() {
        return age;
    }
    return {
        name : name,
        getAge : getAge,
        setAge : setAge
    };
}


var p1 = Person('张三');
p1.setAge(25);
console.log(p1.getAge());
console.log('1213131');