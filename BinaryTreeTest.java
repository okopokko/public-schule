package _test;
import linear.List;
import linear.ListWithViewer;
import gui.GUI;
import baeume.BinaryTree;
import baeume.TreeViewer;


public class BinaryTreeTest {
	public BinaryTree<Integer> suchbaum;
	
	public BinaryTreeTest(){
        suchbaum = new BinaryTree<Integer>(59);
        BinaryTree<Integer> b4= new BinaryTree<Integer>(4);
        BinaryTree<Integer> b34 = new BinaryTree<Integer>(34);
        BinaryTree<Integer> b16 = new BinaryTree<Integer>(16);
        BinaryTree<Integer> b7= new BinaryTree<Integer>(7);
        BinaryTree<Integer> b6 = new BinaryTree<Integer>(6);
        BinaryTree<Integer> b12 = new BinaryTree<Integer>(12);
        BinaryTree<Integer> b18= new BinaryTree<Integer>(18);
        BinaryTree<Integer> b17= new BinaryTree<Integer>(17);
        BinaryTree<Integer> b53 = new BinaryTree<Integer>(53);
        BinaryTree<Integer> b45 = new BinaryTree<Integer>(45);
        BinaryTree<Integer> b78 = new BinaryTree<Integer>(78);
        BinaryTree<Integer> b62 = new BinaryTree<Integer>(62);
        BinaryTree<Integer> b61= new BinaryTree<Integer>(61);
        BinaryTree<Integer> b71 = new BinaryTree<Integer>(71);
        BinaryTree<Integer> b73 = new BinaryTree<Integer>(73);
        
        BinaryTree<Integer> b2 = new BinaryTree<Integer>(2);
        BinaryTree<Integer> b65 = new BinaryTree<Integer>(65);
        BinaryTree<Integer> b83 = new BinaryTree<Integer>(83);
        
        suchbaum.setLeftTree(b4);
        b4.setLeftTree(b2);
        b4.setRightTree(b34);
        b7.setLeftTree(b6);
        b7.setRightTree(b12);
        b16.setRightTree(b18);
        b18.setLeftTree(b17);
        b16.setLeftTree(b7);
        b34.setLeftTree(b16);
        b34.setRightTree(b53);
        b53.setLeftTree(b45);
        suchbaum.setRightTree(b78);
        b78.setRightTree(b83);
        b78.setLeftTree(b62);
        b62.setLeftTree(b61);
        b62.setRightTree(b71);
        b71.setLeftTree(b65);
        b71.setRightTree(b73);
        TreeViewer.showTree(suchbaum, 600, 400);
	}
	
	// Rahmenmethode
	public int summe(){
		return summe(suchbaum);
	}
	
	public int knotenanzahl() {
		return gibKnotenzahl(suchbaum);
	}
	
	public int gibTiefe() {
		return gibTiefe(suchbaum);
	}
	
	public List<Integer> postorder(){
		return postorder(suchbaum);
	}
	
	public List<Integer> preorder(){
		return preorder(suchbaum);
	}
	
	public List<Integer> inorder(){
		return inorder(suchbaum);
	}
	
	public boolean istEnthalten() {
		return istEnthalten(suchbaum, 73);
	}
	
	public int amWeitestenRechts() {
		return amWeitestenRechts(suchbaum);
	}
	
	public int einfuegen() {
		return einfuegen(suchbaum, 60);
	}
	
	public List<Integer> gibBlaetter() {
		return gibBlaetter(suchbaum);
	}

	private List<Integer> gibBlaetter(BinaryTree<Integer> pTree) {
		ListWithViewer<Integer> ergebnis = new ListWithViewer<>();

		if (pTree.isEmpty()) {
		      return ergebnis;
		}
		if (pTree.getLeftTree().isEmpty() && pTree.getRightTree().isEmpty()){
			ergebnis.append(pTree.getContent());
			return ergebnis;
		}

		List<Integer> linkesErgebnis = gibBlaetter(pTree.getLeftTree());
		List<Integer> rechtesErgebnis = gibBlaetter(pTree.getRightTree());

		ergebnis.concat(linkesErgebnis);
		ergebnis.concat(rechtesErgebnis);

		return ergebnis;
	}

