package jp.co.isid.mos.bird.commonform.svsearchnew.logic;


import java.util.Map;

/**
 * SV情報処理ロジックインターフェース
 * @author kusama
 */
public interface GetSvInfoLogic {

    /**
     * SV情報検索処理
     * 
     * @param companyCd
     * @param svCd
     * @return
     */
    public Map execute(String companyCd, String svCd);
}