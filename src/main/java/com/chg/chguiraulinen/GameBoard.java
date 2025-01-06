/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

/**
 *
 * @author carlosherrero
 */
public class GameBoard {

	public GameBoard(int n, int m)
	{
		N=n;
		M=m;
		fBoard=new int [N] [M];
		init();
	}
	
	public GameBoard()
	{
	}
		
	
	public int N;
	public int M;
	public int [] [] fBoard;
	
	static final int EMPTY =0;
	static final int WHITE =1;
	static final int BLACK=2;
	
	public int moves=0; //number of moves done
	
	public void init()
	{
		for (int i=0; i<N; i++)
			for (int j=0; j<M; j++)
				fBoard[i][j]=EMPTY;
	}
	
	//to check users movements
	protected boolean isInBounds( int col)
	{
		return ((col >=0) && (col <M));
	}
	
	//rules of LineN
	protected boolean isValidMove( int col)
	{
		return (fBoard[N-1][col]==EMPTY);
	}
	
	protected int getRow( int col)
	{
		int row=N-2;
		while ((row>=0) && (fBoard[row][col]==EMPTY)) 
		{
			row--;
		}
		row++;
		return row;
	}
	
	protected void move(int row, int col, int PLAYER) throws LineNException
	{
		if (PLAYER!=WHITE && PLAYER!=BLACK) throw new LineNException("GameBoard::move - invalid player");
		fBoard[row][col]=PLAYER;
	}
	
	protected int move(int col, int PLAYER) throws LineNException
	{
		if (!isInBounds(col)) throw new LineNException("GameBoard::move - col is out of bounds");
		if (!isValidMove(col)) throw new LineNException("GameBoard::move - invalid move");
		int r=getRow(col);
		move(r,col, PLAYER);
		moves++;
		return r;
	}
	
	public boolean isCompleted()
	{
		//if the board is full
		return (moves==N*M);
	}
	
	public static int oppositeColor(int color) throws LineNException
	{
		if (color==WHITE) return BLACK;
		else if (color==BLACK) return WHITE;
		else throw new LineNException("GameBoard::oppositeColor - invalid color");
	}
}
