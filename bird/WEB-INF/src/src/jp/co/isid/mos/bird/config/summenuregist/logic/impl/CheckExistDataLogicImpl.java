package jp.co.isid.mos.bird.config.summenuregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstShanaiMenuDao;
import jp.co.isid.mos.bird.common.entity.MstShanaiMenu;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.config.summenuregist.dao.MstSumMenuDao;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;
import jp.co.isid.mos.bird.config.summenuregist.logic.CheckExistDataLogic;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 登録済み情報チェック
 * @author xnkusama
 * @modifire xkinu 2010/01/19 
 *       子メニュー選択画面から遷移時、親メニューの販売終了日(PC10SMNU.MENU_END_TD)が
 *       システム日付より過去の場合、システムエラーが発生する対応
 *       対応策：親メニュー情報取得条件のシステム日付は指定せず(null)に、
 *               検索するよう変更しました。
 */
public class CheckExistDataLogicImpl implements CheckExistDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BCF009L03";

    /*DAO*/
    private MstSumMenuDao sumMenuRegistMstSumMenuDao;
    private MstShanaiMenuDao mstShanaiMenuDao;
    
    /*MESSAGE*/
    private static final String ERR_MSG_01 = "のメニュー分類は、集約先メニューのメニュー分類と異なります。追加できません。";
    private static final String ERR_MSG_02 = "集約先メニューと同じメニューを子メニューとして追加することはできません。";
    private static final String ERR_MSG_03 = "は既に集約先メニューとして登録されています。子メニューとして登録することはできません。";
    private static final String ERR_MSG_04 = "は表示されています。追加できません。";
    private static final String ERR_MSG_05 = "の子メニューです。複数の集約先を持つことはできません。";
    private static final String ERR_MSG_06 = "<br/>子メニューを再選択する場合は、「子メニュー追加」リンクを押してください。";
    private static final String ERR_MSG_07 = "は既に集約先メニューとして登録されています。";
    private static final String ERR_MSG_08 = "の子メニューです。集約先メニューにすることはできません。";
    
    /**
     * 指定メニュー登録済みチェック
     * @param dto
     * @param menuCd
     * @param mode
     */
    public List execute(SumMenuRegistDto dto, String menuCd, List listMenuCd, int mode) {
        List listCheckedMenu = new ArrayList();
        // １．事前条件処理
        validate(menuCd, listMenuCd, mode);
        
        // ２．パラメータ．モード＝１（新規登録）の場合
        if (mode == MODE_NEW) {
            checkModeNew(menuCd);

            // ０．メニュー情報の取得
            List listOyaMenuCd = new ArrayList();
            listOyaMenuCd.add(menuCd);
            listCheckedMenu = getMstShanaiMenuDao().select(listOyaMenuCd, dto.getSysDate());
            
            if (listCheckedMenu == null || listCheckedMenu.isEmpty()) {
                throw new NotExistException("メニュー情報");
            }
        }
        // ３．パラメータ．モード＝２（子メニュー追加）の場合
        if (mode == MODE_MENUADD) {
            // 親メニュー情報の取得
            List listKoMenuCd = new ArrayList();
            String errMsg = "";
            String retMsg = null;
            listKoMenuCd.clear();
            listKoMenuCd.add(dto.getSumMenuCd());
            List listSumMenuInfo = getMstShanaiMenuDao().select(listKoMenuCd, null);
            MstShanaiMenu mstOyaMenu = (MstShanaiMenu) listSumMenuInfo.get(0);
            
            for (Iterator ite = listMenuCd.iterator(); ite.hasNext();) {
                retMsg = checkModeMenuAdd(dto, (String) ite.next(), mstOyaMenu, listCheckedMenu);
                if (!CommonUtil.isNull(retMsg)) {
                    if (!CommonUtil.isNull(errMsg)) {
                        retMsg = "<br/>" + retMsg;
                    }
                    errMsg = errMsg + retMsg; 
                }
            }
            if (!CommonUtil.isNull(errMsg)) {
                errMsg = errMsg + ERR_MSG_06;
                dto.setKoMenuAddErrMsg(errMsg);
            }
        }
        
        return listCheckedMenu;
    }

    /**
     * 新規モードチェック
     * @param menuCd
     */
    private void checkModeNew(String menuCd) {
        //【DAO】集約メニューマスタDao．集約メニュー一覧取得を実行
        List listSumMenu = getSumMenuRegistMstSumMenuDao().selectSumMenuList(menuCd);
        // データが取得できた場合はエラー
        if (!(listSumMenu == null || listSumMenu.isEmpty())) {
            MstSumMenu entity = (MstSumMenu) listSumMenu.get(0);
            throw new GenericMessageException(entity.getSumMenuName() + ERR_MSG_07);
        }
        //【DAO】集約メニューマスタDao．子メニュー登録済みチェックを実行
        MstSumMenu entity = getSumMenuRegistMstSumMenuDao().checkKoExist(menuCd);
        // データが取得でき、[集約メニューマスタ]．親メニューフラグ ≠ '１’の場合
        if (entity != null && !SumMenuRegistConst.OYA_MENU_FLG.equals(entity.getSumMenuFlg())) {
            throw new GenericMessageException(entity.getMenuName() +
                                               "は" +
                                               entity.getSumMenuName() +
                                               ERR_MSG_08);
        }
    }
    
    /**
     * 子メニュー追加モードチェック
     * @param menuCd
     */
    private String checkModeMenuAdd(SumMenuRegistDto dto, String menuCd, MstShanaiMenu mstOyaMenu, List listCheckedMenu) {
        // メニュー情報の取得
        List listMenuCd = new ArrayList();
        listMenuCd.add(menuCd);
        List listMenuInfo = getMstShanaiMenuDao().select(listMenuCd, dto.getSysDate());
        
        if (listMenuInfo == null || listMenuInfo.isEmpty()) {
            throw new NotExistException("メニュー情報");
        }
        MstShanaiMenu mstKoMenu = (MstShanaiMenu) listMenuInfo.get(0);
        
        // セッションDTO．集約メニューコード ＝ パラメータ．メニューコードの場合
        if (dto.getSumMenuCd().equals(menuCd)) {
            //add 2008/09/24 SP020405
            return ERR_MSG_02;
        }
        // メニュー分類チェック 
        if (!mstKoMenu.getMenuBunrui().equals(mstOyaMenu.getMenuBunrui())) {
            return mstKoMenu.getMenuNameKj() + ERR_MSG_01;
        }
        //【DAO】集約メニューマスタDao．集約メニュー一覧取得を実行
        List listSumMenu = getSumMenuRegistMstSumMenuDao().selectSumMenuList(menuCd);
        // データが取得できた場合はエラー
        if (!(listSumMenu == null || listSumMenu.isEmpty())) {
            MstSumMenu entity = (MstSumMenu) listSumMenu.get(0);
            return entity.getSumMenuName() + ERR_MSG_03;
        }
        // パラメータ．メニューがパラメータ．セッションDTO．子メニュー一覧に含まれている場合
        MstSumMenu entity;
        for (Iterator ite = dto.getListKoMenu().iterator(); ite.hasNext();) {
            entity = (MstSumMenu) ite.next();
            if (menuCd.equals(entity.getMenuCd())) {
                if (entity.isSakujoFlg()) {
                    //[集約メニューマスタ]．削除フラグ ＝ trueの場合、下記の値をセット
                    entity.setConvValue("1");
                    entity.setSakujoFlg(false);
                }
                else {
                    //[集約メニューマスタ]．削除フラグ ＝ falseの場合、下記の値をセット
                    return entity.getMenuName() + ERR_MSG_04;
                }
            }
        }
        //【DAO】集約メニューマスタDao．子メニュー登録済みチェックを実行
        entity = getSumMenuRegistMstSumMenuDao().checkKoExist(menuCd);
        // データが取得でき、[集約メニューマスタ]．親メニューフラグ ≠ '１’の場合
        if (entity != null && !SumMenuRegistConst.OYA_MENU_FLG.equals(entity.getSumMenuFlg())
                && !entity.getSumMenuCd().equals(mstOyaMenu.getMenuCd())) 
        {
            return entity.getMenuName() + "は" + entity.getSumMenuName() + ERR_MSG_05;
        }
        
        //正常の場合、DTOへメニュー情報を追加
        listCheckedMenu.add(mstKoMenu);
        //正常終了。エラーMSGなし
        return null;
    }
    
    /**
     * 初期処理
     * @param menuCd
     */
    private void validate(String menuCd, List listMenu, int mode) {
        //メニューコード パラメータチェック
        if (mode == MODE_NEW) {
            if (CommonUtil.isNull(menuCd)) {
                throw new NotNullException("メニューコード");
            }
        }
        else if (mode == MODE_MENUADD) {
            if (listMenu == null || listMenu.isEmpty()) {
                throw new NotNullException("メニューコード");
            }
        }
    }

    public MstSumMenuDao getSumMenuRegistMstSumMenuDao() {
        return sumMenuRegistMstSumMenuDao;
    }

    public void setSumMenuRegistMstSumMenuDao(
            MstSumMenuDao sumMenuRegistMstSumMenuDao) {
        this.sumMenuRegistMstSumMenuDao = sumMenuRegistMstSumMenuDao;
    }

    public MstShanaiMenuDao getMstShanaiMenuDao() {
        return mstShanaiMenuDao;
    }

    public void setMstShanaiMenuDao(MstShanaiMenuDao mstShanaiMenuDao) {
        this.mstShanaiMenuDao = mstShanaiMenuDao;
    }
}
