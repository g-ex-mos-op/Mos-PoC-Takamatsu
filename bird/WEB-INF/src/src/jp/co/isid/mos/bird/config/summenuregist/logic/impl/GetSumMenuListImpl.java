package jp.co.isid.mos.bird.config.summenuregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.config.summenuregist.dao.MstSumMenuDao;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 集約メニュー一覧取得ロジック
 * @author xnkusama
 *
 */
public class GetSumMenuListImpl implements SumMenuRegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BCF009L01";

    /* DAO */
    private MstSumMenuDao sumMenuRegistMstSumMenuDao;
    
    /**
     * 集約メニュー一覧取得
     */
    public void execute(SumMenuRegistDto dto) {
        validate(dto);
        
        //DAO実行
        List listSumMenu = getSumMenuRegistMstSumMenuDao().selectSumMenuList(null);
        
        if (listSumMenu == null || listSumMenu.isEmpty()) {
            //登録済み情報がない場合は、Nullを設定
            listSumMenu = null;
        }
        
        dto.setListSumMenu(listSumMenu);
        
        return;
    }

    /**
     * 初期処理
     * @param dto
     */
    private void validate(SumMenuRegistDto dto) {
        if (dto == null) {
            throw new NotNullException("画面情報");
        }
    }
    
    public MstSumMenuDao getSumMenuRegistMstSumMenuDao() {
        return sumMenuRegistMstSumMenuDao;
    }

    public void setSumMenuRegistMstSumMenuDao(
            MstSumMenuDao sumMenuRegistMstSumMenuDao) {
        this.sumMenuRegistMstSumMenuDao = sumMenuRegistMstSumMenuDao;
    }

}
