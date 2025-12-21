package jp.co.isid.mos.bird.common.entity;

public class TrnDocAccess {
    
    public static final String TABLE = "";
    
    public static final String infoShu_COLUMN = "INFO_SHU";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";
    
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String kobetsuFlg_COLUMN = "KOBETSU_FLG";
    
    public static final String miseFlg_COLUMN = "MISE_FLG";
    
    public static final String kobetsuShu_COLUMN = "KOBETSU_SHU";
    
    public static final String kobetsuCd_COLUMN = "KOBETSU_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    /**
     * 情報種別
     */
    private String infoShu;
    
    /**
     * 情報登録日
     */
    private String regDate;
    
    /**
     * 情報シーケンス番号
     */
    private String seq;
    
    /**
     * 企業コード
     */
    private String rCompanyCd;
    
    /**
     * 所属区分
     */
    private String shozokuKbn;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * 個別設定フラグ
     */
    private String kobetsuFlg;
    
    /**
     * 個店別設定フラグ
     */
    private String miseFlg;
    
    /**
     * 個別設定種別
     */
    private String kobetsuShu;
    
    /**
     * 個別設定コード
     */
    private String kobetsuCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 情報種別を取得します。
     * @return 情報種別
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * 情報種別を設定します。
     * @param infoShu 情報種別
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }
    
    /**
     * 情報登録日を取得します。
     * @return 情報登録日
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * 情報登録日を設定します。
     * @param regDate 情報登録日
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    
    /**
     * 情報シーケンス番号を取得します。
     * @return 情報シーケンス番号
     */
    public String getSeq() {
        return seq;
    }
    /**
     * 情報シーケンス番号を設定します。
     * @param seq 情報シーケンス番号
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }
    
    /**
     * 所属区分を取得します。
     * @return 所属区分
     */
    public String getShozokuKbn() {
        return shozokuKbn;
    }
    /**
     * 所属区分を設定します。
     * @param shozokuKbn 所属区分
     */
    public void setShozokuKbn(String shozokuKbn) {
        this.shozokuKbn = shozokuKbn;
    }
    
    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * 個別設定フラグを取得します。
     * @return 個別設定フラグ
     */
    public String getKobetsuFlg() {
        return kobetsuFlg;
    }
    /**
     * 個別設定フラグを設定します。
     * @param kobetsuFlg 個別設定フラグ
     */
    public void setKobetsuFlg(String kobetsuFlg) {
        this.kobetsuFlg = kobetsuFlg;
    }
    
    /**
     * 個店別設定フラグを取得します。
     * @return 個店別設定フラグ
     */
    public String getMiseFlg() {
        return miseFlg;
    }
    /**
     * 個店別設定フラグを設定します。
     * @param miseFlg 個店別設定フラグ
     */
    public void setMiseFlg(String miseFlg) {
        this.miseFlg = miseFlg;
    }
    
    /**
     * 個別設定種別を取得します。
     * @return 個別設定種別
     */
    public String getKobetsuShu() {
        return kobetsuShu;
    }
    /**
     * 個別設定種別を設定します。
     * @param kobetsuShu 個別設定種別
     */
    public void setKobetsuShu(String kobetsuShu) {
        this.kobetsuShu = kobetsuShu;
    }
    
    /**
     * 個別設定コードを取得します。
     * @return 個別設定コード
     */
    public String getKobetsuCd() {
        return kobetsuCd;
    }
    /**
     * 個別設定コードを設定します。
     * @param kobetsuCd 個別設定コード
     */
    public void setKobetsuCd(String kobetsuCd) {
        this.kobetsuCd = kobetsuCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd( String miseCd) {
        this.miseCd = miseCd;
    }
    
}
