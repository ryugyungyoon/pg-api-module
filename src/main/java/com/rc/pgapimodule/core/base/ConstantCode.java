package com.rc.pgapimodule.core.base;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 상수 코드 Book
 *
 * @author cooky
 */
@RequiredArgsConstructor
public enum ConstantCode {
	/****************************** 전문 ******************************/
	INS_PRODUCT_TYPE_CD_ONOFF("ONOFF"),								// ONOFF : 시간제
	INS_PRODUCT_TYPE_CD_ONEDAY("ONEDAY"),								// ONEDAY : 정액제

	// 보험사 전문 구분
	TRANS_INS_BIZCODE_DELIVERY_START("001"),							// 001 : 운행시작
	TRANS_INS_BIZCODE_DELIVERY_END("002"),								// 002 : 운행종료
	TRANS_INS_BIZCODE_PRIVACY("003"),									// 003 : 개인정보동의
	TRANS_INS_BIZCODE_UNDERWRITING_REQUEST("004"),						// 004 : 심사요청
	TRANS_INS_BIZCODE_UNDERWRITING_RESULT("005"),						// 005 : 심사결과
	TRANS_INS_BIZCODE_ACCIDENT_RECEIPT("006"),							// 006 : 사고접수 통보

	// 전문 요청 결과
	TRANS_RESULT_CD_PLF_LA_SUCC("200"),								// 200 : 성공 (로지올 연동 결과)
	TRANS_RESULT_CD_PLF_LA_FAIL_RESERVE("903"),						// 903 : 기사적립금 부족
	TRANS_RESULT_CD_PLF_LA_FAIL_NOT_EXIST("904"),						// 904 : 해당기사없음

	// [보험 가입 심사 관련]
	// 관련 코드 정의
	TRANS_UNDERWRITING_NEW("NEW"),										// 심사신청타입 : 신규
	TRANS_UNDERWRITING_RETRY("RETRY"),									// 심사신청타입 : 재심사
	TRANS_UNDERWRITING_CHANGE_AUTO_NO("AUTO"),							// 심사신청타입 : 차량번호 변경
	TRANS_UNDERWRITING_OPTIONAL("OPT"),								// 심사신청타입 : 임의추가
	TRANS_UNDERWRITING_RENEWAL("RENEWAL"),								// 심사신청타입 : 갱신
	TRANS_UNDERWRITING_EXPIRE_CHANGE_AUTO_NO("AUTO"),					// 해지신청타입 : 차량번호 변경
	TRANS_UNDERWRITING_EXPIRE_OPTIONAL("OPT"),							// 해지신청타입 : 임의추가
	TRANS_UNDERWRITING_EXPIRE("EXPIRE"),								// 해지신청타입 : 해지
	TRANS_UNDERWRITING_MOBILE_METHOD_CD("03"),							// 가입설계동의방법 03 : 모바일
	TRANS_UNDERWRITING_AUTOMOBILE_OWNER_SAME_EQUAL("2"),				// 차량소유주 일치여부 2 : 이륜차
	TRANS_UNDERWRITING_DRIVER_OWNER_RELATION_ME("0"),					// 차량소유주와의 관계 0 : 본인

