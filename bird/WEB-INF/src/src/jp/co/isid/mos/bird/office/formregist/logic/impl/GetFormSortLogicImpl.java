package jp.co.isid.mos.bird.office.formregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.office.formregist.dao.UIFormInfoDao;
import jp.co.isid.mos.bird.office.formregist.logic.GetFormSortLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * フォームダウンロード順序を取得
 * @author xytamura
 */
public class GetFormSortLogicImpl implements GetFormSortLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L06";


    /**
     * フォームダウンロード順序を取得
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @return List フォーム情報
     * @exception ApplicationException
     */
    public List execute(String nengetu, String cateId, String subCateId)
            throws ApplicationException {
        // 事前条件
        validate(cateId, subCateId, nengetu);
        if(nengetu != null){
            nengetu += "%";
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();

        // フォーム一覧取得
        UIFormInfoDao dao = (UIFormInfoDao) container
                .getComponent(UIFormInfoDao.class);
        List listForm = dao.getFormList(nengetu, cateId, subCateId);

        //        if (listBunsho == null || listBunsho.size() == 0) {
        //            throw new NoResultException();
        //        }

        return listForm;
    }

    /**
     * 事前条件
     * @param cateId
     * @param subCateId
     * @param nengetu
     * @param rCompanyCd
     * @param bumonCd
     * @throws ApplicationException
     */
    private void validate(String cateId, String subCateId, String nengetu

    ) throws ApplicationException {
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(
                DateVerifier.DATE_TYPE_YM);

        // カテゴリID
        if (cateId == null) {
            throw new InvalidInputException("カテゴリID");
        }
        codeValidator.validate(cateId);

        // サブカテゴリID
        if (subCateId == null) {
            throw new InvalidInputException("サブカテゴリID");
        }
        codeValidator.validate(subCateId);

        // 対象月
        if (nengetu != null) {
            dateValidator.validate(nengetu);
        }


    }
}