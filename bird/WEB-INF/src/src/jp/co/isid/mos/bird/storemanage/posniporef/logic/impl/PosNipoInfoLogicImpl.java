package jp.co.isid.mos.bird.storemanage.posniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.storemanage.posniporef.common.PosNipoRefConstants;
import jp.co.isid.mos.bird.storemanage.posniporef.dao.PosNipoInfoDao;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.PosNipoInfo;
import jp.co.isid.mos.bird.storemanage.posniporef.logic.PosNipoInfoLogic;

/**
 * POS日報　POS日報情報取得ロジック
 * @author xwatanabe
 */
public class PosNipoInfoLogicImpl implements PosNipoInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM014L02";

    /** POS日報情報取得DAO */
    private PosNipoInfoDao posNipoInfoDao;

    /**
     * POS日報情報を取得する
     * @param companyCd
     * @param kikan
     * @param miseCd
     * @param onerCd
     * @return Map
     * @throws Exception 想定外エラー
     */
    public List execute(String companyCd, String kikan, String miseCd, String onerCd, String kbn, String userId, boolean limitFlg) {

        //返却用リスト
        List retList = new ArrayList();
        
        //開始年月日と終了年月日を求める
        String startYmd = kikan + "01";
        String endYmd   = kikan + "99";

        
        //-----------------------------------------------
        // 検索区分が指定されていない時(店舗ユーザの時)
        //-----------------------------------------------
        if(kbn == null || kbn.length()==0){
            //DAO実行
            retList = posNipoInfoDao.getPosNipoInfoByMiseCd(companyCd, startYmd, endYmd, miseCd, userId, limitFlg);
        }
        //--------------------------
        // オーナーコード指定時
        //--------------------------
        else if((kbn.equals(PosNipoRefConstants.KBN_OWNER)) || (kbn.equals(PosNipoRefConstants.KBN_ZENTEN))){
            //DAO実行
            retList = posNipoInfoDao.getPosNipoInfoByOnerCd(companyCd, startYmd, endYmd, onerCd, userId, limitFlg);
        }
        //--------------------------
        // 店舗コード指定時
        //--------------------------
        else if(kbn.equals(PosNipoRefConstants.KBN_MISE)){
            //DAO実行
            retList = posNipoInfoDao.getPosNipoInfoByMiseCd(companyCd, startYmd, endYmd, miseCd, userId, limitFlg);
        }
        
        //合計行追加
        retList = addSumRow(retList);

        //返却
        return retList;
    }
    
    /**
     * 合計行のエンティティを生成・追加する。
     */
    private List addSumRow(List list) {

        if(list == null || list.size()==0){
            return list;
        }
        
        //合計行
        PosNipoInfo sum = new PosNipoInfo();
        sum.setSumRow(true);
        sum.setEigyoDt("合計");

        //作業用
        BigDecimal tmpInfo = new BigDecimal(0);
        BigDecimal tmpSum  = new BigDecimal(0);

        for(int i=0; i<list.size(); i++){
            
            PosNipoInfo info = (PosNipoInfo)list.get(i);

            //----------------
            // 日報タブ用
            //----------------
            //売上高計
            tmpInfo = info.getUriage();
            tmpSum  = sum.getUriage();
            sum.setUriage(tmpSum.add(tmpInfo));
            
            //----------------
            // 仕入タブ用
            //----------------
            //食材仕入高
            tmpInfo = info.getSiireShoku();
            tmpSum  = sum.getSiireShoku();
            sum.setSiireShoku(tmpSum.add(tmpInfo));

            //野菜仕入高
            tmpInfo = info.getSiireYasai();
            tmpSum  = sum.getSiireYasai();
            sum.setSiireYasai(tmpSum.add(tmpInfo));

            //包材仕入高
            tmpInfo = info.getSiireHouzai();
            tmpSum  = sum.getSiireHouzai();
            sum.setSiireHouzai(tmpSum.add(tmpInfo));

            //その他仕入高
            tmpInfo = info.getSiireOther();
            tmpSum  = sum.getSiireOther();
            sum.setSiireOther(tmpSum.add(tmpInfo));

            //消耗品仕入高
            tmpInfo = info.getSiireShoumou();
            tmpSum  = sum.getSiireShoumou();
            sum.setSiireShoumou(tmpSum.add(tmpInfo));

            //----------------
            // 原価タブ用
            //----------------
            //実際原価：食材
            tmpInfo = info.getJituShoku();
            tmpSum  = sum.getJituShoku();
            sum.setJituShoku(tmpSum.add(tmpInfo));

            //実際原価：野菜
            tmpInfo = info.getJituYasai();
            tmpSum  = sum.getJituYasai();
            sum.setJituYasai(tmpSum.add(tmpInfo));

            //実際原価：包材
            tmpInfo = info.getJituHouzai();
            tmpSum  = sum.getJituHouzai();
            sum.setJituHouzai(tmpSum.add(tmpInfo));

            //実際原価：その他
            tmpInfo = info.getJituOther();
            tmpSum  = sum.getJituOther();
            sum.setJituOther(tmpSum.add(tmpInfo));

            //標準原価：食材
            tmpInfo = info.getStdShoku();
            tmpSum  = sum.getStdShoku();
            sum.setStdShoku(tmpSum.add(tmpInfo));

            //標準原価：野菜
            tmpInfo = info.getStdYasai();
            tmpSum  = sum.getStdYasai();
            sum.setStdYasai(tmpSum.add(tmpInfo));

            //標準原価：包材
            tmpInfo = info.getStdHouzai();
            tmpSum  = sum.getStdHouzai();
            sum.setStdHouzai(tmpSum.add(tmpInfo));

            //標準原価：その他
            tmpInfo = info.getStdOther();
            tmpSum  = sum.getStdOther();
            sum.setStdOther(tmpSum.add(tmpInfo));

            //----------------
            // 人件費タブ用
            //----------------
            //P/A人件費計
            tmpInfo = info.getPaSal();
            tmpSum  = sum.getPaSal();
            sum.setPaSal(tmpSum.add(tmpInfo));

            //P/A総労働時間
            tmpInfo = info.getPaWh();
            tmpSum  = sum.getPaWh();
            sum.setPaWh(tmpSum.add(tmpInfo));

            //社員人件費計
            tmpInfo = info.getShainSal();
            tmpSum  = sum.getShainSal();
            sum.setShainSal(tmpSum.add(tmpInfo));

            //社員総労働時間
            tmpInfo = info.getShainWh();
            tmpSum  = sum.getShainWh();
            sum.setShainWh(tmpSum.add(tmpInfo));
        }

        list.add(sum);
        
        return list;
    }

    /**
     * POS日報情報取得DAOを取得します
     * @return POS日報情報取得DAO
     */
    public PosNipoInfoDao getPosNipoInfoDao() {
        return posNipoInfoDao;
    }

    /**
     * POS日報情報取得DAOを設定します<br>
     * @param POS日報情報取得DAO
     */
    public void setPosNipoInfoDao(PosNipoInfoDao dao) {
        this.posNipoInfoDao = dao;
    }
}
