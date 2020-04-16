package project11_27;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StarbucksLoginSearchPw extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserid;
	private JTextField tfPhone;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private StarbucksLogin sl;
	private String sexx;
	private DefaultTableModel model;
	private Vector<String> col;
	private String password, userid, sex, phone;
	private StarbucksLoginDTO dto;
	private StarbucksEmpDAO dao;
		

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					StarbucksLoginSearchPw frame = new StarbucksLoginSearchPw();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	public StarbucksLoginSearchPw(StarbucksLogin sl) {
		this();
		this.sl=sl;
	}
	/**
	 * Create the frame.
	 */
	public StarbucksLoginSearchPw() {
		setTitle("비밀번호 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		JLabel lblUserid = new JLabel("아이디");
		lblUserid.setFont(new Font("굴림", Font.PLAIN, 19));
		lblUserid.setForeground(Color.WHITE);
		lblUserid.setBackground(Color.WHITE);
		lblUserid.setBounds(27, 53, 75, 23);
		contentPane.add(lblUserid);	

		tfUserid = new JTextField();
		tfUserid.setFont(new Font("굴림", Font.PLAIN, 19));
		tfUserid.setForeground(Color.WHITE);
		tfUserid.setBackground(Color.GRAY);
		tfUserid.setBounds(132, 56, 135, 21);
		contentPane.add(tfUserid);
		tfUserid.setColumns(10);

		JLabel lblPhone = new JLabel("연락처");
		lblPhone.setFont(new Font("굴림", Font.PLAIN, 19));
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(27, 99, 75, 23);
		contentPane.add(lblPhone);

		tfPhone = new JTextField();
		tfPhone.setFont(new Font("굴림", Font.PLAIN, 19));
		tfPhone.setForeground(Color.WHITE);
		tfPhone.setBackground(Color.GRAY);
		tfPhone.setBounds(132, 102, 135, 21);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);

		JLabel lblNewLabel = new JLabel("(*'-'를 포함하여 적어주십시오)");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(78, 133, 219, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("성별");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(27, 172, 75, 19);
		contentPane.add(lblNewLabel_1);

		JRadioButton rdbtnMan = new JRadioButton("남");
		rdbtnMan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					String sexx="남";
					System.out.println(sexx);
				}
			}
		});
		buttonGroup.add(rdbtnMan);
		rdbtnMan.setBackground(Color.GRAY);
		rdbtnMan.setFont(new Font("굴림", Font.PLAIN, 19));
		rdbtnMan.setForeground(Color.BLUE);
		rdbtnMan.setBounds(145, 170, 49, 23);
		contentPane.add(rdbtnMan);

		JRadioButton rdbtnWoman = new JRadioButton("여");
		rdbtnWoman.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					String sexx="여";
					System.out.println(sexx);
				}
			}
		});
		buttonGroup.add(rdbtnWoman);
		rdbtnWoman.setBackground(Color.GRAY);
		rdbtnWoman.setForeground(Color.RED);
		rdbtnWoman.setFont(new Font("굴림", Font.PLAIN, 19));
		rdbtnWoman.setBounds(218, 170, 49, 23);
		contentPane.add(rdbtnWoman);

		JButton btnNewButton = new JButton("확인");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid=tfUserid.getText();
				String phone=tfPhone.getText();
				String sex=sexx;
				dao.searchPassword1(userid, phone, sex);
				System.out.println(dto);
				String password =dto.getPassword();
//				if(password==dto.getPassword()) {
//					String password=dao.searchPassword(userid, phone, sex);
//					JOptionPane.showMessageDialog(StarbucksLoginSearchPw.this, userid+ "님의 비밀번호는 "+password
//					+"입니다.");
				
//					dispose();
//					
//				}else {
//					JOptionPane.showMessageDialog(StarbucksLoginSearchPw.this, "일치하는 정보가 없습니다.");
//		
//				}
//				StarbucksLoginDTO dto=new StarbucksLoginDTO();
//				Connection conn=null;
//				PreparedStatement pstmt=null;
//				ResultSet rs=null;
//
//				col=new Vector();
//				col.add("이름");
//				col.add("아이디");
//				col.add("비밀번호");
//				col.add("나이");
//				col.add("연락처");
//				col.add("주소");
//				col.add("성별");
//
//				try {
//					StarbucksEmpDAO daoo=new StarbucksEmpDAO();
//					conn=daoo.dbConn();
//					String sql="select password from starbuckslogin where userid=? and phone=? and sex=? ";
//					pstmt=conn.prepareStatement(sql);
//					System.out.println(sql);
//					pstmt.setString(1,userid);
//					pstmt.setString(2, phone);
//					pstmt.setString(3, sex);
//					rs=pstmt.executeQuery();
//					if(rs.next()) {
//						DefaultTableModel model=new DefaultTableModel(dao.searchStarbucksLogin(userid, phone, sex),col);
//						String password=model.getValueAt(0, 2)+"";
//						JOptionPane.showMessageDialog(StarbucksLoginSearchPw.this, userid+ "님의 비밀번호는 "+password+"입니다.");
//						dispose();
//
//					}else {
//						JOptionPane.showMessageDialog(StarbucksLoginSearchPw.this, "일치하는 정보가 없습니다.");
//					}
//
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(44, 246, 97, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(170, 246, 97, 29);
		contentPane.add(btnNewButton_1);


		JLabel lblImage = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\19.11.27\\star4.png"));
		lblImage.setBackground(Color.GRAY);
		lblImage.setBounds(-23, 0, 626, 318);
		contentPane.add(lblImage);
	}
}
