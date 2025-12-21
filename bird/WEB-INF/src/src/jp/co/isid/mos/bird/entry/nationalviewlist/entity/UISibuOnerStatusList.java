package jp.co.isid.mos.bird.entry.nationalviewlist.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.entry.common.code.ConditionKbn;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;

public class UISibuOnerStatusList {
    
    public static final String TABLE = "BT20ENON";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String entryFlg_COLUMN = "ENTRY_FLG";
    
    public static final String attendCnt_COLUMN = "ATTEND_CNT";
    
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
     * 参加フラグ
     */
    private String entryFlg;
    
    /**
     * 参加人数
     */
    private BigDecimal attendCnt;
    
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
     * 参加フラグを取得します。
     * @return 参加フラグ
     */
    public String getEntryFlg() {
        return entryFlg;
    }
    /**
     * 参加フラグを設定します。
     * @param entryFlg 参加フラグ
     */
    public void setEntryFlg(String entryFlg) {
        this.entryFlg = entryFlg;
    }
    
    /**
     * 参加人数を取得します。
     * @return 参加人数
     */
    public BigDecimal getAttendCnt() {
        if(attendCnt == null){
            return new BigDecimal("0");
        }
        return attendCnt;
    }
    /**
     * 参加人数を設定します。
     * @param attendCnt 参加人数
     */
    public void setAttendCnt(BigDecimal attendCnt) {
        this.attendCnt = attendCnt;
    }
    /**
     * 参加フラグ記号取得処理
     * 
     * 0 : 不参加　→　'×'
     * 1 : 参加    →  '○'
     * 空: 未使用  →  '－'
     * @return
     */
    public String getEntryFlgMark(){
        if(NationalViewListUtil.isNull(getEntryFlg())) {
            return "－";
        }
        if(ConditionKbn.VALUE_ENTRY.equals(getEntryFlg().trim())){
            return "○";
        }
        else if(ConditionKbn.VALUE_NOTENTRY.equals(getEntryFlg().trim())){
            return "×";
        }
        else {
            return "－";
        }
    }
}
