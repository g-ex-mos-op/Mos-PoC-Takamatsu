package jp.co.isid.mos.bird.bizsupport.pointmstregist.dao;

/**
 *
 */


import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;

/**
 * 情報(UISeidoPointDao)
 * @author yushuncheng
 *
 */
public interface UISeidoDao {

	public static final Class  BEAN = UISeidoMst.class;

	public static final String insertSeid_ARGS = "urSeidoMst,gRendoKeisu,gRendoKeisuYakuin";

	public static final String updateSeid_ARGS = "tourokuNo,dataVer,userId";

	public static final String getSeidoInfo_ARGS = "tourokuNo,dataVer";

    /**
     * 新規登録処理
     *
     * @param entity　株式報酬制度名称エンティティ
     * @return
     */
    public void insertSeid(UISeidoMst urSeidoMst,BigDecimal gRendoKeisu,BigDecimal gRendoKeisuYakuin);

    public void updateSeid(String tourokuNo,String dataVer,String userId);

    public String getMaxtourNo();

    public UISeidoMst getSeidoInfo(String tourokuNo,String dataVer);

}
