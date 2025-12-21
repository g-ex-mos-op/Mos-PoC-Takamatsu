package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.CodMiseListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.MstOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.CondMiseListLogicChickenRevLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
/**
 * モスチキン予約初期処理でのオーナー保有店舗取得ロジック
 * @author inazawa
 * 2006/09/19
 */
public class CondMiseListLogicImpl implements CondMiseListLogicChickenRevLogic {
    /*保有店舗取得Dao*/
    private CodMiseListDao mosChickenRevCodSMnueInfoDao;
    /*オーナー存在Dao*/
    private MstOnerInfoDao mosChickenRevMstOnerInfoDao;
    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    private static final String ONER_USER  = "02";
    private static final String TENPO_USER = "03";
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L02";
    public List execHonbu(MosChickenRevInfoDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        
        return getMosChickenRevMstOnerInfoDao().getOnerInfo(dto.getCompanyCd(),dto.getOnerCd());
    }
    public List execute(MosChickenRevInfoDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        
        //検索データを戻す。
        return miseSerch(dto);
    }

    private List miseSerch(MosChickenRevInfoDto dto) {
        java.util.List resultList = null;
        
        if(dto.getUserTypeCd().equals(HONBU_USER)){
            resultList = getMosChickenRevCodSMnueInfoDao().getHonbuUserMiseInfo(dto.getCompanyCd(),dto.getOnerDefCd(),dto.getSysDate());
        }else if(dto.getUserTypeCd().equals(ONER_USER)){
            resultList = getMosChickenRevCodSMnueInfoDao().getOnerUserMiseInfo(dto.getCompanyCd(),dto.getUserId(),dto.getSysDate());
        }else if(dto.getUserTypeCd().equals(TENPO_USER)){
            resultList = getMosChickenRevCodSMnueInfoDao().getTenpoUserMiseInfo(dto.getCompanyCd(),dto.getUserId(),dto.getSysDate());
        }
        return resultList;
    }

    private void validate(MosChickenRevInfoDto dto) {
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
    /**
     * mosChickenRevMstOnerInfoDaoを取得
     * @return mosChickenRevMstOnerInfoDao
     */
    public MstOnerInfoDao getMosChickenRevMstOnerInfoDao() {
        return mosChickenRevMstOnerInfoDao;
    }
    /**
     * mosChickenRevMstOnerInfoDaoを設定
     * @param mosChickenRevMstOnerInfoDao
     */
    public void setMosChickenRevMstOnerInfoDao(
            MstOnerInfoDao mosChickenRevMstOnerInfoDao) {
        this.mosChickenRevMstOnerInfoDao = mosChickenRevMstOnerInfoDao;
    }


}
