import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//英语打字练习
public class englishPractice implements typingPractice{
    private JPanel englishPanel;
    private JFrame englishFrame;
    private JButton wordButton;
    private JButton sentenceButton;
    private JButton paragraphButton;
    private JButton backButton;
    private setting musicGetter = new setting();
    private Font textFont = new Font("Time New Roman", Font.PLAIN, 20);
    private Highlighter.HighlightPainter highLighter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
    private Random randomNumber = new Random();

    @Override
    public void renderMenu() throws IOException{
        setting colorRender = new setting();

        englishFrame = new JFrame();
        englishFrame.setVisible(true);
        englishFrame.setSize(1024, 768);
        englishFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        englishFrame.setTitle("英语打字练习");
        englishFrame.setLocationRelativeTo(null);

        englishPanel = new JPanel(new GridBagLayout());
        colorRender.changeBGColor(englishPanel);

        wordButton = new JButton("单词练习");
        sentenceButton = new JButton("句子练习");
        paragraphButton = new JButton("段落练习");
        backButton = new JButton("返回");

        wordButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    wordPractice();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        sentenceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    sentencePractice();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        paragraphButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    paragraphPractice();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setting colorRender = new setting();

                englishFrame.setVisible(false);

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
                        } catch (IOException ioException){
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
                        menu m = new menu();
                        try {
                            m.renderMenu();
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

        GridBagConstraints constrain = new GridBagConstraints();

        constrain.insets = new Insets(30,30,30,30);
        constrain.gridx = 0;
        constrain.gridy = 1;
        englishPanel.add(wordButton, constrain);

        constrain.gridx = 0;
        constrain.gridy = 2;
        englishPanel.add(sentenceButton, constrain);

        constrain.gridx = 0;
        constrain.gridy = 3;
        englishPanel.add(paragraphButton, constrain);

        constrain.gridx = 0;
        constrain.gridy = 4;
        englishPanel.add(backButton, constrain);

        englishFrame.add(englishPanel, BorderLayout.CENTER);
    }

    @Override
    public void TimeandCorrectness(JTextArea ta1, JTextArea ta2, double time, JPanel panel) throws BadLocationException{
        String userInput = "";
        userInput = ta2.getText();

        String originalText = ta1.getText();
        int length = originalText.length();
        int userLength = userInput.length();
        Highlighter addHighlight = ta2.getHighlighter();

        char userArray[] = new char[length];

        if(userLength != length){
            userInput.getChars(0, userLength, userArray, 0);

            for(int i = userLength; i < length; i++){
                userArray[userLength] = ' ';
            }
            userInput = String.valueOf(userArray);
        }

        ta2.setText(userInput);
        char textArray[] = originalText.toCharArray();
        int count = 0;

        for(int i = 0; i <= userLength; i++){
            if(userArray[i] != textArray[i]){
                addHighlight.addHighlight(i,i+1, highLighter);
            }else if(userArray[i] == textArray[i]){
                count++;
            }
        }

        JLabel timeLabel = new JLabel();
        JLabel countLabel = new JLabel();

        panel.add(timeLabel);
        panel.add(countLabel);

        timeLabel.setText("Time taken: " + time);
        countLabel.setText("Correctness: " + count + "/" + length);
    }

    @Override
    public void wordPractice() throws IOException{
        long start = System.currentTimeMillis();

        if(musicGetter.onoffMusic() == true){
            musicGetter.playMusic(musicGetter.getMusic());
        }

        setting colorRender = new setting();

        englishFrame.setVisible(false);

        JFrame wordFrame = new JFrame();
        wordFrame.setVisible(true);
        wordFrame.setSize(1024, 768);
        wordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wordFrame.setTitle("Word practice");
        wordFrame.setLocationRelativeTo(null);
        wordFrame.setLayout(new BorderLayout());

        JPanel wordPanel = new JPanel();
        colorRender.changeBGColor(wordPanel);

        JTextArea wordtextArea = new JTextArea();
        JTextArea usertextArea = new JTextArea();

        wordtextArea.setBounds(30,50,800,400);
        usertextArea.setBounds(400,500,800,400);

        int aNumber = randomNumber.nextInt(3);
        if(aNumber == 0){
            loadText(wordtextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\单词练习\\0.txt");
        }else if(aNumber == 1){
            loadText(wordtextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\单词练习\\1.txt");
        }else{
            loadText(wordtextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\单词练习\\2.txt");
        }

        wordtextArea.setFont(textFont);
        wordtextArea.setEditable(false);

        usertextArea.setFont(textFont);

        wordPanel.add(wordtextArea);
        wordPanel.add(usertextArea);

        JPanel backPanel = new JPanel();
        colorRender.changeBGColor(backPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(musicGetter.onoffMusic() == true){
                        musicGetter.stopMusic();
                    }
                    wordFrame.setVisible(false);
                    renderMenu();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                long end = System.currentTimeMillis();
                double time = (double)(end - start)/1000;

                try {
                    TimeandCorrectness(wordtextArea, usertextArea, time, backPanel);
                } catch (BadLocationException badLocationException){
                    badLocationException.printStackTrace();
                }
            }
        });

        wordPanel.add(submitButton);
        backPanel.add(backButton);

        wordFrame.add(wordPanel, BorderLayout.CENTER);
        wordFrame.add(backPanel, BorderLayout.SOUTH);
    }

    @Override
    public void sentencePractice() throws IOException{
        long start = System.currentTimeMillis();

        if(musicGetter.onoffMusic() == true){
            musicGetter.playMusic(musicGetter.getMusic());
        }

        setting colorRender = new setting();

        englishFrame.setVisible(false);

        JFrame sentenceFrame = new JFrame();
        sentenceFrame.setVisible(true);
        sentenceFrame.setSize(1024, 768);
        sentenceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sentenceFrame.setTitle("Sentence practice");
        sentenceFrame.setLocationRelativeTo(null);

        JPanel sentencePanel = new JPanel();
        colorRender.changeBGColor(sentencePanel);

        JTextArea sentencetextArea = new JTextArea();
        JTextArea usertextArea = new JTextArea();

        sentencetextArea.setBounds(30, 50, 800, 400);
        usertextArea.setBounds(400, 500, 800,400);

        int aNumber = randomNumber.nextInt(3);
        if(aNumber == 0){
            loadText(sentencetextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\句子练习\\0.txt");
        }else if(aNumber == 1){
            loadText(sentencetextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\句子练习\\1.txt");
        }else{
            loadText(sentencetextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\句子练习\\2.txt");
        }

        sentencetextArea.setFont(textFont);
        sentencetextArea.setEditable(false);

        usertextArea.setFont(textFont);

        sentencePanel.add(sentencetextArea);
        sentencePanel.add(usertextArea);

        JPanel backPanel = new JPanel();
        colorRender.changeBGColor(backPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(musicGetter.onoffMusic() == true){
                        musicGetter.stopMusic();
                    }
                    sentenceFrame.setVisible(false);
                    renderMenu();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long end = System.currentTimeMillis();
                double time = (double)(end - start)/1000;

                try{
                    TimeandCorrectness(sentencetextArea, usertextArea, time, backPanel);
                }catch (BadLocationException badLocationException){
                    badLocationException.printStackTrace();
                }
            }
        });

        sentencePanel.add(submitButton);
        backPanel.add(backButton);

        sentenceFrame.add(sentencePanel, BorderLayout.CENTER);
        sentenceFrame.add(backPanel,BorderLayout.SOUTH);
    }

    @Override
    public void paragraphPractice() throws IOException{
        long start = System.currentTimeMillis();

        if(musicGetter.onoffMusic() == true){
            musicGetter.playMusic(musicGetter.getMusic());
        }

        setting colorRender = new setting();

        englishFrame.setVisible(false);

        JFrame paragraphFrame = new JFrame();
        paragraphFrame.setVisible(true);
        paragraphFrame.setSize(1024, 768);
        paragraphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paragraphFrame.setTitle("Paragraph practice");
        paragraphFrame.setLocationRelativeTo(null);

        JPanel paragraphPanel = new JPanel();
        colorRender.changeBGColor(paragraphPanel);

        JTextArea paragraphtextArea = new JTextArea();
        JTextArea usertextArea = new JTextArea();

        paragraphtextArea.setBounds(30, 50, 800, 400);
        usertextArea.setBounds(30, 500, 800,400);

        int aNumber = randomNumber.nextInt(3);
        if(aNumber == 0){
            loadText(paragraphtextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\段落练习\\0.txt");
        }else if(aNumber == 1){
            loadText(paragraphtextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\段落练习\\1.txt");
        }else{
            loadText(paragraphtextArea, "..\\程序资源\\程序txt文档\\打字练习txt文档\\英语打字练习\\段落练习\\2.txt");
        }

        paragraphtextArea.setFont(textFont);
        paragraphtextArea.setEditable(false);

        usertextArea.setFont(textFont);

        paragraphPanel.add(paragraphtextArea);
        paragraphPanel.add(usertextArea);

        JPanel backPanel = new JPanel();
        colorRender.changeBGColor(backPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(musicGetter.onoffMusic() == true){
                        musicGetter.stopMusic();
                    }
                    paragraphFrame.setVisible(false);
                    renderMenu();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long end = System.currentTimeMillis();
                double time = (double)(end - start)/1000;

                try{
                    TimeandCorrectness(paragraphtextArea, usertextArea, time, backPanel);
                }catch (BadLocationException badLocationException){
                    badLocationException.printStackTrace();
                }
            }
        });

        paragraphPanel.add(submitButton);
        backPanel.add(backButton);

        paragraphFrame.add(paragraphPanel, BorderLayout.CENTER);
        paragraphFrame.add(backPanel,BorderLayout.SOUTH);
    }

    @Override
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
