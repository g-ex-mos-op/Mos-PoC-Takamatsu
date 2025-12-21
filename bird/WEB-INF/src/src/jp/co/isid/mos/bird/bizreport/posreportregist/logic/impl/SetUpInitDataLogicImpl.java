/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistCommon;
import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.dao.MstRealTimeScheduleDao;
import jp.co.isid.mos.bird.bizreport.posreportregist.dao.UIPosReportMiseInfoDao;
import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.MstRealTimeSchedule;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.SetUpInitDataLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 初期表示情報取得設定ロジック
 * 
 * DTOの初期化と、DTOへDBから取得した登録情報などを設定を行います。
 * 作成日:2010/11/10
 * @author xkinu
 *
 */
public class SetUpInitDataLogicImpl implements SetUpInitDataLogic {
	/** ロジックID */
    public static final String LOGIC_ID = "BBR007L05";
    /** DAO【POS速報店舗情報リスト取得】 */
    private UIPosReportMiseInfoDao uIPosReportMiseInfoDao;
    /** DAO【店舗別リアルタイム集信期間マスタ】 */
    private MstRealTimeScheduleDao posReportRegistMstRealTimeScheduleDao;
    /** BIRD日付情報 */
	private BirdDateInfo birdDateInfo;
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
    private void validate(final BirdDateInfo birdDateInfo
    		, final BirdUserInfo birdUserInfo
			, final PosReportRegistDto registDto)
    {
        if(birdDateInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("日付情報");
        }
        if(birdUserInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        if(registDto == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("POS速報設定情報");
        }
        setBirdDateInfo(birdDateInfo);
        setBirdUserInfo(birdUserInfo);
        setPosReportRegistDto(registDto);
        
    }
	/**
	 * 実行処理
	 * 
	 * @param birdDateInfo BIRD日付情報
	 * @param birdUserInfo BIRDユーザー情報
	 * @param registDto    DTO【POS速報設定情報保持】
	 * @see jp.co.isid.mos.bird.bizreport.posreportregist.logic.SetUpInitDataLogic#execute(jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto)
	 */
	public void execute(BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo
			, PosReportRegistDto registDto) 
	{
		//０．事前条件処理を行います。
		validate(birdDateInfo, birdUserInfo, registDto);
		
        //１．パラメータ．DTO【POS速報設定情報保持】.デフォルト値設定処理を行い、下記の値をデフォルト値に設定します。
		getPosReportRegistDto().setDefault();
        
        String sysDate = getBirdDateInfo().getSysDate();
        //２．Dao【集信期間マスタ情報】検索を実行し、List[[全店対象期間情報]]を取得します。
        List listMstRealTile = getPosReportRegistMstRealTimeScheduleDao().select(
        		getPosReportRegistDto().getPosCompanyCd()
        	  , PosReportRegistConstants.ALLMISE_CD
        	  , null
        	  , sysDate);
        //処理２の戻り値List[[全店対象期間情報]]のサイズ > ０（ゼロ）の場合、
        if (listMstRealTile.size() > 0) {
	        //２－１．パラメータ．DTO【POS速報設定情報保持】.Entity[全店対象期間情報]へ
	        //処理２の戻り値List[[全店対象期間情報]]のインデックス値0番目の[全店対象期間情報]を設定します。
        	getPosReportRegistDto().setMstRealTimeSchedule((MstRealTimeSchedule) listMstRealTile.get(0));
        }
        //３．Dao【POS速報店舗情報】検索を実行し、List[[POS速報店舗情報]]を取得します。
        List listPos = getUIPosReportMiseInfoDao().select(
        		getPosReportRegistDto().getPosCompanyCd(), sysDate);            

        //４．新しくEntity[UIPosReportMiseInfo]を生成し、初期値を設定します。
        //５．変数.List[[POS速報店舗情報]]に処理４のEntity[UIPosReportMiseInfo]を追加します。
        PosReportRegistCommon.addRowEntity(listPos, sysDate);        
        
        //６．変数.List[[POS速報店舗情報]]に格納されている全エンティティのSEQへ先頭1から連番を設定します。
        PosReportRegistCommon.setSeqNo(listPos);
        
        //７．パラメータ．DTO【POS速報設定情報保持】.List[[POS速報店舗情報]]へ変数.List[[POS速報店舗情報]]を設定します。
        getPosReportRegistDto().setListPosReport(listPos);

        //８．開始日付け設定可能日として、新しく下記の変数を生成します。
        String possibleDtSta = "";
        String possibleDtEnd = "";
        try {
        	//変数．可能日始め→パラメーターBIRD日付情報．システム日付け＋1
            possibleDtSta = DateManager.getNextDate(sysDate, 1);
            //変数．可能日終り→可能日始め＋59
            possibleDtEnd = DateManager.getNextDate(sysDate, 59);
        } catch (Exception e) {
        }
        //９．パラメータ．DTO【POS速報設定情報保持】へ下記の値を保持します。
        //パラメータ．DTO【POS速報設定情報保持】．可能日始め　＝　変数．可能日始め
        getPosReportRegistDto().setPossibleDtSta(possibleDtSta.trim());
        //パラメータ．DTO【POS速報設定情報保持】．可能日終り　＝　変数．可能日終り
        getPosReportRegistDto().setPossibleDtEnd(possibleDtEnd.trim());
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
     * BIRD日付情報取得処理
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return this.birdDateInfo;
    }
    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo
     */
    private void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
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
	/**
	 * DAO【POS速報店舗情報リスト取得】取得処理
	 * @return
	 */
	public UIPosReportMiseInfoDao getUIPosReportMiseInfoDao() {
		return uIPosReportMiseInfoDao;
	}
	/**
	 * DAO【POS速報店舗情報リスト取得】設定処理
	 * @param posReportMiseInfoDao
	 */
	public void setUIPosReportMiseInfoDao(
			UIPosReportMiseInfoDao posReportMiseInfoDao) {
		uIPosReportMiseInfoDao = posReportMiseInfoDao;
	}

}
