package com.chess.application.service;

import com.chess.application.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class ChessService implements IChessService{

    @Override
    public List<ChessBoard> chessPieceMovement(int col, int row, String chessPiece){
        if(col >= 65 && col <= 72  && row >=1 && row <=8){
            switch (chessPiece){
                case "KING":
                    return kingMovement(col, row);
                case "QUEEN":
                    return queenMovement(col, row);
                case "PAWN-BLACK":
                    return pawnBlackMovement(col, row);
                case "PAWN-WHITE":
                    return pawnWhiteMovement(col, row);
                default:
                    return new ArrayList<>();
            }
        }else{
            return new ArrayList<>();
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

    private List<ChessBoard> pawnBlackMovement(int col, int row){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setCol(col);
        chessBoard.setRow(row == 1 ? row : row-1);
        List<ChessBoard> chessBoardList = new ArrayList<>();
        chessBoardList.add(chessBoard);
        return chessBoardList;
    }

    private List<ChessBoard> pawnWhiteMovement(int col, int row){
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

}