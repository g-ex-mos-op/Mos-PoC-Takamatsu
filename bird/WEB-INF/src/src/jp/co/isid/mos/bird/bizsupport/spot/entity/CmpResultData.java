package jp.co.isid.mos.bird.bizsupport.spot.entity;


/**
 * キャンペーン受注管理情報エンティティ
 * @author xsong
 *
 */
public class CmpResultData {
    
    public static final String TABLE = "BM53CPJK";
    
    public static final String cmpFrom_COLUMN = "CMP_FROM";
    
    public static final String cmpTo_COLUMN = "CMP_TO";
    
    public static final String posFromDt_COLUMN = "POS_FROM_DT";
    
    public static final String posEndDt_COLUMN = "POS_END_DT";
    
    public static final String sysDt_COLUMN = "SYS_DT";
    
    /**
     * キャンペーン開始日
     */
    private String cmpFrom;
    
    /**
     * キャンペーン終了日
     */
    private String cmpTo;
    
    /**
     * POS受注開始日 
     */
    private String posFromDt;
    
    /**
     * POS受注締め日
     */
    private String posEndDt;
    
    /**
     * 作成日
     */
    private String sysDt;
    
    /**
     * キャンペーン開始日を取得します。
     * @return キャンペーン開始日
     */
    public String getCmpFrom() {
        return cmpFrom;
    }
    
    /**
     * キャンペーン開始日を設定します。
     * @param cmpFrom キャンペーン開始日
     */
    public void setCmpFrom(String cmpFrom) {
        this.cmpFrom = cmpFrom;
    }
    
    /**
     * キャンペーン終了日を取得します。
     * @return キャンペーン終了日
     */
    public String getCmpTo() {
        return cmpTo;
    }
    
    /**
     * キャンペーン終了日を設定します。
     * @param cmpTo キャンペーン終了日
     */
    public void setCmpTo(String cmpTo) {
        this.cmpTo = cmpTo;
    }
    
    /**
     * POS受注開始日 を取得します。
     * @return POS受注開始日 
     */
    public String getPosFromDt() {
        return posFromDt;
    }
    
    /**
     * POS受注開始日 を設定します。
     * @param posFromDt POS受注開始日 
     */
    public void setPosFromDt(String posFromDt) {
        this.posFromDt = posFromDt;
    }
    
    /**
     * POS受注締め日を取得します。
     * @return POS受注締め日
     */
    public String getPosEndDt() {
        return posEndDt;
    }
    
    /**
     * POS受注締め日を設定します。
     * @param posEndDt POS受注締め日
     */
    public void setPosEndDt(String posEndDt) {
        this.posEndDt = posEndDt;
    }
    
    /**
     * 作成日を取得します。
     * @return 作成日
     */
    public String getSysDt() {
        return sysDt;
    }
    
    /**
     * 作成日を設定します。
     * @param sysDt 作成日
     */
    public void setSysDt(String sysDt) {
        this.sysDt = sysDt;
    }
    
}
