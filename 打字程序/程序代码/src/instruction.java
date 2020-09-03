import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// instruction 类
public class instruction{
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JButton backButton;
    private JTextArea instructionArea;
    private Font textFont = new Font("Time New Roman", Font.PLAIN, 21);
    private Highlighter.HighlightPainter highLighter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);

    // 加载软件使用说明界面
    public void renderMenu() throws IOException, BadLocationException {
        setting colorRender = new setting();
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("软件使用说明");
        frame.setLocationRelativeTo(null);

        panel1 = new JPanel(new GridBagLayout());
        colorRender.changeBGColor(panel1);
        panel2 = new JPanel(new GridBagLayout());
        colorRender.changeBGColor(panel2);

        backButton = new JButton("返回");
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

        instructionArea = new JTextArea();
        instructionArea.setBounds(0,50,800,400);
        loadText(instructionArea, "..\\程序资源\\程序txt文档\\使用说明txt文档\\instruction.txt");
        instructionArea.setFont(textFont);
        instructionArea.setEditable(false);
        panel1.add(instructionArea);

        Highlighter addHighlight = instructionArea.getHighlighter();
        addHighlight.addHighlight(128, 142, highLighter);

        GridBagConstraints constrain = new GridBagConstraints();

        constrain.insets = new Insets(30,30,30,30);
        constrain.gridx = 0;
        constrain.gridy = 1;
        panel2.add(backButton, constrain);

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
    }

    // 读取软件使用说明
    public void loadText(JTextArea textArea, String fileName) throws IOException{
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String content = "";

        while(scanner.hasNextLine()){
            content = content.concat(scanner.nextLine() + "\n");
        }

        textArea.setText(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }
}
