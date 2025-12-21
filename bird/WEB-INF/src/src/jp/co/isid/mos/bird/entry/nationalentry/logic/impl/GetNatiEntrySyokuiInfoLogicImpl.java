package jp.co.isid.mos.bird.entry.nationalentry.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntrySyokuiInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntrySyokuiInfo;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntrySyokuiInfoLogic;

/**
 * 職位情報取得ロジック
 * 
 */
public class GetNatiEntrySyokuiInfoLogicImpl implements GetNatiEntrySyokuiInfoLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN015L06";

    /**
     * 職位情報DAOを取得します。
     */
    private UINatiEntrySyokuiInfoDao uiNatiEntrySyokuiInfoDao;

    /**
     * 職位情報DAOを取得します。
     * @return 職位情報DAO
     */
    public UINatiEntrySyokuiInfoDao getUINatiEntrySyokuiInfoDao() {
        return uiNatiEntrySyokuiInfoDao;
    }

    /**
     * 職位情報DAOを設定します。
     * @param natiEntrySyokuiInfoDao 職位情報DAO
     */
    public void setUINatiEntrySyokuiInfoDao(UINatiEntrySyokuiInfoDao uiNatiEntrySyokuiInfoDao) {
        this.uiNatiEntrySyokuiInfoDao = uiNatiEntrySyokuiInfoDao;
    }

    
    /**
     * 職位情報を取得
     * @return  職位情報リスト
     */
    
    public List execute() {
    	
    	List tmpResult = getUINatiEntrySyokuiInfoDao().select();
    	// 空白Ｏｐｔｉｏｎの生成
		List pullDownList = new ArrayList();
		if (tmpResult == null || tmpResult.size() == 0) {
			pullDownList.add(new SelectItem("", ""));
			return pullDownList;
        }
		for(int i = 0; i < tmpResult.size(); i++) {
			UINatiEntrySyokuiInfo natiEntrySyokuiInfo = (UINatiEntrySyokuiInfo) tmpResult.get(i);
			if(i == 0) {
				pullDownList.add(new SelectItem("", ""));
			}
			pullDownList.add(new SelectItem(natiEntrySyokuiInfo.getSyokuiCd(), natiEntrySyokuiInfo.getSyokuiName()));
		}
        return pullDownList;
    }
}
