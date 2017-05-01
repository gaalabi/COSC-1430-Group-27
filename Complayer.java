package Battleship;


import java.util.Random;

public class Complayer {
    
    private int R, C, arr[][], initialR,initialC, nextSpace; 
    private char dir;
    
    public Complayer()
    {
        R = 0;
        C = 0;
        initialR = 0;
        initialC = 0;
        nextSpace = 0;
    }
    public int setNewSpace()//how the first space is decided
    {
        Random rand = new Random();
        
        R = rand.nextInt(10);
        C = rand.nextInt(10);
        
        return arr[R][C];
        
    }
   
    public int shipSearch()//every space after the first that is not a hit 
    {                     //will be decided like this
        
        if(R > 0 && C > 0)
        {
            R = R--;
            C = C--;
            
            nextSpace = arr[R][C];
        }
        else
        {
            nextSpace = setNewSpace();
        }
        
        return nextSpace;
    }
    
    public int shipFound(int around)//if there is a hit, the computer will shoot around it
    {                               // until it hits something again 
        initialR = R;//holding variable
        initialC = C;//holding variable
        
        switch(around){
            case 1:
                nextSpace = arr[R+1][C];
                dir = 'd';
                break;
            case 2:
                nextSpace = arr[R-1][C];
                dir = 'u';
                break;
            case 3:
                nextSpace = arr[R][C+1];
                dir = 'l';
                break;
            case 4:
                nextSpace = arr[R][C-1];
                dir = 'r';
                break;
            default:
                System.out.println("ERROR");
                break;
             
        }
        
        return nextSpace;
    }
    public char getDir()
    {
        return dir;
    }
    public int getInitialR()
    {
        return initialR;
    }
    public int getInitialC()
    {
        return initialC;
    }
    public int getR()
    {
        return R;
    }
    public int getC()
    {
        return C;
    }
    public int dirFound(char c) //loop getDir() until it stops hitting things
    {
        switch(c){
            case 'd':
                R = R+1;
                nextSpace = arr[R][C];
                break;
            case 'u':
                R = R-1;
                nextSpace = arr[R][C];
                break;
            case 'l':
                C = C+1;
                nextSpace = arr[R][C];
                break;
            case 'r':
                C = C-1;
                nextSpace = arr[R][C];
                break;
        }
        
        return nextSpace;
    }
    
    public int makeSure()//go to inital point of contact and go the opposite diredtion of where it went previouly
    {                    // if it hits something, loop it
        R = initialR;
        C = initialC;
        
        switch(getDir()){
            case 'd':
                R = R-1;
                nextSpace = arr[R][C];
                break;
            case 'u':
                R = R+1;
                nextSpace = arr[R][C];
                break;
            case 'l':
                C = C-1;
                nextSpace = arr[R][C];
                break;
            case 'r':
                C = C+1;
                nextSpace = arr[R][C];
                break;
        }
        
        
        return nextSpace;
    }
    
}
   