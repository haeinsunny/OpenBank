package mybank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date today = new Date();
//		today.getTime();  //자바가 1900년 1월 1일 부터 누적된 시간값을 가지고있음(그래서 getTime의 타입은 long타입)
		long ldate = today.getTime();
		System.out.println(ldate);
		long cdate = System.currentTimeMillis(); //유닉한 현재시간 값
		System.out.println(cdate);
		
		//Date대신에 Calendar쓰기 (메모리효율상): calendar 배열안에 현재시간과 관련된 정보가 다 담겨있음
		Calendar cal = Calendar.getInstance(); 
		System.out.println(cal);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));	
		System.out.println(cal.get(Calendar.MONTH)+1); //+1해야 현재 월
		System.out.println(cal.get(Calendar.DAY_OF_WEEK)); //일요일부터 1
		
		//날짜계산(한달전, 한달후 등)
		LocalDate ld = LocalDate.now();  
		System.out.println("100일 뒤:" + ld.plusDays(100));
		
		//시간계산
		LocalTime lt = LocalTime.now();
		
		//100분 더한 시간을 출력
		System.out.println("100분 뒤:" + lt.plusMinutes(100));
		
		//두 날짜 간격: Period
		
		//뒤 9자리 자르기
		long dd = System.currentTimeMillis();	
		String dt = Long.toString(dd).substring(4, 13);
		System.out.println(dt);
		
		//조회시작일자
		System.out.println(ld.minusMonths(1));
		
	}

}
