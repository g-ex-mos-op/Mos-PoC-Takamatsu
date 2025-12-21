package jp.co.isid.mos.bird.config.summenuregist.action.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.util.SessionKeyUtil;
import jp.co.isid.mos.bird.common.entity.MstShanaiMenu;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.menusearch.dto.MenuSearchDto;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;
import jp.co.isid.mos.bird.config.summenuregist.action.SumMenuRegistEditAction;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistRequestDto;
import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;
import jp.co.isid.mos.bird.config.summenuregist.logic.CheckExistDataLogic;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.dto.ErrHtmlElementDto;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * メニュー集約マスタ設定 編集画面アクション
 * @author xnkusama
 *
 */
public class SumMenuRegistEditActionImpl implements SumMenuRegistEditAction {
    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A11";
    public static final String back_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A12";
    public static final String confirm_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A13";
    public static final String deleteOya_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A14";
    public static final String deleteKo_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A15";
    public static final String addKo_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A16";

    /* DTO */
    private SumMenuRegistDto sumMenuRegistDto;
    private SumMenuRegistRequestDto sumMenuRegistReqDto;
    private MenuSearchDto menuSearchDto;
    private ErrHtmlElementDto errHtmlElementDto;
    
    /* LOGIC */
    private CheckExistDataLogic sumMenuRegistCheckExistDataLogic;
    private SumMenuRegistLogic sumMenuRegistCheckRegistLogic;
    private SumMenuRegistLogic sumMenuRegistRegistLogic;

    /* OTHER */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    
    /* 画面パラメータ */
    private int deleteIndex = 0;
    
    /**
     * 戻る
     */
    public String back() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        // 条件画面へ戻る
        return SumMenuRegistConst.VIEW_ID_CONDITION;
    }

    /**
     * 確認
     */
    public String confirm() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //【ロジック】登録チェック実行
        getSumMenuRegistCheckRegistLogic().execute(getSumMenuRegistDto());
        //セッションキー作成
        createSessionKey();
        //確認画面へ遷移
        return SumMenuRegistConst.VIEW_ID_CONFIRM;
    }

    /**
     * 親メニュー削除
     */
    public String deleteOya() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        for (int i = 0; i < getSumMenuRegistDto().getListKoMenu().size(); i++) {
            MstSumMenu entity = (MstSumMenu) getSumMenuRegistDto().getListKoMenu().get(i);
            if (i == 0) {
                if (entity.isInsertFlg()) {
                    getSumMenuRegistDto().setListKoMenu(null);
                    break; 
                }
            }
            entity.setSakujoFlg(true);
        }
        if (getSumMenuRegistDto().getListKoMenu() != null) { 
            //【ロジック】登録処理を実行
            getSumMenuRegistRegistLogic().execute(getSumMenuRegistDto());
        }
