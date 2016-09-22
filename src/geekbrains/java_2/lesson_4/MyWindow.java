package geekbrains.java_2.lesson_4;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * Created by Ex13m on 22.09.2016.
 */
public class MyWindow extends JFrame {
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
        add(chat_area,BorderLayout.CENTER);
        chat_area.setBackground(Color.white);
        chat_area.setEditable(false);
        chat_area.setBorder(new LineBorder(Color.darkGray,2,true));

        // Окно ввода
        JTextField type_field= new JTextField();
        add(type_field, BorderLayout.SOUTH);
        type_field.setBackground(Color.white);


        //
        type_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat date_format = new SimpleDateFormat(" hh:mm dd.MM.yyyy ");
                String str = type_field.getText();
                chat_area.append((date_format.format(new Date()))+ " : " + str +  "\n" );



            }
        });

        setVisible(true);

  }


}

