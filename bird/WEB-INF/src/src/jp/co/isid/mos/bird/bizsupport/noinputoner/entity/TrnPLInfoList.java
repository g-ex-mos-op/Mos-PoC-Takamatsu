package jp.co.isid.mos.bird.bizsupport.noinputoner.entity;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

/**
 * @author Aspac
 */
public class TrnPLInfoList extends TrnPLInfo {

    public static final String TABLE = "BT17PLDT";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    public static final String closeDt_COLUMN = "CLOSE_DT";

    private String sibuCd;
    private String sibuName;
    private String onerNameKj;
    private String closeDt;

    public String getSibuCd() {
        return sibuCd;
    }
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    public String getSibuName() {
        return sibuName;
    }
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    public String getOnerNameKj() {
        return onerNameKj;
    }
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    public String getCloseDt() {
        return closeDt;
    }
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
}
