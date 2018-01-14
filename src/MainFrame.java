import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {
    private Container cp;
    private ImagePanel jpn = new ImagePanel();
    private JPanel toolPane = new JPanel(new GridLayout(1, 2, 5, 5));
    private JButton jbtnAddFish = new JButton("Add submarine");
    private JButton jbtnExit = new JButton("EXIT");
    private int imgW, imgH;
    //    private int submarineIndex=0;
    private Login loginFrame = new Login();
    private ArrayList<Submarine> submarineList = new ArrayList<Submarine>();
    //    private ArrayList <Submarine2> submarineList2=new ArrayList<Submarine2>();
    private ArrayList<Thread> threadList = new ArrayList<Thread>();

    private JLabel jlabboat = new JLabel(new ImageIcon("boat.png"));
    private JLabel jlabbullet = new JLabel();
    private JLabel jlabcount = new JLabel("Hit:0");
    private Timer t1fire;
    private boolean fireC = false;

    private ImageIcon imgbullet = new ImageIcon("torpedo.png");
    private boolean is_drag = false;
    private int count = 0, labX = 710, labY = 70, bulletX, bulletY;
    private int x1,y1,x2,y2;

    public MainFrame(Login login) {
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(3, 3));
        cp.add(jpn, BorderLayout.CENTER);
        cp.add(toolPane, BorderLayout.NORTH);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.validate();

        jlabboat.setBounds(labX, labY, 150, 150);
        jpn.add(jlabboat);
        jlabboat.addMouseListener(this);
        jlabboat.addMouseMotionListener(this);

        jpn.add(jlabbullet);
        jlabbullet.setIcon(imgbullet);
        jlabbullet.setBounds(2000, 100, 100, 100);
        jlabbullet.setVisible(false);

        loginFrame = login;
        imgW = jpn.getImgWidth();
        imgH = jpn.getImgHeight();
        this.setBounds(50, 50, imgW, imgH + 50);
        this.setResizable(false);
        jpn.setLayout(null);
        toolPane.add(jbtnAddFish);
        toolPane.add(jbtnExit);
//        jlb.setBounds(100,100,80,30);


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
                submarineList.add(new Submarine(imgH, imgW));
                jpn.add(submarineList.get(submarineList.size() - 1));
                threadList.add(new Thread(submarineList.get(submarineList.size() - 1)));
                threadList.get(threadList.size() - 1).start();
            }
        });

        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        t1fire = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < submarineList.size(); i++){
                    if((bulletY > submarineList.get(i).getY() && bulletY < submarineList.get(i).getY()+10) &&
                            (bulletX < submarineList.get(i).getX()+80 && bulletX > submarineList.get(i).getX()-80)){
                        fireC =false;
                        jlabbullet.setVisible(false);
                        submarineList.remove(submarineList.get(i));
                        t1fire.stop();
                    }
                    if(bulletY+100 > 715) {
                        fireC = false;
                        jlabbullet.setVisible(false);
                        t1fire.stop();
                    }
                }
                bulletY += 10;
                jlabbullet.setLocation(bulletX, bulletY);


//                if(bulletX<imgW-100&&bulletX>imgW&&bulletY==imgH){
//                    count++;
//                    jlabcount.setText("HIT:"+count);
//
//                }
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(e.getButton()){
            case MouseEvent.BUTTON3:
                if(!fireC){
                    jlabbullet.setVisible(true);
                    bulletX=jlabboat.getX();
                    bulletY=jlabboat.getY();
                    fireC = true;
                    t1fire.start();
                }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (is_drag) return;
        if (e.getButton() == 1) is_drag = true;
        x1 = e.getX();
//        y2 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!is_drag)return;
        is_drag=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(! is_drag) return;
        x2 = e.getX();
//        y2 = e.getY();
        labX = labX + (x2 - x1);
//        labY = labY + (y2 - y1);
        if (labX <= 0) labX = 0;
        if (labX >= 1255) labX = 1255;
        if (labY <= 0) labY = 0;
        if (labY >=715)labY = 719;
        jlabboat.setLocation(labX, labY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    class ImagePanel extends JPanel {
        private BufferedImage image;
        private int imgW, imgH;

        public ImagePanel() {
            try {
                image = ImageIO.read(new File("bg.png"));
                imgW = image.getWidth();
                imgH = image.getHeight();
            } catch (IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "IOException:" + ex.toString());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }

        public int getImgWidth() {
            return imgW;
        }

        public int getImgHeight() {
            return imgH;
        }
    }
}
