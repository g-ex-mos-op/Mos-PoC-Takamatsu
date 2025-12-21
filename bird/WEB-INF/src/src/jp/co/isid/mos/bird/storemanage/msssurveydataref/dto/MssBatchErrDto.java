package jp.co.isid.mos.bird.storemanage.msssurveydataref.dto;

import java.io.Serializable;

public class MssBatchErrDto implements Serializable {

    public static final long serialVersionUID = 0L;
    
	private String errCd;
	
	private String BErrCd;
    
    private String title;
	
    private String errMsg1;
    
    private String errMsg2;
    public MssBatchErrDto() {
	}

    public String getErrCd() {
        return errCd;
    }

    public void setErrCd(String errCd) {
        this.errCd = errCd;
    }

    public String getBErrCd() {
        return BErrCd;
    }

    public void setBErrCd(String errCd) {
        BErrCd = errCd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getErrMsg1() {
        return errMsg1;
    }

    public void setErrMsg1(String errMsg1) {
        this.errMsg1 = errMsg1;
    }
    public String getErrMsg2() {
        return errMsg2;
    }

    public void setErrMsg2(String errMsg2) {
        this.errMsg2 = errMsg2;
    }
	
}
