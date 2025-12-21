/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.config.zenurikakuteiregist.dao.TrnUsrStatusDao;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.entity.TrnUsrStatus;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.RegistLogic;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.util.ZenurikakuteiRegistUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;


/**
 * [機能設定]【売上速報前月売上確定登録】
 * 編集情報の登録
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ZenurikakuteiRegistUtil.SCREEN_ID+"L02";
	/**
	 * DAO【売上速報レポート確定状況】
	 */
	private TrnUsrStatusDao zenurikakuteiRegistTrnUsrStatusDao;

    /**
	 * @return zenurikakuteiRegistTrnUsrStatusDao を戻します。
	 */
	public TrnUsrStatusDao getZenurikakuteiRegistTrnUsrStatusDao() {
		return zenurikakuteiRegistTrnUsrStatusDao;
	}
	/**
	 * @param zenurikakuteiRegistTrnUsrStatusDao zenurikakuteiRegistTrnUsrStatusDaoへ設定します。
	 */
	public void setZenurikakuteiRegistTrnUsrStatusDao(
			TrnUsrStatusDao zenurikakuteiRegistTrnUsrStatusDao) {
		this.zenurikakuteiRegistTrnUsrStatusDao = zenurikakuteiRegistTrnUsrStatusDao;
	}
	/**
     * 事前条件処理
     * @param sysDate
     * @throws MissingDataException
     */
    private void validate(SessionDto sessionDto) {
    	List listRegistData = sessionDto.getListRegistData();
        if(listRegistData== null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("編集情報");
        }
    }
	/**
	 * 実行処理
	 * 
	 * @param sessionDto DTO【Session】
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.RegistLogic#execute(java.util.Map)
	 */
	public void execute(SessionDto sessionDto) {
		//１．事前条件処理
		validate(sessionDto);
		//２．DTO【自画面Session】.List[[編集情報]]を取得します。
    	List listRegistData = sessionDto.getListRegistData();
    	//３．for文でList[[編集情報]]の件数分、下記の処理を行います。
        for(int i=0; i<listRegistData.size(); i++) {
        	//for-1.List[[編集情報]]から現行インデックスの[編集情報]を取得します。
        	TrnUsrStatus registEntity = (TrnUsrStatus)listRegistData.get(i);
        	//for-2.[編集情報].編集可能判断フラグがtrueの場合下記の処理を行います。
        	if(registEntity.isRegistFlg()) {
                //①．タイムスタンプを生成する。
                Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
                //②．下記の値を設定します。
                String userId = sessionDto.getBirdUserInfo().getUserID();
                String userPgm = LOGIC_ID.substring(0, 7);
                //[売上速報レポート確定状況].更新ユーザー ＝　パラメーター.DTO【自画面Session】.ユーザー情報.ユーザーID
        		registEntity.setLastUser(userId);
                //[売上速報レポート確定状況].更新PGM ＝　画面ID＋"L"
        		registEntity.setLastPgm(userPgm);
                //[売上速報レポート確定状況].確定処理フラグへ
        		//[売上速報レポート確定状況].ステータスフラグの値を設定します。
        		registEntity.setKakuteiFlg(registEntity.getStatusFlg());
        		//③．[売上速報レポート確定状況].登録TMSPがnullの場合下記の処理を行います。
                if(registEntity.getFirstTmsp() == null){
                    //③-1.下記の値を設定します。
                	//[売上速報レポート確定状況].登録ユーザー ＝　パラメーター.DTO【自画面Session】.ユーザー情報.ユーザーID
                	registEntity.setFirstUser(userId);
                    //[売上速報レポート確定状況].登録PGM ＝　画面ID＋"L"
                	registEntity.setFirstPgm(userPgm);
                    //[売上速報レポート確定状況].登録TMSP ＝　処理２.タイムスタンプ
                	registEntity.setFirstTmsp(currentTimestamp);
                    //[売上速報レポート確定状況].更新TMSP ＝　処理２.タイムスタンプ
                	registEntity.setLastTmsp(currentTimestamp);
                	//③-2.新規登録処理の実行
                	getZenurikakuteiRegistTrnUsrStatusDao().insert(registEntity);
                }
        		//④．[売上速報レポート確定状況].登録TMSPがnullでなく、
                //    [売上速報レポート確定状況].ステータスフラグ＝”確定”場合下記の処理を行います。
                else if(ZenurikakuteiRegistUtil.STATUS_KAKUTEI.equals(registEntity.getStatusFlg())){
                	//④-1.更新登録処理の実行
                	getZenurikakuteiRegistTrnUsrStatusDao().update(registEntity);
                }
                break;
        	}
        }
	}

}
