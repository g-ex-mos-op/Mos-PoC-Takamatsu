package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.inforegist.documentregist.dao.UIBunshoInfoDao;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.CountBunshoLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class CountBunshoLogicImpl implements CountBunshoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L07";
    /* 対象年月が指定されていない場合の検索期間 ＝ 3年間 */
    private static final int DEFAULT_SEARCH_KIKAN = 35;
    
    /**
     * 既存の文書情報一覧の取得
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String nengetu 対象年月
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @param String sysDate システム日付
     * @return int 文書の件数
     * @exception ApplicationException
     */
    public int countBunsho(String nengetu,
                            String cateId, 
                           String subCateId, 
                           String rCompanyCd,
                           String bumonCd,
                           String sysDate) 
                    throws ApplicationException
    {
        String kikanFrom = null;
        String kikanTo = null;
        // 事前条件
        validate(cateId, subCateId, nengetu, rCompanyCd, bumonCd);
        
        S2Container container = SingletonS2ContainerFactory.getContainer();

        // 文書一覧取得
        UIBunshoInfoDao dao =
            (UIBunshoInfoDao) container.getComponent(UIBunshoInfoDao.class);
        if(nengetu != null){
            nengetu += '%';
        }
        else {
            // 対象年月が指定されていない場合は、システム日付基準で3年間を検索対象とする
            try {
                kikanFrom = DateManager.getPrevMonth(sysDate.substring(0, 6), DEFAULT_SEARCH_KIKAN) + "01";
                kikanTo = sysDate.substring(0, 6) + "31";
            }
            catch (Exception ex) {
                throw new FtlSystemException("日付計算", null, ex);
            }
        }
        return dao.countBunsho(nengetu, cateId, subCateId, rCompanyCd, bumonCd, kikanFrom, kikanTo);
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
