package com.chess.application.service;

import com.chess.application.model.ChessBoard;

import java.util.List;
import java.util.Optional;

public interface IChessService {

    List<ChessBoard> chessPieceMovement(int col, int row, String chessPiece);

}