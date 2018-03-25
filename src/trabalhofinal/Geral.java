/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bmarquez
 */
public class Geral extends javax.swing.JFrame {
    //Classe para controle dos clicks e atalhos
    public class BotaoAtalho extends AbstractAction {
        private EnumKeys shortcut;

        @Override
        public void actionPerformed(ActionEvent e) {
            //Compara os atalhos através dos valores do enum
            switch(this.shortcut){
                case CTRLN:
                    btnNewActionPerformed(e);
                    break;
                case CTRLO:
                    btnOpenActionPerformed(e);
                    break;
                case CTRLS:
                    btnSaveActionPerformed(e);
                    break;
                case F9:
                    btnCompileActionPerformed(e);
                    break;
                case F1:
                    btnAboutActionPerformed(e);
                    break;
                default:
                    break;
            }
        }

        public BotaoAtalho(EnumKeys keyPressed) {
            this.shortcut = keyPressed;
        }
    }
    
    //Área de declaração dos botões
    private BotaoAtalho buttonNew = new BotaoAtalho(EnumKeys.CTRLN);
    private BotaoAtalho buttonOpen = new BotaoAtalho(EnumKeys.CTRLO);
    private BotaoAtalho buttonSave = new BotaoAtalho(EnumKeys.CTRLS);
    private BotaoAtalho buttonCompile = new BotaoAtalho(EnumKeys.F9);
    private BotaoAtalho buttonAbout = new BotaoAtalho(EnumKeys.F1);
    
    private String filePath;
    
    private FileNameExtensionFilter fileNameExtensionFilter;
    
    /**
     * Creates new form Geral
     */
    public Geral() {
        //Inicia os componentes
        initComponents();

        //Seta a borda numerada no campo de edição
        jtaCommand.setBorder(new NumberedBorder());

        //Registra as ações do teclado
        this.acoesDoTeclado(jpArea);
        this.acoesDoTeclado(jpButtons);
        this.acoesDoTeclado(jpAreaMensagem);
        
        this.filePath = "";
        this.fileNameExtensionFilter = new FileNameExtensionFilter("Documento de Texto (*.txt)", "txt");
        
        mostrarNomeArquivo();
    }

    private void acoesDoTeclado(JPanel painel) {
        //Cria o ActionMap
        ActionMap actionMap = painel.getActionMap();
        
	actionMap.put("new", buttonNew);
        actionMap.put("open", buttonOpen);
        actionMap.put("save", buttonSave);
        actionMap.put("compile", buttonCompile);
        actionMap.put("aboutWe", buttonAbout);
        
	painel.setActionMap(actionMap);
	
	//Pegamos o input map que ocorre sempre que a janela atual está em foco
	InputMap imap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

	//Teclas do teclado
        imap.put(KeyStroke.getKeyStroke("Ctrl n"), "new");  // Esse ainda não funciona
        imap.put(KeyStroke.getKeyStroke("ctrl o"), "open"); // Esse ainda não funciona
        imap.put(KeyStroke.getKeyStroke("ctrl s"), "save"); // Esse ainda não funciona
        imap.put(KeyStroke.getKeyStroke("F9"), "compile");
        imap.put(KeyStroke.getKeyStroke("F1"), "aboutWe");
    }
    
    private void mostrarNomeArquivo(){
        jtfBarraStatus.setText(this.filePath);
    }
    
    private void clearMessageArea(){
        this.jtaMessageArea.setText("");
    }
        
    private void clearAll() {
        this.filePath = "";
        this.jtaCommand.setText("");
        this.jtfBarraStatus.setText("");
        
        this.clearMessageArea();
    }
    
    private void createNew(){
        this.clearAll();
    }
    
