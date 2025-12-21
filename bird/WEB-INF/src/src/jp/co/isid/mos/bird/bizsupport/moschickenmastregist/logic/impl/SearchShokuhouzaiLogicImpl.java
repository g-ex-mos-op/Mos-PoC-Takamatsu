package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstShokuhouzaiDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.SearchShokuhouzaiLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuEisujiVerifier;

/**
 * 
 * 予約販売管理マスタ登録
 * 【食包材マスタの検索】ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public class SearchShokuhouzaiLogicImpl implements SearchShokuhouzaiLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenMstRegistUtil.SCREEN_ID+"L05";
    /**
     * パラメーターキー： 検索食包材開始コード
     */
    public static final String PK_CD_FROM= "cdFrom";
    /**
     * パラメーターキー： 検索食包材開始コード
     */
    public static final String PK_CD_TO= "cdTo";
    /**
     * パラメーターキー： 検索食包材名称
     */
    public static final String PK_SHOKUHOUZAI_NAME= "shokuhouzaiName";
    /**
     * パラメーターキー： 検索発注分類コード
     */
    public static final String PK_HACCHU_BNR_CD= "hacchuBnrCd";
    
    /*【DAO】食包材マスタ */
    private MstShokuhouzaiDao mosChickenMstRegMstShokuhouzaiDao;

    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //検索対象　食包材開始コード
        String cdFrom = (String)params.get(PK_CD_FROM);
        //検索対象　食包材開始コード
        String cdTo = (String)params.get(PK_CD_TO);
        //検索対象　食包材名称
        String shokuhouzaiName = (String)params.get(PK_SHOKUHOUZAI_NAME);
        //検索対象　検索発注分類コード
        String hacchuBnrCd = (String)params.get(PK_HACCHU_BNR_CD);
        if(!MosChickenMstRegistUtil.isNull(shokuhouzaiName)){
            shokuhouzaiName = "%"+ shokuhouzaiName+"%";
        }
        //２．Dao【食包材マスタ】．検索 を実行し、
        //    実行結果[[食包材マスタ]]を取得する。
        List list = getMosChickenMstRegMstShokuhouzaiDao().select(
                cdFrom, cdTo, shokuhouzaiName, hacchuBnrCd);
        
        //４．処理２の結果件数が０件の場合、下記のエラーを実行する。
        //Exception:    MSG【E0103】(”該当データ”）
        if(list == null || list.size() == 0){
            throw new NotExistException("該当データ");
        }
        //５．[[食包材マスタ]]をリターンする。
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //検索対象　食包材開始コード
        String cdFrom = (String)params.get(PK_CD_FROM);
        //検索対象　食包材開始コード
        String cdTo = (String)params.get(PK_CD_TO);
        //検索対象　食包材名称
        String shokuhouzaiName = (String)params.get(PK_SHOKUHOUZAI_NAME);
        //検索対象　検索発注分類コード
        String hacchuBnrCd = (String)params.get(PK_HACCHU_BNR_CD);
        if (MosChickenMstRegistUtil.isNull(cdFrom) 
                && MosChickenMstRegistUtil.isNull(cdTo)
                && MosChickenMstRegistUtil.isNull(shokuhouzaiName)
                && MosChickenMstRegistUtil.isNull(hacchuBnrCd)) {
            //MSG【E0507】’を入力してください。'
            throw new NoInputException(
                    "食包材コード範囲、食包材名、発注分類のいずれか"
                    , PK_CD_FROM, 0);
        }
        CodeFormatter cdf = new CodeFormatter(5, "00000");
        cdf.setFormatPattern("00000");
        HankakuEisujiVerifier hankakuEisu = new HankakuEisujiVerifier();
        if(!MosChickenMstRegistUtil.isNull(cdFrom)){
            if(hankakuEisu.validate(cdFrom)){
                params.put(PK_CD_FROM, cdf.format(cdFrom, true));
            }
            else{
                //MSG【E0505】’が正しくありません。'
                throw new InvalidInputException("食包材コードFROM");
            }
        }
        if(!MosChickenMstRegistUtil.isNull(cdTo)){
            if(hankakuEisu.validate(cdTo)){
                params.put(PK_CD_TO, cdf.format(cdTo, true));
            }
            else{
                //MSG【E0505】’が正しくありません。'
                throw new InvalidInputException("食包材コードTO");
            }
        }
    }
    /**
     * @return mosChickenMstRegMstShokuhouzaiDao を戻します。
     */
    public MstShokuhouzaiDao getMosChickenMstRegMstShokuhouzaiDao() {
        return mosChickenMstRegMstShokuhouzaiDao;
    }
    /**
     * @param mosChickenMstRegMstShokuhouzaiDao 設定する mosChickenMstRegMstShokuhouzaiDao。
     */
    public void setMosChickenMstRegMstShokuhouzaiDao(
            MstShokuhouzaiDao mosChickenMstRegMstShokuhouzaiDao) {
        this.mosChickenMstRegMstShokuhouzaiDao = mosChickenMstRegMstShokuhouzaiDao;
    }
}
