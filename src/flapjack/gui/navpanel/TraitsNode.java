package flapjack.gui.navpanel;

import java.awt.*;
import javax.swing.*;

import flapjack.data.*;
import flapjack.gui.*;
import flapjack.gui.traits.*;

public class TraitsNode extends BaseNode
{
	private TabPanel tabbedPanel;

	public TraitsNode(DataSet dataSet)
	{
		super(dataSet);

		tabbedPanel = new TabPanel(dataSet);
	}

	public String toString()
	{
		return RB.getString("gui.navpanel.TraitsNode.node");
	}

	public void setActions()
	{
		Actions.vizNewView.setEnabled(true);

		// TODO: make dynamic based on inclusion of QTL data or not
		Actions.dataFilterQTLs.setEnabled(true);
		Actions.dataRenameDataSet.setEnabled(true);
		Actions.dataDeleteDataSet.setEnabled(true);
	}

	public JPanel getPanel()
	{
		return tabbedPanel;
	}
}