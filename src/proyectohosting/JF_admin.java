/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectohosting;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zarza
 */
public class JF_admin extends javax.swing.JFrame {

    /**
     * Creates new form JF_admin
     */
    public JF_admin() {
        initComponents();
        actualizarTablaClientes("");
        actualizarTablaDominios("");
        actualizarTablaHostings("");
        cargaPropietario();
        
        
    }
public final void actualizarTablaClientes(String valor){
        DefaultTableModel modelo = (DefaultTableModel) JTclientes.getModel();
        String sql;
        String[] registro = new String[5];
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT nombre, apellidos, correo, DNI, fechaAlta FROM clientes "
                + "WHERE CONCAT (nombre, ' ', apellidos, ' ', correo, ' ', DNI, ' ', fechaAlta) LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registro[0] = rs.getString("nombre");
                registro[1] = rs.getString("apellidos");
                registro[2] = rs.getString("correo");
                registro[3] = rs.getString("DNI");
                registro[4] = rs.getString("fechaAlta");
                
                modelo.addRow(registro);
            }
            JTclientes.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
public final void actualizarTablaDominios(String valor){
        DefaultTableModel modelo = (DefaultTableModel) JTdominios.getModel();
        String sql;
        String[] registro = new String[5];
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT propietario, dominio, fechaAlta, fechaBaja FROM dominios "
                + "WHERE CONCAT (propietario, ' ', dominio, ' ', fechaAlta, ' ', fechaBaja) LIKE '%"+valor+"%'";
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
                registro[4] = rs.getString("propietario");
                
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
        String[] registro = new String[6];
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT propietario, dominioPrincipal, talla, fechaAlta, fechaBaja FROM hostings "
                + "WHERE CONCAT (propietario, ' ', dominioPrincipal, ' ', talla, ' ', fechaAlta, ' ', fechaBaja) LIKE '%"+valor+"%'";
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
                registro[5] = rs.getString("propietario");
                
                modelo.addRow(registro);
            }
            JThostings.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

private void cargaPropietario() {
        try{
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        Statement sent = cn.createStatement();
        ResultSet rs = sent.executeQuery("SELECT nombre, apellidos FROM clientes");
        this.JCpropietarioDominio.removeAllItems();
        this.JCpropietarioHosting.removeAllItems();
        
        while( rs.next()){
            String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellidos");
            this.JCpropietarioDominio.addItem(nombreCompleto);
            this.JCpropietarioHosting.addItem(nombreCompleto);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
private void cargaDominios() {
        try{
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        Statement sent = cn.createStatement();
        ResultSet rs = sent.executeQuery("SELECT dominio, propietario FROM dominios");
        this.JCdominioHosting.removeAllItems();
        
        
        while( rs.next()){
            String propietario = JCpropietarioHosting.getSelectedItem().toString();
            if(propietario.equals(rs.getString("propietario"))){
            String nombreCompleto = rs.getString("dominio");
            this.JCdominioHosting.addItem(nombreCompleto);
            }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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

        JLpaneldeadministracion = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        JPclientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTclientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        JTapellidosCliente = new javax.swing.JTextField();
        JTcorreoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JTnombreCliente = new javax.swing.JTextField();
        JTdniCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JTpasswordCliente = new javax.swing.JTextField();
        JBgenerarPassword = new javax.swing.JButton();
        JBbaja_cliente = new javax.swing.JButton();
        JBalta_cliente = new javax.swing.JButton();
        JCHpermisosCliente = new javax.swing.JCheckBox();
        JBexportarClientes = new javax.swing.JButton();
        JPdominios = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTdominios = new javax.swing.JTable();
        paneldominio = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JTdominioDominio = new javax.swing.JTextField();
        JCpropietarioDominio = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        JBaltaDominio = new javax.swing.JButton();
        JBbajaDominio = new javax.swing.JButton();
        JBexportarDominios = new javax.swing.JButton();
        JPhostings = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JThostings = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JCdominioHosting = new javax.swing.JComboBox();
        JCpropietarioHosting = new javax.swing.JComboBox();
        JCtallaHosting = new javax.swing.JComboBox();
        JBcargarDominios = new javax.swing.JButton();
        JBaltaHosting = new javax.swing.JButton();
        JBbajaHosting = new javax.swing.JButton();
        JBexportarHostings = new javax.swing.JButton();
        JBdesconectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLpaneldeadministracion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JLpaneldeadministracion.setText("Panel de administración");

        JTclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellidos", "Correo", "DNI", "Fecha de alta"
            }
        ));
        jScrollPane1.setViewportView(JTclientes);

        jLabel3.setText("Correo:");

        jLabel2.setText("Apellidos:");

        JTnombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTnombreClienteActionPerformed(evt);
            }
        });

