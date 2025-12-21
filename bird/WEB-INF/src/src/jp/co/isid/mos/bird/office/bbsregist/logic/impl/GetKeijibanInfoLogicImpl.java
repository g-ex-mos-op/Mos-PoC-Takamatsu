/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.office.bbsregist.dao.UIKeijibanDao;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;
import jp.co.isid.mos.bird.office.bbsregist.logic.GetKeijibanInfoLogic;

/**
 * 掲示板情報の取得
 * @author xytamura
 */
public class GetKeijibanInfoLogicImpl implements GetKeijibanInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF003L01";

    private static final int PAGE_MAX_SIZE = 30;

    /* 掲示板DAO */
    private UIKeijibanDao uIKeijibanDao;

    /*
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.office.bbsregist.logic.impl.GetKeijibanInfoLogic#execute(jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto)
     */
    public List execute(KeijibanConditionDto dto) throws ApplicationException {
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
        int page = dto.getCurrentPageNumber();

        int pageFrom = (page - 1) * PAGE_MAX_SIZE + 1;
        int pageTo = page * PAGE_MAX_SIZE;

        if (keyWord != null) {
            keyWord = "%" + keyWord + "%";
        }
        List listkeijiban = null;
        // 掲示板の
        if (keyWord == null || keyWord.trim().length() == 0) {
            listkeijiban = getUIKeijibanDao().getKeijibanInfo(cateId, pageFrom,
                    pageTo);
        } else {
            listkeijiban = getUIKeijibanDao().getKeijibanInfoForSearchKeyword(
                    keyWord, titleFlg, msgFlg, pageFrom, pageTo);
        }

        if (listkeijiban == null || listkeijiban.size() == 0) {
            throw new NoResultException();
        }

        return listkeijiban;
    }

    /**
     * 事前条件チェック
     * @param keyWord キーワード
     * @param page ページ
     * @throws ApplicationException
     */
    private void validate(KeijibanConditionDto dto) throws ApplicationException {
        String keyWord = dto.getKeyWord();
        int page = dto.getCurrentPageNumber();

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

        // ページ
        if (page == 0) {
            throw new InvalidInputException("ページ");
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