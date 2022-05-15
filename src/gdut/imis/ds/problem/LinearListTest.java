package gdut.imis.ds.problem;

import java.util.Scanner;

import gdut.imis.ds.linearlist.LinearList;
import gdut.imis.ds.linearlist.SeqList;

/**
 * @author hongxiaobin
 */
public class LinearListTest {
	public static void main(String[] args) {
		LinearList<String> linearlist = new SeqList<String>();
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<30;i++) {
			String data = (char)((int)(Math.random()*26)+65)+"";
			linearlist.insert(data);
		}
		for (int i = 0  ;  i < linearlist.size();i++){
			System.out.print(linearlist.get(i)+"\t");
		}
		System.out.println();
	}
}
