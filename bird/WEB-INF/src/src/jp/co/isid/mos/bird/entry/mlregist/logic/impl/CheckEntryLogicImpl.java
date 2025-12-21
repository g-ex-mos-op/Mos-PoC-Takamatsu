/*
 * 作成日: 2006/06/14
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.logic.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryPlace;
import jp.co.isid.mos.bird.entry.mlregist.logic.CheckEntryLogic;
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
    public static final String LOGIC_ID = "BEN007L04";

    public void execute(MlRegistDto mlRegistDto) {

        // 妥当性チェック
        validate(mlRegistDto);

        // 相関チェック
        checkCorrelation(mlRegistDto);
    }

    private void validate(MlRegistDto mlRegistDto) {

        NumericVerifier numericVerifier = new NumericVerifier(false, 6, 0);

        // タイトル
        String entryTitle = mlRegistDto.getUiEntryMaster().getEntryTitle();
        if (isNull(entryTitle)) {
            throw new NotNullException("タイトル", "entryTitle", 0);
        }
        if (isLengthOver(entryTitle, 100)) {
            throw new NotRelevantException("タイトル", "全角50文字または半角100文字以内", "entryTitle", 0);
        }

        // 開催開始
        DateVerifier openDateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
        String openFromDt = mlRegistDto.getUiEntryDateOpen().getFromDt();
        if (isNull(openFromDt)) {
            throw new NotNullException("開催開始", "openFromDt", 0);
        }
        if (!openDateVerifier.validate(openFromDt)) {
            throw new InvalidInputException("開催開始", "openFromDt", 0);
        }
        // 開催終了
        String openToDt = mlRegistDto.getUiEntryDateOpen().getToDt();
        if (isNull(openToDt)) {
            throw new NotNullException("開催終了", "openToDt", 0);
        }
        if (!openDateVerifier.validate(openToDt)) {
            throw new InvalidInputException("開催終了", "openToDt", 0);
        }

        // オーナー：表示開始
        validateDate(mlRegistDto.getUiEntryDateDisplayOner().getFromDt(), "オーナー：表示開始", "displayOnerFromDt");
        // オーナー：申込開始
        validateDate(mlRegistDto.getUiEntryDateApplyOner().getFromDt(), "オーナー：申込開始", "applyOnerFromDt");
        // オーナー：申込終了
        validateDate(mlRegistDto.getUiEntryDateApplyOner().getToDt(), "オーナー：申込終了", "applyOnerToDt");
        // オーナー：表示終了
        validateDate(mlRegistDto.getUiEntryDateDisplayOner().getToDt(), "オーナー：表示終了", "displayOnerToDt");

        // 本部：表示開始
        validateDate(mlRegistDto.getUiEntryDateDisplayHead().getFromDt(), "本部：表示開始", "displayHeadFromDt");
        // 本部：申込開始
        validateDate(mlRegistDto.getUiEntryDateApplyHead().getFromDt(), "本部：申込開始", "applyHeadFromDt");
        // 本部：申込終了
        validateDate(mlRegistDto.getUiEntryDateApplyHead().getToDt(), "本部：申込終了", "applyHeadToDt");
        // 本部：表示終了
        validateDate(mlRegistDto.getUiEntryDateDisplayHead().getToDt(), "本部：表示終了", "displayHeadToDt");

        // 結果開始
        validateDate(mlRegistDto.getUiEntryDateRegist().getFromDt(), "結果開始", "registFromDt");
        // 結果終了
        validateDate(mlRegistDto.getUiEntryDateRegist().getToDt(), "結果終了", "registToDt");

        // オーナー：結果表示開始
        validateDate(mlRegistDto.getUiEntryDateResultOner().getFromDt(), "オーナー：結果表示開始", "resultOnerFromDt");
        // オーナー：結果表示終了
        validateDate(mlRegistDto.getUiEntryDateResultOner().getToDt(), "オーナー：結果表示終了", "resultOnerToDt");
        // 本部：結果表示開始
        validateDate(mlRegistDto.getUiEntryDateResultHead().getFromDt(), "本部：結果表示開始", "resultHeadFromDt");
        // 本部：結果表示終了
        validateDate(mlRegistDto.getUiEntryDateResultHead().getToDt(), "本部：結果表示終了", "resultHeadToDt");

        // 備考
        String note = mlRegistDto.getUiEntryMaster().getNote();
        if (isLengthOver(note, 100)) {
            throw new NotRelevantException("備考", "全角50文字または半角100文字以内", "note", 0);
        }

        // 受験地
        for (Iterator it = mlRegistDto.getUiEntryPlaceList().iterator(); it.hasNext();) {
            if (isLengthOver(((UIEntryPlace) it.next()).getEntryPlaceName(), 20)) {
                throw new NotRelevantException("受験地", "全角10文字または半角20文字以内");
            }
        }
    }

    private void checkCorrelation(MlRegistDto mlRegistDto) {

        // 開催開始日 > 開催終了日
        String openFromDt = mlRegistDto.getUiEntryDateOpen().getFromDt();
        String openToDt = mlRegistDto.getUiEntryDateOpen().getToDt();
        if (openFromDt.compareTo(openToDt) > 0) {
            throw new NotRelevantException("開催開始日", "開催終了日 以前の日付", "openFromDt", 0);
        }

        // オーナー：表示開始 > オーナー：申込開始
        String displayOnerFromDt = mlRegistDto.getUiEntryDateDisplayOner().getFromDt();
        String applyOnerFromDt = mlRegistDto.getUiEntryDateApplyOner().getFromDt();
        if (displayOnerFromDt.compareTo(applyOnerFromDt) > 0) {
            throw new NotRelevantException("オーナー：表示開始", "オーナー：申込開始 以前の日付", "displayOnerFromDt", 0);
        }
        // オーナー：申込開始 > オーナー：申込終了
        String applyOnerToDt = mlRegistDto.getUiEntryDateApplyOner().getToDt();
        if (applyOnerFromDt.compareTo(applyOnerToDt) > 0) {
            throw new NotRelevantException("オーナー：申込開始", "オーナー：申込終了 以前の日付", "applyOnerFromDt", 0);
        }
        // オーナー：申込終了 > オーナー：表示終了
        String displayOnerToDt = mlRegistDto.getUiEntryDateDisplayOner().getToDt();
        if (applyOnerToDt.compareTo(displayOnerToDt) > 0) {
            throw new NotRelevantException("オーナー：申込終了", "オーナー：表示終了 以前の日付", "applyOnerToDt", 0);
        }

        // 本部：表示開始 > 本部：申込開始
        String displayHeadFromDt = mlRegistDto.getUiEntryDateDisplayHead().getFromDt();
        String applyHeadFromDt = mlRegistDto.getUiEntryDateApplyHead().getFromDt();
        if (displayHeadFromDt.compareTo(applyHeadFromDt) > 0) {
            throw new NotRelevantException("本部：表示開始", "本部：申込開始 以前の日付", "displayHeadFromDt", 0);
        }
        // 本部：申込開始 > 本部：申込終了
        String applyHeadToDt = mlRegistDto.getUiEntryDateApplyHead().getToDt();
        if (applyHeadFromDt.compareTo(applyHeadToDt) > 0) {
            throw new NotRelevantException("本部：申込開始", "本部：申込終了 以前の日付", "applyHeadFromDt", 0);
        }
        // 本部：申込終了 > 本部：表示終了
        String displayHeadToDt = mlRegistDto.getUiEntryDateDisplayHead().getToDt();
        if (applyHeadToDt.compareTo(displayHeadToDt) > 0) {
            throw new NotRelevantException("本部：申込終了", "本部：表示終了 以前の日付", "applyHeadToDt", 0);
        }

        // 結果登録開始 > 結果登録終了
        String registFromDt = mlRegistDto.getUiEntryDateRegist().getFromDt();
        String registToDt = mlRegistDto.getUiEntryDateRegist().getToDt();
        if (registFromDt.compareTo(registToDt) > 0) {
            throw new NotRelevantException("結果開始", "結果終了 以前の日付", "registFromDt", 0);
        }

        // 結果登録終了 > オーナー：結果表示開始
        String resultOnerFromDt = mlRegistDto.getUiEntryDateResultOner().getFromDt();
        if (registToDt.compareTo(resultOnerFromDt) > 0) {
            throw new NotRelevantException("結果終了", "オーナー：結果表示開始 以前の日付", "registToDt", 0);
        }
        // オーナー：結果表示開始 > オーナー：結果表示終了
        String resultOnerToDt = mlRegistDto.getUiEntryDateResultOner().getToDt();
        if (resultOnerFromDt.compareTo(resultOnerToDt) > 0) {
            throw new NotRelevantException("オーナー：結果表示開始", "オーナー：結果表示終了 以前の日付", "resultOnerFromDt", 0);
        }
        // 結果登録終了 > 本部：結果表示開始
        String resultHeadFromDt = mlRegistDto.getUiEntryDateResultHead().getFromDt();
        if (registToDt.compareTo(resultHeadFromDt) > 0) {
            throw new NotRelevantException("結果終了", "本部：結果表示開始 以前の日付", "registToDt", 0);
        }
        // 本部：結果表示開始 > 本部：結果表示終了
        String resultHeadToDt = mlRegistDto.getUiEntryDateResultHead().getToDt();
        if (resultHeadFromDt.compareTo(resultHeadToDt) > 0) {
            throw new NotRelevantException("本部：結果表示開始", "本部：結果表示終了 以前の日付", "resultHeadFromDt", 0);
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

        // 本部：結果表示開始 > オーナー：結果表示開始
        if (resultHeadFromDt.compareTo(resultOnerFromDt) > 0) {
            throw new NotRelevantException("本部：結果表示開始", "オーナー：結果表示開始 以前の日付", "resultHeadFromDt", 0);
        }
        // 本部：結果表示終了 < オーナー：結果表示終了
        if (resultHeadToDt.compareTo(resultOnerToDt) < 0) {
            throw new NotRelevantException("本部：結果表示終了", "オーナー：結果表示終了 以降の日付", "resultHeadToDt", 0);
        }
    }

    private void validateDate(String date, String message, String id) {
        DateVerifier dateVerifier = new DateVerifier();
        if (isNull(date)) {
            throw new NotNullException(message, id, 0);
        }
        if (!dateVerifier.validate(date)) {
            throw new InvalidInputException(message, id, 0);
        }
    }

    private boolean isNull(String val) {
        return val == null || val.trim().length() == 0;
    }

    private boolean isLengthOver(String val, int length) {
        try {
			return val != null && val.trim().getBytes("Windows-31j").length > length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return false;
    }
}
