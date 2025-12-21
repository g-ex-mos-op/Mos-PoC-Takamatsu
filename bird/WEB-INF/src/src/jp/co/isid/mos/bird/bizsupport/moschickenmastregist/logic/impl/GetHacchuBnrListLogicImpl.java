package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstHacchuBnrDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.GetHacchuBnrListLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 
 * 予約販売管理マスタ登録
 * 【発注分類マスタ情報の取得】ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public class GetHacchuBnrListLogicImpl implements GetHacchuBnrListLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenMstRegistUtil.SCREEN_ID+"L04";
    
    /*【DAO】発注分類マスタ */
    private MstHacchuBnrDao mosChickenMstRegMstHacchuBnrDao;

    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．Dao【発注分類マスタ情報の取得】．検索 を実行し、
        //    実行結果[[発注分類マスタ]]を取得する。
        List list = getMosChickenMstRegMstHacchuBnrDao().select();
        
        //４．処理２の結果件数が０件の場合、下記のエラーを実行する。
        //Exception:    MSG【E0103】(”発注分類マスタ”）
        if(list == null || list.size() == 0){
            throw new NotExistException("発注分類マスタ");
        }
        //５．[[発注分類マスタ]]をリターンする。
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
    }
    /**
     * @return mosChickenMstRegMstHacchuBnrDao を戻します。
     */
    public MstHacchuBnrDao getMosChickenMstRegMstHacchuBnrDao() {
        return mosChickenMstRegMstHacchuBnrDao;
    }
    /**
     * @param mosChickenMstRegMstHacchuBnrDao 設定する mosChickenMstRegMstHacchuBnrDao。
     */
    public void setMosChickenMstRegMstHacchuBnrDao(
            MstHacchuBnrDao mosChickenMstRegMstHacchuBnrDao) {
        this.mosChickenMstRegMstHacchuBnrDao = mosChickenMstRegMstHacchuBnrDao;
    }

}
