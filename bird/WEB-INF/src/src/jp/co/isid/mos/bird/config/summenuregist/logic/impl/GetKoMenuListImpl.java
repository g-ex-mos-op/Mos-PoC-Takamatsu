package jp.co.isid.mos.bird.config.summenuregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstShanaiMenuDao;
import jp.co.isid.mos.bird.common.entity.MstShanaiMenu;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.config.summenuregist.dao.MstSumMenuDao;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 子メニュー一覧取得ロジック
 * @author xnkusama
 *
 */
public class GetKoMenuListImpl implements SumMenuRegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BCF009L02";

    /* DAO */
    private MstSumMenuDao sumMenuRegistMstSumMenuDao;
    private MstShanaiMenuDao mstShanaiMenuDao;
    
    /**
     * 子メニュー一覧取得
     */
    public void execute(SumMenuRegistDto dto) {
        validate(dto);
        
        //DAO実行
        List listSumMenu = getSumMenuRegistMstSumMenuDao().selectKoMenuList(dto.getSumMenuCd());
        
        if (listSumMenu == null || listSumMenu.isEmpty()) {
            //TODO 該当データなし処理
        }
        
        //取得データに親メニューの情報が存在するか確認
        boolean existFlg = false;
        for (Iterator ite = listSumMenu.iterator(); ite.hasNext();) {
            MstSumMenu entity = (MstSumMenu) ite.next();
            if (SumMenuRegistConst.OYA_MENU_FLG.equals(entity.getSumMenuFlg())) {
                existFlg = true;
                break;
            }
        }
        
        //親メニュー情報が存在しない場合は、名称を取得しリストの1行目に追加
        if (!existFlg) {
            MstSumMenu entity = new MstSumMenu();
            entity.setMenuCd(dto.getSumMenuCd());
            entity.setSumMenuCd(dto.getSumMenuCd());
            entity.setConvValue("1");
            entity.setSumMenuFlg(SumMenuRegistConst.OYA_MENU_FLG);
            entity.setInsertFlg(true);
            //名称取得
            List listMenuCd = new ArrayList();
            listMenuCd.add(dto.getSumMenuCd());
            List listMenu = getMstShanaiMenuDao().select(listMenuCd, null);
            if (listMenu == null || listMenu.isEmpty()) {
                throw new NotExistException("集約先メニュー");
            }
            entity.setMenuName(((MstShanaiMenu)listMenu.get(0)).getMenuNameKj());
            entity.setSumMenuName(((MstShanaiMenu)listMenu.get(0)).getMenuNameKj());
            
            //子メニューリストの先頭に追加
            listSumMenu.add(0, entity);
        }
        
        //親メニューのコードと名称をDTOへセット
        MstSumMenu entity = (MstSumMenu) listSumMenu.get(0);
        dto.setSumMenuCd(entity.getSumMenuCd());
        dto.setSumMenuName(entity.getSumMenuName());
        
        dto.setListKoMenu(listSumMenu);
        
        return;
    }

    /**
     * 初期処理
     * @param dto
     */
    private void validate(SumMenuRegistDto dto) {
        if (dto.getSumMenuCd() == null || dto.getSumMenuCd().trim().equals("")) {
            throw new NotNullException("集約先メニューコード");
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
