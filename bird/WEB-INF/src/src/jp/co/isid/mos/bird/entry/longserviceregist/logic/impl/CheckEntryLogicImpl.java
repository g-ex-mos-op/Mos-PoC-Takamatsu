/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic.impl;


import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistCommon;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

/**
 * 登録内容チェック
 * @author kusama
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN017L03";

	/**
     * 登録内容のチェックを行う
     * @param hanyoRegistDto     
     * */
    public void execute(LongserviceRegistDto dto) {
    	
        if (dto == null) {
            throw new NotNullException("永年勤続マスタ登録情報");
        }
        if (dto.getUiEntryMst() == null) {
            throw new NotNullException("永年勤続マスタ情報");
        }
    	
        // 編集モードが新規登録又は、編集の場合
        if (LongserviceRegistConstants.EDIT_MODE_INSERT == dto.getEditMode()
            || LongserviceRegistConstants.EDIT_MODE_UPDATE == dto.getEditMode()) {
        	
            // 入力必須チェック/妥当性チェック
            checkValidity(dto);
            
            // 日付のＦｒｏｍＴｏ相関チェック
        	checkCorrelation(dto);

        // 編集モードが削除の場合
        } else if (LongserviceRegistConstants.EDIT_MODE_DELETE == dto.getEditMode()) {
            checkDeleteData(dto);
        } 	
    }

    /**
     * 日付のＦｒｏｍＴｏチェック
     */
    private void checkCorrelation(LongserviceRegistDto dto) {
    	
    	if (dto.getUiEntryMst().getOnerDispFrom().compareTo(dto.getUiEntryMst().getOnerEntryFrom()) > 0) 
        {
            throw new NotRelevantException("オーナー：表示開始", "オーナー：申込開始 以前の日付", LongserviceRegistConstants.FOCUS_ONER_DISP_FROM, 0);
        }
        if (dto.getUiEntryMst().getOnerEntryFrom().compareTo(dto.getUiEntryMst().getOnerEntryTo() ) > 0) 
        {
            throw new NotRelevantException("オーナー：申込開始", "オーナー：申込終了 以前の日付", LongserviceRegistConstants.FOCUS_ONER_ENTRY_FROM, 0);
        }
        if (dto.getUiEntryMst().getOnerEntryTo().compareTo(dto.getUiEntryMst().getOnerDispTo()) > 0) 
        {
            throw new NotRelevantException("オーナー：申込終了", "オーナー：表示終了 以前の日付", LongserviceRegistConstants.FOCUS_ONER_ENTRY_TO, 0);
        }
        if (dto.getUiEntryMst().getHonbuDispFrom().compareTo(dto.getUiEntryMst().getHonbuEntryFrom()) > 0) 
        {
            throw new NotRelevantException("本部：表示開始", "本部：申込開始 以前の日付", LongserviceRegistConstants.FOCUS_HONBU_DISP_FROM, 0);
        }
        if (dto.getUiEntryMst().getHonbuEntryFrom().compareTo(dto.getUiEntryMst().getHonbuEntryTo()) > 0) 
        {
            throw new NotRelevantException("本部：申込開始", "本部：申込終了 以前の日付", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_FROM, 0);
        }
        if (dto.getUiEntryMst().getHonbuEntryTo().compareTo(dto.getUiEntryMst().getHonbuDispTo()) > 0) 
        {
            throw new NotRelevantException("本部：申込終了", "本部：表示終了 以前の日付", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_TO, 0);
        }
        if (dto.getUiEntryMst().getHonbuDispFrom().compareTo(dto.getUiEntryMst().getOnerDispFrom()) > 0) 
        {
            throw new NotRelevantException("本部：表示開始", "オーナー：表示開始 以前の日付", LongserviceRegistConstants.FOCUS_HONBU_DISP_FROM, 0);
        }
        if (dto.getUiEntryMst().getHonbuEntryFrom().compareTo(dto.getUiEntryMst().getOnerEntryFrom()) > 0) 
        {
            throw new NotRelevantException("本部：申込開始", "オーナー：申込開始 以前の日付", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_FROM, 0);
        }
        if (dto.getUiEntryMst().getHonbuDispTo() .compareTo(dto.getUiEntryMst().getOnerDispTo()) < 0) 
        {
            throw new NotRelevantException("オーナー：表示終了", "本部：表示終了 以前の日付", LongserviceRegistConstants.FOCUS_ONER_DISP_TO, 0);
        }
        if (dto.getUiEntryMst().getHonbuEntryTo().compareTo(dto.getUiEntryMst().getOnerEntryTo()) < 0) 
        {
            throw new NotRelevantException("オーナー：申込終了", "本部：申込終了 以前の日付", LongserviceRegistConstants.FOCUS_ONER_ENTRY_TO, 0);
        }
    }
        
    /**
     * 妥当性チェック
     */
    private void checkValidity(LongserviceRegistDto dto) {
    	
        DateVerifier dateVrifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();

        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getEntryTitle())) {
            throw new NotNullException("イベント名称", LongserviceRegistConstants.FOCUS_TITLE, 0);
        }
        /* レングスチェック及び全角チェック*/
        if (LongserviceRegistCommon.isLengthOver(dto.getUiEntryMst().getEntryTitle(), 100) || 
        		!zenkakuVerifier.validate(dto.getUiEntryMst().getEntryTitle()) ){
            throw new NotRelevantException("イベント名称", "全角５０文字以内", LongserviceRegistConstants.FOCUS_TITLE, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getOnerDispFrom())) {
            throw new NotNullException("オーナー：表示開始", LongserviceRegistConstants.FOCUS_ONER_DISP_FROM, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getOnerDispFrom())) {
            throw new InvalidInputException("オーナー：表示開始", LongserviceRegistConstants.FOCUS_ONER_DISP_FROM, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getOnerEntryFrom())) {
            throw new NotNullException("オーナー：申込開始", LongserviceRegistConstants.FOCUS_ONER_ENTRY_FROM, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getOnerEntryFrom())) {
            throw new InvalidInputException("オーナー：申込開始",LongserviceRegistConstants.FOCUS_ONER_ENTRY_FROM, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getOnerEntryTo())) {
            throw new NotNullException("オーナー：申込終了", LongserviceRegistConstants.FOCUS_ONER_ENTRY_TO, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getOnerEntryTo())) {
            throw new InvalidInputException("オーナー：申込終了", LongserviceRegistConstants.FOCUS_ONER_ENTRY_TO, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getOnerDispTo())) {
            throw new NotNullException("オーナー：表示終了", LongserviceRegistConstants.FOCUS_ONER_DISP_TO, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getOnerDispTo())) {
            throw new InvalidInputException("オーナー：表示終了", LongserviceRegistConstants.FOCUS_ONER_DISP_TO, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getHonbuDispFrom())) {
            throw new NotNullException("本部：表示開始", LongserviceRegistConstants.FOCUS_HONBU_DISP_FROM, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getHonbuDispFrom())) {
            throw new InvalidInputException("本部：表示開始", LongserviceRegistConstants.FOCUS_HONBU_DISP_FROM, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getHonbuEntryFrom())) {
            throw new NotNullException("本部：申込開始", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_FROM, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getHonbuEntryFrom())) {
            throw new InvalidInputException("本部：申込開始", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_FROM, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getHonbuEntryTo())) {
            throw new NotNullException("本部：申込終了", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_TO, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getHonbuEntryTo())) {
            throw new InvalidInputException("本部：申込終了", LongserviceRegistConstants.FOCUS_HONBU_ENTRY_TO, 0);
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getHonbuDispTo())) {
            throw new NotNullException("本部：表示終了", LongserviceRegistConstants.FOCUS_HONBU_DISP_TO, 0);
        }
        if (!dateVrifier.validate(dto.getUiEntryMst().getHonbuDispTo())) {
            throw new InvalidInputException("本部：表示終了", LongserviceRegistConstants.FOCUS_HONBU_DISP_TO, 0);
        }
    }
    
    private void checkDeleteData(LongserviceRegistDto dto) {
        if (LongserviceRegistCommon.isNull(dto.getSysDate())) {
            throw new NotNullException("システム日付");
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getOnerEntryFrom())) {
            throw new NotNullException("オーナー申込開始日");            
        }
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getHonbuEntryFrom())) {
            throw new NotNullException("本部申込開始日");            
        }
        if (dto.getUiEntryMst().getOnerEntryFrom().compareTo(dto.getSysDate()) > 0
            || dto.getUiEntryMst().getHonbuEntryFrom().compareTo(dto.getSysDate()) > 0) {
            throw new CannotExecuteWithReasonException("申込が開始されている", "削除");
        }        
    }
}