# settlers-of-catan-clone
Settlers of Catan Java clone that I started after my object-orientated programming intro classes.  Side project that I occasionally work on to brush up on my Java

# Design Choices

Here's how I think about my design: App --> Pane Organizer --> (Game (--> Player; --> BuildBox; --> TradeBox) <--> SideBar) --> Board --> Graph --> (Vertex (--> Piece) ; <--> Edge (--> Piece)). And then there are some other classes that help divide the labor. (think: inheritance)

Some Enums that I use are Strategy (representation of the three main strategies of the game), Resources (the basic unit of the game), PieceType (used when I'd like to compare things or represent what a vertex/edge has in it) and Property (the team's ownership). I also created a decorator class for decorating edges/vertices when I run KruskalAlgo from the BotPlayer, which is in another class.

Players are extracted from the game because they should hold unique, separate properties, such as the resources they hold. HumanPlayer and BotPlayer inherit from Player superclass. HumanPlayer does not change anything from the superclass as many of the human players actions are done through the game class and actions are done through the click handler. The BotPlayer overrides the makeMove() method to create a minimum spanning tree using Kruskal's algorithm and then tries to build that tree using the resources it has. Kruskal is my complex algorithm for this assignment. Edge weights are discounted on different features of their connected vertices such as high probable rolls, distance from other players, and other things that human players usually think about during the game.

Game manages most of the logic in the game. It creates the GUI board and instantiates the players. It also outlines how turns work holds the ClickHandler which allows human players to interact with the GUI. Comments above the makeTurn() outline how a turn takes place.

BuildBox is a window that opens when a human chooses the build button. It shows you what you can build with your current resources

TradeBox is another window that opens when a human selects the trade button. It shows you what you can trade with the bank (4-of-a-kind for 1-of-anything)

SideBar gets access to the game and displays the current stats of the game so that human players can see what is going on with everyone.

Board builds the GUI for the board and also tells the Graph to insert edges and vertices. The exact ordering was found by trial and error, but I modeled the board after the default board in the game.

Graph is my main data structure and is the abstract representation of Catan. It holds vertices in which users can turn into settlements and cities as well as edges in which users can place roads.

Vertices and Edges hold Pieces, which is a wrapper class for a shape. The game starts with placeholder pieces (CAMP and PATH) but as users buy up property, the Pieces are told to mutate shapes with the given property. Classes that inherit from piece are Camp, Path, Settlement, City, and Road.
