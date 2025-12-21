package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstKanriKikanDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstKanriMenuGroupDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.UIKanriMenuDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstKanriMenuGroup;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 
 * 予約販売管理マスタ登録
 * 【予約販売管理マスタ情報の検索】ロジックインターフェース
 * 
 * 戻り値はListで格納データの詳細は以下のとおりです。
 * 　List：インデックス0＝[[管理対象期間]]
 *         インデックス1＝[[管理対象メニューグループ]]
 *         インデックス2＝[[管理対象メニュー]]
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenMstRegistUtil.SCREEN_ID+"L02";
    /**
     * 管理対象期間 管理番号 YYYY+連番2桁
     */
    public static final String PK_CKANRI_NO= "cKanriNo";
    
    /*【DAO】管理対象期間 */
    private MstKanriKikanDao mosChickenMstRegMstKanriKikanDao;
    /*【DAO】管理対象メニューグループ */
    private MstKanriMenuGroupDao mosChickenMstRegMstKanriMenuGroupDao;
    /*【DAO】管理対象メニュー */
    private UIKanriMenuDao mosChickenMstRegUIKanriMenuDao;

    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Listをインスタンス化する。
        List list = new ArrayList(); 
        //検索対象　管理番号
        String cKanriNo = (String)params.get(PK_CKANRI_NO);
        //３．Dao【管理対象期間】．指定管理番号の管理対象期間情報を取得 を実行し、
        //    実行結果[[管理対象期間]]を取得する。
        List listKikan = getMosChickenMstRegMstKanriKikanDao().select(cKanriNo);
        
        //４．処理２の結果件数が０件の場合、下記のエラーを実行する。
        //Exception:    MSG【E0102】(”管理対象期間”）
        if(listKikan == null || listKikan.size() == 0){
            throw new NoResultException("管理対象期間");
        }
        //５．Dao【管理対象メニューグループ】．検索 を実行し、
        //    実行結果[[管理対象メニューグループ]]を取得する。
        List listMenuGroup = getMosChickenMstRegMstKanriMenuGroupDao().select(cKanriNo);
        
        //６．処理５の結果件数が０件の場合、下記の処理を実行する。
        if(listMenuGroup == null || listMenuGroup.size() == 0){
            //６－１．List[[管理対象メニューグループ]]をインスタンス化する。
            listMenuGroup = new ArrayList();
            //６－２．[管理対象メニューグループ]エンティティをインスタンス化する。
            MstKanriMenuGroup entity = new MstKanriMenuGroup();
            //６－３．[[管理対象メニューグループ]]へ[管理対象メニューグループ]エンティティをaddする。
            listMenuGroup.add(entity);
        }
        //７．Dao【管理対象メニュー】．検索 を実行し、
        //    実行結果[[管理対象メニュー]]を取得する。
        List listMenu = getMosChickenMstRegUIKanriMenuDao().select(cKanriNo);
        
        //８．処理３で取得した[[管理対象期間]]をリターン値へ格納する。
        list.add(0, listKikan);
        //９．処理５で取得した[[管理対象メニューグループ]]をリターン値へ格納する。
        list.add(1, listMenuGroup);
        //１０．処理７で取得した[[管理対象メニュー]]をリターン値へ格納する。
        list.add(2, listMenu);
        //１１．リターン値Listをリターンする。
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //検索対象　管理番号
        String cKanriNo = (String)params.get(PK_CKANRI_NO);
        if (MosChickenMstRegistUtil.isNull(cKanriNo)) {
            throw new NotNullException("管理番号");
        }
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
    /**
     * @return mosChickenMstRegMstKanriMenuGroupDao を戻します。
     */
    public MstKanriMenuGroupDao getMosChickenMstRegMstKanriMenuGroupDao() {
        return mosChickenMstRegMstKanriMenuGroupDao;
    }
    /**
     * @param mosChickenMstRegMstKanriMenuGroupDao 設定する mosChickenMstRegMstKanriMenuGroupDao。
     */
    public void setMosChickenMstRegMstKanriMenuGroupDao(
            MstKanriMenuGroupDao mosChickenMstRegMstKanriMenuGroupDao) {
        this.mosChickenMstRegMstKanriMenuGroupDao = mosChickenMstRegMstKanriMenuGroupDao;
    }
    /**
     * @return mosChickenMstRegUIKanriMenuDao を戻します。
     */
    public UIKanriMenuDao getMosChickenMstRegUIKanriMenuDao() {
        return mosChickenMstRegUIKanriMenuDao;
    }
    /**
     * @param mosChickenMstRegUIKanriMenuDao 設定する mosChickenMstRegUIKanriMenuDao。
     */
    public void setMosChickenMstRegUIKanriMenuDao(
            UIKanriMenuDao mosChickenMstRegUIKanriMenuDao) {
        this.mosChickenMstRegUIKanriMenuDao = mosChickenMstRegUIKanriMenuDao;
    }
 }
