import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JLabel;


/*
 * try {
			//"C:\Program Files\MariaDB 10.9\bin"
			Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"cd C:\\Program Files\\MariaDB 10.9\\bin");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 */


public class MainFrame extends JFrame {

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * try {
					Class.forName("org.mariadb.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","teoria");
				Statement st=con.createStatement();
				String query="SELECT * FROM employees";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String [cols];
				for(int i=0; i<cols;i++)
					colName[i]=rsmd.getColumnClassName(i+1);
				model.setColumnIdentifiers(colName);
				String id,name;
				while(rs.next()) {
					id=rs.getString(1);
					name=rs.getString(2);
					String[] row= {id,name};
					model.addRow(row);
					
					
					
				}
				st.close();
				con.close();
				
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	 */
	public MainFrame() {
		int tipo;
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 354);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 102));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("Cliente");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(95, 192, 181, 36);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteWnd nw = new ClienteWnd();
				
				
				setVisible(false);
				nw.NewScreen(1);
				
				
			}
		});
		panel.setLayout(null);
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(77, 93, 83));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Administrador ");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(339, 192, 181, 36);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aqui se abre la linea de comandos de el pc
				
				try {
					//"C:\Program Files\MariaDB 10.9\bin"
					Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"cd C:\\Program Files\\MariaDB 10.9\\bin");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(77, 93, 83));
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/large.png"));
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(205, 9, 255, 173);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("Empleado");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
               ClienteWnd nw = new ClienteWnd();
				
				
				setVisible(false);
				nw.NewScreen(2);
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1_1.setBackground(new Color(77, 93, 83));
		btnNewButton_1_1.setBounds(95, 256, 181, 36);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Empleado/Transportista");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ClienteWnd nw = new ClienteWnd();
				
				
				setVisible(false);
				nw.NewScreen(3);
			}
		});
		btnNewButton_1_2.setForeground(Color.WHITE);
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1_2.setBackground(new Color(77, 93, 83));
		btnNewButton_1_2.setBounds(339, 256, 181, 36);
		panel.add(btnNewButton_1_2);
	}
}
