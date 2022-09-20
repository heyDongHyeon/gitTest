package geomex.xeus.smartcity.service;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  ReceptPrcsOkVo.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 4. 18.
 * 버전   :  1.0
 * 설명   :  
 * setReceptPrcsOk.json 응답 클래스
 *
 * 수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 
 * ===========================================================
 * </pre>
 */
public class ReceptPrcsOkVo {
    private String retCode = "200";
    private String retMsg = "SUCCESS";

    ///500, FAILED

    public void setSuccess() {
        this.retCode = "200";
        this.retMsg = "SUCCESS";
    }

    public void setFailed() {
        this.retCode = "500"; //실패 or 500
        this.retMsg = "FAILED";
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String callbackString(String callback) {
        StringBuilder sb = new StringBuilder();
        if (callback != null) {
            sb.append(callback).append("(");
        }
        sb.append("{");
        sb.append("\"").append("retCode").append("\"");
        sb.append(":");
        sb.append("\"").append(retCode).append("\"");

        sb.append("\"").append("retMsg").append("\"");
        sb.append(":");
        sb.append("\"").append(retMsg).append("\"");

        sb.append("}");
        if (callback != null) {
            sb.append(")");
        }
        return sb.toString();
    }
}
