import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyGame extends JFrame implements ActionListener {
    JLabel heading;;
    Font font=new Font("", Font.BOLD,40);
    JPanel mainPanel;

    JButton[] btns=new JButton[9];

    //game instant variable
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;

    int wps[][]={   //winning positions
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };
    int winner=2;
    boolean gameOver=false;
    MyGame(){
        System.out.println("creating instant game");
        setTitle("Tic Tac Toe Game");
        setSize(700,700);
        ImageIcon icon= new ImageIcon("src/logo.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);


    }
    private void createGUI(){
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        //north heading
        heading=new JLabel("Tic Tac Toe");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(heading,BorderLayout.NORTH);  //add name to north


        /// panel Section
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        for(int i=1; i<=9; i++){    //add 9 btn to panel
            JButton btn=new JButton();

            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainPanel,BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton currButton= (JButton)e.getSource();
        String nameStr=currButton.getName();
        System.out.println(nameStr);

        int name=Integer.parseInt(nameStr.trim());
        if(gameOver){
            JOptionPane.showMessageDialog(this, "Game already over...");
            return;
        }

        if(gameChances[name]==2)  //position not occupied
        {
            if(activePlayer==1)
            {
                currButton.setIcon(new ImageIcon("src/X.png"));
                gameChances[name]=activePlayer;
                activePlayer=0;
            }
            else{
                currButton.setIcon(new ImageIcon("src/O.png"));
                gameChances[name]=activePlayer;
                activePlayer=1;
            }

            //find winner...
            for(int[]temp:wps){  //every pos of wps
                if((gameChances[temp[0]]==gameChances[temp[1]])&& (gameChances[temp[1]]==gameChances[temp[2]]) && gameChances[temp[2]]!=2){
                    winner=gameChances[temp[0]];
                    gameOver=true;
                    JOptionPane.showMessageDialog(null,"Player "+winner+" has won the game...");
                    int i=JOptionPane.showConfirmDialog(this,"Do you want to play more??");
                    if(i==0){  //yes
                        this.setVisible(false);
                        new MyGame();
                    }else if(i==1){   //no (exit)
                        System.exit(3466);
                    }
                    else{

                    }
                    System.out.println(i);
                    break;
                }
            }
            //draw logic
            int c=0;
            for(int x:gameChances){
                if(x==2){
                    c++;
                    break;
                }
            }
            if(c==0 && gameOver==false){
                JOptionPane.showMessageDialog(null,"It's Draw...");
                int i=JOptionPane.showConfirmDialog(this,"Play more.??");
                if (i == 0) {  // play more-yes
                    this.setVisible(false);
                    new MyGame();
                } else if(i==1){  //play more-no
                    System.exit((34665));
                } else{

                }
                gameOver=true;
            }

        }
        else{
            JOptionPane.showMessageDialog(this,"Position already occupied");
        }
    }
}
