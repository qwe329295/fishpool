import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainFrame extends JFrame{
    private Container cp;
    private ImagePanel jpn =new ImagePanel();
    private JPanel toolPane=new JPanel(new GridLayout(1,2,5,5));
    private JButton  jbtnAddFish =new JButton("Add submarine");
    private JButton jbtnExit =new JButton("EXIT");
    private JLabel jlb =new JLabel("aa");
    private int imgW,imgH;
//    private int submarineIndex=0;
    private Login loginFrame=new Login();
    private ArrayList <Submarine> submarineList=new ArrayList<Submarine>();
//    private ArrayList <Submarine2> submarineList2=new ArrayList<Submarine2>();
    private ArrayList <Thread> threadList =new ArrayList<Thread>();

    private  JLabel jlabboat = new JLabel();
    private  JLabel jlabbullet= new JLabel();
    private JLabel jlabcount = new JLabel("Hit:0");
    private Timer t1fire;
    private ImageIcon imgboat =new ImageIcon("boat.png");
    private ImageIcon imgbullet =new ImageIcon("torpedo.png");
    private int count=0, labX=710,labY=70,bulletX,bulletY;

    public MainFrame(Login login){
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout(3,3));
        cp.add(jpn, BorderLayout.CENTER);
        cp.add(toolPane,BorderLayout.NORTH);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.validate();

        jpn.add(jlabboat);
        jlabboat.setIcon(imgboat);
        jlabboat.setBounds(labX,labY,150,150);
        jpn.add(jlabbullet);
        jlabbullet.setIcon(imgbullet);
        jlabbullet.setBounds(1000,100,100,100);
        jlabbullet.setVisible(false);
        loginFrame=login;
        imgW=jpn.getImgWidth();
        imgH=jpn.getImgHeight();
        this.setBounds(350,100,imgW,imgH+50);
        this.setResizable(false);
        jpn.setLayout(null);
        toolPane.add(jbtnAddFish);
        toolPane.add(jbtnExit);
//        jlb.setBounds(100,100,80,30);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        labX-=10;
                        jlabboat.setLocation(labX, labY);
                        System.out.print("123");
                        break;
                    case KeyEvent.VK_RIGHT:
                        labX+=10;
                        jlabboat.setLocation(labX,labY);
                    case KeyEvent.VK_SPACE:
                        jlabbullet.setVisible(true);
                        bulletX=jlabboat.getX();
                        bulletY=jlabboat.getY();
                        t1fire.start();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                loginFrame.setVisible(true);
            }
        });

        jbtnAddFish.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                submarineList.add(new Submarine(imgH,imgW));
                jpn.add(submarineList.get(submarineList.size()-1));
                threadList.add(new Thread(submarineList.get(submarineList.size()-1)));
                threadList.get(threadList.size()-1).start();
            }
        });

        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        t1fire=new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bulletY+=10;
                jlabbullet.setLocation(bulletX,bulletY);
//                if(bulletX<imgW-100&&bulletX>imgW&&bulletY==imgH){
//                    count++;
//                    jlabcount.setText("HIT:"+count);
//
//                }
            }
        });

    }


    class  ImagePanel extends JPanel{
        private BufferedImage image;
        private int imgW,imgH;
        public ImagePanel(){
            try {
                image = ImageIO.read(new File("bg.png"));
                imgW = image.getWidth();
                imgH = image.getHeight();
            }catch(IOException ex){
                javax.swing.JOptionPane.showMessageDialog(this,"IOException:"+ex.toString());
            }
        }
        @Override
        protected  void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image,0,0,null);
        }
        public int getImgWidth(){
            return imgW;
        }
        public int getImgHeight(){
            return imgH;
        }
    }
}