/*        
        if (getSumMenuRegistDto().getListSumMenu() != null && getSumMenuRegistDto().getListSumMenu().isEmpty()) {
            getSumMenuRegistDto().setListSumMenu(null);
        }
        else {
            //セッションDTO．親メニュー一覧から該当の親メニューを削除
            for (Iterator ite = getSumMenuRegistDto().getListSumMenu().iterator(); ite.hasNext();) {
                MstSumMenu entity = (MstSumMenu) ite.next();
                if (entity.getSumMenuCd().equals(getSumMenuRegistDto().getSumMenuCd())) {
                    ite.remove();
                    break;
                }
            }
        }
*/        
        //セッションキー作成
        createSessionKey();
        return SumMenuRegistConst.VIEW_ID_CONDITION;
    }

    /**
     * 子メニュー削除
     */
    public String deleteKo() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        MstSumMenu entity = (MstSumMenu) getSumMenuRegistDto().getListKoMenu().get(getDeleteIndex());
        //セッションDTO．子メニューリストのパラメータ．削除Indexの要素が
        if (entity.isInsertFlg()) {
            //[集約メニューマスタ]．新規行フラグ ＝ trueの場合
            getSumMenuRegistDto().getListKoMenu().remove(getDeleteIndex());
        }
        else {
            //[集約メニューマスタ]．新規行フラグ ＝ falseの場合
            entity.setSakujoFlg(true);
        }
        //セッションキー作成
        createSessionKey();
        return null;
    }

    /**
     * 子メニュー追加
     */
    public String addKo() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //メニュー検索DTOへ情報をセット
        getMenuSearchDto().initialize();
        getMenuSearchDto().setRequesterViewId(SumMenuRegistConst.VIEW_ID_EDIT);
        getMenuSearchDto().setSelectMode(MenuSearchUtil.SELECT_MODE_MULTI);
        //メニュー選択画面へ遷移
        return SumMenuRegistConst.VIEW_ID_MENU_SEARCH;
    }

    /**
     * 初期処理
     */
    public String initialize() {
        //セッションキー作成
        createSessionKey();
        
        // メニュー検索画面から遷移された場合（子メニュー追加 アクションの戻り）
        if (MenuSearchUtil.ACTION_KIND_INIT != getMenuSearchDto().getActionKind()) {
            if (MenuSearchUtil.ACTION_KIND_SELECT == getMenuSearchDto().getActionKind()) {
                List listSelectMenu = getMenuSearchDto().getListSelectMenu();
                //メニュー検索DTOクリア
                getMenuSearchDto().initialize();
                getMenuSearchDto().setActionKind(MenuSearchUtil.ACTION_KIND_NULL);
                //子メニュー追加処理
                doKoMenuAdd(listSelectMenu);
            }
        }

        return null;
    }

    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String sessionKey = SessionKeyUtil.makeSessionKey();
        getSumMenuRegistReqDto().setSessionKey(sessionKey);
        getSumMenuRegistDto().setSessionKey(sessionKey);
    }
    
    /**
     * 初期処理（子メニュー追加）
     */
    private void doKoMenuAdd(List listMenu) {
        //2008/11/21 複数メニュー追加対応
        String firstMenuCd = "";
        // 【ロジック】登録済み情報チェックを実行
        List listCheckedMenu = getSumMenuRegistCheckExistDataLogic()
                                        .execute(getSumMenuRegistDto(), 
                                                 null,
                                                 listMenu,
                                                 CheckExistDataLogic.MODE_MENUADD);
        for (Iterator ite = listCheckedMenu.iterator(); ite.hasNext();) {
            MstShanaiMenu uiMenuInfo = (MstShanaiMenu) ite.next();
            if (CommonUtil.isNull(firstMenuCd)) {
                firstMenuCd = uiMenuInfo.getMenuCd();
            }
            //追加メニューが画面DTOのメニューリスト内にある場合は、新規追加せずに削除フラグを寝かせる
            boolean existDtoList = false;
            for (Iterator ite2 = getSumMenuRegistDto().getListKoMenu().iterator(); ite2.hasNext();) {
                MstSumMenu entityDtoList = (MstSumMenu) ite2.next();
                if (uiMenuInfo.getMenuCd().equals(entityDtoList.getMenuCd())) {
                    entityDtoList.setSakujoFlg(false);
                    existDtoList = true;
                    break;
                }
            }
            if (existDtoList) {
                continue;
            }
            // [集約メニューマスタ]を作成し、下記の値をセット
            MstSumMenu entitySumMenuKo = new MstSumMenu();
            MstSumMenu entitySumMenuOya = (MstSumMenu) getSumMenuRegistDto().getListKoMenu().get(0);
            entitySumMenuKo.setMenuCd(uiMenuInfo.getMenuCd());
            entitySumMenuKo.setSumMenuCd(entitySumMenuOya.getSumMenuCd());
            entitySumMenuKo.setMenuName(uiMenuInfo.getMenuNameKj());
            entitySumMenuKo.setSumMenuName(entitySumMenuOya.getSumMenuName());
            entitySumMenuKo.setConvValue("1");
            entitySumMenuKo.setInsertFlg(true);
            //子メニューをセッションDTOへセット
            getSumMenuRegistDto().getListKoMenu().add(0, entitySumMenuKo);
        }
        
        //子メニューリストをソート
        Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                MstSumMenu entity1 = (MstSumMenu) o1;
                MstSumMenu entity2 = (MstSumMenu) o2;
                
                String val1 = "";
                String val2 = "";
                if (SumMenuRegistConst.OYA_MENU_FLG.equals(entity1.getSumMenuFlg())) {
                    val1 = "0" + entity1.getMenuCd();
                }
                else {
                    val1 = "1" + entity1.getMenuCd();
                }
                if (SumMenuRegistConst.OYA_MENU_FLG.equals(entity2.getSumMenuFlg())) {
                    val2 = "0" + entity2.getMenuCd();
                }
                else {
                    val2 = "1" + entity2.getMenuCd();
                }
                
                return val1.compareTo(val2);
            }
        };
        Collections.sort(getSumMenuRegistDto().getListKoMenu(), comparator);
        //追加子メニューのHTML位置情報をセット
        //追加子メニューのインデックを取得
        for (int i = 0; i < getSumMenuRegistDto().getListKoMenu().size(); i++) {
            MstSumMenu entity = (MstSumMenu) getSumMenuRegistDto().getListKoMenu().get(i);
            if (firstMenuCd.equals(entity.getMenuCd())) {
                getErrHtmlElementDto().setHtmlElementIndex(i);
                getErrHtmlElementDto().setHtmlElementName("convValue");
                break;
            }
        }

        //追加子メニューでエラーがあった場合は、例外を発生
        if (!CommonUtil.isNull(getSumMenuRegistDto().getKoMenuAddErrMsg())) {
            String errMsg = getSumMenuRegistDto().getKoMenuAddErrMsg();
            getSumMenuRegistDto().setKoMenuAddErrMsg("");
            throw new GenericMessageException(errMsg);
        }
    }
    
    public SumMenuRegistDto getSumMenuRegistDto() {
        return sumMenuRegistDto;
    }

    public void setSumMenuRegistDto(SumMenuRegistDto sumMenuRegistDto) {
        this.sumMenuRegistDto = sumMenuRegistDto;
    }

    public SumMenuRegistRequestDto getSumMenuRegistReqDto() {
        return sumMenuRegistReqDto;
    }

    public void setSumMenuRegistReqDto(SumMenuRegistRequestDto sumMenuRegistReqDto) {
        this.sumMenuRegistReqDto = sumMenuRegistReqDto;
    }

    public MenuSearchDto getMenuSearchDto() {
        return menuSearchDto;
    }

    public void setMenuSearchDto(MenuSearchDto menuSearchDto) {
        this.menuSearchDto = menuSearchDto;
    }

    public CheckExistDataLogic getSumMenuRegistCheckExistDataLogic() {
        return sumMenuRegistCheckExistDataLogic;
    }

    public void setSumMenuRegistCheckExistDataLogic(
            CheckExistDataLogic sumMenuRegistCheckExistDataLogic) {
        this.sumMenuRegistCheckExistDataLogic = sumMenuRegistCheckExistDataLogic;
    }

    public int getDeleteIndex() {
        return deleteIndex;
    }

    public void setDeleteIndex(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    public SumMenuRegistLogic getSumMenuRegistCheckRegistLogic() {
        return sumMenuRegistCheckRegistLogic;
    }

    public void setSumMenuRegistCheckRegistLogic(
            SumMenuRegistLogic sumMenuRegistCheckRegistLogic) {
        this.sumMenuRegistCheckRegistLogic = sumMenuRegistCheckRegistLogic;
    }

    public SumMenuRegistLogic getSumMenuRegistRegistLogic() {
        return sumMenuRegistRegistLogic;
    }

    public void setSumMenuRegistRegistLogic(
            SumMenuRegistLogic sumMenuRegistRegistLogic) {
        this.sumMenuRegistRegistLogic = sumMenuRegistRegistLogic;
    }

    public ErrHtmlElementDto getErrHtmlElementDto() {
        return errHtmlElementDto;
    }

    public void setErrHtmlElementDto(ErrHtmlElementDto errHtmlElementDto) {
        this.errHtmlElementDto = errHtmlElementDto;
    }

}