        jLabel4.setText("DNI:");

        jLabel1.setText("Nombre:");

        jLabel6.setText("Contraseña:");

        JBgenerarPassword.setText("Generar contraseña aleatoria");
        JBgenerarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBgenerarPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JTpasswordCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(JBgenerarPassword))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTdniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTnombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JTcorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JTapellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTnombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(JTapellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTcorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(JTdniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTpasswordCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBgenerarPassword)
                    .addComponent(jLabel6))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        JBbaja_cliente.setBackground(new java.awt.Color(255, 153, 153));
        JBbaja_cliente.setText("Dar de baja");
        JBbaja_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBbaja_clienteActionPerformed(evt);
            }
        });

        JBalta_cliente.setBackground(new java.awt.Color(153, 255, 153));
        JBalta_cliente.setText("Dar de alta");
        JBalta_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBalta_clienteActionPerformed(evt);
            }
        });

        JCHpermisosCliente.setText("Permisos administrador");

        JBexportarClientes.setText("Exportar datos");
        JBexportarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBexportarClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPclientesLayout = new javax.swing.GroupLayout(JPclientes);
        JPclientes.setLayout(JPclientesLayout);
        JPclientesLayout.setHorizontalGroup(
            JPclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPclientesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(JPclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPclientesLayout.createSequentialGroup()
                        .addGroup(JPclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBalta_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBbaja_cliente))
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPclientesLayout.createSequentialGroup()
                        .addComponent(JCHpermisosCliente)
                        .addGap(43, 43, 43))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPclientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPclientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBexportarClientes)
                .addGap(50, 50, 50))
        );
        JPclientesLayout.setVerticalGroup(
            JPclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPclientesLayout.createSequentialGroup()
                .addGroup(JPclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPclientesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(JCHpermisosCliente)
                        .addGap(14, 14, 14)
                        .addComponent(JBalta_cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBbaja_cliente)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JPclientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBexportarClientes)
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab("Clientes", JPclientes);

        JTdominios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dominio", "Precio", "Fecha de alta", "Fecha de baja", "Propietario"
            }
        ));
        jScrollPane2.setViewportView(JTdominios);
        if (JTdominios.getColumnModel().getColumnCount() > 0) {
            JTdominios.getColumnModel().getColumn(1).setMaxWidth(50);
            JTdominios.getColumnModel().getColumn(4).setMinWidth(200);
        }

        jLabel5.setText("Dominio:");

        JTdominioDominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTdominioDominioActionPerformed(evt);
            }
        });

        JCpropietarioDominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCpropietarioDominioActionPerformed(evt);
            }
        });

        jLabel8.setText("Propietario:");

        javax.swing.GroupLayout paneldominioLayout = new javax.swing.GroupLayout(paneldominio);
        paneldominio.setLayout(paneldominioLayout);
        paneldominioLayout.setHorizontalGroup(
            paneldominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldominioLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(paneldominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(paneldominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTdominioDominio)
                    .addComponent(JCpropietarioDominio, 0, 183, Short.MAX_VALUE))
                .addGap(0, 162, Short.MAX_VALUE))
        );
        paneldominioLayout.setVerticalGroup(
            paneldominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldominioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneldominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTdominioDominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(14, 14, 14)
                .addGroup(paneldominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(JCpropietarioDominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        JBaltaDominio.setBackground(new java.awt.Color(153, 255, 153));
        JBaltaDominio.setText("Dar de alta");
        JBaltaDominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBaltaDominioActionPerformed(evt);
            }
        });

        JBbajaDominio.setBackground(new java.awt.Color(255, 153, 153));
        JBbajaDominio.setText("Dar de baja");
        JBbajaDominio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBbajaDominioActionPerformed(evt);
            }
        });

        JBexportarDominios.setText("Exportar datos");
        JBexportarDominios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBexportarDominiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPdominiosLayout = new javax.swing.GroupLayout(JPdominios);
        JPdominios.setLayout(JPdominiosLayout);
        JPdominiosLayout.setHorizontalGroup(
            JPdominiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPdominiosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPdominiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(JPdominiosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(paneldominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(JPdominiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JBaltaDominio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBbajaDominio))
                        .addGap(0, 145, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPdominiosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBexportarDominios)
                .addGap(38, 38, 38))
        );
        JPdominiosLayout.setVerticalGroup(
            JPdominiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPdominiosLayout.createSequentialGroup()
                .addGroup(JPdominiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPdominiosLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(JBaltaDominio)
                        .addGap(18, 18, 18)
                        .addComponent(JBbajaDominio))
                    .addGroup(JPdominiosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(paneldominio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBexportarDominios)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Dominios", JPdominios);

        JThostings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dominio principal", "Talla", "Precio", "Fecha de alta", "Fecha de baja", "Propietario"
            }
        ));
        jScrollPane3.setViewportView(JThostings);
        if (JThostings.getColumnModel().getColumnCount() > 0) {
            JThostings.getColumnModel().getColumn(1).setMaxWidth(50);
            JThostings.getColumnModel().getColumn(2).setMaxWidth(50);
            JThostings.getColumnModel().getColumn(5).setMinWidth(200);
        }

        jLabel9.setText("Talla: ");

        jLabel10.setText("Propietario:");

        jLabel7.setText("Dominio principal:");

        JCpropietarioHosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCpropietarioHostingActionPerformed(evt);
            }
        });

        JCtallaHosting.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "S", "M", "L" }));

        JBcargarDominios.setText("Cargar dominos");
        JBcargarDominios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBcargarDominiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(JCtallaHosting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JCpropietarioHosting, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JCdominioHosting, 0, 189, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBcargarDominios)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCtallaHosting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(JCpropietarioHosting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(JCdominioHosting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBcargarDominios))
                .addContainerGap())
        );

        JBaltaHosting.setBackground(new java.awt.Color(153, 255, 153));
        JBaltaHosting.setText("Dar de alta");
        JBaltaHosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBaltaHostingActionPerformed(evt);
            }
        });

        JBbajaHosting.setBackground(new java.awt.Color(255, 153, 153));
        JBbajaHosting.setText("Dar de baja");
        JBbajaHosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBbajaHostingActionPerformed(evt);
            }
        });

        JBexportarHostings.setText("Exportar datos");
        JBexportarHostings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBexportarHostingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPhostingsLayout = new javax.swing.GroupLayout(JPhostings);
        JPhostings.setLayout(JPhostingsLayout);
        JPhostingsLayout.setHorizontalGroup(
            JPhostingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPhostingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(JPhostingsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(JPhostingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBaltaHosting, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBbajaHosting))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPhostingsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBexportarHostings)
                .addGap(40, 40, 40))
        );
        JPhostingsLayout.setVerticalGroup(
            JPhostingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPhostingsLayout.createSequentialGroup()
                .addGroup(JPhostingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPhostingsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(JBaltaHosting)
                        .addGap(18, 18, 18)
                        .addComponent(JBbajaHosting)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBexportarHostings)
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("Hostings", JPhostings);

        JBdesconectar.setText("Desconectar");
        JBdesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBdesconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLpaneldeadministracion, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(JBdesconectar)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLpaneldeadministracion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBdesconectar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTnombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTnombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTnombreClienteActionPerformed

    private void JBbaja_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBbaja_clienteActionPerformed
        //BAJA CLIENTES
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();

        int fila = JTclientes.getSelectedRow();
        Object dni = JTclientes.getValueAt(fila,3).toString();
        String sql = "DELETE FROM clientes WHERE DNI='"+dni+"'";

        try{
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.executeUpdate();

            //actualiza la tabla en el momento de añadir un nuevo participante
            ((DefaultTableModel)JTclientes.getModel()).setRowCount(0);
            actualizarTablaClientes("");
            cargaPropietario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_JBbaja_clienteActionPerformed

    private void JBalta_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBalta_clienteActionPerformed
        //ALTA CLIENTES
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        Date fechaActual = new Date();
        String fechaActualFormat = new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);

        String nombre, apellidos, correo, DNI, fechaAlta, password;
        int admin = 0;
        nombre = JTnombreCliente.getText();
        apellidos = JTapellidosCliente.getText();
        correo = JTcorreoCliente.getText();
        DNI = JTdniCliente.getText();
        fechaAlta = fechaActualFormat;
        password = JTpasswordCliente.getText();
        if(JCHpermisosCliente.isSelected()) admin = 1;

        String sql = "INSERT INTO clientes (nombre, apellidos, correo, DNI, fechaAlta, password, admin) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, apellidos);
            pst.setString(3, correo);
            pst.setString(4, DNI);
            pst.setString(5, fechaAlta);
            pst.setString(6, password);
            pst.setInt(7, admin);

            int n = pst.executeUpdate();

            //actualiza la tabla en el momento de añadir un nuevo participante
            ((DefaultTableModel)JTclientes.getModel()).setRowCount(0);
            actualizarTablaClientes("");
            JTnombreCliente.setText("");
            JTapellidosCliente.setText("");
            JTdniCliente.setText("");
            JTcorreoCliente.setText("");
            JTpasswordCliente.setText("");
            JCHpermisosCliente.setSelected(false);
            cargaPropietario();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_JBalta_clienteActionPerformed

    private void JTdominioDominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTdominioDominioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTdominioDominioActionPerformed

    private void JCpropietarioDominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCpropietarioDominioActionPerformed
      
    }//GEN-LAST:event_JCpropietarioDominioActionPerformed

    private void JBaltaDominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBaltaDominioActionPerformed
        //ALTA DOMINIOS
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
       
        Calendar cal = Calendar.getInstance();
        Date hoy = cal.getTime();
        cal.add(Calendar.YEAR,1); //suma un año a la fecha
        Date fechaCaducidad = cal.getTime();
      
        
        String fechaActualFormat = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        String fechaCaducidadFormat = new SimpleDateFormat("yyyy-MM-dd").format(fechaCaducidad);
        

        String propietario, dominio, fechaAlta, fechaBaja;
        propietario = JCpropietarioDominio.getSelectedItem().toString();
        dominio = JTdominioDominio.getText();
        fechaAlta = fechaActualFormat;
        fechaBaja = fechaCaducidadFormat;
        

        String sql = "INSERT INTO dominios (propietario, dominio, fechaAlta, fechaBaja) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, propietario);
            pst.setString(2, dominio);
            pst.setString(3, fechaAlta);
            pst.setString(4, fechaBaja);
            

            int n = pst.executeUpdate();

            //actualiza la tabla en el momento de añadir un nuevo participante
            ((DefaultTableModel)JTdominios.getModel()).setRowCount(0);
            actualizarTablaDominios("");
           JTdominioDominio.setText("");
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }//GEN-LAST:event_JBaltaDominioActionPerformed

    private void JBbajaDominioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBbajaDominioActionPerformed
        //BAJA DOMINIOS
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();

        int fila = JTdominios.getSelectedRow();
        Object dominio = JTdominios.getValueAt(fila,0).toString();
        String sql = "DELETE FROM dominios WHERE dominio='"+dominio+"'";

        try{
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.executeUpdate();

            //actualiza la tabla en el momento de añadir un nuevo participante
            ((DefaultTableModel)JTdominios.getModel()).setRowCount(0);
            actualizarTablaDominios("");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_JBbajaDominioActionPerformed

    private void JBgenerarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBgenerarPasswordActionPerformed
        String passwordAleatoria = GenerarPassword.getPassword(
		GenerarPassword.minusculas+
		GenerarPassword.mayusculas+
		GenerarPassword.numeros,12);
        JTpasswordCliente.setText(passwordAleatoria);
    }//GEN-LAST:event_JBgenerarPasswordActionPerformed

    private void JBdesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBdesconectarActionPerformed
        this.dispose();
        new JF_login().setVisible(true);
    }//GEN-LAST:event_JBdesconectarActionPerformed

    private void JCpropietarioHostingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCpropietarioHostingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCpropietarioHostingActionPerformed

    private void JBaltaHostingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBaltaHostingActionPerformed
        //ALTA HOSTINGS
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
       
        Calendar cal = Calendar.getInstance();
        Date hoy = cal.getTime();
        cal.add(Calendar.YEAR,1); //suma un año a la fecha
        Date fechaCaducidad = cal.getTime();
      
        
        String fechaActualFormat = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        String fechaCaducidadFormat = new SimpleDateFormat("yyyy-MM-dd").format(fechaCaducidad);
        

        String propietario, dominio, talla, fechaAlta, fechaBaja;
        propietario = JCpropietarioHosting.getSelectedItem().toString();
        dominio = JCdominioHosting.getSelectedItem().toString();
        talla = JCtallaHosting.getSelectedItem().toString();
        fechaAlta = fechaActualFormat;
        fechaBaja = fechaCaducidadFormat;
        this.JCdominioHosting.removeAllItems();

        String sql = "INSERT INTO hostings (propietario, dominioPrincipal, talla, fechaAlta, fechaBaja) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, propietario);
            pst.setString(2, dominio);
            pst.setString(3, talla);
            pst.setString(4, fechaAlta);
            pst.setString(5, fechaBaja);
            

            int n = pst.executeUpdate();

            //actualiza la tabla en el momento de añadir un nuevo participante
            ((DefaultTableModel)JThostings.getModel()).setRowCount(0);
            actualizarTablaHostings("");
           
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }//GEN-LAST:event_JBaltaHostingActionPerformed

    private void JBcargarDominiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBcargarDominiosActionPerformed
        cargaDominios();
    }//GEN-LAST:event_JBcargarDominiosActionPerformed

    private void JBbajaHostingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBbajaHostingActionPerformed
       //BAJA DOMINIOS
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();

        int fila = JThostings.getSelectedRow();
        Object dominioPrincipal = JThostings.getValueAt(fila,0).toString();
        String sql = "DELETE FROM hostings WHERE dominioPrincipal='"+dominioPrincipal+"'";

        try{
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.executeUpdate();

            //actualiza la tabla en el momento de añadir un nuevo participante
            ((DefaultTableModel)JThostings.getModel()).setRowCount(0);
            actualizarTablaHostings("");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_JBbajaHostingActionPerformed

    private void JBexportarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBexportarClientesActionPerformed
         Exportar exp = new Exportar();
         exp.exportarClientes("");
    }//GEN-LAST:event_JBexportarClientesActionPerformed

    private void JBexportarDominiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBexportarDominiosActionPerformed
        Exportar exp = new Exportar();
        exp.exportarDominios("");
    }//GEN-LAST:event_JBexportarDominiosActionPerformed

    private void JBexportarHostingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBexportarHostingsActionPerformed
       Exportar exp = new Exportar();
       exp.exportarHostings("");
    }//GEN-LAST:event_JBexportarHostingsActionPerformed

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
            java.util.logging.Logger.getLogger(JF_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JF_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBaltaDominio;
    private javax.swing.JButton JBaltaHosting;
    private javax.swing.JButton JBalta_cliente;
    private javax.swing.JButton JBbajaDominio;
    private javax.swing.JButton JBbajaHosting;
    private javax.swing.JButton JBbaja_cliente;
    private javax.swing.JButton JBcargarDominios;
    private javax.swing.JButton JBdesconectar;
    private javax.swing.JButton JBexportarClientes;
    private javax.swing.JButton JBexportarDominios;
    private javax.swing.JButton JBexportarHostings;
    private javax.swing.JButton JBgenerarPassword;
    private javax.swing.JCheckBox JCHpermisosCliente;
    private javax.swing.JComboBox JCdominioHosting;
    private javax.swing.JComboBox JCpropietarioDominio;
    private javax.swing.JComboBox JCpropietarioHosting;
    private javax.swing.JComboBox JCtallaHosting;
    private javax.swing.JLabel JLpaneldeadministracion;
    private javax.swing.JPanel JPclientes;
    private javax.swing.JPanel JPdominios;
    private javax.swing.JPanel JPhostings;
    private javax.swing.JTextField JTapellidosCliente;
    private javax.swing.JTable JTclientes;
    private javax.swing.JTextField JTcorreoCliente;
    private javax.swing.JTextField JTdniCliente;
    private javax.swing.JTextField JTdominioDominio;
    private javax.swing.JTable JTdominios;
    private javax.swing.JTable JThostings;
    private javax.swing.JTextField JTnombreCliente;
    private javax.swing.JTextField JTpasswordCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel paneldominio;
    // End of variables declaration//GEN-END:variables

    
}
