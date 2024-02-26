// Add code here
//TODO 1: assign properties for players 1 and 2
const player1 = {name: 'Player 1', className: 'p1'};

const player2 = {name: 'Player 2', className: 'p2'};


//TODO 2: assign values for remaining scoring sets 
//Scoring Sets
const row1 = [1,2,3];
const row2 = [4,5,6];
const row3 = [7,8,9];
const col1 = [1,4,7];
const col2 = [2,5,8];
const col3 = [3,6,9];
const dia1 = [1,5,9];
const dia2 = [3,5,7];

//TODO 3: assign the scoringSets values
const scoringSets = [row1, row2, row3, col1, col2, col3, dia1, dia2];

let currentPlayer = player1;

let gameOver = false;

//'DOMContentCheck
document.addEventListener('DOMContentLoaded', attachListeners);  //calling a named function instead of using anonymous function.



//TODO 4: Create attachListeners() function 
    // add event listener to game-board  ; click ; event.target -- pass to circleClick function.
    // add event listener to btnClearBoard; click; call clearBoard()
    function attachListeners(){
    
        document.getElementById("game-board").addEventListener('click', (event) => { circleClick(event.target); });
    
        const btnClearBoard = document.getElementById('btnClearBoard');
        btnClearBoard.addEventListener('click', () => {
            clearBoard();
        });
    
    }



//TODO 5: Create circleClick(clickedCircle) function 
function circleClick(clickedCircle){

    //const clickedCircle = event.target;

    if( clickedCircle.classList.contains("circle")
        && !(clickedCircle.classList.contains(player1.className))
        && !(clickedCircle.classList.contains(player2.className))
        && !gameOver
        ){

        clickedCircle.classList.add(currentPlayer.className);
        let otherPlayer = (currentPlayer===player1) ? player2: player1;

        scoreGameBoard(currentPlayer);

        currentPlayer = otherPlayer;
        
        

    }else{
        alert("Doink!");
    }

    

}
    


 //TODO 6: Create scoreGameBoard(player) function 
 function scoreGameBoard(player){

    scoringSets.forEach( (set) => {

        let scoreCounter = 0;

        set.forEach( (id) => {

            let scoredCircle = document.getElementById(id);

            if(scoredCircle.classList.contains(player.className)){
                scoreCounter++;
            }

        } );

        if(scoreCounter === 3){

            
            gameOver = true;
            alert(player.name.toUpperCase() + " HAS WON!")
        }

    });

 }
   


//TODO 7: Create clearBoard() function
function clearBoard(){
    scoringSets.forEach( (set) => {

        set.forEach( (id) => {
            let circle = document.getElementById(id);
            circle.classList.remove(player1.className, player2.className);

        });


    });
    gameOver = false;
}
