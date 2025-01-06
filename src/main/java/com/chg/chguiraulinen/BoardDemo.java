/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author carlosherrero
 */
public class BoardDemo extends JFrame {

     private BoardPanel jContentPane = null;
	/**
	 * This method initializes 
	 * 
	 */
	public BoardDemo() {
		super();
		initialize();
	}
	/**
	 * This method initializes 
	 * 
	 */
	public BoardDemo(int n, int m) {
		super();
		dimN=n;
		dimM=m;
		theGameBoard=new GameBoard(dimN,dimM);
		theGameBoard.init();
		theGameBoard.fBoard[1][4]=GameBoard.BLACK;
		theGameBoard.fBoard[2][3]=GameBoard.WHITE;
		initialize();
        JFrame f = new JFrame("BoardDemo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
        });
        f.getContentPane().add(getJContentPane(), BorderLayout.CENTER);
        f.setSize(new Dimension((dimM+1)*100, (dimN+1)*100));
        f.setVisible(true);
	}
	
	int dimN;
	int dimM;
	GameBoard theGameBoard;
	
	public static void main(String[] args) {
		BoardDemo thisBoardDemo=new BoardDemo(7,8);

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private BoardPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new BoardPanel();
			jContentPane.setDimension(dimN+1,dimM+1);
			jContentPane.setGameBoard(theGameBoard.fBoard);
			java.awt.BorderLayout layBorderLayout3 = new java.awt.BorderLayout();
			jContentPane.setLayout(layBorderLayout3);
			jContentPane.setPreferredSize(new java.awt.Dimension((dimM+1)*80,(dimN+1)*80));
		}
		return jContentPane;
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
        this.setContentPane(getJContentPane());
        this.setSize(157, 54);
			
	}
}  //  @jve:visual-info  decl-index=0 visual-constraint="0,0"
