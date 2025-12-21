/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.claimref.entity.UIVoiceInfo;

/**
 * お客様の声情報
 * 
 * @author xkinu
 *
 */
public interface UIVoiceInfoDao {
    public static final Class BEAN = UIVoiceInfo.class;
    public static final String select_ARGS = "sysDate, userId, userTypeCd, limitFlg, companyCd, taishoNengetu, taishoJoken, hyojiTaisho, hyojiNaiyo, bnrM, kanriNo";
    
    /**
     * 指定表示内容お客様の声情報の取得
     * 
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param taishoNengetu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param hyojiNaiyo
     * @param bnrM
     * @param kanriNo
     * @return
     */
    public List select(String sysDate, String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String taishoNengetu, String taishoJoken, String hyojiTaisho
    		, String hyojiNaiyo
    		, String bnrM
    		, String kanriNo);

}
