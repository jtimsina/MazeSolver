


import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by Altaf Rattani && Zave Timsina on 11/29/2017.
 */
public class MazeGUI extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        int c [][]  = {
        {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
        {0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
        {0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0},
        {0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
        {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
        {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
      
       
        Maze maze = new Maze(c); //instance of Maze class
        int mazeHeight = maze.getMazeWidth(); //get Maze Height
        int mazeWidth =  maze.getMazeHeight();  // get Maze Width
        System.out.println("maze Height:"+mazeHeight);
        
       
        HBox BoxforButtons = new HBox(mazeWidth/3); //Hbox box Buttons
        BoxforButtons.setPrefHeight(80);
        BoxforButtons.setPrefWidth(mazeWidth);
        //creating buttons
        Button btTakeAStep = new Button("TAKE STEP"); // Button take step
        Button btExit = new Button("EXIT GAME"); //button exit
        Button btFindExit = new Button ("SHOW PATH"); // button Find exit
        BoxforButtons.setAlignment(Pos.CENTER);
        //Adding buttons to H-Box
        BoxforButtons.getChildren().addAll(btTakeAStep,btFindExit,btExit);
      
      
        /*Text Field For the Test*/
        TextArea ta = new TextArea();
        ta.setEditable(false);              // TextField to show the Maze in Text View
        ta.setText(maze.getStringMaze());
      //  pane.getChildren().add(ta);
        
        
        //array of pane to store Pictures of walls and road
        GridPane pane = new GridPane(); // setCenter to Border Pane(carry array of pane)
        pane.setPrefSize(400, 400);
        Rectangle[][] squares = new Rectangle[mazeHeight][mazeWidth];
       
        
      // System.out.println("Pane height = "+pane.getHeight());
      //Main Pain to add on scene
        Image start = new Image("pic/start.png");
        Image end = new Image("pic/end.jpeg");
        Image snowMan = new Image("pic/snowMan.jpeg");
        Image wall = new Image("pic/wall.png");
        Image playButton = new Image("pic/btPlay.jpeg");
//        //ArrayList<Image> giftImage = new ArrayList<Image>();
//        for(int zave = 0; zave <= 6; zave++)
//        {
//        	String a = "pic/gift"+(zave+1)+".jpeg";
////        giftImage.add(new Image (a));
     	Image [] gifts = new Image[6];
//    		gifts[zave] = (new Image (a));
//        }
//        
        gifts[0] = new Image("pic/gift1.jpeg");
        gifts[1] = new Image("pic/gift2.jpeg");
        gifts[2] = new Image("pic/gift3.jpeg");
        gifts[3] = new Image("pic/gift4.jpeg");
        gifts[4] = new Image("pic/gift5.jpeg");
        gifts[5] = new Image("pic/gift6.jpeg");
        BorderPane Bpane = new BorderPane();
        Button btPlayNow = new Button("", new ImageView(playButton));

        VBox p = new VBox(20);
        p.getChildren().add(btPlayNow);
        p.setAlignment(Pos.CENTER);
        for (int i = 0; i < squares.length; i++) 
        {
            for (int k = 0; k < squares[i].length; k++) 
            {
                
                squares[i][k] = new Rectangle(i,k,pane.getWidth()/mazeWidth,pane.getHeight()/mazeHeight);
                if (c[i][k] == 0)
                {
                squares[i][k].setStroke(Color.TRANSPARENT);
                squares[i][k].setFill(Color.RED);
                
                }
                else
                    squares[i][k].setFill(new ImagePattern(start));

                pane.add(squares[i][k], k, i);
                pane.setAlignment(Pos.CENTER);
               // pane.setPrefHeight(450);
                //pane.setPrefHeight(550);
            
            }
        }
        System.out.println("maze Width "+mazeWidth);
//        btTakeAStep.setOnAction(e -> {
//            maze.takeStep();
//            ta.setText(maze.getStringMaze());
//            
//            
//        });

        btExit.setOnAction(e -> {
            System.exit(0);
        });

        
     	 String path = new File("src/pic/backMusic.mp3").getAbsolutePath();
  		 Media backMusic = new Media(new File(path).toURI().toString());
  		 MediaPlayer mediaPlayer = new MediaPlayer(backMusic);
  		 mediaPlayer.setAutoPlay(false);
  		 String path1 = new File("src/pic/funSound.m4a").getAbsolutePath();
  		 Media  takeStepMusic = new Media(new File(path1).toURI().toString());
  		 MediaPlayer mediaPlayer1 = new MediaPlayer(takeStepMusic);
  		 mediaPlayer1.setAutoPlay(false);
  		 pane.add(btPlayNow,14,6);
  	     Bpane.setCenter(pane);
  	     
  	     
  	     
  	     mediaPlayer.play();
  	     btPlayNow.setOnAction(e -> {
        	 pane.getChildren().remove(btPlayNow);
        	 
        });
        
        Bpane.setBottom(BoxforButtons);
       // Bpane.setTop(ta);
        Scene scene = new Scene(Bpane);
        primaryStage.setTitle("MAZE");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Setting the Maze in Pane(grid pane)
        for (int i = 0; i < squares.length; i++) 
        {
            for (int k = 0; k < squares[i].length; k++) 
            {
            	
            	
                squares[i][k] = new Rectangle(i,k,pane.getWidth()/mazeWidth,pane.getHeight()/mazeHeight);
				
                if (c[i][k] == 0)
                {
                squares[i][k].setStroke(Color.TRANSPARENT);
                squares[i][k].setFill(new ImagePattern(wall));
                squares[0][2].setFill(new ImagePattern(start));
                squares[16][2].setFill(new ImagePattern(end));
                }
                else
                    squares[i][k].setFill(Color.WHITE);

                pane.add(squares[i][k], k, i);
                pane.setAlignment(Pos.CENTER);
          		int x=i;
				int y=k;	
				squares[i][k].setOnMouseReleased(e -> {
					
					if(c[x][y]==0){
						c[x][y]=1;
						squares[x][y].setFill(Color.WHITE);
					}else{
						c[x][y]=0;
						squares[x][y].setFill(new ImagePattern(wall));	
					}
					maze.setMazeArrayValue(x,y,c[x][y]);
				});
				
                btTakeAStep.setOnAction(e -> {
                		mediaPlayer.stop();
                		mediaPlayer1.play();
                		int [] position = new int[2];
                		position = maze.getMousePosition();
                	    mediaPlayer.play();
                    squares[position[0]][position[1]].setFill(new ImagePattern(gifts[(int) (Math.random()*6)]));

                    maze.takeStep();
                    
                    
                    ta.setText(maze.getStringMaze());
                    squares[position[0]][position[1]].setFill(new ImagePattern(snowMan));
                    
                });
              
                btFindExit.setOnAction(e -> {
                		
                		mediaPlayer1.stop();
                	
                		int num1 = maze.getMousePosition()[0];
                		if (num1 == 2)
                		mediaPlayer.play();
                		while(num1 != 16){
                		int num2  = maze.getMousePosition()[1];
                		
                		squares[num1][num2].setFill(new ImagePattern(gifts[(int) (Math.random()*6)]));
                		maze.takeStep();
                		 num1 = maze.getMousePosition()[0];
                		 num2 = maze.getMousePosition()[1];
                		squares[num1][num2].setFill(new ImagePattern(snowMan));
                		}
                		
                		//int [] position = new int[2];
                    //position = maze.getMousePosition();
                		
                });
            }
        }
	
        }
    
    
    public static void main(String[] args) {
        Application.launch();
    }
}
