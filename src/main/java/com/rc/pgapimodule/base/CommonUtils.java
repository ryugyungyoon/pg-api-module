package com.rc.pgapimodule.base;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {

	/* ==============================================================================
	 * return true or false
	 * =========================================================================== */
	/**
	 * 객체의 null 체크
	 *
	 * @author jklee
	 * @param obj			비교할 객체
	 * @return boolean		null 여부
	 */
	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().isEmpty() || Objects.equals(obj.toString(), "null");
	}

	/**
	 * 객체의 null인 경우 newValue 리턴
	 *
	 * @author cooky
	 * @param obj			비교할 객체
	 * @return Object		new value
	 */
	public static Object nvl(Object obj, Object newObj) {
		return CommonUtils.isEmpty(obj) ? newObj : obj;
	}

	/**
	 * 객체의 값이 숫자인지 체크
	 *
	 * @author ryu
	 * @param str			내용
	 * @return boolean		숫자 여부
	 */
	public static boolean isNumber(String str) {
		if (str != null && !str.isEmpty()) {
			String regexp = "^[0-9]+$";
			return str.matches(regexp);
		}

		return false;
	}

	/**
	 * 객체의 값이 실수 형태인지 체크
	 *
	 * @author ryu
	 * @param str			내용
	 * @return boolean		실수 여부
	 */
	public static boolean isFloatNumber(String str) {
		if (str != null && !str.isEmpty()) {
			String regexp = "^[0-9.]+$";
			return str.matches(regexp);
		}

		return false;
	}

	/* ==============================================================================
	 * return value
	 * =========================================================================== */
	/**
	 * length 만큼 난수 발생
	 *
	 * @param length 			길이
	 * @return String			난수
	 */
	public static String getMakeRandomNum(int length) {
		StringBuilder rtnVal = new StringBuilder();
		for (int i = 0; i < length; i++) {
			Random rand = new Random();
			String ran = Integer.toString(rand.nextInt(10));
			rtnVal.append(ran);
		}

		return rtnVal.toString();
	}

	/**
	 * length 만큼 Random 문자 발생
	 *
	 * @param length 			길이
	 * @param ignoreCase		대소문자 무시여부(true:대문자+숫자, false:대소문자+숫자)
	 * @return String			Random 문자
	 */
	public static String getMakeRandomString(Integer length, Boolean ignoreCase) {
		char[] charaters = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		for( int i = 0 ; i < length ; i++ ){
			sb.append(charaters[rnd.nextInt(charaters.length)]);
		}

		return ignoreCase ? sb.toString().toUpperCase() : sb.toString();
	}

	/**
	 * 유일한 식별자 생성 : UUID(=GUID)
	 *
	 * @author cooky
	 * @return String
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Transactino ID 생성 : UUID(=GUID) 활용
	 *
	 * @author cooky
	 * @return String
	 */
	public static String getTrnID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * type에 따라 시간 더하거나 빼서 Date 타입으로 반환
	 *
	 * @author jklee
	 * @param type, addNum
	 * @return Date
	 */
	public static Date getAddDate(String type, int addNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		switch (type) {
			case "year" -> cal.add(Calendar.YEAR, addNum);
			case "month" -> cal.add(Calendar.MONTH, addNum);
			case "day" -> cal.add(Calendar.DATE, addNum);
			case "hour" -> cal.add(Calendar.HOUR, addNum);
			case "minute" -> cal.add(Calendar.MINUTE, addNum);
		}

		return cal.getTime();
	}

	/**
	 * type에 따른 시간 더하거나 빼서 String 타입으로 반환
	 *
	 * @author cooky
	 * @param type				단위
	 * @param addNum			더할 값
	 * @return String			일자
	 */
	public static String getAddDateStr(String type, String addType, int addNum) {
		Date date = getAddDate(addType, addNum);

		return getDateStr(type, date);
	}

	/**
	 * 오늘의 날짜와 관련된 문자열
	 *
	 * @author jklee
	 * @param type				단위
	 * @return String
	 */
	public static String getDateStr(String type) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = null;

		switch (type) {
			case "year" -> sdf = new SimpleDateFormat("yyyy");
			case "month" -> sdf = new SimpleDateFormat("yyyyMM");
			case "day" -> sdf = new SimpleDateFormat("yyyyMMdd");
			case "hour" -> sdf = new SimpleDateFormat("yyyyMMddHH");
			case "minute" -> sdf = new SimpleDateFormat("yyyyMMddHHmm");
			case "second" -> sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		}

		return sdf.format(cal.getTime());
	}

	/**
	 * 오늘의 날짜와 관련된 문자열
	 *
	 * @author jklee
	 * @param type				단위
	 * @param date 				일시
	 * @return String
	 */
	public static String getDateStr(String type, Date date) {
		SimpleDateFormat sdf = null;

		switch (type) {
			case "year" -> sdf = new SimpleDateFormat("yyyy");
			case "month" -> sdf = new SimpleDateFormat("yyyyMM");
			case "day" -> sdf = new SimpleDateFormat("yyyyMMdd");
			case "hour" -> sdf = new SimpleDateFormat("yyyyMMddHH");
			case "minute" -> sdf = new SimpleDateFormat("yyyyMMddHHmm");
			case "second" -> sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		}

		return sdf.format(date);
	}

	/**
	 * 날짜와 관련된 문자열
	 *
	 * @author ryuky
	 * @param type				단위
	 * @return String
	 */
	public static String getDateStr(String type, int addDate) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, addDate);
		SimpleDateFormat sdf = null;

		switch (type) {
			case "year" -> sdf = new SimpleDateFormat("yyyy");
			case "month" -> sdf = new SimpleDateFormat("yyyyMM");
			case "day" -> sdf = new SimpleDateFormat("yyyyMMdd");
			case "hour" -> sdf = new SimpleDateFormat("yyyyMMddHH");
			case "minute" -> sdf = new SimpleDateFormat("yyyyMMddHHmm");
			case "second" -> sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		}

		return sdf.format(cal.getTime());
	}

	/**
	 * 시간 관련 단위들 반환
	 *
	 * @author jklee
	 * @param type				단위
	 * @return String			일시 문자열
	 */
	public static String getDate(String type) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = null;

		switch (type) {
			case "yyyy" -> sdf = new SimpleDateFormat("yyyy");
			case "MM" -> sdf = new SimpleDateFormat("MM");
			case "dd" -> sdf = new SimpleDateFormat("dd");
			case "HH" -> sdf = new SimpleDateFormat("HH");
			case "mm" -> sdf = new SimpleDateFormat("mm");
			case "ss" -> sdf = new SimpleDateFormat("ss");
			case "HHmm" -> sdf = new SimpleDateFormat("HHmm");
		}

		return sdf.format(cal.getTime());
	}

	/**
	 * 해당월에 첫일자 와 끝일자
	 *
	 * @author jklee
	 * @return String[]
	 */
	@SuppressWarnings("deprecation")
	public static String[] getStartAndEndDateStr() {
		Calendar cal = Calendar.getInstance();
		Date startDate = new Date();
		Date endDate = new Date();
		startDate.setDate(1);
		endDate.setDate(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date1 = sdf.format(startDate);
		String date2 = sdf.format(endDate);
		return new String[] { date1, date2 };
	}

	/**
	 * 로컬 맥 주소를 가져오는 메소드
	 *
	 * @author ryu
	 * @return String
	 */
	public static String getLocalMacAddress() {
		String result = "";
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}

			result = sb.toString();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 초 -> 시간문자열로 변환
	 *
	 * @author cooky
	 * @param seconds				seconds
	 * @return String
	 */
	public static String convertSecToTime(Long seconds) {
		String hour = String.format("%02d", seconds / (60 * 60));
		String min = String.format("%02d", (seconds % (60 * 60) / 60));
		String sec = String.format("%02d", (seconds % 60));

		return hour + ":" + min + ":" + sec;
	}
}