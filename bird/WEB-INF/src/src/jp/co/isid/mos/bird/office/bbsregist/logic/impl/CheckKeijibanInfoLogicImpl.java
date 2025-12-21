/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic.impl;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;
import jp.co.isid.mos.bird.office.bbsregist.logic.CheckKeijibanInfoLogic;

/**
 * 登録内容のチェック
 * @author xytamura
 */
public class CheckKeijibanInfoLogicImpl implements CheckKeijibanInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF003L03";

    /*
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.office.bbsregist.logic.impl.CheckKeijibanInfoLogic#execute(jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto)
     */
    public void execute(KeijibanRegistDto dto) throws ApplicationException {
        UIKeijiban entity = dto.getEditEntity();
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

    }

    /**
     * 事前条件
     * @param KeijibanRegistDto dto
     * @throws ApplicationException
     */
    private void validate(KeijibanRegistDto dto) throws ApplicationException {
        UIKeijiban entity = dto.getEditEntity();
        // コードバリデーター
        CodeVerifier codeVerifier = new CodeVerifier(3);

        // カテゴリID
        if (isNull(entity.getCateId())) {
            throw new NotNullException("カテゴリー", "keijibancate", null);
        }
        if (!codeVerifier.validate(entity.getCateId())) {
            throw new InvalidInputException("カテゴリー", "keijibancate", null);
        }

        // タイトル
        if (isNull(entity.getTitle())) {
            throw new NotNullException("タイトル", "keijibantitle", null);
        }
        LengthVerifier lengthVerifierTitle = new LengthVerifier(80);
        if (!lengthVerifierTitle.validate(entity.getTitle())) {
            throw new InvalidInputException("タイトル", "keijibantitle", null);
        }
// add start xkhata 20070627        
        DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
        if ( !dj.validate(entity.getTitle())) {
            throw new InvalidInputException("タイトル", "keijibantitle", null);
        }
// add end
        // メッセージ
        if (isNull(entity.getMessage())) {
            throw new NotNullException("メッセージ", "keijibanmsg", null);
        }
        LengthVerifier lengthVerifierMsg = new LengthVerifier(1200);
        if (!lengthVerifierMsg.validate(entity.getMessage())) {
            throw new InvalidInputException("メッセージ", "keijibanmsg", null);
        }
//      add start xkhata 20070627        
        DefaultJapaneseVerifier djMsg = new DefaultJapaneseVerifier();
        if ( !djMsg.validate(entity.getMessage())) {
            throw new InvalidInputException("メッセージ", "keijibanmsg", null);
        }
// add end
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