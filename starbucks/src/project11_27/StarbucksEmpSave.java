package project11_27;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class StarbucksEmpSave extends JFrame {

	private JPanel contentPane;
	private StarbucksEmpList sel;
	private StarbucksEmpDTO dto;
	private	StarbucksEmpDAO dao;
	private Vector<String> col;
	private String sexx;
	
	private JTextField tfEno;
	private JTextField tfPosition;
	private JTextField tfename;
	private JTextField tfSal;
	private JTextField tfPhone;
	private JTextField tfLocation;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StarbucksEmpSave frame = new StarbucksEmpSave();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public StarbucksEmpSave(StarbucksEmpList sel) {
				this.sel=sel;

		setTitle("사원등록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 339, 674);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("사원번호");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(29, 42, 100, 27);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("직책");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_2.setBounds(29, 90, 100, 27);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("사원 이름");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_3.setBounds(29, 139, 100, 27);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("급여");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_4.setBounds(29, 211, 100, 27);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("연락처");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_5.setBounds(29, 267, 100, 27);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("근무 지점");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_6.setBounds(29, 318, 100, 28);
		panel.add(lblNewLabel_6);

		tfEno = new JTextField();
		tfEno.setBackground(Color.LIGHT_GRAY);
		tfEno.setFont(new Font("굴림", Font.BOLD, 18));
		tfEno.setBounds(155, 41, 154, 27);
		panel.add(tfEno);
		tfEno.setColumns(10);

		tfPosition = new JTextField();
		tfPosition.setBackground(Color.LIGHT_GRAY);
		tfPosition.setFont(new Font("굴림", Font.BOLD, 18));
		tfPosition.setBounds(155, 90, 154, 26);
		panel.add(tfPosition);
		tfPosition.setColumns(10);

		tfename = new JTextField();
		tfename.setBackground(Color.LIGHT_GRAY);
		tfename.setFont(new Font("굴림", Font.BOLD, 18));
		tfename.setBounds(155, 139, 154, 26);
		panel.add(tfename);
		tfename.setColumns(10);

		tfSal = new JTextField();
		tfSal.setFont(new Font("굴림", Font.BOLD, 18));
		tfSal.setBackground(Color.LIGHT_GRAY);
		tfSal.setBounds(155, 211, 154, 26);
		panel.add(tfSal);
		tfSal.setColumns(10);

		tfPhone = new JTextField();
		tfPhone.setBackground(Color.LIGHT_GRAY);
		tfPhone.setFont(new Font("굴림", Font.BOLD, 18));
		tfPhone.setBounds(155, 267, 154, 26);
		panel.add(tfPhone);
		tfPhone.setColumns(10);

		tfLocation = new JTextField();
		tfLocation.setFont(new Font("굴림", Font.BOLD, 18));
		tfLocation.setBackground(Color.LIGHT_GRAY);
		tfLocation.setBounds(155, 318, 154, 27);
		panel.add(tfLocation);
		tfLocation.setColumns(10);

		JLabel lblLogo = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\19.11.27\\star2.png"));
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result=0;
				int response=JOptionPane.showConfirmDialog(StarbucksEmpSave.this, "등록하시겠습니까");
				if(response==JOptionPane.YES_OPTION) {
					int eno=Integer.parseInt(tfEno.getText());
					String position=tfPosition.getText();
					String ename= tfename.getText();
					int sal=Integer.parseInt(tfSal.getText());
					String phone=tfPhone.getText();
					String location=tfLocation.getText();
					String sex=sexx;
					dto=new StarbucksEmpDTO(eno, sal, position, ename, phone, location, sex);
					dao=new StarbucksEmpDAO();
					result=dao.insertStarbucksEmp(dto);
				}else if(response==JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(StarbucksEmpSave.this, "등록이 되지않았습니다.");
				}
				if(result==1) {
					JOptionPane.showMessageDialog(StarbucksEmpSave.this, "등록되었습니다");
					sel.refreshTable();
					list();

				}
			dispose();
			}
		});

		lblLogo.setBackground(Color.LIGHT_GRAY);

		lblLogo.setBounds(0, 374, 339, 300);
		panel.add(lblLogo);

		JRadioButton rdbtnMan = new JRadioButton("남");
		rdbtnMan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					 sexx="남";
			}
			}
		});
		buttonGroup.add(rdbtnMan);
		rdbtnMan.setForeground(Color.BLUE);
		rdbtnMan.setBackground(Color.LIGHT_GRAY);
		rdbtnMan.setFont(new Font("굴림", Font.PLAIN, 15));
		rdbtnMan.setBounds(161, 171, 59, 23);
		panel.add(rdbtnMan);

		JRadioButton rdbtnWoman = new JRadioButton("여");
		rdbtnWoman.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					 sexx="여";
			}
			}
		});
		buttonGroup.add(rdbtnWoman);
		rdbtnWoman.setForeground(Color.RED);
		rdbtnWoman.setBackground(Color.LIGHT_GRAY);
		rdbtnWoman.setFont(new Font("굴림", Font.PLAIN, 15));
		rdbtnWoman.setBounds(234, 171, 75, 23);
		panel.add(rdbtnWoman);
	}
	public void list() {
		DefaultTableModel model=new DefaultTableModel(dao.listStarbucksEmp(),col) {
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}


		};
	}
}
