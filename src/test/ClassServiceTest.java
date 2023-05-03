package test;


import com.chess.application.model.ChessBoard;
import com.chess.application.service.ChessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClassServiceTest {


    @InjectMocks
    ChessService chessService;

    @Test
    public void testPawnMovement(){
        List<ChessBoard> chessBoardList = chessService.chessPieceMovement(65, 2, "PAWN");
        Assertions.assertNotNull(chessBoardList);
        Assertions.assertEquals(chessBoardList.size(), 1);
        Assertions.assertEquals(chessBoardList.get(0).getCol(), 65);
        Assertions.assertEquals(chessBoardList.get(0).getRow(), 3);
    }


}
