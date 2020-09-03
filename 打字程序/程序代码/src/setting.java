import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// setting类
public class setting{
    // 创建设置菜单需要的
    private JPanel panel1;
    private JPanel panel2;
    private JFrame frame;
    private String[] colors = {"白色", "黑色", "蓝色", "黄色", "绿色"};
    private String[] music = {"0", "1", "2"};
    private JComboBox colorComboBox;
    private JComboBox musicComboBox;
    private JLabel colorLabel;
    private JLabel musicLabel;
    private JLabel volumeLabel;
    private JButton colorButton;
    private JButton musicButton;
    private JButton onButton;
    private JButton offButton;
    private JButton backButton;
    private boolean ismusicOn;
    private String BGColor;
    private String BGM;
    private Clip clip;

    // renderMenu操作可以将设置菜单呈现在用户电脑屏幕
    public void renderMenu() throws IOException{
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("设置");
        frame.setLocationRelativeTo(null);

        panel1 = new JPanel(new GridBagLayout());
        panel2 = new JPanel(new GridBagLayout());

        changeBGColor(panel1);
        changeBGColor(panel2);

        musicLabel = new JLabel("音乐设置     ");
        musicButton = new JButton("确定");
        musicComboBox = new JComboBox(music);

        onButton = new JButton("开");
        offButton = new JButton("关");
        volumeLabel = new JLabel("音量调节     ");

        backButton = new JButton("返回");

        GridBagConstraints constrain = new GridBagConstraints();

        constrain.insets = new Insets(30,30,30,30);
        constrain.gridx = 0;
        constrain.gridy = 1;
        panel2.add(backButton, constrain);

        colorLabel = new JLabel("颜色设置     ");
        colorButton = new JButton("确定");
        colorComboBox = new JComboBox(colors);

        constrain.gridx = 0;
        constrain.gridy = 1;
        panel1.add(colorLabel);

        constrain.gridx = 1;
        constrain.gridy = 1;
        panel1.add(colorComboBox);

        constrain.gridx = 2;
        constrain.gridy = 1;
        panel1.add(colorButton);

        constrain.gridx = 0;
        constrain.gridy = 2;
        panel1.add(musicLabel, constrain);

        constrain.gridx = 1;
        constrain.gridy = 2;
        panel1.add(musicComboBox, constrain);

        constrain.gridx = 2;
        constrain.gridy = 2;
        panel1.add(musicButton, constrain);

        constrain.gridx = 0;
        constrain.gridy = 3;
        panel1.add(volumeLabel, constrain);

        constrain.gridx = 1;
        constrain.gridy = 3;
        panel1.add(onButton, constrain);

        constrain.gridx = 2;
        constrain.gridy = 3;
        panel1.add(offButton, constrain);

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                menu backtoMenu = new menu();
                try {
                    backtoMenu.renderMenu();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        colorButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    FileWriter fw = new FileWriter("..\\程序资源\\程序txt文档\\设置txt文档\\color.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    if(colorComboBox.getSelectedIndex() == 0){
                        pw.println("WHITE");
                    }else if(colorComboBox.getSelectedIndex() == 1){
                        pw.println("BLACK");
                    }else if(colorComboBox.getSelectedIndex() == 2){
                        pw.println("BLUE");
                    }else if(colorComboBox.getSelectedIndex() == 3){
                        pw.println("YELLOW");
                    }else if(colorComboBox.getSelectedIndex() == 4){
                        pw.println("GREEN");
                    }
                    pw.close();
                    changeBGColor(panel1);
                    changeBGColor(panel2);
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        musicButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    FileWriter fw = new FileWriter("..\\程序资源\\程序txt文档\\设置txt文档\\music.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    if(musicComboBox.getSelectedIndex() == 0){
                        pw.println("..\\程序资源\\程序音乐\\0.wav");
                    }else if(musicComboBox.getSelectedIndex() == 1){
                        pw.println("..\\程序资源\\程序音乐\\1.wav");
                    }else if(musicComboBox.getSelectedIndex() == 2){
                        pw.println("..\\程序资源\\程序音乐\\2.wav");
                    }
                    pw.close();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        onButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    FileWriter fw = new FileWriter("..\\程序资源\\程序txt文档\\设置txt文档\\onoffMusic.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println("true");
                    pw.close();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        offButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    FileWriter fw = new FileWriter("..\\程序资源\\程序txt文档\\设置txt文档\\onoffMusic.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println("false");
                    pw.close();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });
    }

    // 获取软件背景颜色
    protected String getBGColor() throws IOException {
        readFile();
        return BGColor;
    }

    // 获取软件背景音乐
    protected String getMusic() throws IOException {
        readFile();
        return BGM;
    }

    // 更改软件背景颜色
    protected void changeBGColor(JPanel panel) throws IOException{
        if(getBGColor().equals("WHITE")){
            panel.setBackground(Color.white);
        }else if(getBGColor().equals("BLACK")){
            panel.setBackground(Color.black);
        }else if(getBGColor().equals("BLUE")){
            panel.setBackground(Color.blue);
        }else if(getBGColor().equals("YELLOW")){
            panel.setBackground(Color.yellow);
        }else if(getBGColor().equals("GREEN")){
            panel.setBackground(Color.green);
        }
    }

    // 播放音乐
    protected void playMusic(String musicLocation){
        try {
            File musicFile = new File(musicLocation);
            AudioInputStream music = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(music);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (LineUnavailableException e){
            e.printStackTrace();
        }
    }

    // 将音乐停止
    protected void stopMusic(){
        clip.stop();
        clip.close();
    }

    // 查看音乐是否停止
    protected boolean onoffMusic() throws IOException {
        readFile();
        return ismusicOn;
    }

    // 读取数据
    protected void readFile() throws IOException{
        BGColor = Files.readAllLines(Paths.get("..\\程序资源\\程序txt文档\\设置txt文档\\color.txt")).get(0);
        BGM = Files.readAllLines(Paths.get("..\\程序资源\\程序txt文档\\设置txt文档\\music.txt")).get(0);
        String readValue = Files.readAllLines(Paths.get("..\\程序资源\\程序txt文档\\设置txt文档\\onoffMusic.txt")).get(0);
        if(readValue.equals("true")){
            ismusicOn = true;
        }else if(readValue.equals("false")){
            ismusicOn = false;
        }
    }
}
