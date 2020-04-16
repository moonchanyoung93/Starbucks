package project11_27;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class StarbucksEmpUpdate extends JFrame {

	private JPanel contentPane;
	private StarbucksEmpList sel;
	private String ename;
	private JPanel panel;

	private JTextField tfEno;
	private JTextField tfPosition;
	private JTextField tfEname;
	private JTextField tfSal;
	private JTextField tfPhone;
	private JTextField tfLocation;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private StarbucksEmpDAO dao;
	private StarbucksEmpDTO dto;
	private JRadioButton rdbtnMan;
	private JRadioButton rdbtnWoman;
	private Vector col;
	private String sexx;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					StarbucksEmpUpdate frame = new StarbucksEmpUpdate();
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
	public StarbucksEmpUpdate(StarbucksEmpList sel, String ename) {
		this.sel=sel;
		this.ename=ename;

		setBackground(Color.DARK_GRAY);
		setTitle("사원수정");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 553);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblLogo = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\19.11.27\\star5.png"));
		lblLogo.setBounds(0, 264, 310, 218);
		contentPane.add(lblLogo);

		dao=new StarbucksEmpDAO();
		dto=dao.viewStarbucksEmp(ename);


		JButton btnExit = new JButton("닫기");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.GRAY);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(193, 477, 97, 23);
		contentPane.add(btnExit);

		JLabel lblNewLabel = new JLabel("사원번호");
		lblNewLabel.setBounds(32, 25, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("직책");
		lblNewLabel_1.setBounds(32, 60, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setBounds(32, 98, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("급여");
		lblNewLabel_3.setBounds(32, 134, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("연락처");
		lblNewLabel_4.setBounds(32, 173, 57, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("근무지점");
		lblNewLabel_5.setBounds(32, 216, 57, 15);
		contentPane.add(lblNewLabel_5);

		tfEno = new JTextField();
		tfEno.setBackground(Color.LIGHT_GRAY);
		tfEno.setBounds(120, 22, 116, 21);
		contentPane.add(tfEno);
		tfEno.setColumns(10);
		tfEno.setEditable(false);
		tfEno.setText(dto.getEno()+"");

		tfPosition = new JTextField();
		tfPosition.setBackground(Color.LIGHT_GRAY);
		tfPosition.setBounds(120, 57, 116, 21);
		contentPane.add(tfPosition);
		tfPosition.setColumns(10);
		tfPosition.setText(dto.getPosition());

		tfEname = new JTextField();
		tfEname.setBackground(Color.LIGHT_GRAY);
		tfEname.setBounds(120, 95, 116, 21);
		contentPane.add(tfEname);
		tfEname.setColumns(10);
		tfEname.setText(dto.getEname());


		tfSal = new JTextField();
		tfSal.setBackground(Color.LIGHT_GRAY);
		tfSal.setBounds(120, 131, 116, 21);
		contentPane.add(tfSal);
		tfSal.setColumns(10);
		tfSal.setText(dto.getSal()+"");


		tfPhone = new JTextField();
		tfPhone.setBackground(Color.LIGHT_GRAY);
		tfPhone.setBounds(120, 170, 116, 21);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		tfPhone.setText(dto.getPhone());

		tfLocation = new JTextField();
		tfLocation.setBackground(Color.LIGHT_GRAY);
		tfLocation.setBounds(120, 213, 116, 21);
		contentPane.add(tfLocation);
		tfLocation.setColumns(10);
		tfLocation.setText(dto.getLocation());



		rdbtnWoman = new JRadioButton("여");
		buttonGroup.add(rdbtnWoman);
		rdbtnWoman.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					 sexx="여";
				}
			}
		});
		rdbtnWoman.setBackground(Color.LIGHT_GRAY);
		rdbtnWoman.setForeground(Color.RED);
		rdbtnWoman.setBounds(169, 244, 51, 23);
		contentPane.add(rdbtnWoman);



		System.out.println(dto.getSex());
		rdbtnMan = new JRadioButton("남");
		buttonGroup.add(rdbtnMan);
		rdbtnMan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					 sexx="남";
				}
			}
		});
		if(dto.getSex().equals("남")) {
			rdbtnMan.setSelected(true);
		}else {
			rdbtnWoman.setSelected(true);
		}
		rdbtnMan.setBackground(new Color(192, 192, 192));
		rdbtnMan.setForeground(Color.BLUE);
		rdbtnMan.setBounds(120, 244, 45, 23);
		contentPane.add(rdbtnMan);


		JButton btnSave = new JButton("완료");
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.GRAY);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eno=Integer.parseInt(tfEno.getText());
				String position=tfPosition.getText();
				String ename=tfEname.getText();
				int sal=Integer.parseInt(tfSal.getText());
				String phone=tfPhone.getText();
				String location=tfLocation.getText();
                String sex=sexx;
                dto=new StarbucksEmpDTO(eno, sal, position, ename, phone, location, sex);
				int result=dao.updateStarbucksEmp(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(StarbucksEmpUpdate.this, "수정되었습니다.");
					sel.refreshTable();
					dispose();
				}
			}
		});
		btnSave.setBounds(32, 477, 97, 23);
		contentPane.add(btnSave);

		JPanel panel = new JPanel();
		this.panel=panel;
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 322, 515);
		contentPane.add(panel);


	}
}
