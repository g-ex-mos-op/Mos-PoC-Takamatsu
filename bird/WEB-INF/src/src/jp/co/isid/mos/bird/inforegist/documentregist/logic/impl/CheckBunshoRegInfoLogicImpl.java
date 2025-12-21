package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.text.validator.CodeValidator;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.CheckBunshoRegInfoLogic;

public class CheckBunshoRegInfoLogicImpl implements CheckBunshoRegInfoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L02";

    private static final int DESCRIPTION_MAX_SIZE = 400;
    private static final int TITLE_MAX_SIZE = 80;
    
    /**
     * 既存の文書情報一覧の取得
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象共通DTO
     * @exception ApplicationException
     */
    public void checkBunshoRegInfo(DocumentRegistRegistFormDto dto, PublicTargetDto publicTargetDto) throws ApplicationException {
        UIBunshoInfo entity = dto.getEditEntity();
        /* １．必須チェック */
        validate(dto);

        /* ２．ファイルの登録チェック */
        // 添付タイトル１
        // 添付ファイル１オブジェクト
        if (!isNull(entity.getAttachName1())) {
            if (isNull(entity.getAttachFl1())) {
                throw new NotNullException("添付ファイルタイトル１を指定場合、添付ファイル１");
            }
        }
        // 添付タイトル２
        // 添付ファイル２オブジェクト
        if (!isNull(entity.getAttachName2())) {
            if (isNull(entity.getAttachFl2())) {
                throw new NotNullException("添付ファイルタイトル２を指定場合、添付ファイル２");
            }
        }
        // 添付タイトル３
        // 添付ファイル３オブジェクト
        if (!isNull(entity.getAttachName3())) {
            if (isNull(entity.getAttachFl3())) {
                throw new NotNullException("添付ファイルタイトル３を指定場合、添付ファイル３");
            }
        }
        
        /* ３．掲載期間の整合性チェック */
        if (entity.getPubDateFrom().compareTo(entity.getPubDateTo()) > 0) {
            throw new ConstraintsViolationException("掲載期間From ＜ 掲載期間Toで", "bunshoFrom", null);
        }
        
        /* ４．公開対象の選択チェック */
        if (publicTargetDto.getListTrnControlCompanySize() <= 0
                && publicTargetDto.getListTrnControlShozokuSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiKobetuSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiTenpoSize() <= 0) {
            throw new InvalidInputException("公開対象");
        }
        
        /* ５．説明の最大文字数チェック */
        LengthVerifier destVeri = new LengthVerifier( DESCRIPTION_MAX_SIZE );
        if( !destVeri.validate( entity.getDiscription()) ) {
        	throw new GenericCommentException("説明は全角２００文字まで", "bunshoDiscription", null);
        }
        
        /* ６．タイトルの最大文字数チェック */
        LengthVerifier titleVeri = new LengthVerifier( TITLE_MAX_SIZE );
        if ( !titleVeri.validate( entity.getTitle()) ) {
        	throw new GenericCommentException("タイトルは全角４０文字まで", "bunshoTitle", null);
        }
        
// 2006/03/22 xkhata 禁則文字対応
        /* 1.説明の禁則文字チェック */
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if ( !defJap.validate( entity.getDiscription() ) ) {
            throw new InvalidInputException("説明", "bunshoDiscription", null);
        }
        
        /* 2.タイトルの禁則文字チェック */
        if ( !defJap.validate( entity.getTitle() ) ) {
        	throw new InvalidInputException("タイトル", "bunshoTitle", null);
        }
    }
// end
    /**
     * 事前条件
     * @param RegistFormDto dto
     * @throws ApplicationException
     */
    private void validate(DocumentRegistRegistFormDto dto) throws ApplicationException {
        UIBunshoInfo entity = dto.getEditEntity();
        String chkVal = "";
        //コードバリデーター
        CodeValidator codeValidator = new CodeValidator();
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(DateVerifier.DATE_TYPE_YM);
        
        // タイトル
        if (isNull(entity.getTitle())) {
            throw new NotNullException("タイトル", "bunshoTitle", null);
        }

        // 掲載期間From
        chkVal = entity.getPubDateFrom();
        if (chkVal == null) {
            throw new NotNullException("掲載期間From", "bunshoFrom", null);
        }
        codeValidator.validate(chkVal);

        // 掲載期間To
        chkVal = entity.getPubDateTo();
        if (chkVal == null) {
            throw new NotNullException("掲載期間To", "bunshoTo", null);
        }
        dateValidator.validate(chkVal);


        // カテゴリID
        if (isNull(entity.getCateId())) {
            throw new NotNullException("カテゴリID", "bunshoCate", null);
        }
        codeValidator.validate(entity.getCateId());
        
        // サブカテゴリID
        if (isNull(entity.getSubCateId())) {
            throw new NotNullException("サブカテゴリID", "bunshoSubCate", null);
        }
        codeValidator.validate(entity.getSubCateId());

        // ファイル名
        if (isNull(entity.getFileName())) {
            throw new NotNullException("ファイル名",  "uploadFile", null);
        }
        
    }
    
    
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
}
