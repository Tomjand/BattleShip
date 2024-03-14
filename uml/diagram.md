classDiagram
class Game {
+play()
}

class Player {
+String name
+placeShips()
+takeShot()
}

class Board {
+int BOARD_SIZE$
+int SHIP_TYPES_COUNT$
+displayBoard()
+fillBoard() throws IllegalMoveException
+printBoard()
+addShip(int x, int y, Ship ship) throws IllegalMoveException
+getShipsCount() : int
+getField : Field
+shot(int x, int y) throws IllegalMoveException
-getShip(int decks, Orientation orientation) : Ship
-Field[][] fields
-int[] numberOfShipsByDeck
-int shipsCount
-State getRandomValue()
-printLetters$
-isFieldOccupied(final Field field) : boolean
-isValidPositionOnBoard(final int position) : boolean
-isOutside(): boolean
-int getTotalCountOfShips(final int decksCount)

}

class Field {
+Field(int x, int y, State state)
+State getState()
+setState(State state)
+int getX()
+int getY
+Ship getShip()
+setShip(Ship ship)
+char StateToChar()
-int x
-int y
-State state
-Ship ship
}

class Ship {
<<interface>>
+int getDecksCount()
+hit()
+isSunk() bool
setOnField(Field field, int deckNumber)
Orientation getOrientation()
}

class WarShip{
<<Abstract>>
-Field[] occupied
-Orientation orientation
-getDecksCount()
-int hits

}

class SingleMastShip {

}

class DoubleMastShip {

}

class TripleMastShip {

}

class QuadrupleMastShip {

}

Game "1" *-- "2" Player
Player "1" *-- "1" Board
Board "1" *-- "size*size" Field
Field --> FieldStatus
Field --> ShipType
Ship <|.. WarShip
WarShip <|.. SingleMastShip
WarShip <|.. DoubleMastShip
WarShip <|.. TripleMastShip
WarShip <|.. QuadrupleMastShip

%% enum State {
%%     EMPTY
%%     SHIP
%%     HIT
%%     MISSED
%% }

%% enum Ship {
%%     SINGLE_MAST
%%     DOUBLE_MAST
%%     TRIPLE_MAST
%%     QUADRUPLE_MAST
%% }