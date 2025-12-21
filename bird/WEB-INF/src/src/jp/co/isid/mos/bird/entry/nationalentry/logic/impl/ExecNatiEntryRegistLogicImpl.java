/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.seasar.framework.exception.SQLRuntimeException;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryDutyInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryJoinInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiEntryStateDao;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryDutyInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryState;
import jp.co.isid.mos.bird.entry.nationalentry.logic.ExecNatiEntryRegistLogic;
import jp.co.isid.mos.bird.entry.nationalentry.util.CheckInputParam;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 全国大会　申込登録処理ロジック
 *
 * 更新日:2012/06/26追加 xkinu 全国大会改善対応(「備考」改行対応)
 *
 * @author xlee
 */
public class ExecNatiEntryRegistLogicImpl implements ExecNatiEntryRegistLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN015L03";

    /**
     * 責任者登録処理DAOを取得します。
     */
    private UINatiEntryDutyInfoDao uiNatiEntryDutyInfoDao;

    /**
     * 参加者登録処理DAOを取得します。
     */
    private UINatiEntryJoinInfoDao uiNatiEntryJoinInfoDao;

    /**
     * オーナー別エントリー状況DAOを取得します。
     */
    private UINatiEntryStateDao uiNatiEntryStateDao;
    /**
     * 責任者登録処理DAOを取得します。
     * @return 責任者登録処理DAO
     */
    public UINatiEntryDutyInfoDao getUINatiEntryDutyInfoDao() {
        return uiNatiEntryDutyInfoDao;
    }

    /**
     * 責任者登録処理DAOを設定します。
     * @param uiOfferDutyDao 責任者登録処理DAO
     */
    public void setUINatiEntryDutyInfoDao(UINatiEntryDutyInfoDao uiNatiEntryDutyInfoDao) {
        this.uiNatiEntryDutyInfoDao = uiNatiEntryDutyInfoDao;
    }

    /**
     * 参加者登録処理DAOを取得します。
     * @return 参加者登録処理DAO
     */
    public UINatiEntryJoinInfoDao getUINatiEntryJoinInfoDao() {
        return uiNatiEntryJoinInfoDao;
    }

    /**
     * 参加者登録処理DAOを設定します。
     * @param uiOfferDutyDao 参加者登録処理DAO
     */
    public void setUINatiEntryJoinInfoDao(UINatiEntryJoinInfoDao uiNatiEntryJoinInfoDao) {
        this.uiNatiEntryJoinInfoDao = uiNatiEntryJoinInfoDao;
    }

    /**
     * 参加者登録処理DAOを取得します。
     * @return 参加者登録処理DAO
     */
    public UINatiEntryStateDao getUINatiEntryStateDao() {
        return uiNatiEntryStateDao;
    }

    /**
     * 参加者登録処理DAOを設定します。
     * @param uiOfferDutyDao 参加者登録処理DAO
     */
    public void setUINatiEntryStateDao(UINatiEntryStateDao uiNatiEntryStateDao) {
        this.uiNatiEntryStateDao = uiNatiEntryStateDao;
    }

	/**
	 * 事業方針説明会　申込登録処理ロジック
	 *
	 * 更新日:2012/06/26追加 xkinu「備考」改行対応
	 * @param paramMap 入力データ保持マップ
	 * @return null
	 */
    public List execute(Map paramMap) {
    	//タイムスタンプ
    	Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
    	String procKbn = (String)paramMap.get(NationalEntryDto.MAPKEY_PROC);
    	UINatiEntryState uiNatiEntryState = (UINatiEntryState)paramMap.get(NationalEntryDto.MAPKEY_STATE);
    	UINatiEntryDutyInfo uiNatiEntryDutyInfo =
    		(UINatiEntryDutyInfo)paramMap.get(NationalEntryDto.MAPKEY_DUTYINFO);

    	if(procKbn.equals(NationalEntryDto.PROC_KBN_INSERT)) {
    		uiNatiEntryState.setFirstPgm(LOGIC_ID.substring(0,7));
    		uiNatiEntryState.setFirstTmsp(currentTimestamp);
    		uiNatiEntryState.setLastPgm(LOGIC_ID.substring(0,7));
    		uiNatiEntryState.setLastTmsp(currentTimestamp);

    		uiNatiEntryDutyInfo.setFirstPgm(LOGIC_ID.substring(0,7));
    		uiNatiEntryDutyInfo.setFirstTmsp(currentTimestamp);
    		uiNatiEntryDutyInfo.setLastPgm(LOGIC_ID.substring(0,7));
    		uiNatiEntryDutyInfo.setLastTmsp(currentTimestamp);

    		//１．オーナー別エントリー状況登録
    		try {
    			getUINatiEntryStateDao().insertEntryState(uiNatiEntryState);
        	} catch(SQLRuntimeException se) {
                SQLException e2 = (SQLException) se.getCause();
                if( e2.getSQLState().equals("23505") ) {
                        throw new CannotExecuteWithReasonException(
                            "このデータは既に登録されている", "登録");
                } else {
                	//重複エラー以外のエラーが発生
                	throw se;
                }
            }
    		//２．責任者情報登録
    		getUINatiEntryDutyInfoDao().insertDuty(uiNatiEntryDutyInfo);
    	} else {
    		uiNatiEntryState.setLastPgm(LOGIC_ID.substring(0,7));

    		uiNatiEntryDutyInfo.setLastPgm(LOGIC_ID.substring(0,7));
    		uiNatiEntryDutyInfo.setLastTmsp(currentTimestamp);

    		//１．オーナー別エントリー状況更新
    		getUINatiEntryStateDao().updateEntryState(uiNatiEntryState);

    		//２．責任者情報更新
    		getUINatiEntryDutyInfoDao().updateDuty(uiNatiEntryDutyInfo);
    	}
    	int cnt = 1;
    	//参加者
    	List joinInfoList = (List)paramMap.get(NationalEntryDto.MAPKEY_JOININFO);
    	for(int i = 0; i < joinInfoList.size(); i++) {
    		UINatiEntryJoinInfo uiNatiEntryJoinInfo = (UINatiEntryJoinInfo)joinInfoList.get(i);
    		uiNatiEntryJoinInfo.setOptional17("");
    		uiNatiEntryJoinInfo.setOptional18("");
    		uiNatiEntryJoinInfo.setOptional19("");
    		uiNatiEntryJoinInfo.setOptional20("");
    		//３．責任者が参加の場合
    		if(uiNatiEntryState.getEntryFlg().equals("1")) {
    			//削除、
				if(i == 0) {
    				getUINatiEntryJoinInfoDao().deleteJoin(
						uiNatiEntryJoinInfo.getEntryCd(),
						uiNatiEntryJoinInfo.getEntryYear(),
						uiNatiEntryJoinInfo.getEntryKai(),
						uiNatiEntryJoinInfo.getCompanyCd(),
						uiNatiEntryJoinInfo.getOnerCd());
				}
				if(!CheckInputParam.chkAllJoinInfo(uiNatiEntryJoinInfo)) {
					String nSeqNo = "";
					if(cnt < 10) {
						nSeqNo = "0" + cnt;
					} else {
						nSeqNo = String.valueOf(cnt);
					}
					uiNatiEntryJoinInfo.setSeqNo(nSeqNo);

					//2012/06/26追加「備考」改行対応
					uiNatiEntryJoinInfo.setNote(
							CommonUtil.changeEnterWordJSFtoDB(uiNatiEntryJoinInfo.getNote()));
					uiNatiEntryJoinInfo.setNote1(
							CommonUtil.changeEnterWordJSFtoDB(uiNatiEntryJoinInfo.getNote1()));

					if(procKbn.equals(NationalEntryDto.PROC_KBN_INSERT)) {
		    				uiNatiEntryJoinInfo.setFirstPgm(LOGIC_ID.substring(0,7));
		    				uiNatiEntryJoinInfo.setFirstTmsp(currentTimestamp);
		    				uiNatiEntryJoinInfo.setLastPgm(LOGIC_ID.substring(0,7));
		    				uiNatiEntryJoinInfo.setLastTmsp(currentTimestamp);
					} else if(procKbn.equals(NationalEntryDto.PROC_KBN_UPDATE)) {
						if(CommonUtil.isNull(uiNatiEntryJoinInfo.getFirstPgm())) {
		    				uiNatiEntryJoinInfo.setFirstPgm(LOGIC_ID.substring(0,7));
		    				uiNatiEntryJoinInfo.setFirstTmsp(currentTimestamp);
						} else {
		    				uiNatiEntryJoinInfo.setFirstPgm(uiNatiEntryJoinInfo.getFirstPgm());
		    				uiNatiEntryJoinInfo.setFirstTmsp(uiNatiEntryJoinInfo.getFirstTmsp());
						}
	    				uiNatiEntryJoinInfo.setLastPgm(LOGIC_ID.substring(0,7));
	    				uiNatiEntryJoinInfo.setLastTmsp(currentTimestamp);
					}
					getUINatiEntryJoinInfoDao().insertJoin(uiNatiEntryJoinInfo);
					cnt++;
				}
    		} else {
    			//４．責任者が不参加の場合
    			if(procKbn.equals(NationalEntryDto.PROC_KBN_UPDATE)) {
    				getUINatiEntryJoinInfoDao().deleteJoin(
    						uiNatiEntryJoinInfo.getEntryCd(),
    						uiNatiEntryJoinInfo.getEntryYear(),
    						uiNatiEntryJoinInfo.getEntryKai(),
    						uiNatiEntryJoinInfo.getCompanyCd(),
    						uiNatiEntryJoinInfo.getOnerCd());
    			}
    		}
    	}
		return null;
    }
}
