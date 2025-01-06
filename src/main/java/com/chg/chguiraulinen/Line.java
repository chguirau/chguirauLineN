/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chguiraulinen;

/**
 *
 * @author carlosherrero
 */
public class Line {
	
	int row;
	int col;
	int L;
	int orientation;
	//the line starts in row,col and is N spaces long in orientation
	
	int numWhite;
	int numBlack;
	int numEmpty;
	
	static final int vertical=0;
	static final int horizontal=1;
	static final int diagonalUpDownLeftRigth=2;
	static final int diagonalDownUpLeftRight=3;
	
	static final int useless=0;
	static final int empty=1;
	static final int white=2;
	static final int black=3;
	
	Line(int i, int j, int n, int anOrientation)
	{
		row=i;
		col=j;
		L=n;
		orientation=anOrientation;
		numWhite=0;
		numBlack=0;		
		numEmpty=L;
	}
	
	void move( int player) throws LineNException
	{
		//we know it is a valid move inside this Line
		//we don't care which move is done, we don't care about positions, just counters
		if (player==GameBoard.WHITE)
		{
			numWhite++;
			numEmpty--;	
		}
		else //if player==GameBoard.BLACK
		{
			numBlack++;
			numEmpty--;
		}
		if (numEmpty<0) throw new LineNException("Line::move - numEmpty: inconsistent value");
		if (numWhite>L) throw new LineNException("Line::move - numWhite: inconsistent value");
		if (numBlack>L) throw new LineNException("Line::move - numBlack: inconsistent value");
		if (numWhite+numBlack>L) throw new LineNException("Line::move - numWhite and numBlack: inconsistent value");
	}
	
	int getStatus()
	{
		if (numWhite > 0 && numBlack > 0) return useless;
		//no white player neither blck player can line N

		if (numWhite > 0) return white;
		//only white pieces

		if (numBlack > 0) return black;
		//only black pieces

		//if (numEmpty==N)
		return empty;
		//no white and no black pieces, all empty
	}

	boolean isUseless()
	{
		return (getStatus()==useless);	
	}
	
	boolean isWhite()
	{
		return (getStatus()==white);
	}
	
	boolean isBlack()
	{
		return (getStatus()==black);
	}
	
	boolean isEmpty()
	{
		return (getStatus()==empty);
	}
	
	Line replicate()
	{
		Line other=new Line(row,col,L,orientation);
		other.numWhite=numWhite;
		other.numBlack=numBlack;
		other.numEmpty=numEmpty;
		return other;
	}
}