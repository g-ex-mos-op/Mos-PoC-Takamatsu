/*
 * 作成日: 2005/12/19
 */
package jp.co.isid.mos.bird.framework.dto;

/**
 * オーナー関連共通DTO
 * @author xnkusama
 */
public class CommonDateDto implements CommonDto {
    private String _dateKikanFrom;
    private String _dateKikanTo;
    private String _nengetuFrom;
    private String _nengetuTo;
    private boolean _isUseDto;
    
    /**
     * 検索条件 期間From取得処理
     * @return String 期間From
     */
    public String getKikanFrom() {
        return _dateKikanFrom;
    }
    /**
     * 検索条件 期間From設定処理
     * @param miseCode 期間From
     */
    public void setKikanFrom(final String kikanFrom) {
        this._dateKikanFrom = kikanFrom;
    }

    /**
     * 検索条件 期間To取得処理
     * @return String 期間To
     */
    public String getKikanTo() {
        return _dateKikanTo;
    }
    /**
     * 検索条件 期間To設定処理
     * @param miseCode 期間To
     */
    public void setKikanTo(final String kikanTo) {
        this._dateKikanTo = kikanTo;
    }

    /**
     * 検索条件 年月From取得処理
     * @return String 年月From
     */
    public String getNengetuFrom() {
        return _nengetuFrom;
    }
    /**
     * 検索条件 年月From設定処理
     * @param miseCode 年月From
     */
    public void setNengetuFrom(final String nengetuFrom) {
        this._nengetuFrom = nengetuFrom;
    }

    /**
     * 検索条件 年月To取得処理
     * @return String 年月To
     */
    public String getNengetuTo() {
        return _nengetuTo;
    }
    /**
     * 検索条件 年月To設定処理
     * @param miseCode 年月To
     */
    public void setNengetuTo(final String nengetuTo) {
        this._nengetuTo = nengetuTo;
    }

    /**
     * 共通DTO使用フラグ取得処理
     * @return boolean true:使用する
     */
    public boolean getUseCommonDto() {
        return _isUseDto;
    }
    /**
     * 共通DTO使用フラグ設定処理
     * @param boolean true:使用する
     */
    public void setUseCommonDto(boolean flg) {
        this._isUseDto = flg;
    }
    
    /**
     * クリア処理
     */
    public void clear() {
        setKikanFrom("");
        setKikanTo("");
        setNengetuFrom("");
        setNengetuTo("");
        setUseCommonDto(false);
    }
}
