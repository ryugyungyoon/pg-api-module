<%@ page  contentType="text/html; charset=euc-kr" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.util.Calendar" %>
<%

    //*********************************************************************************
// �����ڰ� �Ա��ϸ� ���������� �뺸�� �����Ͽ� DB ó�� �ϴ� �κ� �Դϴ�.
// ���ŵǴ� �ʵ忡 ���� DB �۾��� �����Ͻʽÿ�.
// �����ʵ� �ڼ��� ������ �޴��� ����
//*********************************************************************************

    String PayMethod    = request.getParameter("PayMethod");        //���Ҽ���
    String PayMethod2    = request.getParameter("payNotiRequstDto");        //���Ҽ���
    String MID          = request.getParameter("MID");              //����ID
    String MallUserID   = request.getParameter("MallUserID");       //ȸ���� ID
    String Amt          = request.getParameter("Amt");              //�ݾ�
    String name         = request.getParameter("name");             //�����ڸ�
    String GoodsName    = request.getParameter("GoodsName");        //��ǰ��
    String TID          = request.getParameter("TID");              //�ŷ���ȣ
    String MOID         = request.getParameter("MOID");             //�ֹ���ȣ
    String AuthDate     = request.getParameter("AuthDate");         //�Ա��Ͻ� (yyMMddHHmmss)
    String ResultCode   = request.getParameter("ResultCode");       //����ڵ� ('4110' ��� �Ա��뺸)
    String ResultMsg    = request.getParameter("ResultMsg");        //����޽���
    String VbankNum     = request.getParameter("VbankNum");         //������¹�ȣ
    String FnCd         = request.getParameter("FnCd");             //������� �����ڵ�
    String VbankName    = request.getParameter("VbankName");        //������� �����
    String VbankInputName = request.getParameter("VbankInputName"); //�Ա��� ��
    String CancelDate   = request.getParameter("CancelDate");       //����Ͻ�

//*********************************************************************************
//�������ä���� ���ݿ����� �ڵ��߱޽�û�� �Ǿ������ ���޵Ǹ�
//RcptTID �� ���� �ִ°�츸 �߱�ó�� ��
//*********************************************************************************
    String RcptTID      = request.getParameter("RcptTID");          //���ݿ����� �ŷ���ȣ
    String RcptType     = request.getParameter("RcptType");         //���� ������ ����(0:�̹���, 1:�ҵ������, 2:����������)
    String RcptAuthCode = request.getParameter("RcptAuthCode");     //���ݿ����� ���ι�ȣ


//*********************************************************************************
// �̺κп� �α����� ��θ� �������ּ���.
// �α״� �����߻��� ���� ������ �߿䵥���� �̹Ƿ� �ݵ�� �������ֽñ� �ٶ��ϴ�.
//*********************************************************************************
    String file_path = "/project/exapi/logs/payInfo.txt";

    File file = new File(file_path);
    file.createNewFile();
    FileWriter fw = new FileWriter(file_path, true);

    fw.write("************************************************\r\n");
    fw.write("PayMethod     : " + PayMethod + "\r\n");
    fw.write("PayMethod2     : " + PayMethod2 + "\r\n");
    fw.write("MID           : " + MID + "\r\n");
    fw.write("MallUserID    : "+ MallUserID + "\r\n");
    fw.write("Amt           : " + Amt + "\r\n");
    fw.write("name          : " +  name + "\r\n");
    fw.write("GoodsName     : " + GoodsName + "\r\n");
    fw.write("TID           : "+ TID + "\r\n");
    fw.write("MOID          : "+ MOID + "\r\n");
    fw.write("AuthDate      : "+ AuthDate + "\r\n");
    fw.write("ResultCode    : "+ ResultCode + "\r\n");
    fw.write("ResultMsg     : "+ ResultMsg + "\r\n");
    fw.write("VbankNum      : "+ VbankNum + "\r\n");
    fw.write("FnCd          : "+ FnCd + "\r\n");
    fw.write("VbankName     : "+ VbankName + "\r\n");
    fw.write("VbankInputName : "+ VbankInputName + "\r\n");
    fw.write("RcptTID       : "+ RcptTID + "\r\n");
    fw.write("RcptType      : "+ RcptType + "\r\n");
    fw.write("RcptAuthCode  : "+ RcptAuthCode + "\r\n");
    fw.write("CancelDate    : "+ CancelDate + "\r\n");
    fw.write("************************************************\r\n");

    fw.close();

//������ DBó��

//**************************************************************************************************
//**************************************************************************************************
//���� ������ �뺸 ���� > ��OK�� üũ�ڽ��� üũ�� ���" �� ó�� �Ͻñ� �ٶ��ϴ�.
//**************************************************************************************************
//TCP�� ��� OK ���ڿ� �ڿ� �����ǵ� �߰�
//������ ���� �����ͺ��̽��� ��� ���������� ���� �����ÿ��� "OK"�� NICEPAY��
//�����ϼž��մϴ�. �Ʒ� ���ǿ� �����ͺ��̽� ������ �޴� FLAG ������ ��������
//(����) OK�� �������� �����ø� NICEPAY ������ "OK"�� �����Ҷ����� ��� �������� �õ��մϴ�
//��Ÿ �ٸ� ������ PRINT(out.print)�� ���� �����ñ� �ٶ��ϴ�
//if (�����ͺ��̽� ��� ���� ���� ���Ǻ��� = true)
//  {
//      out.print("OK"); // ����� ������ ������
//  }
//  else
//  {
//      out.print("FAIL"); // ����� ������ ������
//  }
//*************************************************************************************************
//*************************************************************************************************

%>

