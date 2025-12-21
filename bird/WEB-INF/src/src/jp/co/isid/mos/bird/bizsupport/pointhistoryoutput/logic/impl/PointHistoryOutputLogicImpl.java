/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao.UIHuyoPointRirekiDao;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao.UIPointRuikeiDao;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao.UITaishokuSeisanDao;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao.UIYakuinPointRirekiDao;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dto.PointHistoryOutputDto;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.PointHistoryOutputLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.MismatchException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.exception.TooManyResultException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * ポイント履歴  検索ロジック
 * @author Yuichi Tamura(ISID-AO)
 *
 */
public class PointHistoryOutputLogicImpl implements PointHistoryOutputLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BBS038L02";
    //社員付与ポイント履歴
    private UIHuyoPointRirekiDao  uiHuyoPointRirekiDao;
    //退職時精算
    private UITaishokuSeisanDao   uiTaishokuSeisanDao;
    //役員付与ポイント履歴
    private UIYakuinPointRirekiDao uiYakuinPointRirekiDao;
    //社員役員ポイント累計
    private UIPointRuikeiDao uiPointRuikeiDao;
    //検索結果件数上限
    private int selectMaxCount;


	/**
     * ポイント履歴の検索
     * @return 検索結果
     */
    public List execute(PointHistoryOutputDto dto) {

    	//事前条件チェック
    	validate(dto);

        String kbCompanyCd = dto.getKbCompanyCd();
        String userId = dto.getUserId();

        String nendoFrom = dto.getNendoFrom();
        String nendoTo    = dto.getNendoTo();
        String taishokuFlg    = dto.getTaishokuIndex();
        String sysDate    = dto.getSysDate();

        //選択されたラジオボタンのインデックス
		String index = dto.getShoriKbnIndex();


        List csvList = new ArrayList();
        int resultCount = 0;
        //ポイント履歴の場合
        if(PointHistoryOutputCsvLogicImpl.SYAIN_POINT_RIREKI.equals(index)){
			/* 社員付与ポイント履歴(UIHuyoPointRireki) */
			resultCount = getUiHuyoPointRirekiDao().selectCountSyainHuyoPoint(kbCompanyCd, userId, nendoFrom, nendoTo,
					taishokuFlg, sysDate);

			// 検索結果件数チェック
			checkSelectCount(resultCount);

			csvList = getUiHuyoPointRirekiDao().selectSyainHuyoPoint(kbCompanyCd, userId, nendoFrom, nendoTo,
					taishokuFlg, sysDate);

        //退職時精算の場合
        } else if(PointHistoryOutputCsvLogicImpl.TAISHOKU.equals(index)){
            /* 退職時精算(UITaishokuSeisan) */
        	resultCount = getUiTaishokuSeisanDao().selectCountTaishokuSeisanPoint(kbCompanyCd, userId, nendoFrom, nendoTo);

        	//検索結果件数チェック
        	checkSelectCount(resultCount);

        	csvList = getUiTaishokuSeisanDao().selectTaishokuSeisanPoint(kbCompanyCd, userId, nendoFrom, nendoTo);

        //役員ポイント履歴の場合
        } else if(PointHistoryOutputCsvLogicImpl.YAKUIN_POINT_RIREKI.equals(index)){
			/* 役員付与ポイント履歴(UIYakuinPointRireki) */
			resultCount = getUiYakuinPointRirekiDao().selectCountYakuinHuyoPoint(kbCompanyCd, userId, nendoFrom,
					nendoTo, taishokuFlg, sysDate);

			// 検索結果件数チェック
			checkSelectCount(resultCount);

			csvList = getUiYakuinPointRirekiDao().selectYakuinHuyoPoint(kbCompanyCd, userId, nendoFrom, nendoTo,
					taishokuFlg, sysDate);

        //累積ポイント確認の場合
        } else if(PointHistoryOutputCsvLogicImpl.RUISEKI.equals(index)){
			/* 社員役員ポイント累計(UIPointRuikei) */
			resultCount = getUiPointRuikeiDao().selectCountPointRuikei(kbCompanyCd, userId, nendoFrom, nendoTo,
					taishokuFlg, sysDate);

			// 検索結果件数チェック
			checkSelectCount(resultCount);

			csvList = getUiPointRuikeiDao().selectPointRuikei(kbCompanyCd, userId, nendoFrom, nendoTo, taishokuFlg,
					sysDate);

        }
        return csvList;
    }


    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }


	/**
	 * uiHuyoPointRirekiDaoを返す
	 * @return uiHuyoPointRirekiDao
	 */
	public UIHuyoPointRirekiDao getUiHuyoPointRirekiDao() {
		return uiHuyoPointRirekiDao;
	}


	/**
	 * uiHuyoPointRirekiDaoをセットする
	 * @param uiHuyoPointRirekiDao
	 */
	public void setUiHuyoPointRirekiDao(UIHuyoPointRirekiDao uiHuyoPointRirekiDao) {
		this.uiHuyoPointRirekiDao = uiHuyoPointRirekiDao;
	}


	/**
	 * uiTaishokuSeisanDaoを返す
	 * @return uiTaishokuSeisanDao
	 */
	public UITaishokuSeisanDao getUiTaishokuSeisanDao() {
		return uiTaishokuSeisanDao;
	}


	/**
	 * uiTaishokuSeisanDaoをセットする
	 * @param uiTaishokuSeisanDao
	 */
	public void setUiTaishokuSeisanDao(UITaishokuSeisanDao uiTaishokuSeisanDao) {
		this.uiTaishokuSeisanDao = uiTaishokuSeisanDao;
	}


	/**
	 * uiYakuinPointRirekiDaoを返す
	 * @return uiYakuinPointRirekiDao
	 */
	public UIYakuinPointRirekiDao getUiYakuinPointRirekiDao() {
		return uiYakuinPointRirekiDao;
	}


	/**
	 * uiYakuinPointRirekiDaoをセットする
	 * @param uiYakuinPointRirekiDao
	 */
	public void setUiYakuinPointRirekiDao(UIYakuinPointRirekiDao uiYakuinPointRirekiDao) {
		this.uiYakuinPointRirekiDao = uiYakuinPointRirekiDao;
	}


	/**
	 * uiPointRuikeiDaoを返す
	 * @return uiPointRuikeiDao
	 */
	public UIPointRuikeiDao getUiPointRuikeiDao() {
		return uiPointRuikeiDao;
	}


	/**
	 * uiPointRuikeiDaoをセットする
	 * @param uiPointRuikeiDao
	 */
	public void setUiPointRuikeiDao(UIPointRuikeiDao uiPointRuikeiDao) {
		this.uiPointRuikeiDao = uiPointRuikeiDao;
	}


	/**
	 * 検索結果件数上限を返す
	 * @return selectMaxCount 検索結果件数上限
	 */
	public int getSelectMaxCount() {
		return selectMaxCount;
	}


	/**
	 * 検索結果件数上限をセットする
	 * @param selectMaxCount  検索結果件数上限
	 */
	public void setSelectMaxCount(int selectMaxCount) {
		this.selectMaxCount = selectMaxCount;
	}


    /**
     * 事前条件処理
     * @param  PointHistoryOutputDto パラメータ
     * @throws ApplicationException
     */
    private void validate(PointHistoryOutputDto dto) {

        String userId = dto.getUserId();
        String nendoFrom = dto.getNendoFrom();
        String nendoTo    = dto.getNendoTo();
        String index = dto.getShoriKbnIndex();
        String taishokuFlg = dto.getTaishokuIndex();

        //処理区分の必須チェック
        if (index == null ) {
            throw new NotSelectedException("処理");
        }

    	CodeVerifier  userIdVerifier = new  CodeVerifier(8, true);
    	userIdVerifier.setNullable(true);
    	//社員番号の妥当性チェック
    	if(!userIdVerifier.validate(userId)){
    		throw new MismatchException("社員番号が英数字");
    	}
    	NumericVerifier  nendoNumericVerifier = new  NumericVerifier(false, 4);
    	nendoNumericVerifier.setNullable(true);
    	//年度の妥当性チェック
    	if(!nendoNumericVerifier.validate(nendoFrom)){
    		throw new MismatchException("年度(From)が日付");
    	}
    	if(!nendoNumericVerifier.validate(nendoTo)){
    		throw new MismatchException("年度(To)が日付");
    	}
    	//年度の論理チェック
    	if(nendoFrom != null && nendoTo != null){
    		if(nendoFrom.compareTo(nendoTo) > 0){
        		throw new  ConstraintsViolationException("年度(From) ≦ 年度(To)で");
    		}
    	}
        //退職者を含む/含まないの必須チェック
        if (taishokuFlg == null ) {
            throw new NotSelectedException("退職者を含む/含まない");
        }


    }

    /**
     * 検索結果閾値オーバーの場合と検索結果が０件の場合、エラーメッセージを出力する。
     * @param resultCount 検索結果件数
     */
    private void checkSelectCount(int resultCount) throws ApplicationException{
    	//０件の場合
    	if(resultCount == 0){
            throw new NoResultException();
    	}
    	// 検索結果閾値オーバーの場合
    	if(resultCount > getSelectMaxCount()){
            throw new TooManyResultException("件数:" + resultCount + "/上限:" + getSelectMaxCount() );
    	}
    }

}
