package geekbrains.java_2.lesson_4;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Ex13m on 22.09.2016.
 */
public class MyWindow  extends JFrame {
    public MyWindow () {

        setTitle("Flash chat v1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(1250,100);
        setSize(600,700);
        setBackground(Color.gray);
        setLayout(new BorderLayout());

        //Окно чата
        JTextArea chat_area = new JTextArea();
        chat_area.setLineWrap(true);
        //chat_area.setCaretPosition(chat_area.getDocument().getLength());
        JScrollPane chat_scroll= new JScrollPane(chat_area);
        add(chat_scroll,BorderLayout.CENTER);
        chat_area.setBackground(Color.lightGray);
        chat_area.setEditable(false);
        chat_area.setBorder(new LineBorder(Color.darkGray,2,true));
        //chat_area.setPreferredSize(new Dimension(600,600));


        // Окно ввода
        JTextField type_field= new JTextField();
        add(type_field, BorderLayout.SOUTH);
        type_field.setBackground(Color.white);

        // Добавляем свой ник нейм .
        String nickname = JOptionPane.showInputDialog(this,"What is your nickname");


        //Слушатель на кнопку eнтер для переноса вводимого текста в поле чата
        type_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat date_format = new SimpleDateFormat(" hh:mm dd.MM.yyyy ");
                String str = type_field.getText();
                chat_area.append((date_format.format(new Date()))+ " " + nickname+": " + str +  "\n" );
                type_field.setText("");

        //Пишем лог чата в файл
                try {
                    PrintStream out = new PrintStream(new FileOutputStream("c:\\java\\java2\\java_2_lesson_4_ChatWindow\\chat_log.txt", true), true);

                    out.println((date_format.format(new Date()))+ " " + nickname+": " + str +  "\n" );
                    out.close();
                } catch (IOException e1) {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!не понял как тут чтобы срабатывало на оибку !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    JPanel myRootPane = new JPanel();
                    JOptionPane.showMessageDialog( myRootPane, "File not found", "Error", JOptionPane.DEFAULT_OPTION );
                }
            }
        });

        //Кнопка send для мышки
        JButton send_button = new JButton("Send");
        add(send_button,BorderLayout.EAST);

        send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat date_format = new SimpleDateFormat(" hh:mm dd.MM.yyyy ");
                String str = type_field.getText();
                chat_area.append((date_format.format(new Date()))+ " : " + str +  "\n" );
         //проигрывание звука - не работает
         //Sound.playSound("c:\\java\\java2\\java_2_lesson_4_ChatWindow\\WavLibraryNet_Windows10_Printcomplete.wav").join();
                type_field.setText("");

         //Пишем лог чата в файл
                try {
                    PrintStream out = new PrintStream(new FileOutputStream("c:\\java\\java2\\java_2_lesson_4_ChatWindow\\chat_log.txt", true), true);

                    out.println((date_format.format(new Date()))+ " : " + str +  "\n" );
                    out.close();
                } catch (IOException e1) {
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!не понял как тут чтобы срабатывало на оибку !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    JPanel myRootPane = new JPanel();
                    JOptionPane.showMessageDialog( myRootPane, "File not found", "Error", JOptionPane.DEFAULT_OPTION );
                }
            }
        });

        //Делаем панель для компановки кнопки сенд и поля ввода текста
//        JPanel jp = new JPanel();
//        jp.setLayout(new GridLayout(1,2));
//        jp.add(type_field);
//        jp.add(send_button);
//        add(jp,BorderLayout.SOUTH);

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(type_field,BorderLayout.CENTER);
        jp.add(send_button,BorderLayout.EAST);
        add(jp,BorderLayout.SOUTH);
        type_field.setPreferredSize(new Dimension(500, 35));
        type_field.setPreferredSize(new Dimension(70, 35));

//        jp.add(type_field);
//        jp.add(send_button);
//        add(jp,BorderLayout.SOUTH);
//        type_field.setPreferredSize(new Dimension(500, 35));
//        send_button.setPreferredSize(new Dimension(70, 35));


        setVisible(true);
    }
}

