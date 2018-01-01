import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JButton jbtnStart = new JButton("Start");
    private JButton jbtnExit = new JButton("Exit");
    private JLabel jlb1 = new JLabel("潛水艇大戰");
    private JPanel jpn = new JPanel();
    private Container cp;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW = 500, frmH = 300, screenW, screenH;

    public Login() {
        init();
    }
    public void init() {
        cp=this.getContentPane();
        JPanel jp = new JPanel();
        jp.setOpaque(false);
        cp.add(jp);
        screenW = dim.width;
        screenH = dim.height;
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH, frmW, frmH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp.setLayout(null);

        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("img/ocean.jpg");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

        this.setResizable(false);
        jlb1.setBounds(148, 50, 225, 60);
        jlb1.setFont(new Font("標楷體", Font.BOLD, 40));
        jlb1.setForeground(new Color(3, 23, 235));
        jbtnStart.setBounds(215, 130, 70, 35);
        jbtnExit.setBounds(215, 175, 70, 35);
        jpn.setOpaque(false);
        cp.add(jlb1);
        cp.add(jbtnStart);
        cp.add(jbtnExit);

        jbtnExit.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });

        jbtnStart.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {
                MainFrame mmmm= new MainFrame();
                mmmm.setVisible(true);
                Login.this.setVisible(false);
            }

        });

    }



}