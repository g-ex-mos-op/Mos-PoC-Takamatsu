package jp.co.isid.mos.bird.analysis.posdata.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.analysis.posdata.dao.UIPosDataInfoDao;
import jp.co.isid.mos.bird.analysis.posdata.entity.UIPosDataInfo;
import jp.co.isid.mos.bird.analysis.posdata.common.PosDataConstants;
import jp.co.isid.mos.bird.analysis.posdata.logic.PosDataFormLogic;
import jp.co.isid.mos.bird.analysis.posdata.util.dataSiteiUtil;

import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

public class PosDataFormLogicImpl implements PosDataFormLogic {
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BDT001L01";
    
    private UIPosDataInfoDao uIPosDataInfoDao;  
            
    /**
     * POS集信データの取得
     * @param paramMap
     * ユーザーID：ONER_ID
     * ユーザータイプ：USER_TYPE
     * 集信データ日：DATA_DT
     */
    public List execute(Map paramMap) {
        
    	//検索条件の取得
    	String onerId = (String)paramMap.get( PosDataConstants.MAP_ONER_ID );
    	String dataDt = (String)paramMap.get( PosDataConstants.MAP_DATA_DT );
    	String companyCd = PosDataConstants.DEFORT_COMPANY_CD;
                
        List posDataList = new ArrayList();
             	
        //POS集信データを取得
        posDataList = getUIPosDataInfoDao().getPosDataList(companyCd,onerId,dataDt);
    
        // データが０件の場合は結果無し
        if ( posDataList == null || posDataList.size() == 0 ) {
        	throw new NoResultException();
        }
        
        return posDataList;
    }
    
    /**
     * ダウンロードリストの作成。
     * @param downloadList
     * @return
     */
    public List addDawnloadList(List posDataList){
    	
    	//画面表示用のリスト
    	List downloadList = new ArrayList();
    	UIPosDataInfo posData = new UIPosDataInfo();
    	
        for(int i = 0 ; i < posDataList.size() ; i++){
    	
        	//検索結果リストからi番目のレコードを取り出す。
        	posData = (UIPosDataInfo) posDataList.get(i);
        	String dataInfo = posData.getData();
        	
        	//DATA内容をリストに追加する。        	        	
        	downloadList.add( this.posDataByteCheck(dataInfo) );
    	}
		
    	return downloadList;
    }
    
    /**
     * DATA内容の追加。
     * @param downloadList
     * @return
     */
    public String posDataByteCheck(String dataInfo){
    	
    	// 最大バイト数512以下の場合は、空文字1バイトで埋める。
//---2006/12/29 update kusama 文字列の長さは文字列数ではなくバイト数で比較する
//    	if(dataInfo.length() < PosDataConstants.MAX_BYTE){
        if (dataInfo == null) {
            dataInfo = "";
        }
        if(dataInfo.getBytes().length < PosDataConstants.MAX_BYTE){
//---2006/12/29 update end
    		String byteData = PosDataConstants.EMPTY;
//---2006/12/29 update kusama 文字列の長さは文字列数ではなくバイト数で比較する
//    		int len = dataInfo.length();
            int len = dataInfo.getBytes().length;
//---2006/12/29 update end
    		for(int i = len ; i < PosDataConstants.MAX_BYTE ; i++){
    			//空白１バイトを追加
    			byteData = byteData + PosDataConstants.ONE_BYTE;
    		}
    		dataInfo = dataInfo + byteData;
    	}
		
    	return dataInfo;
    }
        
    /**
     * 条件部情報を取得する
     * @param userType	ユーザタイプ
     * @param userId	ユーザID
     * @param appDate	アプリ日付
     * @return Map     条件部情報
     */
    public Map execute(String userType,String appDate) {
		
    	Map map = new HashMap();
		 
    	// ユーザータイプが店舗の場合
		if (!UserType.HONBU.equals(userType) && !UserType.ONER.equals(userType)) {
	    	// ユーザータイプが本部又はオーナーでない場合、権限エラー
	    	throw new CannotAccessException();
		}
    	
        // 集信データ日(yyyy/MM/dd)リスト取得    
        List dataYmdList = dataSiteiUtil.getDateDayList(appDate); 
		// 取得したリストをMapへ格納
        map.put(PosDataConstants.MAP_DATA_DT_LIST, dataYmdList);
 
        return map;
		
	}
    
    /**
     * オーナーコードの取得
     */
    public String getOnerId(List onerList,String companyCd){
        boolean onerExistFlg = false;

         UIUserOner oner = new UIUserOner();
         
         for ( int i = 0; i < onerList.size(); i++ ) {
             oner = (UIUserOner)onerList.get(i);
             if ( companyCd.equals( oner.getCompanyCd() ) ) {
                 onerExistFlg = true;
                 break;
             }
         }
         if ( !onerExistFlg ) {
             throw new NotExistException("オーナー");
         }
         
         return oner.getOnerCd();
    }
    
	public UIPosDataInfoDao getUIPosDataInfoDao() {
		return uIPosDataInfoDao;
	}

	public void setUIPosDataInfoDao(UIPosDataInfoDao posDataInfoDao) {
		uIPosDataInfoDao = posDataInfoDao;
	}
}
