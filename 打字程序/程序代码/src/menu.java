import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Menu类
public class menu{
    // 创建主菜单所需要的
    private JPanel mainPanel;
    private JFrame mainFrame;
    private JButton typingButton;
    private JButton settingButton;
    private JButton instructionButton;

    // renderMenu操作可以将主菜单呈现在用户电脑屏幕
    public void renderMenu() throws IOException{
        // 读取颜色设置
        setting colorRender = new setting();

        // 创建主框架
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(1024, 768);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("打字练习软件");
        mainFrame.setLocationRelativeTo(null);

        // 创建主嵌板
        mainPanel = new JPanel(new GridBagLayout());
        // 将用户上一次设置的颜色呈现
        colorRender.changeBGColor(mainPanel);

        // 进入打字练习的按钮
        typingButton = new JButton("打字练习");
        // 此按钮的作用
        typingButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setting colorRender = new setting();

                mainFrame.setVisible(false);
                JFrame typingFrame = new JFrame();
                typingFrame.setVisible(true);
                typingFrame.setSize(1024, 768);
                typingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                typingFrame.setTitle("打字练习软件");
                typingFrame.setLocationRelativeTo(null);

                JPanel typingPanel = new JPanel(new GridBagLayout());
                try {
                    colorRender.changeBGColor(typingPanel);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                JButton mandarinpracticeButton = new JButton("汉语打字练习");
                JButton englishpracticeButton = new JButton("英语打字练习");
                JButton backButton = new JButton("返回");

                mandarinpracticeButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        typingFrame.setVisible(false);
                        mandarinPractice mP = new mandarinPractice();
                        try {
                            mP.renderMenu();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });

                englishpracticeButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        typingFrame.setVisible(false);
                        englishPractice eP = new englishPractice();
                        try {
                            eP.renderMenu();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });

                backButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        typingFrame.setVisible(false);
                        try {
                            renderMenu();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });

                GridBagConstraints constrain = new GridBagConstraints();

                constrain.insets = new Insets(30,30,30,30);
                constrain.gridx = 0;
                constrain.gridy = 1;
                typingPanel.add(mandarinpracticeButton, constrain);

                constrain.gridx = 0;
                constrain.gridy = 2;
                typingPanel.add(englishpracticeButton, constrain);

                constrain.gridx = 0;
                constrain.gridy = 3;
                typingPanel.add(backButton, constrain);

                typingFrame.add(typingPanel, BorderLayout.CENTER);
            }
        });

        // 进入设置的按钮
        settingButton = new JButton("设置");
        // 此按钮的功能
        settingButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mainFrame.setVisible(false);
                setting s = new setting();
                try{
                    s.renderMenu();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        // 进入软件说明按钮
        instructionButton = new JButton("使用说明");
        // 此按钮的功能
        instructionButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mainFrame.setVisible(false);
                instruction i = new instruction();
                try{
                    i.renderMenu();
                }catch (IOException | BadLocationException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        // 将按钮分布
        GridBagConstraints constrain = new GridBagConstraints();

        constrain.insets = new Insets(30,30,30,30);
        constrain.gridx = 0;
        constrain.gridy = 1;
        mainPanel.add(typingButton, constrain);

        constrain.gridx = 0;
        constrain.gridy = 2;
        mainPanel.add(settingButton, constrain);

        constrain.gridx = 0;
        constrain.gridy = 3;
        mainPanel.add(instructionButton, constrain);

        // 将主嵌板放入主框架中
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    // 主操作
    public static void main(String args[]) throws IOException{
        menu m = new menu();
        m.renderMenu();
    }
}
