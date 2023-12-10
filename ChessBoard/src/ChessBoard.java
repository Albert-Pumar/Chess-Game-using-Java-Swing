import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

// Name: Albert Pumar i Berga
// Student ID: 2023-81084
public class ChessBoard {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Piece[][] chessBoardStatus = new Piece[8][8];
    private ImageIcon[] pieceImage_b = new ImageIcon[7];
    private ImageIcon[] pieceImage_w = new ImageIcon[7];
    private JLabel message = new JLabel("Enter Reset to Start");

    ChessBoard(){
        initPieceImages();
        initBoardStatus();
        initializeGui();
    }

    public final void initBoardStatus(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
        }
    }

    public final void initPieceImages(){
        pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

        pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    }

    public ImageIcon getImageIcon(Piece piece){
        if(piece.color.equals(PlayerColor.black)){
            if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
            else return pieceImage_b[6];
        }
        else if(piece.color.equals(PlayerColor.white)){
            if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
            else return pieceImage_w[6];
        }
        else return pieceImage_w[6];
    }

    public final void initializeGui(){
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        JButton startButton = new JButton("Reset");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                initiateBoard();
            }
        });

        tools.add(startButton);
        tools.addSeparator();
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 8));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        Insets buttonMargin = new Insets(0,0,0,0);
        for(int i=0; i<chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.addActionListener(new ButtonListener(i, j));
                b.setMargin(buttonMargin);
                b.setIcon(defaultIcon);
                if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
                else b.setBackground(Color.gray);
                b.setOpaque(true);
                b.setBorderPainted(false);
                chessBoardSquares[j][i] = b;
            }
        }

        for (int i=0; i < 8; i++) {
            for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);

        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    //================================Utilize these functions========================================//

    class Piece{
        PlayerColor color;
        PieceType type;

        Piece(){
            color = PlayerColor.none;
            type = PieceType.none;
        }
        Piece(PlayerColor color, PieceType type){
            this.color = color;
            this.type = type;
        }
    }

    public void setIcon(int x, int y, Piece piece){
        chessBoardSquares[y][x].setIcon(getImageIcon(piece));
        chessBoardStatus[y][x] = piece;
    }

    public Piece getIcon(int x, int y){
        return chessBoardStatus[y][x];
    }

    public void markPosition(int x, int y){
        chessBoardSquares[y][x].setBackground(Color.pink);
    }

    public void unmarkPosition(int x, int y){
        if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
        else chessBoardSquares[y][x].setBackground(Color.gray);
    }

    public void setStatus(String inpt){
        message.setText(inpt);
    }

    public void initiateBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) setIcon(i, j, new Piece());
        }
        setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
        setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
        setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
        setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
        for(int i=0;i<8;i++){
            setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
            setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
        }
        setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
        setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
        setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
        setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) unmarkPosition(i, j);
        }
        onInitiateBoard();
    }
