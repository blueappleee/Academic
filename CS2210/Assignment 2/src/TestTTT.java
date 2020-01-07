import java.util.*;

public class TestTTT {

    	public static final char HUMAN        = 'X';
    	public static final char COMPUTER     = 'O';

    	public static final int HUMAN_WIN    = 0;
    	public static final int DRAW         = 2;
    	public static final int UNCLEAR      = 1;
    	public static final int COMPUTER_WIN = 3;
    	private final char EMPTY = ' ';

    	public static void main(String args[]) {

		int size = 4;
		int line = 3;

		nk_TicTacToe ttt = new nk_TicTacToe(size,line,4);
		Dictionary d;  
		boolean[] test = new boolean[6];
		int i,j;

		if (args.length == 0) 
		    for (i = 0; i < 6; ++i) test[i] = true;
		else {
		    if (args[0].equals("r") || args[0].equals("0")) {
			System.out.println("Usage: java TestTTT, or java TestTTT n1 n2 n3 ... ");
			System.out.println("In the second form the value of each is from 1 - 6: only those tests will be run");
			System.exit(0);
		    }
		    for (i = 0; i < 6; ++i) test[i] = false;
		    for (i = 0; i < args.length; ++i) {
			j = Integer.parseInt(args[i]);
			if (j >= 1 && j <= 5) test[j] = true;
		    }
		}

		String[] config = new String[8];
		config[0] = " XXX OO  XX XXOO"; // 3 in row
		config[1] = "X X O XO  X O  O"; // 3 in column
		config[2] = " XOO X    X O  X"; // 3 in main diagonal
		config[3] = "O    OX  X  X   "; // 3 in main diagonal
		config[4] = "XO X  O XX O    "; // 3 in diagonal (computer ttt.wins)
		config[5] = "XX X   O  O XO O"; // 3 in diagonal (computer ttt.wins)
		config[6] = "  XXOO OOO O  XX"; // undecided
		config[7] = "XOXOOXOXOXOXXOXO"; // draw

		int[] score = {0,0,0,0,3,3,1,2};

		int r, c;
		boolean pass = true;

		System.out.println(" Testing class nk_TicTacToe");
		System.out.println(" -----------------------");
		System.out.println("");

	/*	if (test[1]) {
		for (r = 0; r < size; ++r)
			for (c = 0; c < size; ++c)
				if (!ttt.squareIsEmpty(r,c)) pass = false;

		ttt.storePlay(2,2,'X');
		ttt.storePlay(3,3,'O');
		if (ttt.squareIsEmpty(2,2)) pass = false;
		if (ttt.squareIsEmpty(3,3)) pass = false;

		d = ttt.createDictionary();
		ttt.insertConfig(d,2);
		ttt.storePlay(1,1,'X');
		if (ttt.repeatedConfig(d) != -1) pass = false;
		ttt.insertConfig(d,2);
		if (ttt.repeatedConfig(d) != 2) pass = false;
		ttt.storePlay(0,0,'O');
		if (ttt.repeatedConfig(d) != -1) pass = false;

		if (pass) 
		     System.out.println("   Test 1 passed");
		else System.out.println("***Test 1 failed");

		System.out.println("          Test 1 checks methods squareIsEmpty and repeatedConfig");
		}*/

		pass = true;
		if (test[2]) {
		for (i = 0; i < 2; ++i) {
			j = 0;
			ttt = new nk_TicTacToe (size,line,4);
			for (r = 0; r < size; ++r)
				for (c = 0; c < size; ++c) {
					if (config[i].charAt(j) != ' ') 
						ttt.storePlay(r,c,config[i].charAt(j));
					++j;
				}
			if (!ttt.wins('X')) pass = false;
			if (ttt.wins('O')) pass = false;
			if (ttt.evalBoard() != HUMAN_WIN) pass = false;
		}

		if (pass) 
		     System.out.println("   Test 2 passed");
		else System.out.println("***Test 2 failed");

		System.out.println("          Test 2 checks methods wins and evalBoard: 3 \"X\"s in same row or column");
		}

		if (test[3]) {
		pass = true;
		for (i = 2; i < 4; ++i) {
			j = 0;
			ttt = new nk_TicTacToe (size,line,4);
			for (r = 0; r < size; ++r)
				for (c = 0; c < size; ++c) {
					if (config[i].charAt(j) != ' ') 
						ttt.storePlay(r,c,config[i].charAt(j));
					++j;
				}
			if (!ttt.wins('X')) pass = false;
			if (ttt.wins('O')) pass = false;
			if (ttt.evalBoard() != HUMAN_WIN) pass = false;
		}

		if (pass) 
		     System.out.println("   Test 3 passed");
		else System.out.println("***Test 3 failed");

		System.out.println("          Test 3 checks methods wins and evalBoard: 3 \"X\"s in same diagonal");
		}
	
		if (test[4]) {
		pass = true;
		for (i = 4; i < 6; ++i) {
			j = 0;
			ttt = new nk_TicTacToe (size,line,4);
			for (r = 0; r < size; ++r)
				for (c = 0; c < size; ++c) {
					if (config[i].charAt(j) != ' ') 
						ttt.storePlay(r,c,config[i].charAt(j));
					++j;
				}
			if (!ttt.wins('O')) pass = false;
			if (ttt.wins('X')) pass = false;
			if (ttt.evalBoard() != COMPUTER_WIN) pass = false;
		}

		if (pass) 
		     System.out.println("   Test 4 passed");
		else System.out.println("***Test 4 failed");

		System.out.println("          Test 4 checks methods wins and evalBoard: 3 \"O\"s in same diagonal");
		}

		if (test[5]) {
		pass = true;
		j = 0;
		ttt = new nk_TicTacToe(size,line,4);
		for (r = 0; r < size; ++r)
			for (c = 0; c < size; ++c) {
				if (config[6].charAt(j) != ' ') 
					ttt.storePlay(r,c,config[6].charAt(j));
				++j;
			}

		if (ttt.evalBoard() != UNCLEAR) pass = false;

		j = 0;
		ttt = new nk_TicTacToe(size,line,4);
		for (r = 0; r < size; ++r)
			for (c = 0; c < size; ++c)
				if (config[7].charAt(j) != ' ') 
					ttt.storePlay(r,c,config[7].charAt(j++));
		if (ttt.evalBoard() != DRAW) pass = false;

		if (pass) 
		     System.out.println("   Test 5 passed");
		else System.out.println("***Test 5 failed");

		System.out.println("          Test 4 checks methods wins and evalBoard: undecided and draw");
		}

 
	} 
}