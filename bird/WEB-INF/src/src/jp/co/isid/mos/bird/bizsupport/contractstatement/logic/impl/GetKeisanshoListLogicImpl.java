/*
 * 作成日2007/07/05
 */
package jp.co.isid.mos.bird.bizsupport.contractstatement.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.contractstatement.dao.UIKeisanshoListDao;
import jp.co.isid.mos.bird.bizsupport.contractstatement.dto.ContractStatementDto;
import jp.co.isid.mos.bird.bizsupport.contractstatement.entity.UICategoryList;
import jp.co.isid.mos.bird.bizsupport.contractstatement.entity.UIKeisanshoList;
import jp.co.isid.mos.bird.bizsupport.contractstatement.logic.GetKeisanshoListLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 計算書一覧取得ロジック
 * @author xnkusama
 *
 */
public class GetKeisanshoListLogicImpl implements GetKeisanshoListLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BBS030L01";

    /* 制御フラグ */
//    private static final String CTRL_FLG_DISP = "1";
//    private static final String CTRL_FLG_NOT_DISP = "0";
    
    /**
     * 売上確定ステータスDao
     */
    private UIKeisanshoListDao contractStatementDao;

    /** 
     * [インナークラス]比較用
     */
    private class SortComparator implements Comparator {

        public int compare(Object obj1, Object obj2) {

            //比較対象エンティティ
            UIKeisanshoList ent1 = (UIKeisanshoList)obj1;
            UIKeisanshoList ent2 = (UIKeisanshoList)obj2;

            //計算書カテゴリ、オーナーコード、計算書の種類までで2つの値に違いがある場合
            String str1 = ent1.getKeiCategory() + ent1.getOnerCd() + ent1.getKeiShu();
            String str2 = ent2.getKeiCategory() + ent2.getOnerCd() + ent2.getKeiShu();
            if (!str1.equals(str2)) {
                return str1.compareTo(str2);
            }
            //発行日に違いがある場合 （降順）
            str1 = ent1.getHakkoDt();
            str2 = ent2.getHakkoDt();
            if (!str1.equals(str2)) {
                return str2.compareTo(str1);
            }
            //売掛先コードに違いがある場合
            str1 = ent1.getUrikakeCd();
            str2 = ent2.getUrikakeCd();
            if (!str1.equals(str2)) {
                return str1.compareTo(str2);
            }
            //対象年月に違いがある場合（降順）
            str1 = ent1.getKeisanYm();
            str2 = ent2.getKeisanYm();
            return str2.compareTo(str1);
        }
    }
    
    /**
     * 計算書一覧取得
     * @param 会社コード
     * @param オーナーコード
     * @param システム日付
     */
    public void execute(ContractStatementDto dto, String sysDate) {
        String companyCd = dto.getCompanyCd();
        String onerCd = dto.getOnerCd();

        /* 検索基準発行日を算出（システム日付前月の月初）*/
        String hakkoDt = "";
        try {
            hakkoDt = DateManager.getPrevMonth(sysDate.substring(0, 6), 1) + "01";
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", "", ex);
        }
        /* 検索 */
        List listAllData = getContractStatementDao().getKeisanshoList(companyCd, onerCd, hakkoDt);
        /* データが存在しない場合は、空のリストをセットし処理終了 */
        if (listAllData == null || listAllData.isEmpty()) {
            dto.setListData(new ArrayList());
            dto.setListAllData(new ArrayList());
            return;
        }
        /* カテゴリごとのリストを作成し、複数のカテゴリを持ったリストとしてデータ保持する */
        String oldCategory = null;
        List listCate = new ArrayList();
        List listData = new ArrayList();
        for (int i = 0; i < listAllData.size(); i++) {
            UIKeisanshoList entity = (UIKeisanshoList) listAllData.get(i);
            if (i != 0 && !oldCategory.equals(entity.getKeiCategory())) {
                listData.add(createUiCategory(listCate));
                listCate = new ArrayList();
                listCate.add(entity);
            }
            else {
                listCate.add(entity);
            }
            oldCategory = entity.getKeiCategory();
        }
        if (!listCate.isEmpty()) {
            listData.add(createUiCategory(listCate));
        }
        dto.setListAllData(listAllData);
        dto.setListData(listData);
    }

    /**
     * 同一計算書カテゴリのデータをUICategoryList型として作成する
     * @param listCate
     * @return
     */
    private UICategoryList createUiCategory(List listCate) {
        UICategoryList uiCate = new UICategoryList();
        /* 表示月数による表示対象外のデータをリストから削除 */
        int duplicateCnt = 1;
        for (int i = 0; i < listCate.size(); i++) {
            UIKeisanshoList entity = (UIKeisanshoList) listCate.get(i);
            if (i == 0) {
                entity.setWorkCnt1(1);
            }
            else {
                UIKeisanshoList entityBef = (UIKeisanshoList) listCate.get(i - 1);
                if (entity.getOnerCd().equals(entityBef.getOnerCd())
                        && entity.getUrikakeCd().equals(entityBef.getUrikakeCd())
                        && entity.getKeiShu().equals(entityBef.getKeiShu()))
                {
                    duplicateCnt++;
                }
                else {
                    duplicateCnt = 1;
                }
                entity.setWorkCnt1(duplicateCnt);
            }        
        }
        
        for (Iterator ite = listCate.iterator(); ite.hasNext();) {
            UIKeisanshoList entity = (UIKeisanshoList) ite.next();
            if (entity.getWorkCnt1() > entity.getDispMonths().intValue()) {
                ite.remove();
            }
        }
        
        //リストをソート
        Collections.sort(listCate, new SortComparator());
        
        /* オーナー単位でセル結合する為の情報セット */
        int spanCntOner = 1;
        UIKeisanshoList entityOner;
        for (int i = listCate.size() - 1; i > 0; i--) {
            entityOner = (UIKeisanshoList) listCate.get(i);
            UIKeisanshoList entityNext = (UIKeisanshoList) listCate.get(i - 1);
            if (entityOner.getOnerCd().equals(entityNext.getOnerCd())) {
                entityOner.setSpanCntOner(0);
                spanCntOner++;
            }
            else {
                entityOner.setSpanCntOner(spanCntOner);
                spanCntOner = 1;
            }
        }
        entityOner = (UIKeisanshoList) listCate.get(0);
        entityOner.setSpanCntOner(spanCntOner);
        
        /* オーナー単位でセル結合する為の情報セット */
        int spanCntUrikake = 1;
        UIKeisanshoList entityUrikake;
        for (int i = listCate.size() - 1; i > 0; i--) {
            entityUrikake = (UIKeisanshoList) listCate.get(i);
            UIKeisanshoList entityNext = (UIKeisanshoList) listCate.get(i - 1);
            if (entityUrikake.getUrikakeCd().equals(entityNext.getUrikakeCd())) {
                entityUrikake.setSpanCntUrikake(0);
                spanCntUrikake++;
            }
            else {
                entityUrikake.setSpanCntUrikake(spanCntUrikake);
                spanCntUrikake = 1;
            }
        }
        entityUrikake = (UIKeisanshoList) listCate.get(0);
        entityUrikake.setSpanCntUrikake(spanCntUrikake);
        
        
        /* 計算書カテゴリごとのデータをセット */
        uiCate.setListData(listCate);
        /* 制御フラグをセット */
        UIKeisanshoList oldEntity = (UIKeisanshoList) listCate.get(0);
        uiCate.setCtrlFlg1(oldEntity.getCtrlFlg1());
        uiCate.setCtrlFlg2(oldEntity.getCtrlFlg2());
        uiCate.setCtrlFlg2(oldEntity.getCtrlFlg2());
        uiCate.setKeiCateName(oldEntity.getKeiCateName());
        /* テーブル幅設定 */
        // 全項目表示
        int width = 940;
        //---2009/02/06 delete statrt
        //---テーブルの幅は表示項目に関わらず揃える
//        //発行日、売掛先が非表示
//        if (uiCate.getCtrlFlg1().equals(CTRL_FLG_NOT_DISP)
//                && uiCate.getCtrlFlg2().equals(CTRL_FLG_NOT_DISP)) {
//            width = 565;
//        }
//        //発行日：表示、売掛先：非表示
//        else if (uiCate.getCtrlFlg1().equals(CTRL_FLG_DISP)
//                && uiCate.getCtrlFlg2().equals(CTRL_FLG_NOT_DISP)) {
//            width = 660;
//        }
//        //発行日：非表示、売掛先：表示
//        else if (uiCate.getCtrlFlg1().equals(CTRL_FLG_NOT_DISP)
//                && uiCate.getCtrlFlg2().equals(CTRL_FLG_DISP)) {
//            width = 850;
//        }
        //---2009/02/06 delete end
        uiCate.setTableWidth(width);
        
        return uiCate;
    }
    public UIKeisanshoListDao getContractStatementDao() {
        return contractStatementDao;
    }

    public void setContractStatementDao(UIKeisanshoListDao contractStatementDao) {
        this.contractStatementDao = contractStatementDao;
    }

}