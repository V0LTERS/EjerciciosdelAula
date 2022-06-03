package clases;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.awt.color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class VentanaC extends javax.swing.JFrame {
    
    public String mensaje;


    public VentanaC() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String ip_puerto_nombre[]=getIP_Puerto_Nombre();
        String ip=ip_puerto_nombre[0];
        String puerto=ip_puerto_nombre[1];
        String nombre=ip_puerto_nombre[2];
        cliente=new Cliente(this, ip, Integer.valueOf(puerto), nombre);
        
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        this.setLocation(550, 50);
        txtHistorial.setEditable(false);
        txtHistorial.setColumns(8);
        txtHistorial.setFont(new java.awt.Font("Century gothic", 2, 14)); 
        txtHistorial.setBackground(Color.DARK_GRAY);
        txtHistorial.setForeground(Color.WHITE);
        txtHistorial.setLineWrap(true);
        txtHistorial.setRows(5);
        txtHistorial.setDoubleBuffered(true);
        txtHistorial.setDragEnabled(true);
        jScrollPane1.setViewportView(txtHistorial);

        txtMensaje.setFont(new java.awt.Font("Century gothic", 2, 13)); 
        txtMensaje.setHorizontalAlignment(JTextField.CENTER);
        txtMensaje.setForeground(new java.awt.Color(169, 169, 169));
        txtMensaje.setBackground(Color.DARK_GRAY);
        txtMensaje.setForeground(Color.WHITE);

        btnEnviar.setBackground(Color.black);
        btnEnviar.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        btnEnviar.setText(" >>> ");
        btnEnviar.setForeground(Color.LIGHT_GRAY);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel1.setText(" ");
        jLabel1.setForeground(Color.DARK_GRAY);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMensaje))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        ))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMensaje)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(5, Short.MAX_VALUE))
        );

        pack();
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        cliente.confirmarDesconexion();
    }

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        if(txtMensaje.getText().equals("1")||txtMensaje.getText().equals("0")){
            txtHistorial.append("Yo: \n ¿Cuál es el factorial de?" + txtMensaje.getText() + "?\n");
            txtHistorial.append("El factorial es 1\n");
            txtMensaje.setText("");
        }else{
            mensaje = txtMensaje.getText();
            cliente.enviarFactorial(mensaje);
            txtHistorial.append("Yo: \n ¿Cuál es el factorial de?" + mensaje + "?\n");
            txtMensaje.setText("");
        }
        //mensaje=txtMensaje.getText();
        //cliente.enviarMensaje(mensaje);
        //txtHistorial.append("Usuario: \n" + mensaje+"\n");
        //txtMensaje.setText("");
    }
    private void txtMensajeKeyTyped(java.awt.event.KeyEvent evt){
        char validar = evt.getKeyChar();
        if(txtMensaje.getText().length()>=5){
            evt.consume();
        }else{
                if(!Character.isDigit(validar));
            }
        }

    public static void main(String args[]) {

        java.awt.Color color =  new Color(0, 0, 0);
        UIManager.put("nimbusBase", color);
        UIManager.put("control", color.DARK_GRAY);
        UIManager.put("text", color.WHITE);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaC().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextField txtMensaje;
    private final String DEFAULT_PORT="1024";  
    private final String DEFAULT_IP="10.0.0.4";
    private final Cliente cliente;
 
    void addContacto(String contacto) {

        
    }

    void addMensaje(String emisor, String mensaje) {

        txtHistorial.append(emisor + " \n" + mensaje+"\n");
    }

    void sesionIniciada(String identificador) {
        this.setTitle(" | En Línea |" +identificador);
        this.setForeground(Color.BLUE);
    }

    private String[] getIP_Puerto_Nombre() {
        String s[]=new String[3];
        s[0]=DEFAULT_IP;
        s[1]=DEFAULT_PORT;
        JTextField ip = new JTextField(8);
        JTextField puerto = new JTextField(8);
        JTextField usuario = new JTextField(8);
        ip.setText(DEFAULT_IP);
        ip.setBackground(Color.LIGHT_GRAY);
        ip.setForeground(Color.DARK_GRAY);
        puerto.setText(DEFAULT_PORT);
        puerto.setBackground(Color.LIGHT_GRAY);
        puerto.setForeground(Color.DARK_GRAY);
        usuario.setText("Diego");
        usuario.setBackground(Color.LIGHT_GRAY);
        usuario.setForeground(Color.DARK_GRAY);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(3, 2));
        myPanel.add(new JLabel("IP:"));
        myPanel.add(ip);
        myPanel.add(new JLabel("Puerto:"));
        myPanel.add(puerto);
        myPanel.add(new JLabel("Nombre:"));
        myPanel.add(usuario); 

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Login de registro", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
                s[0]=ip.getText();
                s[1]=puerto.getText();
                s[2]=usuario.getText();
        }else{
            System.exit(0);
        }
        return s;
    }    
    
    void eliminarContacto(String identificador) {
        
        System.out.println("Se elimino el usuari0");
        
        return;
    }
}