package project11_27;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class StarbucksLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserid;
	private JPasswordField tfpassword;
	private StarbucksEmpDAO dao;
	private StarbucksEmpDTO dto;
	private StarbucksEmpList sl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StarbucksLogin frame = new StarbucksLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StarbucksLogin() {
		setTitle("스타벅스 코리아");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 361);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbluserid = new JLabel("아이디");
		lbluserid.setFont(new Font("굴림", Font.BOLD, 18));
		lbluserid.setForeground(Color.WHITE);
		lbluserid.setBounds(31, 81, 78, 21);
		contentPane.add(lbluserid);
		
		JLabel lblpassword = new JLabel("비밀번호");
		lblpassword.setFont(new Font("굴림", Font.BOLD, 18));
		lblpassword.setForeground(Color.WHITE);
		lblpassword.setBounds(31, 128, 78, 21);
		contentPane.add(lblpassword);
		
		tfUserid = new JTextField();
		tfUserid.setFont(new Font("굴림", Font.PLAIN, 18));
		tfUserid.setBackground(Color.GRAY);
		tfUserid.setBounds(147, 81, 128, 23);
		contentPane.add(tfUserid);
		tfUserid.setColumns(10);
		
		tfpassword = new JPasswordField();
		tfpassword.setFont(new Font("굴림", Font.PLAIN, 18));
		tfpassword.setBackground(Color.GRAY);
		tfpassword.setBounds(147, 128, 128, 23);
		contentPane.add(tfpassword);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tfUserid.getText();
				String pw=String.valueOf(tfpassword.getPassword());
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				try {
					StarbucksEmpDAO dao=new StarbucksEmpDAO();
					conn=dao.dbConn();
					String sql ="select userid, password from starbuckslogin where userid=? and password=? ";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
					
					rs=pstmt.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(StarbucksLogin.this, id+ "님 로그인 되셨습니다.");
						StarbucksEmpList sl=new StarbucksEmpList(StarbucksLogin.this);
						sl.setVisible(true);
						sl.setLocation(350,350);
						dispose();

					}else {
						JOptionPane.showMessageDialog(StarbucksLogin.this, "아이디 또는 비밀번호가 틀렸습니다.");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setBounds(23, 215, 112, 38);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("나가기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(163, 215, 112, 38);
		contentPane.add(btnExit);
		
		JLabel lblLogo = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\19.11.27\\star4.png"));
		lblLogo.setBackground(Color.GRAY);
		lblLogo.setBounds(0, 0, 580, 327);
		contentPane.add(lblLogo);
	}
}
