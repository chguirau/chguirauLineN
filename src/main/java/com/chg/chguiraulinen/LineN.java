/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.chg.chguiraulinen;

/**
 *
 * @author carlosherrero
 */
public class LineN {
	static final int COMPUTER=0;
	static final int PLAYERS1=1;
	static final int PLAYERS2=2;
	int cols; //cols in the board
	int rows; //rows in the board
	int L; //number of pieces to line to win
	int mode; // COMPUTER, PLAYERS1, PLAYERS2
	boolean playerStarts; //if the player starts (is WHITE) or not (is BLACK)
	int level; //number of levels in the search tree to evaluate next move

	int currentTurn;
	int LastMoveTurn;
	
	LineN()
	{
		
	}
	
	LineN( int i, int j, int l, int aMode, boolean playerStart, int aLevel)
	{
		rows=i;
		cols=j;
		L=l;
		mode=aMode;
		playerStarts=playerStart;
		level=aLevel;	
		currentTurn=GameBoard.WHITE;
		fEvaluatedBoard	=new EvaluatedBoard(rows,cols,L);

	}
	
	EvaluatedBoard fEvaluatedBoard;
	
	int [][] getBoard()
	{
		return fEvaluatedBoard.fBoard;
	}
	
	EvaluatedMove evaluateNextMove() throws LineNException
	{
		EvaluatedMove a, b;
		a=fEvaluatedBoard.getEvaluatedMove();
		b=fEvaluatedBoard.getEvaluatedMove();
		a.setMinimun(currentTurn);
		b.setMaximun(currentTurn);
		//we get evaluatedMove objects with the proper dimensions (L for line, and eval arrays of size L) just to set minimum and maximum for alpha beta algorithm
		//return evaluateAlphaBeta_Max_multithread(fEvaluatedBoard,a,b,level,currentTurn, currentTurn);
		return evaluateAlphaBeta_Max(fEvaluatedBoard,a,b,level,currentTurn, currentTurn);
		//return evaluateAlphaBeta_Max_multithread or evaluateAlphaBeta_Max 
	}
	
	//these are the main methods - they evaluate the best move from current board
	EvaluatedMove evaluateAlphaBeta_Max( EvaluatedBoard current, EvaluatedMove A, EvaluatedMove B, int currentLevel, int color, int playerColor) throws LineNException
	{
		//"color" is turn color in recursive calls
		//"playerColor" is player color in this match, so that evaluation funtion is properly evaluated for black or white
		//in initial call is the same, but alphabeta recursively changes color to control the turn which is being evaluated
		
		EvaluatedMove Alpha, Beta, Val;
		int i;
		boolean end=false;
		EvaluatedBoard nextBoard;
		int oppColor;
		
		//if last search level or draw or any player wins
		//leaves are either draw, or any of the player wins
		if (currentLevel<=1 || current.WhiteWins() || current.BlackWins() || current.isDrawEvaluated()) 
			return current.getEvaluatedMove();
		Alpha=A;
		Beta=B;
		
		for (i=0; i<cols && !end; i++)
		{
			if (current.isValidMove(i))
			{
				nextBoard=current.replicate();
				nextBoard.moveAndEvaluate(i,color);
				oppColor=GameBoard.oppositeColor(color);
				Val=evaluateAlphaBeta_Min(nextBoard, new EvaluatedMove(Alpha),new EvaluatedMove(Beta),currentLevel-1,oppColor, playerColor);
				Alpha=Alpha.Max(Val,playerColor);
				//Important: Alpha=Max(Alpha,Val) if Alpha>Val (NOT if Alpha>=Val)

				//if it is first call to this method, we set the move in Alpha to know which move was the one with the Maximum value
				if (currentLevel==level)
				{
					if (Alpha==Val)
					//just got a new Maximum
					{
						Alpha.move=i;
					}
				}

				if (Alpha==Alpha.Max(Beta,playerColor))
				//if Alpha >= Beta
				{
					end=true;
				}
			}
		}	
		//at least there is one valid move, as first condition was false
		
		return Alpha;
	}

