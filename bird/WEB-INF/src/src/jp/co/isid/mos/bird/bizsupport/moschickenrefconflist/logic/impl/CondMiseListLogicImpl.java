package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.CodMiseListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListCondDto;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.CondMiseListLogicChickenRevLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
/**
 * 販売予約確認保有店舗取得ロジック
 * @author inazawa
 * 2006/10/16
 */
public class CondMiseListLogicImpl implements CondMiseListLogicChickenRevLogic {
    /*保有店舗取得Dao*/
    private CodMiseListDao mosChickenRevCodSMnueInfoDao;
    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    private static final String ONER_USER  = "02";
    private static final String TENPO_USER = "03";
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS020L02";
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";

    public List execute(MosChickenRefConfListCondDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        
        //検索データを戻す。
        return miseSerch(dto);
    }

    private List miseSerch(MosChickenRefConfListCondDto dto) {
        java.util.List resultList = null;
        
        if(dto.getUserTypeCd().equals(HONBU_USER)){
            resultList = getMosChickenRevCodSMnueInfoDao().getHonbuUserMiseInfo(COMPANY_CD_MOS,dto.getOnerCd(),dto.getSysDate());
        }else if(dto.getUserTypeCd().equals(ONER_USER)){
            resultList = getMosChickenRevCodSMnueInfoDao().getOnerUserMiseInfo(COMPANY_CD_MOS,dto.getUserId(),dto.getSysDate());
        }else if(dto.getUserTypeCd().equals(TENPO_USER)){
            resultList = getMosChickenRevCodSMnueInfoDao().getTenpoUserMiseInfo(COMPANY_CD_MOS,dto.getUserId(),dto.getSysDate());
        }
        return resultList;
    }

    private void validate(MosChickenRefConfListCondDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("モスチキン モスチキン予約 画面DTO");
        }
    }
    /**
     * mosChickenRevCodSMnueInfoDaoを戻す
     * @return mosChickenRevCodSMnueInfoDao
     */
    public CodMiseListDao getMosChickenRevCodSMnueInfoDao() {
        return mosChickenRevCodSMnueInfoDao;
    }
    /**
     * mosChickenRevCodSMnueInfoDaoを設定
     * @param mosChickenRevCodSMnueInfoDao
     */
    public void setMosChickenRevCodSMnueInfoDao(
            CodMiseListDao mosChickenRevCodSMnueInfoDao) {
        this.mosChickenRevCodSMnueInfoDao = mosChickenRevCodSMnueInfoDao;
    }


}
