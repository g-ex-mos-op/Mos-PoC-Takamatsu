package jp.co.isid.mos.bird.commonform.publictargetsearch.entity;

public class MstCompanySibuTorikomi {
    
    public static final String TABLE = "BM10BSIB";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";


    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 業態区分
     */
    private  String gyotaiKbn;
    
    /**
     * 支部取込コード
     */
    private String sibuCd;
    
    /**
     * 支部取込名称
     */
    private String sibuName;
    
    /**
     * 選択フラグ
     */
    private boolean sentakuFlg;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 支部取込コードを取得します。
     * @return 支部取込コード
     */
    public String getSibuCd() {
        if(sibuCd != null){
            return sibuCd.trim();
        }
        return sibuCd;
    }
    /**
     * 支部取込コードを設定します。
     * @param sibuCd 支部取込コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部取込名称を取得します。
     * @return 支部取込名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部取込名称を設定します。
     * @param sibuName 支部取込名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean getSentakuFlg() {
        return sentakuFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param sentakuFlg 選択フラグ
     */
    public void setSentakuFlg(boolean sentakuFlg) {
        this.sentakuFlg = sentakuFlg;
    }
    
    
    /**
     * 業態区分を取得します。
     * @return gyotaiKbn 業態区分
     */
    public String getGyotaiKbn() {
        if(gyotaiKbn != null){
            return gyotaiKbn.trim();
        }

        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
}
