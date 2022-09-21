import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.lang.*;
import javax.swing.JLabel;

public class EmpBusq {

	private JFrame frame;
	private JTable table;
	static String userFP; 

	/**
	 * Launch the application.
	 */
	
	/*
	 * Create table ExEnvios (
    Numero_Orden int primary key,
    Excepcion varchar(50),
    Clasificacion varchar(50),
    Internacional varchar(50) not null,
    Declaracion varchar(50) not null);
	 */
	
	/*
	INSERT INTO ExEnvios (Numero_Orden, Excepcion, Clasificacion, Internacional, Declaracion) VALUES ('6','Material no Peligroso','Flamable ','no','bengala, polvora explosiva presio: 8$');
	 INSERT INTO ExEnvios (Numero_Orden, Excepcion, Clasificacion, Internacional, Declaracion) VALUES ('6','Material no Peligroso','Flamable ','no','bengala, polvora explosiva presio: 8$');

INSERT INTO ExEnvios (Numero_Orden, Excepcion, Clasificacion, Internacional, Declaracion) VALUES ('7','Material Peligroso','Metal ','no','Cuchillo, Acero inoxidable precio: 15$');
INSERT INTO ExEnvios (Numero_Orden, Excepcion, Clasificacion, Internacional, Declaracion) VALUES ('9','Material no Peligroso','Explosivo ','si','granada de combate precio: 350$');

	 
	 
	 
	 SELECT paquete.Usuario, ExEnvios.Excepcion,ExEnvios.Clasificacion, ExEnvios.Internacional, ExEnvios.Declaracion
FROM ExEnvios 
INNER JOIN paquete
ON ExEnvios.Numero_Orden = paquete.Numero_Orden
Where paquete.Usuario = 'OKSecurity';


	 */
	
	
	public static void NewScreen(String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				userFP = user;
				try {
					EmpBusq window = new EmpBusq();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmpBusq() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 882, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 102));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 124, 754, 165);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(new Color(255, 255, 255));
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 64, 0), new Color(0, 64, 0), new Color(0, 64, 0), new Color(0, 64, 0)));
		
		 
		 
		
		JButton btnNewButton = new JButton("Busqueda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Class.forName("org.mariadb.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","teoria");
				Statement st=con.createStatement();
				String query="SELECT paquete.Numero_Orden,paquete.Usuario, ExEnvios.Excepcion,ExEnvios.Clasificacion, ExEnvios.Internacional, ExEnvios.Declaracion "
						+ "FROM ExEnvios "
						+ "INNER JOIN paquete "
						+ "ON ExEnvios.Numero_Orden = paquete.Numero_Orden "
						+ "Where paquete.Usuario = '"+userFP+"';";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int cols=rsmd.getColumnCount();
				System.out.print(cols);
				String[] colName= new String [cols];
				for(int i=0; i<cols;i++) {
					colName[i]=rsmd.getColumnName(i+1);
					
				}
				model.setColumnIdentifiers(colName);
				
				String id,User,Excepciones,Clasificacion, Internacional, Declaracion;
				while(rs.next()) {
					id=rs.getString(1);
					User=rs.getString(2);
					Excepciones=rs.getString(3);
					Clasificacion=rs.getString(4);
					Internacional=rs.getString(5);
					Declaracion=rs.getString(6);
					String[] row= {id,User,Excepciones,Clasificacion, Internacional, Declaracion};
					model.addRow(row);
					
					
					
				}
				st.close();
				con.close();
				
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(77, 93, 83));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(195, 314, 107, 37);
		panel.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_1.setBackground(new Color(77, 93, 83));
		btnNewButton_1.setBounds(456, 314, 107, 37);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Bienvenido de regreso ");
		lblNewLabel.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(225, 35, 239, 57);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(userFP);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(474, 35, 239, 57);
		panel.add(lblNewLabel_1);
	}
}
