/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dao.UIMiseBlockInfoDao;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.RegistLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * ブロックメンテナンス
 * データ登録ロジック
 * 
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BlockMaintenanceUtil.LOGIC_ID_REGIST;
    /** DAO【店舗ブロック設定情報】*/
    private UIMiseBlockInfoDao blockMainteUIMiseBlockInfoDao;
    
    /**
     * 事前条件処理
     * @param map
     * @throws MissingDataException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        List regList = (List)params.get(PK_LIST_REG);
        if(regList == null || regList.size() == 0){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("登録対象データ");
        }
    }
	/**
	 * 実行処理
     * 
     * @param params
     * @return
	 */
	public void execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        List regList = (List)params.get(PK_LIST_REG);
        //処理対象　ユーザーID
        String userId = userInfo.getUserID();
        String userPgm = LOGIC_ID.substring(0, 7);
        //３．登録データ件数分下記の処理を行う。
        for (int i=0; i<regList.size(); i++){
        	//
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)regList.get(i);
        	//1.[登録データ].移動先ブロックコードがNullまたは空でない場合、
        	//  以下の処理を行う。
        	if(!BlockMaintenanceUtil.isNull(entity.getMoveBlockCd())) {
        		//1-1.新しく[店舗ブロック設定情報]をインスタンス化する。
        		//　　以下の値を設定する。
        		UIMiseBlockInfo regData = new UIMiseBlockInfo();
        		//管理会社企業コード
        		regData.setCompanyCd(entity.getCompanyCd());
        		//店コード
        		regData.setMiseCd(entity.getMiseCd());
        		//ブロックコード
        		regData.setBlockCd(entity.getMoveBlockCd());
        		//支部クコード
        		regData.setSibuCd(entity.getSibuCd());
	            //更新ユーザー ＝　パラメーター.ユーザーID
        		regData.setLastUser(userId);
	            //更新PGM ＝　画面ID＋"L"
        		regData.setLastPgm(userPgm);
        		
	            if(entity.getFirstTmsp() == null){
	                //[店舗ブロック設定情報].登録ユーザー ＝　パラメーター.ユーザーID
	            	regData.setFirstUser(userId);
	                //[店舗ブロック設定情報].登録PGM ＝　画面ID＋"L"
	            	regData.setFirstPgm(userPgm);
	                //[店舗ブロック設定情報].登録TMSP ＝　処理２.タイムスタンプ
	            	regData.setFirstTmsp(currentTimestamp);
	                //[店舗ブロック設定情報].更新TMSP ＝　処理２.タイムスタンプ
	            	regData.setLastTmsp(currentTimestamp);
	            	//新規登録処理の実行
	                getBlockMainteUIMiseBlockInfoDao().insert(regData);
	            }else{
	                //[店舗ブロック設定情報].登録ユーザー ＝　[登録データ].ユーザーID
	            	regData.setFirstUser(entity.getFirstUser());
	                //[店舗ブロック設定情報].登録PGM ＝　[登録データ].登録PGM
	            	regData.setFirstPgm(entity.getFirstPgm());
	                //[店舗ブロック設定情報].登録TMSP ＝　[登録データ].登録TMSP
	            	regData.setFirstTmsp(entity.getFirstTmsp());
	                //[店舗ブロック設定情報].更新TMSP ＝　[登録データ].更新TMSP
	            	regData.setLastTmsp(entity.getLastTmsp());
	            	//更新登録処理の実行
	            	getBlockMainteUIMiseBlockInfoDao().update(regData);
	            }
        	}
        }
	}
	/**
	 * @return blockMainteUIMiseBlockInfoDao を戻します。
	 */
	public UIMiseBlockInfoDao getBlockMainteUIMiseBlockInfoDao() {
		return blockMainteUIMiseBlockInfoDao;
	}
	/**
	 * @param blockMainteUIMiseBlockInfoDao 設定する blockMainteUIMiseBlockInfoDao。
	 */
	public void setBlockMainteUIMiseBlockInfoDao(
			UIMiseBlockInfoDao blockMainteUIMiseBlockInfoDao) {
		this.blockMainteUIMiseBlockInfoDao = blockMainteUIMiseBlockInfoDao;
	}

}
