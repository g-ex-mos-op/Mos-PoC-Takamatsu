package jp.co.isid.mos.bird.config.summenuregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;

/**
 * 登録済み情報チェック
 * @author xnkusama
 *
 */
public interface CheckExistDataLogic {

    public static final int MODE_NEW = 1;
    public static final int MODE_MENUADD =2;
    
    /**
     * メニューコード 追加チェック
     * @param dto
     * @param menuCd
     * @param listMenuCd
     * @param mode
     */
    public List execute(SumMenuRegistDto dto, String menuCd, List listMenuCd, int mode);
}
