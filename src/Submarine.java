import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Submarine extends JLabel implements Runnable {
    private int frmW,frmH,x,y,r1,r;
    private  ImageIcon[][] imgIcon={{new ImageIcon("1.png"),new ImageIcon("2.png"),
    new ImageIcon("3.png")},{new ImageIcon("111.png"),new ImageIcon("222.png"),new ImageIcon("333.png")}};
    private  boolean  dirFlag =true;
    private Timer t1;
    private Random rand=new Random();
    public Submarine(int frmH, int frmW){
        this.frmH=frmH;
        this.frmW=frmW;
        x=rand.nextInt(frmW-100);
        y=rand.nextInt(frmH-250) +150;
        r=rand.nextInt(2);
        if(r==1){
            this.dirFlag=false;
        }
        this.setIcon(imgIcon[r][r1=rand.nextInt(3)]);
        this.setBounds(x,y,this.getIcon().getIconWidth(),this.getIcon().getIconHeight());
    }
    @Override
    public  void run(){
        t1= new Timer(rand.nextInt(1000) + 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Submarine.this.dirFlag){
                    if((x-10)>0){
                        x-=10;
                    }else{
                        Submarine.this.dirFlag=!Submarine.this.dirFlag;
                        r=1;
                        Submarine.this.setIcon(imgIcon[r][r1]);
                    }
                    Submarine.this.setLocation(x,y);
                }else{
                    if((x+ Submarine.this.getIcon().getIconWidth()+20<frmW)){
                        x+=10;
                    }else{
                        Submarine.this.dirFlag=!Submarine.this.dirFlag;
                        r=0;
                        Submarine.this.setIcon((imgIcon[r][r1]));
                        x-=10;
                    }
                    Submarine.this.setLocation(x,y);
                }
            }
        });
        t1.start();
    }
}
