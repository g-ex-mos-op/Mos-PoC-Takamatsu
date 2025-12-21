package jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.inforegist.notificationregist.dao.UITutatuInfoDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.GetTotalCountLogic;

/**
 * 文書の件数取得ロジック
 * 
 * @author xnkusama
 */
public class GetTotalCountLogicImpl implements GetTotalCountLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF002L02";

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
     * 既存の通達情報一覧の件数取得
     * 
     * @param nengetu 対象年月
     * @param cateId  カテゴリID
     * @param kanriNo 管理番号
     * @param mstUser ログインユーザー情報エンティティ
     * @return 通達の件数
     */
	public int countTutatu(String nengetu
    		, String cateId
    		, String kanriNo
    		, MstUser mstUser)
    {
        // 事前条件
        validate(cateId, nengetu, kanriNo);
        
		nengetu = (nengetu == null) ? null : nengetu + "%";
		kanriNo = (kanriNo == null) ? null : "%" + kanriNo + "%";
		String userId = mstUser.getUser_id();
		String rcompanyCd = mstUser.getRCompanyCd();

        // 文書一覧取得
        return getUITutatuInfoDao().getCount(nengetu, cateId, kanriNo, userId, rcompanyCd);
    }
    
    /**
     * 事前処理
     * 
     * @param cateId
     * @param nengetu
     * @param kanriNo
     */
    private void validate(String cateId, 
                            String nengetu, 
                            String kanriNo)
    {
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        
        // カテゴリID
        if (cateId == null) {
            throw new InvalidInputException("カテゴリID");
        }
        codeValidator.validate(cateId);
    }
}
