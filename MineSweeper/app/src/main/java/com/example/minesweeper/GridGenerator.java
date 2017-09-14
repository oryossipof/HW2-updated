package com.example.minesweeper;

        import android.util.Log;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Random;

/**
 * Created by אלי on 18/08/2017.
 */

public class GridGenerator {
public static int[][] grid;

    public static ArrayList<Integer> xlist = new ArrayList<Integer>();
    public static ArrayList<Integer> ylist = new ArrayList<Integer>();



    // we get the number of booms and the size of the grid (we will need to change that for easy,medium and hard)
    public static int [][] generate(int boomNumber,final int width ,final int height)
    {


        Random rn = new Random();
         grid = new int[width][height];


        //create the game grid
        for (int x=0 ; x< width ;x++)
        {
            grid[x]= new int[height];
        }


        //creates booms and place them on the grid
        Log.e("Number of booms before:" , boomNumber+ " ");
        while (boomNumber > 0 )
        {
            int x = rn.nextInt(width);
            int y = rn.nextInt(height);

            if(grid[x][y] != -1)
            {
                grid[x][y] = -1;
                boomNumber--;
            }


        }


        Log.e("Number of booms After:" , boomNumber + " " );
        grid = calculateNeighbours(grid,width,height);

        createXlistAndYlist(grid ,width,height );
        return  grid;
    }

    private static void createXlistAndYlist(int[][] grid, int width, int height) {

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                if (grid[x][y] != -1) {
                    xlist.add(x);
                    ylist.add(y);
                }
            }
    }

    public  static int[][] calculateNeighbours(int[][] grid, final int  width , final int height)
    {

        Log.e("calculateNeighbours "," ");
        for (int x = 0 ; x < width ; x++ )
            for(int y = 0 ; y < height ; y++)
                grid[x][y] = getNeighbourNumber(grid,x,y,width,height);

        return  grid;
        
    }


    //calculate the number of the neighbour
    private static int getNeighbourNumber(final  int [][] grid , final int x , final int y , final int width ,final int height ) {

        Log.e("getNeighbourNumber "," ");

        // if is a boom , no need to calculate
        if (grid[x][y] == -1)
        {

            return -1;
        }

            int count = 0;
            if (isMineAt(grid,x-1,y-1,width,height)) count++;
            if (isMineAt(grid,x,y-1,width,height)) count++;
            if (isMineAt(grid,x+1,y-1,width,height)) count++;
            if (isMineAt(grid,x-1,y,width,height)) count++;
            if (isMineAt(grid,x+1,y,width,height)) count++;
            if (isMineAt(grid,x-1,y+1,width,height)) count++;
            if (isMineAt(grid,x,y+1,width,height)) count++;
            if (isMineAt(grid,x+1,y+1,width,height)) count++;




        return  count;

    }

    // help us when the position we are checking it is out of bounds
    private  static boolean isMineAt (final  int[][] grid ,final int x,final int y ,final int width , final int height)
    {

        Log.e("isMineAt "," ");

        if(x>=0 && y >= 0 && x < width && y < height)
        {
            if(grid[x][y] == -1) {
                return true;
            }

        }
        return false;
    }



    public static int [][] addBombAndGenerate(int boomNumber,final int width ,final int height)
    {

        Random rn = new Random();

       int pos1 =  rn.nextInt(xlist.size() - 0 + 1);

        grid[xlist.get(pos1)][ylist.get(pos1)] = -1;
        grid = calculateNeighbours(grid,width,height);
        xlist.remove(pos1);
        ylist.remove(pos1);


        //creates booms and place them on the grid
        Log.e("Number of booms before:" , boomNumber+ " ");
/*
                for(int x = 0; x<width; x++)
                {

                        for (int y = 0; y < height; y++) {

                            if (grid[x][y] != -1) {
                                grid[x][y] = -1;
                                boomNumber--;
                                grid = calculateNeighbours(grid,width,height);
                                return  grid;

                        }
                    }
                }

*/


        Log.e("Number of booms After:" , boomNumber + " " );
       // grid = calculateNeighbours(grid,width,height);
        return  grid;
    }



}
