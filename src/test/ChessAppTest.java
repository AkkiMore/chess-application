package test;


import com.chess.application.service.ChessApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ChessAppTest {


    @InjectMocks
    ChessApp chessApp;

    @Test
    public void testPawnMovement(){
        List<String> chessMovementList = chessApp.chessInput("PAWN", "A3", "BLACK");
        Assertions.assertNotNull(chessMovementList);
        Assertions.assertEquals(chessMovementList.size(), 1);
        Assertions.assertEquals(chessMovementList.get(0), "A2");
    }

    @Test
    public void testWhitePawnMovement(){
        List<String> chessMovementList = chessApp.chessInput("PAWN", "A3", "WHITE");
        Assertions.assertNotNull(chessMovementList);
        Assertions.assertEquals(chessMovementList.size(), 1);
        Assertions.assertEquals(chessMovementList.get(0), "A4");
    }

}
