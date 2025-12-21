/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 登録内容チェック
 * @author kusama
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN004L01";


	/**
     * 登録内容のチェックを行う
     * @param hanyoRegistDto     
     * */
    public List execute(HanyoRegistDto hanyoRegistDto) {
    	// １．必須のパラメータが満たされていること。E0506（@パラメータ）
        validate(hanyoRegistDto);
        
		// ２．パラメータが妥当であること。E0505（@パラメータ）
    	// １．日付項目とシステム日付との大小チェックを行う。
    	// ①．パラメータ.下記チェック対象項目 ＜ パラメータ.システム日付の場合、
    	// ・【E0606】（“？？？？” , “本日以降の日付”）を発生させる。※“？？？？” には下記項目が入る。
    	// Ⅰ．パラメータ．登録画面モード＝ '0'の場合
    	// ＜チェック対象項目＞
    	// ・開催日FROM
    	// ・開催日TO
    	// ・本部(受付開始)
    	// ・本部(申込締切)
    	// ・本部(表示開始)
    	// ・本部(表示終了)
    	// ・オーナー(受付開始)
    	// ・オーナー(申込締切)
    	// ・オーナー(表示開始)
    	// ・オーナー(表示終了)
    	// Ⅱ．パラメータ．登録画面モード＝ '1'の場合
    	// ＜チェック対象項目＞
    	// ・開催日TO
    	// ・本部(申込締切)
    	// ・本部(表示終了)
    	// ・オーナー(申込締切)
    	// ・オーナー(表示終了)
//        String sysDate = hanyoRegistDto.getBirdDateInfo().getSysDate();

//--- delete 過去日付の入力を可能とする 参照）PI-270104-007.01
/*
        if (hanyoRegistDto.isEditKomoku()) {
            if (hanyoRegistDto.getUiEntryDateKaisai().getFromDt().compareTo(sysDate) < 0) {
                throw new NotRelevantException("開催日FROM", "本日以降の日付", "kaisaiFromDt", 0);
            }
            if (hanyoRegistDto.getUiEntryDateHonbuToroku().getFromDt().compareTo(sysDate) < 0) {
                throw new NotRelevantException("本部：受付開始", "本日以降の日付", "honbuTorokuFromDt", 0);
            }
            if (hanyoRegistDto.getUiEntryDateHonbuHyoji().getFromDt().compareTo(sysDate) < 0) {
                throw new NotRelevantException("本部：表示開始", "本日以降の日付", "honbuHyojiFromDt", 0);
            }
            if (hanyoRegistDto.getUiEntryDateOnerToroku().getFromDt().compareTo(sysDate) < 0) {
                throw new NotRelevantException("オーナー：受付開始", "本日以降の日付", "onerTorokuFromDt", 0);
            }
            if (hanyoRegistDto.getUiEntryDateOnerHyoji().getFromDt().compareTo(sysDate) < 0) {
                throw new NotRelevantException("オーナー：表示開始", "本日以降の日付", "onerHyojiFromDt", 0);
            }
        }
        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt().compareTo(sysDate) < 0) {
            throw new NotRelevantException("本部：申込締切", "本日以降の日付", "honbuTorokuToDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateHonbuHyoji().getToDt().compareTo(sysDate) < 0) {
            throw new NotRelevantException("本部：表示終了", "本日以降の日付", "honbuHyojiToDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateOnerToroku().getToDt().compareTo(sysDate) < 0) {
            throw new NotRelevantException("オーナー：申込締切", "本日以降の日付", "onerTorokuToDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateOnerHyoji().getToDt().compareTo(sysDate) < 0) {
            throw new NotRelevantException("オーナー：表示終了", "本日以降の日付", "onerHyojiToDt", 0);
        }
*/
    	// ２．日付項目の大小チェックを行う。
    	// １．パラメータ.開催日FROM ＞ パラメータ.開催日TOの場合、
    	// ・【E0606】（“開催日FROM” , “開催日TO以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateKaisai().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateKaisai().getToDt()) > 0) 
        {
            throw new NotRelevantException("開催日FROM", "開催日TO 以前の日付", "kaisaiFromDt", 0);
        }
    	// ２．パラメータ.オーナー(表示開始) ＞ パラメータ.オーナー(受付開始)の場合、
    	// ・【E0606】（“オーナー 表示開始” , “オーナー 受付開始 以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateOnerHyoji().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerToroku().getFromDt()) > 0) 
        {
            throw new NotRelevantException("オーナー：表示開始", "オーナー：申込開始 以前の日付", "onerHyojiFromDt", 0);
        }
    	// ３．パラメータ.オーナー(受付開始) ＞ パラメータ.オーナー(申込締切)の場合、
    	// ・【E0606】（“オーナー 受付開始” , “オーナー 申込締切 以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateOnerToroku().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerToroku().getToDt()) > 0) 
        {
            throw new NotRelevantException("オーナー：申込開始", "オーナー：申込終了 以前の日付", "onerTorokuFromDt", 0);
        }
    	// ４．パラメータ.オーナー(申込終了) ＞ パラメータ.オーナー(表示終了)の場合、
    	// ・【E0606】（“オーナー 申込終了” , “オーナー 表示終了 以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateOnerToroku().getToDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerHyoji().getToDt()) > 0) 
        {
            throw new NotRelevantException("オーナー：申込終了", "オーナー：表示終了 以前の日付", "onerHyojiFromDt", 0);
        }
    	// ５．パラメータ.本部(表示開始)) ＞ パラメータ.本部(受付開始)の場合、
    	// ・【E0606】（“本部 表示開始” , “本部 受付開始 以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateHonbuHyoji().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateHonbuToroku().getFromDt()) > 0) 
        {
            throw new NotRelevantException("本部：表示開始", "本部：申込開始 以前の日付", "honbuHyojiFromDt", 0);
        }
    	// ６．パラメータ.本部(受付開始) ＞ パラメータ.本部(申込終了)の場合、
    	// ・【E0606】（“本部 受付開始” , “本部 申込終了 以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt()) > 0) 
        {
            throw new NotRelevantException("本部：申込開始", "本部：申込終了 以前の日付", "honbuTorokuFromDt", 0);
        }
    	// ７．パラメータ.本部(申込終了) ＞ パラメータ.本部(表示終了)の場合、
    	// ・【E0606】（“本部 申込終了” , “本部 表示終了 以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt().compareTo(
                hanyoRegistDto.getUiEntryDateHonbuHyoji().getToDt()) > 0) 
        {
            throw new NotRelevantException("本部：申込終了", "本部：表示終了 以前の日付", "honbuTorokuFromDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateHonbuHyoji().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerHyoji().getFromDt()) > 0) 
        {
            throw new NotRelevantException("本部：表示開始", "オーナー：表示開始 以前の日付", "honbuHyojiFromDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getFromDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerToroku().getFromDt()) > 0) 
        {
            throw new NotRelevantException("本部：申込開始", "オーナー：申込開始 以前の日付", "honbuHyojiFromDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateHonbuHyoji().getToDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerHyoji().getToDt()) < 0) 
        {
            throw new NotRelevantException("本部：表示終了", "オーナー：表示終了 以降の日付", "honbuHyojiFromDt", 0);
        }
        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt().compareTo(
                hanyoRegistDto.getUiEntryDateOnerToroku().getToDt()) < 0) 
        {
            throw new NotRelevantException("本部：申込終了", "オーナー：申込終了 以降の日付", "honbuHyojiFromDt", 0);
        }
        
//    	// ８．パラメータ.オーナー(申込締切) ＞ パラメータ.オーナー(表示終了）の場合、
//    	// ・【E0606】（“オーナー 申込締切” , “オーナー 表示終了 以前の日付”）を発生させる。
//        if (hanyoRegistDto.getUiEntryDateOnerToroku().getToDt().compareTo(
//                hanyoRegistDto.getUiEntryDateOnerHyoji().getToDt()) > 0) 
//        {
//            throw new NotRelevantException("オーナー：申込締切", "オーナー：表示終了 以前の日付", "onerTorokuToDt", 0);
//        }
//    	// ９．パラメータ.本部(申込締切) ＞ パラメータ.本部(表示終了）の場合、
//    	// ・【E0606】（“本部 申込締切” , “本部 表示終了 以前の日付”）を発生させる。
//        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt().compareTo(
//                hanyoRegistDto.getUiEntryDateHonbuHyoji().getToDt()) > 0) 
//        {
//            throw new NotRelevantException("本部：申込締切", "本部：表示終了 以前の日付", "honbuTorokuToDt", 0);
//        }
    	// １０．パラメータ.オーナー(申込終了） ＞ パラメータ.開催日FROMの場合、
    	// ・【E0606】（“オーナー 申込終了” , “開催日FROM の前日以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateOnerToroku().getToDt().compareTo(
                hanyoRegistDto.getUiEntryDateKaisai().getFromDt()) > 0) 
        {
            throw new NotRelevantException("オーナー：申込終了", "開催日FROM 以前の日付", "onerTorokuToDt", 0);
        }
    	// １１．パラメータ.本部(申込終了） ＞ パラメータ.開催日FROMの場合、
    	// ・【E0606】（“本部 申込終了” , “開催日FROM の前日以前の日付”）を発生させる。
        if (hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt().compareTo(
                hanyoRegistDto.getUiEntryDateKaisai().getFromDt()) > 0) 
        {
            throw new NotRelevantException("本部：申込終了", "開催日FROM 以前の日付", "honbuTorokuToDt", 0);
        }
    	// ３．定員人数の大小チェックを行う。
    	// ①．パラメータ.申込定員 ＞ パラメータ.会場定員 の場合、
    	// ・【E0606】（“申込定員” , “会場定員以下の人数”）を発生させる。
    	if (hanyoRegistDto.getUiEntryMst().getNumberLimit().compareTo(
                hanyoRegistDto.getUiEntryMst().getPlaceLimit()) > 0) {
            throw new NotRelevantException("申込定員", "会場定員以下の人数", "numberLimit", 0);
        }
        
        return null;
    }

    /**
     * 必須、妥当性チェック
     */
    private void validate(HanyoRegistDto hanyoRegistDto) {
        DateVerifier dateVrifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        NumericVerifier numericVerifier = new NumericVerifier();
        /* 必須チェック */
        if (hanyoRegistDto.isEditKomoku() 
                && isNull(hanyoRegistDto.getUiEntryDateKaisai().getFromDt())) {
            throw new NotNullException("開催日FROM", "kaisaiFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && !dateVrifier.validate(hanyoRegistDto.getUiEntryDateKaisai().getFromDt())) {
            throw new InvalidInputException("開催日FROM", "kaisaiFromDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateKaisai().getToDt())) {
            throw new NotNullException("開催日TO", "kaisaiToDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateKaisai().getToDt())) {
            throw new InvalidInputException("開催日TO", "kaisaiToDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryMst().getEntryTitle())) {
            throw new NotNullException("開催名", "entryTitle", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryMst().getEntryPlace())) {
            throw new NotNullException("開催場所", "entryPlace", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryCourse().getCourseCd())) {
            throw new NotNullException("コース", "courseCd", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryCourse().getCourseName())) {
            throw new NotNullException("コース", "courseCd", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && isNull(hanyoRegistDto.getUiEntryDateOnerHyoji().getFromDt())) {
            throw new NotNullException("オーナー：表示開始", "onerHyojiFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && !dateVrifier.validate(hanyoRegistDto.getUiEntryDateOnerHyoji().getFromDt())) {
            throw new InvalidInputException("オーナー：表示開始", "onerHyojiFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && isNull(hanyoRegistDto.getUiEntryDateOnerToroku().getFromDt())) {
            throw new NotNullException("オーナー：申込開始", "onerTorokuFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && !dateVrifier.validate(hanyoRegistDto.getUiEntryDateOnerToroku().getFromDt())) {
            throw new InvalidInputException("オーナー：申込開始", "onerTorokuFromDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateOnerToroku().getToDt())) {
            throw new NotNullException("オーナー：申込終了", "onerTorokuToDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateOnerToroku().getToDt())) {
            throw new InvalidInputException("オーナー：申込終了", "onerTorokuToDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateOnerHyoji().getToDt())) {
            throw new NotNullException("オーナー：表示終了", "onerHyojiToDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateOnerHyoji().getToDt())) {
            throw new InvalidInputException("オーナー：表示終了", "onerHyojiToDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && isNull(hanyoRegistDto.getUiEntryDateHonbuHyoji().getFromDt())) {
            throw new NotNullException("本部：表示開始", "honbuHyojiFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && !dateVrifier.validate(hanyoRegistDto.getUiEntryDateHonbuHyoji().getFromDt())) {
            throw new InvalidInputException("本部：表示開始", "honbuHyojiFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && isNull(hanyoRegistDto.getUiEntryDateHonbuToroku().getFromDt())) {
            throw new NotNullException("本部：申込開始", "honbuTorokuFromDt", 0);
        }
        if (hanyoRegistDto.isEditKomoku() 
                && !dateVrifier.validate(hanyoRegistDto.getUiEntryDateHonbuToroku().getFromDt())) {
            throw new InvalidInputException("本部：申込開始", "honbuTorokuFromDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt())) {
            throw new NotNullException("本部：申込締切", "honbuTorokuToDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateHonbuToroku().getToDt())) {
            throw new InvalidInputException("本部：申込締切", "honbuTorokuToDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateHonbuHyoji().getToDt())) {
            throw new NotNullException("本部：表示終了", "honbuHyojiToDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateHonbuHyoji().getToDt())) {
            throw new InvalidInputException("本部：表示終了", "honbuHyojiToDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateKekka().getFromDt())) {
            throw new NotNullException("結果開始", "kekkaFromDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateKekka().getFromDt())) {
            throw new InvalidInputException("結果開始", "kekkaFromDt", 0);
        }
        if (isNull(hanyoRegistDto.getUiEntryDateKekka().getToDt())) {
            throw new NotNullException("結果終了", "kekkaToDt", 0);
        }
        if (!dateVrifier.validate(hanyoRegistDto.getUiEntryDateKekka().getToDt())) {
            throw new InvalidInputException("結果終了", "kekkaToDt", 0);
        }
        if (hanyoRegistDto.getUiEntryMst().getNumberLimit() == null) {
            throw new NotNullException("申込定員", "numberLimit", 0);
        }
        if (!numericVerifier.validate(hanyoRegistDto.getUiEntryMst().getNumberLimit())) {
            throw new InvalidInputException("申込定員", "numberLimit", 0);
        }
        if (hanyoRegistDto.getUiEntryMst().getNumberLimit().length() > 4) {
            throw new NotRelevantException("申込定員", "整数４桁以内", "numberLimit", 0);
        }
        if (hanyoRegistDto.getUiEntryMst().getPlaceLimit() == null) {
            throw new NotNullException("会場定員", "placeLimit", 0);
        }
        if (!numericVerifier.validate(hanyoRegistDto.getUiEntryMst().getPlaceLimit())) {
            throw new InvalidInputException("会場定員", "placeLimit", 0);
        }
        if (hanyoRegistDto.getUiEntryMst().getPlaceLimit().length() > 4) {
            throw new NotRelevantException("会場定員", "整数４桁以内", "placeLimit", 0);
        }
        
        /* レングスチェック */
        if (isLengthOver(hanyoRegistDto.getUiEntryMst().getEntryTitle(), 100)) {
            throw new NotRelevantException("開催名", "全角50文字または半角100文字以内", "entryTitle", 0);
        }
        if (isLengthOver(hanyoRegistDto.getUiEntryMst().getEntryPlace(), 50)) {
            throw new NotRelevantException("開催場所", "全角25文字または半角50文字以内", "entryPlace", 0);
        }
        if (isLengthOver(hanyoRegistDto.getUiEntryCourse().getCourseName(), 20)) {
            throw new NotRelevantException("コース", "全角10文字または半角20文字以内", "courseCd", 0);
        }
        if (isLengthOver(hanyoRegistDto.getUiEntryMst().getNote(), 100)) {
            throw new NotRelevantException("備考", "全角50文字または半角100文字以内", "note", 0);
        }
// add start 2007/01/19 inazawa マスタライセンス４次対応        
        if (isLengthOver(hanyoRegistDto.getUIEntryNotice().getNotice2(), 500)) {
            throw new NotRelevantException("注意事項", "全角250文字または半角500文字以内", "notice", 0);
        }
// add end        
    }
    
    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
    
    /**
     * レングスチェック
     * @return boolean true:チェックNG
     */
    private boolean isLengthOver(String val, int length) {
        if (val == null) {
            return false;
        }
        return val.trim().getBytes().length > length ? true : false;
    }
}