	// 심사 신청 결과 코드
	TRANS_UNDERWRITING_RESULT_CD_SUCC("01"),							// 결과코드 01 : 성공
	TRANS_UNDERWRITING_RESULT_CD_REJECT("02"),							// 결과코드 02 : 심사 거절
	TRANS_UNDERWRITING_RESULT_CD_CONFIRM_FAIL("C2"),					// 결과코드 C2 : 계약체결확정 거절
	TRANS_UNDERWRITING_RESULT_CD_EXPIRE_FAIL("E2"),					// 결과코드 E2 : 해지 거절
	TRANS_UNDERWRITING_RESULT_CD_IMPROVE("03"),						// 결과코드 03 : 서류보완
	TRANS_UNDERWRITING_RESULT_CD_ERROR("04"),							// 결과코드 04 : 오류
	TRANS_UNDERWRITING_RESULT_CD_PTL_NEW("01"),						// 심사구분 01 : 신규 가입
	TRANS_UNDERWRITING_RESULT_CD_PTL_AUTONO_CHANGE("02"),				// 심사구분 02 : 차량 변경
	TRANS_UNDERWRITING_RESULT_CD_PTL_RENEWAL("03"),					// 심사구분 03 : 갱신-심사
	TRANS_UNDERWRITING_ERROR_TYPE_CD_NOT_EXIST_DRVR_RESD("01"),		// 에러유형 코드 01 : 차량소유주 주민번호 없음
	TRANS_UNDERWRITING_ERROR_TYPE_CD_NOT_EXIST_AUTONO("02"),			// 에러유형 코드 02 : 차량번호 없음
	TRANS_UNDERWRITING_ERROR_TYPE_CD_NOT_EXIST_POLICY("03"),			// 에러유형 코드 03 : 증권번호 없음
	TRANS_UNDERWRITING_ERROR_TYPE_CD_DRVR_RESD_VALID("04"),			// 에러유형 코드 04 : 차량소유주 주민번호 부정합


	// [개인정보 제공동의 관련]
	// 결과 코드
	TRANS_PRIVACY_RESULT_CD_AGREE("1"),								// 결과코드 1 : 동의
	TRANS_PRIVACY_RESULT_CD_DISAGREE("0"),								// 결과코드 0 : 미동의
	/* ***************************** 전문 ***************************** */

	/****************************** 배치 ******************************/
	// KB 전문 코드
	BATCH_NESO01("NESO01"),
	BATCH_NESO02("NESO02"),
	BATCH_NESO03("NESO03"),
	BATCH_NESO04("NESO04"),
	BATCH_NESO05("NESO05"),
	BATCH_NESO06("NESO06"),
	BATCH_NESO07("NESO07"),
	BATCH_NESO08("NESO08"),

	// 심사 결과
	BATCH_UNDERWRITING_SEND("MT02"),									// 00001 : 보험심사 신청(일배치)
	BATCH_UNDERWRITING_RECEIVE("MT03"),								// 00002 : 보험심사 갱신 결과(일배치)
	BATCH_UNDERWRITING_AUTO_CHANGE_SEND("MT04"),						// 00004 : 차량변경 갱신 결과(일배치)
	BATCH_DELIVERY_SEND_HDINS("MT01"),									// MT01 : 배송운행(일배치) for 현대해상
	BATCH_DELIVERY_SEND_SSINS("MT01_SS"),								// MT01_SS : 배송운행(일배치) for 삼성화재
	BATCH_EXTRA_FILE("EXTRA"),											// 추가 배송운행
	BATCH_SEND_FILE_EXT(""),											// 전문파일 발송 확장자
	BATCH_RECEIVE_FILE_EXT(""),										// 전문파일 수신 확장자

	// 배치파일 사용 Flag
	BATCH_VALIDATION_DATA_SOURCE_API("A"),								// 배치파일 운행정보검증 API
	BATCH_VALIDATION_DATA_SOURCE_FILE("F"),							// 배치파일 운행정보검증 FILE
	BATCH_SEND_DATA_SOURCE_VALID("V"),									// 배치파일 운행정보검증 검증FILE
	BATCH_SEND_DATA_SOURCE_CONSOLE("C"),								// 배치파일 운행정보검증 운영자
	BATCH_RENEWAL_DATA_SOURCE_FILE("F"),								// Data 출처(F:갱신파일)
	BATCH_RENEWAL_DATA_SOURCE_NEW("N"),								// Data 출처(N:신규심사신청)
	BATCH_RENEWAL_DATA_SOURCE_CHANGE("C"),								// Data 출처(C:차량변경)
	BATCH_RENEWAL_DATA_SOURCE_RENEW("R"),								// Data 출처(R:재가입)
	/* ****************************** 배치 ****************************** */

