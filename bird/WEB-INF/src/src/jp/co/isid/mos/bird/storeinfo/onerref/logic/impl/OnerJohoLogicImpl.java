/*
 * 作成日: 2006/3/7
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic.impl;

import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.storeinfo.onerref.dao.MstKyoeiDao;
import jp.co.isid.mos.bird.storeinfo.onerref.dao.MstOnerDao;
import jp.co.isid.mos.bird.storeinfo.onerref.dao.MstOnerHoyuMiseDao;
import jp.co.isid.mos.bird.storeinfo.onerref.dao.TrnOnerDaiDao;
import jp.co.isid.mos.bird.storeinfo.onerref.dto.OwnerReferenceDto;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstOner;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.OnerJohoLogic;

/**
 * オーナー情報の取得ロジックアクション
 * @author itamoto
 */
public class OnerJohoLogicImpl implements OnerJohoLogic {

    public static final String LOGIC_ID = "BSI002L02";

    /**
     * グループ統合オーナーマスタの検索（MstOnerDao）
     */
    private MstOnerDao mstOnerDao;
    
    /**
     * オーナー代表者情報履歴（TrnOnerDaiDao）
     */
    private TrnOnerDaiDao trnOnerDaiDao;

    /**
	 * オーナー保有店舗一覧(MstOnerHoyuMise）
	 */
    private MstOnerHoyuMiseDao mstOnerHoyuMiseDao;

    /**
	 * 共栄会役員実績（MstKyoeiDao）
	 */
    private MstKyoeiDao mstKyoeiDao;

    /**
	 * グループ統合オーナーマスタの検索Daoの設定
	 * @return mstOnerDao を戻します。
	 */
	public MstOnerDao getMstOnerDao() {
		return mstOnerDao;
	}
	/**
	 * グループ統合オーナーマスタの検索Daoの設定
	 * @param mstOnerDao mstOnerDao を設定。
	 */
	public void setMstOnerDao(MstOnerDao mstOnerDao) {
		this.mstOnerDao = mstOnerDao;
	}

	/**
     * オーナー代表者情報履歴Daoの設定
	 * @return trnOnerDaiDao を戻します。
	 */
	public TrnOnerDaiDao getTrnOnerDaiDao() {
		return trnOnerDaiDao;
	}
	/**
     * オーナー代表者情報履歴Daoの設定
	 * @param trnOnerDaiDao trnOnerDaiDao を設定。
	 */
	public void setTrnOnerDaiDao(TrnOnerDaiDao trnOnerDaiDao) {
		this.trnOnerDaiDao = trnOnerDaiDao;
	}

	/**
	 * オーナー保有店舗一覧Daoの設定
	 * @return mstOnerHoyuMiseDao を戻します。
	 */
	public MstOnerHoyuMiseDao getMstOnerHoyuMiseDao() {
		return mstOnerHoyuMiseDao;
	}
	/**
	 * オーナー保有店舗一覧Daoの設定
	 * @param mstOnerHoyuMiseDao mstOnerHoyuMiseDao を設定。
	 */
	public void setMstOnerHoyuMiseDao(MstOnerHoyuMiseDao mstOnerHoyuMiseDao) {
		this.mstOnerHoyuMiseDao = mstOnerHoyuMiseDao;
	}
	
	/**
	 * 共栄会役員実績Daoの設定
	 * @return mstKyoeiDao を戻します。
	 */
	public MstKyoeiDao getMstKyoeiDao() {
		return mstKyoeiDao;
	}
	/**
	 * 共栄会役員実績Daoの設定
	 * @param mstKyoeiDao mstKyoeiDao を設定。
	 */
	public void setMstKyoeiDao(MstKyoeiDao mstKyoeiDao) {
		this.mstKyoeiDao = mstKyoeiDao;
	}

	/**
	 * オーナー情報の検索を行う
	 * @return 検索結果
	 */
	public MstOner execute(OwnerReferenceDto ownerReferenceDto) {
		// １．Dao【グループ統合オーナーマスタ.グループ統合オーナーマスタの検索】を実行する。
		// パラメータ：会社コード、オーナーコード
		// ①件数が0件の場合、【E0103】を発生させる。
		MstOner mstOnerList = mstOnerDao.selectOnerMst(ownerReferenceDto
				.getCompanyCd(), ownerReferenceDto.getOnerCd(),
                ownerReferenceDto.getBirdUserInfo().getUserID(),
                ownerReferenceDto.getBirdUserInfo().isLimit());
//		ownerReferenceDto.setMstOner(mstOnerDao.selectOnerMst(ownerReferenceDto
//				.getCompanyCd(), ownerReferenceDto.getOnerCd()));
		if (mstOnerList == null) {
//		if (ownerReferenceDto.getMstOner() == null) {
            throw new NotExistException("オーナーコード", "onerCd", "");
		}
		ownerReferenceDto.setMstOner(mstOnerList);

		// ２．Dao【オーナー代表者情報履歴.オーナー代表者情報履歴の検索】を実行する。
		// パラメータ：会社コード、オーナーコード
		ownerReferenceDto.setTrnOnerDaiList(trnOnerDaiDao
				.selectOnerDaiRireki(ownerReferenceDto.getCompanyCd(),
						ownerReferenceDto.getOnerCd()));
		
		// ３．Dao【オーナー保有店舗一覧.オーナー保有店舗一覧の検索】を実行する。
		// パラメータ：会社コード、オーナーコード
		ownerReferenceDto.setMstOnerHoyuMiseList(mstOnerHoyuMiseDao
				.selectOnerHoyuMise(ownerReferenceDto.getCompanyCd(),
						ownerReferenceDto.getOnerCd()));
		
		// ３．Dao【共栄会役員実績.共栄会役員実績の検索】を実行する。
		// パラメータ：会社コード、オーナーコード
		ownerReferenceDto.setMstKyoeiList(mstKyoeiDao
				.selectKyoei(ownerReferenceDto.getCompanyCd(),
						ownerReferenceDto.getOnerCd()));
		return null;
	}
}
