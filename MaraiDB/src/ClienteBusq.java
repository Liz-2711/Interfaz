import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class ClienteBusq {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	static String userFP; 

	/**
	 * Launch the application.
	 * @param user 
	 */
	
	
	/*
	 * create table paquete(
    Numero_Orden int primary key auto_increment,
    peso int,
    puntualidad varchar(20),
    Estado varchar(20),
    ubicacion varchar(50),
    Bloque varchar(50), 
    Calle varchar(50) not null, 
    Casa varchar(50),
    usuario varchar(52) not null);


 INSERT INTO paquete (peso, puntualidad, Estado, ubicacion, Calle, usuario) VALUES ('40','Entrega de dia','En camino','Calle 3 Avenida WoodBrige ','Miyagi Prefecture','orangeHead');
 INSERT INTO paquete (peso, puntualidad, Estado, ubicacion, Calle, usuario) VALUES ('152','Entrega de noche','En camino','Calle 7 Avenida wearStealth ','tokoyo  parkReki','Snow');
 INSERT INTO paquete (peso, puntualidad, Estado, ubicacion, Calle, usuario) VALUES ('78','Entrega de dia','En camino','Calle 3 Avenida cherryBlossom','tokoyo  parkReki','Snow');
 INSERT INTO paquete (peso, puntualidad, Estado, ubicacion, Calle, usuario) VALUES ('240','Entrega de noche','En camino','Calle 3 Avenida Dominospizza ','Miyagi Prefecture','orangeHead');

	 
	 * */
	public static void NewScreen(String user) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						userFP = user;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ClienteBusq window = new ClienteBusq();
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
	public ClienteBusq() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 922, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 102));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(231, 95, 172, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Busqueda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					Class.forName("org.mariadb.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","teoria");
				Statement st=con.createStatement();
				String query="SELECT * FROM paquete";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String [cols];
				for(int i=0; i<cols;i++)
					colName[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colName);
				String NumOrden,peso, puntualidad, Estado, ubicacion, Bloque, Calle,Casa, usuario;
				
				 String PAQUQTENUM=textField.getText();  
			//	usuario = " 148454";
				while(rs.next()) {
					int i=0;
					NumOrden=rs.getString(1);
					peso=rs.getString(2);
					puntualidad=rs.getString(3);
					Estado=rs.getString(4);
					ubicacion=rs.getString(5);
					Bloque= rs.getString(6);
					Calle= rs.getString(7);
				    Casa= rs.getString(8);
				    usuario=rs.getString(9);
				   // System.out.print("igual");
				    //System.out.print(usuario);
						//String[] row= {NumOrden,idCliente, peso, puntualidad, Estado, ubicacion, Descripcion};
						//model.addRow(row);
						
						
					System.out.print(usuario);
					
					
					if (NumOrden.equals(PAQUQTENUM) && usuario.equals(userFP)) {
					System.out.print("igual");
					
					String[] row= {NumOrden,peso, puntualidad, Estado, ubicacion, Bloque, Calle,Casa, usuario};
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
		btnNewButton.setBackground(new Color(77, 93, 83));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(627, 94, 85, 21);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 124, 753, 83);
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
		btnNewButton_1.setBounds(425, 227, 85, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Bienvenido de regreso ");
		lblNewLabel.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(190, 10, 239, 57);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(userFP);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(502, 10, 239, 57);
		panel.add(lblNewLabel_1);
	}
}