	private int einfuegen(BinaryTree<Integer> pBaum, int pZahl) {
		BinaryTree<Integer> n = new BinaryTree<Integer>(pZahl);
		int wurzel = pBaum.getContent();
		if(pZahl > wurzel) {
			if(!pBaum.getRightTree().isEmpty()) {
				int rechts = einfuegen(pBaum.getRightTree(), pZahl);
			}
			else if(pBaum.getRightTree().isEmpty()) {
				pBaum.setRightTree(n);
			}
		}
		else if(pZahl < wurzel) {
			if(!pBaum.getLeftTree().isEmpty()) {
				int links = einfuegen(pBaum.getLeftTree(), pZahl);
			}
			else if(pBaum.getRightTree().isEmpty()) {
				pBaum.setLeftTree(n);
			}
		}
		
		return 0;
	}

	private boolean istEnthalten(BinaryTree<Integer> pBaum, int gesuchteZahl) {
		boolean ergebnis = false;
		List<Integer> zahlen = preorder(pBaum);
		zahlen.toFirst();
		while(!zahlen.isEmpty()) {
			if(zahlen.getContent() == gesuchteZahl) {
				return true;
			}else {
				zahlen.next();
			}
		}
		return ergebnis;
	}
	
	private int amWeitestenRechts(BinaryTree<Integer> pBaum) {
		List<Integer> zahlen = inorder(pBaum);
		zahlen.toLast();
		return zahlen.getContent();
	}

	private int summe(BinaryTree<Integer> pTree) {
		int ergebnis = 0;
		// TODO programmieren:
		// Abbruchbedingung, Wurzelbehandlung, 2 rekursive Aufrufe, Sachlogik
		if(pTree.isEmpty()) {
			return 0;
		}
		int currentWurzel = pTree.getContent().intValue();
		ergebnis += currentWurzel;
		ergebnis += summe(pTree.getLeftTree());
		ergebnis += summe(pTree.getRightTree());
		return ergebnis;
	}
	
	public int gibKnotenzahl(BinaryTree<Integer> pTree) {
		int ergebnis = 0;
		if(pTree.isEmpty()) return 0;
		
		int l = gibKnotenzahl(pTree.getLeftTree());
		int r = gibKnotenzahl(pTree.getRightTree());
		ergebnis = l + r + 1;
		return ergebnis;
	}
	
	private int gibTiefe(BinaryTree<Integer> n) {
		int ergebnis = 0;
		
		if(n.isEmpty()) return -1;
		
		int l = gibTiefe(n.getLeftTree());
		int r = gibTiefe(n.getRightTree());
		if(l > r) {
			ergebnis = l + 1;
		}
		else {
			ergebnis = r + 1;
		}
		return ergebnis;
	}

	//Links Rechts Wurzel; Postorder
	private List<Integer> postorder(BinaryTree<Integer> pBaum) {
		ListWithViewer<Integer> ergebnisListe = new ListWithViewer<>();
		//Abbruchbedingung
		if(pBaum.isEmpty()) return ergebnisListe;
		
		//rekursiver Selbstaufruf
		List<Integer> linkeListe = postorder(pBaum.getLeftTree());
		List<Integer> rechteListe = postorder(pBaum.getRightTree());
		int wurzel = pBaum.getContent().intValue();
		
		//merging Lists
		ergebnisListe.concat(linkeListe);
		ergebnisListe.concat(rechteListe);
		ergebnisListe.append(wurzel);
		
		return ergebnisListe;
	}
	
	//Wurzel Links Rechts; Preorder
	private List<Integer> preorder(BinaryTree<Integer> pBaum) {
		ListWithViewer<Integer> ergebnisListe = new ListWithViewer<>();
		if(pBaum.isEmpty()) return ergebnisListe;
		
		List<Integer> linkeListe = preorder(pBaum.getLeftTree());
		List<Integer> rechteListe = preorder(pBaum.getRightTree());
		int wurzel = pBaum.getContent().intValue();
		
		ergebnisListe.append(wurzel);
		ergebnisListe.concat(linkeListe);
		ergebnisListe.concat(rechteListe);
		
		return ergebnisListe;
	}
	
	//Links Wurzel Rechts; Inorder
	private List<Integer> inorder(BinaryTree<Integer> pBaum) {
		ListWithViewer<Integer> ergebnisListe = new ListWithViewer<>();
		if(pBaum.isEmpty()) return ergebnisListe;
		
		List<Integer> linkeListe = inorder(pBaum.getLeftTree());
		List<Integer> rechteListe = inorder(pBaum.getRightTree());
		int wurzel = pBaum.getContent().intValue();
		
		ergebnisListe.concat(linkeListe);
		ergebnisListe.append(wurzel);
		ergebnisListe.concat(rechteListe);
		
		return ergebnisListe;
	}
	
	
	public static void main(String[] args) {
		new GUI(new BinaryTreeTest());
	}
}
