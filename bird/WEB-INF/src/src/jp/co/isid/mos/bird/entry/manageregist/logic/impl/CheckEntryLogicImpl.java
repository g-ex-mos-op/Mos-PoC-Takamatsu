package jp.co.isid.mos.bird.entry.manageregist.logic.impl;

import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistCommon;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;
import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntrySelection;
import jp.co.isid.mos.bird.entry.manageregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

/**
 * 全国店長勉強会マスタ情報チェックロジック
 *
 * @author xjung
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {
    /** 全国店長勉強会マスタ情報チェックロジックID */
    public static final String LOGIC_ID = "BEN020L03";

    /** 大会名称桁数 */
    private int entryTitleSize;

    /** セレクション名称桁数 */
    private int selectionNameSize;

    /**
     * 全国店長勉強会マスタ情報の事前チェック(入力必須、妥当性、相関)を行う。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    public void execute(ManageRegistDto dto) {
        // 全国店長勉強会マスタ登録情報
        if (dto == null) {
            throw new NotNullException(ManageRegistConstants.MSG_MANAGE_REGIST_INFO);
        }
        // 全国店長勉強会マスタ情報
        if (dto.getUiEntryMst() == null) {
            throw new NotNullException(ManageRegistConstants.MSG_MANAGE_MST_INFO);
        }

        // 編集モードが新規登録又は、編集の場合
        if (ManageRegistConstants.EDIT_MODE_INSERT == dto.getEditMode()
            || ManageRegistConstants.EDIT_MODE_UPDATE == dto.getEditMode()) {
            checkRegistData(dto);
        // 編集モードが削除の場合
        } else if (ManageRegistConstants.EDIT_MODE_DELETE == dto.getEditMode()) {
            checkDeleteData(dto);
        }
    }

    /**
     * 新規登録、編集時の入力必須、妥当性、相関チェックを行う
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    private void checkRegistData(ManageRegistDto dto) {
        // 日付バリデータ
        DateVerifier dateVerifier = new DateVerifier();

        // 全角バリデータ
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();

        /* 入力必須・妥当性チェック */
        // 開催日開始
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOpenFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_OPEN_FROM,
                ManageRegistConstants.ID_OPEN_FROM, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getOpenFrom())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_OPEN_FROM,
                ManageRegistConstants.ID_OPEN_FROM, 0);
        }
        // 開催日終了
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOpenTo())) {
            throw new NotNullException(ManageRegistConstants.MSG_OPEN_TO,
                ManageRegistConstants.ID_OPEN_TO, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getOpenTo())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_OPEN_TO,
                ManageRegistConstants.ID_OPEN_TO, 0);
        }
        //「開催日開始＞開催日終了」の場合、エラー
        if (dto.getUiEntryMst().getOpenFrom().compareTo(dto.getUiEntryMst().getOpenTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_OPEN_FROM,
                ManageRegistConstants.MSG_OPEN_TO_PREV, ManageRegistConstants.ID_OPEN_FROM, 0);
        }
        // 大会名称
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getEntryTitle())) {
            throw new NotNullException(ManageRegistConstants.MSG_ENTRY_TITLE,
                ManageRegistConstants.ID_ENTRY_TITLE, 0);
        }        
        if (!zenkakuVerifier.validate(dto.getUiEntryMst().getEntryTitle())
            || dto.getUiEntryMst().getEntryTitle().trim().getBytes().length > getEntryTitleSize()) {
            throw new InputConstraintException(ManageRegistConstants.MSG_ENTRY_TITLE,
                ManageRegistConstants.MSG_ENTRY_TITLE_LMT, ManageRegistConstants.ID_ENTRY_TITLE, 0);
        }
        // オーナー：表示開始
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOnerDispFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_ONER_DISP_FROM,
                ManageRegistConstants.ID_ONER_DISP_FROM, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getOnerDispFrom())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_ONER_DISP_FROM,
                ManageRegistConstants.ID_ONER_DISP_FROM, 0);
        }
        // 本部：表示開始
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getHonbuDispFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_HONBU_DISP_FROM,
                ManageRegistConstants.ID_HONBU_DISP_FROM, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getHonbuDispFrom())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_HONBU_DISP_FROM,
                ManageRegistConstants.ID_HONBU_DISP_FROM, 0);
        }
        // オーナー：申込開始
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOnerEntryFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_ONER_ENTRY_FROM,
                ManageRegistConstants.ID_ONER_ENTRY_FROM, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getOnerEntryFrom())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_ONER_ENTRY_FROM,
                ManageRegistConstants.ID_ONER_ENTRY_FROM, 0);
        }
        // 本部：申込開始
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getHonbuEntryFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_HONBU_ENTRY_FROM,
                ManageRegistConstants.ID_HONBU_ENTRY_FROM, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getHonbuEntryFrom())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_HONBU_ENTRY_FROM,
                ManageRegistConstants.ID_HONBU_ENTRY_FROM, 0);
        }
        // オーナー：申込終了
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOnerEntryTo())) {
            throw new NotNullException(ManageRegistConstants.MSG_ONER_ENTRY_TO,
                ManageRegistConstants.ID_ONER_ENTRY_TO, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getOnerEntryTo())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_ONER_ENTRY_TO,
                ManageRegistConstants.ID_ONER_ENTRY_TO, 0);
        }
        // 本部：申込終了
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getHonbuEntryTo())) {
            throw new NotNullException(ManageRegistConstants.MSG_HONBU_ENTRY_TO,
                ManageRegistConstants.ID_HONBU_ENTRY_TO, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getHonbuEntryTo())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_HONBU_ENTRY_TO,
                ManageRegistConstants.ID_HONBU_ENTRY_TO, 0);
        }
        // オーナー：表示終了
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOnerDispTo())) {
            throw new NotNullException(ManageRegistConstants.MSG_ONER_DISP_TO,
                ManageRegistConstants.ID_ONER_DISP_TO, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getOnerDispTo())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_ONER_DISP_TO,
                ManageRegistConstants.ID_ONER_DISP_TO, 0);
        }
        // 本部：表示終了
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getHonbuDispTo())) {
            throw new NotNullException(ManageRegistConstants.MSG_HONBU_DISP_TO,
                ManageRegistConstants.ID_HONBU_DISP_TO, 0);
        }
        if (!dateVerifier.validate(dto.getUiEntryMst().getHonbuDispTo())) {
            throw new InvalidInputException(ManageRegistConstants.MSG_HONBU_DISP_TO,
                ManageRegistConstants.ID_HONBU_DISP_TO, 0);
        }
        // 申込区分
        if (dto.getEntryKbnList() == null || dto.getEntryKbnList().size() != dto.getEntryKbnSize()) {
            throw new NotNullException(ManageRegistConstants.MSG_ENTRY_KBN,
                ManageRegistConstants.ID_ENTRY_KBN, 0);
        }
        int count = 0;
        int focusIndex=0;
        for(int i = 0; i < dto.getEntryKbnList().size(); i++) {
            UIEntrySelection info = (UIEntrySelection) dto.getEntryKbnList().get(i);
            if(ManageRegistCommon.isNull(info.getSelectionName())) {
                if (count == 0) {
                    focusIndex = i;
                }
                count++;
            } else if (!zenkakuVerifier.validate(info.getSelectionName())
                || info.getSelectionName().trim().getBytes().length > getSelectionNameSize()) {
                throw new InputConstraintException(ManageRegistConstants.MSG_ENTRY_KBN,
                    ManageRegistConstants.MSG_SELECTION_LMT, ManageRegistConstants.ID_ENTRY_KBN, i);
            }
        }
        // 申込区分が二つ以上入力されていない場合エラー
        if (count > dto.getEntryKbnSize() - 2) {
            throw new GenericMessageException
                (ManageRegistConstants.MSG_ENTRY_KBN_REQ, ManageRegistConstants.ID_ENTRY_KBN, focusIndex);
        }
        // オプショナル
        if (dto.getOptionalList() == null || dto.getOptionalList().size() != dto.getOptionalSize()) {
            throw new NotNullException(ManageRegistConstants.MSG_OPTIONAL,
                ManageRegistConstants.ID_OPTIONAL, 0);
        }
        for(int i = 0; i < dto.getOptionalList().size(); i++) {
            UIEntrySelection info = (UIEntrySelection) dto.getOptionalList().get(i);
            if(!ManageRegistCommon.isNull(info.getSelectionName())
                && (!zenkakuVerifier.validate(info.getSelectionName())
                    || info.getSelectionName().trim().getBytes().length > getSelectionNameSize())) {
                throw new InputConstraintException(ManageRegistConstants.MSG_OPTIONAL,
                    ManageRegistConstants.MSG_SELECTION_LMT, ManageRegistConstants.ID_OPTIONAL, i);
            }
        }

        /* 相関チェック */
        //「オーナー：表示開始＞オーナー：申込開始」の場合、エラー
        if (dto.getUiEntryMst().getOnerDispFrom().compareTo(dto.getUiEntryMst().getOnerEntryFrom()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_ONER_DISP_FROM,
                ManageRegistConstants.MSG_ONER_ERTRY_FROM_PREV, ManageRegistConstants.ID_ONER_DISP_FROM, 0);
        }
        //「オーナー：申込開始＞オーナー：申込終了」の場合、エラー    
        if (dto.getUiEntryMst().getOnerEntryFrom().compareTo(dto.getUiEntryMst().getOnerEntryTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_ONER_ENTRY_FROM,
                ManageRegistConstants.MSG_ONER_ERTRY_TO_PREV, ManageRegistConstants.ID_ONER_ENTRY_FROM, 0);
        }
        //「オーナー：申込終了＞オーナー：表示終了」の場合、エラー
        if (dto.getUiEntryMst().getOnerEntryTo().compareTo(dto.getUiEntryMst().getOnerDispTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_ONER_ENTRY_TO,
                ManageRegistConstants.MSG_ONER_DISP_TO_PREV, ManageRegistConstants.ID_ONER_ENTRY_TO, 0);
        }            
        //「本部：表示開始＞本部：申込開始」の場合、エラー   
        if (dto.getUiEntryMst().getHonbuDispFrom().compareTo(dto.getUiEntryMst().getHonbuEntryFrom()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_HONBU_DISP_FROM,
                    ManageRegistConstants.MSG_HONBU_ERTRY_FROM_PREV, ManageRegistConstants.ID_HONBU_DISP_FROM, 0);
        }            
        //「本部：申込開始＞本部：申込終了」の場合、エラー
        if (dto.getUiEntryMst().getHonbuEntryFrom().compareTo(dto.getUiEntryMst().getHonbuEntryTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_HONBU_ENTRY_FROM,
                    ManageRegistConstants.MSG_HONBU_ERTRY_TO_PREV, ManageRegistConstants.ID_HONBU_ENTRY_FROM, 0);
        }            
        //「本部：申込終了＞本部：表示終了」の場合、エラー
        if (dto.getUiEntryMst().getHonbuEntryTo().compareTo(dto.getUiEntryMst().getHonbuDispTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_HONBU_ENTRY_TO,
                ManageRegistConstants.MSG_HONBU_DISP_TO_PREV, ManageRegistConstants.ID_HONBU_ENTRY_TO, 0);
        }            
        //「本部：表示開始＞オーナー：表示開始」の場合、エラー
        if (dto.getUiEntryMst().getHonbuDispFrom().compareTo(dto.getUiEntryMst().getOnerDispFrom()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_HONBU_DISP_FROM,
                ManageRegistConstants.MSG_ONER_DISP_FROM_PREV, ManageRegistConstants.ID_HONBU_DISP_FROM, 0);
        }            
        //「本部：申込開始＞オーナー：申込開始」の場合、エラー
        if (dto.getUiEntryMst().getHonbuEntryFrom().compareTo(dto.getUiEntryMst().getOnerEntryFrom()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_HONBU_ENTRY_FROM,
                ManageRegistConstants.MSG_ONER_ERTRY_FROM_PREV, ManageRegistConstants.ID_HONBU_ENTRY_FROM, 0);
        }            
        //「オーナー：申込終了＞本部：申込終了」の場合、エラー
        if (dto.getUiEntryMst().getOnerEntryTo().compareTo(dto.getUiEntryMst().getHonbuEntryTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_ONER_ENTRY_TO,
                ManageRegistConstants.MSG_HONBU_ERTRY_TO_PREV, ManageRegistConstants.ID_ONER_ENTRY_TO, 0);
        }            
        //「オーナー：表示終了＞本部：表示終了」の場合、エラー
        if (dto.getUiEntryMst().getOnerDispTo().compareTo(dto.getUiEntryMst().getHonbuDispTo()) > 0) {
            throw new NotRelevantException(ManageRegistConstants.MSG_ONER_DISP_TO,
                ManageRegistConstants.MSG_HONBU_DISP_TO_PREV, ManageRegistConstants.ID_ONER_DISP_TO, 0);
        }            
        //「本部：申込終了≧開催開始日」の場合、エラー
        if (dto.getUiEntryMst().getHonbuEntryTo().compareTo(dto.getUiEntryMst().getOpenFrom()) > -1) {
            throw new NotRelevantException(ManageRegistConstants.MSG_HONBU_ENTRY_TO,
                ManageRegistConstants.MSG_OPEN_FROM_PREV, ManageRegistConstants.ID_HONBU_ENTRY_TO, 0);
        }     
    }

    /**
     * 削除時の入力必須、相関チェックを行う
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    private void checkDeleteData(ManageRegistDto dto) {
        if (ManageRegistCommon.isNull(dto.getSysDate())) {
            throw new NotNullException(ManageRegistConstants.MSG_SYS_DATE);
        }
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getOnerEntryFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_ONER_ENTRY_FROM);            
        }
        if (ManageRegistCommon.isNull(dto.getUiEntryMst().getHonbuEntryFrom())) {
            throw new NotNullException(ManageRegistConstants.MSG_HONBU_ENTRY_FROM);            
        }
        // オーナー申込開始≦システム日付又は本部申込開始≦システム日付の場合、削除不可
        if (dto.getUiEntryMst().getOnerEntryFrom().compareTo(dto.getSysDate()) < 1
            || dto.getUiEntryMst().getHonbuEntryFrom().compareTo(dto.getSysDate()) < 1) {
            throw new CannotExecuteWithReasonException(
                ManageRegistConstants.MSG_ENTRY_CHK, ManageRegistConstants.MSG_DELETE);
        }
    }

    /**
     * 店長勉強名称桁数を取得する
     * @return 店長勉強名称桁数
     */
    public int getEntryTitleSize() {
        return entryTitleSize;
    }

    /**
     * 店長勉強名称桁数を設定する
     * @param entryTitleSize 店長勉強名称桁数
     */
    public void setEntryTitleSize(int entryTitleSize) {
        this.entryTitleSize = entryTitleSize;
    }

    /**
     * セレクション名称桁数を取得する
     * @return セレクション名称桁数
     */
    public int getSelectionNameSize() {
        return selectionNameSize;
    }

    /**
     * セレクション名称桁数を設定する
     * @param selectionNameSize セレクション名称桁数
     */
    public void setSelectionNameSize(int selectionNameSize) {
        this.selectionNameSize = selectionNameSize;
    }    
}