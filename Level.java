import greenfoot.*;

public abstract class Level extends World {
    
   /**
     *Default Welt
     *0 = wand
     *1 = Spieler
     *2 = Gegnertyp 1
     */
   int[][] world = {
       {0,0,0,0},
       {0,1,2,0},
       {0,0,0,0},
   };
    
   public Level() {    
       super(1280, 720, 1);
       setBackground("brick.jpg");
   }
   
   public abstract void finish();
   
   public void generateWorld() {
       
       for(int i = world.length-1;i != -1; i--){
           for(int j = world[i].length-1;j != -1; j--){
               generateObject(world[i][j], (1+j)*10, (1+i)*10);
           }
       }
       
       
   }
   
   public void generateObject(int obj, int x, int y) {
       Actor object = null; 
       if(obj == 0){
           object = new Barrel(); 
       }else if(obj == 1){
           object = new Player(); 
       }else if(obj == 2){
           object = new Bomb();
       }else if(obj == 3){
           object = new Player(); 
       }
       
       addObject(object, x, y);
   }
}
