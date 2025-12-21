/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.urisokuregist.dao.TrnTvcmDao;
import jp.co.isid.mos.bird.config.urisokuregist.dao.TrnUsrCampDao;
import jp.co.isid.mos.bird.config.urisokuregist.dao.TrnUsrMiseCntDao;
import jp.co.isid.mos.bird.config.urisokuregist.dao.TrnUsrShohinGroupDao;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnTvcm;
import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnUsrMiseCnt;
import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnUsrShohinGroup;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrCamp;
import jp.co.isid.mos.bird.config.urisokuregist.logic.RegistLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * [機能設定]【売上速報設定】
 * 編集情報登録
 * ロジック
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = UrisokuRegistUtil.SCREEN_ID+"L04";
    
	/** DAO【売上速報レポート用店舗数】*/
	private TrnUsrMiseCntDao urisokuRegistTrnUsrMiseCntDao;
	/** DAO【TVCM放映日】*/
	private TrnTvcmDao urisokuRegistTrnTvcmDao;
	/** DAO【売上速報レポート用商品グループ】*/
	private TrnUsrShohinGroupDao urisokuRegistTrnUsrShohinGroupDao;
	/** DAO【売上速報レポート用キャンペーン】*/
	private TrnUsrCampDao urisokuRegistTrnUsrCampDao;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.logic.RegistLogic#execute(jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto)
	 */
	public void execute(SessionDto sessionDto) {
        //１．事前条件判断処理
        validate(sessionDto);
        //２．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        //３．処理対象　ユーザーID
        String userId = sessionDto.getBirdUserInfo().getUserID();
        String userPgm = LOGIC_ID.substring(0, 7);
        //４．月末店舗数設定処理を行います。
        registMiseCnt(sessionDto.getListRegistDataMiseCnt(), userId, userPgm, currentTimestamp);
        //５．TVCM放映日設定処理を行います。
        registTvcm(sessionDto.getListRegistDataTvcm(), userId, userPgm, currentTimestamp);
        //６．商品メニュー設定処理を行います。
        registShohinMenu(sessionDto.getListRegistDataMenu(), userId, userPgm, currentTimestamp);
        //７．キャンペーン設定処理を行います。
        registCamp(sessionDto.getListRegistDataCamp(), userId, userPgm, currentTimestamp, sessionDto.getTaishoYm());
	}
    /**
     * 事前条件処理
     * 
     * @param taishoYm
     * @param frameKbn
     * @throws MissingDataException
     */
    private void validate(final SessionDto sessionDto) {
        if(sessionDto == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("自画面SessionDto");
        }
    	List listRegistData = sessionDto.getListRegistData();
        if(listRegistData== null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("編集情報");
        }
    }
    /**
     * 月末店舗数設定処理
     * 
     * @param listRegist
     * @param userId
     * @param userPgm
     * @param currentTimestamp
     */
    private void registMiseCnt(List listRegist, String userId, String userPgm, Timestamp currentTimestamp) {
		// TODO 自動生成されたメソッド・スタブ
    	for(int i=0; i<listRegist.size(); i++) {
    		TrnUsrMiseCnt registEntity = (TrnUsrMiseCnt)listRegist.get(i);
            //更新ユーザー ＝　パラメーター.ユーザーID
    		registEntity.setLastUser(userId);
            //更新PGM ＝　画面ID＋"L"
    		registEntity.setLastPgm(userPgm);
    		
            if(registEntity.getFirstTmsp() == null){
                //[月末店舗数情報].登録ユーザー ＝　パラメーター.ユーザーID
            	registEntity.setFirstUser(userId);
                //[月末店舗数情報].登録PGM ＝　画面ID＋"L"
            	registEntity.setFirstPgm(userPgm);
                //[月末店舗数情報].登録TMSP ＝　処理２.タイムスタンプ
            	registEntity.setFirstTmsp(currentTimestamp);
                //[月末店舗数情報].更新TMSP ＝　処理２.タイムスタンプ
            	registEntity.setLastTmsp(currentTimestamp);
            	//DAO【売上速報レポート用店舗数】の新規登録処理の実行
            	getUrisokuRegistTrnUsrMiseCntDao().insert(registEntity);
            }else{
            	//DAO【売上速報レポート用店舗数】の更新登録処理の実行
            	getUrisokuRegistTrnUsrMiseCntDao().update(registEntity);
            }
    	}
    }
    /**
     * TVCM放映日設定処理
     * 
     * @param listRegist
     * @param userId
     * @param userPgm
     * @param currentTimestamp
     */
    private void registTvcm(List listRegist, String userId, String userPgm, Timestamp currentTimestamp) {
		// TODO 自動生成されたメソッド・スタブ
    	for(int i=0; i<listRegist.size(); i++) {
    		UILists listYm = (UILists)listRegist.get(i);
    		for(int w=0; w<listYm.getListData().size(); w++) {
    			List listWeek = (List)listYm.getListData().get(w);
        		for(int d=0; d<listWeek.size(); d++) {
		    		TrnTvcm registEntity = (TrnTvcm)listWeek.get(d);
		    		if(registEntity.getTvcmDay() == null) {
		    			continue;
		    		}
		            //更新ユーザー ＝　パラメーター.ユーザーID
		    		registEntity.setLastUser(userId);
		            //更新PGM ＝　画面ID＋"L"
		    		registEntity.setLastPgm(userPgm);
		    		if(registEntity.isSelectFlg()) {
			            if(registEntity.getFirstTmsp() == null){
			                //[TVCM放映日情報].登録ユーザー ＝　パラメーター.ユーザーID
			            	registEntity.setFirstUser(userId);
			                //[TVCM放映日情報].登録PGM ＝　画面ID＋"L"
			            	registEntity.setFirstPgm(userPgm);
			                //[TVCM放映日情報].登録TMSP ＝　処理２.タイムスタンプ
			            	registEntity.setFirstTmsp(currentTimestamp);
			                //[TVCM放映日情報].更新TMSP ＝　処理２.タイムスタンプ
			            	registEntity.setLastTmsp(currentTimestamp);
			            	//DAO【売上速報レポート用TVCM放映日】の新規登録処理の実行
			            	getUrisokuRegistTrnTvcmDao().insert(registEntity);
			            }
		    		}
		    		else if(registEntity.getFirstTmsp() != null){
		            	//DAO【売上速報レポート用TVCM放映日】の更新登録処理の実行
		    			getUrisokuRegistTrnTvcmDao().delete(registEntity);
		    		}
	    		}// end of for(int d=0; d<listWeek.size(); d++)
    		}// end of for(int w=0; w<listYm.getListData().size(); w++)
    	}//end of for(int i=0; i<listRegist.size(); i++)
    }
    /**
     * 商品メニュー設定処理
     * 
     * @param listRegist
     * @param userId
     * @param userPgm
     * @param currentTimestamp
     */
    private void registShohinMenu(List listRegist, String userId, String userPgm, Timestamp currentTimestamp) {
		// TODO 自動生成されたメソッド・スタブ
    	for(int i=0; i<listRegist.size(); i++) {
    		UILists listFrame = (UILists)listRegist.get(i);
    		for(int s=0; s<listFrame.getListData().size(); s++) {
	    		TrnUsrShohinGroup registEntity = (TrnUsrShohinGroup)listFrame.getListData().get(s);
	            //更新ユーザー ＝　パラメーター.ユーザーID
	    		registEntity.setLastUser(userId);
	            //更新PGM ＝　画面ID＋"L"
	    		registEntity.setLastPgm(userPgm);
	    		if( CommonUtil.isNull(registEntity.getDispWord())
	    				&& CommonUtil.isNull(registEntity.getMenuCd())) {
	    			registEntity.setDataKbn(UrisokuRegistUtil.DATA_KBN_COMMENT);
	    		}
	    		else if(!CommonUtil.isNull(registEntity.getMenuCd())) {
	    			registEntity.setDataKbn(UrisokuRegistUtil.DATA_KBN_MENU);
	    		}
	    		else if(!CommonUtil.isNull(registEntity.getDispWord()) ) {
	    			registEntity.setDataKbn(UrisokuRegistUtil.DATA_KBN_COMMENT);
	    		}
	            if(registEntity.getFirstTmsp() == null){
	                //[商品メニュー情報].登録ユーザー ＝　パラメーター.ユーザーID
	            	registEntity.setFirstUser(userId);
	                //[商品メニュー情報].登録PGM ＝　画面ID＋"L"
	            	registEntity.setFirstPgm(userPgm);
	                //[商品メニュー情報].登録TMSP ＝　処理２.タイムスタンプ
	            	registEntity.setFirstTmsp(currentTimestamp);
	                //[商品メニュー情報].更新TMSP ＝　処理２.タイムスタンプ
	            	registEntity.setLastTmsp(currentTimestamp);
	            	//DAO【売上速報レポート用商品メニュー】の新規登録処理の実行
	            	getUrisokuRegistTrnUsrShohinGroupDao().insert(registEntity);
	            }else{
	            	//DAO【売上速報レポート用店舗数】の更新登録処理の実行
	            	getUrisokuRegistTrnUsrShohinGroupDao().update(registEntity);
	            }
    		}// end of for(int s=0; s<listFrame.getListData().size(); s++)
    	}//end of for(int i=0; i<listRegist.size(); i++)
    }
    /**
     * キャンペーン設定処理
     * 
     * @param listRegist
     * @param userId
     * @param userPgm
     * @param currentTimestamp
     */
    private void registCamp(List listRegist, String userId, String userPgm, Timestamp currentTimestamp, String taishoYm) {
		// TODO 自動生成されたメソッド・スタブ
    	for(int i=0; i<listRegist.size(); i++) {
    		UIUsrCamp registEntity = (UIUsrCamp)listRegist.get(i);
            if(registEntity.getMenuSeqNo().equals("1")) {
	            //更新ユーザー ＝　パラメーター.ユーザーID
            	registEntity.setLastUser(userId);
	            //更新PGM ＝　画面ID＋"L"
            	registEntity.setLastPgm(userPgm);
    		
            	if(registEntity.getFirstTmsp() == null){
	                //[キャンペーン情報].売上速報年月 ＝　パラメーター.対象年月
            		registEntity.setUriSokuYm(taishoYm);
	                //[キャンペーン情報].登録ユーザー ＝　パラメーター.ユーザーID
            		registEntity.setFirstUser(userId);
	                //[キャンペーン情報].登録PGM ＝　画面ID＋"L"
            		registEntity.setFirstPgm(userPgm);
	                //[キャンペーン情報].登録TMSP ＝　処理２.タイムスタンプ
	            	registEntity.setFirstTmsp(currentTimestamp);
	                //[キャンペーン情報].更新TMSP ＝　処理２.タイムスタンプ
	            	registEntity.setLastTmsp(currentTimestamp);
	            	//DAO【売上速報レポート用キャンペーン】の新規登録処理の実行
	            	getUrisokuRegistTrnUsrCampDao().insert(registEntity);
	            }else{
	            	//DAO【売上速報レポート用キャンペーン】の更新登録処理の実行
	            	getUrisokuRegistTrnUsrCampDao().update(registEntity);
	            }
            }
    	}
    }
	/**
	 * @return urisokuRegistTrnTvcmDao を戻します。
	 */
	public TrnTvcmDao getUrisokuRegistTrnTvcmDao() {
		return urisokuRegistTrnTvcmDao;
	}
	/**
	 * @param urisokuRegistTrnTvcmDao urisokuRegistTrnTvcmDaoへ設定します。
	 */
	public void setUrisokuRegistTrnTvcmDao(TrnTvcmDao urisokuRegistTrnTvcmDao) {
		this.urisokuRegistTrnTvcmDao = urisokuRegistTrnTvcmDao;
	}
	/**
	 * @return urisokuRegistTrnUsrCampDao を戻します。
	 */
	public TrnUsrCampDao getUrisokuRegistTrnUsrCampDao() {
		return urisokuRegistTrnUsrCampDao;
	}
	/**
	 * @param urisokuRegistTrnUsrCampDao urisokuRegistTrnUsrCampDaoへ設定します。
	 */
	public void setUrisokuRegistTrnUsrCampDao(
			TrnUsrCampDao urisokuRegistTrnUsrCampDao) {
		this.urisokuRegistTrnUsrCampDao = urisokuRegistTrnUsrCampDao;
	}
	/**
	 * @return urisokuRegistTrnUsrMiseCntDao を戻します。
	 */
	public TrnUsrMiseCntDao getUrisokuRegistTrnUsrMiseCntDao() {
		return urisokuRegistTrnUsrMiseCntDao;
	}
	/**
	 * @param urisokuRegistTrnUsrMiseCntDao urisokuRegistTrnUsrMiseCntDaoへ設定します。
	 */
	public void setUrisokuRegistTrnUsrMiseCntDao(
			TrnUsrMiseCntDao urisokuRegistTrnUsrMiseCntDao) {
		this.urisokuRegistTrnUsrMiseCntDao = urisokuRegistTrnUsrMiseCntDao;
	}
	/**
	 * @return urisokuRegistTrnUsrShohinGroupDao を戻します。
	 */
	public TrnUsrShohinGroupDao getUrisokuRegistTrnUsrShohinGroupDao() {
		return urisokuRegistTrnUsrShohinGroupDao;
	}
	/**
	 * @param urisokuRegistTrnUsrShohinGroupDao urisokuRegistTrnUsrShohinGroupDaoへ設定します。
	 */
	public void setUrisokuRegistTrnUsrShohinGroupDao(
			TrnUsrShohinGroupDao urisokuRegistTrnUsrShohinGroupDao) {
		this.urisokuRegistTrnUsrShohinGroupDao = urisokuRegistTrnUsrShohinGroupDao;
	}

}
