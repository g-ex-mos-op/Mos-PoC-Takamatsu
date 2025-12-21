package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic;

import java.util.List;


/**
 *　スポット数量確認取得ロジック
 * 
 * @author xlee
 */
public interface GetSpotQuantityInfoLogic {

    /**
     *　スポット数量情報の取得
     * @param taishoCond　対象条件
     * @param cmpNo　キャンペーンNO
     * @param companyCd　企業コード
     * @param miseCd　店コード
     * @param sibuCd　支部コード
     * @param blockCd　ブロックコード
     * @param sysDate　システム日付
     * @return スポット数量情報
     */
    public List execute(String taishoCond, String companyCd, String cmpNo, String miseCd, String sibuCd, String blockCd, String sysDate, List onerCdList);
    
    /**
     *　スポット数量情報の取得
     * @param cmpNo　キャンペーンNO
     * @param companyCd　企業コード
     * @param sysDate　システム日付
     * @param onerCd　オーナーコード
     * @return スポット数量情報
     */
    public List execute(String companyCd, String cmpNo, String sysDate, List onerCdList);
    
}
