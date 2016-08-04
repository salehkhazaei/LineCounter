
package charcounter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author Saleh Khazaei
 * @email saleh.khazaei@gmail.com
 */
public class MainFrame extends JFrame{
    ArrayList<String> addresses = new ArrayList<>();
    DefaultListModel<String> listmodel = new DefaultListModel<>();
    JList<String> list = new JList<>(listmodel);
    JLabel label = new JLabel("");
    JButton btn = new JButton("Browse");

    public MainFrame() {
        this.setLayout(null);
        this.add(list);
        this.add(btn);
        this.add(label);
        this.setSize(700, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        list.setBounds(10, 10, 670, 300);
        btn.setBounds(580, 310, 100, 50);
        label.setBounds(10, 370, 670, 300);
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser c = new JFileChooser();
                c.setCurrentDirectory( new File("E:\\Programming\\Work\\Football\\Football\\src") );
                c.setMultiSelectionEnabled(true);
                c.showOpenDialog(MainFrame.this);
                File[] files = c.getSelectedFiles();
                if ( files != null )
                {
                    for ( File file : files )
                    {
                        addresses.add(file.getAbsolutePath());
                        listmodel.addElement(file.getAbsolutePath());
                    }
                    list.validate();
                }
                
                HashMap<String,Integer> chars = new HashMap<>();
                int line_count = 0 ;
                int char_count = 0 ;
                for ( String str : addresses )
                {
                    try {
                        FileInputStream input = new FileInputStream(new File(str));
                        int read = 0 ;
                        while ( read != -1 )
                        {
                            read = input.read();
                            if ( read == -1 )
                                break;
                            char_count ++ ;
                            if ( read == 10 )
                                line_count ++ ;
                            if ( chars.containsKey("" + (char)read) )
                            {
                                chars.put("" + (char)read, chars.get("" + (char)read) + 1 );
                            }
                            else
                            {
                                chars.put("" + (char)read, 1 );
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                label.setText("Char Count: " + char_count + "    Line Count: " + line_count);
            }
        });
    }
    
    
}
