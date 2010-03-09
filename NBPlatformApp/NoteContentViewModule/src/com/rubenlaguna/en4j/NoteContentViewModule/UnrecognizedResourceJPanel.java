/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UnrecognizedResourceJPanel.java
 *
 * Created on 2010-feb-15, 16:26:41
 */
package com.rubenlaguna.en4j.NoteContentViewModule;

/**
 *
 * @author ecerulm
 */
public class UnrecognizedResourceJPanel extends javax.swing.JPanel {

    /** Creates new form UnrecognizedResourceJPanel */
    public UnrecognizedResourceJPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        filenameJLabel = new javax.swing.JLabel();
        mimeJLabel = new javax.swing.JLabel();
        filesizeJLabel = new javax.swing.JLabel();

        jLabel12.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.jLabel12.text")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rubenlaguna/en4j/NoteContentViewModule/unknown-icon.png"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.jLabel4.text")); // NOI18N

        filenameJLabel.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.filenameJLabel.text")); // NOI18N

        mimeJLabel.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.mimeJLabel.text")); // NOI18N

        filesizeJLabel.setText(org.openide.util.NbBundle.getMessage(UnrecognizedResourceJPanel.class, "UnrecognizedResourceJPanel.filesizeJLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filenameJLabel)
                    .addComponent(mimeJLabel)
                    .addComponent(filesizeJLabel))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(filenameJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(mimeJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(filesizeJLabel))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel filenameJLabel;
    private javax.swing.JLabel filesizeJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel mimeJLabel;
    // End of variables declaration//GEN-END:variables

    void setFilename(String filename) {
        if (filename == null) {
            filenameJLabel.setText("[no name]");
        } else {
            filenameJLabel.setText(filename);
        }
    }

    void setMime(String mime) {
        mimeJLabel.setText(mime);
    }

    void setFilesize(int length) {
        filesizeJLabel.setText(new Integer(length).toString()+" bytes");
    }
}
