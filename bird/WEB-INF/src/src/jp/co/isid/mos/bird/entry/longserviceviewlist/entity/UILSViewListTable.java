/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.entity;

import java.math.BigDecimal;

public class UILSViewListTable {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String applyCnt_COLUMN = "APPLY_CNT";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 申請者カウント
     */
    private java.math.BigDecimal applyCnt;
    
    
    private String applyMark;
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
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
     * 申請者カウントを取得します。
     * @return 申請者カウント
     */
    public java.math.BigDecimal getApplyCnt() {
        if(applyCnt == null){
            return new BigDecimal("0");
        }
        return applyCnt;
    }
    
    public String getApplyCntMark() {
//        String applyMark = null;
//        BigDecimal dec0 = new BigDecimal("0");
//        if(getApplyCnt().compareTo(dec0) > 0) {
//            applyMark = "○";
//        } else {
//            applyMark = "－";
//        }
//        return applyMark;
        
      String applyMark = null;
      BigDecimal dec0 = new BigDecimal("0");
      if(getApplyCnt().compareTo(dec0) > 0) {
          applyMark = "○";
      } else {
          applyMark = "－";
      }
      return applyMark;
        
    }
    
    /**
     * 申請者カウントを設定します。
     * @param applyCnt 申請者カウント
     */
    public void setApplyCnt(java.math.BigDecimal applyCnt) {
        this.applyCnt = applyCnt;
    }
    
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

    public String getApplyMark() {
        return applyMark;
    }

    public void setApplyMark(String applyMark) {
        this.applyMark = applyMark;
    }
    
}