    private void open(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(fileNameExtensionFilter);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        int open = jFileChooser.showOpenDialog(this);
        switch (open) {
            case APPROVE_OPTION:
                this.clearAll();
                File arquivo = jFileChooser.getSelectedFile();
                this.filePath = arquivo.getAbsolutePath();
                this.mostrarNomeArquivo();
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    bufferedReader.lines().forEach(line -> stringBuilder.append(line).append("\n"));
                    this.jtaCommand.setText(stringBuilder.toString());
                    bufferedReader.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Não foi possível abrir o seu arquivo", "Aviso", ERROR_MESSAGE);
                }
                break;
        }
    }
    
    private void save(){
        if (this.filePath.isEmpty()) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setFileFilter(fileNameExtensionFilter);
            int opcao = jFileChooser.showSaveDialog(this);
            if (opcao == APPROVE_OPTION) {
                this.filePath = jFileChooser.getSelectedFile().getAbsolutePath();
                if (!this.filePath.toLowerCase().endsWith(".txt")) {
                    this.filePath += ".txt";
                }
                File arquivo = new File(filePath);
                JOptionPane.showMessageDialog(null, arquivo.getAbsoluteFile());
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo));
                    bufferedWriter.write(this.jtaCommand.getText());
                    bufferedWriter.close();
                } catch (IOException ex) {
                    JOptionPane.showInputDialog(null, "Não foi possível salvar o seu arquivo");
                }
            }

            this.mostrarNomeArquivo();
            this.clearMessageArea();
            return;
        }
        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filePath));
            bufferedWriter.write(jtaCommand.getText());
            bufferedWriter.close();
        } catch (IOException ex) {
            JOptionPane.showInputDialog(null, "Não foi possível salvar o seu arquivo");
        }
        
        this.clearMessageArea();
    }

    private void compilar(){
        jtaMessageArea.append("Compilação de programas ainda não foi implementada. \n");
    }
    
    private void sobre(){
        jtaMessageArea.append("Equipe formada por: Ana Paula Fidelis e Bárbara Marquez. \n");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpArea = new javax.swing.JPanel();
        jpButtons = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCopy = new javax.swing.JButton();
        btnPaste = new javax.swing.JButton();
        btnCut = new javax.swing.JButton();
        btnCompile = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaMessageArea = new javax.swing.JTextArea();
        jtfBarraStatus = new javax.swing.JTextField();
        jpAreaMensagem = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaCommand = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 620));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jpArea.setMinimumSize(new java.awt.Dimension(900, 620));

        jpButtons.setMaximumSize(new java.awt.Dimension(145, 590));
        jpButtons.setMinimumSize(new java.awt.Dimension(145, 590));

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_save_white_24dp_2x.png"))); // NOI18N
        btnSave.setText("save [ctrl-s]");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_content_copy_white_24dp_2x.png"))); // NOI18N
        btnCopy.setText("copy [ctrl-c]");
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        btnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_content_paste_white_24dp_2x.png"))); // NOI18N
        btnPaste.setText("paste [ctrl-v]");
        btnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasteActionPerformed(evt);
            }
        });

        btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_content_cut_white_24dp_2x.png"))); // NOI18N
        btnCut.setText("cut [ctrl-x]");
        btnCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCutActionPerformed(evt);
            }
        });

        btnCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_play_arrow_white_24dp_2x.png"))); // NOI18N
        btnCompile.setText("compile [F9]");
        btnCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompileActionPerformed(evt);
            }
        });

        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_supervisor_account_white_24dp_2x.png"))); // NOI18N
        btnAbout.setText("about [F1]");
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });
        btnAbout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAboutKeyPressed(evt);
            }
        });

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_folder_open_white_24dp_2x.png"))); // NOI18N
        btnOpen.setText("open [ctrl-o]");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhofinal/images/ic_insert_drive_file_white_24dp_2x.png"))); // NOI18N
        btnNew.setText("new [ctrl-n]");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        btnNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNewKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpButtonsLayout = new javax.swing.GroupLayout(jpButtons);
        jpButtons.setLayout(jpButtonsLayout);
        jpButtonsLayout.setHorizontalGroup(
            jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPaste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCompile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpButtonsLayout.setVerticalGroup(
            jpButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCopy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPaste)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCompile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbout))
        );

        jtaMessageArea.setEditable(false);
        jtaMessageArea.setColumns(20);
        jtaMessageArea.setRows(5);
        jScrollPane2.setViewportView(jtaMessageArea);

        jtfBarraStatus.setEditable(false);
        jtfBarraStatus.setMinimumSize(new java.awt.Dimension(900, 25));
        jtfBarraStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfBarraStatusActionPerformed(evt);
            }
        });

        jtaCommand.setColumns(20);
        jtaCommand.setRows(5);
        jtaCommand.setMinimumSize(new java.awt.Dimension(750, 480));
        jScrollPane1.setViewportView(jtaCommand);
        jtaCommand.getAccessibleContext().setAccessibleDescription("");
        jtaCommand.getAccessibleContext().setAccessibleParent(jpArea);

        javax.swing.GroupLayout jpAreaMensagemLayout = new javax.swing.GroupLayout(jpAreaMensagem);
        jpAreaMensagem.setLayout(jpAreaMensagemLayout);
        jpAreaMensagemLayout.setHorizontalGroup(
            jpAreaMensagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
        );
        jpAreaMensagemLayout.setVerticalGroup(
            jpAreaMensagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpAreaLayout = new javax.swing.GroupLayout(jpArea);
        jpArea.setLayout(jpAreaLayout);
        jpAreaLayout.setHorizontalGroup(
            jpAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAreaLayout.createSequentialGroup()
                        .addComponent(jtfBarraStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(53, Short.MAX_VALUE))
                    .addGroup(jpAreaLayout.createSequentialGroup()
                        .addComponent(jpButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jpAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpAreaLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                                .addGap(53, 53, 53))
                            .addComponent(jpAreaMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jpAreaLayout.setVerticalGroup(
            jpAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAreaLayout.createSequentialGroup()
                .addGroup(jpAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAreaLayout.createSequentialGroup()
                        .addComponent(jpAreaMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBarraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.createNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasteActionPerformed
        
    }//GEN-LAST:event_btnPasteActionPerformed

    private void btnCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCutActionPerformed
        
    }//GEN-LAST:event_btnCutActionPerformed

    private void btnCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompileActionPerformed
        this.compilar();
    }//GEN-LAST:event_btnCompileActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        this.sobre();
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        this.open();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNewKeyPressed

    }//GEN-LAST:event_btnNewKeyPressed

    private void btnAboutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAboutKeyPressed

    }//GEN-LAST:event_btnAboutKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jtfBarraStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfBarraStatusActionPerformed
        
    }//GEN-LAST:event_jtfBarraStatusActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        jpArea.setSize(this.getWidth(), this.getHeight());
        add(jpArea);
    }//GEN-LAST:event_formComponentResized

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
            java.util.logging.Logger.getLogger(Geral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Geral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Geral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Geral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Geral().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnCompile;
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnCut;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPaste;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpArea;
    private javax.swing.JPanel jpAreaMensagem;
    private javax.swing.JPanel jpButtons;
    private javax.swing.JTextArea jtaCommand;
    private javax.swing.JTextArea jtaMessageArea;
    private javax.swing.JTextField jtfBarraStatus;
    // End of variables declaration//GEN-END:variables
}
