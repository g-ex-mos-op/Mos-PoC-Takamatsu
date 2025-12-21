/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.dao.MstRealTimeScheduleDao;
import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.MstRealTimeSchedule;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.RegistLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 登録ロジック
 * 
 * Ｐ６対応仕様
 * テーブルBR82RTSM(店舗別リアルタイム集信期間マスタ)へ登録処理を行います。
 * 
 * 作成日:2010/11/19
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR007L06";
    /** DAO【店舗別リアルタイム集信期間マスタ】 */
    private MstRealTimeScheduleDao posReportRegistMstRealTimeScheduleDao;
    /** BIRDユーザー情報 */
	private BirdUserInfo birdUserInfo;
    /** DTO【POS速報設定情報保持】 */
	private PosReportRegistDto posReportRegistDto;
    /**
     * 事前条件処理
     * 
     * @param birdDateInfo
     * @param birdUserInfo
     * @param registDto
     */
    private void validate(final BirdUserInfo birdUserInfo
			, final PosReportRegistDto registDto)
    {
        if(birdUserInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        if(registDto == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("POS速報設定情報");
        }
        setBirdUserInfo(birdUserInfo);
        setPosReportRegistDto(registDto);
        
    }

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.posreportregist.logic.RegistLogic#execute(jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto)
	 */
	public void execute(BirdUserInfo birdUserInfo, PosReportRegistDto registDto) {
		//０．事前条件処理を行います。
		validate(birdUserInfo, registDto);
		//１．DTO【POS速報設定情報保持】．削除フラグ＝trueか、
		//    又はDTO【POS速報設定情報保持】．変更用集信開始日!=(null and ''空)の場合下記の処理を行います。
		if (getPosReportRegistDto().isDelete()
				|| !CommonUtil.isNull(getPosReportRegistDto().getShuDtStart())) 
		{
//			try {
				//DAO【店舗別リアルタイム集信期間マスタ情報】全店対象データ削除を実行します。
				getPosReportRegistMstRealTimeScheduleDao().deleteZenten();
//			}
//			catch () {
//				
//			}
		}
		//２．新しく変数．Current TimeStampへBirdFrameWork共通クラス．DateManager.getCurrentTimestamp()を実行し、
		//    その値（現在の日時）を設定します。
		Timestamp currentTimeStamp = DateManager.getCurrentTimestamp();
		//３．DTO【POS速報設定情報保持】．変更用集信開始日!=(null and ''空)の場合下記の処理を行います。
		if(!CommonUtil.isNull(getPosReportRegistDto().getShuDtStart())) {
			//３－１．新しくエンティティ[MstRealTimeSchedule]を生成し、変数．[変更用全店対象期間]とします。
			MstRealTimeSchedule eInsert = new MstRealTimeSchedule();
			//３－２．変数．[変更用全店対象期間]へ下記の値を設定します。
			eInsert.setCompanyCd(getPosReportRegistDto().getPosCompanyCd());
			eInsert.setMiseCd(PosReportRegistConstants.ALLMISE_CD);
			eInsert.setShuDtSta(getPosReportRegistDto().getShuDtStart());
			eInsert.setShuDtEnd(getPosReportRegistDto().getShuDtEnd());
			eInsert.setFirstUser(getBirdUserInfo().getUserID());
			eInsert.setFirstPgm(LOGIC_ID.substring(0, 7));
			eInsert.setFirstTmsp(currentTimeStamp);
			eInsert.setLastUser(getBirdUserInfo().getUserID());
			eInsert.setLastPgm(LOGIC_ID.substring(0, 7));
			eInsert.setLastTmsp(currentTimeStamp);
			
			//３－３．DAO【店舗別リアルタイム集信期間マスタ情報】insertを実行します。
			getPosReportRegistMstRealTimeScheduleDao().insert(eInsert);
		}
		//４．新しく変数．List[[削除対象]]を生成します。
		List listDelete = new ArrayList(0);
		//５．新しく変数．List[[更新対象]]を生成します。
		List listInsertUpdate = new ArrayList(0);
		//６．DTO【POS速報設定情報保持】.List[[店舗別情報]]のサイズ分、for文で下記の処理を行います。
		List listRegistData = getPosReportRegistDto().getListRegistData();
		for (int i=0; i<listRegistData.size(); i++) {
			//for-1.List[[店舗別情報]]から現行インデックス位置にあるEntity[店舗別情報]を取得します。
			UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) listRegistData.get(i);
			//for-2.Entity[店舗別情報].処理ステータス＝null（or’’）の場合、処理for-1に戻ります。
			if (!posinfo.getDelFlg() && CommonUtil.isNull(posinfo.getProcState())) {
				//処理for-1へ戻ります。
				continue;
			}
			//for-3.新しくMstMiseRealTimeGetSchedule[店舗別リアルタイム集信期間マスタ情報]を生成し、下記の値を設定します。
			MstRealTimeSchedule eRegistData = new MstRealTimeSchedule();
			eRegistData.setCompanyCd(posinfo.getCompanyCd());
			eRegistData.setMiseCd(posinfo.getMiseCd());
			eRegistData.setShuDtSta(posinfo.getShuDtSta());
			eRegistData.setShuDtEnd(posinfo.getShuDtEnd());
			//for-4.Entity[店舗別情報].削除フラグ == "true"の場合、下記の処理を行います。
			//for-5.Entity[店舗別情報].処理ステータス == "3"(削除)の場合、下記の処理を行います。
			if(posinfo.getDelFlg() 
					|| PosReportRegistConstants.PRO_STATE_DEL.equals(posinfo.getProcState()))
			{
				//Ⅰ．MstMiseRealTimeGetSchedule[店舗別リアルタイム集信期間マスタ情報]を変数．List[[削除対象]]に追加します。
				listDelete.add(eRegistData);
				//Ⅱ．処理for-1へ戻ります。
				continue;
			}
			//for-6.Entity[店舗別情報].処理ステータス == "1"(新規)　または　"2"(変更)の場合、下記の処理を行います。
			else {
				//Ⅰ．各項目へ下記の値を設定します。
				eRegistData.setFirstUser(getBirdUserInfo().getUserID());
				eRegistData.setFirstPgm(LOGIC_ID.substring(0, 7));
				eRegistData.setFirstTmsp(currentTimeStamp);
				eRegistData.setLastUser(getBirdUserInfo().getUserID());
				eRegistData.setLastPgm(LOGIC_ID.substring(0, 7));
				eRegistData.setLastTmsp(currentTimeStamp);
				//Ⅱ．MstMiseRealTimeGetSchedule[店舗別リアルタイム集信期間マスタ情報]を変数．List[[更新対象]]に追加します。
				listInsertUpdate.add(eRegistData);
			}
		}// end of for (int i=0; i<getPosReportRegistDto().getListPosReport().size(); i++)
		
		//７．DB削除処理として、変数．List[[削除対象]]件数分、for文処理で下記の処理します。
		for (int d=0; d<listDelete.size(); d++) {
			MstRealTimeSchedule eDelete = (MstRealTimeSchedule) listDelete.get(d);
			//Dao【店舗別リアルタイム集信期間マスタ情報】削除登録を実行する
			getPosReportRegistMstRealTimeScheduleDao().delete(eDelete);
		}
		//８．登録処理として、変数．List[[更新対象]]件数分、for文処理で下記の処理します。
		for (int iu=0; iu<listInsertUpdate.size(); iu++) {
			MstRealTimeSchedule eRegist = (MstRealTimeSchedule) listInsertUpdate.get(iu);
			//８－１．Dao【店舗別リアルタイム集信期間マスタ情報】新規登録を実行します。
//			try {
				getPosReportRegistMstRealTimeScheduleDao().insert(eRegist);
//            }
//			//８－２．処理８－１でSQLRuntimeExceptionが発生した場合下記の処理を行います。
//            catch(SQLRuntimeException se) {
//                SQLException e2 = (SQLException) se.getCause();
//                //SQLState＝”23505”の場合、重複レコードのエラーと判断し、更新登録処理を行います。
//                if( e2.getSQLState().equals("23505") ) {
//                	getPosReportRegistMstRealTimeScheduleDao().update(eRegist);
//                }
//            }
		}
		//９．処理終了
	}
    /**
     * POS速報設定DTOを取得します。
     * @return POS速報設定DTO
     */
    private PosReportRegistDto getPosReportRegistDto() {
        return posReportRegistDto;
    }
    /**
     * POS速報設定DTOを設定します。
     * @param POS速報設定DTO
     */
    private void setPosReportRegistDto(PosReportRegistDto posReportRegistDto) {
        this.posReportRegistDto = posReportRegistDto;
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo
     */
    public BirdUserInfo getBirdUserInfo() {
        return this.birdUserInfo;
    }
    /**
     * BIRDユーザー情報設定処理
     * @param birdUserInfo
     */
    private void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * DAO【店舗別リアルタイム集信期間マスタ】取得処理
     * @return
     */
	public MstRealTimeScheduleDao getPosReportRegistMstRealTimeScheduleDao() {
		return posReportRegistMstRealTimeScheduleDao;
	}
	/**
	 * DAO【店舗別リアルタイム集信期間マスタ】設定処理
	 * @param posReportRegistMstRealTimeScheduleDao
	 */
	public void setPosReportRegistMstRealTimeScheduleDao(
			MstRealTimeScheduleDao posReportRegistMstRealTimeScheduleDao) {
		this.posReportRegistMstRealTimeScheduleDao = posReportRegistMstRealTimeScheduleDao;
	}

}
