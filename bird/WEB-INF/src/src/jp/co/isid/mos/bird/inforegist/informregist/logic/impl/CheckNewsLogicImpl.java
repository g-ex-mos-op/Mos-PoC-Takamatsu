/*
 * 作成日: 2006/2/9
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;
import jp.co.isid.mos.bird.inforegist.informregist.logic.CheckNewsLogic;

/**
 * 登録内容のチェック処理ロジック
 * @author itamoto
 */
public class CheckNewsLogicImpl implements CheckNewsLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BIF004L02";

    /**
     * 登録内容のチェック処理
     * @param uiNews
     */
    public List execute(UINews uiNews, PublicTargetDto publicTargetDto) {
    	
    	// 必須チェック
        if (isNull(uiNews.getTitle())) {
            throw new NotNullException("タイトル", "osiraseTitle", null);
        }
        if (isNull(uiNews.getPubDateFrom())) {
            throw new NotNullException("公開期間FROM", "pubDateFrom", null);
        }
        if (isNull(uiNews.getPubDateTo())) {
            throw new NotNullException("公開期間TO", "pubDateTo", null);
        }
        if (isNull(uiNews.getMessage())) {
            throw new NotNullException("メッセージ", "message", null);
        }
    	
    	// 1.各項目の制限文字数をチェック
    	// タイトルは全角40文字（半角80文字）以内
    	if (uiNews.getTitle().getBytes().length > 80) {
            throw new InvalidInputException("タイトル", "osiraseTitle", null);
    	}
    	// メッセージは全角1250文字(2500バイト)以内
    	if (uiNews.getMessage() != null
				&& !"".equals(uiNews.getMessage().trim())
				&& uiNews.getMessage().getBytes().length > 2500) {
			throw new NotRelevantException("メッセージ", "2500バイト以内", "message", null);
		}

    	// 2.文字型の欄に文字以外の型が入力されていないかチェック
    	DefaultJapaneseVerifier verifier = new DefaultJapaneseVerifier();
    	if (!verifier.validate(uiNews.getTitle())) {
            throw new InvalidInputException("タイトル", "osiraseTitle", null);
    	}
    	if (!verifier.validate(uiNews.getMessage())) {
            throw new InvalidInputException("メッセージ", "message", null);
		}    	

        if (uiNews.getPubDateFrom().compareTo(uiNews.getPubDateTo()) > 0) {
            throw new ConstraintsViolationException("公開期間FROM＜公開期間TO", "pubDateFrom", null);
        }
    	
    	// ３．公開対象が選択されていることのチェックを行う。
        if (publicTargetDto.getListTrnControlCompanySize() <= 0
                && publicTargetDto.getListTrnControlShozokuSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiKobetuSize() <= 0
                && publicTargetDto.getListTrnControlGyotaiTenpoSize() <= 0) {
            throw new InvalidInputException("公開対象");
        }
        return null;
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
