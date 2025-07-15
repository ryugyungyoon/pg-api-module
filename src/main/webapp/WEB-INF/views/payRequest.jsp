<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="org.apache.commons.codec.binary.Hex" %>
<%
/*
*******************************************************
* <������û �Ķ����>
* ������ Form �� ������ ������û �Ķ�����Դϴ�.
* ���������������� �⺻(�ʼ�) �Ķ���͸� ���õǾ� ������, 
* �߰� ������ �ɼ� �Ķ���ʹ� �����޴����� �����ϼ���.
*******************************************************
*/
String merchantKey 		= "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; // ����Ű
String merchantID 		= "nicepay00m"; 				// �������̵�
String goodsName 		= "���̽�����"; 					// ������ǰ��
String price 			= "1004"; 						// ������ǰ�ݾ�	
String buyerName 		= "���̽�"; 						// �����ڸ�
String buyerTel 		= "01000000000"; 				// �����ڿ���ó
String buyerEmail 		= "happy@day.co.kr"; 			// �����ڸ����ּ�
String moid 			= "mnoid1234567890"; 			// ��ǰ�ֹ���ȣ	
String returnURL 		= "http://localhost:8080/nicepay3.0_euc-kr/payResult.jsp"; // ���������(������) - ����� ����â ����

/*
*******************************************************
* <�ؽ���ȣȭ> (�������� ������)
* SHA-256 �ؽ���ȣȭ�� �ŷ� �������� �������� ����Դϴ�. 
*******************************************************
*/
DataEncrypt sha256Enc 	= new DataEncrypt();
String ediDate 			= getyyyyMMddHHmmss();
String hashString 		= sha256Enc.encrypt(ediDate + merchantID + price + merchantKey);
%>
<!DOCTYPE html>
<html>
<head>
<title>NICEPAY PAY REQUEST(EUC-KR)</title>
<meta charset="euc-kr">
<style>
	html,body {height: 100%;}
	form {overflow: hidden;}
</style>
<script src="https://pg-web.nicepay.co.kr/v3/common/js/nicepay-pgweb.js" type="text/javascript"></script>
<script type="text/javascript">
//����â ���� ��û�� ����˴ϴ�.
function nicepayStart(){
	goPay(document.payForm);
}

//[PC ����â ����]���� ���� ��û�� ����˴ϴ�. <<'nicepaySubmit()' �̸� ���� �Ұ���>>
function nicepaySubmit(){
	document.payForm.submit();
}

//[PC ����â ����]����â ���� �Լ� <<'nicepayClose()' �̸� ���� �Ұ���>>
function nicepayClose(){
	alert("������ ��� �Ǿ����ϴ�");
}

</script>
</head>
<body>
<form name="payForm" method="post" action="payResult.jsp">
	<table>
		<tr>
			<th>���� ����</th>
			<td><input type="text" name="PayMethod" value=""></td>
		</tr>
		<tr>
			<th>���� ��ǰ��</th>
			<td><input type="text" name="GoodsName" value="<%=goodsName%>"></td>
		</tr>
		<tr>
			<th>���� ��ǰ�ݾ�</th>
			<td><input type="text" name="Amt" value="<%=price%>"></td>
		</tr>				
		<tr>
			<th>���� ���̵�</th>
			<td><input type="text" name="MID" value="<%=merchantID%>"></td>
		</tr>	
		<tr>
			<th>��ǰ �ֹ���ȣ</th>
			<td><input type="text" name="Moid" value="<%=moid%>"></td>
		</tr> 
		<tr>
			<th>�����ڸ�</th>
			<td><input type="text" name="BuyerName" value="<%=buyerName%>"></td>
		</tr>
		<tr>
			<th>�����ڸ� �̸���</th>
			<td><input type="text" name="BuyerEmail" value="<%=buyerEmail%>"></td>
		</tr>		
		<tr>
			<th>������ ����ó</th>
			<td><input type="text" name="BuyerTel" value="<%=buyerTel%>"></td>
		</tr>	 
		<tr>
			<th>�����Ϸ� ���ó�� URL<!-- (����� ����â ����)PC ����â ���� �ʿ� ���� --></th>
			<td><input type="text" name="ReturnURL" value="<%=returnURL%>"></td>
		</tr>
		<tr>
			<th>��������Աݸ�����(YYYYMMDD)</th>
			<td><input type="text" name="VbankExpDate" value=""></td>
		</tr>		
					
		<!-- �ɼ� -->	 
		<input type="hidden" name="GoodsCl" value="1"/>						<!-- ��ǰ����(�ǹ�(1),������(0)) -->
		<input type="hidden" name="TransType" value="0"/>					<!-- �Ϲ�(0)/����ũ��(1) --> 
		<input type="hidden" name="CharSet" value="euc-kr"/>				<!-- ���� �Ķ���� ���ڵ� ��� -->
		<input type="hidden" name="ReqReserved" value=""/>					<!-- ���� �����ʵ� -->
					
		<!-- ���� �Ұ��� -->
		<input type="hidden" name="EdiDate" value="<%=ediDate%>"/>			<!-- ���� �����Ͻ� -->
		<input type="hidden" name="SignData" value="<%=hashString%>"/>		<!-- �ؽ��� -->
	</table>
	<a href="#" class="btn_blue" onClick="nicepayStart();">�� û</a>
</form>
</body>
</html>
<%!
public final synchronized String getyyyyMMddHHmmss(){
	SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	return yyyyMMddHHmmss.format(new Date());
}
// SHA-256 �������� ��ȣȭ
public class DataEncrypt{
	MessageDigest md;
	String strSRCData = "";
	String strENCData = "";
	String strOUTData = "";

	public DataEncrypt(){ }
	public String encrypt(String strData){
		String passACL = null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(strData.getBytes());
			byte[] raw = md.digest();
			passACL = encodeHex(raw);
		}catch(Exception e){
			System.out.print("��ȣȭ ����" + e.toString());
		}
		return passACL;
	}

	public String encodeHex(byte [] b){
		char [] c = Hex.encodeHex(b);
		return new String(c);
	}
}
%>