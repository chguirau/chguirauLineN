/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author carlosherrero
 */
public class LineNGame extends JFrame {

     private javax.swing.JPanel jContentPane = null;
     private javax.swing.JButton jButton = null;
     private javax.swing.JTextField jTextField = null;
     private javax.swing.JButton jButton1 = null;
	/**
	 * This method initializes 
	 * 
	 */
	public LineNGame() {
		super();
		initialize();
	}
	public static void main(String[] args) {
		new LineNGame();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
        this.setContentPane(getJContentPane());
        this.setSize(648, 662);
        this.setVisible(true);
        this.setTitle("LineNGame");
			
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJScrollPane(), null);
			//jContentPane.add(getJPanel(),null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton() {
		if(jButton == null) {
			jButton = new javax.swing.JButton();
			jButton.setBounds(34, 8, 79, 23);
			jButton.setText("Move");
			jButton.setEnabled(false);
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String t=getJTextField().getText();
					if (t != null)
					{
						playerLastMove=Integer.parseInt(t);
						playerLastMove--;
						play();
					}
					else
					{
						JOptionPane.showMessageDialog(getJContentPane(),"Invalid move", "user input error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return jButton;
	}
	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField() {
		if(jTextField == null) {
			jTextField = new javax.swing.JTextField();
			jTextField.setBounds(128, 11, 25, 19);
		}
		return jTextField;
	}
	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton1() {
		if(jButton1 == null) {
			jButton1 = new javax.swing.JButton();
			jButton1.setBounds(326, 8, 60, 19);
			jButton1.setText("New");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getLineNGameSetupDlg().setVisible(true);
				}
			});
		}
		return jButton1;
	}
	
	JDialog getLineNGameSetupDlg()
	{
		if (fLineNGameSetupDlg==null)
		{
			fLineNGameSetupDlg=new JDialog();
			fLineNGameSetupDlg.setContentPane(getJContentPane1());
			fLineNGameSetupDlg.setSize(266, 215);
			fLineNGameSetupDlg.setTitle("Setup new game");
			fLineNGameSetupDlg.setModal(true);
		}
		return fLineNGameSetupDlg;
	}
 
 	private JDialog fLineNGameSetupDlg=null;  //  @jve:visual-info  decl-index=0 visual-constraint="701,81"
     private javax.swing.JPanel jContentPane1 = null;
     private javax.swing.JButton jButton2 = null;
     private javax.swing.JButton jButton3 = null;
     private javax.swing.JRadioButton jRadioButton = null;
     private javax.swing.JRadioButton jRadioButton1 = null;
     private javax.swing.JRadioButton jRadioButton2 = null;
     private javax.swing.JLabel jLabel = null;
     private javax.swing.JLabel jLabel1 = null;
     private javax.swing.JLabel jLabel2 = null;
     private javax.swing.JRadioButton jRadioButton3 = null;
     private javax.swing.JLabel jLabel3 = null;
     private javax.swing.JTextField jTextField1 = null;
     private javax.swing.JTextField jTextField2 = null;
     private javax.swing.JTextField jTextField3 = null;
     private javax.swing.JTextField jTextField4 = null;
	/**
	 * This method initializes jContentPane1
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane1() {
		if(jContentPane1 == null) {
			jContentPane1 = new javax.swing.JPanel();
			jContentPane1.setLayout(null);
			jContentPane1.add(getJButton2(), null);
			jContentPane1.add(getJButton3(), null);
			jContentPane1.add(getJRadioButton(), null);
			jContentPane1.add(getJRadioButton1(), null);
			jContentPane1.add(getJRadioButton2(), null);
			jContentPane1.add(getJLabel(), null);
			jContentPane1.add(getJLabel1(), null);
			jContentPane1.add(getJLabel2(), null);
			jContentPane1.add(getJRadioButton3(), null);
			jContentPane1.add(getJLabel3(), null);
			jContentPane1.add(getJTextField1(), null);
			jContentPane1.add(getJTextField2(), null);
			jContentPane1.add(getJTextField3(), null);
			jContentPane1.add(getJTextField4(), null);
			// Group the radio buttons.
		    ButtonGroup group = new ButtonGroup();
		    group.add(getJRadioButton());
		    group.add(getJRadioButton1());
		    group.add(getJRadioButton2());

		}
		return jContentPane1;
	}
	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton2() {
		if(jButton2 == null) {
			jButton2 = new javax.swing.JButton();
			jButton2.setBounds(38, 154, 79, 26);
			jButton2.setText("ok");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try
					{
						gameSetup=true;
						gameN=Integer.parseInt(getJTextField1().getText());
						gameRows=Integer.parseInt(getJTextField2().getText());
						gameCols=Integer.parseInt(getJTextField3().getText());
						gameLevels=Integer.parseInt(getJTextField4().getText());
						if (gameN < 2) throw new Exception("N must be > 2");
						if (gameRows <= gameN) throw new Exception("Rows must be > N");
						if (gameCols < gameRows) throw new Exception("Cols must be > Rows");
						if (gameLevels < 1) throw new Exception("Levels must be >= 1");
						if (getJRadioButton().isSelected()) gamePlayers=LineN.COMPUTER;
						else if (getJRadioButton1().isSelected()) gamePlayers=LineN.PLAYERS1;
						else 
						//if (jRadioButton2.isSelected()) 
							gamePlayers=LineN.PLAYERS2;
						if (getJRadioButton3().isSelected()) gameStartPlayer=true;
						traceConfig();
						getJButton1().setEnabled(false);
						start();
						fLineNGameSetupDlg.setVisible(false);
					}
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(getJContentPane(),ex.toString(), "user input error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return jButton2;
	}
	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton3() {
		if(jButton3 == null) {
			jButton3 = new javax.swing.JButton();
			jButton3.setBounds(136, 154, 79, 26);
			jButton3.setText("cancel");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fLineNGameSetupDlg.setVisible(false);
				}
			});
		}
		return jButton3;
	}
	/**
	 * This method initializes jRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private javax.swing.JRadioButton getJRadioButton() {
		if(jRadioButton == null) {
			jRadioButton = new javax.swing.JRadioButton();
			jRadioButton.setBounds(23, 18, 99, 21);
			jRadioButton.setText("0 players");
		}
		return jRadioButton;
	}
	/**
	 * This method initializes jRadioButton1
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private javax.swing.JRadioButton getJRadioButton1() {
		if(jRadioButton1 == null) {
			jRadioButton1 = new javax.swing.JRadioButton();
			jRadioButton1.setBounds(23, 44, 99, 21);
			jRadioButton1.setText("1 player");
			jRadioButton1.setSelected(true);
		}
		return jRadioButton1;
	}
	/**
	 * This method initializes jRadioButton2
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private javax.swing.JRadioButton getJRadioButton2() {
		if(jRadioButton2 == null) {
			jRadioButton2 = new javax.swing.JRadioButton();
			jRadioButton2.setBounds(23, 70, 99, 21);
			jRadioButton2.setText("2 players");
		}
		return jRadioButton2;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel() {
		if(jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setBounds(124, 19, 30, 17);
			jLabel.setText("N");
		}
		return jLabel;
	}
	/**
	 * This method initializes jLabel1
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new javax.swing.JLabel();
			jLabel1.setBounds(123, 47, 43, 20);
			jLabel1.setText("Rows");
		}
		return jLabel1;
	}
	/**
	 * This method initializes jLabel2
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new javax.swing.JLabel();
			jLabel2.setBounds(124, 79, 40, 18);
			jLabel2.setText("Cols");
		}
		return jLabel2;
	}
	/**
	 * This method initializes jRadioButton3
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private javax.swing.JRadioButton getJRadioButton3() {
		if(jRadioButton3 == null) {
			jRadioButton3 = new javax.swing.JRadioButton();
			jRadioButton3.setBounds(32, 121, 99, 21);
			jRadioButton3.setText("I start");
		}
		return jRadioButton3;
	}
	/**
	 * This method initializes jLabel3
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new javax.swing.JLabel();
			jLabel3.setBounds(129, 122, 41, 18);
			jLabel3.setText("Levels");
		}
		return jLabel3;
	}
	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField1() {
		if(jTextField1 == null) {
			jTextField1 = new javax.swing.JTextField();
			jTextField1.setBounds(167, 18, 21, 19);
			jTextField1.setText("4");
		}
		return jTextField1;
	}
	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField2() {
		if(jTextField2 == null) {
			jTextField2 = new javax.swing.JTextField();
			jTextField2.setBounds(171, 46, 21, 19);
			jTextField2.setText("6");
		}
		return jTextField2;
	}
	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField3() {
		if(jTextField3 == null) {
			jTextField3 = new javax.swing.JTextField();
			jTextField3.setBounds(171, 78, 21, 19);
			jTextField3.setText("7");
		}
		return jTextField3;
	}
	/**
	 * This method initializes jTextField4
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField4() {
		if(jTextField4 == null) {
			jTextField4 = new javax.swing.JTextField();
			jTextField4.setBounds(178, 120, 21, 19);
			jTextField4.setText("4");
		}
		return jTextField4;
	}
	
	public int gameN=-1;
	public int gameRows=-1;
	public int gameCols=-1;
	public int gameLevels=-1;
	public boolean gameStartPlayer=false;
	public boolean gameSetup=false;
	public int gamePlayers=-1;
	public int playerLastMove;
	
     private javax.swing.JScrollPane jScrollPane = null;
     private javax.swing.JTextArea jTextArea = null;
	private void traceConfig()
	{
		getJTextArea().append("Config:\n");
		getJTextArea().append("N:"+gameN+"\n");
		getJTextArea().append("Rows:"+gameRows+"\n");
		getJTextArea().append("Cols:"+gameCols+"\n");
		getJTextArea().append("Levels:"+gameLevels+"\n");
		getJTextArea().append("Players:"+gamePlayers+"\n");
		if (gamePlayers==LineN.PLAYERS1 && gameStartPlayer) getJTextArea().append("Player starts\n");
		if (gamePlayers==LineN.PLAYERS1 && !gameStartPlayer) getJTextArea().append("computer starts\n");
		
	}
	
	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private javax.swing.JScrollPane getJScrollPane() {
		if(jScrollPane == null) {
			jScrollPane = new javax.swing.JScrollPane();
			jScrollPane.setViewportView(getJTextArea());
			jScrollPane.setBounds(4, 521, 632, 110);
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private javax.swing.JTextArea getJTextArea() {
		if(jTextArea == null) {
			jTextArea = new javax.swing.JTextArea();
		}
		return jTextArea;
	}

	private JButton boardPanelButtons[][];
	private JLabel boardPanelRowLabels[];
	private JLabel boardPanelColLabels[];
	private void createBoard ( int [][] board)
	{
		JButton jButton=null;
		JLabel rowName=null;
		JLabel colName=null;
		int i,j;
		if (boardPanelButtons==null) boardPanelButtons=new JButton[10][10];
		if (boardPanelRowLabels==null) boardPanelRowLabels=new JLabel[10];
		if (boardPanelColLabels==null) boardPanelColLabels=new JLabel[10];
		for ( i=0; i<10; i++)
		{
			if (boardPanelColLabels[i]!=null)
			{
				colName=boardPanelColLabels[i];
				jContentPane.remove(colName);
			}
			if (boardPanelRowLabels[i]!=null)
			{
				rowName=boardPanelRowLabels[i];
				jContentPane.remove(rowName);				
			}
			for ( j=0; j<10 ; j++)
			{
				if (boardPanelButtons[i][j]!=null)
				{
					jButton=boardPanelButtons[i][j];
					jContentPane.remove(jButton);
				}
			}
		}
		jContentPane.repaint();
		jButton=null;
		rowName=null;
		colName=null;
		for ( i=0; i<engine.rows; i++)
		{
			rowName=new JLabel();
			rowName.setBounds(20,105+40*i,50,10);
			rowName.setText("Row #"+ (engine.rows-i));
			boardPanelRowLabels[i]=rowName;
			jContentPane.add(rowName);				
			for ( j=0; j<engine.cols; j++)
			{
				if (i==0)
				{
					colName=new JLabel();
					colName.setBounds(78+40*j,72,23,15);
					colName.setText("#"+(j+1));
					boardPanelColLabels[j]=colName;
					jContentPane.add(colName);
				}
				jButton = new javax.swing.JButton();
				jButton.setSize(38,38);
				jButton.setLocation(75+40*j, 92+40*i);
				//jButton.setVisible(false);
				boardPanelButtons[engine.rows-1-i][j]=jButton;
				jButton.setBackground(blackColor);
				jContentPane.add(jButton);
			}
		}
		jContentPane.setVisible(true);
		jContentPane.setEnabled(true);
	}

	private LineN engine;
	private void start()
	{
		int r,col;
		EvaluatedMove theMove;
		try
		{
			engine=new LineN(gameRows,gameCols,gameN,gamePlayers,gameStartPlayer,gameLevels);
			createBoard(engine.getBoard());
			if (engine.isPlayerTurn())
			{
				getJTextArea().append("It is player turn to move ("+engine.getTurnString()+")\n");
				getJButton().setEnabled(true);
			}
			else
			{
				//computer turn
				getJTextArea().append("It is computer turn to move ("+engine.getTurnString()+"). Please wait...\n");
				theMove=engine.evaluateNextMove();
				if (theMove.isDrawEvaluated()) getJTextArea().append("Evaluation engine: DRAW!\n");
				else if (theMove.isWhiteWinsEvaluated()) getJTextArea().append("Evaluation engine: WHITE WINS!\n");
				else if (theMove.isBlackWinsEvaluated()) getJTextArea().append("Evaluation engine: BLACK WINS!\n");

				col=theMove.move;
				r=engine.move(col);
        			getJTextArea().append("Move "+(col+1)+"\n");
				drawMove(r,col,engine.LastMoveTurn);
				if (engine.isEnd())
				{
					endGame();
                                        if (engine.isDraw()) getJTextArea().append("Game over, DRAW!\n");
                                        else if (engine.WhiteWins()) getJTextArea().append("Game over, White wins!\n");
                                        else if (engine.BlackWins()) getJTextArea().append("Game over, Black wins!\n");
                                        else throw new LineNException("Error in End Game evaluation");
                                                
				}
				//turn has changed, check again
				if (engine.isPlayerTurn())
				//computer vs player game
				{
					getJTextArea().append("It is player turn to move ("+engine.getTurnString()+")\n");
					getJButton().setEnabled(true);
				}
				else
				//computer vs computer game
				{
					while(!engine.isEnd())
					{
						getJTextArea().append("It is computer turn to move ("+engine.getTurnString()+"). Please wait...\n");
						theMove=engine.evaluateNextMove();
						if (theMove.isDrawEvaluated()) getJTextArea().append("Evaluation engine: DRAW!\n");
						else if (theMove.isWhiteWinsEvaluated()) getJTextArea().append("Evaluation engine: WHITE WINS!\n");
						else if (theMove.isBlackWinsEvaluated()) getJTextArea().append("Evaluation engine: BLACK WINS!\n");
						col=theMove.move;
						r=engine.move(col);
                        			getJTextArea().append("Move "+(col+1)+"\n");
						drawMove(r,col,engine.LastMoveTurn);
					}
					endGame();
				}
			}
		}
		catch(LineNException ex)
		{
			getJTextArea().append(ex.getMessage()+"\n");
		}
	}
	
	private static final Color blackColor=new java.awt.Color(0,0,0);
	private static final Color whiteColor=new java.awt.Color(200,200,200);
	private static final Color emptyColor=new java.awt.Color(255,0,0);
	
	
	void drawMove(int i, int j, int player)
	{
		System.out.print("Move " + j);
		JButton b=boardPanelButtons[i][j];
		b.setVisible(false);
		b.setOpaque(true);
		if (player==GameBoard.WHITE)
		{
			b.setBackground(whiteColor);
			System.out.println(" White");
		}
		else
		{
			b.setBackground(blackColor);
			System.out.println(" Black");
		}
		b.setVisible(true);
	}
	
	private void play()
	{
		int r;
		try
		{
			getJButton().setEnabled(false);			
			r=engine.move(playerLastMove);
			getJTextArea().append("Move "+(playerLastMove+1)+"\n");
			drawMove(r,playerLastMove,engine.LastMoveTurn);
			if (engine.isEnd())
			{
				endGame();
			}
			//turn has changed
			else if (engine.isPlayerTurn())
			//player vs player game
			{
				getJTextArea().append("It is player turn to move ("+engine.getTurnString()+")\n");
				getJButton().setEnabled(true);			
			}
			else
			//player vs computer game
			{
				int col;
				getJTextArea().append("It is computer turn to move ("+engine.getTurnString()+"). Please wait...\n");
				EvaluatedMove theMove=engine.evaluateNextMove();
				//theMove=engine.evaluateNextMove();
				if (theMove.isDrawEvaluated()) getJTextArea().append("Evaluation engine: DRAW!\n");
				else if (theMove.isWhiteWinsEvaluated()) getJTextArea().append("Evaluation engine: WHITE WINS!\n");
				else if (theMove.isBlackWinsEvaluated()) getJTextArea().append("Evaluation engine: BLACK WINS!\n");
				col=theMove.move;
				r=engine.move(col);
        			getJTextArea().append("Move "+(col+1)+"\n");
				drawMove(r,col,engine.LastMoveTurn);
				if (engine.isEnd())
				{
					endGame();
				}
				else
				{	
					//turn has changed, check again
					getJTextArea().append("It is player turn to move ("+engine.getTurnString()+")\n");
					getJButton().setEnabled(true);
				}
			}
		}
		catch(LineNException ex)
		{
			getJTextArea().append(ex.getMessage()+"\n");
		}		
	}

	private void endGame()
	{
	    	if (engine.isDraw())
	    	{
				getJTextArea().append("End of Game. Draw! \n");	    		
	    	}
	    	else if (engine.WhiteWins())
	    	{
	    		getJTextArea().append("End of Game. White Wins! \n");
	    	}
	    	else if (engine.BlackWins())
	    	{
	    		getJTextArea().append("End of Game. Black Wins! \n");
	    	}
			getJButton1().setEnabled(true);
			getJButton().setEnabled(false);
	}	
	
}  //  @jve:visual-info  decl-index=0 visual-constraint="18,23"