	/****************************** 운행정보 ******************************/
	DELIVERY_PLATFORM_START_TYPE("S"),									// 운행시작 Type
	DELIVERY_PLATFORM_END_TYPE("E"),									// 운행종료 Type
	DELIVERY_PLATFORM_EQUALS_TYPE("Q"),								// 운행일치 Type
	DELIVERY_PLATFORM_CUSTOM_TYPE("C"),								// 운행번호 임의발급 Type
	DELIVERY_PLATFORM_UNWR_REJECT("R"),								// 심사결과 거절 운행건 Type
	DELIVERY_PLATFORM_EQUALS_AUTO_NO("A"),								// 심사승인된 차량번호와 운행차량 번호 비교 Type
	DELIVERY_PLATFORM_DRVG_DTM_GRATER_THAN_TYPE("G"),					// 운행번호 시작일시 종료일시 크기 비교 Type
	DELIVERY_INSURANCE_DATA_SOURCE_API("A"),							// 운행데이터 출처(API)
	DELIVERY_INSURANCE_DATA_SOURCE_UNVALIDATION("U"),					// 운행데이터 출처(UNVALIDATION)
	DELIVERY_INSURANCE_ID_REALTIME_TYPE("R"),							// 운행번호 발급 실시간 Type
	DELIVERY_INSURANCE_ID_REALTIME_PREFIX("LA"),						// 운행번호 발급 실시간 Prefix
	DELIVERY_INSURANCE_ID_CUSTOM_TYPE("C"),							// 운행번호 발급 임의 Type
	DELIVERY_INSURANCE_ID_CUSTOM_PREFIX("LAC"),						// 운행번호 발급 임의 Prefix
	DELIVERY_VALIDATION_PLATFORM_RESULT_SUCCESS("00"),					// 플랫폼 운행정보 검증 결과(00:일치)
	DELIVERY_VALIDATION_PLATFORM_RESULT_NOT_EXIST_DRVG_INS_ID("01"),	// 플랫폼 운행정보 검증 결과(01:API 운행번호 불일치=null)
	DELIVERY_VALIDATION_PLATFORM_RESULT_NOT_EQUAL_DRVG_ST_DTM("02"),	// 플랫폼 운행정보 검증 결과(02:API 운행시작일시 불일치)
	DELIVERY_VALIDATION_PLATFORM_RESULT_NOT_EQUAL_DRVG_ED_DTM("03"),	// 플랫폼 운행정보 검증 결과(03:API 운행종료일시 불일치)
	DELIVERY_VALIDATION_PLATFORM_RESULT_DRVG_ST_DTM_GRATER_THAN("04"),	// 플랫폼 운행정보 검증 결과(04:시작일시가 종료일시보다 큰경우)
	DELIVERY_VALIDATION_PLATFORM_RESULT_NOT_EQUALS_AUTO_NO("08"),		// 플랫폼 운행정보 검증 결과(08:심사승인 차량번호 불일치)
	DELIVERY_VALIDATION_PLATFORM_RESULT_UNWR_REJECT("09"),				// 플랫폼 운행정보 검증 결과(09:심사결과 거절 운행건)
	/* *************************** 운행정보 ************************** */

