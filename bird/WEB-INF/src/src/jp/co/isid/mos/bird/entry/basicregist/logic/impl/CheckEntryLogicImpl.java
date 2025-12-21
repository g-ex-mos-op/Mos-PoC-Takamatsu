/*
 * 作成日: 2006/06/02
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.logic.impl;

import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * @author xyuchida
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN001L03";

    public void execute(BasicRegistDto basicRegistDto) {

        // 妥当性チェック
        validate(basicRegistDto);

        // 相関チェック
        checkCorrelation(basicRegistDto);
    }

    private void validate(BasicRegistDto basicRegistDto) {
        
        DateVerifier dateVerifier = new DateVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();

        // タイトル
        String entryTitle = basicRegistDto.getUiEntryMst().getEntryTitle();
        if (isNull(entryTitle)) {
            throw new NotNullException("タイトル", "entryTitle", 0);
        }
        if (isLengthOver(entryTitle, 100)) {
            throw new NotRelevantException("タイトル", "全角50文字または半角100文字以内", "entryTitle", 0);
        }

        // ベーシック研修FROM
        String basicFromDt = basicRegistDto.getUiEntryDateBasic().getFromDt();
        if (isNull(basicFromDt)) {
            throw new NotNullException("ベーシック研修FROM", "basicFromDt", 0);
        }
        if (!dateVerifier.validate(basicFromDt)) {
            throw new InvalidInputException("ベーシック研修FROM", "basicFromDt", 0);
        }
        // ベーシック研修TO
        String basicToDt = basicRegistDto.getUiEntryDateBasic().getToDt();
        if (isNull(basicToDt)) {
            throw new NotNullException("ベーシック研修TO", "basicToDt", 0);
        }
        if (!dateVerifier.validate(basicToDt)) {
            throw new InvalidInputException("ベーシック研修TO", "basicToDt", 0);
        }

        // 臨店実習コースFROM
        String visitFromDt = basicRegistDto.getUiEntryDateVisit().getFromDt();
        if (isNull(visitFromDt)) {
            throw new NotNullException("臨店実習コースFROM", "visitFromDt", 0);
        }
        if (!dateVerifier.validate(visitFromDt)) {
            throw new InvalidInputException("臨店実習コースFROM", "visitFromDt", 0);
        }
        // 臨店実習コースTO
        String visitToDt = basicRegistDto.getUiEntryDateVisit().getToDt();
        if (isNull(visitToDt)) {
            throw new NotNullException("臨店実習コースTO", "visitToDt", 0);
        }
        if (!dateVerifier.validate(visitToDt)) {
            throw new InvalidInputException("臨店実習コースTO", "visitToDt", 0);
        }

        // オーナー：表示開始
        String displayOnerFromDt = basicRegistDto.getUiEntryDateDisplayOner().getFromDt();
        if (isNull(displayOnerFromDt)) {
            throw new NotNullException("オーナー：表示開始", "displayOnerFromDt", 0);
        }
        if (!dateVerifier.validate(displayOnerFromDt)) {
            throw new InvalidInputException("オーナー：表示開始", "displayOnerFromDt", 0);
        }
        // オーナー：申込開始
        String applyOnerFromDt = basicRegistDto.getUiEntryDateApplyOner().getFromDt();
        if (isNull(applyOnerFromDt)) {
            throw new NotNullException("オーナー：申込開始", "applyOnerFromDt", 0);
        }
        if (!dateVerifier.validate(applyOnerFromDt)) {
            throw new InvalidInputException("オーナー：申込開始", "applyOnerFromDt", 0);
        }
        // オーナー：申込終了
        String applyOnerToDt = basicRegistDto.getUiEntryDateApplyOner().getToDt();
        if (isNull(applyOnerToDt)) {
            throw new NotNullException("オーナー：申込終了", "applyOnerToDt", 0);
        }
        if (!dateVerifier.validate(applyOnerToDt)) {
            throw new InvalidInputException("オーナー：申込終了", "applyOnerToDt", 0);
        }
        // オーナー：表示終了
        String displayOnerToDt = basicRegistDto.getUiEntryDateDisplayOner().getToDt();
        if (isNull(displayOnerToDt)) {
            throw new NotNullException("オーナー：表示終了", "displayOnerToDt", 0);
        }
        if (!dateVerifier.validate(displayOnerToDt)) {
            throw new InvalidInputException("オーナー：表示終了", "displayOnerToDt", 0);
        }

        // 本部：表示開始
        String displayHeadFromDt = basicRegistDto.getUiEntryDateDisplayHead().getFromDt();
        if (isNull(displayHeadFromDt)) {
            throw new NotNullException("本部：表示開始", "displayHeadFromDt", 0);
        }
        if (!dateVerifier.validate(displayHeadFromDt)) {
            throw new InvalidInputException("本部：表示開始", "displayHeadFromDt", 0);
        }
        // 本部：申込開始
        String applyHeadFromDt = basicRegistDto.getUiEntryDateApplyHead().getFromDt();
        if (isNull(applyHeadFromDt)) {
            throw new NotNullException("本部：申込開始", "applyHeadFromDt", 0);
        }
        if (!dateVerifier.validate(applyHeadFromDt)) {
            throw new InvalidInputException("本部：申込開始", "applyHeadFromDt", 0);
        }
        // 本部：申込終了
        String applyHeadToDt = basicRegistDto.getUiEntryDateApplyHead().getToDt();
        if (isNull(applyHeadToDt)) {
            throw new NotNullException("本部：申込終了", "applyHeadToDt", 0);
        }
        if (!dateVerifier.validate(applyHeadToDt)) {
            throw new InvalidInputException("本部：申込終了", "applyHeadToDt", 0);
        }
        // 本部：表示終了
        String displayHeadToDt = basicRegistDto.getUiEntryDateDisplayHead().getToDt();
        if (isNull(displayHeadToDt)) {
            throw new NotNullException("本部：表示終了", "displayHeadToDt", 0);
        }
        if (!dateVerifier.validate(displayHeadToDt)) {
            throw new InvalidInputException("本部：表示終了", "displayHeadToDt", 0);
        }

        // 結果開始
        String resultFromDt = basicRegistDto.getUiEntryDateResult().getFromDt();
        if (isNull(resultFromDt)) {
            throw new NotNullException("結果開始", "resultFromDt", 0);
        }
        if (!dateVerifier.validate(resultFromDt)) {
            throw new InvalidInputException("結果開始", "resultFromDt", 0);
        }
        // 結果終了
        String resultToDt = basicRegistDto.getUiEntryDateResult().getToDt();
        if (isNull(resultToDt)) {
            throw new NotNullException("結果終了", "resultToDt", 0);
        }
        if (!dateVerifier.validate(resultToDt)) {
            throw new InvalidInputException("結果終了", "resultToDt", 0);
        }

        // 定員
        String numberLimit = basicRegistDto.getUiEntryMst().getNumberLimit();
        if (isNull(numberLimit)) {
            throw new NotNullException("定員", "numberLimit", 0);
        }
        if (!numericVerifier.validate(numberLimit)) {
            throw new InvalidInputException("定員", "numberLimit", 0);
        }
        if (numberLimit.length() > 4) {
            throw new NotRelevantException("定員", "整数４桁以内", "numberLimit", 0);
        }

        // 備考
        String note = basicRegistDto.getUiEntryMst().getNote();
        if (isLengthOver(note, 100)) {
            throw new NotRelevantException("備考", "全角50文字または半角100文字以内", "note", 0);
        }

        // コースコード
        String courseCd = basicRegistDto.getUiEntryCourse().getCourseCd();
        if (isNull(courseCd)) {
            throw new NotNullException("コース", "courseCd", 0);
        }
        // コース名称
        String courseName = basicRegistDto.getUiEntryCourse().getCourseName();
        if (isNull(courseName)) {
            throw new NotNullException("コース", "courseCd", 0);
        }
        if (isLengthOver(courseName, 20)) {
            throw new NotRelevantException("コース", "全角10文字または半角20文字以内", "courseCd", 0);
        }
    }

    private void checkCorrelation(BasicRegistDto basicRegistDto) {

        // ベーシック研修FROM > ベーシック研修TO
        String basicFromDt = basicRegistDto.getUiEntryDateBasic().getFromDt();
        String basicToDt = basicRegistDto.getUiEntryDateBasic().getToDt();
        if (basicFromDt.compareTo(basicToDt) > 0) {
            throw new NotRelevantException("ベーシック研修FROM", "ベーシック研修TO 以前の日付", "basicFromDt", 0);
        }
        // 臨店実習コースFROM > 臨店実習コースTO
        String visitFromDt = basicRegistDto.getUiEntryDateVisit().getFromDt();
        String visitToDt = basicRegistDto.getUiEntryDateVisit().getToDt();
        if (visitFromDt.compareTo(visitToDt) > 0) {
            throw new NotRelevantException("臨店実習コースFROM", "臨店実習コースTO 以前の日付", "visitFromDt", 0);
        }

        // オーナー：表示開始 > オーナー：申込開始
        String displayOnerFromDt = basicRegistDto.getUiEntryDateDisplayOner().getFromDt();
        String applyOnerFromDt = basicRegistDto.getUiEntryDateApplyOner().getFromDt();
        if (displayOnerFromDt.compareTo(applyOnerFromDt) > 0) {
            throw new NotRelevantException("オーナー：表示開始", "オーナー：申込開始 以前の日付", "displayOnerFromDt", 0);
        }
        // オーナー：申込開始 > オーナー：申込終了
        String applyOnerToDt = basicRegistDto.getUiEntryDateApplyOner().getToDt();
        if (applyOnerFromDt.compareTo(applyOnerToDt) > 0) {
            throw new NotRelevantException("オーナー：申込開始", "オーナー：申込終了 以前の日付", "applyOnerFromDt", 0);
        }
        // オーナー：申込終了 > オーナー：表示終了
        String displayOnerToDt = basicRegistDto.getUiEntryDateDisplayOner().getToDt();
        if (applyOnerToDt.compareTo(displayOnerToDt) > 0) {
            throw new NotRelevantException("オーナー：申込終了", "オーナー：表示終了 以前の日付", "applyOnerToDt", 0);
        }

        // 本部：表示開始 > 本部：申込開始
        String displayHeadFromDt = basicRegistDto.getUiEntryDateDisplayHead().getFromDt();
        String applyHeadFromDt = basicRegistDto.getUiEntryDateApplyHead().getFromDt();
        if (displayHeadFromDt.compareTo(applyHeadFromDt) > 0) {
            throw new NotRelevantException("本部：表示開始", "本部：申込開始 以前の日付", "displayHeadFromDt", 0);
        }
        // 本部：申込開始 > 本部：申込終了
        String applyHeadToDt = basicRegistDto.getUiEntryDateApplyHead().getToDt();
        if (applyHeadFromDt.compareTo(applyHeadToDt) > 0) {
            throw new NotRelevantException("本部：申込開始", "本部：申込終了 以前の日付", "applyHeadFromDt", 0);
        }
        // 本部：申込終了 > 本部：表示終了
        String displayHeadToDt = basicRegistDto.getUiEntryDateDisplayHead().getToDt();
        if (applyHeadToDt.compareTo(displayHeadToDt) > 0) {
            throw new NotRelevantException("本部：申込終了", "本部：表示終了 以前の日付", "applyHeadToDt", 0);
        }

        // 結果開始 > 結果終了
        String resultFromDt = basicRegistDto.getUiEntryDateResult().getFromDt();
        String resultToDt = basicRegistDto.getUiEntryDateResult().getToDt();
        if (resultFromDt.compareTo(resultToDt) > 0) {
            throw new NotRelevantException("結果開始", "結果終了 以前の日付", "resultFromDt", 0);
        }

        // 本部：表示開始 > オーナー：表示開始
        if (displayHeadFromDt.compareTo(displayOnerFromDt) > 0) {
            throw new NotRelevantException("本部：表示開始", "オーナー：表示開始 以前の日付", "displayHeadFromDt", 0);
        }
        // 本部：申込開始 > オーナー：申込開始
        if (applyHeadFromDt.compareTo(applyOnerFromDt) > 0) {
            throw new NotRelevantException("本部：申込開始", "オーナー：申込開始 以前の日付", "applyHeadFromDt", 0);
        }
        // 本部：申込終了 < オーナー：申込終了
        if (applyHeadToDt.compareTo(applyOnerToDt) < 0) {
            throw new NotRelevantException("本部：申込終了", "オーナー：申込終了 以降の日付", "applyHeadToDt", 0);
        }
        // 本部：表示終了 < オーナー：表示終了
        if (displayHeadToDt.compareTo(displayOnerToDt) < 0) {
            throw new NotRelevantException("本部：表示終了", "オーナー：表示終了 以降の日付", "displayHeadToDt", 0);
        }
    }

    private boolean isNull(String val) {
        return val == null || val.trim().length() == 0;
    }

    private boolean isLengthOver(String val, int length) {
        return val != null && val.trim().getBytes().length > length;
    }
}
