package com.bank.application;

import com.bank.application.exception.InvalidInputException;
import com.bank.application.model.ChessBoard;

import java.util.*;

public class ChessApplication {

    public static void main(String[] args) {

        for(int i=0; i < 5; i++) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nEnter chess Piece type: ");
            String chessPieceType = scanner.next();
            chessPieceType = chessPieceType.toUpperCase(Locale.ROOT);

            System.out.println("Enter chess Piece Position: ");
            String chessPiecePosition = scanner.next();

            String rowColPosition[] = chessPiecePosition.split("");

            String colPosition = rowColPosition[0].toUpperCase(Locale.ROOT);
            String rowPosition = rowColPosition[1];

            int col = colPosition.charAt(0);
            int row = Integer.parseInt(rowPosition);

            Optional<List<ChessBoard>> chessBoard = chessPieceMovement(col, row, chessPieceType);
            try {
                possibleChessOutput(chessBoard.orElse(new ArrayList()));
            } catch (InvalidInputException e) {
                System.err.println(e.getErrorMsg());
            }
        }
    }

    public static Optional<List<ChessBoard>> chessPieceMovement(int col, int row, String chessPiece){
        if(col >= 65 && col <= 72  && row >=1 && row <=8){
            switch (chessPiece){
                case "KING":
                    return Optional.of(kingMovement(col, row));
                case "QUEEN":
                    return Optional.of(queenMovement(col, row));
                case "PAWN":
                    return Optional.of(pawnMovement(col, row));
                default:
                    return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
    }

    public static List<ChessBoard> kingMovement(int col, int row){
        List<ChessBoard> chessBoardList = new ArrayList<>();
        chessBoardList.add(createChessBoardObj(col, row+1));
        chessBoardList.add(createChessBoardObj(col, row-1));
        chessBoardList.add(createChessBoardObj(col-1, row));
        chessBoardList.add(createChessBoardObj(col-1, row-1));
        chessBoardList.add(createChessBoardObj(col-1, row+1));
        chessBoardList.add(createChessBoardObj(col+1, row));
        chessBoardList.add(createChessBoardObj(col+1, row-1));
        chessBoardList.add(createChessBoardObj(col+1, row+1));
        return chessBoardList;
    }

    public static List<ChessBoard> queenMovement(int col, int row){
        List<ChessBoard> chessBoardList = new ArrayList<>();

        // Column
        for (int i = col; i <= 72 ; i++) {
            for (int j = row + 1; j <= 8 ; j++) {
                chessBoardList.add(createChessBoardObj(i, j));
            }
            for (int k = row - 1; k >=1 ; k--) {
                chessBoardList.add(createChessBoardObj(i, k));
            }
            break;
        }

        // Row
        for (int i = row; i <= 8 ; i++) {
            for (int j = col + 1; j <= 72 ; j++) {
                chessBoardList.add(createChessBoardObj(j, i));
            }
            for (int k = col - 1; k >=65 ; k--) {
                chessBoardList.add(createChessBoardObj(k, i));
            }
            break;
        }

        // Diagonal
        int temp = row;
        for (int i = col + 1; i <= 72 ; i++) {
            for (int j = temp + 1; j <= 8 ; j++) {
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
                break;
            }

        }

        // Diagonal
        temp = row;
        for (int i = col + 1; i <= 72 ; i++) {
            for (int j = temp - 1; j >= 1 ; j--) {
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
                break;
            }
        }

        // Diagonal
        temp = row;
        for (int i = col - 1; i >= 65 ; i--) {
            for (int j = temp + 1; j <= 8 ; j++) {
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
                break;
            }

        }

        // Diagonal
        temp = row;
        for (int i = col - 1; i >= 65 ; i--) {
            for (int j = temp - 1; j >= 1 ; j--) {
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
                break;
            }
        }
        return chessBoardList;
    }

    public static List<ChessBoard> pawnMovement(int col, int row){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setCol(col);
        chessBoard.setRow(row == 8 ? row : row+1);
        List<ChessBoard> chessBoardList = new ArrayList<>();
        chessBoardList.add(chessBoard);
        return chessBoardList;
    }

    private static ChessBoard createChessBoardObj(int col, int row){
        if(col >= 65 && col <= 72  && row >=1 && row <=8){
            ChessBoard chessBoard = new ChessBoard();
            chessBoard.setCol(col);
            chessBoard.setRow(row);
            return chessBoard;
        }
        return  null;
    }

    public static void possibleChessOutput(List<ChessBoard> chessBoardList){
        if(chessBoardList.isEmpty())
            throw new InvalidInputException("Please enter valid input");
        chessBoardList.stream().filter(Objects::nonNull)
                .map(chess -> (char)chess.getCol() + String.valueOf(chess.getRow()))
                    .forEach(System.out::println);
    }
}