//======================================================Don't modify above==============================================================//




    //======================================================Implement below=================================================================//
    enum MagicType {MARK, CHECK, CHECKMATE};
    private int selX = 0, selY = 0;
    private boolean check, checkmate, end;
    private PlayerColor currentPlayerColor = PlayerColor.black; // For convenience, the first turn is always Black

    // This function checks whether a move is valid
    private boolean isValidMove(int x, int y){
        // We just check if the new position is one of the marked in pink
        Color backgroundColor = chessBoardSquares[y][x].getBackground();

        return backgroundColor.equals(Color.PINK);
    }

    // This function marks the squares given a direction
    private void markDirection(int startX, int startY, int deltaX, int deltaY) {
        boolean foundPiece = false;

        for (int i = startX + deltaX, j = startY + deltaY; i >= 0 && i < 8 && j >= 0 && j < 8; i += deltaX, j += deltaY) {
            if (getIcon(i, j).type == PieceType.none) {
                // If there isn't a piece in that direction, we mark in that direction
                markPosition(i, j);
            } else {
                // Check if it's an opponent's piece
                Piece currentPiece = getIcon(startX, startY);
                if (getIcon(i, j).color != currentPiece.color) { // if the color of the two pieces are different
                    // Mark the position of the opponent's piece
                    markPosition(i, j);
                }

                // Stop marking in this direction
                foundPiece = true;
                break;
            }
        }

        // If a piece is found, mark the positions up to that piece
        if (foundPiece) {
            for (int i = startX + deltaX, j = startY + deltaY; i >= 0 && i < 8 && j >= 0 && j < 8; i += deltaX, j += deltaY) {
                if (getIcon(i, j).type == PieceType.none) {
                    markPosition(i, j);
                } else {
                    break;
                }
            }
        }
    }

    private boolean KingInCheck(PlayerColor playerColor) {
        // Get the position of the enemy King
        int opkingX = -1, opkingY = -1;
        int kingX = -1, kingY = -1;

        // Search for both kings
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getIcon(i, j);

                if (piece.type == PieceType.king) {
                    if (piece.color == playerColor) { // we save the positions of the king of the actual player in kingX and kingY
                        kingX = i;
                        kingY = j;
                    } else { // we save the positions of the king of the opposite player in kingX and kingY
                        opkingX = i;
                        opkingY = j;
                    }
                }
            }
        }

        if(opkingX == -1 && opkingY == -1){
            // There's no opposite king, actual player wins
            end = true;
            Piece actualKing = getIcon(kingX, kingY);
            setStatus(actualKing.color.name().toUpperCase() + " WINS. CONGRATULATIONS!");
            return true;

        } else if(kingX == -1 && kingY == -1) {
            // There's no actual king, opposite player wins
            end = true;
            Piece oppositeKing = getIcon(opkingX, opkingY);
            setStatus(oppositeKing.color.name().toUpperCase() + " WINS. CONGRATULATIONS!");
            return true;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece currentPiece = getIcon(i,j);
                showPossibleMoves(i,j,currentPiece); // we check first, for all the pieces the different moves they can do
                if (isValidMove(opkingX, opkingY)) { // if the opposite king is marked, means some piece can attack it
                    return true;
                }else if(isValidMove(kingX, kingY)){ // the same for the actual king, we have to check if any of the kings are in check
                    return true;
                }
            }
        }
        return false;
    }

    // This function is used to show which possible moves are available for each piece.
    // For all the squares that the piece can move to, will be marked as pink.
    private void showPossibleMoves(int x, int y, Piece selectedPiece) {

        //Clean the markers each time we click on a new piece
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) unmarkPosition(i, j);
        }

        if (selectedPiece.color == PlayerColor.white) {
            if (selectedPiece.type == PieceType.pawn) {
                int direction = -1; // Pawn moves upwards for white;

                // Check if the square in front is empty
                if (x + direction >= 0 && x + direction < 8 && getIcon(x + direction, y).type == PieceType.none) {
                    markPosition(x + direction, y);

                    // If it's the pawn's first move, check the square two steps in front
                    if (x == 6 && getIcon(x + 2 * direction, y).type == PieceType.none) { //x == 6 to check if we haven't moved the pawn
                        markPosition(x + 2 * direction, y);
                    }
                }

                // Check if there is an opponent's piece diagonally to the left
                if (x + direction >= 0 && x + direction < 8 && y - 1 >= 0 && getIcon(x + direction, y - 1).color == PlayerColor.black) {
                    markPosition(x + direction, y - 1);
                }

                // Check if there is an opponent's piece diagonally to the right
                if (x + direction >= 0 && x + direction < 8 && y + 1 < 8 && getIcon(x + direction, y + 1).color == PlayerColor.black) {
                    markPosition(x + direction, y + 1);
                }
            }
            else if (selectedPiece.type == PieceType.rook) {
                // Mark the right side squares
                markDirection(x, y, 1, 0);

                // Mark the left side squares
                markDirection(x, y, -1, 0);

                // Mark the top side squares
                markDirection(x, y, 0, 1);

                // Mark the bottom side squares
                markDirection(x, y, 0, -1);
            } else if (selectedPiece.type == PieceType.bishop) {
                markDirection(x, y, 1, 1);
                markDirection(x, y, -1, 1);
                markDirection(x, y, 1, -1);
                markDirection(x, y, -1, -1);
            } else if (selectedPiece.type == PieceType.knight) {
                int[][] knightMoves = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}}; // options for the knight

                for (int[] move : knightMoves) {
                    int newX = x + move[0];
                    int newY = y + move[1];

                    if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                        Piece targetPiece = getIcon(newX, newY);

                        // if there isn't a piece in the square, or it is a piece of the opposite team
                        if (getIcon(newX, newY).type == PieceType.none || targetPiece.color != getIcon(x, y).color) {
                            // If there isn't a piece in that direction, we mark in that direction
                            markPosition(newX, newY);
                        }
                    }
                }
            } else if (selectedPiece.type == PieceType.queen) {
                // Same moves as the rook
                markDirection(x, y, 1, 0);
                markDirection(x, y, -1, 0);
                markDirection(x, y, 0, 1);
                markDirection(x, y, 0, -1);

                // Same moves as the bishop
                markDirection(x, y, 1, 1);
                markDirection(x, y, -1, 1);
                markDirection(x, y, 1, -1);
                markDirection(x, y, -1, -1);

            } else if (selectedPiece.type == PieceType.king) {
                int[][] kingMoves = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}}; //options for the king

                for (int[] move : kingMoves) {
                    int newX = x + move[0];
                    int newY = y + move[1];

                    if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                        Piece targetPiece = getIcon(newX, newY);
                        // if there isn't a piece in the square, or it is a piece of the opposite team
                        if (getIcon(newX, newY).type == PieceType.none || targetPiece.color != getIcon(x, y).color) {
                            // If there isn't a piece in that direction, we mark in that direction
                            markPosition(newX, newY); //if the new position is not in check
                        }
                    }
                }
            }
        }
        else{
            if (selectedPiece.type == PieceType.pawn) {
                int direction = 1; // Pawn moves downwards for black;

                // Check if the square in front is empty
                if (x + direction >= 0 && x + direction < 8 && getIcon(x + direction, y).type == PieceType.none) {
                    markPosition(x + direction, y);

                    // If it's the pawn's first move, check the square two steps in front
                    if (x == 1 && getIcon(x + 2 * direction, y).type == PieceType.none) { // x == 1 to check if we haven't moved the pawn
                        markPosition(x + 2 * direction, y);
                    }
                }

                // Check if there is an opponent's piece diagonally to the left
                if (x + direction >= 0 && x + direction < 8 && y - 1 >= 0 && getIcon(x + direction, y - 1).color == PlayerColor.white) {
                    markPosition(x + direction, y - 1);
                }

                // Check if there is an opponent's piece diagonally to the right
                if (x + direction >= 0 && x + direction < 8 && y + 1 < 8 && getIcon(x + direction, y + 1).color == PlayerColor.white) {
                    markPosition(x + direction, y + 1);
                }
            }
            else if (selectedPiece.type == PieceType.rook) {
                // Mark the right side squares
                markDirection(x, y, -1, 0);

                // Mark the left side squares
                markDirection(x, y, 1, 0);

                // Mark the top side squares
                markDirection(x, y, 0, -1);

                // Mark the bottom side squares
                markDirection(x, y, 0, 1);
            } else if (selectedPiece.type == PieceType.bishop) {
                markDirection(x, y, -1, -1);
                markDirection(x, y, 1, -1);
                markDirection(x, y, -1, 1);
                markDirection(x, y, 1, 1);
            } else if (selectedPiece.type == PieceType.knight) {
                int[][] knightMoves = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}}; // options for the knight

                for (int[] move : knightMoves) {
                    int newX = x + move[0];
                    int newY = y + move[1];

                    if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                        Piece targetPiece = getIcon(newX, newY);

                        // if there isn't a piece in the square, or it is a piece of the opposite team
                        if (getIcon(newX, newY).type == PieceType.none || targetPiece.color != getIcon(x, y).color) {
                            // If there isn't a piece in that direction, we mark in that direction
                            markPosition(newX, newY);
                        }
                    }
                }
            } else if (selectedPiece.type == PieceType.queen) {
                // Same moves as the rook
                markDirection(x, y, -1, 0);
                markDirection(x, y, 1, 0);
                markDirection(x, y, 0, -1);
                markDirection(x, y, 0, 1);

                // Same moves as the bishop
                markDirection(x, y, -1, -1);
                markDirection(x, y, 1, -1);
                markDirection(x, y, -1, 1);
                markDirection(x, y, 1, 1);

            } else if (selectedPiece.type == PieceType.king) {
                int[][] kingMoves = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}}; // options for the king

                for (int[] move : kingMoves) {
                    int newX = x + move[0];
                    int newY = y + move[1];

                    if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                        Piece targetPiece = getIcon(newX, newY);
                        // if there isn't a piece in the square, or it is a piece of the opposite team
                        if (getIcon(newX, newY).type == PieceType.none || targetPiece.color != getIcon(x, y).color) {
                            // If there isn't a piece in that direction, we mark in that direction
                            markPosition(newX, newY); // if the new position is not in check
                        }
                    }
                }
            }
        }
    }

    // This function moves a piece from a square to another. It also checks if the new move caused a check and changes turns.
    // The boolean turn is for whether we want to change of turn or we don't, only important in the check case
    public void makeMove(int fromX, int fromY, int toX, int toY, boolean turn) {

        // Make the movement in the Chess Board
        setIcon(toX, toY, getIcon(fromX, fromY));
        setIcon(fromX, fromY, new Piece());

        //System.out.println("The piece from: ("+fromX+", "+fromY+") got moved to: ("+toX+", "+toY+")");

        // Update if the new move has put the enemy king in check or checkmate
        check = KingInCheck(currentPlayerColor);

        //Clean the markers each time we make a move
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) unmarkPosition(i, j);
        }

        if(turn) {
            // Change of turn
            currentPlayerColor = (currentPlayerColor == PlayerColor.white) ? PlayerColor.black : PlayerColor.white;
            if (check) setStatus(currentPlayerColor.name().toUpperCase() + "'s TURN/CHECK");
            else setStatus(currentPlayerColor.name().toUpperCase() + "'s TURN");
        }
    }

    class ButtonListener implements ActionListener {
        int x;
        int y;

        ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void actionPerformed(ActionEvent e) {    // Only modify here
            // (x, y) is where the click event occurred
            if (!end) {
                if (checkmate) {
                    // We change the title and finish the game
                    PlayerColor oppPlayerColor = (currentPlayerColor == PlayerColor.white) ? PlayerColor.black : PlayerColor.white;
                    setStatus("CHECKMATE! GAME OVER. "+oppPlayerColor.name().toUpperCase() + " WINS, CONGRATULATIONS!");
                    end = true;

                } else if (check) {
                    //The player must move the King or another piece that cancels the check
                    Piece selectedPiece = getIcon(x, y);
                    if (selectedPiece.type != PieceType.none && selectedPiece.color == currentPlayerColor) {
                        showPossibleMoves(x, y, selectedPiece);

                        // Save the current selection
                        selX = x;
                        selY = y;
                    } else if (isValidMove(x, y)) {// We have to check if the new move is going to cancel the check

                        // Make the movement in the Chess Board, but we don't change the turn in case the move doesn't cancel the check
                        makeMove(selX, selY, x, y, false);

                        if(check) { //If the movement doesn't cancel the check, we have to return to the previous state
                            // Undo the move
                            System.out.println("Invalid move, you must block the check.");
                            makeMove(x, y, selX, selY, false); // we return the piece to the square it was placed
                        }else { // in case it cancels the check, we undo the move without changing turns, and we make the move but changing turns
                            makeMove(x, y, selX, selY, false);
                            makeMove(selX, selY, x, y, true);
                            // Reset the selection
                            selX = 0;
                            selY = 0;
                        }
                    }else checkmate = true; // If there's no valid move, just click on any not pink chessboard square and it indicates the checkmate
                } else { // General case, there's no check or checkmate
                    Piece selectedPiece = getIcon(x, y);

                    if (selectedPiece.type != PieceType.none && selectedPiece.color == currentPlayerColor) {
                        // Implement logic to validate the movement based on chess rules
                        showPossibleMoves(x, y, selectedPiece);

                        // Save the current selection
                        selX = x;
                        selY = y;
                    } else if (isValidMove(x, y)) {
                        // Make the move
                        makeMove(selX, selY, x, y,true);

                        // Reset the selection
                        selX = 0;
                        selY = 0;
                    }
                }
            }
        }
    }
    // This function is used to restart the values of the game once the button restart is pressed.
    void onInitiateBoard(){
        currentPlayerColor = PlayerColor.black; // // For convenience, the first turn is always Black
        end = false;
        check = false;
        checkmate = false;
        setStatus(currentPlayerColor.name().toUpperCase() + "'s TURN");
    }
}