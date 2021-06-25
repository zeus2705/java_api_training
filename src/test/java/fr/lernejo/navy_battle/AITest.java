package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AITest {

    @Test
    void CorrectPlaceTest()
    {
        AI test = new AI();
        List<List<int[]>> ships =new ArrayList<>();
        List<int[]> temp = new ArrayList<>();
        temp.add(new int[]{0,0});
        ships.add(temp);
        Assertions.assertEquals(true,test.CorrectPlacement(ships,5,5,0,2),"Ship should be placed");
        Assertions.assertEquals(true,test.CorrectPlacement(ships,5,5,1,2),"Ship should be placed");
        Assertions.assertEquals(true,test.CorrectPlacement(ships,0,3,1,2),"Ship should be placed");
        Assertions.assertEquals(true,test.CorrectPlacement(ships,3,0,0,2),"Ship should be placed");
        Assertions.assertEquals(false,test.CorrectPlacement(ships,2,0,1,2),"Ship should not be placed");
        Assertions.assertEquals(false,test.CorrectPlacement(ships,0,2,0,2),"Ship should not be placed");
        Assertions.assertEquals(false,test.CorrectPlacement(ships,0,1,1,2),"Ship should not be placed");
        Assertions.assertEquals(false,test.CorrectPlacement(ships,1,0,0,2),"Ship should not be placed");
        Assertions.assertEquals(false,test.CorrectPlacement(ships,0,0,1,1),"Ship should not be placed");
        Assertions.assertEquals(false,test.CorrectPlacement(ships,0,0,0,1),"Ship should not be placed");
    }

    @Test
    void PlacementTest()
    {
        AI test = new AI();
        List<List<int[]>> ships =new ArrayList<>();
        test.PlaceShip(ships,2,0,1,2);
        test.PlaceShip(ships,0,2,0,2);
    }

    void DisplayBoard(List<List<int[]>> ships)
    {
         int[][] board = new int[10][10];
        for (List<int[]> coordship : ships) {
            for (int[] coord : coordship) {
                board[coord[1]][coord[0]] = coordship.size();
            }
        }
        System.out.println();
        int l = 0;
        for (int i = 0 ; i < 10; i++)
        {
            for (int k = 0 ; k < 10; k++)
            {
                if (board[i][k] != 0)
                    l++;
                System.out.print(board[i][k]);
            }
            System.out.println();
        }
        System.out.println();
        Assertions.assertEquals(17,l,"Number of filled cell");
    }


    @Test
    void GenerateBoard()
    {
        AI test = new AI();
        for (int i = 0; i < 25; i++) {
            int k = 0;
            List<List<int[]>> ships = test.GenerateBoard();
            for (List<int[]> coordship : ships) {
                for (int[] coord : coordship) {
                    k++;
                }
            }
            //DisplayBoard(ships);
            Assertions.assertEquals(17,k,"Number of filled cell");
        }
    }
}
