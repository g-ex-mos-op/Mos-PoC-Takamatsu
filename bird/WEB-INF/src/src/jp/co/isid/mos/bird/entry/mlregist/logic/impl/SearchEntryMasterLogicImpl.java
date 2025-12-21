/*
 * 作成日: 2006/06/13
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.logic.impl;

import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryPlaceDao;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryMaster;
import jp.co.isid.mos.bird.entry.mlregist.logic.SearchEntryMasterLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author xyuchida
 */
public class SearchEntryMasterLogicImpl implements SearchEntryMasterLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN007L02";

    private UIEntryDateDao uiEntryDateDao;
    private UIEntryPlaceDao uiEntryPlaceDao;

    public UIEntryDateDao getUiEntryDateDao() {
        return uiEntryDateDao;
    }
    public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
        this.uiEntryDateDao = uiEntryDateDao;
    }
    public UIEntryPlaceDao getUiEntryPlaceDao() {
        return uiEntryPlaceDao;
    }
    public void setUiEntryPlaceDao(UIEntryPlaceDao uiEntryPlaceDao) {
        this.uiEntryPlaceDao = uiEntryPlaceDao;
    }

    public void execute(MlRegistDto mlRegistDto) {

        // エントリーマスタ管理
        UIEntryMaster uiEntryMaster = new UIEntryMaster();
        try {
            PropertyUtils.copyProperties(
                    uiEntryMaster,
                    mlRegistDto.getMlEntryList().get(mlRegistDto.getSelectIndex()));
        } catch (Exception e) {
            throw new FtlSystemException("マスタライセンスマスタ登録");
        }
        mlRegistDto.setUiEntryMaster(uiEntryMaster);

        // キー項目取得
        String entryCd = uiEntryMaster.getEntryCd();
        String entryYear = uiEntryMaster.getEntryYear();
        String entryKai = uiEntryMaster.getEntryKai();

        // エントリー日付管理
        // 開催日
        mlRegistDto.setUiEntryDateOpen(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_OTHER, MlRegistConstants.DAY_KBN_OPEN));
        // 表示：オーナー
        mlRegistDto.setUiEntryDateDisplayOner(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_OWNER, MlRegistConstants.DAY_KBN_DISPLAY));
        // 表示：本部
        mlRegistDto.setUiEntryDateDisplayHead(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_HONBU, MlRegistConstants.DAY_KBN_DISPLAY));
        // 申込：オーナー
        mlRegistDto.setUiEntryDateApplyOner(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_OWNER, MlRegistConstants.DAY_KBN_APPLY));
        // 申込：本部
        mlRegistDto.setUiEntryDateApplyHead(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_HONBU, MlRegistConstants.DAY_KBN_APPLY));
        // 結果登録
        mlRegistDto.setUiEntryDateRegist(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_HONBU, MlRegistConstants.DAY_KBN_REGIST));
        // 結果表示：オーナー
        mlRegistDto.setUiEntryDateResultOner(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_OWNER, MlRegistConstants.DAY_KBN_RESULT));
        // 結果表示：本部
        mlRegistDto.setUiEntryDateResultHead(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, MlRegistConstants.USERTYPE_CODE_HONBU, MlRegistConstants.DAY_KBN_RESULT));

        // エントリー受験地管理
        mlRegistDto.setUiEntryPlaceList(getUiEntryPlaceDao().getEntryPlaceList(entryCd, entryYear, entryKai));
    }
}