	EvaluatedMove evaluateAlphaBeta_Max_multithread( EvaluatedBoard current, EvaluatedMove A, EvaluatedMove B, int currentLevel, int color, int playerColor) throws LineNException
	{
		//"color" is turn color in recursive calls
		//"playerColor" is player color in this match, so that evaluation funtion is properly evaluated for black or white
		//in initial call is the same, but alphabeta recursively changes color to control the turn which is being evaluated
		//in multithread we launch several threads, each 1 to evaluate a different column move, then they sync and eventually continue with next column
		//for now 2 theads
		
		EvaluatedMove Alpha1, Beta1, Alpha2, Beta2;
		int i;
		boolean end=false;
		EvaluatedBoard nextBoard;
		int oppColor;
		
		//if last search level or draw or any player wins
		//leaves are either draw, or any of the player wins
		//if (currentLevel<=1 || current.WhiteWins() || current.BlackWins() || current.isDrawEvaluated()) 
		//	return current.getEvaluatedMove();

		initAvailableCols(); //shared
		setGlobalAlpha(A); //shared
		setGlobalEnd(false); //shared
		//inits list of cols to evaluate, i from 0 to col-1
		Object lock=new Object();
		Alpha1=new EvaluatedMove(A);
		Beta1=new EvaluatedMove(B);
		LineNThread t1=new LineNThread();
		t1.set(this, Alpha1, Beta1, color, currentLevel, playerColor, lock);
		t1.start();
		Alpha2=new EvaluatedMove(A);
		Beta2=new EvaluatedMove(B);
		LineNThread t2=new LineNThread();
		t2.set(this, Alpha2, Beta2, color, currentLevel, playerColor, lock);
		t2.start();
		
		try 
		{
			synchronized(lock)
			{
				lock.wait();
				lock.wait();
				//mainThread wait until end of evaluation
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return getGlobalAlpha();
	}
	
	//shared global EvaluatedMove between threads
	EvaluatedMove globalEvaluatedMove=null;
	synchronized void setGlobalAlpha( EvaluatedMove A)
	{
		globalEvaluatedMove=new EvaluatedMove(A);
		
	}
	
	EvaluatedMove getGlobalAlpha()
	{
		return globalEvaluatedMove;
	}
	
	synchronized void setGlobalAlphaMove(int i)
	{
		globalEvaluatedMove.move=i;
	}
	
	synchronized EvaluatedMove GlobalAlphaMax( EvaluatedMove A, int playerColor)
	{
		return globalEvaluatedMove.Max(A, playerColor);	
	}
	
	int nextAvailableCol=0;
	void initAvailableCols()
	{
		nextAvailableCol=0;
	}
	
	synchronized int getNextAvailableCol()
	{
		return nextAvailableCol;
	}
	
	synchronized void incrementNextAvailableCol()
	{
		nextAvailableCol++;
		if (nextAvailableCol==cols) setGlobalEnd(true);
	}
	
	Boolean GlobalEnd=Boolean.valueOf(false);
	synchronized void setGlobalEnd(boolean b)
	{
			GlobalEnd=b;
			//notify();
	}
	
	synchronized boolean isGlobalEnd()
	{
		return GlobalEnd;
	}
	


	
	EvaluatedMove evaluateAlphaBeta_Min( EvaluatedBoard current, EvaluatedMove A, EvaluatedMove B, int currentLevel, int color, int playerColor) throws LineNException
	{
		EvaluatedMove Alpha, Beta, Val;
		int i;
		boolean end=false;
		EvaluatedBoard nextBoard;
		int oppColor;
		
		//if last search level or draw or any player wins
		//leaves are either draw, or any of the player wins
		if (currentLevel<=1 || current.WhiteWins() || current.BlackWins() || current.isDrawEvaluated()) 
			return current.getEvaluatedMove();
		Alpha=A;
		Beta=B;
		
		for (i=0; i<cols && !end; i++)
		{
			if (current.isValidMove(i))
			{
				nextBoard=current.replicate();
				nextBoard.moveAndEvaluate(i,color);
				oppColor=GameBoard.oppositeColor(color);
				Val=evaluateAlphaBeta_Max(nextBoard, new EvaluatedMove(Alpha),new EvaluatedMove(Beta),currentLevel-1,oppColor, playerColor);
				Beta=Val.Min(Beta,playerColor);
				if (Alpha==Alpha.Max(Beta,playerColor))
				//if Alpha >= Beta
				{
					end=true;
				}
			}
		}	
		//at least there is one valid move, as first condition was false
		
		return Beta;
	
	}
	
		
	int move(int col) throws LineNException
	{
		int r;
		r=fEvaluatedBoard.moveAndEvaluate(col, currentTurn);	
		changeTurn();
		return r;
	}	
	
	void changeTurn()
	{
		if (currentTurn==GameBoard.WHITE)
		{
			currentTurn=GameBoard.BLACK;
			LastMoveTurn=GameBoard.WHITE;
		}
		else
		{
			currentTurn=GameBoard.WHITE;
			LastMoveTurn=GameBoard.BLACK;
		}
	}

	boolean isPlayerTurn()
	{
		if (mode==PLAYERS2) return true;
		if (mode==PLAYERS1)
		{
			if ((playerStarts && currentTurn==GameBoard.WHITE) || (!playerStarts && currentTurn==GameBoard.BLACK))
			{
				return true;
			}
		}
		return false;
	}	
	
	String getTurnString()
	{
		if (currentTurn==GameBoard.WHITE) return "White";
		else return "Black";
	}
	
	boolean isDraw()
	{
		//return (fEvaluatedBoard.isCompleted());
		return (fEvaluatedBoard.isDrawEvaluated());
	}
	
	boolean WhiteWins()
	{
		return (fEvaluatedBoard.WhiteWins());
	}
	
	boolean BlackWins()
	{
		return (fEvaluatedBoard.BlackWins());
	}
	
	boolean isEnd()
	{
		//return (isDraw() || (WhiteWins() && currentTurn==GameBoard.WHITE) || (BlackWins() && currentTurn==GameBoard.BLACK));
		return (isDraw() || WhiteWins() || BlackWins());
	}
}
