/*
 * 作成日: 2005/12/07
 */
package jp.co.isid.mos.bird.framework.dto;

import java.util.HashMap;
import java.util.Map;


/**
 * 共通DTO
 * @author xnkusama
 */
public class CommonCodeDto implements CommonDto {
	private String companyCd;
    private String miseCd;
    private String onerCd;
    private boolean isUseDto;
    private Map paramMap = new HashMap();
    /**
     * 遷移元情報
     */
    private String navigationCase;

    
    /**
     * 検索条件 店コード取得処理
     * @return String 店コード
     */
    public String getMiseCd() {
        return this.miseCd;
    }
    /**
     * 検索条件 店コード設定処理
     * @param miseCode 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    /**
     * 検索条件 オーナーコード取得処理
     * @return String オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * 検索条件 オーナー設定処理
     * @param miseCode オーナー
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * 共通DTO使用フラグ取得処理
     * @return boolean true:使用する
     */
    public boolean getUseCommonDto() {
        return isUseDto;
    }
    /**
     * 共通DTO使用フラグ設定処理
     * @param boolean true:使用する
     */
    public void setUseCommonDto(boolean flg) {
        this.isUseDto = flg;
    }
    
    /**
     * クリア処理
     */
    public void clear() {
        setMiseCd("");
        setOnerCd("");
        setCompanyCd("");
        setUseCommonDto(false);
        paramMap.clear();
        setNavigationCase("");

    }
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
    /**
     * 画面間パラメータ受け渡し用プロパティ
     * 　※キー名は、画面間で定義する
     * @param key
     * @return
     */
    public Object getParam(String key) {
        return paramMap.get(key);
    }
    public void setParam(String key, Object value) {
        this.paramMap.put(key, value);
    }
    public String getNavigationCase() {
        return navigationCase;
    }
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }
}
