README: Settlers
Final Handin

DESIGN CHOICES:
Here's how I think about my design: App --> Pane Organizer --> (Game (--> Player; --> BuildBox; --> TradeBox) <-->  SideBar) --> Board --> Graph --> (Vertex (--> Piece) ; <--> Edge (--> Piece)).  And then there are some other classes that help divide the labor. (think: inheritance) 

Some Enums that I use are Strategy (representation of the three main strategies of the game), Resources (the basic unit of the game), PieceType (used when I'd like to compare things or represent what a vertex/edge has in it) and Property (the team's ownership).  I also created a decorator class for decorating edges/vertices when I run KruskalAlgo from the BotPlayer, which is in another class.

Players are extracted from the game because they should hold unique, separate properties, such as the resources they hold.  HumanPlayer and BotPlayer inherit from Player superclass.  HumanPlayer does not change anything from the superclass as many of the human players actions are done through the game class and actions are done through the click handler.  The BotPlayer overrides the makeMove() method to create a minimum spanning tree using Kruskal's algorithm and then tries to build that tree using the resources it has.  Kruskal is my complex algorithm for this assignment.  Edge weights are discounted on different features of their connected vertices such as high probable rolls, distance from other players, and other things that human players usually think about during the game.
   
Game manages most of the logic in the game.  It creates the GUI board and instantiates the players.  It also outlines how turns work holds the ClickHandler which allows human players to interact with the GUI.  Comments above the makeTurn() outline how a turn takes place. 

BuildBox is a window that opens when a human chooses the build button.  It shows you what you can build with your current resources

TradeBox is another window that opens when a human selects the trade button.  It shows you what you can trade with the bank (4-of-a-kind for 1-of-anything) 

SideBar gets access to the game and displays the current stats of the game so that human players can see what is going on with everyone.

Board builds the GUI for the board and also tells the Graph to insert edges and vertices.  The exact ordering was found by trial and error, but I modeled the board after the default board in the game.

Graph is my main data structure and is the abstract representation of Catan.  It holds vertices in which users can turn into settlements and cities as well as edges in which users can place roads.

Vertices and Edges hold Pieces, which is a wrapper class for a shape.  The game starts with placeholder pieces (CAMP and PATH) but as users buy up property, the Pieces are told to mutate shapes with the given property.  Classes that inherit from piece are Camp, Path, Settlement, City, and Road.


KNOWN BUGS:
Not so much of a bug as it is a silly oversight in design.  I restricted players from placing roads that aren't attached to Settlements, but I should've made it perfectly valid to place a road that's connected to a road.  I didn't spend much time thinking about this because roads are basically useless with no Longest Road, and users are better off just establishing Settlements across the board. Still, I wanted to mention this so you don't think it was a bug, I just misremembered the rules and realized it much too late. 

Sometimes, if a bot player wins, the Game Over appears before you can see what the game winning move was.  The bot probably places a settlement to get the game winning score but the GUI isn't updated after it says "GAME OVER" so it's not obvious what the bot did to win.  I think this happens mostly when the bot builds a city when it has 4 points, so the GUI doesn't have the chance to update to show it on the board.
