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
    planets.forEach((planet) => {console.log(planet.name)});

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
    const closePlanets = planets.filter((planet) => { return planet.distanceFromSun < 1.5});
    console.log(closePlanets);

    /*
    // Named function to check if a planet is closer than 1.5 AU
    function isClose(planet) {
        return planet.distanceFromSun < 1.5;
    }
    // Using filter with a named function
    const closePlanets = planets.filter(isClose);
    console.log(closePlanets); // Outputs planets closer than 1.5 AU
    */
}


// TODO 2:
// 5. map() - Creates a new array with all elements that pass the test implemented by the provided function.
function getPlanetNames(){

    // Using map with an anonymous arrow function to get an array of planet names
    const planetNames = planets.map((planet) => { return planet.name});
    console.log(planetNames); // Outputs an array of planet names

    /*
    // Named function to retrieve the name of a planet
    function getPlanetName(planet) {
        return planet.name;
    }
    // Using map with a named function
    const planetNames = planets.map(getPlanetName);
    console.log(planetNames); // Outputs an array of planet names

    */
}



// TODO 3:
// 6. reduce() - Applies a function against an accumulator and each element in the array (from left to right) to reduce it to a single value.
function getCollectiveDistanceFromSun(){

    // Using reduce with an anonymous arrow function to sum distances from the sun - shorthand syntax
    //const totalDistance = planets.reduce((total, planet) => total + planet.distanceFromSun, 0);
    
    // Using reduce with an anonymous arrow function to sum distances from the sun
    const totalDistance = planets.reduce((total, planet) => {
        return total + planet.distanceFromSun}, 0
    );
    
    console.log(totalDistance); // Outputs the sum of distances

    /*
    // Named function to add a planet's distance to the total
    function sumDistance(total, planet) {
        return total + planet.distanceFromSun;
    }
    // Using reduce with a named function
    const totalDistance = planets.reduce(sumDistance, 0);
    console.log(totalDistance); // Outputs the sum of distances
    */

}


