/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIVansSokoInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchVansSokoLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansSokoInfo;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 対象倉庫リスト取得ロジック
 * @author narita
 */
public class SearchVansSokoLogicImpl implements SearchVansSokoLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L01";
    
	/** バンズ倉庫別登録 対象倉庫情報取得DAO */
    private UIVansSokoInfoDao uIVansSokoInfoDao;

    /**
     * 対象倉庫情報のリストを取得する
     * @return sokoList 対象倉庫情報のリスト
     */
    public List execute() {

    	List sokoList = getUIVansSokoInfoDao().getVansSokoList();
    	
    	if (sokoList == null || sokoList.size() == 0) {
    		throw new NotExistException("対象倉庫"); //E0103 対象倉庫
        }
    	
    	// 全倉庫のプルダウンデータを追加
    	UIVansSokoInfo allSoko = new UIVansSokoInfo();
    	allSoko.setKanriMoto("0");
    	allSoko.setSokoNameKj(VansmastRegistConstants.ALL_SOKO_KJ);
    	sokoList.add(0,allSoko);
    	
    	return sokoList;
    }
    
    /**
     * 対象倉庫情報のリストから管理元名称を取得する
     * @param sokoList 対象倉庫情報のリスト
     * @param kanriMoto 管理元コード
     * @return kanriMotoKj 管理元名称
     */
    public String getKanriMotoKj(List sokoList, String kanriMoto) {
    	
    	String kanriMotoKj = "";
    	
    	for(int i = 0;i < sokoList.size(); i++){
            
    		UIVansSokoInfo sokoInfo = (UIVansSokoInfo)sokoList.get(i);
    		
    		if(sokoInfo.getKanriMoto().equals(kanriMoto)){
    			return sokoInfo.getSokoNameKj();
    		}
    	}
    	
    	return kanriMotoKj;
    }

	public UIVansSokoInfoDao getUIVansSokoInfoDao() {
		return uIVansSokoInfoDao;
	}

	public void setUIVansSokoInfoDao(UIVansSokoInfoDao vansSokoInfoDao) {
		uIVansSokoInfoDao = vansSokoInfoDao;
	}
}
