package com.chess.application.service;

import com.chess.application.exception.InvalidInputException;
import com.chess.application.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessApp {

    public List<String> chessInput(String chessPieceType, String chessPiecePosition, String chessPieceColor){
        IChessService chessService = new ChessService();

        try {
            String[] rowColPosition = chessPiecePosition.split("");

            String colPosition = rowColPosition[0].toUpperCase(Locale.ROOT);
            String rowPosition = rowColPosition[1];

            int col = colPosition.charAt(0);
            int row = Integer.parseInt(rowPosition);
            List<ChessBoard> chessBoard;
            if(chessPieceType.equals("PAWN")){
                chessBoard = chessService.chessPieceMovement(col, row, chessPieceType+"-"+chessPieceColor);
            }else{
                chessBoard = chessService.chessPieceMovement(col, row, chessPieceType);
            }

            if(chessBoard.isEmpty())
                throw new InvalidInputException("Please enter valid input");

            return chessBoard.stream().filter(Objects::nonNull)
                    .map(chess -> (char)chess.getCol() + String.valueOf(chess.getRow()))
                    .collect(Collectors.toList());


        } catch (InvalidInputException e){
            System.err.println(e.getErrorMsg());
        } catch (Exception e){
            System.err.println("Please enter valid input");
        }
        return new ArrayList<>();
    }
}
