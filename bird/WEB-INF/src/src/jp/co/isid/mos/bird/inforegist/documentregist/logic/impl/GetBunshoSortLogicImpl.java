package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.inforegist.documentregist.dao.UIBunshoInfoDao;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.GetBunshoSortLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class GetBunshoSortLogicImpl implements GetBunshoSortLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L04";

    /**
     * 文書照会順序を取得する
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @return List 文書情報
     * @exception ApplicationException
     */
    public List getBunshoSort(String nengetu,
                               String cateId, 
                               String subCateId, 
                               String rCompanyCd,
                               String bumonCd) 
                        throws ApplicationException
    {
        // 事前条件
        validate(cateId, subCateId, nengetu, rCompanyCd, bumonCd);
        
        S2Container container = SingletonS2ContainerFactory.getContainer();

        // 文書一覧取得
        UIBunshoInfoDao dao =
            (UIBunshoInfoDao) container.getComponent(UIBunshoInfoDao.class);
        if(nengetu != null){
            nengetu += '%';
        }
        List listBunsho = dao.getShokaiList(nengetu, cateId, subCateId, rCompanyCd, bumonCd);

//        if (listBunsho == null || listBunsho.size() == 0) {
//            throw new NoResultException();
//        }
        
        return listBunsho;
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
    private void validate(String cateId, 
                            String subCateId, 
                            String nengetu,
                            String rCompanyCd,
                            String bumonCd) 
                     throws ApplicationException
    {
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(DateVerifier.DATE_TYPE_YM);
        
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

        // 企業コード
        if (rCompanyCd == null) {
            throw new InvalidInputException("企業コード");
        }
        codeValidator.validate(rCompanyCd);

        // 部門コード
        if (bumonCd == null) {
            throw new InvalidInputException("部門コード");
        }
        codeValidator.validate(bumonCd);
        
    }
}
