package jp.co.isid.mos.bird.office.formregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.office.formregist.dao.UIFormInfoDao;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.GetDefaultSubCategoryLogic;

/**
 * 
 * @author xytamura
 */
public class GetDefaultSubCategoryLogicImpl implements GetDefaultSubCategoryLogic {
    /* ロジックID */
    public static final String LOGIC_ID = "BOF003L08";
    
    private UIFormInfoDao uiFormInfoDao;
    
    /**
     * デフォルトサブカテゴリの取得
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String nengetu 対象年月
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @return int フォームの件数
     * @exception ApplicationException
     */
    public String execute(FormConditionDto dto, String userId) throws ApplicationException {
        // 事前条件
        validate(dto);
        String nengetu = dto.getNengetu();
        if(nengetu != null){
            nengetu += "%";
        }
        
        // フォーム一覧取得
        List listSubCate = getUiFormInfoDao()
                                .getDefaultSubCatery(
                                        nengetu,
                                        dto.getCateId(),
                                        userId);
        
        if (listSubCate == null || listSubCate.size() == 0) {
            return null;
        }
        
        UIFormInfo entity = (UIFormInfo) listSubCate.get(0);
        return entity.getSubCateId();
    }

    /**
     * 事前条件
     * @param FormConditionDto
     * @throws ApplicationException
     */
    private void validate(FormConditionDto dto) throws ApplicationException {
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
    public UIFormInfoDao getUiFormInfoDao() {
        return uiFormInfoDao;
    }
    /**
     * @param uiBunshoInfoDao uiBunshoInfoDao を設定。
     */
    public void setUiFormInfoDao(UIFormInfoDao uiFormInfoDao) {
        this.uiFormInfoDao = uiFormInfoDao;
    }
}
