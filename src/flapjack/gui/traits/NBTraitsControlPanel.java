package flapjack.gui.traits;

import flapjack.gui.*;

class NBTraitsControlPanel extends javax.swing.JPanel
{
	NBTraitsControlPanel()
	{
		initComponents();

		RB.setText(bImport, "gui.traits.NBTraitsControlPanel.bImport");
		RB.setText(bRemove, "gui.traits.NBTraitsControlPanel.bRemove");
		
		bImport.setIcon(Icons.IMPORTTRAITS);
		bRemove.setIcon(Icons.DELETE);
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bImport = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        bRemove = new javax.swing.JButton();

        bImport.setText("Import trait data");

        statusLabel.setText("No trait data loaded");

        bRemove.setText("Remove all traits");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(statusLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 64, Short.MAX_VALUE)
                .add(bImport)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(bRemove)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(statusLabel)
                    .add(bRemove)
                    .add(bImport))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bImport;
    javax.swing.JButton bRemove;
    javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}