package project11_27;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StarbucksEmpSearch extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField tfSearch;
	private JLabel lblPosition;
	private JLabel lblEno;
	private JLabel lblEname;
	private JLabel lblSal;
	private JLabel lblPhone;
	private JLabel lblLocation;
	private JLabel lblSex;

	private DefaultTableModel model;
	private StarbucksEmpDTO dto;
	private StarbucksEmpDAO dao;
	private Vector<String> col;
	private StarbucksEmpList sel;
	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					StarbucksEmpSearch frame = new StarbucksEmpSearch();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	public StarbucksEmpSearch(StarbucksEmpList sel) {
		this();
		this.sel=sel;
	}
	/**
	 * Create the frame.
	 */
	public StarbucksEmpSearch() {
		setTitle("직원 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 926, 587);
		contentPane.add(panel);
		panel.setLayout(null);
		scrollPane.setBounds(12, 75, 654, 502);
		panel.add(scrollPane);

		dao=new StarbucksEmpDAO();
		col=new Vector<String>();
		col.add("사원번호");
		col.add("직책");
		col.add("이름");
		col.add("급여");
		col.add("연락처");
		col.add("근무 지점");
		col.add("성별");

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				lblEno.setText(table.getValueAt(idx, 0)+"");
				lblPosition.setText(table.getValueAt(idx, 1)+"");
				lblEname.setText(table.getValueAt(idx, 2)+"");
				lblSal.setText(table.getValueAt(idx, 3)+"");
				lblPhone.setText(table.getValueAt(idx, 4)+"");
				lblLocation.setText(table.getValueAt(idx, 5)+"");
				lblSex.setText(table.getValueAt(idx, 6)+"");
				if(lblSex.getText().equals("남")) {
					lblSex.setForeground(Color.blue);
				}else if(lblSex.getText().equals("여")){
					lblSex.setForeground(Color.red);
				}else {
					lblSex.setForeground(Color.black);
				}
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		JButton btnExit = new JButton("나가기");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.GRAY);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(744, 544, 154, 23);
		panel.add(btnExit);

		String[] co= {"지점명", "사원명","직책"};
		JComboBox comSearch = new JComboBox(co);
		comSearch.setForeground(Color.WHITE);
		comSearch.setBackground(Color.GRAY);
		comSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg=(String)comSearch.getSelectedItem().toString();
			}
		});
		comSearch.setBounds(62, 24, 99, 23);
		panel.add(comSearch);

		tfSearch = new JTextField();
		tfSearch.setBounds(189, 25, 223, 21);
		panel.add(tfSearch);
		tfSearch.setColumns(10);

		JButton btnSearch = new JButton("검색");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.GRAY);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg=(String)comSearch.getSelectedItem().toString();
				System.out.println(msg);
				switch(msg) {
				case "지점명" : 
					search_location();
					break;

				case "사원명" :
					search_name();
					break;
				case "직책":
					search_position();
					break;
				}
			}
		});
		btnSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String msg=(String)comSearch.getSelectedItem().toString();
				System.out.println(msg);
				switch(msg) {
				case "지점명" : 
					search_location();
					break;
				case "사원명" :
					search_name();
					break;
				case "직책":
					search_position();
					break;
				}
			}
		});

		btnSearch.setBounds(460, 24, 135, 23);
		panel.add(btnSearch);

		JLabel lblNewLabel = new JLabel("사원 번호 :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(680, 90, 72, 23);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("직책        :");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(680, 139, 87, 23);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("이름        :");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(678, 188, 89, 23);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("급여        :");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(678, 238, 89, 23);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("연락처     :");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(678, 290, 89, 23);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("근무 지점  :");
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(678, 338, 89, 23);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("성별        :");
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(678, 391, 89, 15);
		panel.add(lblNewLabel_6);

		lblEno = new JLabel("");
		lblEno.setFont(new Font("굴림", Font.BOLD, 15));
		lblEno.setBounds(791, 92, 79, 19);
		panel.add(lblEno);

		lblPosition = new JLabel("");
		lblPosition.setFont(new Font("굴림", Font.BOLD, 15));
		lblPosition.setBounds(791, 141, 79, 19);
		panel.add(lblPosition);

		lblEname = new JLabel("");
		lblEname.setFont(new Font("굴림", Font.BOLD, 15));
		lblEname.setBounds(791, 190, 79, 19);
		panel.add(lblEname);

		lblSal = new JLabel("");
		lblSal.setFont(new Font("굴림", Font.BOLD, 15));
		lblSal.setBounds(791, 240, 79, 19);
		panel.add(lblSal);

		lblPhone = new JLabel("");
		lblPhone.setFont(new Font("굴림", Font.BOLD, 15));
		lblPhone.setBounds(791, 292, 135, 19);
		panel.add(lblPhone);

		lblLocation = new JLabel("");
		lblLocation.setFont(new Font("굴림", Font.BOLD, 15));
		lblLocation.setBounds(791, 340, 107, 19);
		panel.add(lblLocation);

		lblSex = new JLabel("");
		lblSex.setFont(new Font("굴림", Font.BOLD, 15));
		lblSex.setBounds(791, 388, 79, 21);
		panel.add(lblSex);

		JLabel lblImage = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\19.11.27\\starlogo.png"));
		lblImage.setBounds(649, 0, 277, 79);
		panel.add(lblImage);
	}

	//이름으로 찾기
	public void search_name() {
		String ename=tfSearch.getText();
		model=new DefaultTableModel(dao.searchStarbucksEmp_n(ename),col);
		table.setModel(model);
	}

	//지점으로 찾기
	public void search_location() {
		String location=tfSearch.getText();
		model=new DefaultTableModel(dao.searchStarbucksEmp_l(location),col);
		table.setModel(model);
	}

	public void search_position() {
		String position=tfSearch.getText();
		model=new DefaultTableModel(dao.searchStarbucksEmp_P(position),col);
		table.setModel(model);
	}
	public void list() {
		model=new DefaultTableModel(dao.listStarbucksEmp(), col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}
