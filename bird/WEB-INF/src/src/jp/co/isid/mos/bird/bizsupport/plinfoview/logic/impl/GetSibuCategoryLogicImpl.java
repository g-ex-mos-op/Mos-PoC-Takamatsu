/*
 * 作成日: 2006/04/04
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.CodBlockDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.MSTSibuCategoryInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetSibuCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 条件画面のプルダウン用の情報取得ロジック インターフェイス
 * @author xnkusama
 */
public class GetSibuCategoryLogicImpl implements GetSibuCategoryLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS006L02";
    
    private MSTSibuCategoryInfoDao mstSibuCategoryInfoDao;
    private CodBlockDao codBlockDao;
    
    /**
     * 条件画面のプルダウン用の情報取得
     * @param String 企業コード
     * @return List 支部データ
     * @exception ApplicationException
     */
    public Map execute(String companyCd, String userId, boolean isLimit) throws ApplicationException {
        validate(companyCd);
        
        Map mapPulldownInfo = new HashMap();
        
        //支部情報
        List listSibu = getMstSibuCategoryInfoDao().getSibuInfo(companyCd, userId, isLimit);
        mapPulldownInfo.put("sibu", listSibu);
        //事業本部
        List listJigyou = getMstSibuCategoryInfoDao().getJigyouInfo(companyCd, userId, isLimit);
        mapPulldownInfo.put("jigyou", listJigyou);
        //営業エリア
        List listSlarea = getMstSibuCategoryInfoDao().getSlareaInfo(companyCd, userId, isLimit);
        mapPulldownInfo.put("slarea", listSlarea);
        //ブロック
        List listBlock = getCodBlockDao().getBlock();
        mapPulldownInfo.put("block", listBlock);
        
        return mapPulldownInfo;
    }
    
    private void validate(String companyCd) {
        if (isNull(companyCd)) {
            throw new NotNullException("企業コード");
        }
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
	public MSTSibuCategoryInfoDao getMstSibuCategoryInfoDao() {
		return mstSibuCategoryInfoDao;
	}
	public void setMstSibuCategoryInfoDao(
			MSTSibuCategoryInfoDao mstSibuCategoryInfoDao) {
		this.mstSibuCategoryInfoDao = mstSibuCategoryInfoDao;
	}
	public CodBlockDao getCodBlockDao() {
		return codBlockDao;
	}
	public void setCodBlockDao(CodBlockDao codBlockDao) {
		this.codBlockDao = codBlockDao;
	}
}