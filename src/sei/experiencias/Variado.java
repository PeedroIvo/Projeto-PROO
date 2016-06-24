package sei.experiencias;

import java.util.Calendar;

public class Variado {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(); 
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}

}
