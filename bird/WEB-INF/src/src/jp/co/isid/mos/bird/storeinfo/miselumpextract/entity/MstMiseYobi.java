package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

public class MstMiseYobi {
    
    public static final String TABLE = "BM23MIYO";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String emergenceTel1_COLUMN = "EMERGENCE_TEL1";
    
    public static final String emergenceTel2_COLUMN = "EMERGENCE_TEL2";
    
    public static final String emergenceTel3_COLUMN = "EMERGENCE_TEL3";
    
    public static final String emergenceName1_COLUMN = "EMERGENCE_NAME1";
    
    public static final String emergenceName2_COLUMN = "EMERGENCE_NAME2";
    
    public static final String emergenceName3_COLUMN = "EMERGENCE_NAME3";
    
    public static final String yobiFree1_COLUMN = "YOBI_FREE_1";
    
    public static final String yobiFree2_COLUMN = "YOBI_FREE_2";
    
    public static final String yobiFree3_COLUMN = "YOBI_FREE_3";
    
    public static final String yobiFree4_COLUMN = "YOBI_FREE_4";
    
    public static final String yobiFree5_COLUMN = "YOBI_FREE_5";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * 緊急連絡先電話番号1
     */
    private String emergenceTel1;
    
    /**
     * 緊急連絡先電話番号2
     */
    private String emergenceTel2;
    
    /**
     * 緊急連絡先電話番号3
     */
    private String emergenceTel3;
    
    /**
     * 緊急連絡先氏名1
     */
    private String emergenceName1;
    
    /**
     * 緊急連絡先氏名2
     */
    private String emergenceName2;
    
    /**
     * 緊急連絡先氏名3
     */
    private String emergenceName3;
    
    /**
     * 予備フリー項目1
     */
    private String yobiFree1;
    
    /**
     * 予備フリー項目2
     */
    private String yobiFree2;
    
    /**
     * 予備フリー項目3
     */
    private String yobiFree3;
    
    /**
     * 予備フリー項目4
     */
    private String yobiFree4;
    
    /**
     * 予備フリー項目5
     */
    private String yobiFree5;
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    
    /**
     * 管理会社コード企業コードを取得します。
     * @return 管理会社コード企業コード
     */
    public String getCompanyCd() {
        return convString(companyCd);
    }
    /**
     * 管理会社コード企業コードを設定します。
     * @param companyCd 管理会社コード企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return convString(miseNameKj);
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return convString(closeDt);
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * 緊急連絡先電話番号1を取得します。
     * @return 緊急連絡先電話番号1
     */
    public String getEmergenceTel1() {
        return convString(emergenceTel1);
    }
    /**
     * 緊急連絡先電話番号1を設定します。
     * @param emergenceTel1 緊急連絡先電話番号1
     */
    public void setEmergenceTel1(String emergenceTel1) {
        this.emergenceTel1 = emergenceTel1;
    }
    
    /**
     * 緊急連絡先電話番号2を取得します。
     * @return 緊急連絡先電話番号2
     */
    public String getEmergenceTel2() {
        return convString(emergenceTel2);
    }
    /**
     * 緊急連絡先電話番号2を設定します。
     * @param emergenceTel2 緊急連絡先電話番号2
     */
    public void setEmergenceTel2(String emergenceTel2) {
        this.emergenceTel2 = emergenceTel2;
    }
    
    /**
     * 緊急連絡先電話番号3を取得します。
     * @return 緊急連絡先電話番号3
     */
    public String getEmergenceTel3() {
        return convString(emergenceTel3);
    }
    /**
     * 緊急連絡先電話番号3を設定します。
     * @param emergenceTel3 緊急連絡先電話番号3
     */
    public void setEmergenceTel3(String emergenceTel3) {
        this.emergenceTel3 = emergenceTel3;
    }
    
    /**
     * 緊急連絡先氏名1を取得します。
     * @return 緊急連絡先氏名1
     */
    public String getEmergenceName1() {
        return convString(emergenceName1);
    }
    /**
     * 緊急連絡先氏名1を設定します。
     * @param emergenceName1 緊急連絡先氏名1
     */
    public void setEmergenceName1(String emergenceName1) {
        this.emergenceName1 = emergenceName1;
    }
    
    /**
     * 緊急連絡先氏名2を取得します。
     * @return 緊急連絡先氏名2
     */
    public String getEmergenceName2() {
        return convString(emergenceName2);
    }
    /**
     * 緊急連絡先氏名2を設定します。
     * @param emergenceName2 緊急連絡先氏名2
     */
    public void setEmergenceName2(String emergenceName2) {
        this.emergenceName2 = emergenceName2;
    }
    
    /**
     * 緊急連絡先氏名3を取得します。
     * @return 緊急連絡先氏名3
     */
    public String getEmergenceName3() {
        return convString(emergenceName3);
    }
    /**
     * 緊急連絡先氏名3を設定します。
     * @param emergenceName3 緊急連絡先氏名3
     */
    public void setEmergenceName3(String emergenceName3) {
        this.emergenceName3 = emergenceName3;
    }
    
    /**
     * 予備フリー項目1を取得します。
     * @return 予備フリー項目1
     */
    public String getYobiFree1() {
        return convString(yobiFree1);
    }
    /**
     * 予備フリー項目1を設定します。
     * @param yobiFree1 予備フリー項目1
     */
    public void setYobiFree1(String yobiFree1) {
        this.yobiFree1 = yobiFree1;
    }
    
    /**
     * 予備フリー項目2を取得します。
     * @return 予備フリー項目2
     */
    public String getYobiFree2() {
        return convString(yobiFree2);
    }
    /**
     * 予備フリー項目2を設定します。
     * @param yobiFree2 予備フリー項目2
     */
    public void setYobiFree2(String yobiFree2) {
        this.yobiFree2 = yobiFree2;
    }
    
    /**
     * 予備フリー項目3を取得します。
     * @return 予備フリー項目3
     */
    public String getYobiFree3() {
        return convString(yobiFree3);
    }
    /**
     * 予備フリー項目3を設定します。
     * @param yobiFree3 予備フリー項目3
     */
    public void setYobiFree3(String yobiFree3) {
        this.yobiFree3 = yobiFree3;
    }
    
    /**
     * 予備フリー項目4を取得します。
     * @return 予備フリー項目4
     */
    public String getYobiFree4() {
        return convString(yobiFree4);
    }
    /**
     * 予備フリー項目4を設定します。
     * @param yobiFree4 予備フリー項目4
     */
    public void setYobiFree4(String yobiFree4) {
        this.yobiFree4 = yobiFree4;
    }
    
    /**
     * 予備フリー項目5を取得します。
     * @return 予備フリー項目5
     */
    public String getYobiFree5() {
        return convString(yobiFree5);
    }
    /**
     * 予備フリー項目5を設定します。
     * @param yobiFree5 予備フリー項目5
     */
    public void setYobiFree5(String yobiFree5) {
        this.yobiFree5 = yobiFree5;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return convString(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return convString(blockCd);
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return convString(blockName);
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
    
}
