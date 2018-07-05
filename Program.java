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
		System.out.println("칼로리 계산프로그램입니다^^");
		System.out.println("=====================");
		System.out.println("성별을 입력해 주세요");
		System.out.println("(1.남성  2.여성)");
		System.out.println("=====================");

		String str = sc.next();

		int man = 2500;
		int woman = 2000;
		int rec_cal = 0;

		if (str.equals("여성")) {
			rec_cal = woman;
		} else {
			rec_cal = man;
		}

		System.out.println(str + "분 이시군요?");
		System.out.println("=====================");
		System.out.println("권장칼로리는 " + rec_cal + "Kcal 입니다");
		System.out.println("=====================");

		System.out.println("하루에 몇끼를 드시나요?");
		System.out.println("1.1일1식");
		System.out.println("2.1일2식");
		System.out.println("3.1일3식");
		System.out.println("=====================");

		// System.out.println(cnt_scan);
		cnt = sc.nextInt();
		System.out.println("하루에 " + cnt + "끼를 드시는군요?");
		System.out.println("한끼에 먹은 칼로리를 입력하세요");
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
			System.out.println("입력값을  초과하셨습니다");
		}

		int a = rec_cal - total;
		// System.out.println(total);

		if (a < 0) {
		
			 a = a * (-1);

			System.out.println("귀하는 " + str + " 권장 열량인 " + rec_cal + " Kcal 보다");
			System.out.println(a + "kcal 를 초과 합니다");
			System.out.println("건강을 위해 운동을 추천합니다");
			System.out.println("=====================");

		} else {

			System.out.println("귀하는" + str + " 권장 열량인 " + rec_cal + " Kcal 보다");
			System.out.println(a + "kcal 가 부족 합니다");
			System.out.println("다이어트 중이신가요?");
			System.out.println("=====================");

		}

		v.clear();
	}

	
}
