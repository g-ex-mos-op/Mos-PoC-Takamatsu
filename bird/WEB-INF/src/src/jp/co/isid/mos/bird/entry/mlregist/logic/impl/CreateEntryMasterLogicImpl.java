/*
 * 作成日: 2006/06/14
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryPlaceDao;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryMaster;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryPlace;
import jp.co.isid.mos.bird.entry.mlregist.logic.CreateEntryMasterLogic;

/**
 * @author xyuchida
 */
public class CreateEntryMasterLogicImpl implements CreateEntryMasterLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN007L03";

    private UIEntryPlaceDao uiEntryPlaceDao;

    public UIEntryPlaceDao getUiEntryPlaceDao() {
        return uiEntryPlaceDao;
    }
    public void setUiEntryPlaceDao(UIEntryPlaceDao uiEntryPlaceDao) {
        this.uiEntryPlaceDao = uiEntryPlaceDao;
    }

    public void execute(MlRegistDto mlRegistDto) {

        String entryCd = MlRegistConstants.ENTRY_CD_ML;

        // エントリーマスタ管理
        UIEntryMaster uiEntryMaster = new UIEntryMaster();
        uiEntryMaster.setEntryCd(entryCd);
        uiEntryMaster.setEntryPlace("");
        uiEntryMaster.setNumberLimit(BigDecimal.valueOf(0));
        uiEntryMaster.setPlaceLimit(BigDecimal.valueOf(0));
        uiEntryMaster.setSpareFlg1("");
        uiEntryMaster.setSpareFlg2("");
        uiEntryMaster.setNote("");
        uiEntryMaster.setSakujoFlg(MlRegistConstants.SAKUJO_FLG_OFF);
        mlRegistDto.setUiEntryMaster(uiEntryMaster);

        // エントリー日付管理
        // 開催日
        UIEntryDate uiEntryDateOpen = new UIEntryDate();;
        uiEntryDateOpen.setEntryCd(entryCd);
        uiEntryDateOpen.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_OTHER);
        uiEntryDateOpen.setDayKbn(MlRegistConstants.DAY_KBN_OPEN);
        mlRegistDto.setUiEntryDateOpen(uiEntryDateOpen);
        // 表示：オーナー
        UIEntryDate uiEntryDateDisplayOner = new UIEntryDate();;
        uiEntryDateDisplayOner.setEntryCd(entryCd);
        uiEntryDateDisplayOner.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_OWNER);
        uiEntryDateDisplayOner.setDayKbn(MlRegistConstants.DAY_KBN_DISPLAY);
        mlRegistDto.setUiEntryDateDisplayOner(uiEntryDateDisplayOner);
        // 表示：本部
        UIEntryDate uiEntryDateDisplayHead = new UIEntryDate();;
        uiEntryDateDisplayHead.setEntryCd(entryCd);
        uiEntryDateDisplayHead.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateDisplayHead.setDayKbn(MlRegistConstants.DAY_KBN_DISPLAY);
        mlRegistDto.setUiEntryDateDisplayHead(uiEntryDateDisplayHead);
        // 申込：オーナー
        UIEntryDate uiEntryDateApplyOner = new UIEntryDate();;
        uiEntryDateApplyOner.setEntryCd(entryCd);
        uiEntryDateApplyOner.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_OWNER);
        uiEntryDateApplyOner.setDayKbn(MlRegistConstants.DAY_KBN_APPLY);
        mlRegistDto.setUiEntryDateApplyOner(uiEntryDateApplyOner);
        // 申込：本部
        UIEntryDate uiEntryDateApplyHead = new UIEntryDate();;
        uiEntryDateApplyHead.setEntryCd(entryCd);
        uiEntryDateApplyHead.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateApplyHead.setDayKbn(MlRegistConstants.DAY_KBN_APPLY);
        mlRegistDto.setUiEntryDateApplyHead(uiEntryDateApplyHead);
        // 結果登録
        UIEntryDate uiEntryDateRegist = new UIEntryDate();;
        uiEntryDateRegist.setEntryCd(entryCd);
        uiEntryDateRegist.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateRegist.setDayKbn(MlRegistConstants.DAY_KBN_REGIST);
        mlRegistDto.setUiEntryDateRegist(uiEntryDateRegist);
        // 結果表示：オーナー
        UIEntryDate uiEntryDateResultOner = new UIEntryDate();;
        uiEntryDateResultOner.setEntryCd(entryCd);
        uiEntryDateResultOner.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_OWNER);
        uiEntryDateResultOner.setDayKbn(MlRegistConstants.DAY_KBN_RESULT);
        mlRegistDto.setUiEntryDateResultOner(uiEntryDateResultOner);
        // 結果表示：本部
        UIEntryDate uiEntryDateResultHead = new UIEntryDate();;
        uiEntryDateResultHead.setEntryCd(entryCd);
        uiEntryDateResultHead.setUsertypeCd(MlRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateResultHead.setDayKbn(MlRegistConstants.DAY_KBN_RESULT);
        mlRegistDto.setUiEntryDateResultHead(uiEntryDateResultHead);

        // デフォルト受験地取得
        mlRegistDto.setUiEntryPlaceList(searchDefaultPlace(entryCd));
    }

    private List searchDefaultPlace(String entryCd) {
        List defaultPlaceList = getUiEntryPlaceDao().getDefaultPlaceList();
        for (Iterator it = defaultPlaceList.iterator(); it.hasNext();) {
            UIEntryPlace uiEntryPlace = (UIEntryPlace) it.next();
            uiEntryPlace.setEntryCd(entryCd);
        }
        return defaultPlaceList;
    }
}
