<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.URL"%>

<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="org.apache.commons.codec.binary.Hex"%>
<%
    request.setCharacterEncoding("euc-kr");

    String transStatusURL = "https://webapi.nicepay.co.kr/webapi/inquery/trans_status.jsp"; 	// transaction status URL
    String mid = "nicepay00m";										// 상점아이디 (개발시 값을 넣어줘야하는 필드)
    String tid = "nicepay00m01012411081606583810";										// 거래번호 (개발시 값을 넣어줘야하는 필드)


    // 응답 결과 파라미터 초기화
    String ResultCode = "";
    String ResultMsg = "";
    String TID = "";
    String Status = "";
    String AuthCode = "";
    String AuthDate = "";

    String resultJsonStr = "";


    /*
     ****************************************************************************************
     * <해쉬암호화> (수정하지 마세요)
     * SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다.
     ****************************************************************************************
     */
    DataEncrypt sha256Enc = new DataEncrypt();
    String merchantKey = "";
    String ediDate = getyyyyMMddHHmmss();
    String SignData = sha256Enc.encrypt(tid + mid + ediDate + merchantKey);

    StringBuffer requestData = new StringBuffer();
    requestData.append("TID=").append(tid).append("&");
    requestData.append("MID=").append(mid).append("&");
    requestData.append("EdiDate=").append(ediDate).append("&");
    requestData.append("Charset=").append("euc-kr").append("&");
    requestData.append("SignData=").append(SignData).append("&");

    // API Call
    resultJsonStr = connectToServer(requestData.toString(),transStatusURL);

    HashMap resultData = new HashMap();

    resultData = jsonStringToHashMap(resultJsonStr);
    ResultCode = (String) resultData.get("ResultCode");
    ResultMsg = (String) resultData.get("ResultMsg");
    TID = (String) resultData.get("TID");
    Status = (String) resultData.get("Status");
    AuthCode = (String) resultData.get("AuthCode");
    AuthDate = (String) resultData.get("AuthDate");


%>

<!DOCTYPE html>
<html>
<head>
    <title>NICEPAY TRANSACTION_STATUS_RESULT(EUC-KR)</title>
    <meta charset="euc-kr">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi" />
    <link rel="stylesheet" type="text/css" href="./css/import.css" />
</head>
<body>
<div class="top">NICEPAY TRANSACTION_STATUS_RESULT(EUC-KR)</div>
<div class="conwrap">
    <div class="tabletypea">
        <table>
            <colgroup>
                <col width="150px" />
                <col width="*">
            </colgroup>
            <tr>
                <th><span>ResultCode</span></th>
                <td><%=ResultCode%></td>
            </tr>
            <tr>
                <th><span>ResultMsg</span></th>
                <td><%=ResultMsg%></td>
            </tr>
            <tr>
                <th><span>TID</span></th>
                <td><%=TID%></td>
            </tr>
            <tr>
                <th><span>Status</span></th>
                <td><%=Status%></td>
            </tr>
            <tr>
                <th><span>AuthCode</span></th>
                <td><%=AuthCode%></td>
            </tr>
            <tr>
                <th><span>AuthDate</span></th>
                <td><%=AuthDate%></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
<%!
    public final synchronized String getyyyyMMddHHmmss() {
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
        return yyyyMMddHHmmss.format(new Date());
    }

    // SHA-256 encryption
    public class DataEncrypt {
        MessageDigest md;
        String strSRCData = "";
        String strENCData = "";
        String strOUTData = "";

        public DataEncrypt() {

        }

        public String encrypt(String strData) {
            String passACL = null;
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-256");
                md.reset();
                md.update(strData.getBytes());
                byte[] raw = md.digest();
                passACL = encodeHex(raw);
            } catch (Exception e) {
                System.out.print("encryption error" + e.toString());
            }
            return passACL;
        }

        public String encodeHex(byte[] b) {
            char[] c = Hex.encodeHex(b);
            return new String(c);
        }
    }

    public String connectToServer(String data, String reqUrl)throws Exception{
        HttpURLConnection conn = null;
        BufferedReader resultReader = null;
        PrintWriter pw = null;
        URL url = null;

        int statusCode = 0;
        StringBuffer recvBuffer = new StringBuffer();
        try {
            url = new URL(reqUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(25000);
            conn.setDoOutput(true);

            pw = new PrintWriter(conn.getOutputStream());
            pw.write(data);
            pw.flush();

            statusCode = conn.getResponseCode();
            resultReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));
            for (String temp; (temp = resultReader.readLine()) != null;) {
                recvBuffer.append(temp).append("\n");
            }
            if (!(statusCode == HttpURLConnection.HTTP_OK)) {
                throw new Exception();
            }

            return recvBuffer.toString().trim();
        } catch (Exception e) {
            return "9999";
        } finally {
            recvBuffer.setLength(0);

            try {
                if (resultReader != null) {
                    resultReader.close();
                }
            } catch (Exception ex) {
                resultReader = null;
            }
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception ex) {
                pw = null;
            }

            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception ex) {
                conn = null;
            }
        }
    }

    private static HashMap jsonStringToHashMap(String str) throws Exception {
        HashMap dataMap = new HashMap();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(str);
            JSONObject jsonObject = (JSONObject) obj;
            Iterator<String> keyStr = jsonObject.keySet().iterator();
            while (keyStr.hasNext()) {
                String key = keyStr.next();
                Object value = jsonObject.get(key);
                dataMap.put(key, value);
            }
        } catch (Exception e) {

        }
        return dataMap;
    }
%>