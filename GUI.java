import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import SeniorProj.DBConnectionManagerSingleton;

public class GUI {
	// Variable for User Info
    private static String sName;
    private static String sPass;
    private static char[] hPass;
    private static String name;
    private static String sCode;
    private static int sID;
    private static int nID;
    private static int sEventID;
    private static String valueSelected;
    private static String valueSelected1 = "";
    private static String date1 = "";
    private static String time1 = "";
    private static String password;
	static DBConnectionManagerSingleton dbmt;
	static Connection conn;
	PreparedStatement ps;
	static Statement stmt;
	Statement s;
	static ResultSet rs1;
	
	 public static void main(String[] args) throws Exception {
	        login();
	    }
	 
	 //Static Method determines if there is internet
	 private static boolean netIsAvailable() {
		    try {
		        final URL url = new URL("http://www.gmail.com");
		        final URLConnection conn = url.openConnection();
		        conn.connect();
		        conn.getInputStream().close();
		        return true;
		    } catch (MalformedURLException e) {
		        throw new RuntimeException(e);
		    } catch (IOException e) {
		        return false;
		    }
		}
	 //login screen
	 public static void login() throws IOException {
		// CONNECTS TO DATABASE
			try {
				dbmt =  DBConnectionManagerSingleton.getInstance();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			conn = dbmt.getConnection();
			stmt = dbmt.getStatement();
		 JFrame frame = new JFrame();
			 try {
					frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			//Creates frame	
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(true);
	         
	   
			//creates jpanel
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			//username label & textfield
			JLabel lblUsername = new JLabel("USERNAME");
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUsername.setBounds(227, 114, 127, 33);
			contentPane.add(lblUsername);
			
			JTextField txtAdsfadf = new JTextField();
			txtAdsfadf.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtAdsfadf.setBounds(227, 148, 372, 33);
			contentPane.add(txtAdsfadf);
			txtAdsfadf.setColumns(10);
			
			//password label & textfield
			JLabel lblPassword = new JLabel("PASSWORD");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPassword.setBounds(227, 209, 127, 33);
			contentPane.add(lblPassword);
			
			JPasswordField PassField = new JPasswordField();
			PassField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			PassField.setColumns(10);
			PassField.setBounds(227, 238, 372, 33);
			contentPane.add(PassField);
			
			//sign in button
			JButton btnLogin = new JButton("SIGN IN");
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnLogin.setForeground(Color.WHITE);
			btnLogin.setBackground(new Color(60, 179, 113));
			btnLogin.setBorderPainted(false);
			btnLogin.setBounds(285, 318, 262, 33);
			contentPane.add(btnLogin);
			
			//forgot password label/button
			JLabel lblForgotPassword = new JLabel("Forgot Password?");
			lblForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblForgotPassword.setForeground(new Color(128, 128, 128));
			lblForgotPassword.setBounds(364, 430, 115, 26);
			lblForgotPassword.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						forgotPassword1();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			contentPane.add(lblForgotPassword);
			
			//contact label/button
			JLabel lblContact = new JLabel("Contact");
			lblContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblContact.setForeground(new Color(128, 128, 128));
			lblContact.setBounds(783, 475, 57, 33);
			lblContact.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						contact();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			contentPane.add(lblContact);
			
			//Seperator for visuals
			JSeparator separator = new JSeparator();
			separator.setBackground(new Color(128, 128, 128));
			separator.setBounds(227, 407, 372, 2);
			contentPane.add(separator);
			
			//sign in label
			JLabel lblSignIn = new JLabel("SIGN IN");
			lblSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSignIn.setBounds(227, 40, 94, 33);
			contentPane.add(lblSignIn);
			
			//signup label/button
			JLabel lblSignUp = new JLabel("SIGN UP");
			lblSignUp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						signUp();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSignUp.setBounds(346, 40, 81, 33);
			contentPane.add(lblSignUp);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBackground(Color.BLUE);
			separator_1.setBounds(227, 75, 71, 2);
			contentPane.add(separator_1);
			
			//image decoration
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			//responce to clicking of login
			btnLogin.addActionListener(e -> {
//	            incorrectUsername.setVisible(false);
//	            incorrectPassword.setVisible(false);
	            name = txtAdsfadf.getText();
	            hPass = PassField.getPassword();
	            password = "";
	            sName = "";
	            sPass = "";
	            for(char a : hPass) {
	                password += a;
	            }
	            // SQL to compare Username and Password
	            try {
					rs1 = stmt.executeQuery("SELECT username FROM users where username ="+"'"+name+"'");
					while(rs1.next()) {
						sName = rs1.getString(1);
					}
	            } catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	            try {
					rs1 = stmt.executeQuery("SELECT password FROM users where username ="+"'"+name+"'");
					while(rs1.next()) {
						sPass = rs1.getString(1);
					}
	            } catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	          
	            //Checking for problems
	            if (name.equals("") || password.equals("")) {
	            	JOptionPane.showMessageDialog(frame, "Fill in all fields");
	            }
	            else if (!sName.equals(name)) {
	            	JOptionPane.showMessageDialog(frame, "Username does not exit");

	            }
	            else if (!sPass.equals(password)) {
//	                incorrectPassword.setVisible(true);
	            	JOptionPane.showMessageDialog(frame, "Password incorrect");
	            }
	            else {
	            	try {
						rs1 = stmt.executeQuery("SELECT ID FROM users where username ="+"'"+name+"'");
						while(rs1.next()) {
							sID = rs1.getInt(1);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	                try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                frame.dispose();
	            }
	        });
			
			frame.setVisible(true);
	 }
	 public static void signUp() throws IOException {
		 	//Create signup frame
		 JFrame frame = new JFrame();
		 try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			//Creates sign up panel
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			//username label & texfield
			JLabel lblUsername = new JLabel("USERNAME");
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUsername.setBounds(26, 119, 127, 33);
			contentPane.add(lblUsername);
			
			JTextField txtAdsfadf = new JTextField();
			txtAdsfadf.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtAdsfadf.setBounds(25, 154, 372, 33);
			contentPane.add(txtAdsfadf);
			txtAdsfadf.setColumns(10);
			
			//password label & password field
			JLabel lblPassword = new JLabel("PASSWORD");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPassword.setBounds(26, 205, 127, 33);
			contentPane.add(lblPassword);
			
			JPasswordField textField = new JPasswordField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textField.setColumns(10);
			textField.setBounds(25, 243, 372, 33);
			textField.setOpaque(false);
			contentPane.add(textField);
			
			//sign in label/button
			JLabel lblSignIn = new JLabel("SIGN IN");
			lblSignIn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSignIn.setBounds(227, 40, 94, 33);
			contentPane.add(lblSignIn);
			
			//sign up label
			JLabel lblSignUp = new JLabel("SIGN UP");
			lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSignUp.setBounds(346, 40, 81, 33);
			contentPane.add(lblSignUp);
			
			//used for decoration
			JSeparator separator_1 = new JSeparator();
			separator_1.setBackground(Color.BLUE);
			separator_1.setBounds(346, 75, 71, 2);
			contentPane.add(separator_1);
			
			//image used for decoration
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			//confirm password label & password field
			JLabel lblConfirmPassword = new JLabel("CONFIRM PASSWORD");
			lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblConfirmPassword.setBounds(26, 293, 200, 33);
			contentPane.add(lblConfirmPassword);
			
			JPasswordField passwordField = new JPasswordField();
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			passwordField.setColumns(10);
			passwordField.setBounds(25, 331, 372, 33);
			contentPane.add(passwordField);
			
			//first name label & textfield
			JLabel firstName = new JLabel("FIRST NAME");
			firstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			firstName.setBounds(442, 205, 127, 33);
			contentPane.add(firstName);
			
			JTextField passwordField_1 = new JTextField();
			passwordField_1.setOpaque(false);
			passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			passwordField_1.setColumns(10);
			passwordField_1.setBounds(442, 243, 372, 33);
			contentPane.add(passwordField_1);
			
			//last name label & textfield
			JLabel lastName = new JLabel("LAST NAME");
			lastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lastName.setBounds(442, 293 , 127, 33);
			contentPane.add(lastName);
			
			JTextField textField3 = new JTextField();
			textField3.setOpaque(false);
			textField3.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField3.setColumns(10);
			textField3.setBounds(442, 331 , 372, 33);
			contentPane.add(textField3);
			
			//email label & textfield
			JLabel lblEmail = new JLabel("EMAIL");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEmail.setBounds(442, 119, 127, 33);
			contentPane.add(lblEmail);
			
			JTextField textField_1 = new JTextField();
			textField_1.setOpaque(false);
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField_1.setColumns(10);
			textField_1.setBounds(442, 154, 372, 33);
			contentPane.add(textField_1);
			
			//sign up button, passes inputs from text/password fields into database
			JButton btnLogin = new JButton("SIGN UP");
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnLogin.setForeground(Color.WHITE);
			btnLogin.setBackground(new Color(60, 179, 113));
			btnLogin.setBorderPainted(false);
			btnLogin.setBounds(275, 423, 262, 33);
			contentPane.add(btnLogin);
			btnLogin.addActionListener(e -> {
	        	if(textField.getText().equals("")|| txtAdsfadf.getText().equals("") || textField_1.getText().equals("") || passwordField.getText().equals("") || passwordField_1.getText().equals("") || textField3.getText().equals("") ) {
	        		JOptionPane.showMessageDialog(frame, "Enter all values");
	        	}	
	        	else if(!textField.getText().equals(passwordField.getText())) {
	        		JOptionPane.showMessageDialog(frame, "Password does not match");
	        	}
	            else {
	            	int a = 0;
	            	String email1 = "";
	            	try {
	            		rs1 = stmt.executeQuery("select  emails from email where emails = '" + textField_1 .getText() + "'");
						while(rs1.next()) {
						 email1 = (rs1.getString(1));
						}
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
	            	try {
						rs1 = stmt.executeQuery("select count (username) from users where username = '" + txtAdsfadf.getText() + "'");
						while(rs1.next()) {
							 a = rs1.getInt(1);
						}
	            	} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
	            	}
	            	boolean b =  !email1.equals(textField_1.getText());
	            	if(a == 0 && !email1.equals(textField_1 .getText())) {
	            	if(netIsAvailable()) {
	                try {
	                	String emailType = "setUpPassword";
	                	EmailConnect f = new EmailConnect();
	                	sCode = f.getCodeGenerator();
	                	f.setRecipient(textField_1.getText());
	                	f.setEmailType(null,null,sCode,emailType);
	            		f.sendMail();
						signUp2(passwordField_1.getText(),textField3.getText(),txtAdsfadf.getText(),textField.getText(),textField_1.getText());
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "Not a valid email");
					}
	            	}else {
	            		JOptionPane.showMessageDialog(frame, "Connect to internet");
	            	}
	            } else if(a==1) {
	            	JOptionPane.showMessageDialog(frame, "Username already exists");
	            }else{
	            	JOptionPane.showMessageDialog(frame, "Email linked to another account ");
	            }
	            	}
	        });
			
			frame.setVisible(true);
	 }
	 public static void signUp2(String firstName,String lastName,String username,String password,String email) throws IOException {
		 	
		 	//Creates second sign up fram
		    JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			//image decoration
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			//returns to previous frame
			JLabel label_1 = new JLabel("Back");
			Icon back = new ImageIcon(ImageIO.read(new File("Images/154838359951245887[1].png")));
			label_1.setIcon(back);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			label_1.setBounds(15, 28, 86, 33);
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						signUp();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblNewLabel.add(label_1);
			
			JLabel lblFind = new JLabel("Check your email");
			lblFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblFind.setBounds(227, 161, 165, 33);
			contentPane.add(lblFind);
			
			JLabel lblNewLabel_1 = new JLabel("Enter code sent to your email");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(227, 201, 212, 33);
			contentPane.add(lblNewLabel_1);
			
			//textbox to enter code
			JTextField textField = new JTextField();
			textField.setBounds(227, 246, 372, 33);
			contentPane.add(textField);
			textField.setColumns(10);
			
			//button that submits sign up information into the database
			JButton btnF = new JButton("ENTER");
			btnF.setForeground(Color.WHITE);
			btnF.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnF.setBackground(new Color(60, 179, 113));
			btnF.setBounds(278, 333, 262, 33);
			btnF.addActionListener(e -> {
	        	if(textField.getText().equals("")) {
	        		JOptionPane.showMessageDialog(frame, "Enter code");
	        	}	
	            else {
		            if(sCode.equals(textField.getText())) {
		            	try {
							stmt.executeUpdate("insert into users(firstName, lastName, username, password) values ('" + firstName  + "','" + lastName  + "','" + username  + "','" +password + "')" );
							rs1 = stmt.executeQuery("select  ID from users where username = '" + username + "'");
							while(rs1.next()) {
								sID = rs1.getInt(1);
							 }
							stmt.executeUpdate("insert into email(emails,ID) values ('" + email + "'," +sID +")");
							stmt.executeUpdate("Update email SET emailCode = '"+ sCode +"' where emails ='" +email+ "'");
		            	} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					
	                try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                frame.dispose();
		            }
		            else {
		            	JOptionPane.showMessageDialog(frame, "Invalid code");
		            }
	            }
	        });
			contentPane.add(btnF);
			
			frame.setVisible(true);
	 }
	 public static void forgotPassword1() throws IOException {
		 
		 	//Creates frame
		 JFrame frame = new JFrame();
		 try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
			
	         //decoration
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			//return to previous frame
			JLabel lblNewLabel_2 = new JLabel("Back");
			Icon back = new ImageIcon(ImageIO.read(new File("Images/154838359951245887[1].png")));
			lblNewLabel_2.setIcon(back);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_2.setBounds(15, 28, 115, 33);
			lblNewLabel_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblNewLabel.add(lblNewLabel_2);
		
			
			JLabel lblFind = new JLabel("Find your account");
			lblFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblFind.setBounds(227, 161, 165, 33);
			contentPane.add(lblFind);
			
			JLabel lblNewLabel_1 = new JLabel("Enter your email");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(227, 201, 115, 33);
			contentPane.add(lblNewLabel_1);
			
			//textbox to enter email, for user recovery
			JTextField textField = new JTextField();
			textField.setBounds(227, 246, 372, 33);
			contentPane.add(textField);
			textField.setColumns(10);
			
			//button that sends an email to user to view their pass and user
			JButton btnF = new JButton("SEARCH");
			btnF.setForeground(Color.WHITE);
			btnF.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnF.setBackground(new Color(60, 179, 113));
			btnF.setBounds(278, 333, 262, 33);
			btnF.addActionListener(e -> {
	        	if(textField.getText().equals("")) {
	        		JOptionPane.showMessageDialog(frame, "Enter an email address");
	        	}	
	            else {
	            	String a = "";
	            	String b= "";
	            	String c = "";
	            	try {
					rs1 = stmt.executeQuery("select emails,ID from email where emails = '" + textField.getText() + "'");
						while(rs1.next()) {
							a = rs1.getString(1);
							nID = rs1.getInt(2);
						}
						rs1 = stmt.executeQuery("select username,password from users where id ="+nID);
						while(rs1.next()) {
							b = rs1.getString(1);
							c= rs1.getString(2);
						}
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
	            	if(a.equals(textField.getText())) {
	            	if(netIsAvailable()) {
	                try {	
	                	String emailType = "forgotPassword";
	                	EmailConnect s = new EmailConnect();
	                	s.setRecipient(textField.getText());
	                	s.setEmailType(b,c,null,emailType);
	            		s.sendMail();
	            		JOptionPane.showMessageDialog(frame, "Email Sent");
						login();
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "Not a valid email");
					}

	            } else {
	            	JOptionPane.showMessageDialog(frame, "Connect to internet");
	            }
	            	}else {
	            		JOptionPane.showMessageDialog(frame, "Email not valid");
	            	}
	            }
	        });
			
			contentPane.add(btnF);
			
			frame.setVisible(true);
	 }
	 public static void contact() throws IOException {
		 	JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblBack = new JLabel("Back");
			lblBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Icon back = new ImageIcon(ImageIO.read(new File("Images/154838359951245887[1].png")));
			lblBack.setIcon(back);
			lblBack.setBounds(15, 28, 86, 33);
			lblBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblNewLabel.add(lblBack);
			
			JLabel lblName = new JLabel("NAME");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblName.setBounds(129, 119, 53, 33);
			contentPane.add(lblName);
			
			JTextField textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(129, 151, 372, 33);
			contentPane.add(textField);
			
			JLabel lblEmail = new JLabel("EMAIL");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEmail.setBounds(129, 196, 53, 33);
			contentPane.add(lblEmail);
			
			JTextField textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(129, 235, 372, 33);
			contentPane.add(textField_1);
			
			JLabel lblMessage = new JLabel("MESSAGE");
			lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblMessage.setBounds(129, 281, 117, 33);
			contentPane.add(lblMessage);
			
			
			JTextArea textField_2 = new JTextArea();
			textField_2.setSize(372, 160);
			textField_2.setLineWrap(true);
			textField_2.setWrapStyleWord(true);
			
			JScrollPane scrollPane = new JScrollPane(textField_2);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(129, 320, 372, 160);
			contentPane.add(scrollPane);
			
			JButton btnSend = new JButton("SEND");
			btnSend.setForeground(Color.WHITE);
			btnSend.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnSend.setBackground(new Color(60, 179, 113));
			btnSend.setBounds(538, 447, 189, 33);
			btnSend.addActionListener(e -> {
	        	if((textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals(""))) {
	        		JOptionPane.showMessageDialog(frame, "Don't leave any fields blank");
	        	}	
	            else {
	            	if(netIsAvailable()) {
	    	                try {
	    	                	String emailType = "contact";
	    	                	EmailConnect s = new EmailConnect();
	    	                	s.setEmailType(null,null,null, emailType);
	    	                	s.setBody("Sender: "+ textField_1.getText() + "\nName: "+textField.getText()+"\nMessage: " + textField_2.getText());
	    	            		s.sendMail();
	    	            		JOptionPane.showMessageDialog(frame, "Email Sent");
	    	            		textField.setText("");
	    	            		textField_1.setText("");
	    	            		textField_2.setText("");
	    					} catch (MessagingException e1) {
	    						// TODO Auto-generated catch block
	    						JOptionPane.showMessageDialog(frame, "Not a valid email");
	    					}
	            }else {
	            	JOptionPane.showMessageDialog(frame, "Connect to internet");
	            }
	            }
	        });
			contentPane.add(btnSend);
			
			frame.setVisible(true);
	 }
	 public static void contact2() throws IOException {
		 	JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblBack = new JLabel("Back");
			lblBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Icon back = new ImageIcon(ImageIO.read(new File("Images/154838359951245887[1].png")));
			lblBack.setIcon(back);
			lblBack.setBounds(15, 28, 86, 33);
			lblBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblNewLabel.add(lblBack);
			
			JLabel lblName = new JLabel("NAME");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblName.setBounds(129, 119, 53, 33);
			contentPane.add(lblName);
			
			JTextField textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(129, 151, 372, 33);
			contentPane.add(textField);
			
			JLabel lblEmail = new JLabel("EMAIL");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEmail.setBounds(129, 196, 53, 33);
			contentPane.add(lblEmail);
			
			JTextField textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(129, 235, 372, 33);
			contentPane.add(textField_1);
			
			JLabel lblMessage = new JLabel("MESSAGE");
			lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblMessage.setBounds(129, 281, 117, 33);
			contentPane.add(lblMessage);
			
			
			JTextArea textField_2 = new JTextArea();
			textField_2.setSize(372, 160);
			textField_2.setMaximumSize(textField_2.getPreferredSize());
			textField_2.setLineWrap(true);
			textField_2.setWrapStyleWord(true);
			
			JScrollPane scrollPane = new JScrollPane(textField_2);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(129, 320, 372, 160);
			contentPane.add(scrollPane);
			
			JButton btnSend = new JButton("SEND");
			btnSend.setForeground(Color.WHITE);
			btnSend.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnSend.setBackground(new Color(60, 179, 113));
			btnSend.setBounds(538, 447, 189, 33);
			btnSend.addActionListener(e -> {
				if((textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals(""))) {
	        		JOptionPane.showMessageDialog(frame, "Don't leave any fields blank");
	        	}	
	            else {
	            	if(netIsAvailable()) {
	    	                try {
	    	                	String emailType = "contact";
	    	                	EmailConnect s = new EmailConnect();
	    	                	s.setEmailType(null,null,null, emailType);
	    	                	s.setBody("Sender: "+ textField_1.getText() + "\nName: "+textField.getText()+"\nMessage: " + textField_2.getText());
	    	            		s.sendMail();
	    	            		JOptionPane.showMessageDialog(frame, "Email Sent");
	    	            		textField.setText("");
	    	            		textField_1.setText("");
	    	            		textField_2.setText("");
	    					} catch (MessagingException e1) {
	    						// TODO Auto-generated catch block
	    						JOptionPane.showMessageDialog(frame, "Not a valid email");
	    					}
	            	}else {
	            		JOptionPane.showMessageDialog(frame, "Connect to internet");
	            	}
	            }
	        });
			contentPane.add(btnSend);
			
			frame.setVisible(true);
	 }
	 public static void mainMenu() throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
			
			JPanel panel = new JPanel();
//			panel.setBounds(0, 0, 172, 508);
			contentPane.add(panel);
			panel.setLayout(null);
			
			
			JLabel lblRemoveEvent = new JLabel("Remove Event");
			Icon delete = new ImageIcon(ImageIO.read(new File("Images/icons8-delete-30.png")));
			lblRemoveEvent.setIcon(delete);
			lblRemoveEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblRemoveEvent.setBounds(0, 211, 172, 55);
			lblRemoveEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						removeEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblRemoveEvent);
			
			JLabel lblAddEvent = new JLabel("Add Event");
			Icon add = new ImageIcon(ImageIO.read(new File("Images/icons8-plus-math-30.png")));
			lblAddEvent.setIcon(add);
			lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAddEvent.setBounds(0, 158, 172, 55);
			lblAddEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						addEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblAddEvent);
			
			JLabel lblHome = new JLabel("Home");
			Icon home = new ImageIcon(ImageIO.read(new File("Images/icons8-home-30.png")));
			lblHome.setIcon(home);
			lblHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblHome.setBounds(0, 105, 172, 55);
			lblHome.setOpaque(true);
			lblHome.setBackground(new Color(46, 139, 87));
			panel.add(lblHome);
			
			JLabel lblLogout = new JLabel("Logout");
			Icon exit = new ImageIcon(ImageIO.read(new File("Images/icons8-exit-30.png")));
			lblLogout.setIcon(exit);
			lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLogout.setBounds(0, 453, 172, 55);
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblLogout);
			
