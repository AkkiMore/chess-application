package com.chess.application;

import com.chess.application.exception.InvalidInputException;
import com.chess.application.model.ChessBoard;
import com.chess.application.service.ChessService;
import com.chess.application.service.IChessService;

import java.util.*;

public class ChessApplication {

    public static void main(String[] args) {
        IChessService chessService = new ChessService();
        Scanner scanner = new Scanner(System.in);
        char exitCondition;
        do{
            try {
                System.out.println("\nEnter chess Piece type: ");
                String chessPieceType = scanner.next();
                chessPieceType = chessPieceType.toUpperCase(Locale.ROOT);

                System.out.println("Enter chess Piece Position: ");
                String chessPiecePosition = scanner.next();

                String[] rowColPosition = chessPiecePosition.split("");

                String colPosition = rowColPosition[0].toUpperCase(Locale.ROOT);
                String rowPosition = rowColPosition[1];

                int col = colPosition.charAt(0);
                int row = Integer.parseInt(rowPosition);

                Optional<List<ChessBoard>> chessBoard = chessService.chessPieceMovement(col, row, chessPieceType);
                chessService.possibleChessOutput(chessBoard.orElse(new ArrayList()));
            } catch (InvalidInputException e){
                System.err.println(e.getErrorMsg());
            } catch (Exception e){
                System.err.println("Please enter valid input");
            }
            System.out.println("Do you want to continue ? Y/N : " );
            exitCondition = scanner.next().charAt(0);
        }while(exitCondition == 'Y' || exitCondition == 'y');
    }
}