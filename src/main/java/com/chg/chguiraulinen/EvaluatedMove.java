/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

/**
 *
 * @author carlosherrero
 */
public class EvaluatedMove {
	int L; //size of lines
	
	private int evalWhite[];
	private int evalBlack[];

	public EvaluatedMove( int otherL, int otherEvalWhite[], int otherEvalBlack[])
	{
		L=otherL;
		evalWhite=new int[L];
		evalBlack=new int[L];
		for (int i=0; i<L; i++)
		{
			evalWhite[i]=otherEvalWhite[i];
			evalBlack[i]=otherEvalBlack[i];
		}
	}
	
	public EvaluatedMove( EvaluatedMove other)
	{
		L=other.L;
		evalWhite=new int[L];
		evalBlack=new int[L];
		for (int i=0; i<L; i++)
		{
			evalWhite[i]=other.evalWhite[i];
			evalBlack[i]=other.evalBlack[i];
		}				
	}
	
	public EvaluatedMove Max (EvaluatedMove other, int playerColor )
	{
		//evaluation from playerColor point of view
		int i,k;
                
                
		for (i=L; i>0; i--)
		{
			k=evalWhite[i-1]-evalBlack[i-1]-other.evalWhite[i-1]+other.evalBlack[i-1];
			if (k<0)
			{
				if (playerColor==GameBoard.WHITE) return other; else return this;
			}
			else if (k>0)
			{
				if (playerColor==GameBoard.WHITE) return this; else return other;
			}
			// else next step
		}
		//equal 
		return this;
	}
	
	public EvaluatedMove Min (EvaluatedMove other, int playerColor)
	{
		if (other==Max(other,playerColor)) return this;
		else return other;
	}
	
	public int move;
	
	public void setMinimun(int turn)
	{
		if (turn==GameBoard.WHITE) evalBlack[L-1]=100; else evalWhite[L-1]=100;
                // we set 100 as we want to be lower than a real minimum 
                // 100 is big enough
                // a real minumum with L-1 == 1 or 2 or ... maybe up to 7*N, in all directions except up, 
                // as it is not possible to put a position down another (as it falls over them)  
	}
	
	public void setMaximun(int turn)
	{
		if (turn==GameBoard.WHITE) evalWhite[L-1]=100; else evalBlack[L-1]=100 ;
                // we set 100 as we want to be higher than a real maximum 
	}
	
	public boolean isDrawEvaluated()
	{
		return false;
	}
	public boolean isWhiteWinsEvaluated()
	{
		return (evalWhite[L-1]>0);
	}
	public boolean isBlackWinsEvaluated()
	{
		return (evalBlack[L-1]>0);
	}
}
