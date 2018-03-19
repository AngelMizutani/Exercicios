/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package placavelocidade;

import connectPlacaVel.MySQLConnectPlaca;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Angelica
 */
public class Placa extends javax.swing.JFrame {

    /**
     * Creates new form Placa
     */
    
public String gerarNumero(){
    String num = "";
    
    for (int i=0; i<4; i++){
        num += Integer.toString(new Random().nextInt(10));
    }
    return num;
}

public String gerarLetra(){
    String letras = "";
    String alfab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    for (int i =0; i<3; i++){
        letras += alfab.charAt(new Random().nextInt(alfab.length()));
    }
    return letras;
}

public String gerarPlaca() {
    String num = gerarNumero();
    String letra = gerarLetra();
    
    return (letra + "-" + num);
}

public int gerarVelocidade(){
    int vel = new Random().nextInt(200);
    
    return vel;
}
    
    public Placa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNumCapturas = new javax.swing.JTextField();
        btnCapturar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtVelocidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 370));
        setMinimumSize(new java.awt.Dimension(400, 370));
        setPreferredSize(new java.awt.Dimension(400, 370));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quantidade de placas capturadas");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 20, 360, 40);

        txtNumCapturas.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        txtNumCapturas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumCapturas.setToolTipText("");
        getContentPane().add(txtNumCapturas);
        txtNumCapturas.setBounds(130, 70, 100, 40);

        btnCapturar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCapturar.setText("Capturar!");
        btnCapturar.setBorder(null);
        btnCapturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapturar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCapturar);
        btnCapturar.setBounds(140, 270, 90, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Velocidade");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(230, 150, 120, 50);

        txtVelocidade.setEditable(false);
        txtVelocidade.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtVelocidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtVelocidade);
        txtVelocidade.setBounds(210, 200, 70, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("km/h");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(290, 200, 90, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Placa");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 160, 60, 29);

        txtPlaca.setEditable(false);
        txtPlaca.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtPlaca);
        txtPlaca.setBounds(10, 200, 130, 40);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarActionPerformed
        // TODO add your handling code here:
        try {
            MySQLConnectPlaca con = new MySQLConnectPlaca("localhost", "placasVelocidade", "root", "");
            con.getMySQLConnect();
            
            int numCapturas = Integer.parseInt(txtNumCapturas.getText());
            String placa;
            int velocidade;
            
            for (int i = 0; i < numCapturas; i++){
                
                placa = gerarPlaca();
                txtPlaca.setText(placa);
                
                velocidade = gerarVelocidade();
                txtVelocidade.setText(Integer.toString(velocidade));
                
                if (velocidade >110){
                    JOptionPane.showMessageDialog(null, "Velocidade Acima do Limite! Gerar Multa!", 
                            "Velocidade Acima do Limite", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Velocidade dentro do Limite");
                }
                
                String comando = "Insert into placas values (NOW(), '" + placa + "', '" + velocidade + "')";
                con.updateMySQL(comando);
            }
            
        String queryMysql = "select * from placas";
        String[][] resultQuery;
        resultQuery = con.queryMySQL(queryMysql);
        
        for(int i = 0; i<resultQuery.length; i++){
            for (int j=0; j<resultQuery[i].length; j++){
                System.out.print(resultQuery[i][j] + " | ");
            }
            System.out.println("");
        }
            
        con.closeConnection();   
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar: "+e);
        }
        
    }//GEN-LAST:event_btnCapturarActionPerformed

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
            java.util.logging.Logger.getLogger(Placa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Placa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Placa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Placa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Placa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtNumCapturas;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtVelocidade;
    // End of variables declaration//GEN-END:variables
}
