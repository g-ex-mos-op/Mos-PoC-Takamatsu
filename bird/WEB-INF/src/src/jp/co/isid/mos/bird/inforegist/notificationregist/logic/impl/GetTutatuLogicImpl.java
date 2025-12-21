package jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.inforegist.notificationregist.dao.UITutatuInfoDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.GetTutatuLogic;

public class GetTutatuLogicImpl implements GetTutatuLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF002L01";

    private static final int PAGE_MAX_SIZE = 30;
    
	/**
	 * DAO
	 */
	private UITutatuInfoDao uITutatuInfoDao;
	/**
     * DAOを取得します。
     * 
     * @return DAO
     */
	public UITutatuInfoDao getUITutatuInfoDao() {
		return uITutatuInfoDao;
	}
    /**
     * DAOを設定します。
     * 
     * @param uIUserRoleDao DAO
     */
	public void setUITutatuInfoDao(UITutatuInfoDao uITutatuInfoDao) {
		this.uITutatuInfoDao = uITutatuInfoDao;
	}

    
    /**
     * 既存の文書情報一覧の取得
     * 
     * @param nengetu 対象年月
     * @param cateId  カテゴリID
     * @param kanriNo 管理番号
     * @param page    ページ
     * @param mstUser ログインユーザー情報エンティティ
     * @return
     */
    public List getTutatu(String nengetu,
                           String cateId, 
                           String kanriNo, 
                           int page,
                           MstUser mstUser)
    {
        // 事前条件
        validate(cateId, nengetu, kanriNo, page);
        
        int pageFrom = (page - 1) * PAGE_MAX_SIZE + 1;
        int pageTo   = page * PAGE_MAX_SIZE;
		nengetu = (nengetu == null) ? null : nengetu + "%";
		kanriNo = (kanriNo == null) ? null : "%" + kanriNo + "%";
		String userId = mstUser.getUser_id();
		String rcompanyCd = mstUser.getRCompanyCd();
        
        List listTutatu = getUITutatuInfoDao().getTutatu(
        		  nengetu, cateId, kanriNo
        		, userId, rcompanyCd
        		, pageFrom, pageTo);

        if (listTutatu == null || listTutatu.size() == 0) {
            throw new NoResultException();
        }
        
        return listTutatu;
    }
    
    /**
     * 事前条件
     * @param cateId
     * @param subCateId
     * @param nengetu
     * @param page
     * @param rCompanyCd
     * @param bumonCd
     * @throws ApplicationException
     */
    private void validate(String cateId, 
                            String nengetu,
                            String kanriNo,
                            int page) 
                     throws ApplicationException
    {
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        
        // カテゴリID
        if (cateId == null) {
            throw new InvalidInputException("カテゴリID", "cateId", null);
        }
        codeValidator.validate(cateId);
        //管理番号チェック
        if (kanriNo != null) {
        	kanriNo.trim();
        	if(kanriNo.getBytes().length > 24) {
        		throw new InvalidInputException("管理番号", "kanriNo", null);
        	}
        }
        // ページ
        if (page == 0) {
            throw new InvalidInputException("ページ");
        }
    }
}
