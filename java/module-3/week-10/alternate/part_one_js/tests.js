let should = chai.should();

describe('JS Assessment', () => {

    const testArray1 = ['c', 'm', 'B', 'a', 'A', 'x', 't'];
    const testArray2 = ['x', 'd', 'i', 'o', 'y', 'g', 'b'];

    describe('decodeMessage', () => {
        it("should return 'lemur' for (0, 1, ['l', 'e', 'm', 'u', 'r'])", () => {
            decodeMessage(0, 1, ['l', 'e', 'm', 'u', 'r']).should.equal("lemur");
        });
        it("should return 'cat' for (0, 3, ['c', 'm', 'B', 'a', 'A', 'x', 't'])", () => {
            decodeMessage(0, 3, ['c', 'm', 'B', 'a', 'A', 'x', 't']).should.equal("cat");
        });
        it("should return 'dog' for (1, 2, ['x','d', 'i', 'o', 'y', 'g', 'b'])", () => {
            decodeMessage(1, 2, ['x', 'd', 'i', 'o', 'y', 'g', 'b']).should.equal("dog");
        });
        it("should return an empty string for (7, 1, ['b', 'i', 'r', 'd'])", () => {
            decodeMessage(7, 1, ['b', 'i', 'r', 'd']).should.equal("");
        });

    });

    describe('getNumberOfCampsites', () => {

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
            { name: "Lake", distanceFromStart: 7.6, hasCamping: true },
        ];

        it('should handle an empty input array', () => {
            getNumberOfCampsites([]).should.equal(0);
        });
        it('should handle an array with two campsites', () => {
            getNumberOfCampsites(lakeTrailLocations).should.equal(2);
        });
        it('should handle an array with three campsites', () => {
            getNumberOfCampsites(fallsTrailLocations).should.equal(3);
        });
    });

    describe('calculateHikeDistance', () => {

        const locations = [
            { name: "Trailhead", distanceFromStart: 0.0, hasCamping: false },
            { name: "Holly", distanceFromStart: 4.2, hasCamping: true },
            { name: "Ivy", distanceFromStart: 11.4, hasCamping: true },
            { name: "Boulder", distanceFromStart: 15.0, hasCamping: false },
            { name: "Falls", distanceFromStart: 18.6, hasCamping: true },
        ];

        it('should report 0 distance for a hike that starts and finishes in the same location', () => {
            calculateHikeDistance('Trailhead', 'Trailhead', locations).should.equal(0.0);
        });
        it('should report 4.2 distance for Trailhead to Holly', () => {
            calculateHikeDistance ("Trailhead", "Holly", locations).should.equal(4.2);
        });
        it('should report 4.2 distance for Holly to Trailhead', () => {
            calculateHikeDistance ("Holly", "Trailhead", locations).should.equal(4.2);
        });
        it('should report 7.2 distance for Holly to Ivy', () => {
            calculateHikeDistance ("Holly", "Ivy", locations).should.equal(7.2);
        });
        it('should report 7.2 distance for Ivy to Holly', () => {
            calculateHikeDistance ("Ivy", "Holly", locations).should.equal(7.2);
        });
        it('should report 15 distance for Holly to Boulder', () => {
            calculateHikeDistance ("Holly", "Boulder", locations).should.equal(10.8);
        });
        it('should report 15 distance for Boulder to Holly', () => {
            calculateHikeDistance ("Boulder", "Holly", locations).should.equal(10.8);
        });
        it('should report -1 for invalid start point', () => {
            calculateHikeDistance ("Aspen", "Holly", locations).should.equal(-1);
        });
        it('should report -1 for invalid end point', () => {
            calculateHikeDistance ("Holly", "Aspen", locations).should.equal(-1);
        });
    });
});
