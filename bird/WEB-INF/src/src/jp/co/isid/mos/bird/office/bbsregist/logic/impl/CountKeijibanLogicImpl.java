/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic.impl;


import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.office.bbsregist.dao.UIKeijibanDao;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;
import jp.co.isid.mos.bird.office.bbsregist.logic.CountKeijibanLogic;

/**
 * 掲示板件数の取得
 * @author xytamura
 */
public class CountKeijibanLogicImpl implements CountKeijibanLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF003L02";

    /* 掲示板DAO */
    private UIKeijibanDao uIKeijibanDao;

    /*
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.office.bbsregist.logic.impl.CountKeijibanLogic#execute(jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto)
     */
    public int execute(KeijibanConditionDto dto) throws ApplicationException {
        // 事前条件
        validate(dto);

        String keyWord = dto.getKeyWord();
        String titleFlg = KeijibanConditionDto.FLG_OFF;
        if (dto.getTitleFlg()) {
            titleFlg = KeijibanConditionDto.FLG_ON;
        }
        String msgFlg = KeijibanConditionDto.FLG_OFF;
        if (dto.getMsgFlg()) {
            msgFlg = KeijibanConditionDto.FLG_ON;
        }
        String cateId = dto.getCateId();
        if("".equals(cateId)){
            cateId = null;
        }
        
        if (keyWord != null) {
            keyWord = "%" + keyWord + "%";
        }
        int count = 0;
        if (keyWord == null || keyWord.trim().length() == 0) {
            count = getUIKeijibanDao().countKeijiban(cateId);
        } else {
            count = getUIKeijibanDao().countKeijibanForSearchKeyword(keyWord, titleFlg,
                    msgFlg);
        }

        // 掲示板件数
        return count;
    }

    /**
     * 事前条件チェック
     * @param keyWord キーワード
     * @throws ApplicationException
     */
    private void validate(KeijibanConditionDto dto) throws ApplicationException {
        String keyWord = dto.getKeyWord();

        DefaultJapaneseVerifier djp = new DefaultJapaneseVerifier();
        LengthVerifier len80 = new LengthVerifier(80);

        // 検索文字
        if (keyWord != null && keyWord.trim().length() != 0) {
            if (!len80.validate(keyWord)) {
                throw new InvalidInputException("検索文字", "keyWord", null);
            }
            if (!djp.validate(keyWord)) {
                throw new InvalidInputException("検索文字", "keyWord", null);
            }
        }
        if (!dto.getTitleFlg() && !dto.getMsgFlg()) {
            throw new NotSelectedException("タイトル、又はメッセージ");
        }
    }

    /**
     * 掲示板DAOを取得します。
     * @return 掲示板DAO
     */
    public UIKeijibanDao getUIKeijibanDao() {
        return uIKeijibanDao;
    }

    /**
     * 掲示板DAOを設定します。
     * @param 掲示板DAO
     */
    public void setUIKeijibanDao(UIKeijibanDao keijibanDao) {
        uIKeijibanDao = keijibanDao;
    }

}
