package com.chess.application.service;

import com.chess.application.exception.InvalidInputException;
import com.chess.application.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ChessService implements IChessService{

    @Override
    public Optional<List<ChessBoard>> chessPieceMovement(int col, int row, String chessPiece){
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

    private List<ChessBoard> kingMovement(int col, int row){
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

    private List<ChessBoard> queenMovement(int col, int row){
        List<ChessBoard> chessBoardList = new ArrayList<>();

        // Column
        if(col<=72){
            for (int j = row + 1; j <= 8 ; j++) {
                chessBoardList.add(createChessBoardObj(col, j));
            }
            for (int k = row - 1; k >=1 ; k--) {
                chessBoardList.add(createChessBoardObj(col, k));
            }
        }

        // Row
        if(row <= 8){
            for (int j = col + 1; j <= 72 ; j++) {
                chessBoardList.add(createChessBoardObj(j, row));
            }
            for (int k = col - 1; k >=65 ; k--) {
                chessBoardList.add(createChessBoardObj(k, row));
            }
        }

        // Diagonal
        int temp = row;
        for (int i = col + 1; i <= 72 ; i++) {
            int j = temp + 1;
            if(j <= 8){
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
            }
        }

        // Diagonal
        temp = row;
        for (int i = col + 1; i <= 72 ; i++) {
            int j = temp - 1;
            if(j >= 1){
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
            }
        }

        // Diagonal
        temp = row;
        for (int i = col - 1; i >= 65 ; i--) {
            int j = temp + 1;
            if(j <= 8){
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
            }
        }

        // Diagonal
        temp = row;
        for (int i = col - 1; i >= 65 ; i--) {
            int j = temp - 1;
            if(j >= 1) {
                chessBoardList.add(createChessBoardObj(i, j));
                temp = j;
            }
        }
        return chessBoardList;
    }

    private List<ChessBoard> pawnMovement(int col, int row){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setCol(col);
        chessBoard.setRow(row == 8 ? row : row+1);
        List<ChessBoard> chessBoardList = new ArrayList<>();
        chessBoardList.add(chessBoard);
        return chessBoardList;
    }

    private ChessBoard createChessBoardObj(int col, int row){
        if(col >= 65 && col <= 72  && row >=1 && row <=8){
            ChessBoard chessBoard = new ChessBoard();
            chessBoard.setCol(col);
            chessBoard.setRow(row);
            return chessBoard;
        }
        return  null;
    }

    @Override
    public void possibleChessOutput(List<ChessBoard> chessBoardList){
        if(chessBoardList.isEmpty())
            throw new InvalidInputException("Please enter valid input");
        chessBoardList.stream().filter(Objects::nonNull)
                .map(chess -> (char)chess.getCol() + String.valueOf(chess.getRow()))
                .forEach(System.out::println);
    }
}