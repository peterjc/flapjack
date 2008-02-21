package flapjack.gui.visualization;

import java.awt.event.*;
import javax.swing.*;

import flapjack.gui.*;

class CanvasMenu
{
	private GenotypePanel gPanel;
	private GenotypeCanvas canvas;

	private JPopupMenu menu = new JPopupMenu();

	private JCheckBoxMenuItem mLock;
	private JMenuItem mSortLines;
	private JCheckBoxMenuItem mShowGenotypes;

	private AbstractAction aLock;
	private AbstractAction aSortLines;
	private AbstractAction aShowGenotypes;

	CanvasMenu(GenotypePanel gPanel, GenotypeCanvas canvas)
	{
		this.gPanel = gPanel;
		this.canvas = canvas;

		createActions();
		createItems();
	}

	boolean isShowingMenu()
		{ return menu.isVisible(); }

	private void createActions()
	{
		aLock = new AbstractAction(RB.getString("gui.Actions.canvasLock")) {
			public void actionPerformed(ActionEvent e) {
				canvas.locked = !canvas.locked;
			}
		};

		aSortLines = new AbstractAction(RB.getString("gui.Actions.canvasSortLines")) {
			public void actionPerformed(ActionEvent e)
			{
				int line = canvas.highlightY;
				new flapjack.analysis.SimilaritySort(canvas.view, line).run();

				gPanel.refreshView();
				gPanel.jumpToPosition(0, 0);
			}
		};

		aShowGenotypes = new AbstractAction(RB.getString("gui.Actions.canvasShowGenotypes")) {
			public void actionPerformed(ActionEvent e)
			{
				Prefs.visShowGenotypes = !Prefs.visShowGenotypes;
				gPanel.refreshView();
			}
		};
	}

	private void createItems()
	{
		mLock = WinMainMenuBar.getCheckedItem(aLock, KeyEvent.VK_L, 0, 0, canvas.locked);
		mSortLines = WinMainMenuBar.getItem(aSortLines, KeyEvent.VK_S, 0, 0);
		mShowGenotypes = WinMainMenuBar.getCheckedItem(aShowGenotypes, KeyEvent.VK_G, 0, 0, Prefs.visShowGenotypes);
	}

	void handlePopup(MouseEvent e)
	{
		menu = new JPopupMenu();

		menu.add(mLock);
		menu.add(mShowGenotypes);
		menu.addSeparator();
		menu.add(mSortLines);

		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}