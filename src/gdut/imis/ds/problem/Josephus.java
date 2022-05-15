package gdut.imis.ds.problem;

import gdut.imis.ds.linearlist.LinearList;
import gdut.imis.ds.linearlist.SeqList;

public class Josephus {
	private LinearList<Integer> josephusRing;
	private int n;
	private int start;
	private int distance;
	public Josephus(int n,int s,int d) {
		this.n = n;
		this.start = s;
		this.distance = d;
		this.josephusRing = new SeqList<Integer>(n);
		for(int i=1;i<=n;i++) {
			josephusRing.insert(i);
		}
	}
	public void resolve() {
		//TODO
	}
	public static void main(String[] args) {
		new Josephus(7,3,5).resolve();
	}
}
