import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

public class ClienteWnd {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
static int getTipo;
	/**
	 * Launch the application.
	 */
	public static void NewScreen(int tipo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// gettipo define que usuario es ej: cliente, empleado,ect
					getTipo = tipo;
			
				try {
					ClienteWnd window = new ClienteWnd();
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
	public ClienteWnd() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 102));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(128, 83, 96, 19);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					// se connecta a la base de datos root, se debe cambiar el nombre de la DB y la password 
					
					Class.forName("org.mariadb.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","teoria");
				Statement st=con.createStatement();
				String query="SELECT * FROM cliente";
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				
				int cols=rsmd.getColumnCount();
				String[] colName=new String [cols];
				for(int i=0; i<cols;i++)
					colName[i]=rsmd.getColumnName(i+1);
				
				
				// nombre de los atributos de la tabla para el cliente 
				
				
				 String Usertxt=txtUsuario.getText();  

				 String passtxt= new String(passwordField.getPassword());  
			      
				 String user, contraseña, NumCel , Edad, Direccion, Fname, Lname;
					
				
			//	usuario = " 148454";
				while(rs.next()) {
					int i=0;
					user=rs.getString(1);
					contraseña=rs.getString(2);
					NumCel=rs.getString(3);
					Edad=rs.getString(4);
					Direccion=rs.getString(5);
					Fname=rs.getString(6);
				    Lname= rs.getString(7);
				   // System.out.print("igual");
				    //System.out.print(usuario);
						//String[] row= {NumOrden,idCliente, peso, puntualidad, Estado, ubicacion, Descripcion};
						//model.addRow(row);
						
						
					//System.out.print(user +"\n");
					
					
					if (user.equals(Usertxt) && contraseña.equals(passtxt)) {
					
					
						System.out.print("U DiD IT !!!!!! \n");
					
					
					//Si se requiere aqui se haria la busqueda de usuario en los deferentes bases de datos 			
					
		//-----	>		if (user.equals(Usertxt) && pass.equals(passtxt)) {
					
						if(getTipo == 1) {
							ClienteBusq nw2 = new ClienteBusq();
							
							frame.setVisible(false);
							nw2.NewScreen(user);
							
							}
							else if(getTipo == 2) {
								EmpBusq nw2 = new EmpBusq();
								//String user = "148454";
								frame.setVisible(false);
								nw2.NewScreen(user);
								}
							if(getTipo == 3) {
								TranBusq nw2 = new TranBusq();
								//String user = "148454";
								frame.setVisible(false);
								nw2.NewScreen(user);
								}
								//ClienteBusq nw2 = new ClienteBusq();
							
					};
							
					
				}
				
				
				st.close();
				con.close();
				
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



				
				//String user = "148454";
				
				
				
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(77, 102, 83));
		btnNewButton.setBounds(259, 105, 109, 33);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(22, 82, 88, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(22, 141, 73, 13);
		panel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 139, 96, 19);
		panel.add(passwordField);
	}
}
