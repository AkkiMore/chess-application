package com.chess.application;

import com.chess.application.service.ChessApp;

import java.util.Locale;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {

        char exitCondition;

        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter chess Piece type: ");
            String chessPieceType = scanner.next();
            chessPieceType = chessPieceType.toUpperCase(Locale.ROOT);

            System.out.println("\nEnter chess Piece color: ");
            String chessPieceColor = scanner.next();
            chessPieceColor = chessPieceColor.toUpperCase(Locale.ROOT);

            System.out.println("Enter chess Piece Position: ");
            String chessPiecePosition = scanner.next();

            ChessApp chessApp = new ChessApp();
            chessApp.chessInput(chessPieceType, chessPiecePosition, chessPieceColor);
            System.out.println("Do you want to continue ? Y/N : " );
            exitCondition = scanner.next().charAt(0);
        }while(exitCondition == 'Y' || exitCondition == 'y');
    }
}