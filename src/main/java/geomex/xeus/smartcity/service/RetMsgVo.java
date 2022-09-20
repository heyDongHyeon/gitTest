package geomex.xeus.smartcity.service;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  RetMsgVo.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 4. 18.
 * 버전   :  1.0
 * 설명   :  
 * 응답 메시지 클래스
 * 
 *
 * 수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 
 * ===========================================================
 * </pre>
 */
public class RetMsgVo {
    private String rMsg = "0";  //성공,  실패 1

    public String getrMsg() {
        return rMsg;
    }

    public void setrMsg(String rMsg) {
        this.rMsg = rMsg;
    }

    public String callbackString(String callback) {
        StringBuilder sb = new StringBuilder();
        if (callback != null) {
            sb.append(callback).append("(");
        }
        sb.append("{");
        sb.append("\"").append("rMsg").append("\"");
        sb.append(":");
        sb.append("\"").append(rMsg).append("\"");
        sb.append("}");
        if (callback != null) {
            sb.append(")");
        }
        return sb.toString();
    }
}
