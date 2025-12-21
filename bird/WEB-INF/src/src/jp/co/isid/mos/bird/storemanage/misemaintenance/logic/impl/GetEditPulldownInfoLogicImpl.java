package jp.co.isid.mos.bird.storemanage.misemaintenance.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.CodAccessWayDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.CodAirConditionerDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.CodChintaiDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.CodGasDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.CodSmokeDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.CodYachinZeiDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.GetEditPulldownInfoLogic;

/**
 * ユーザー所属管理会社の検索ロジック
 * @author xnkusama
 * 
 * 更新日:2011/07/08 xkinu ガス&エアコン種別項目追加対応
 */
public class GetEditPulldownInfoLogicImpl implements GetEditPulldownInfoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BSM001L05";

    /*【DAO】*/
    private CodChintaiDao codChintaiDao;
    private CodYachinZeiDao codYachinZeiDao;
    private CodSmokeDao codSmoke;
    private CodAccessWayDao codAccessWayDao;
    /* DAO【ガス種別情報】*/
    private CodGasDao miseMaintenanceCodGasDao;
    /* DAO【エアコン種別情報】*/
    private CodAirConditionerDao miseMaintenanceCodAirConDao;

	/**
     * 編集画面で使用するプルダウンの情報を取得
     * 
     * @param MiseMaintenanceDto sessionDto
     * 
	 * 更新日:2011/07/08 xkinu ガス&エアコン種別項目追加対応
     */
    public void execute(MiseMaintenanceDto sessionDto) {

        /* モス以外の場合、プルダウン用の情報を取得 */
        if (!sessionDto.isMosCompany()) {

	        List listSmoke = getCodSmoke().selectSmoke();
	        List listYachin = getCodYachinZeiDao().selectYachinZei();
	        List listChintai = getCodChintaiDao().selectChintai(sessionDto.getCondCompanyCd());
	        List listAccessWay = getCodAccessWayDao().selectAccessWay();
	        sessionDto.setListPulldownSmoke(listSmoke);
	        sessionDto.setListPulldownYachin(listYachin);
	        sessionDto.setListPulldownChintai(listChintai);
	        sessionDto.setListPulldownAccessWay(listAccessWay);
        }
        List listShubetuGas = getMiseMaintenanceCodGasDao().select();
        List listShubetuAirCon = getMiseMaintenanceCodAirConDao().select();
        sessionDto.setListShubetuGas(listShubetuGas);
        sessionDto.setListShubetuAircon(listShubetuAirCon);
    }
    
    /**
     * @return codChintaiDao を戻します。
     */
    public CodChintaiDao getCodChintaiDao() {
        return codChintaiDao;
    }
    /**
     * @param codChintaiDao codChintaiDao を設定。
     */
    public void setCodChintaiDao(CodChintaiDao codChintaiDao) {
        this.codChintaiDao = codChintaiDao;
    }
    /**
     * @return codSmoke を戻します。
     */
    public CodSmokeDao getCodSmoke() {
        return codSmoke;
    }
    /**
     * @param codSmoke codSmoke を設定。
     */
    public void setCodSmoke(CodSmokeDao codSmoke) {
        this.codSmoke = codSmoke;
    }
    /**
     * @return codYachinZeiDao を戻します。
     */
    public CodYachinZeiDao getCodYachinZeiDao() {
        return codYachinZeiDao;
    }
    /**
     * @param codYachinZeiDao codYachinZeiDao を設定。
     */
    public void setCodYachinZeiDao(CodYachinZeiDao codYachinZeiDao) {
        this.codYachinZeiDao = codYachinZeiDao;
    }
    /**
     * @return codAccessWayDao を戻します。
     */
    public CodAccessWayDao getCodAccessWayDao() {
        return codAccessWayDao;
    }
    /**
     * @param codAccessWayDao codAccessWayDao を設定。
     */
    public void setCodAccessWayDao(CodAccessWayDao codAccessWayDao) {
        this.codAccessWayDao = codAccessWayDao;
    }
    
    /**
     * DAO【エアコン種別情報】取得処理
     * 
	 * @return クラス変数miseMaintenanceCodAirConDao を戻します。
	 */
	public CodAirConditionerDao getMiseMaintenanceCodAirConDao() {
		return miseMaintenanceCodAirConDao;
	}

	/**
	 * DAO【エアコン種別情報】設定処理
	 * @param dao を クラス変数miseMaintenanceCodAirConDaoへ設定します。
	 */
	public void setMiseMaintenanceCodAirConDao(
			CodAirConditionerDao dao) {
		this.miseMaintenanceCodAirConDao = dao;
	}

	/**
	 * DAO【ガス種別情報】取得処理
	 * @return クラス変数miseMaintenanceCodGasDao を戻します。
	 */
	public CodGasDao getMiseMaintenanceCodGasDao() {
		return miseMaintenanceCodGasDao;
	}

	/**
	 * DAO【ガス種別情報】設定処理
	 * @param dao を クラス変数miseMaintenanceCodGasDaoへ設定します。
	 */
	public void setMiseMaintenanceCodGasDao(CodGasDao dao) {
		this.miseMaintenanceCodGasDao = dao;
	}
}
