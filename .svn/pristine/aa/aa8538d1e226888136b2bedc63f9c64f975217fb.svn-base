package geomex.xeus.smartcity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  BodyDSC.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 4. 10.
 * 버전   :  1.0
 * 설명   :  
 * 사회적약자 지원 서비스
 *
 * 수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 
 * ===========================================================
 * </pre>
 */
public class BodyDSC {
    public String SEND_NUM = "";    //발생번호(접수번호-송신시스템코드+SF00+YMDHMS(14)+밀리세컨(3), 유일번호 key로 활용
    public String SVC_TYP = "";     //서비스유형     프로기,초록버튼,기어S2,루나워치 등
    public String EVT_LON = "";     //발생위치   경도(longitude)
    public String EVT_LAT = "";     //발생위치   위도(latitude)
    public String EVT_ADDR = "";    //발생위치   전체 주소
    public String EVT_BJD = "";     //법정동코드 시도(2)+시군구(3)+읍면동(3)+리(2)

    public String REF_ID = "";      //SKT와 112 신고센터 주고 받는 ID

    public String DSC_NM = "";      //이름
    public String DSC_PHONE = "";   //휴대폰 번호
    public String DSC_BIRTH = "";   //출생일(출생날짜)
    public String DSC_SEX = "";     //성별
    public String DSC_ADDR = "";    //거주지
    public String GUARD_NM = "";    //보호자 성명
    public String GUARD_PHONE = ""; //보호자 휴대폰
    public String EVT_DTM = "";     //발생일시,로그인일시(yyyyMMddHHmmss)
    public String IMAGE = null;     //이미지 파일명
    public String INFO = "";        //신상정보
    public String NOTE = "";        //특이사항
    public String SENDER_ID = "";   //전송자 ID
    public String END = Code.END_OF_DATA; //END

    public BodyDSC() {}

    public BodyDSC(ByteBuffer buffer) throws Exception {
        String str = Utils.readString(buffer, buffer.remaining()); //전체를 읽는다.
        //맨뒤에 ;제거
        //str = Utils.removeENDStr(str); 안해도 됨
        //
        String items[] = str.split("\\" + Code.DATA_SEPERATOR);
        this.SEND_NUM = items[0];
        this.SVC_TYP = items[1];
        this.EVT_LON = items[2];
        this.EVT_LAT = items[3];
        this.EVT_ADDR = items[4];
        this.EVT_BJD = items[5];
        this.REF_ID = items[6];
        this.DSC_NM = items[7];
        this.DSC_PHONE = items[8];
        this.DSC_BIRTH = items[9];
        this.DSC_SEX = items[10];
        this.DSC_ADDR = Utils.array(items[11]);
        this.GUARD_NM = Utils.array(items[12]);
        this.GUARD_PHONE = Utils.array(items[13]);
        this.EVT_DTM = items[14];
        this.IMAGE = Utils.array(items[15]);
        this.INFO = Utils.array(items[16]);
        this.NOTE = Utils.array(items[17]);
        this.SENDER_ID = items[18];
        //END
    }

    public void write(ByteBuffer buffer) throws Exception {
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(Utils.getUTFBytes(SEND_NUM + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(SVC_TYP + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(EVT_LON + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(EVT_LAT + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(EVT_ADDR + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(EVT_BJD + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(REF_ID + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(DSC_NM + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(DSC_PHONE + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(DSC_BIRTH + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(DSC_SEX + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(DSC_ADDR + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(GUARD_NM + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(GUARD_PHONE + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(EVT_DTM + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(IMAGE + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(INFO + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(NOTE + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(SENDER_ID + Code.DATA_SEPERATOR));
        buffer.put(Utils.getUTFBytes(END));
    }

    //    public String asText() {
    //        StringBuilder sb = new StringBuilder();
    //        sb.append(SEND_NUM);
    //        sb.append(SVC_TYP);
    //        sb.append(EVT_LON);
    //        sb.append(EVT_LAT);
    //        sb.append(EVT_ADDR);
    //        sb.append(EVT_BJD);
    //        sb.append(REF_ID);
    //        sb.append(DSC_NM);
    //        sb.append(DSC_PHONE);
    //        sb.append(DSC_BIRTH);
    //        sb.append(DSC_SEX);
    //        sb.append(DSC_ADDR);
    //        sb.append(GUARD_NM);
    //        sb.append(GUARD_PHONE);
    //        sb.append(EVT_DTM);
    //        sb.append(IMAGE);
    //        sb.append(INFO);
    //        sb.append(NOTE);
    //        sb.append(SENDER_ID);
    //        sb.append(END);
    //        return sb.toString();
    //    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SEND_NUM).append(Code.DATA_SEPERATOR);
        sb.append(SVC_TYP).append(Code.DATA_SEPERATOR);
        sb.append(EVT_LON).append(Code.DATA_SEPERATOR);
        sb.append(EVT_LAT).append(Code.DATA_SEPERATOR);
        sb.append(EVT_ADDR).append(Code.DATA_SEPERATOR);
        sb.append(EVT_BJD).append(Code.DATA_SEPERATOR);
        sb.append(REF_ID).append(Code.DATA_SEPERATOR);
        sb.append(DSC_NM).append(Code.DATA_SEPERATOR);
        sb.append(DSC_PHONE).append(Code.DATA_SEPERATOR);
        sb.append(DSC_BIRTH).append(Code.DATA_SEPERATOR);
        sb.append(DSC_SEX).append(Code.DATA_SEPERATOR);
        sb.append(DSC_ADDR).append(Code.DATA_SEPERATOR);
        sb.append(GUARD_NM).append(Code.DATA_SEPERATOR);
        sb.append(GUARD_PHONE).append(Code.DATA_SEPERATOR);
        sb.append(EVT_DTM).append(Code.DATA_SEPERATOR);
        sb.append(IMAGE).append(Code.DATA_SEPERATOR);
        sb.append(INFO).append(Code.DATA_SEPERATOR);
        sb.append(NOTE).append(Code.DATA_SEPERATOR);
        sb.append(SENDER_ID).append(Code.DATA_SEPERATOR);
        sb.append(END);
        return sb.toString();
    }
}