			JLabel label_1 = new JLabel("");
			Icon menu = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label_1.setIcon(menu);
			label_1.setBounds(14, 16, 37, 33);
			panel.add(label_1);
			
			JLabel lblSettings = new JLabel("Settings");
			Icon settings = new ImageIcon(ImageIO.read(new File("Images/icons8-settings-filled-30.png")));
			lblSettings.setIcon(settings);
			lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSettings.setBounds(0, 267, 172, 55);
			lblSettings.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						settings();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblSettings);
			
			JLabel lblNewLabel_1 = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel_1.setIcon(gradient);
			lblNewLabel_1.setBounds(0, 0, 172, 508);
			panel.add(lblNewLabel_1);
			
			JLabel label = new JLabel("");
			Icon menu_1 = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label.setIcon(menu_1);
			label.setBounds(14, 16, 36, 33);
			contentPane.add(label);
			
			JLabel lblNewLabel = new JLabel("");
			Icon gradient_1 = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient_1);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel label_2 = new JLabel("Contact");
			label_2.setForeground(Color.GRAY);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label_2.setBounds(783, 475, 57, 33);
			label_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						contact2();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			contentPane.add(label_2);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(200, 226, 500, 198);
			contentPane.add(scrollPane);
			
			ArrayList<String> strList = new ArrayList<>(); 
			String b = "";
			int a = 0;
			try {
				rs1 = stmt.executeQuery("select count (eventName) from events where ID = "+ sID +"");
				while(rs1.next()) {
					a = rs1.getInt(1);
				}
				rs1 = stmt.executeQuery("select eventName from events where ID ="+sID+"");
				while(rs1.next()) {
					b = rs1.getString(1);
					strList.add(b);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    JList listd = new JList(strList.toArray());
			scrollPane.setViewportView(listd);
			listd.addListSelectionListener(e -> {
				  valueSelected1 = (String) listd.getSelectedValue();
			});
			
			JButton bttnEnterEvent = new JButton("Enter Event");
			bttnEnterEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			bttnEnterEvent.setBounds(560, 450, 138, 33);
			bttnEnterEvent.setForeground(Color.WHITE);
			bttnEnterEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			bttnEnterEvent.setBackground(new Color(60, 179, 113));
			contentPane.add(bttnEnterEvent);
			bttnEnterEvent.addActionListener(e-> {
					if(!valueSelected1.equals("")) {
					frame.dispose();
					try {
						events(valueSelected1);
						valueSelected1 = "";
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			});
			
			JLabel lblEditEvent = new JLabel("Edit Event");
			Icon edit = new ImageIcon(ImageIO.read(new File("Images/icons8-edit-24.png")));
			lblEditEvent.setIcon(edit);
			lblEditEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEditEvent.setBounds(200, 191, 138, 33);
			lblEditEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(!valueSelected1.equals("")) {
					frame.dispose();
					try {
						editEvent(valueSelected1);
						valueSelected1 = "";
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}
			});
			contentPane.add(lblEditEvent);
			
//			JLabel lblFindYourEvent = new JLabel("Find your event");
//			lblFindYourEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
//			lblFindYourEvent.setBounds(480, 122, 115, 33);
//			contentPane.add(lblFindYourEvent);
			
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(false);
								label_1.setVisible(true);
								for(int a = 0; a<=172; a++) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(true);
								label_1.setVisible(false);
								for(int a = 172; a>=0; a--) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			frame.setVisible(true);
		}
	 
	 public static void events(String valSelect) throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblBack = new JLabel("Back");
			lblBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Icon back = new ImageIcon(ImageIO.read(new File("Images/154838359951245887[1].png")));
			lblBack.setIcon(back);
			lblBack.setBounds(15, 28, 86, 33);
			lblBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblNewLabel.add(lblBack);
			
			JLabel lblEditEvent = new JLabel("");
			Icon edit = new ImageIcon(ImageIO.read(new File("Images/icons8-search-24.png")));
			lblEditEvent.setIcon(edit);
			lblEditEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEditEvent.setBounds(214, 125, 138, 33);
			contentPane.add(lblEditEvent);
			
			JTextField textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField.setBounds(247, 125, 372, 33);
			contentPane.add(textField);
			textField.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(!date1.equals("") || !time1.equals("") || !textField.equals("")) {
						 int key = e.getKeyCode();
				           if (key == KeyEvent.VK_ENTER) {
				        	   try {
				        		   int a = 0;
				        		   rs1 = stmt.executeQuery("select dateID from date where eventID ="+sEventID);
					        	   while(rs1.next()) {
					        		   a = rs1.getInt(1);
					        	   }
					        	   stmt.executeUpdate("insert into attendance(dateID,studentID,present,absent) values ("+a+","+textField.getText()+",1,0)");
					        	   textField.setText("");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				           }
					}
				}
			});
			
			
			
			JButton btnStarEvent = new JButton("Start Event");
			btnStarEvent.setForeground(Color.WHITE);
			btnStarEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnStarEvent.setBackground(new Color(60, 179, 113));
			btnStarEvent.setBounds(225, 180, 120, 33);
			contentPane.add(btnStarEvent);
			btnStarEvent.addActionListener(e -> {
				Date thisDate = new Date();
				SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y");
				date1 = dateForm.format(thisDate);
				Date thisStartTime = new Date();
				SimpleDateFormat dateForm1 = new SimpleDateFormat("hh:mm a");
				time1 = dateForm1.format(thisStartTime);
	        });
			
			JButton btnSubEvent = new JButton("Add Member");
			btnSubEvent.setForeground(Color.WHITE);
			btnSubEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnSubEvent.setBackground(new Color(60, 179, 113));
			btnSubEvent.setBounds(375, 180, 120, 33);
			contentPane.add(btnSubEvent);
			btnSubEvent.addActionListener(e -> {
				frame.dispose();
				try {
					addMember(valSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        });
			
			JButton btnStopEvent = new JButton("Stop Event");
			btnStopEvent.setForeground(Color.WHITE);
			btnStopEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnStopEvent.setBackground(new Color(60, 179, 113));
			btnStopEvent.setBounds(525, 180, 120, 33);
			contentPane.add(btnStopEvent);
			btnStopEvent.addActionListener(e -> {
				if(!date1.equals("") || !time1.equals("")) {
				Date thisStartTime = new Date();
				SimpleDateFormat dateForm1 = new SimpleDateFormat("hh:mm a");
				String time2 = dateForm1.format(thisStartTime);
				try {
					stmt.executeUpdate("insert into date(date, startTime, endTime, eventID) values ('"+date1+"','"+time1+"','"+time2+"',"+sEventID+")");
					date1 = "";
					time1 = "";
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 try {
					 	String a = "";
					 	int b = 0;
					 	int c = 0;
					 	String d= "";
					 	String g= "";
					 	try {
					 		rs1 = stmt.executeQuery("select emails from email where ID ="+sID);
						 	while(rs1.next()) {
						 		a = rs1.getString(1);
						 	}
							rs1 = stmt.executeQuery("select dateID from date where eventID ="+sEventID);
						 	while(rs1.next()) {
						 		b = rs1.getInt(1);
						 	}
						 	rs1 = stmt.executeQuery("select studentID from attendance where dateID ="+b);
						 	while(rs1.next()) {
						 		c = rs1.getInt(1);
						 	} 
						 	rs1 = stmt.executeQuery("select studentFirstName, studentLastName from students where studentID = "+c);
						 	while(rs1.next()) {
						 		d = rs1.getString(1);
						 		g = rs1.getString(2);
						 	}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	String emailType = "attendance";
	                	EmailConnect f = new EmailConnect();
	                	f.setRecipient(a);
	                	f.setEmailType(null,null,null,emailType);
	                	f.setBody("Present: Frederick Santiago\nAbsent: ");
	            		f.sendMail();
						frame.dispose();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "Not a valid email");
					}
	            	}
				frame.dispose();
				try {
					events(valSelect);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        });
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(200, 226, 500, 198);
			contentPane.add(scrollPane);
			
			ArrayList<String> strList = new ArrayList<>(); 
			String b = "";
			int a = 0;
			try {
				rs1 = stmt.executeQuery("select eventID from events where eventName = '"+ valSelect +"'");
				while(rs1.next()) {
					sEventID = rs1.getInt(1);
				}
				rs1 = stmt.executeQuery("select count (date) from date where eventID = "+ sEventID +"");
				while(rs1.next()) {
					a = rs1.getInt(1);
				}
				rs1 = stmt.executeQuery("select date, startTime, endTime from date where eventID ="+sEventID+"");
				while(rs1.next()) {
					b = "Date: "+rs1.getString(1) +"        Start Time: "+ rs1.getString(2)+"         End Time: "+ rs1.getString(3);
					strList.add(b);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    JList listd = new JList(strList.toArray());
			scrollPane.setViewportView(listd);
			listd.addListSelectionListener(e -> {
				
			    });
			
			
			frame.setVisible(true);
	 }
	 public static void addMember(String valSelect) throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			JLabel lblNewLabel = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblBack = new JLabel("Back");
			lblBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
			Icon back = new ImageIcon(ImageIO.read(new File("Images/154838359951245887[1].png")));
			lblBack.setIcon(back);
			lblBack.setBounds(15, 28, 86, 33);
			lblBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						events(valSelect);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			JLabel lblStudentFirstName = new JLabel("FIRST NAME");
			lblStudentFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblStudentFirstName.setBounds(227, 150, 127, 33);
			contentPane.add(lblStudentFirstName);
			
			JTextField textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField.setBounds(227, 190, 372, 33);
			contentPane.add(textField);

			JLabel lblStudentLastName = new JLabel("LAST NAME");
			lblStudentLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblStudentLastName.setBounds(227, 230, 127, 33);
			contentPane.add(lblStudentLastName);
			
			JTextField textField1 = new JTextField();
			textField1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField1.setBounds(227, 260, 372, 33);
			contentPane.add(textField1);

			JLabel lblStudentNum = new JLabel("STUDENT NUMBER");
			lblStudentNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblStudentNum.setBounds(227, 300, 300, 33);
			contentPane.add(lblStudentNum);
			
			JTextField textField3 = new JTextField();
			textField3.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField3.setBounds(227, 330, 372, 33);
			contentPane.add(textField3);
			
			JButton btnAdd = new JButton("ADD");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAdd.setBackground(new Color(60, 179, 113));
			btnAdd.setBounds(227, 400, 371, 33);
			contentPane.add(btnAdd);
			btnAdd.addActionListener(e -> {
				if(textField.getText().equals("") || textField1.getText().equals("") || textField3.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Don't leave any fields blank");
				}else {
				try {
					stmt.executeUpdate("insert into students(studentFirstName, studentLastName, studentID) values ('" + textField .getText() + "','" +textField1.getText()+ "',"+ textField3.getText()+")");
					textField.setText("");
					textField1.setText("");
					textField3.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Member already exists");
				}
				}
	        });
			
			
			lblNewLabel.add(lblBack);
			frame.setVisible(true);
	 }
	 public static void addEvent() throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
			
			JPanel panel = new JPanel();
//			panel.setBounds(0, 0, 172, 508);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblRemoveEvent = new JLabel("Remove Event");
			Icon delete = new ImageIcon(ImageIO.read(new File("Images/icons8-delete-30.png")));
			lblRemoveEvent.setIcon(delete);
			lblRemoveEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblRemoveEvent.setBounds(0, 211, 172, 55);
			lblRemoveEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						removeEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblRemoveEvent);
			
			JLabel lblAddEvent = new JLabel("Add Event");
			Icon add = new ImageIcon(ImageIO.read(new File("Images/icons8-plus-math-30.png")));
			lblAddEvent.setIcon(add);
			lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAddEvent.setBounds(0, 158, 172, 55);
			lblAddEvent.setOpaque(true);
			lblAddEvent.setBackground(new Color(46, 139, 87));
			panel.add(lblAddEvent);
			
			JLabel lblHome = new JLabel("Home");
			Icon home = new ImageIcon(ImageIO.read(new File("Images/icons8-home-30.png")));
			lblHome.setIcon(home);
			lblHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblHome.setBounds(0, 105, 172, 55);
			lblHome.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblHome);
			
			JLabel lblLogout = new JLabel("Logout");
			Icon exit = new ImageIcon(ImageIO.read(new File("Images/icons8-exit-30.png")));
			lblLogout.setIcon(exit);
			lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLogout.setBounds(0, 453, 172, 55);
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblLogout);
			
			JLabel label_1 = new JLabel("");
			Icon menu = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label_1.setIcon(menu);
			label_1.setBounds(14, 16, 37, 33);
			panel.add(label_1);
			
			JLabel lblSettings = new JLabel("Settings");
			Icon settings = new ImageIcon(ImageIO.read(new File("Images/icons8-settings-filled-30.png")));
			lblSettings.setIcon(settings);
			lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSettings.setBounds(0, 267, 172, 55);
			lblSettings.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						settings();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblSettings);
			
			
			JLabel lblNewLabel_1 = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel_1.setIcon(gradient);
			lblNewLabel_1.setBounds(0, 0, 172, 508);
			panel.add(lblNewLabel_1);
			
			JLabel label = new JLabel("");
			Icon menu_1 = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label.setIcon(menu_1);
			label.setBounds(14, 16, 36, 33);
			contentPane.add(label);
			
			JLabel lblNewLabel = new JLabel("");
			Icon gradient_1 = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient_1);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblEventName = new JLabel("EVENT NAME");
			lblEventName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEventName.setBounds(227, 189, 127, 33);
			contentPane.add(lblEventName);
			
			JTextField textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField.setBounds(227, 244, 372, 33);
			contentPane.add(textField);
			
			JButton btnAdd = new JButton("ADD");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAdd.setBackground(new Color(60, 179, 113));
			btnAdd.setBounds(227, 319, 371, 33);
			contentPane.add(btnAdd);
			btnAdd.addActionListener(e -> {
				try {
					rs1 = stmt.executeQuery("select count (eventName) from events where ID = "+sID);
					int a = 0;
					while(rs1.next()) {
						a = rs1.getInt(1);
					}
					if(a==0) {
					stmt.executeUpdate("insert into events(eventName,ID) values ('" + textField .getText() + "'," +sID+ ")");
					textField.setText("");
					}else {
					JOptionPane.showMessageDialog(frame, "Event name already exists");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        });

			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(false);
								label_1.setVisible(true);
								for(int a = 0; a<=172; a++) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(true);
								label_1.setVisible(false);
								for(int a = 172; a>=0; a--) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			
			frame.setVisible(true);
	 }
	 public static void removeEvent() throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
			
			JPanel panel = new JPanel();
//			panel.setBounds(0, 0, 172, 508);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblRemoveEvent = new JLabel("Remove Event");
			Icon delete = new ImageIcon(ImageIO.read(new File("Images/icons8-delete-30.png")));
			lblRemoveEvent.setIcon(delete);
			lblRemoveEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblRemoveEvent.setBounds(0, 211, 172, 55);
			lblRemoveEvent.setOpaque(true);
			lblRemoveEvent.setBackground(new Color(46, 139, 87));
			panel.add(lblRemoveEvent);
			
			JLabel lblAddEvent = new JLabel("Add Event");
			Icon add = new ImageIcon(ImageIO.read(new File("Images/icons8-plus-math-30.png")));
			lblAddEvent.setIcon(add);
			lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAddEvent.setBounds(0, 158, 172, 55);
			lblAddEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						addEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblAddEvent);
			
			JLabel lblHome = new JLabel("Home");
			Icon home = new ImageIcon(ImageIO.read(new File("Images/icons8-home-30.png")));
			lblHome.setIcon(home);
			lblHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblHome.setBounds(0, 105, 172, 55);
			lblHome.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblHome);
			
			JLabel lblLogout = new JLabel("Logout");
			Icon exit = new ImageIcon(ImageIO.read(new File("Images/icons8-exit-30.png")));
			lblLogout.setIcon(exit);
			lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLogout.setBounds(0, 453, 172, 55);
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblLogout);
			
			JLabel label_1 = new JLabel("");
			Icon menu = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label_1.setIcon(menu);
			label_1.setBounds(14, 16, 37, 33);
			panel.add(label_1);
			
			JLabel lblSettings = new JLabel("Settings");
			Icon settings = new ImageIcon(ImageIO.read(new File("Images/icons8-settings-filled-30.png")));
			lblSettings.setIcon(settings);
			lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSettings.setBounds(0, 267, 172, 55);
			lblSettings.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						settings();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblSettings);
			
			
			JLabel lblNewLabel_1 = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel_1.setIcon(gradient);
			lblNewLabel_1.setBounds(0, 0, 172, 508);
			panel.add(lblNewLabel_1);
			
			JLabel label = new JLabel("");
			Icon menu_1 = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label.setIcon(menu_1);
			label.setBounds(14, 16, 36, 33);
			contentPane.add(label);
			
			JLabel lblNewLabel = new JLabel("");
			Icon gradient_1 = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient_1);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(200, 180, 500, 260);
			contentPane.add(scrollPane);
			
			ArrayList<String> strList = new ArrayList<>(); 
			String b = "";
			int a = 0;
			try {
				rs1 = stmt.executeQuery("select count (eventName) from events where ID = "+ sID +"");
				while(rs1.next()) {
					a = rs1.getInt(1);
				}
				rs1 = stmt.executeQuery("select eventName from events where ID ="+sID+"");
				while(rs1.next()) {
					b = rs1.getString(1);
					strList.add(b);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    JList listd = new JList(strList.toArray());
			scrollPane.setViewportView(listd);
			listd.addListSelectionListener(e -> {
				  valueSelected = (String) listd.getSelectedValue();
			});
			
			JLabel lblEditEvent = new JLabel("Remove Event");
			lblEditEvent.setIcon(delete);
			lblEditEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEditEvent.setBounds(200, 133, 184, 33);
			contentPane.add(lblEditEvent);
			lblEditEvent.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					try {
						rs1 = stmt.executeQuery("Select eventID from events where eventName = '" + valueSelected + "' and ID = " + sID);
						while(rs1.next()) {
							sEventID = rs1.getInt(1);
						}
						stmt.executeQuery("Delete from events where eventID = '" + sEventID + "'");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.dispose();
					try {
						removeEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(false);
								label_1.setVisible(true);
								for(int a = 0; a<=172; a++) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(true);
								label_1.setVisible(false);
								for(int a = 172; a>=0; a--) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			frame.setVisible(true);
	 }
	 public static void editEvent(String valSelect) throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
	         
			JPanel panel = new JPanel();
//			panel.setBounds(0, 0, 172, 508);
			contentPane.add(panel);
			panel.setLayout(null);

			JLabel lblRemoveEvent = new JLabel("Remove Event");
			Icon delete = new ImageIcon(ImageIO.read(new File("Images/icons8-delete-30.png")));
			lblRemoveEvent.setIcon(delete);
			lblRemoveEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblRemoveEvent.setBounds(0, 211, 172, 55);
			lblRemoveEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						removeEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblRemoveEvent);
			
			JLabel lblAddEvent = new JLabel("Add Event");
			Icon add = new ImageIcon(ImageIO.read(new File("Images/icons8-plus-math-30.png")));
			lblAddEvent.setIcon(add);
			lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAddEvent.setBounds(0, 158, 172, 55);
			lblAddEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						addEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblAddEvent);
			
			JLabel lblHome = new JLabel("Home");
			Icon home = new ImageIcon(ImageIO.read(new File("Images/icons8-home-30.png")));
			lblHome.setIcon(home);
			lblHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblHome.setBounds(0, 105, 172, 55);
			lblHome.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblHome);
			
			JLabel lblLogout = new JLabel("Logout");
			Icon exit = new ImageIcon(ImageIO.read(new File("Images/icons8-exit-30.png")));
			lblLogout.setIcon(exit);
			lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLogout.setBounds(0, 453, 172, 55);
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblLogout);
			
			JLabel label_1 = new JLabel("");
			Icon menu = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label_1.setIcon(menu);
			label_1.setBounds(14, 16, 37, 33);
			panel.add(label_1);
			
			JLabel lblSettings = new JLabel("Settings");
			Icon settings = new ImageIcon(ImageIO.read(new File("Images/icons8-settings-filled-30.png")));
			lblSettings.setIcon(settings);
			lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSettings.setBounds(0, 267, 172, 55);
			lblSettings.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						settings();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblSettings);
			
			
			JLabel lblNewLabel_1 = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel_1.setIcon(gradient);
			lblNewLabel_1.setBounds(0, 0, 172, 508);
			panel.add(lblNewLabel_1);
			
			JLabel label = new JLabel("");
			Icon menu_1 = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label.setIcon(menu_1);
			label.setBounds(14, 16, 36, 33);
			contentPane.add(label);
			
			JLabel lblNewLabel = new JLabel("");
			Icon gradient_1 = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient_1);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblEventName = new JLabel("EDIT EVENT NAME");
			lblEventName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEventName.setBounds(227, 189, 372, 33);
			contentPane.add(lblEventName);
			
			JTextField textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField.setBounds(227, 244, 372, 33);
			contentPane.add(textField);
			
			JButton btnAdd = new JButton("EDIT");
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAdd.setBackground(new Color(60, 179, 113));
			btnAdd.setBounds(227, 319, 371, 33);
			contentPane.add(btnAdd);
			btnAdd.addActionListener(e-> {
				if(!textField.getText().equals("")) {
					try {
						rs1 = stmt.executeQuery("select count (eventName) from events where eventName = '"+textField.getText()+"'");
						int a = 0;
						while(rs1.next()) {
							a = rs1.getInt(1); 
						}
						if(a==0) {
							stmt.executeUpdate("Update events set eventName ='"+textField.getText()+"' where eventName = '"+valSelect+"' and ID ="+sID);
							textField.setText("");
						}else {
							JOptionPane.showMessageDialog(frame, "Event name already exists");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(frame, "Not a valid name");
				}
			});
			
			
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(false);
								label_1.setVisible(true);
								for(int a = 0; a<=172; a++) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(true);
								label_1.setVisible(false);
								for(int a = 172; a>=0; a--) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			frame.setVisible(true);
	 }
	 public static void settings() throws IOException {
		 JFrame frame = new JFrame();
		 	try {
				frame.setIconImage(ImageIO.read(new File("Images/SuncoastIcon.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				};
			frame.setBackground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 855, 545);
			JPanel contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	         frame.setLocation(dim.width/2-frame.getSize().width/2,dim.height/2-frame.getSize().height/2);
	         frame.setResizable(false);
			
			JPanel panel = new JPanel();
//			panel.setBounds(0, 0, 172, 508);
			contentPane.add(panel);
			panel.setLayout(null);

			JLabel lblRemoveEvent = new JLabel("Remove Event");
			Icon delete = new ImageIcon(ImageIO.read(new File("Images/icons8-delete-30.png")));
			lblRemoveEvent.setIcon(delete);
			lblRemoveEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblRemoveEvent.setBounds(0, 211, 172, 55);
			lblRemoveEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						removeEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblRemoveEvent);
			
			JLabel lblAddEvent = new JLabel("Add Event");
			Icon add = new ImageIcon(ImageIO.read(new File("Images/icons8-plus-math-30.png")));
			lblAddEvent.setIcon(add);
			lblAddEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAddEvent.setBounds(0, 158, 172, 55);
			lblAddEvent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						addEvent();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblAddEvent);
			
			JLabel lblHome = new JLabel("Home");
			Icon home = new ImageIcon(ImageIO.read(new File("Images/icons8-home-30.png")));
			lblHome.setIcon(home);
			lblHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblHome.setBounds(0, 105, 172, 55);
			lblHome.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblHome);
			
			JLabel lblLogout = new JLabel("Logout");
			Icon exit = new ImageIcon(ImageIO.read(new File("Images/icons8-exit-30.png")));
			lblLogout.setIcon(exit);
			lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLogout.setBounds(0, 453, 172, 55);
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
					try {
						login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panel.add(lblLogout);
			
			JLabel label_1 = new JLabel("");
			Icon menu = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label_1.setIcon(menu);
			label_1.setBounds(14, 16, 37, 33);
			panel.add(label_1);
			
			JLabel lblSettings = new JLabel("Settings");
			Icon settings = new ImageIcon(ImageIO.read(new File("Images/icons8-settings-filled-30.png")));
			lblSettings.setIcon(settings);
			lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSettings.setBounds(0, 267, 172, 55);
			lblSettings.setOpaque(true);
			lblSettings.setBackground(new Color(46, 139, 87));
			panel.add(lblSettings);
			
			
			JLabel lblNewLabel_1 = new JLabel("");
			Icon gradient = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel_1.setIcon(gradient);
			lblNewLabel_1.setBounds(0, 0, 172, 508);
			panel.add(lblNewLabel_1);
			
			JLabel label = new JLabel("");
			Icon menu_1 = new ImageIcon(ImageIO.read(new File("Images/icons8-menu-30.png")));
			label.setIcon(menu_1);
			label.setBounds(14, 16, 36, 33);
			contentPane.add(label);
			
			JLabel lblNewLabel = new JLabel("");
			Icon gradient_1 = new ImageIcon(ImageIO.read(new File("Images/GradientGreen2.PNG")));
			lblNewLabel.setIcon(gradient_1);
			lblNewLabel.setBounds(0, 0, 840, 105);
			contentPane.add(lblNewLabel);
			
			JLabel lblChangePassword = new JLabel("NEW PASSWORD");
			lblChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblChangePassword.setBounds(227, 161, 123, 33);
			contentPane.add(lblChangePassword);
			
			JLabel lblConfirmpassword = new JLabel("CONFIRM PASSWORD");
			lblConfirmpassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblConfirmpassword.setBounds(227, 258, 161, 33);
			contentPane.add(lblConfirmpassword);
			
			JPasswordField passwordField = new JPasswordField();
			passwordField.setOpaque(false);
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			passwordField.setColumns(10);
			passwordField.setBounds(227, 197, 372, 33);
			contentPane.add(passwordField);
			
			JPasswordField passwordField_1 = new JPasswordField();
			passwordField_1.setOpaque(false);
			passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			passwordField_1.setColumns(10);
			passwordField_1.setBounds(227, 293, 372, 33);
			contentPane.add(passwordField_1);
			
			JButton button = new JButton("RESET");
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Tahoma", Font.PLAIN, 15));
			button.setBackground(new Color(60, 179, 113));
			button.setBounds(289, 384, 262, 33);
			button.addActionListener(e -> {
				if((passwordField.getText().equals("")||passwordField_1.getText().equals(""))) {
	        		JOptionPane.showMessageDialog(frame, "Enter valid password");
	        	}	
	        	else if(!passwordField.getText().equals(passwordField_1.getText())) {
	        		JOptionPane.showMessageDialog(frame, "Passwords must match");
	        	}
	            else {
	            	try {
						stmt.executeUpdate("Update users set password = '"+passwordField.getText()+"' where ID ="+sID+"");
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	            	JOptionPane.showMessageDialog(frame, "Passwords was reset");
	            	passwordField.setText("");
	            	passwordField_1.setText("");
	                try {
						mainMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                frame.dispose();
	            }
	        });
			contentPane.add(button);
			
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(false);
								label_1.setVisible(true);
								for(int a = 0; a<=172; a++) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Thread th = new Thread() {
						public void run() {
							try {
								label.setVisible(true);
								label_1.setVisible(false);
								for(int a = 172; a>=0; a--) {
									Thread.sleep(2);
									panel.setBounds(0,0,a,508);
								}
								
							}
							catch(Exception e){
								System.out.println(e);
							}
						}
					};th.start();
				}
			});
			frame.setVisible(true);
			
	 }
	 }
