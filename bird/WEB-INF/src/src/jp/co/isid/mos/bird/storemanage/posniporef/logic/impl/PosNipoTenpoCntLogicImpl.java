package jp.co.isid.mos.bird.storemanage.posniporef.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.posniporef.common.PosNipoRefConstants;
import jp.co.isid.mos.bird.storemanage.posniporef.dao.PosNipoCntDao;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.PosNipoCnt;
import jp.co.isid.mos.bird.storemanage.posniporef.logic.PosNipoTenpoCntLogic;

/**
 * POS日報　取得店舗数取得ロジック
 * @author xwatanabe
 */
public class PosNipoTenpoCntLogicImpl implements PosNipoTenpoCntLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM014L01";

    /** POS日報 店舗数取得DAO */
    private PosNipoCntDao posNipoCntDao;

    /**
     * POS日報情報を取得する
     * @param companyCd
     * @param kikan
     * @param miseCd
     * @param onerCd
     * @param kbn
     * @param userId
     * @param limitFlg
     * @return int
     * @throws Exception 想定外エラー
     */
    public int execute(String companyCd, String kikan, String miseCd, String onerCd, String kbn, String userId, boolean limitFlg) {

        //返却用リスト
        int retCnt = 0;
        
        //開始年月日と終了年月日を求める
        String startYmd = kikan + "01";
        String endYmd   = kikan + "99";
        
        //----------------------------------------------
        // 検索区分が設定されていない時(店舗ユーザの時)
        //----------------------------------------------
        if(kbn == null || kbn.length() ==0){

            //DAO実行
            List list = posNipoCntDao.countPosNipoInfoByMiseCd(companyCd, startYmd, endYmd, miseCd, userId, limitFlg);
            if(list != null && list.size() > 0){
                PosNipoCnt cnt = (PosNipoCnt)list.get(0);
                retCnt = cnt.getTenpoCnt();
            }
        }
        //--------------------------
        // オーナーコード指定時
        //--------------------------
        else if((kbn.equals(PosNipoRefConstants.KBN_OWNER)) || (kbn.equals(PosNipoRefConstants.KBN_ZENTEN))){

            //DAO実行
            List list = posNipoCntDao.countPosNipoInfoByOnerCd(companyCd, startYmd, endYmd, onerCd, userId, limitFlg);
            if(list != null && list.size() > 0){
                PosNipoCnt cnt = (PosNipoCnt)list.get(0);
                retCnt = cnt.getTenpoCnt();
            }
        }
        //--------------------------
        // 店舗コード指定時
        //--------------------------
        else if((kbn.equals(PosNipoRefConstants.KBN_MISE))){

            //DAO実行
            List list = posNipoCntDao.countPosNipoInfoByMiseCd(companyCd, startYmd, endYmd, miseCd, userId, limitFlg);
            if(list != null && list.size() > 0){
                PosNipoCnt cnt = (PosNipoCnt)list.get(0);
                retCnt = cnt.getTenpoCnt();
            }
        }

        //返却
        return retCnt;
    }
    

    /**
     * POS日報　店舗数取得DAOを取得します
     */
    public PosNipoCntDao getPosNipoCntDao() {
        return posNipoCntDao;
    }

    /**
     * POS日報　店舗数取得DAOを設定します
     */
    public void setPosNipoCntDao(PosNipoCntDao dao) {
        this.posNipoCntDao = dao;
    }
}
