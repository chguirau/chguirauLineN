/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author carlosherrero
 */
public class EvaluatedBoard extends GameBoard {
	public EvaluatedBoard(int n, int m, int sizeOfLine)
	{
		super(n,m);
		L=sizeOfLine;	
		initEval();
	}

	public EvaluatedBoard()
	{
	}
	
	int L; //size of lines
	
	private int evalWhite[];
	private int evalBlack[];
	private int evalEmpty;
	private static ArrayList evalBoard[][];
	private ArrayList possibleLinesList;
	
	protected void initEval()
	{
		int i,j,k;
		evalWhite=new int[L]; 
		// evalWhite[0] is number of existing Lines in the Board with 1 white piece and no black pieces
		// evalWhite[1] is number of existing Lines in the Board with 2 white piece and no black pieces
		// ... and evalWhite[L-1] with L, and idem with evalBlack
		//evalEmpty is number of empty lines (with no white or black pieces) in the board
		//initially all lines are empty!
		evalBlack=new int[L];
		
		//initially all lines are empty so all counter for white or black are 0
		for (i=0; i<L; i++)
		{
			evalWhite[i]=0;
			evalBlack[i]=0;
		}

		evalBoard=new ArrayList [N][M];
		//for each position in the board there is a list of lines that use this position
		//note that each line appears in N positions!
		//this list contains the index of the Line in a global list of possible lines for all the board, named possibleLinesList
		possibleLinesList=new ArrayList();

		//first we traverse the board to create the ArrayList objects
		ArrayList a;
		for (i=0; i<N; i++)
		{
			for (j=0; j<M; j++)
			{
				a=new ArrayList();		
				evalBoard[i][j]=a;					
			}
		}
		
		//add lines to positions
		Line l;
		//first we traverse the board vertically
		for (i=0; i<N-L+1; i++)
		{
			for (j=0; j<M; j++)
			{
				l=new Line(i,j,L,Line.vertical);
				possibleLinesList.add(evalEmpty,l);
				for (k=i;k<i+L;k++)
				{
					evalBoard[k][j].add(evalEmpty);
				}
				evalEmpty++;
			}
		}
		//we traverse the board horizontally
		for (j=0; j<M-L+1; j++)
		{
			for (i=0; i<N; i++)
			{
				l=new Line(i,j,L,Line.horizontal);
				possibleLinesList.add(evalEmpty,l);
				for (k=j;k<j+L;k++)
				{
					evalBoard[i][k].add(evalEmpty);
				}
				evalEmpty++;
			}
		}
		//we traverse the board diagonally UpDownLeftRight
		for (i=0; i<N-L+1; i++)
		{
			for (j=0; j<M-L+1; j++)
			{
				l=new Line(i,j,L,Line.diagonalDownUpLeftRight);
				possibleLinesList.add(evalEmpty,l);
				for (k=0;k<L;k++)
				{
					evalBoard[i+k][j+k].add(evalEmpty);
				}
				evalEmpty++;
			}
		}

		//we traverse the board diagonally DownUpLeftRight
		for (j=0; j<M-L+1; j++)
		{
			for (i=N-1; i>=L-1; i--)
			{
				l=new Line(i,j,L,Line.diagonalUpDownLeftRigth);
				possibleLinesList.add(evalEmpty,l);
				for (k=0;k<L;k++)
				{
					evalBoard[i-k][j+k].add(evalEmpty);
				}
				evalEmpty++;
			}
		}
	}


	
	int moveAndEvaluate(int j, int player) throws LineNException
	{
		int row=move(j,player);
		ArrayList a=evalBoard[row][j];
		Line l;
		int lineIndex;
		Iterator it=a.iterator();
		int oldStatus;
		int newStatus;
		while (it.hasNext())
		{
			lineIndex=((Integer)it.next()).intValue();
			l=(Line)possibleLinesList.get(lineIndex);
			oldStatus=l.getStatus();
			l.move(player);
			newStatus=l.getStatus();
			//valid status transitions
			// empty to white or black (player is black or white respectively)
			// white to white (numWhite increases) (player is white)
			// black to black (numBlack increase) (player is black)
			// white to useless (player is black)
			// black to useless (player is white)
			// useless to useless (player white or black)
			
			if (oldStatus!=newStatus)
			{
				if (oldStatus==Line.empty)
				{
					evalEmpty--;
					if (player==WHITE)
					{
						evalWhite[0]++;
					}
					else //if (player==BLACK)
					{
						evalBlack[0]++;
					}
				}
				else //transition to useless
				{
					if (player==WHITE)
					{
						evalBlack[l.numBlack-1]--;
					}
					else //if (player==BLACK)
					{
						evalWhite[l.numWhite-1]--;
					}
				}
			}
			else //same status
			{
				if (oldStatus!=Line.useless)
				{
					if (player==WHITE)
					{
						evalWhite[l.numWhite-1]++;
						evalWhite[l.numWhite-2]--;
					}
					else if (player==BLACK)
					{
						evalBlack[l.numBlack-1]++;
						evalBlack[l.numBlack-2]--;			
					}
				}
			}
		}
		return row;
	}
	
	EvaluatedBoard replicate()
	{
		int i,j,k,size;
		EvaluatedBoard other=new EvaluatedBoard();	
		other.L=L;
		other.M=M;
		other.N=N;
		other.moves=moves;
		other.fBoard=new int[N][M];
		for(i=0; i<N;i++)
		{
			for (j=0;j<M; j++)
			{
				other.fBoard[i][j]=fBoard[i][j];
			}
		}
		
		other.evalEmpty=evalEmpty;
		other.evalWhite=new int[L];
		other.evalBlack=new int[L];
		for (k=0;k<L;k++)
		{
			other.evalWhite[k]=evalWhite[k];
			other.evalBlack[k]=evalBlack[k];
		}

		other.possibleLinesList=new ArrayList();
		size=possibleLinesList.size();
		for (i=0; i<size;i++)
		{
			other.possibleLinesList.add(i,((Line)possibleLinesList.get(i)).replicate());
		}		
		
		return other;
	}
	
	public boolean isDrawEvaluated()
	{
		//returns true if there exist no possible lines for any player
		int i;
		boolean draw=true;
		for (i=0; i<L && draw; i++)
		{
			draw=draw && evalWhite[i]==0 && evalBlack[i]==0;			
		}
		if (draw)
		{
			draw=draw && evalEmpty==0;
		}
		return draw;
	}
	
	public boolean WhiteWins()
	{
		return evalWhite[L-1]!=0;
	}

	public boolean BlackWins()
	{
		return evalBlack[L-1]!=0;
	}

	
	public EvaluatedMove getEvaluatedMove()
	{
		return new EvaluatedMove(L,evalWhite, evalBlack);
	}
}
