/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

/**
 *
 * @author carlosherrero
 */
public class LineNThread extends Thread {

	public void set(LineN parentThread, EvaluatedMove A, EvaluatedMove B, int color, int currentLevel, int playerColor, Object lock)
	{
		mainThread=parentThread;
		thisThread=new LineN();
		thisThread.rows=mainThread.rows;
		thisThread.cols=mainThread.cols;
		thisThread.L=mainThread.L;
		thisThread.mode=mainThread.mode;
		thisThread.playerStarts=mainThread.playerStarts;
		thisThread.level=mainThread.level;
		thisThread.currentTurn=mainThread.currentTurn;
		thisThread.fEvaluatedBoard=mainThread.fEvaluatedBoard.replicate();
		tAlpha=A;
		tBeta=B;
		tColor=color;
		tCurrentLevel=currentLevel;
		tPlayerColor=playerColor;
		threadNumber++;
		setName(""+threadNumber);
		tLock=lock;
	}
	
	LineN mainThread;
	LineN thisThread;
	EvaluatedMove tAlpha;
	EvaluatedMove tBeta;
	int tColor;
	int tCurrentLevel;
	int tPlayerColor;
	static int threadNumber=0;
	Object tLock;
	
	public void run()
	{	
		try
		{
		
		EvaluatedMove Alpha, Beta, Val;
		Alpha=tAlpha;
		Beta=tBeta;
		int i;
		EvaluatedBoard nextBoard;
		int oppColor;
		
		i=mainThread.getNextAvailableCol(); //shared
		//returns next available col but cols and End=true if limit
		while (!mainThread.isGlobalEnd())
		//there are still cols to evaluate
		{
			if (thisThread.fEvaluatedBoard.isValidMove(i))
			{
				nextBoard=thisThread.fEvaluatedBoard.replicate();
				nextBoard.moveAndEvaluate(i,tColor);
				oppColor=GameBoard.oppositeColor(tColor);
				Val=thisThread.evaluateAlphaBeta_Min(nextBoard, new EvaluatedMove(Alpha),new EvaluatedMove(Beta),tCurrentLevel-1,oppColor, tPlayerColor);
				Alpha=Alpha.Max(Val,tPlayerColor); //shared
				//Important: Alpha=Max(Alpha,Val) if Alpha>Val (NOT if Alpha>=Val)

				if (Alpha==Val)
				//just got a new Maximum
				{
					if (Alpha==mainThread.GlobalAlphaMax(Alpha, tPlayerColor))
					{
						mainThread.setGlobalAlpha(Alpha);
						mainThread.setGlobalAlphaMove(i);
					}
				}
				
				Alpha=mainThread.GlobalAlphaMax(Alpha,tPlayerColor);
				//in any case we check if GlobalAlpha changed during this thread evaluation and it is a new maximum, and set it
				

				if (Alpha==Alpha.Max(Beta,tPlayerColor))
				//if Alpha >= Beta
				{
						mainThread.setGlobalEnd(true);
				}
			}
			mainThread.incrementNextAvailableCol();
			i=mainThread.getNextAvailableCol();
			//Debug
			System.out.println(this.getName() + " Next" + i);
		}	
		//at least there is one valid move, as first condition was false
		synchronized(tLock)
		{
			tLock.notify();
		}

		}
		catch (LineNException e)
		{
			e.printStackTrace();
		}
	}

}