package jp.co.isid.mos.bird.communication.docform.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.communication.docform.common.DocFormCont;
import jp.co.isid.mos.bird.communication.docform.dao.MstCategoryInfoDao;
import jp.co.isid.mos.bird.communication.docform.dto.DocFormDto;
import jp.co.isid.mos.bird.communication.docform.logic.InitializeLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 初期化ロジック
 * 
 * ログインユーザー別のカテゴリ情報を取得します。
 * 作成日:2011/02/22
 * @author xkinu
 *
 */
public class InitializeLogicImpl implements InitializeLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BCM004L02";
    //DAO【公開対象カテゴリ】
    private MstCategoryInfoDao docformMstCategoryInfoDao;

    /**
    /**
     * 実行処理
     * 
     * ・自画面DTOの初期化
     * ・自分に公開されているカテゴリーをを取得する
     * 
     * @param birdDateInfo
     * @param birdUserInfo
     * @param sessionDto
     */
    public void execute(BirdDateInfo birdDateInfo
    		, BirdUserInfo birdUserInfo, DocFormDto sessionDto) {

        // 事前条件
        validate(birdDateInfo, birdUserInfo, sessionDto);
        sessionDto.clearDto();
        sessionDto.setCurrentPageNumber(1);
        sessionDto.setViewCateName("");
        sessionDto.setNaviSelectedInfoShu((String) sessionDto.getListInfoShu().get(0));

        // カテゴリ取得
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		String userId = birdUserInfo.getUserID();
		//個別指定判断制御フラグを取得します。
		boolean kobetsuFlg = DocFormCont.getKobetsuFlg(birdUserInfo);
		List listInfoShu = sessionDto.getListInfoShu();
		String infoShu = "";
		for (int i=0; i<listInfoShu.size(); i++) {
			infoShu = (String) listInfoShu.get(i);
			//各カテゴリ取得し、DTO[セッション情報]へ設定します。
			sessionDto.setListCategory(
					infoShu, getDocformMstCategoryInfoDao().select(
							infoShu, birdDateInfo.getSysDate()
							, userId, userTypeCd, kobetsuFlg));
		}
    }

    /**
     * 事前条件
     * 
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String title タイトル
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @param int pageNo ページ番号
     * @throws ApplicationException
     */
    private void validate(
    		BirdDateInfo birdDateInfo
    	  , BirdUserInfo birdUserInfo
    	  , DocFormDto sessionDto) 
    {
        // BIRDユーザー情報
        if (birdDateInfo == null) {
            throw new MissingDataException("日付情報");
        }
        // BIRDユーザー情報
        if (birdUserInfo == null) {
            throw new MissingDataException("ユーザー情報");
        }
        if (sessionDto.getListInfoShu()==null || sessionDto.getListInfoShu().size()==0) {
        	throw new MissingDataException("対象情報種別");
        }
    }
	/**
	 * @return クラス変数docformMstCategoryInfoDao を戻します。
	 */
	public MstCategoryInfoDao getDocformMstCategoryInfoDao() {
		return docformMstCategoryInfoDao;
	}

	/**
	 * @param docformMstCategoryInfoDao を クラス変数docformMstCategoryInfoDaoへ設定します。
	 */
	public void setDocformMstCategoryInfoDao(
			MstCategoryInfoDao docformMstCategoryInfoDao) {
		this.docformMstCategoryInfoDao = docformMstCategoryInfoDao;
	}

}
