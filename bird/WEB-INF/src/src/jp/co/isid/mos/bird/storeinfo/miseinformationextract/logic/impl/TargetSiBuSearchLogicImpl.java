package jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.SiBuJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.SiBuJoho;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic.TargetSiBuSearchLogic;

/**
 * 店舗グループ（支部）の検索ロジック
 * @author xayumi
 */
public class TargetSiBuSearchLogicImpl implements TargetSiBuSearchLogic{
    /* ロジックID */
    public static final String LOGIC_ID = "BSI008L01";
    /*【DAO】*/
    private SiBuJohoDao siBuJohoDao;

    /**
     * 店舗グループ（支部）の検索を行う
     * @param String shukeKubu 集計区分
     * @return List
     * @exception ApplicationException
     */
	public List execute(String shukeKubu) throws ApplicationException {
		List sibuList =  getSiBuJohoDao().select(shukeKubu);

        // 検索結果が０件の場合
        if (sibuList == null || sibuList.isEmpty()) {
            throw new NotExistException(BizReportConstants.MSG_TAISHO_SIBU);
        }

        // プルダウンリスト作成
        List returnList = new ArrayList();
        SelectItem itemAll = new SelectItem(
                "All",
                "すべて");
        returnList.add(itemAll);
        for (int i = 0; i < sibuList.size(); i++) {
        	SiBuJoho sibu = (SiBuJoho) sibuList.get(i);
            SelectItem item = new SelectItem(
                sibu.getSibuCd().trim(),
                sibu.getSibuName().trim());
            returnList.add(item);
        }
        return returnList;
	}

	/**
	 * @return siBuJohoDao
	 */
	public SiBuJohoDao getSiBuJohoDao() {
		return siBuJohoDao;
	}

	/**
	 * @param siBuJohoDao セットする siBuJohoDao
	 */
	public void setSiBuJohoDao(SiBuJohoDao siBuJohoDao) {
		this.siBuJohoDao = siBuJohoDao;
	}


}
