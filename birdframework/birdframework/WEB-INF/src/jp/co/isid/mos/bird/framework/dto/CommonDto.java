/*
 * 作成日: 2005/12/21
 */
package jp.co.isid.mos.bird.framework.dto;

/**
 * 共通DTOインターフェイス
 * @author xnkusama
 */
public interface CommonDto {
    /**
     * クリア処理
     */
    public void clear();
    
    /**
     * 共通DTO使用フラグ取得処理
     * @return boolean true:使用する
     */
    public boolean getUseCommonDto();
    /**
     * 共通DTO使用フラグ設定処理
     * @param boolean true:使用する
     */
    public void setUseCommonDto(boolean flg);
  
}
