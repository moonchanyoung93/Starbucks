package project11_27;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class StarbucksEmpList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Image img;
	private StarbucksEmpDTO dto;
	private StarbucksEmpDAO dao;
	private Vector<String> col;
	private DefaultTableModel model;
	private JLabel lblEno;
	private JLabel lblPosition;
	private JLabel lblEname;
	private JLabel lblSal;
	private JLabel lblPhone;
	private JLabel lblLocation;
	private JLabel lblsex;
	private StarbucksEmpList sel;
	private StarbucksEmpUpdate seu;
	private StarbucksLogin sl;
	private String userid;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					StarbucksEmpList frame = new StarbucksEmpList();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}


	public StarbucksEmpList(StarbucksLogin sl) {
		this();
		this.sl=sl;
	}

	/**
	 * Create the frame.
	 */
	public StarbucksEmpList() {
		setTitle("스타벅스 사원관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 648);
		contentPane = new JPanel();
		contentPane.setBackground(Color.gray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao=new StarbucksEmpDAO();
		col=new Vector<String>();
		col.add("사원번호");
		col.add("직책");
		col.add("이름");
		col.add("급여");
		col.add("연락처");
		col.add("근무 지점");
		col.add("성별");
		list();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.white);
		scrollPane.setBounds(12, 252, 631, 315);
		contentPane.add(scrollPane);

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row();
				if(lblsex.getText().equals("남")) {
					lblsex.setForeground(Color.blue);
				}else if(lblsex.getText().equals("여")){
					lblsex.setForeground(Color.red);
				}else {
					lblsex.setForeground(Color.black);
				}

			}
		});
		table.setBackground(Color.white);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(12, 10, 631, 232);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbllogo = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\19.11.27\\star3.jpg"));
		lbllogo.setBounds(0, 29, 215, 158);
		panel.add(lbllogo);

		JLabel label = new JLabel("사원번호");
		label.setFont(new Font("굴림", Font.PLAIN, 15));
		label.setBounds(212, 20, 77, 23);
		panel.add(label);

		JLabel lblNewLabel = new JLabel("직책");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(212, 53, 77, 24);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(212, 87, 77, 21);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("급여");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(212, 118, 77, 23);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("연락처");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(212, 151, 77, 23);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("근무 지점");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(209, 184, 80, 28);
		panel.add(lblNewLabel_4);

		lblEno = new JLabel("");
		lblEno.setFont(new Font("굴림", Font.PLAIN, 15));
		lblEno.setBounds(318, 24, 94, 15);
		panel.add(lblEno);

		lblPosition = new JLabel("");
		lblPosition.setFont(new Font("굴림", Font.BOLD, 15));
		lblPosition.setBounds(318, 58, 94, 15);
		panel.add(lblPosition);

		lblEname = new JLabel("");
		lblEname.setFont(new Font("굴림", Font.PLAIN, 15));
		lblEname.setBounds(318, 90, 57, 15);
		panel.add(lblEname);

		lblSal = new JLabel("");
		lblSal.setFont(new Font("굴림", Font.PLAIN, 15));
		lblSal.setBounds(318, 122, 57, 15);
		panel.add(lblSal);

		lblPhone = new JLabel("");
		lblPhone.setFont(new Font("굴림", Font.PLAIN, 15));
		lblPhone.setBounds(318, 155, 145, 15);
		panel.add(lblPhone);

		lblLocation = new JLabel("");
		lblLocation.setFont(new Font("굴림", Font.PLAIN, 15));
		lblLocation.setBounds(318, 191, 116, 15);
		panel.add(lblLocation);

		JButton btnUpdate = new JButton("사원수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				if(idx==-1) {
					JOptionPane.showMessageDialog(StarbucksEmpList.this, "사원을 선택하세요.");
					return;
				}else {
					String ename=String.valueOf(table.getValueAt(idx, 2));
					StarbucksEmpUpdate seu=new StarbucksEmpUpdate(StarbucksEmpList.this, ename);
					seu.setVisible(true);
					seu.setLocation(200, 200);
				}
			}
		});
		btnUpdate.setForeground(Color.white);
		btnUpdate.setBackground(Color.gray);
		btnUpdate.setBounds(494, 69, 97, 23);
		panel.add(btnUpdate);

		JButton btnSearch = new JButton("사원찾기");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarbucksEmpSearch sesc=new StarbucksEmpSearch(StarbucksEmpList.this);
				sesc.setVisible(true);
				sesc.setLocation(300, 300);
			}
		});
		btnSearch.setBackground(Color.gray);
		btnSearch.setForeground(Color.white);
		btnSearch.setBounds(494, 118, 97, 23);
		panel.add(btnSearch);

		JButton btnSave = new JButton("사원등록");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarbucksEmpSave ses=new StarbucksEmpSave(StarbucksEmpList.this);
				ses.setVisible(true);
				ses.setLocation(300, 300);
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.gray);
		btnSave.setBounds(494, 20, 97, 23);
		panel.add(btnSave);

		lblsex = new JLabel("");
		lblsex.setFont(new Font("굴림", Font.PLAIN, 15));


		lblsex.setBounds(398, 90, 57, 15);
		panel.add(lblsex);

		JButton btnDelete = new JButton("사원삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				if(idx==-1) {
					JOptionPane.showMessageDialog(StarbucksEmpList.this, "삭제할 사원을 선택하세요.");
					return;
				}else {
					String ename=lblEname.getText();
					int result=0;
					int response=JOptionPane.showConfirmDialog(StarbucksEmpList.this, "삭제하시겠습니까?");
					if(response==JOptionPane.YES_OPTION) {
						result=dao.deleteStarbucksEmp(ename);
						list();
						JOptionPane.showMessageDialog(StarbucksEmpList.this, "삭제되었습니다.");
						table.setModel(model);
						lblEno.setText("");
						lblPosition.setText("");
						lblEname.setText("");
						lblSal.setText("");
						lblPhone.setText("");
						lblLocation.setText("");
						lblsex.setText("");
						lblEname.requestFocus();
					}
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.GRAY);
		btnDelete.setBounds(494, 164, 97, 23);
		panel.add(btnDelete);

		JButton btnNewButton = new JButton("나가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(Color.white);
		btnNewButton.setBounds(495, 577, 148, 23);
		contentPane.add(btnNewButton);

		JButton btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response=JOptionPane.showConfirmDialog(StarbucksEmpList.this, "로그아웃하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					StarbucksLogin sl=new StarbucksLogin();
					sl.setVisible(true);
					sl.setLocation(250, 250);
					dispose();
				}
			}
		});
		btnLogout.setBounds(22, 577, 148, 23);
		contentPane.add(btnLogout);
		refreshTable();
	}
	public void list() {
		model=new DefaultTableModel(dao.listStarbucksEmp(), col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listStarbucksEmp(),col);
		table.setModel(model);
	}

	public void row() {
		int idx=table.getSelectedRow();
		lblEno.setText(table.getValueAt(idx, 0)+"");
		lblPosition.setText(table.getValueAt(idx, 1)+"");
		lblEname.setText(table.getValueAt(idx, 2)+"");
		lblSal.setText(table.getValueAt(idx, 3)+"");
		lblPhone.setText(table.getValueAt(idx, 4)+"");
		lblLocation.setText(table.getValueAt(idx, 5)+"");
		lblsex.setText(table.getValueAt(idx, 6)+"");
		//	   String man=table.getValueAt(idx, 6)+"";
		//	   if(man=="남") {
		//		   lblsex.setForeground(Color.blue);
		//	   }

	}
}
