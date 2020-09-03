import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.IOException;

// typingPractice接口
interface typingPractice{

    // 将打字练习界面呈现在用户显示屏幕上
    void renderMenu() throws IOException;

    // 检测用户输入正确以及打字速度
    void TimeandCorrectness(JTextArea ta1, JTextArea ta2, double time, JPanel panel) throws BadLocationException;

    // 单词练习
    void wordPractice() throws IOException;

    // 句子练习
    void sentencePractice() throws IOException;

    // 段落练习
    void paragraphPractice() throws IOException;

    // 加载单词/句子/段落文章
    void loadText(JTextArea textArea, String fileName) throws IOException;
}
