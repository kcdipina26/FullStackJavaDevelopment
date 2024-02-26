/*
    Example of a multi-line comment just like in C#/Java
*/

// Single line comment

/**
 * Functions start with the word function.
 * They don't have a return type and the naming convention is camel-case.
 */
 function variables() {
    // Declares a variable where the value cannot be changed
    const daysPerWeek = 7;
    console.log(`There are ${daysPerWeek} days in the week.`);
  
    // Declares a variable whose value can be changed
    let daysPerMonth = 30;
    console.log(`There are ${daysPerMonth} days in the month.`);
  
    // Declares a variable that will always be an array
    const weekdays = [
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
      "Sunday"
    ];
    console.table(weekdays);
  }


  function arrayAddRemoveElements(){
    //Arrays
    //The push() method adds one or more elements to the end of an array and returns the new length of the array.
    // Initialize an array of planets
    let planets = ['Mercury', 'Venus', 'Earth'];

    // Adding a single element to the array
    planets.push('Mars');
    console.log('After push (single element):', planets);

    // Adding multiple elements to the array
    planets.push('Jupiter', 'Saturn');
    console.log('After push (multiple elements):', planets);


    // The pop() method removes the last element from an array and returns that element. This method changes the length of the array.
    // Continue with the current array
    console.log('Initial array:', planets);

    // Removing the last element from the array
    let removedPlanet = planets.pop();
    console.log('After pop:', planets);
    console.log('Removed planet:', removedPlanet);


    // The shift() method removes the first element from the array. In our planetary example, let's remove the first planet from the list.
    // Removing the first planet from the array
    let removedPlanet = planets.shift();
    console.log('After shift (removing first planet):', planets);
    console.log('Removed planet:', removedPlanet);


    //The unshift() method adds one or more elements to the beginning of the array. We'll add a couple of planets to the beginning of our planets array.
    // Continue with the current array of planets
    console.log('Initial array:', planets);

    // Adding a single planet to the beginning of the array
    planets.unshift('Neptune');
    console.log('After unshift (adding one planet):', planets);

    // Adding multiple planets to the beginning of the array
    planets.unshift('Galileo', 'Ceres');
    console.log('After unshift (adding multiple planets):', planets);



  }

  
  /**
   * Functions can also accept parameters.
   * Notice the parameters do not have types. 
   * Note: The types shown in these docs is informational only and not enforced. 
   * @param {Number} param1 The first number to display
   * @param {Number} param2 The second number to display
   */
  function printParameters(param1, param2) {
    console.log(`The value of param1 is ${param1} and its type is ${typeof param1}`);
    console.log(`The value of param2 is ${param2} and its type is ${typeof param2}`);
  }
  
  /**
   * Compares two values x and y.
   * == is loose equality
   * === is strict equality
   * @param {Object} x
   * @param {Object} y
   */
  function equality(x, y) {
    console.log(`x is ${typeof x}`);
    console.log(`y is ${typeof y}`);
  
    console.log(`x == y : ${x == y}`); // true
    console.log(`x === y : ${x === y}`); // false
  }
  
  /**
   * Each value is inherently truthy or falsy.
   * false, 0, '', null, undefined, and NaN are always falsy
   * everything else is always truthy
   * @param {Object} x The object to check for truthy or falsy,
   */
  function falsy(x) {
    if (x) {
      console.log(`${x} is truthy`);
    } else {
      console.log(`${x} is falsy`);
    }
  }
  
  /**
   *  Objects are simple key-value pairs
      - values can be primitive data types
      - values can be arrays
      - or they can be functions
  */
  function objects() {
    const person = {
      firstName: "Bill",
      lastName: "Lumbergh",
      age: 42,
      employees: [
        "Peter Gibbons",
        "Milton Waddams",
        "Samir Nagheenanajar",
        "Michael Bolton"
      ],
      toString: function() {
        return `${this.lastName}, ${this.firstName} (${this.age})`;
      }
    };
  
    console.table(person);
  
    console.log(`${person.firstName} ${person.lastName}`);
  
    for (let i = 0; i < person.employees.length; i++) {
      console.log(`Employee ${i + 1} is ${person.employees[i]}`);
    }
  
    console.log(person.toString());
  }
  
  /*
  ########################
  Function Overloading
  ########################
  
  Function Overloading is not available in Javascript. If you declare a
  function with the same name, more than one time in a script file, the
  earlier ones are overriden and the most recent one will be used.
  */
  
  function add(num1, num2) {
    return num1 + num2;
  }
  
  function add(num1, num2, num3) {
    return num1 + num2 + num3;
  }
  
  /*
  ########################
  Math Library
  ########################
  
  A built-in `Math` object has properties and methods for mathematical constants and functions.
  */
  
  function mathFunctions() {
    console.log("Math.PI : " + Math.PI);
    console.log("Math.LOG10E : " + Math.LOG10E);
    console.log("Math.abs(-10) : " + Math.abs(-10));
    console.log("Math.floor(1.99) : " + Math.floor(1.99));
    console.log("Math.ceil(1.01) : " + Math.ceil(1.01));
    console.log("Math.random() : " + Math.random());
  }
  
  /*
  ########################
  String Methods
  ########################
  
  The string data type has a lot of properties and methods similar to strings in Java/C#
  */
  
  function stringFunctions(value) {
    console.log(`.length -  ${value.length}`);
    console.log(`.endsWith('World') - ${value.endsWith("World")}`);
    console.log(`.startsWith('Hello') - ${value.startsWith("Hello")}`);
    console.log(`.indexOf('Hello') - ${value.indexOf("Hello")}`);
  
       /*
      Some Other Methods
          - split(string)
          - substr(number, number)
          - substring(number, number)
          - toLowerCase()
          - trim()
          - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String
      */
    


 
  }
  