package flapjack.gui.dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import flapjack.data.*;

class NBFilterQTLsPanel extends javax.swing.JPanel
{
	private DataSet dataSet;

	// Stores all the traits and experiments (and their visibility states)
	private Hashtable<String, Boolean> traits;
	private Hashtable<String, Boolean> experiments;

	public NBFilterQTLsPanel(DataSet dataSet)
	{
		initComponents();

		setBackground((Color)UIManager.get("fjDialogBG"));
		panel.setBackground((Color)UIManager.get("fjDialogBG"));

		this.dataSet = dataSet;

		initHashtables();
		createTraitsTable();
		createExperimentsTable();
	}

	private void initHashtables()
	{
		traits = new Hashtable<String, Boolean>();
		experiments = new Hashtable<String, Boolean>();

		// Scan every track in every chromosome
		for (ChromosomeMap cMap: dataSet.getChromosomeMaps())
			for (Vector<Feature> track: cMap.getTrackSet())
				for (Feature feature: track)
					if (feature instanceof QTL)
					{
						QTL qtl = (QTL) feature;

						Boolean tValue = traits.get(qtl.getTrait());

						// Either add the trait information...
						if (tValue == null)
							traits.put(qtl.getTrait(), qtl.isVisible());
						// Or update it to ensure that if *any* QTL is visible
						// with this trait, then it must be enabled
						else if (qtl.isVisible())
							traits.put(qtl.getTrait(), true);

						// And the same for experiments
						Boolean eValue = experiments.get(qtl.getExperiment());

						if (eValue == null)
							experiments.put(qtl.getExperiment(), qtl.isVisible());
						else if (qtl.isVisible())
							experiments.put(qtl.getExperiment(), true);
					}
	}

	// Fill the traits table with data
	private void createTraitsTable()
	{
		String[] columnNames = { "Trait", "Visible" };

		Object[][] data = new Object[traits.size()][2];
		Enumeration<String> keys = traits.keys();

		for (int i = 0; keys.hasMoreElements(); i++)
		{
			data[i][0] = keys.nextElement();
			data[i][1] = new Boolean(traits.get(data[i][0]));
		}

		traitsTable.setModel(getModel(data, columnNames));
	}

	// Fill the experiments table with data
	private void createExperimentsTable()
	{
		String[] columnNames = { "Experiment", "Visible" };

		Object[][] data = new Object[traits.size()][2];
		Enumeration<String> keys = experiments.keys();

		for (int i = 0; keys.hasMoreElements(); i++)
		{
			data[i][0] = keys.nextElement();
			data[i][1] = new Boolean(experiments.get(data[i][0]));
		}

		experimentsTable.setModel(getModel(data, columnNames));
	}

	// Builds a table model for the two methods above
	private DefaultTableModel getModel(Object[][] data, String[] columnNames)
	{
		return new DefaultTableModel(data, columnNames)
		{
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

			public boolean isCellEditable(int row, int col) {
				return col == 1;
			}
		};
	}

	// Runs the actual filter operation
	void filterQTLs()
	{
		// Update the hashtables with the latest values from the UI tables
		traits.clear();
		experiments.clear();

		for (int i = 0; i < traitsTable.getRowCount(); i++)
		{
			String tName = (String) traitsTable.getValueAt(i, 0);
			boolean tValue = (Boolean) traitsTable.getValueAt(i, 1);

			traits.put(tName, tValue);
		}

		for (int i = 0; i < experimentsTable.getRowCount(); i++)
		{
			String eName = (String) experimentsTable.getValueAt(i, 0);
			boolean eValue = (Boolean) experimentsTable.getValueAt(i, 1);

			experiments.put(eName, eValue);
		}

		// Scan over all the chromosomes/tracks and update the QTLs
		for (ChromosomeMap cMap: dataSet.getChromosomeMaps())
			for (Vector<Feature> track: cMap.getTrackSet())
				for (Feature feature: track)
					if (feature instanceof QTL && feature.isAllowed())
					{
						QTL qtl = (QTL) feature;

						// Make the QTL visible only if both its trait and
						// exeperiment values have been set to true
						if (traits.get(qtl.getTrait()) && experiments.get(qtl.getExperiment()))
							qtl.setVisible(true);
						else
							qtl.setVisible(false);
					}
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        traitsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        experimentsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter visible QTLs:"));

        jLabel1.setText("You can filter which QTLs are visible by using the options below.");

        traitsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        traitsTable.setRowSelectionAllowed(false);
        traitsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(traitsTable);

        experimentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        experimentsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(experimentsTable);

        jLabel2.setText("Only include QTLs for these traits:");

        jLabel3.setText("Only include QTLs for these experiments:");

        org.jdesktop.layout.GroupLayout panelLayout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel1)
                    .add(panelLayout.createSequentialGroup()
                        .add(panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2))
                        .add(18, 18, 18)
                        .add(panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelLayout.linkSize(new java.awt.Component[] {jScrollPane1, jScrollPane2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelLayout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(panelLayout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(panel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable experimentsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel;
    private javax.swing.JTable traitsTable;
    // End of variables declaration//GEN-END:variables

}
