package jp.co.isid.mos.bird.bizreport.campkako.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.entity.UINipoMise;

/**
 * 店舗一覧用キャンペーン日報Dao
 * 
 * @author xnkusama
 * 更新日:2011/06/24 xkinu ADD 期間合計時に店舗種別の条件追加対応に伴う店舗種別を引数へ追加
 *
 */
public interface UINipoMiseDao {
    public static final Class BEAN = UINipoMise.class;
    
    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, tenpoShubetu, campId, menuTotaledKbn, shukeiKbn, campFrom, campTo, hyojiTaisho, blockCd, rankKind";
    /**
     * 検索実行処理
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param tenpoShubetu
     * @param campId
     * @param menuTotaledKbn
     * @param shukeiKbn
     * @param campFrom
     * @param campTo
     * @param hyojiTaisho
     * @param blockCd
     * @param rankKind
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg
                            , String companyCd, String tenpoShubetu, String campId, String menuTotaledKbn
                            , String shukeiKbn, String campFrom, String campTo
                            , String hyojiTaisho, String blockCd, String rankKind);
}