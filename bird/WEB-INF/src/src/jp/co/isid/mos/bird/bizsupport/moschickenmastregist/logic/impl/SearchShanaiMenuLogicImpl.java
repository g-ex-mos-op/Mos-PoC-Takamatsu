package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstShanaiMenuDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.SearchShanaiMenuLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
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
public class SearchShanaiMenuLogicImpl implements SearchShanaiMenuLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenMstRegistUtil.SCREEN_ID+"L03";
    /**
     * パラメーターキー： 検索メニュー開始コード
     */
    public static final String PK_CD_FROM= "menuCdFrom";
    /**
     * パラメーターキー： 検索メニュー開始コード
     */
    public static final String PK_CD_TO= "menuCdTo";
    /**
     * パラメーターキー： 検索メニュー名称
     */
    public static final String PK_MENU_NAME= "menuName";
    /**
     * パラメーターキー： システム日付
     */
    public static final String PK_SYSDATE= "sysDate";
    
    /*【DAO】社内メニュー名称 */
    private MstShanaiMenuDao mosChickenMstRegMstShanaiMenuDao;

    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //検索対象　メニュー開始コード
        String cdFrom = (String)params.get(PK_CD_FROM);
        //検索対象　メニュー開始コード
        String cdTo = (String)params.get(PK_CD_TO);
        //検索対象　メニュー開始コード
        String menuName = (String)params.get(PK_MENU_NAME);
        //検索対象　システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if(!MosChickenMstRegistUtil.isNull(menuName)){
            menuName = "%"+ menuName+"%";
        }
        //２．Dao【社内メニューマスタ】．検索 を実行し、
        //    実行結果[[社内メニューマスタ]]を取得する。
        List list = getMosChickenMstRegMstShanaiMenuDao().select(cdFrom, cdTo, menuName, sysDate);
        
        //４．処理２の結果件数が０件の場合、下記のエラーを実行する。
        //Exception:    MSG【E0103】(”該当データ”が存在しません。）
        if(list == null || list.size() == 0){
            throw new NotExistException("該当データ");
        }
        //５．[[社内メニューマスタ]]をリターンする。
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //検索対象　メニュー開始コード
        String cdFrom = (String)params.get(PK_CD_FROM);
        //検索対象　メニュー開始コード
        String cdTo = (String)params.get(PK_CD_TO);
        //検索対象　メニュー開始コード
        String menuName = (String)params.get(PK_MENU_NAME);
        if (MosChickenMstRegistUtil.isNull(cdFrom) 
                && MosChickenMstRegistUtil.isNull(cdTo)
                && MosChickenMstRegistUtil.isNull(menuName)) {
            //MSG【E0507】’を入力してください。'
            throw new NoInputException(
                    "メニューコード範囲、メニュー名のいずれか"
                    , PK_CD_FROM, 0);
        }
        //検索対象　システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if(MosChickenMstRegistUtil.isNull(sysDate)){
            //MSG【E0302】’が設定されていません。'
            throw new MissingConfigException("システム日付");
        }
        CodeFormatter cdf = new CodeFormatter(6, "000000");
        cdf.setFormatPattern("000000");
        HankakuEisujiVerifier hankakuEisu = new HankakuEisujiVerifier();
        if(!MosChickenMstRegistUtil.isNull(cdFrom)){
            if(hankakuEisu.validate(cdFrom)){
                params.put(PK_CD_FROM, cdf.format(cdFrom, true));
            }
            else{
                //MSG【E0505】’が正しくありません。'
                throw new InvalidInputException("メニューコードFROM");
            }
        }
        if(!MosChickenMstRegistUtil.isNull(cdTo)){        
            if(hankakuEisu.validate(cdTo)){
                params.put(PK_CD_TO, cdf.format(cdTo, true));
            }
            else{
                //MSG【E0505】’が正しくありません。'
                throw new InvalidInputException("メニューコードTO");
            }
        }
   }
    /**
     * @return mosChickenMstRegMstShanaiMenuDao を戻します。
     */
    public MstShanaiMenuDao getMosChickenMstRegMstShanaiMenuDao() {
        return mosChickenMstRegMstShanaiMenuDao;
    }
    /**
     * @param mosChickenMstRegMstShanaiMenuDao 設定する mosChickenMstRegMstShanaiMenuDao。
     */
    public void setMosChickenMstRegMstShanaiMenuDao(
            MstShanaiMenuDao mosChickenMstRegMstShanaiMenuDao) {
        this.mosChickenMstRegMstShanaiMenuDao = mosChickenMstRegMstShanaiMenuDao;
    }
}
