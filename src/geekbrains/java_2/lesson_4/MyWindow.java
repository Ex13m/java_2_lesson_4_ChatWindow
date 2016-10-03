package geekbrains.java_2.lesson_4;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Ex13m on 22.09.2016.
 */
public class MyWindow extends JFrame {

    //Class fields for methods , look down)) ;
    private JTextArea chat_area;
    private JTextField type_field;
    private SimpleDateFormat date_format;
    private String nickname;
    private PrintStream out;

    //private String str;
    //private String log_path ;
    public MyWindow() {

        //Main window settings
        setTitle("Flash chat v1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(1250, 100);
        setSize(600, 700);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        //set Date format for insert
        date_format = new SimpleDateFormat(" HH:mm dd.MM.yyyy ");


        //Chat area
        chat_area = new JTextArea();
        chat_area.setLineWrap(true);
        JScrollPane chat_scroll = new JScrollPane(chat_area);
        add(chat_scroll, BorderLayout.CENTER);
        chat_area.setBackground(Color.lightGray);
        chat_area.setEditable(false);
        chat_area.setBorder(new LineBorder(Color.darkGray, 2, true));

        //Text type field
        type_field = new JTextField();
        add(type_field, BorderLayout.SOUTH);
        type_field.setBackground(Color.white);


        //SEND mouse button
        JButton send_button = new JButton("Send");
        add(send_button, BorderLayout.EAST);

        // Add nick name
        nickname = JOptionPane.showInputDialog(this, "What is your nickname");


        //Mouse button listener
        send_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Transition text from type_field to chat_area + Logfile writer
                sendMSG();
            }
        });

        //ENTER key listener
        type_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Transition text from type_field to chat_area + Logfile writer
                sendMSG();
            }
        });

        //Arrangement for SEND mouse button and  for type field
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        JLabel info = new JLabel("Write text " + nickname + " : ");
        jp.add(type_field, BorderLayout.CENTER);
        jp.add(send_button, BorderLayout.EAST);
        jp.add(info, BorderLayout.WEST);
        add(jp, BorderLayout.SOUTH);
        type_field.setPreferredSize(new Dimension(500, 30));
        send_button.setPreferredSize(new Dimension(70, 30));


        //Close all streams of file_writer when program end
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                out.close();
            }
        });


        // set window parameter visible = true
        setVisible(true);
    }

    //Method for transition text from type_field to chat_area & Writing chat log in logfile
    public void sendMSG() {

        String str = type_field.getText();
        //Transition text from type_field to chat_area
        chat_area.append((date_format.format(new Date())) + " " + nickname + ": " + str + "\n");
        type_field.setText("");

        //Writing chat log in logfile
        try {
            String log_path = "c:\\java\\java2\\java_2_lesson_4_ChatWindow\\chat_log.txt";
            out = new PrintStream(new FileOutputStream(log_path, true), true);
            out.println((date_format.format(new Date())) + " " + nickname + ": " + str + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //set cursor o type field on active mode
        type_field.grabFocus();
    }

}

