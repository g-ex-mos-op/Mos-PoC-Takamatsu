package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.inforegist.documentregist.dao.UIBunshoInfoDao;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistConditionDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.GetDefaultSubCategoryLogic;

public class GetDefaultSubCategoryLogicImpl implements GetDefaultSubCategoryLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L08";
    
    private UIBunshoInfoDao uiBunshoInfoDao;
    
    /**
     * デフォルトサブカテゴリの取得
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String nengetu 対象年月
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @return int 文書の件数
     * @exception ApplicationException
     */
    public String getDefaultSubCategory(DocumentRegistConditionDto dto, MstUser mstUser) throws ApplicationException {
        // 事前条件
        validate(dto);
        
        String nengetu = dto.getNengetu();
        if(nengetu != null){
            nengetu += '%';
        }

        // 文書一覧取得
        List listSubCate = getUiBunshoInfoDao()
                                .getDefaultSubCatery(
                                        nengetu,
                                        dto.getCateId(),
                                        mstUser.getBumonCd());
        
        if (listSubCate == null || listSubCate.size() == 0) {
            throw new NoResultException();
        }
        
        UIBunshoInfo entity = (UIBunshoInfo) listSubCate.get(0);
        return entity.getSubCateId();
    }
    
    /**
     * 事前条件
     * @param ConditionDto
     * @throws ApplicationException
     */
    private void validate(DocumentRegistConditionDto dto) throws ApplicationException {
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(DateVerifier.DATE_TYPE_YM);
        
        String cateId = dto.getCateId();
        String nengetu = dto.getNengetu();
        
        // カテゴリID
        if (cateId == null) {
            throw new InvalidInputException("カテゴリID");
        }
        codeValidator.validate(cateId);

        // 対象月
        if (nengetu != null) {
	        dateValidator.validate(nengetu);
        }
    }
    /**
     * @return uiBunshoInfoDao を戻します。
     */
    public UIBunshoInfoDao getUiBunshoInfoDao() {
        return uiBunshoInfoDao;
    }
    /**
     * @param uiBunshoInfoDao uiBunshoInfoDao を設定。
     */
    public void setUiBunshoInfoDao(UIBunshoInfoDao uiBunshoInfoDao) {
        this.uiBunshoInfoDao = uiBunshoInfoDao;
    }
}