	/****************************** 기타 ******************************/
	INSURANCE_CD_HD("hd"),												//보험사 코드 - 현대해상
	INSURANCE_CD_SS("ss"),												//보험사 코드 - 삼성화재
	INSURANCE_CD_DB("db"),												//보험사 코드 - DB손해보험
	INSURANCE_CD_KB("kb"),												//보험사 코드 - KB손해보험
	PLATFORM_CD_LOGIALL("LA"),											//플랫폼업체 코드 - 로지올
	PLATFORM_CD_INSUNG("IS"),											//플랫폼업체 코드 - 인성정보
	PRODUCT_CD_LEGACY("LEGACY"),										//상품 코드 - 내연자동차
	PRODUCT_CD_ELECTRIC("ELEC"),										//상품 코드 - 전기자동차
	FUEL_TYP_CD_GASOLINE("01"),										//연료형태 코드 - 휘발유
	FUEL_TYP_CD_DISEL("02"),											//연료형태 코드 - 경유
	FUEL_TYP_CD_LPG("03"),												//연료형태 코드 - LPG
	FUEL_TYP_CD_HYBRID("04"),											//연료형태 코드 - 하이브리드
	FUEL_TYP_CD_ELECTRIC("05"),										//연료형태 코드 - 전기차
	FUEL_TYP_CD_HYDROGEN("06"),										//연료형태 코드 - 수소전기차
	FUEL_TYP_CD_PLUGINHYBRID("07"),									//연료형태 코드 - 플러그인하이브리드
	FUEL_TYP_CD_BYFUEL("08"),											//연료형태 코드 - 바이퓨얼
	FUEL_TYP_CD_ETC("09"),												//연료형태 코드 - LPG외
	CHARSET_EUCKR("EUC-KR"),											//CharacterSet : EUC-KR
	CHARSET_UTF8("UTF-8"),												//CharacterSet : UTF-8
	DEFAULT_LIST_DATA_CNT_10("10"),									//리스트 노출 수 = 10
	DEFAULT_LIST_DATA_CNT_20("20"),									//리스트 노출 수 = 20
	DEFAULT_LIST_DATA_CNT_50("50"),									//리스트 노출 수 = 50
	DEFAULT_LIST_DATA_CNT_100("100"),									//리스트 노출 수 = 100

	MESSAGE_TYPE_SMS("SMS"),											//Message 타입 - SMS
	MESSAGE_TYPE_LMS("LMS"),											//Message 타입 - LMS
	MESSAGE_TYPE_MMS("MMS"),											//Message 타입 - MMS
	MESSAGE_TYPE_ALIMTALK("ALIMTALK"),									//Message 타입 - ALIMTALK
	MESSAGE_CONTENT_TYPE_SYSTEM("SYSTEM"),								//Message Content 타입 - SYSTEM NOTICE
	MESSAGE_CONTENT_TYPE_CERTIFY("CERTIFY"),							//Message Content 타입 - 인증
	MESSAGE_CONTENT_TYPE_PRIVACY("PRIVACY"),							//Message Content 타입 - 개인정보처리 동의
	MESSAGE_CONTENT_TYPE_PRIVACY_RENEWAL("PRIVACY_RENEWAL"),			//Message Content 타입 - 개인정보처리 동의 갱신

	SHORTEN_URL_TYPE_PREFIX_PRIVACY("P"),								//ShortenURL Prefix - 개인정보처리 동의
	SHORTEN_URL_TYPE_PREFIX_PRIVACY_RENEWAL("PR"),						//ShortenURL Prefix - 개인정보처리 동의 갱신
	SHORTEN_URL_TYPE_PRIVACY("PRIVACY"),								//ShortenURL 타입 - 개인정보처리 동의
	SHORTEN_URL_TYPE_PRIVACY_RENEWAL("PRIVACY_RENEWAL"),				//ShortenURL 타입 - 개인정보처리 동의 갱신

	FILE_EXTENSION_EXCEL("xlsx"),										//파일 확장자 - 엑셀

	DB_ORDER_BY_DESCENDING("DESCENDING"),								//DB Order By 조건 : 내림차순(99->1)
	DB_ORDER_BY_ASCENDING("ASCENDING"),								//DB Order By 조건 : 오름차순(1->99)

	PAYMENTGATEWAY_CD_KG("KG");										//PG 코드 - KG
	/****************************** 기타 ******************************/

	@NonNull
	private final String codeText;

	@Override
	public String toString() {
		return codeText;
	}

}