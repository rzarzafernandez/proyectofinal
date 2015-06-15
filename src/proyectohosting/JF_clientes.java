/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectohosting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zarza
 */
public final class JF_clientes extends javax.swing.JFrame {

    /**
     * Creates new form JF_clientes
     */
    public JF_clientes() {
        initComponents();
        String correo = JF_login.correoCliente;
        nombreCliente(correo);
        actualizarTablaDominios("");
        actualizarTablaHostings("");
    }
    
    public void nombreCliente(String correo){  //recoge el correo usado en el login y muestra el nombre completo del propietario en la interfaz
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        
        String sql = "SELECT nombre, apellidos FROM clientes WHERE correo LIKE '%"+correo+"%' ";
        
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String nombreCliente = rs.getString("nombre") + " " + rs.getString("apellidos");
                this.JLcliente.setText(nombreCliente);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        
    }

   public final void actualizarTablaDominios(String valor){
        DefaultTableModel modelo = (DefaultTableModel) JTdominios.getModel();
        String sql;
        String[] registro = new String[4];
        String nombreCliente = JLcliente.getText();
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT propietario, dominio, fechaAlta, fechaBaja FROM dominios "
                + "WHERE propietario LIKE '%"+nombreCliente+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            //asigna un precio según la extensión del dominio
            while(rs.next()){
            String dominio = rs.getString("dominio");
            String extension = dominio.substring(dominio.length()-3);
            String  extensionPrecio="";
            switch (extension){
                case ".es": extensionPrecio="7";
                    break;
                case "com": extensionPrecio="9";
                    break;
                case "org": extensionPrecio="11";
                    break;
                case "net": extensionPrecio="13";
                    break;                    
            }
                
                registro[0] = rs.getString("dominio");
                registro[1] = extensionPrecio;
                registro[2] = rs.getString("fechaAlta");
                registro[3] = rs.getString("fechaBaja");
                
                
                modelo.addRow(registro);
            }
            JTdominios.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

public final void actualizarTablaHostings(String valor){
        DefaultTableModel modelo = (DefaultTableModel) JThostings.getModel();
        String sql;
        String[] registro = new String[5];
        String nombreCliente = JLcliente.getText();
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT propietario, dominioPrincipal, talla, fechaAlta, fechaBaja FROM hostings "
                + "WHERE propietario LIKE '%"+nombreCliente+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            //asigna un precio según la talla del hosting
            while(rs.next()){
            String talla = rs.getString("talla");
            String tallaPrecio="0";
            
            switch (talla){
                case "S": tallaPrecio="25";
                    break;
                case "M": tallaPrecio="60";
                    break;
                case "L": tallaPrecio="110";
                    break;                                 
            }
                
                registro[0] = rs.getString("dominioPrincipal");
                registro[1] = rs.getString("talla");
                registro[2] = tallaPrecio;                
                registro[3] = rs.getString("fechaAlta");
                registro[4] = rs.getString("fechaBaja");
                
                
                modelo.addRow(registro);
            }
            JThostings.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JThostings = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTdominios = new javax.swing.JTable();
        JBdesconectar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JLcliente = new javax.swing.JLabel();
        JLbienvenido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Zona Cliente");

        JThostings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dominio principal", "Talla", "Precio", "Fecha Alta", "Fecha Caducidad"
            }
        ));
        jScrollPane1.setViewportView(JThostings);

        JTdominios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dominio", "Precio", "Fecha Alta", "Fecha Caducidad"
            }
        ));
        jScrollPane2.setViewportView(JTdominios);

        JBdesconectar.setText("Desconectar");
        JBdesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBdesconectarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tus dominios:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tus hostings:");

        JLbienvenido.setText("Bienvenido/a:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JLbienvenido)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JBdesconectar)
                                    .addComponent(JLcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JLbienvenido)
                    .addComponent(JLcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBdesconectar)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBdesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBdesconectarActionPerformed
        this.dispose();
        new JF_login().setVisible(true);
    }//GEN-LAST:event_JBdesconectarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JF_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBdesconectar;
    private javax.swing.JLabel JLbienvenido;
    public javax.swing.JLabel JLcliente;
    private javax.swing.JTable JTdominios;
    private javax.swing.JTable JThostings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
