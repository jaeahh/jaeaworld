package calrorie_calcualtor;

import java.util.Scanner;
import java.util.Vector;

public class Program {

	public static int cnt;
	public static int total;

	static Vector<Integer> v = new Vector<Integer>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("=====================");
		System.out.println("Į�θ� ������α׷��Դϴ�^^");
		System.out.println("=====================");
		System.out.println("������ �Է��� �ּ���");
		System.out.println("(1.����  2.����)");
		System.out.println("=====================");

		String str = sc.next();

		int man = 2500;
		int woman = 2000;
		int rec_cal = 0;

		if (str.equals("����")) {
			rec_cal = woman;
		} else {
			rec_cal = man;
		}

		System.out.println(str + "�� �̽ñ���?");
		System.out.println("=====================");
		System.out.println("����Į�θ��� " + rec_cal + "Kcal �Դϴ�");
		System.out.println("=====================");

		System.out.println("�Ϸ翡 ��� ��ó���?");
		System.out.println("1.1��1��");
		System.out.println("2.1��2��");
		System.out.println("3.1��3��");
		System.out.println("=====================");

		// System.out.println(cnt_scan);
		cnt = sc.nextInt();
		System.out.println("�Ϸ翡 " + cnt + "���� ��ô±���?");
		System.out.println("�ѳ��� ���� Į�θ��� �Է��ϼ���");
		System.out.println("=====================");

		for (int i = 0; i < cnt; i++) {
			int cc = sc.nextInt();
			v.add(cc);
		}
		// System.out.println(v);
		// System.out.println(v.size());
		// System.out.println(v.get(0));
		Meal_cnt meal = new Meal_cnt();
		if (cnt == 1) {
			total = meal.plus(v.get(0));

		} else if (cnt == 2) {
			total = meal.plus(v.get(0), v.get(1));

		} else if (cnt == 3) {
			total = meal.plus(v.get(0), v.get(1), v.get(2));
		} else {
			System.out.println("�Է°���  �ʰ��ϼ̽��ϴ�");
		}

		int a = rec_cal - total;
		// System.out.println(total);

		if (a < 0) {
		
			 a = a * (-1);

			System.out.println("���ϴ� " + str + " ���� ������ " + rec_cal + " Kcal ����");
			System.out.println(a + "kcal �� �ʰ� �մϴ�");
			System.out.println("�ǰ��� ���� ��� ��õ�մϴ�");
			System.out.println("=====================");

		} else {

			System.out.println("���ϴ�" + str + " ���� ������ " + rec_cal + " Kcal ����");
			System.out.println(a + "kcal �� ���� �մϴ�");
			System.out.println("���̾�Ʈ ���̽Ű���?");
			System.out.println("=====================");

		}

		v.clear();
	}

	
}
