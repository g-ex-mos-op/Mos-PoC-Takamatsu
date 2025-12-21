package jp.co.isid.mos.bird.storemanage.posniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.storemanage.posniporef.dao.TenpoListByOnerDao;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.TenpoInfo;
import jp.co.isid.mos.bird.storemanage.posniporef.logic.MakeMiseListByOnerLogic;

/**
 * POS日報　オーナーに紐づく店舗リストの生成
 * @author xwatanabe
 */
public class MakeMiseListByOnerLogicImpl implements MakeMiseListByOnerLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM014L03";

    /** オーナー対応店舗取得DAO */
    private TenpoListByOnerDao tenpoListByOnerDao;

    /**
     * オーナーに紐づく店舗リストの生成
     * @param  companyCd
     * @param  onerCd
     * @return List
     * @throws Exception 想定外エラー
     */
    public List execute(String companyCd, String onerCd, String date, String userId, boolean limitFlg) {
        
        List closeMiseList = new ArrayList();
        List miseList   = new ArrayList();

        //DAO実行
        List list = tenpoListByOnerDao.getTenpoListByOner(companyCd, onerCd, date, userId, limitFlg);
        if(list != null && list.size() >0){

            for(int i=0; i<list.size(); i++){
                TenpoInfo info = (TenpoInfo)list.get(i);

                String miseCd   = info.getMiseCd();
                String miseName = info.getMiseNameKj();

                if(info.getCloseFlg()==0){
                    info.setMiseNameAddCd(miseCd + " " + miseName);
                    miseList.add(info);
                    
                }else{
                    info.setMiseNameAddCd(miseCd + " " + miseName + "(CLOSE)");
                    closeMiseList.add(info);
                }
            }
        }

        if(closeMiseList != null && closeMiseList.size() > 0){

            for(int i=0; i<closeMiseList.size(); i++){
                
                TenpoInfo info = (TenpoInfo)closeMiseList.get(i);
                miseList.add(info);
            }
        }

        //返却
        return miseList;
    }
    

    /**
     * @return tenpoListByOnerDao を戻します。
     */
    public TenpoListByOnerDao getTenpoListByOnerDao() {
        return tenpoListByOnerDao;
    }


    /**
     * @param tenpoListByOnerDao 設定する tenpoListByOnerDao。
     */
    public void setTenpoListByOnerDao(TenpoListByOnerDao tenpoListByOnerDao) {
        this.tenpoListByOnerDao = tenpoListByOnerDao;
    }
}
