//Final Exercises CIS263AA
//Lucky 7 Class 
//Written by Jeriod Ruschell Jer2181272 on 1/16


import java.util.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 



public class JLuckySeven extends JPanel implements ActionListener
{//declarations; 
   int wins, losses;//variables to track players wins and losses 
   int x,y,z;//to control loops throughout panel 
   int winGame;//variable to determine if player wins 
   
   String inst = "Please click a position to begin game.";//string for beginning instructions  
   String total;//string to change label after play begins 
   //array declarations
   int[] randnums = new int[7];//for random numbers to correlate to gamecard array 
   JButton[] gameCard = new JButton[7];//buttons that represents game cards 
   JLabel[] position = new JLabel[7];//array of labels for position number
   JPanel[] posHold = new JPanel[7];//array of panels to center within frame  
   
   //JPanel declarations
   JPanel north = new JPanel();//Panel to handle components in north end of JFrame  
   JPanel south = new JPanel();//Panel to handle components in south end of JFrame 
   JPanel center = new JPanel();//Panel to handle center components 
   JPanel studentInfo = new JPanel();//panel to hold student info
   JPanel win = new JPanel();//Filler panel(originally to hold label for wins)  
   JPanel loss = new JPanel();//filler panel(originally to hold label for losses) 
   //Jlabel declarations
   JLabel instructions = new JLabel();//
   //method to load arrays of components into JFrame   
   public void loadCompArray()
   {
      for(x = 0; x < 7; ++x)//loop to run 7 times to load elements into the proper arrays 
      {  //gameCard array loading
         gameCard[x] = new JButton();//creates new instance and loads in array  
         gameCard[x].setName(Integer.toString(x+1));//creates name equal to position placement  
         gameCard[x].setFont(new Font("Arial", Font.BOLD, 120));//sets font  
         gameCard[x].addActionListener(this);//adds button click action listener 
         center.add(gameCard[x]);//loads instance into center panel  
         //position panel array loading
         posHold[x] = new JPanel();//creates new instance in array 
         north.add(posHold[x]);//adds to the north panel
         //position label array loading
         position[x] = new JLabel(); 
         position[x].setText(Integer.toString(x+1));
         position[x].setFont(new Font("Times", Font.BOLD, 30));
         posHold[x].add(position[x]); 
            
      }
   } 
   //unique method to load random numbers to correlate to position numbers in another array 
   //this will pick a unique number between 1 - 7 and place in array
   public void loadRandomNums()
   {
      int control;//variable to hold the randomized number before loading into array 
      int cont = 0;//loop control variable  
      Random rand = new Random();//Random instance 
      //loop to clear random numbers from array and to set gamecards to default setting
      for(x = 0; x < randnums.length; ++x)
      {
         randnums[x] = 0;//sets to zero  
         gameCard[x].setText("X");//sets to default 
      }
      //double loop to get unique random numbers and load into array 
      for(z = 0; z < randnums.length; ++z)//outer loop to load unique number into seperate array
      {
         //do while loop to continue running until unique number is reached 
         do
         {
            control = rand.nextInt(7)+1;//sets random number 1 - 7
            for(x = 0; x < randnums.length-1; ++x)//inner loop to ensure no instances of random number already exist in array
            {  //if else to study random number to array
               if(randnums[x] == control)//if they are found to be equal 
               {  cont = 1;//changes control variable to one to continue looping 
                  break;//breaks out of inner loop but stays within the do while loop 
               }
               else//if random number does not array element
               {  
                  cont = 0;//changes continue variable to zero thus breaking out of do while loop and loading unique number in array 
               }
               
            }
           
         } while(cont == 1);//condition for do while loop   
         randnums[z] = control;//sets unique number in array 
      } 
       
   }
   //method to change game card from default X to random number associated with position 
   public void displaySecretNumber(JButton tmp)
   {
      int temp = Integer.parseInt(tmp.getName()) - 1;//changes position name to integer and reduces by one to represent array location  
      gameCard[temp].setText(Integer.toString(randnums[temp]));//sets game card to random number array element   
   } 
   //method to look at game board and set what selection can be done next
   public void setNextSelection(JButton tmp)
   {
      int temp;//variable to hold array location of next selection   
      int arrayLocation = Integer.parseInt(tmp.getName()) - 1;//variable to hold array location of user choice 
      temp = randnums[arrayLocation];//gets corresponding random number  
      for (x = 0; x < gameCard.length ; ++x)//loop to highlight next selection 
      {
         if (x == (temp-1))//takes the random number reduced by one compares to location 
         {
            gameCard[x].setEnabled(true);//sets button to active          
         }
         else//changes all other 
         {
            gameCard[x].setEnabled(false);//makes inactive
         }
      } 
      
   }
   //method to check board determine outcome
   public void analyzeBoard() 
   {  
      for(x = 0; x < gameCard.length ; ++x)//loops through all game cards
      {
         if(gameCard[x].isEnabled())//finds the game card that is active
         {  
            if(gameCard[x].getText().equals("X"))//if default text still occupies button
            {
               
               ++winGame;//adds one to control variable for user win  
               
            }
            else if(winGame == 6)//ensures all cards are turned over declaring user winner 
            {
               JOptionPane.showMessageDialog(null, "Winner, Winner, chicken dinner");
               wins = wins + 1;//adds one to overall win total  
               loadRandomNums(); //Loads new array of random numbers 
               resetBoard(); //resets games board
            }
            else//if either is not true must mean position is occupied by number and it is not the last card
            {
               JOptionPane.showMessageDialog(null,"You Lose");
               losses = losses + 1; //adds one to overall loss total  
               resetBoard();//resets board 
               loadRandomNums();//gets new set of random numbers 
            }
            
         }
      }
      total = "Player Stats: "+wins+"-"+losses;//sets string variable with updated wins and losses  
      
   }
   //method to reset the game board
   public void resetBoard()
   {  //loops through gameCard array
      for(x = 0; x < gameCard.length; ++x)
      {
         gameCard[x].setText("X"); //sets text to default settings
         gameCard[x].setEnabled(true);//sets button to active
         instructions.setText(inst);//reset text default settings 
         winGame = 0;//resets player win control variable to zero  
      }   
   }
   //method that accepts JPanel and adds to student info panel 
   public void getStudentInfo(JPanel p)
   {
       
      studentInfo.add(p);//takes passed panel and adds to current jframe 
       
   }
   //class constructor   
   public JLuckySeven()
   { //sets container layouts and adds to panel 
      setLayout(new BorderLayout());//sets main to borderlayout
      north.setLayout(new GridLayout(1,7,10,10));//sets layout north panel  
      center.setLayout(new GridLayout(1,7,10,10));//sets layout center panel  
      south.setLayout(new FlowLayout());//sets layout south panel 
      add(north, BorderLayout.NORTH);//adds to main north section 
      add(south, BorderLayout.SOUTH);//adds to main south section  
      add(center, BorderLayout.CENTER);//adds to main center section 
      
      loadCompArray();//calls load component array method 
      
      
      south.add(studentInfo);//adds student info to the south panel   
      south.add(instructions);//adds intruction label to south panel  
      instructions.setFont(new Font("Times", Font.ITALIC, 40));//sets font for the instruction label 
      instructions.setText(inst);//sets text of label to beginning instructions
      loadRandomNums();//loads the array of new random numbers  
   }
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
            
      Object source = e.getSource();//gets source of button click  
      JButton temp = new JButton();//creates a new temporary button  
      temp = (JButton)source;//moves source into the temp button for manipulation
      instructions.setText(total);//changes text to win/loss total message 
      displaySecretNumber(temp);//method to display the random number associated with card
      setNextSelection(temp);//method call to set for next selection 
      analyzeBoard();//analyzes game board for a winner  
       
   }
   
     

   public static void main(String[] args)
   {
      drawStudentInfo draw = new drawStudentInfo();//new panel to draw student information
      JFrame frame = new JFrame();//creates new frame
      JLuckySeven panel = new JLuckySeven();//creates new instance of current panel  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sets close option  
      frame.add(panel);//adds lucky sevens panel to frame 
      frame.setSize(1400,400);//sets size of frame
      panel.getStudentInfo(draw);//calls method to add to lucky sevens panel  
        
      frame.setLocationRelativeTo(null); //sets locaton to the middle of the page
      frame.setVisible(true);//makes frame visible  
   }
      
}