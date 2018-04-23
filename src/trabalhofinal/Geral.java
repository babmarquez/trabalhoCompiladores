/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bmarquez
 */
public class Geral extends javax.swing.JFrame {

    //Classe para controle dos clicks e atalhos
    public class ShortcutButton extends AbstractAction {

        private EnumKeys shortcut;

        @Override
        public void actionPerformed(ActionEvent e) {
            //Compara os atalhos através dos valores do enum
            switch (this.shortcut) {
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

        public ShortcutButton(EnumKeys keyPressed) {
            this.shortcut = keyPressed;
        }
    }

    //Área de declaração dos botões
    private ShortcutButton buttonNew = new ShortcutButton(EnumKeys.CTRLN);
    private ShortcutButton buttonOpen = new ShortcutButton(EnumKeys.CTRLO);
    private ShortcutButton buttonSave = new ShortcutButton(EnumKeys.CTRLS);
    private ShortcutButton buttonCompile = new ShortcutButton(EnumKeys.F9);
    private ShortcutButton buttonAbout = new ShortcutButton(EnumKeys.F1);

    private String filePath;
    private Clipboard clipboard;
    private TransferHandler transferHandler;
    private FileNameExtensionFilter fileNameExtensionFilter;

    /**
     * Creates new form Geral
     */
    public Geral() {
        //Inicia os componentes
        initComponents();

        //Seta a borda numerada no campo de edição
        jtaCommand.setBorder(new NumberedBorder());

        jpButtons.setBackground(Color.LIGHT_GRAY);
        jpArea.setBackground(Color.LIGHT_GRAY);
        jpAreaMensagem.setBackground(Color.LIGHT_GRAY);
        jtfBarraStatus.setBackground(Color.LIGHT_GRAY);
        
        jtaMessageArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));        

        //Registra as ações do teclado
        this.keyboardActions(jpArea);
        this.keyboardActions(jpButtons);
        this.keyboardActions(jpAreaMensagem);
        //O tipo de extensão do arquivo
        this.fileNameExtensionFilter = new FileNameExtensionFilter("Documento de Texto (*.txt)", "txt");
        //Controle dos atalhos de copiar, colar e recortar
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        transferHandler = jtaCommand.getTransferHandler();
        //Inicializa a variável como vazia
        showFileName("");
    }

