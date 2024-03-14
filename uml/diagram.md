```mermaid
classDiagram 
    class Game {
        +fillBoard() 
        +play() 
    }
    
    class Config {
        -int BOARD_SIZE
        -map <shipType, int> ShipConfig
        }
        
class Player {
    +String name
    +placeShips()
    +takeShot()
}

class Board {
    +int BOARD_SIZE$
    +displayBoard()
    +addShip(Field field, <T implements Ship> shipType)
    +getShipsCount() : int
    +shot(Field field) : Field
    
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
    +isSunk() bool
    +int getHitFields() : Field[]
    +setOnField(Field field, int deckNumber)
    Orientation getOrientation()
}

class WarShip {
    <<Abstract>>
    +setOnField(Field field, Orientation orientation)*
    -Field[] occupied
    -Orientation orientation
    -getDecksCount()
}

class SingleMastShip {
    setOnField(Field field, Orientation orientation)
}

class DoubleMastShip {
    setOnField(Field field, Orientation orientation)
}

class TripleMastShip {
    setOnField(Field field, Orientation orientation)
}

class QuadrupleMastShip {
    setOnField(Field field, Orientation orientation)
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



class State {
     <<ENUMERATION>>   
     EMPTY
     SHIP
     HIT
     MISSED
 }

 class Ship {
     <<ENUMERATION>>
     SINGLE_MAST
     DOUBLE_MAST
     TRIPLE_MAST
     QUADRUPLE_MAST
 }
```
1. getHitFields() : Field[] - zabezpieczyc sie aby nie dało sie manipulowac zwroconymi Field, 
    dane ktore zwracamy w np API maja byc niezmienialne (immutable spr: w Collection List i kopie) 
2. Ship.hit() - zwieksza ilos w hits++ oraz sprawdza czy isSunk() - jesli tak to zmienia state na SUNK   
3. Ship.isSunk() - zwraca true jesli hits == decksCount
4. Board.shot()
5. setOnField() - ustawia statek 1 pole statku - implementacja dla kazdego statku inna
6. fillBoard() - ustawia statki na planszy - setup
7. Map <class SHIP, int> Config - konfiguracja statkow
Zad dodatkowe napisac klase Config, nie zpomniec o configach
