/*
    Write the function decodeMessage() that takes in two numbers and an array of characters.
    The first number, start, indicates the starting position in the array.
    The second number, step, indicates how many positions forward to move in the array.
    
    The function returns a string that is concatenated from every n-th character in the array based on
    the two number parameters.

    EXAMPLES:
    decodeMessage(0, 1, ['l', 'e', 'm', 'u', 'r']) --> "lemur"
    decodeMessage(0, 3, ['c', 'm', 'B', 'a', 'A', 'x', 't']) --> "cat"
    decodeMessage(1, 2, ['x','d', 'i', 'o', 'y', 'g', 'b']) --> "dog"
    decodeMessage(7, 1, ['b', 'i', 'r', 'd']) --> ""
*/

function decodeMessage(start, step, message) {

}

/* 
    Working for the National Park Service, you are tasked with providing digital information
    about trail systems in the national parks. Trail information is stored with an array of Location 
    objects, each of which stores the name of the location, its distance from the trail's origin,
    and whether the location offers camping.
    
    An individual Location object looks like this:
    
    {
        name: 'Holly',
        distanceFromStart: 4.2, 
        hasCamping: true
    }
    
    Write the function getNumberOfCampsites(), which takes an array of Location objects
    and returns the number of locations that allow camping.

    EXAMPLE: 

    const fallsTrailLocations = [
        { name: "Trailhead", distanceFromStart: 0.0, hasCamping: false },
        { name: "Holly", distanceFromStart: 4.2, hasCamping: true },
        { name: "Ivy", distanceFromStart: 11.4, hasCamping: true },
        { name: "Boulder", distanceFromStart: 15.0, hasCamping: false },
        { name: "Falls", distanceFromStart: 18.6, hasCamping: true }
    ];

    const lakeTrailLocations = [
        { name: "Ranger Station", distanceFromStart: 0.0, hasCamping: true },
        { name: "Jack's Mill", distanceFromStart: 2.7, hasCamping: false },
        { name: "Lake", distanceFromStart: 7.6, hasCamping: true }
    ];

    getNumberOfCampsites(fallsTrailLocations) --> 3
    getNumberOfCampsites(lakeTrailLocations) --> 2 
*/

function getNumberOfCampsites(locations) {

}

/*
    Write the function calculateHikeDistance(), which accepts
    a string indicating the starting point of the hike,
    a string indicating the end point of the hike, 
    and an array of Location objects.

    calculateHikeDistance() returns the distance covered on the hike.

    The distance returned is positive irrespective of which direction the hikers are moving.

    If either the starting point or the end point are not found in the
    array of locations, the function returns -1 (indicating an error).

    EXAMPLES:

    const fallsTrailLocations = [
        { name: "Trailhead", distanceFromStart: 0.0, hasCamping: false },
        { name: "Holly", distanceFromStart: 4.2, hasCamping: true },
        { name: "Ivy", distanceFromStart: 11.4, hasCamping: true },
        { name: "Boulder", distanceFromStart: 15.0, hasCamping: false },
        { name: "Falls", distanceFromStart: 18.6, hasCamping: true }
    ];

    calculateHikeDistance("Trailhead", "Ivy", fallsTrailLocations) => 11.4
    calculateHikeDistance("Ivy", "Trailhead", fallsTrailLocations) => 11.4
    calculateHikeDistance("Holly", "Ivy", fallsTrailLocations) => 7.2
    calculateHikeDistance("Ivy", "Holly", fallsTrailLocations) => 7.2
    calculateHikeDistance("Aspen", "Ivy", fallsTrailLocations) => -1.0
*/

function calculateHikeDistance(start, end, locations) {

}
