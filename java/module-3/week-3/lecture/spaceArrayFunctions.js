// Array of planets for the examples
const planets = [
    { name: "Mercury", distanceFromSun: 0.39 },
    { name: "Venus", distanceFromSun: 0.72 },
    { name: "Earth", distanceFromSun: 1.00 },
    { name: "Mars", distanceFromSun: 1.52 },
];

//Show arrays push, pop, etc.



// 1. forEach() - Iterates over each element in the array and performs an operation (logging in this case).
function printPlanets(){
    // Using forEach with an anonymous arrow function to log each planet's name
    //planets.forEach(planet => console.log(planet.name));

    // Named function for logging a planet's name
    function logPlanet(planet) {
        console.log(planet.name);
    }
    // Using forEach with a named function
    planets.forEach(logPlanet);
}


// 2. find() - Returns the first element that satisfies the provided testing function.
function findEarth(){
    // Using find with an anonymous arrow function to find Earth
    //const earth = planets.find(planet => planet.name === "Earth");
    //console.log(earth); // Outputs the Earth object

    // Named function to check if a planet is Earth
    function isEarth(planet) {
        return planet.name === "Earth";
    }
    // Using find with a named function
    const earth = planets.find(isEarth);
    console.log(earth); // Outputs the Earth object
}


// 3. findIndex() - Returns the index of the first element that satisfies the provided testing function.
function findIndexOfEarth(){
    // Using findIndex with an anonymous arrow function to find the index of Earth
    //const earthIndex = planets.findIndex(planet => planet.name === "Earth");
    //console.log(earthIndex); // Outputs the index of Earth

    // Named function to check if a planet is Earth
    function isEarth(planet) {
        return planet.name === "Earth";
    }
    // Using findIndex with a named function
    const earthIndex = planets.findIndex(isEarth);
    console.log(earthIndex); // Outputs the index of Earth

}



// TODO 1:
// 4. filter() - Creates a new array with all elements that pass the test implemented by the provided function.
function filterPlanets(){

// Using filter with an anonymous arrow function to find planets closer than 1.5 AU


// Named function to check if a planet is closer than 1.5 AU


// Using filter with a named function

}


// TODO 2:
// 5. map() - Creates a new array with all elements that pass the test implemented by the provided function.
function getPlanetNames(){

// Using map with an anonymous arrow function to get an array of planet names


// Named function to retrieve the name of a planet


// Using map with a named function

}



// TODO 3:
// 6. reduce() - Applies a function against an accumulator and each element in the array (from left to right) to reduce it to a single value.
function getCollectiveDistanceFromSun(){

// Using reduce with an anonymous arrow function to sum distances from the sun



// Named function to add a planet's distance to the total



// Using reduce with a named function

}


