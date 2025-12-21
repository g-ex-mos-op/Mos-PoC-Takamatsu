/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.util.List;
import java.util.ArrayList;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIVansShohinInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansSokoInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIKanrimotoInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchVansShohinLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchVansSokoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 代表商品リスト取得ロジック
 * @author narita
 */
public class SearchVansShohinLogicImpl implements SearchVansShohinLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L02";
    
	/** バンズ倉庫別登録 代表商品情報取得DAO */
    private UIVansShohinInfoDao uIVansShohinInfoDao;
    
    /** バンズ倉庫別登録 倉庫リスト取得ロジック */
    private SearchVansSokoLogic searchVansSokoLogic;

    /**
     * 代表商品情報のリストを取得する
     * @param kanriMoto 管理元コード
     * @param kanriList 対象倉庫リスト
     * @return shohinList  代表商品情報のリスト
     */
    public List execute(String kanriMoto,List kanriList) {

    	List shohinList = new ArrayList();
    	List kanriMotoList = new ArrayList();
    	UIVansSokoInfo uIVansSokoInfo = new UIVansSokoInfo();
    	
    	if(kanriMoto.equals("0")){
    		// 全ての対象倉庫を管理元別に検索
        	for(int i = 1;i < kanriList.size(); i++){
        		// 対象倉庫情報の取得
        		uIVansSokoInfo = (UIVansSokoInfo)kanriList.get(i);
        		// 商品リストの検索
        		shohinList = getUIVansShohinInfoDao().getVansShohinList(uIVansSokoInfo.getKanriMoto());
        		// 管理元情報の設定
        		UIKanrimotoInfo uIKanrimotoInfo = new UIKanrimotoInfo();
        		uIKanrimotoInfo.setShohinList(shohinList);
        		uIKanrimotoInfo.setKanriMotoCd(uIVansSokoInfo.getKanriMoto());
        		uIKanrimotoInfo.setKanriMotoName(
        				getSearchVansSokoLogic().getKanriMotoKj(kanriList,uIVansSokoInfo.getKanriMoto()));
        		
        		if (shohinList == null || shohinList.size() == 0) {
            		throw new NotExistException("対象倉庫"); //E0103 代表商品
                }else{
                	kanriMotoList.add(uIKanrimotoInfo);
                }
        	}
    		
    	}else{
    		// 選択した対象倉庫の商品リストを検索
    		shohinList = getUIVansShohinInfoDao().getVansShohinList(kanriMoto);
    		// 管理元情報の設定
    		UIKanrimotoInfo uIKanrimotoInfo = new UIKanrimotoInfo();
    		uIKanrimotoInfo.setShohinList(shohinList);
    		uIKanrimotoInfo.setKanriMotoCd(kanriMoto);
    		uIKanrimotoInfo.setKanriMotoName(
    				getSearchVansSokoLogic().getKanriMotoKj(kanriList,kanriMoto));
    		
        	if (shohinList == null || shohinList.size() == 0) {
        		throw new NotExistException("対象倉庫"); //E0103 代表商品
            }else{
            	kanriMotoList.add(uIKanrimotoInfo);
            }
    	}
    	    	
    	return kanriMotoList;
    }

	public UIVansShohinInfoDao getUIVansShohinInfoDao() {
		return uIVansShohinInfoDao;
	}

	public void setUIVansShohinInfoDao(UIVansShohinInfoDao vansShohinInfoDao) {
		uIVansShohinInfoDao = vansShohinInfoDao;
	}

	public SearchVansSokoLogic getSearchVansSokoLogic() {
		return searchVansSokoLogic;
	}

	public void setSearchVansSokoLogic(SearchVansSokoLogic searchVansSokoLogic) {
		this.searchVansSokoLogic = searchVansSokoLogic;
	}
}
