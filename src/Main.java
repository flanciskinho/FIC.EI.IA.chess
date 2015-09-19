import model.*;

public class Main {
	
	/* Ejecuta un problema */
	public static void exe1() {
		int [][] board = {
			   //0,1,2,3,4,5,6,7,8,9
				{0,0,0,0,0,0,0,0,0,0},//0
				{0,0,0,0,0,0,0,0,0,0},//1
				{0,0,0,0,0,0,0,0,0,0},//2
				{0,0,0,0,0,0,0,0,0,0},//3
				{0,0,0,0,0,0,0,0,0,0},//4
				{0,0,0,0,0,0,0,0,0,0},//5
				{0,0,0,0,0,0,0,0,0,0},//6
				{0,0,0,0,0,0,0,0,0,0},//7
				{0,0,0,0,0,0,0,0,0,0},//8
				{0,0,0,0,0,0,0,0,0,0},//9
				};
		
		int x = 2, y = 2; //init pos
		int xf = 6, yf = 0;//goal pos
		
		State state = new Horse(x,y, xf,yf, board);
		Node root = new Node(state);
		Strategy strategy = new Breadth();
		//Search search = new Tree(root, strategy);
		Search search = new Graph(root, strategy);
		
		System.out.println(search.search());
		
		String str = search.getGoalNode().toStringReversePath();
		System.out.println(str);
		
		System.exit(-1);
	}
	
	public static void exe2() {
		int [][] board = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}};
		
		int x = 2, y = 2;
		int xf = 0, yf = 1;
		
		exe1();
		
		Operator op, actions[] = Operator.values();
		Horse h = new Horse(x,y, xf,yf, board);
		Node n = new Node(h);
		/*
		for (int cnt = 0; cnt < actions.length; cnt++){
			op = actions[cnt];
			System.out.print(op.getKey()+" "+ op.toString());
			System.out.print(" "+h+" -> "+h.successor(op));
			
			System.out.println();
		}
		System.out.println(h.goalTest());
		
		System.out.println(n);
		
		int a = n.expand();
		System.out.println(a);
		System.out.println(n.getSuccessors());
		*/
		
		System.out.println(n.getParent());
	}
	
	public static void main(String args[]){
		new controller.Controller();
	}
}
