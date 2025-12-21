/*
 * 作成日: 2006/02/07
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.UIKanrenBunshoInfoDao;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.entity.UIKanrenBunshoInfo;
import jp.co.isid.mos.bird.common.logic.RegKanrenBunshoLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 関連文書の登録
 * @author xytamura
 */
public class RegKanrenBunshoLogicImpl implements RegKanrenBunshoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L04";
    
    /**
     * 関連文書Dao
     */
    private UIKanrenBunshoInfoDao uIKanrenBunshoInfoDao;

    /**
     * 関連文書の登録
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @param listUIDocSearch 関連文書情報
     * @param userId ユーザＩＤ
     * @param programId プログラムＩＤ
     */
    public void execute(String infoShu, String regDate, String seq,
            List listUIDocSearch, String userId, String programId) {
        //関連文書情報作成
        List bunshoInfo =  createUIKanrenBunshoInfo(infoShu, regDate, seq, listUIDocSearch, userId, programId);
        //既存データを削除
        getUIKanrenBunshoInfoDao().deleteKanrenBunsho(infoShu, regDate, seq);
        //登録
        for(int i = 0; i < bunshoInfo.size(); i++ ){
            UIKanrenBunshoInfo entity =  (UIKanrenBunshoInfo)bunshoInfo.get(i);
            getUIKanrenBunshoInfoDao().insertKanrenBunsho(entity);
        }
        
        
    }
    
    /**
     * 関連文書Daoを取得します。
     * @return 関連文書Dao
     */
    public UIKanrenBunshoInfoDao getUIKanrenBunshoInfoDao() {
        return uIKanrenBunshoInfoDao;
    }

    /**
     * 関連文書Daoを設定します。
     * @param 関連文書Dao
     */
    public void setUIKanrenBunshoInfoDao(
            UIKanrenBunshoInfoDao kanrenBunshoInfoDao) {
        uIKanrenBunshoInfoDao = kanrenBunshoInfoDao;
    }

    /**
     * 登録する関連文書情報を作成します。
     * @param infoShu データ種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @param listUIDocSearch 関連文書
     * @param userId ユーザＩＤ
     * @param programId プログラムＩＤ
     * @return
     */
    private List createUIKanrenBunshoInfo(String infoShu, String regDate,
            String seq, List listUIDocSearch, String userId, String programId) {
        if(listUIDocSearch == null ){
            return new ArrayList();
        }
        
        List listUIKanrenBunsho = new ArrayList();
        
        for(int i = 0; i < listUIDocSearch.size(); i++){
            UIDocSearch uiDocSearch = (UIDocSearch)listUIDocSearch.get(i);
            
            UIKanrenBunshoInfo kanrenBunsho = new UIKanrenBunshoInfo();
            kanrenBunsho.setInfoShu(infoShu);
            kanrenBunsho.setRegDate(regDate);
            kanrenBunsho.setSeq(seq);
            kanrenBunsho.setRelInfoShu(uiDocSearch.getInfoShu());
            kanrenBunsho.setRelRegDate(uiDocSearch.getRegDate());
            kanrenBunsho.setRelSeq(uiDocSearch.getSeq());
            kanrenBunsho.setFirstUser(userId);
            kanrenBunsho.setFirstPgm(programId);
            kanrenBunsho.setFirstTmsp(DateManager.getCurrentTimestamp());
            kanrenBunsho.setLastUser(userId);
            kanrenBunsho.setLastPgm(programId);
            kanrenBunsho.setLastTmsp(DateManager.getCurrentTimestamp());
            listUIKanrenBunsho.add(kanrenBunsho);
        }
        
        return listUIKanrenBunsho;
    
    }

}