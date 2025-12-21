/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bill.common.dto.SeikyuBunruiKigouDto;
import jp.co.isid.mos.bird.bill.itemtotal.dao.UIBunruiInfoDao;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIBunruiInfo;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetBunruiInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 * 請求書分類報取得ロジック
 * 
 * @author xlee
 */
public class GetBunruiInfoLogicImpl implements GetBunruiInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L03";

    /**
     * 請求書分類情報DAOを取得します。
     */
    private UIBunruiInfoDao uiBunruiInfoDao;

    /**
     * 請求書分類情報DAOを取得します。
     * @return 分類情報DAO
     */
    public UIBunruiInfoDao getUIBunruiInfoDao() {
        return uiBunruiInfoDao;
    }

    /**
     * 請求書分類情報DAOを設定します。
     * @param uiBunruiInfoDao 分類情報DAO
     */
    public void setUIBunruiInfoDao(UIBunruiInfoDao uiBunruiInfoDao) {
        this.uiBunruiInfoDao = uiBunruiInfoDao;
    }

    /**　請求書分類記号シート　*/
    private List seikyuBunruiList;
    
    /**
     * 請求書分類記号を取得します。
     * @return 請求書分類記号
     */
    public List getSeikyuBunruiList() {
        return seikyuBunruiList;
    }
    
    /**
     * 請求書分類記号を設定します。
     * @param codSeikyuBnruiDao 請求書分類記号
     */
    public void setSeikyuBunruiList(List seikyuBunruiList) {
        this.seikyuBunruiList = seikyuBunruiList;
    }

    /**
     * 請求書分類情報を取得
     * @param　shobetuGoukeiDto　商品別合計DTO
     * @return 請求書分類情報リスト
     */
    public List execute(ShobetuGoukeiDto shobetuGoukeiDto) {

    	List tmpResult = getUIBunruiInfoDao().getBunruiInfo();
        
    	if (tmpResult == null || tmpResult.size() == 0) {
            throw new NoResultException(); //E0103 分類情報
        }
        
        //請求書分類記号シートから取得した内容の保持用
        List newBunruiInfoList = new ArrayList();
        
        //記号・ソートデータ取得
        for (Iterator it = tmpResult.iterator(); it.hasNext();) {
        	        	
        	UIBunruiInfo uiBunruiInfo = (UIBunruiInfo) it.next();
        	
        	uiBunruiInfo  = getBunruiInfo(uiBunruiInfo, shobetuGoukeiDto);
        	
        	if(uiBunruiInfo.getSeikyuBnrui() != null) {
        		newBunruiInfoList.add(uiBunruiInfo);
        	}
        }
        
        //生成されたリストソートする
        Collections.sort(newBunruiInfoList, new SortComparator());
        
        //ソートされたリストの最後に【総合計】を追加
        UIBunruiInfo uiBunruiInfo = new UIBunruiInfo();
        uiBunruiInfo.setSeikyuBnrui("");
        uiBunruiInfo.setSeBnrName("総合計");
        uiBunruiInfo.setSeikyuBunruiKigou("総合計");
        uiBunruiInfo.setSeikyuBunruiSeq("");
        newBunruiInfoList.add(uiBunruiInfo);

        return newBunruiInfoList;
    }
    
    /**
     * 請求書分類記号シートから請求分類情報を取得
     * 
     * @param uiBunruiInfo 分類情報
     * @param　shobetuGoukeiDto 商品別合計DTO
     * @return 請求書分類記号シートから取得した記号・ソート情報
     */
    private UIBunruiInfo getBunruiInfo(UIBunruiInfo uiBunruiInfo, ShobetuGoukeiDto shobetuGoukeiDto) {
    	
    	//DTOに設定されている情報を取得
    	List seikyuBunruiList = getSeikyuBunruiList();
    	
    	for(int i = 0; i < seikyuBunruiList.size(); i++) {
    		
    		SeikyuBunruiKigouDto seikyuBunruiKigouDto = (SeikyuBunruiKigouDto) seikyuBunruiList.get(i);
	    	
    		if(seikyuBunruiKigouDto.getBunrui().equals(uiBunruiInfo.getSeikyuBnrui())) {
    			if(seikyuBunruiKigouDto.getKigou().equals("")) {
    				uiBunruiInfo.setSeikyuBnrui(null);
    			} else {
    				uiBunruiInfo.setSeikyuBunruiKigou(seikyuBunruiKigouDto.getKigou());
    				uiBunruiInfo.setSeikyuBunruiSeq(seikyuBunruiKigouDto.getSort());
    			}
    		}
    	}
    	return uiBunruiInfo;
    	
    }

    /**
     * 請求分類情報をソート順でソートする
     */    
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((UIBunruiInfo) obj1).getSeikyuBunruiSeq();
            
            String val2 = ((UIBunruiInfo) obj2).getSeikyuBunruiSeq();
            
            return val1.compareTo(val2);
        }
    }
}
