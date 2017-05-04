import java.util.Random;

public class Complayer {
    
    private int R, C, arr[][], initialR, initialC, nextSpace, dirNum; 
    private char dir;
    private Random rand;
    
    public Complayer()
    {
        R = 0;
        C = 0;
        initialR = 0;
        initialC = 0;
        nextSpace = 0;
        dirNum = 1;
        rand = new Random();
    }
    
    public void setNewR()//how the first space is decided
    {   
        R = rand.nextInt(10);
    }
   public void setNewC()
   {   
       C = rand.nextInt(10);
   }
   public void setInitialSpace()
   {
       R = rand.nextInt(10);
       C = rand.nextInt(10);
   }
    public int shipSearch(int arr[][])//every space after the first that is not a hit 
    {                                 //will be decided like this
        
        if(R > 0 && C > 0)
        {
            R = this.getR();
            R--;
            C = this.getC();
            C--;
            
            nextSpace = arr[R][C];
        }
        else if(R > 0 && C == 0)
        {
            //System.out.println("2"); test; do not delete
            this.setNewC();
            R = R--;
            nextSpace = arr[R][C];
        }
        else if(R == 0 && C > 0)
        {
            //System.out.println("3"); test;do not delete
            this.setNewR();
            C = C--;
            nextSpace = arr[R][C];
        }
        else
        {
            //System.out.println("4"); test;do not delete
            this.setNewC();
            this.setNewR();
            
            nextSpace = arr[R][C];
        }
        
        return nextSpace;
    }
        
    public int shipFound(int arr[][])//if there is a hit, the computer will shoot around it
    {                               // until it hits something again 
    	if(dirNum == 1){
    		this.initialR = this.R;//holding variable
    		this.initialC = this.C;//holding variable
    	}
    	
        R = this.getR();
        C = this.getC();
        //dirNum %= 4;
        switch(dirNum){
            case 1:
                if (R < 9)
                {
                    R++;
                    dirNum++;
                    nextSpace = arr[R++][C];
                    dir = 'd';
                }
                else
                {
                	dirNum ++;
                    //this.shipFound(arr);
                }
                                  
                break;
                
            case 2:
                if (R >0)
                {
                    R--;
                    dirNum++;
                    nextSpace = arr[R][C];
                    dir = 'u';
                }
                else
                {
                	dirNum++;
                    //this.shipFound(arr);
                }
                break;
            case 3:
                if (C < 9)
                {
                    C++;
                    dirNum++;
                    nextSpace = arr[R][C];
                    dir = 'r';
                }
                else
                {
                	dirNum++;
                    //this.shipFound(arr);
                }
                break;
            case 4:
                if (C > 0)
                {
                    C--;
                    dirNum++;
                    nextSpace = arr[R][C];
                    dir = 'l';
                }
                break;
            default:
                System.out.println("ERROR");
                break;
             
        }
        
        return nextSpace;
    }
    public void setDirNum(int x){
    	dirNum = x;
    }
    public int getDirNum()
    {
    	return dirNum;
    }
    public void returnToCenter()
    {
        this.setR(this.getInitialR());
        this.setC(this.getInitialC());
    }
    public void setR(int r)
    {
        R = r;
    }
    public void setC(int c)
    {
        C = c;
    }
    public void setDir(char d)
    {
        dir = d;
    }
    public void setNext(int next1, int next2, int arr[][])
    {
        nextSpace =  arr[next1][next2];
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
    public int getNext()
    {
        return nextSpace;
    }
    
    public int getC()
    {
        return C;
    }
    
    public int dirFound(int arr[][]) //loop getDir() until it stops hitting things
    {
        switch(dir){
            case 'd':
                R = R+1;
                nextSpace = arr[R][C];
                break;
            case 'u':
                R = R-1;
                nextSpace = arr[R][C];
                break;
            case 'r':
                C = C+1;
                nextSpace = arr[R][C];
                break;
            case 'l':
                C = C-1;
                nextSpace = arr[R][C];
                break;
        }
        
        return nextSpace;
    }
    
    public int makeSure(int arr[][])//go to inital point of contact and go the opposite direction of where it went previously
    {                    // if it hits something, loop it
        R = initialR;
        C = initialC;
        
        switch(getDir()){
            case 'd':
                R = R-1;
                nextSpace = arr[R][C];
                dir = 'd';
                break;
            case 'u':
                R = R+1;
                nextSpace = arr[R][C];
                dir = 'u';
                break;
            case 'l':
                C = C-1;
                nextSpace = arr[R][C];
                dir = 'l';
                break;
            case 'r':
                C = C+1;
                nextSpace = arr[R][C];
                dir = 'r';
                break;
        }
        
        
        return nextSpace;
    }
    
}