package GuiPanels;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginPanel extends JFrame{

	private JFrame login = new JFrame();
	
	private JTextField user;
	private JTextField pass;
	private String loc=System.getProperty("user.dir"); 
	
	public static void main(String[] args) {
	LoginPanel p = new LoginPanel();	

	}

	public LoginPanel() {

		login.setBounds(750, 300, 397, 877);
		login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		JLabel msg = new JLabel ("SQL Company DB");
		msg.setBounds(50, 50, 250, 40);
		login.getContentPane().add(msg);
		
		JLabel userLaber = new JLabel("Username");
		userLaber.setBounds(50, 115, 250, 25);
		login.getContentPane().add(userLaber);
		
		user=new JTextField();
		user.setBounds(50, 150, 250, 40);
		login.getContentPane().add(user);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(50, 215, 250, 25);
		login.getContentPane().add(passLabel);
		
		pass=new JTextField();
		pass.setBounds(50, 250, 250, 40);
		login.getContentPane().add(pass);
		
		JButton log = new JButton("Login");
		log.setBounds(100, 330, 150, 50);
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkCredentials(user, pass))
				try {
					
					GUI g = new GUI();
					g.setVisible(true);
					login.setVisible(false);
					dispose();
				}catch(Exception ee) {}
				else
					JOptionPane.showMessageDialog(null, "Invalid Username/Password");
			}
		});
		login.getContentPane().add(log);
		
		login.setVisible(true);
	}
	
	private boolean checkCredentials(JTextField user2, JTextField pass2) {
		ArrayList<String[]>readedData = credentials();
		
        if(readedData.get(1)[0].equals(user2.getText()) 
        && readedData.get(2)[0].equals(pass2.getText()))
        	return true;
        return false;
	}

	public static ArrayList<String[]> credentials() {
		//find file
        String directoryPath = System.getProperty("user.dir"); //where to look
        File[] filesInDirectory = new File(directoryPath).listFiles(); //list all files
        String path = null;
        try {
            for (File f : filesInDirectory) {
                String filePath = f.getAbsolutePath();
                String fileExtenstion = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length()); //check only the name part, not the entire path
                if ("credentials.txt".equals(fileExtenstion)) {
//                	file found
                    path = filePath;
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        //read file
        ArrayList<String[]>readedData = new ArrayList<>();
        if(path!=null)
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                String[] data = input.split(";");
                readedData.add(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //print to console
//        for(String[] i : readedData){
//            for (String s : i) {
//                System.out.print(s + " ");
//            }
//            System.out.println("====");
//        }
        return readedData;
	}
	
}
