package jp.co.isid.mos.bird.office.formregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.office.formregist.dao.UIFormInfoDao;
import jp.co.isid.mos.bird.office.formregist.logic.GetFormInfoLogic;

/**
 * フォーム情報の取得
 * @author xytamura
 * 更新日：2010/07/06 xkinu 対象年月からの絞込み条件を外す対応
 * 　対応1．過去36ヵ月分の制約を解除
 * 　対応2．対象年月プルダウンを削除
 * 
 */
public class GetFormInfoLogicImpl implements GetFormInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L01";

    private static final int PAGE_MAX_SIZE = 30;
    /* 対象年月が指定されていない場合の検索期間 ＝ 3年間 */
//    private static final int DEFAULT_SEARCH_KIKAN = 35;


    /* DAO */
    private UIFormInfoDao uiFormInfoDao;
    
    /**
     * フォーム情報の取得
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String userId ユーザーID
     * @param int page ページ
     * @param String sysDate システム日付
     * @return List フォーム情報
     * 
	 * 更新日：2010/07/06 xkinu 対象年月からの絞込み条件を外す対応
	 * 　対応1．過去36ヵ月分の制約を解除
	 * 　対応2．対象年月プルダウンを削除
     */
    public List execute(String nengetu, String cateId, String subCateId, String userId, 
            int page, String sysDate) {
        String kikanFrom = null;
        String kikanTo = null;
        
        // 事前条件
        validate(cateId, subCateId, nengetu, page);

//        S2Container container = SingletonS2ContainerFactory.getContainer();
        int pageFrom = (page - 1) * PAGE_MAX_SIZE + 1;
        int pageTo = page * PAGE_MAX_SIZE;

       	nengetu = CommonUtil.isNull(nengetu)?null:nengetu +"%";
//        if(nengetu != null){
//            nengetu += "%";
//        }
//        else {
//            // 対象年月が指定されていない場合は、システム日付基準で3年間を検索対象とする
//            try {
//                kikanFrom = DateManager.getPrevMonth(sysDate.substring(0, 6), DEFAULT_SEARCH_KIKAN) + "01";
//                kikanTo = sysDate.substring(0, 6) + "31";
//            }
//            catch (Exception ex) {
//                throw new FtlSystemException("日付計算", null, ex);
//            }
//        }

        // フォーム一覧取得
//        UIFormInfoDao dao = (UIFormInfoDao) container
//                .getComponent(UIFormInfoDao.class);
        List listForm = getUiFormInfoDao().getForm(nengetu, cateId, subCateId, userId, kikanFrom, kikanTo, pageFrom, pageTo);

        if (listForm == null || listForm.size() == 0) {
            throw new NoResultException();
        }

        return listForm;
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
    private void validate(String cateId, String subCateId, String nengetu,
            int page) throws ApplicationException {
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(
                DateVerifier.DATE_TYPE_YM);

        // カテゴリID
        if (cateId == null || cateId.trim().length() == 0) {
            throw new NotSelectedException("カテゴリ");
        }
        codeValidator.validate(cateId);

        // サブカテゴリID
        if (subCateId == null || subCateId.trim().length() == 0) {
            throw new NotSelectedException("サブカテゴリ");
        }
        codeValidator.validate(subCateId);

        // 対象月
        if (nengetu != null && nengetu.trim().length() != 0) {
            dateValidator.validate(nengetu);
        }

        // ページ
        if (page == 0) {
            throw new InvalidInputException("ページ");
        }

//        // 企業コード
//        if (rCompanyCd == null) {
//            throw new InvalidInputException("企業コード");
//        }
//        codeValidator.validate(rCompanyCd);
//
//        // 部門コード
//        if (bumonCd == null) {
//            throw new InvalidInputException("部門コード");
//        }
//        codeValidator.validate(bumonCd);

    }

    public UIFormInfoDao getUiFormInfoDao() {
        return uiFormInfoDao;
    }

    public void setUiFormInfoDao(UIFormInfoDao uiFormInfoDao) {
        this.uiFormInfoDao = uiFormInfoDao;
    }
}