    private void keyboardActions(JPanel painel) {
        //Cria o ActionMap
        ActionMap actionMap = painel.getActionMap();

        actionMap.put("new", buttonNew);
        actionMap.put("open", buttonOpen);
        actionMap.put("save", buttonSave);
        actionMap.put("compile", buttonCompile);
        actionMap.put("aboutWe", buttonAbout);

        painel.setActionMap(actionMap);

        //Pegamos o input map
        InputMap imap = painel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

        //Teclas do teclado
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), "new");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK), "open");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), "save");
        imap.put(KeyStroke.getKeyStroke("F9"), "compile");
        imap.put(KeyStroke.getKeyStroke("F1"), "aboutWe");
    }

    private void showFileName(String path) {
        //Salva o nome da variável
        this.filePath = path;
        //Coloca como texto da nossa barra de status
        jtfBarraStatus.setText(this.filePath);
    }

    private void clearMessageArea() {
        //Limpa a área de mensagem
        this.jtaMessageArea.setText("");
    }

    private void clearAll() {
        //Limpa todos os componentes
        this.filePath = "";
        this.jtaCommand.setText("");
        this.jtfBarraStatus.setText("");

        this.clearMessageArea();
    }

    private void createNew() {
        //Para criar um novo, deve limpar tudo
        this.clearAll();
    }

    private void open() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(fileNameExtensionFilter);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        int open = jFileChooser.showOpenDialog(this);
        switch (open) {
            case APPROVE_OPTION:
                this.clearAll();
                File file = jFileChooser.getSelectedFile();
                this.showFileName(file.getAbsolutePath());
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
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

    private void save() {
        if (this.filePath.isEmpty()) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setFileFilter(fileNameExtensionFilter);
            int option = jFileChooser.showSaveDialog(this);
            if (option == APPROVE_OPTION) {
                this.showFileName(jFileChooser.getSelectedFile().getAbsolutePath());
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

    private void cut(java.awt.event.ActionEvent evt) {
        transferHandler.exportToClipboard(jtaCommand, clipboard, TransferHandler.MOVE);
    }

    private void copy(java.awt.event.ActionEvent evt) {
        transferHandler.exportToClipboard(jtaCommand, clipboard, TransferHandler.COPY);
    }

    private void paste(java.awt.event.ActionEvent evt) {
        transferHandler.importData(jtaCommand, clipboard.getContents(null));
    }

    private void compile() {
        //Implementada a parte léxica da compilação
        Lexico lexico = new Lexico();
        
        Util util = new Util();

        util.setTexto(jtaCommand.getText());        
        lexico.setInput(jtaCommand.getText());
        
        int tmClasse = 30;
        int tmLinha  = 10;
        
        try {
            Token t = null;
            
            if (jtaCommand.getText().trim().isEmpty()){
                jtaMessageArea.setText("Nenhum programa para compilar");
            }else{
                jtaMessageArea.setText(util.preencheDireita("linha", tmLinha, ' ')+util.preencheDireita("classe", tmClasse, ' ')+util.preencheDireita("lexema", tmClasse, ' ')+"\n");
                while ((t = lexico.nextToken()) != null) {
                    jtaMessageArea.setText(jtaMessageArea.getText()+
                            util.preencheDireita(Integer.toString(util.getLinhaAtu(t.getPosition())), tmLinha, ' ')+   //Posição
                            util.preencheDireita(util.getClasse((Integer)t.getId()), tmClasse, ' ')+ //Classe
                            util.preencheDireita(t.getLexeme(), tmClasse, ' ')+"\n");                //lexema
                }
                jtaMessageArea.setText(jtaMessageArea.getText()+ "\nPrograma compilado com sucesso.");
            }
        } catch (LexicalError e) {
            jtaMessageArea.setText("Erro na linha "+util.getLinhaAtu(e.getPosition())+" - "+e.getMessage());
        }
    }

    private void about() {
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
        java.awt.GridBagConstraints gridBagConstraints;

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
        getContentPane().setLayout(new java.awt.CardLayout());

        jpArea.setMinimumSize(new java.awt.Dimension(900, 620));
        jpArea.setPreferredSize(new java.awt.Dimension(900, 620));
        jpArea.setLayout(new java.awt.GridBagLayout());

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
                .addContainerGap()
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
                .addComponent(btnAbout)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jpArea.add(jpButtons, gridBagConstraints);

        jtaMessageArea.setEditable(false);
        jtaMessageArea.setColumns(200);
        jtaMessageArea.setRows(200);
        jtaMessageArea.setMinimumSize(new java.awt.Dimension(750, 105));
        jtaMessageArea.setPreferredSize(new java.awt.Dimension(750, 105));
        jScrollPane2.setViewportView(jtaMessageArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 750;
        gridBagConstraints.ipady = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 0, 0);
        jpArea.add(jScrollPane2, gridBagConstraints);

        jtfBarraStatus.setEditable(false);
        jtfBarraStatus.setBackground(new java.awt.Color(102, 102, 102));
        jtfBarraStatus.setToolTipText("");
        jtfBarraStatus.setBorder(null);
        jtfBarraStatus.setMinimumSize(new java.awt.Dimension(900, 25));
        jtfBarraStatus.setPreferredSize(new java.awt.Dimension(900, 25));
        jtfBarraStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfBarraStatusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 15, 31, 0);
        jpArea.add(jtfBarraStatus, gridBagConstraints);

        jpAreaMensagem.setMinimumSize(new java.awt.Dimension(750, 480));
        jpAreaMensagem.setPreferredSize(new java.awt.Dimension(750, 480));
        jpAreaMensagem.setLayout(new java.awt.GridLayout(1, 0));

        jtaCommand.setColumns(200);
        jtaCommand.setRows(200);
        jtaCommand.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(jtaCommand);
        jtaCommand.getAccessibleContext().setAccessibleDescription("");
        jtaCommand.getAccessibleContext().setAccessibleParent(jpArea);

        jpAreaMensagem.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 150, 0);
        jpArea.add(jpAreaMensagem, gridBagConstraints);

        getContentPane().add(jpArea, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.createNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        this.copy(evt);
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasteActionPerformed
        this.paste(evt);
    }//GEN-LAST:event_btnPasteActionPerformed

    private void btnCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCutActionPerformed
        this.cut(evt);
    }//GEN-LAST:event_btnCutActionPerformed

    private void btnCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompileActionPerformed
        this.compile();
    }//GEN-LAST:event_btnCompileActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        this.about();
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
