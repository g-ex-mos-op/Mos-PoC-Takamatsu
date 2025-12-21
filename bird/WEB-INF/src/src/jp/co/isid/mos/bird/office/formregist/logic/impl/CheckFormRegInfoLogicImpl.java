package jp.co.isid.mos.bird.office.formregist.logic.impl;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.CheckFormRegInfoLogic;

/**
 * 登録内容のチェック
 * @author xytamura
 */
public class CheckFormRegInfoLogicImpl implements CheckFormRegInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L03";

    /**
     * 登録内容のチェック
     * @param FormRegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象共通DTO
     * @exception ApplicationException
     */
    public void execute(FormRegistFormDto dto, PublicTargetDto publicTargetDto)
            throws ApplicationException {
        UIFormInfo entity = dto.getEditEntity();
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
            throw new ConstraintsViolationException("掲載期間From <= 掲載期間Toで", "keisaiFrom", "formNaiyo");
        }

        /* ４．公開対象の選択チェック */
        if (publicTargetDto.getListTrnControlCompanySize() <= 0
                || publicTargetDto.getListTrnControlShozokuSize() <= 0
                || publicTargetDto.getListTrnControlGyotaiSize() <= 0) {
            throw new NotNullException("公開対象", "", "formNaiyo");
        }
        /* ５．説明の文字数チェック */
        if (entity.getDiscription() != null
                && entity.getDiscription().trim().length() != 0) {
            if (entity.getDiscription().getBytes().length > 800) {
                throw new InvalidInputException("説明");
            }
// add start xkhata 20070627
            DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
            if ( !dj.validate(entity.getDiscription())) {
                throw new InvalidInputException("説明");
            }
//  add end
        }
    }

    /**
     * 事前条件
     * @param FormRegistFormDto dto
     * @throws ApplicationException
     */
    private void validate(FormRegistFormDto dto) throws ApplicationException {
        UIFormInfo entity = dto.getEditEntity();
        String chkVal = "";
        //コードバリデーター
        CodeVerifier codeVerifier = new CodeVerifier(3);
        //日付バリデーター
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);

        // タイトル
        if (isNull(entity.getTitle())) {
            throw new NotNullException("タイトル", "formtitle", "formNaiyo");
        }
//        LengthVerifier lengthVerifierTitle = new LengthVerifier(40);
        LengthVerifier lengthVerifierTitle = new LengthVerifier(80);
        if (!lengthVerifierTitle.validate(entity.getTitle())) {
            throw new InvalidInputException("タイトル", "formtitle", "formNaiyo");
        }

// add start xkhata 20070627
        DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
        if (!dj.validate(entity.getTitle())) {
            throw new InvalidInputException("タイトル", "formtitle", "formNaiyo");
        }
        
        DefaultJapaneseVerifier djMsg = new DefaultJapaneseVerifier();
        if (!djMsg.validate(entity.getDiscription())) {
            throw new InvalidInputException("説明", "discription", null);
        }
        
// add end
        
        // 掲載期間From
        chkVal = entity.getPubDateFrom();
        if (chkVal == null) {
            throw new NotNullException("掲載期間From", "keisaiFrom", "formNaiyo");
        }
        if (!dateVerifier.validate(chkVal)) {
            throw new InvalidInputException("掲載期間From", "keisaiFrom",
                    "formNaiyo");
        }

        // 掲載期間To
        chkVal = entity.getPubDateTo();
        if (chkVal == null) {
            throw new NotNullException("掲載期間To", "keisaiTo", "formNaiyo");
        }
        if (!dateVerifier.validate(chkVal)) {
            throw new InvalidInputException("掲載期間From", "keisaiFrom",
                    "formNaiyo");
        }

//        // 情報制限
//        if (isNull(entity.getLimitKbn())) {
//            throw new NotNullException("情報制限", "infoLimit", "formNaiyo");
//        }

        // カテゴリID
        if (isNull(entity.getCateId())) {
            throw new NotNullException("カテゴリ", "cate", "formNaiyo");
        }
        if (!codeVerifier.validate(entity.getCateId())) {
            throw new InvalidInputException("カテゴリ", "cate", "formNaiyo");
        }

        // サブカテゴリID
        if (isNull(entity.getSubCateId())) {
            throw new NotNullException("サブカテゴリ", "subcate", "formNaiyo");
        }
        if (!codeVerifier.validate(entity.getCateId())) {
            throw new InvalidInputException("サブカテゴリ", "subcate", "formNaiyo");
        }

        // ファイル名
        if (isNull(entity.getFileName())) {
            throw new NotNullException("ファイル名", "fileName", "formFile");
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