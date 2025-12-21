/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.dao;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;

/**
 * î•ñ(UIPointDao)
 * @author yushuncheng
 *
 */
public interface UIPointDao {

	public static final Class  BEAN = UIPointMst.class;

    public static final String getPointInfo_ARGS = "tourokuNo";
    public static final String insertHuyp_ARGS = "uiPointMst,kihonPoint";
    public static final String getMaxSeq_ARGS = "tourokuNo";
    public static final String updatePoint_ARGS = "tourokuNo,dataVer,tourokuSeq,userId,nendo";
    public static final String checkNendoExist_ARGS = "tourokuNo,maxNendo,minNendo";

	/**
     * ‰ïĞ“™‹‰ƒŠƒXƒg‚ğæ“¾‚·‚é(getPointInfo)
     * @return UIPointMst ŒŸõŒ‹‰Ê
     */
    public List<UIPointMst> getPointInfo(String tourokuNo);

    public void insertHuyp(UIPointMst uiPointMst,BigDecimal kihonPoint);

    public void updatePoint(String tourokuNo,String dataVer,String tourokuSeq,String userId,String nendo);

    public String getMaxSeq(String tourokuNo);

    public int checkNendoExist(String tourokuNo,String maxNendo,String minNendo);

    public UIPointMst getMaxTmsp(String tourokuNo);

}
