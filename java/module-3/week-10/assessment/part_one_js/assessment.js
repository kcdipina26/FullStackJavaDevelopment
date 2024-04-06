/**
 * Write a function that takes in a string and an array of values, 
 * and returns a count of values in the array that exactly match the string.
 * 
 * Example 1: filteredCount('A', ['A', 0, 'B', 'A', 'A']) --> 3 ('A' is found 3 times)
 * Example 2: filteredCount('A', ['a', '1', 'B', 1]) --> 0 ('A' is not found - case sensitive)
 * Example 3: filteredCount('1', ['a', '1', 'B', 1]) --> 1 ('1' is found once as a string)
 * Example 4: filteredCount('A', []) --> 0
 */
function filteredCount(matchStr, arr) {
    // Initialized a counter
    let count = 0;

    // Looping through the array
    for (let i = 0; i < arr.length; i++) {
        // Check if the current array element exactly matches the  given matchStr
        if (arr[i] === matchStr) {
            // Incrementing the counter for a match
            count++;
        }
    }

    // Returned the final count
    return count;
}


/**
 * Given a state code and an array of order objects, return a count of the orders 
 * for that state that have a status of 'In-Progress'.
 * 
 * Order objects have properties for state, status, weight, and cost.
 */
 function inProgressOrders(stateCode, orderArray) {
   
        // Initialized a counter
        let count = 0;
    
        // Looping through the array of orders
        for (let i = 0; i < orderArray.length; i++) {
            
            
            // Checking if the current order's state matches stateCode and the status is 'In-Progress'


            if (orderArray[i].state === stateCode && orderArray[i].status === 'In-Progress') {
                // Incrementing the counter if both of given conditions are met

                count++;
            }
        }
    
        // Returned the final count
        return count;
    }
    

/**
 * Given a state code and an array of order objects, identify the number of orders
 * shipped to that state, the total weight, and the total sales amount from those
 * orders. Only consider orders with a status of 'Delivered' or 'Shipped'.
 * 
 * Order objects have properties for state, status, weight, and cost.
 * 
 * Return an object with properties for count, weight, and sales. 
 *   For example: {
 *     count: 5,
 *     weight: 42,
 *     sales: 29.50
 *   }
 */
function orderVolume(stateCode, orderArray) {

    //initialize //

    let count = 0;
    let totalWeight = 0;
    let totalSales = 0;

    //looping through the array of orders
    for (let i = 0; i < orderArray.length; i++) {

        //check to see if looking to add for shipped or delivered //
        if (orderArray[i].state === stateCode && (orderArray[i].status === 'Delivered' || orderArray[i].status === 'Shipped')) {
            count++;   //incrementing//
            //same here adding to total weight and total sales//
            totalWeight += orderArray[i].weight;  
            totalSales += orderArray[i].cost;
        }
    }
    //we need to make sure we returning as an object// 
    return { count, weight: totalWeight, sales: totalSales };
}