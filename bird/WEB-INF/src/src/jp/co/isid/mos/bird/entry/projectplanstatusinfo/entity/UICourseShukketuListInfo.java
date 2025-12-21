package jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity;

public class UICourseShukketuListInfo {
    
    public static final String TABLE = "BT46KENS";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";

    public static final String shukketuKbn_COLUMN = "SHUKKETU_KBN";
    
    public static final String shussekiCnt_COLUMN = "SHUSSEKI_CNT";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String keiyakuSta_COLUMN = "KEIYAKU_STA";
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部
     */
    private String sibuName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
   /**
     * 出欠区分
     */
    private String shukketuKbn;
    
   /**
     * 出席者
     */
    private String shussekiCnt;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 契約開始日
     */
    private String keiyakuSta;
    
    /**
     * 契約終了日
     */
    private String keiyakuEnd;
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部を取得します。
     * @return 支部
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部を設定します。
     * @param sibuName 支部
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
   /**
     * 出欠区分を取得します。
     * @return 出欠区分
     */
    public String getShukketuKbn() {
        return shukketuKbn;
    }
    /**
     * 出欠区分を設定します。
     * @param shukketuKbn 出欠区分
     */
    public void setShukketuKbn(String shukketuKbn) {
        this.shukketuKbn = shukketuKbn;
    }
    
   /**
     * 出席者を取得します。
     * @return 出席者
     */
    public String getShussekiCnt() {
        return shussekiCnt;
    }
    /**
     * 出席者を設定します。
     * @param shussekiCnt 出席者
     */
    public void setShussekiCnt(String shussekiCnt) {
        this.shussekiCnt = shussekiCnt;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 契約開始日を取得します。
     * @return 契約開始日
     */
    public String getKeiyakuSta() {
        return keiyakuSta;
    }
    /**
     * 契約開始日を設定します。
     * @param keiyakuSta 契約開始日
     */
    public void setKeiyakuSta(String keiyakuSta) {
        this.keiyakuSta = keiyakuSta;
    }
    
    /**
     * 契約終了日を取得します。
     * @return 契約終了日
     */
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    /**
     * 契約終了日を設定します。
     * @param keiyakuEnd 契約終了日
     */
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    
}
