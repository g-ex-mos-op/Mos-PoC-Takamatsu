package jp.co.isid.mos.bird.commonform.svsearchnew.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.svsearchnew.common.SvSearchNewConst;
import jp.co.isid.mos.bird.commonform.svsearchnew.dao.UISvDao;
import jp.co.isid.mos.bird.commonform.svsearchnew.entity.UISv;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.GetSvInfoLogic;

/**
 * SV検索処理ロジック
 * @author kusama
 */
public class GetSvInfoLogicImpl implements GetSvInfoLogic {

    public static final String LOGIC_ID = SvSearchNewConst.VIEW_ID + "L04";
    public static final String DATA_KEY_SVENTITY = "SVINFO";
    public static final String DATA_KEY_SVSIBU = "SVSIBU";
    
    /* UISvDao */
    private UISvDao newUISvDao;

    /**
     * SV検索Daoを取得します。
     * @return uiSvDao
     */
    public UISvDao getUISvDao() {
        return newUISvDao;
    }

    /**
     * SV検索Daoを設定します。
     * @param uiSvDao
     */
    public void setUISvDao(UISvDao uiSvDao) {
        this.newUISvDao = uiSvDao;
    }

    /**
     * SV検索処理
     */
	public Map execute(String companyCd, String svCd) {
        Map map = new HashMap();
        // SV名称を取得
        UISv svEntity = getUISvDao().selectSvInfo(companyCd, svCd);
        svEntity.setCompanyCd(companyCd);
        map.put(DATA_KEY_SVENTITY, svEntity);
 
        // SV担当支部を取得し、支部コードの一覧を作成する
        List listSibu = getUISvDao().selectSvSibu(companyCd, svCd);
        List sibu = new ArrayList();
        if (listSibu != null) {
            for (Iterator ite = listSibu.iterator(); ite.hasNext();) {
                UISv entity = (UISv) ite.next();
                sibu.add(entity.getSibuCd());
            }
        }
        map.put(DATA_KEY_SVSIBU, sibu);
        
        return map;
	}
}