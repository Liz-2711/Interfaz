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

public class TranBusq {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	static String usuario; 
	/**
	 * Launch the application.
	 */
	public static void NewScreen(String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				usuario = user;
				try {
					TranBusq window = new TranBusq();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 
	  
Create table MoviTransp (
    NumTracking int primary key,
    NumOrden varchar(50) not null,
    Tiempo time,
    Fecha date not null,
    Tipo_Vehiculo varchar(50) not null,
    Bloque varchar(50), 
    Calle varchar(50) not null, 
    Casa varchar(50),
    usuarioC varchar(52) not null,
FOREIGN KEY (usuarioC) REFERENCES Cliente(usuario)
);




INSERT INTO MoviTransp(NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Calle, usuarioC) VALUES ('145','1','02:55:05','2022-09-10','Avion','Calle 3 Avenida WoodBrige','orangeHead');
 INSERT INTO MoviTransp(NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Calle, usuarioC) VALUES ('159','2','05:30:55','2022-08-23','Camion','Calle 7 Avenida wearStealth','Snow');
INSERT INTO MoviTransp(NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Calle, usuarioC) VALUES ('160','3','05:36:55','2022-08-23','Camion','Calle 3 Avenida cherryBlossom','Snow');
INSERT INTO MoviTransp(NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Calle, usuarioC) VALUES ('165','4','10:46:45','2022-05-30','Embarcacion','Calle 3 Avenida Dominospizza','orangeHead');

INSERT INTO MoviTransp(NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Calle, usuarioC) VALUES ('178','7','07:58:21','2022-07-15','Embarcacion','Calle 9 Avenida WoodBrige','orangeHead');

UPDATE paquete
SET Estado ='Entragado (Firmado)'
WHERE Numero_Orden='7';


SELECT MoviTransp.*, paquete.Estado
     FROM MoviTransp
LEFT JOIN paquete
ON MoviTransp.NumOrden = paquete.Numero_Orden;

SELECT paquete.Numero_Orden, paquete.Usuario, ExEnvios.Excepcion,ExEnvios.Clasificacion, ExEnvios.Internacional, ExEnvios.Declaracion
FROM ExEnvios 
INNER JOIN paquete
ON ExEnvios.Numero_Orden = paquete.Numero_Orden
Where paquete.Usuario = 'OKSecurity';
	  
	  
	 
	 */
	public TranBusq() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1008, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 102));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(298, 63, 172, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ver Todo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","teoria");
				Statement st=con.createStatement();
				String query="SELECT MoviTransp.*, paquete.Estado\r\n"
						+ "     FROM MoviTransp\r\n"
						+ "LEFT JOIN paquete\r\n"
						+ "ON MoviTransp.NumOrden = paquete.Numero_Orden;";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String [cols];
				for(int i=0; i<cols;i++)
					colName[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colName);
				String NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Bloque,Calle, Casa, usuarioC, Estado;
				while(rs.next()) {
					NumTracking=rs.getString(1);
					NumOrden=rs.getString(2);
					Tiempo=rs.getString(3);
					Fecha=rs.getString(4);
					Tipo_Vehiculo=rs.getString(5);
					Bloque = rs.getString(6);
					Calle=rs.getString(7);
					Casa=rs.getString(8);
					usuarioC=rs.getString(9);
					Estado=rs.getString(10);
					
					String[] row= { NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Bloque,Calle, Casa, usuarioC, Estado};
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
		btnNewButton.setBounds(317, 353, 85, 21);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 124, 859, 161);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_1.setBackground(new Color(77, 93, 83));
		btnNewButton_1.setBounds(554, 353, 85, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Busqueda");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				Class.forName("org.mariadb.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","teoria");
				Statement st=con.createStatement();
				String query="SELECT MoviTransp.*, paquete.Estado\r\n"
						+ "     FROM MoviTransp\r\n"
						+ "LEFT JOIN paquete\r\n"
						+ "ON MoviTransp.NumOrden = paquete.Numero_Orden;";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String txt = textField.getText();
				int cols=rsmd.getColumnCount();
				String[] colName=new String [cols];
				for(int i=0; i<cols;i++)
					colName[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colName);
				String NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Bloque,Calle, Casa, usuarioC, Estado;
				while(rs.next()) {
					NumTracking=rs.getString(1);
					NumOrden=rs.getString(2);
					Tiempo=rs.getString(3);
					Fecha=rs.getString(4);
					Tipo_Vehiculo=rs.getString(5);
					Bloque = rs.getString(6);
					Calle=rs.getString(7);
					Casa=rs.getString(8);
					usuarioC=rs.getString(9);
					Estado=rs.getString(10);
					
				if (NumOrden.equals(txt) ) {
				//	System.out.print("igual");
					
					String[] row= {NumTracking, NumOrden, Tiempo, Fecha, Tipo_Vehiculo,Bloque,Calle, Casa, usuarioC, Estado};
					model.addRow(row);
					};
					
				}
				st.close();
				con.close();
				
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(77, 93, 83));
		btnNewButton_2.setBounds(530, 62, 85, 21);
		panel.add(btnNewButton_2);
	}
}
