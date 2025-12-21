package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstKanriKikanDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * モスチキン管理マスタ登録
 * 【条件項目情報の取得】ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic{
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenMstRegistUtil.SCREEN_ID+"L01";
    /**
     * 管理対象期間 検索対象開始日 YYYYMMDD
     */
    public static final String PK_START_DT = "startDt";
    
    /*【DAO】管理対象期間 */
    private MstKanriKikanDao mosChickenMstRegMstKanriKikanDao;

    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        
        //検索対象開始日
        String startDt = (String)params.get(PK_START_DT);
        
        //２．Dao【管理対象期間】．当年度含めて過去３年度分以降のデータを取得 を実行し、
        //    実行結果[[管理対象期間]]を取得する。
        List list = getMosChickenMstRegMstKanriKikanDao().selectList(startDt);
        
        //３．[管理対象期間]]をリターンする。
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //検索対象開始日
//        String startDt = (String)params.get(PK_START_DT);
//        if (MosChickenMstRegistUtil.isNull(startDt)) {
//            throw new NotNullException("検索対象開始日");
//        }
    }
    /**
     * @return mosChickenMstRegMstKanriKikanDao を戻します。
     */
    public MstKanriKikanDao getMosChickenMstRegMstKanriKikanDao() {
        return mosChickenMstRegMstKanriKikanDao;
    }
    /**
     * @param mosChickenMstRegMstKanriKikanDao 設定する mosChickenMstRegMstKanriKikanDao。
     */
    public void setMosChickenMstRegMstKanriKikanDao(
            MstKanriKikanDao mosChickenMstRegMstKanriKikanDao) {
        this.mosChickenMstRegMstKanriKikanDao = mosChickenMstRegMstKanriKikanDao;
    }
}
