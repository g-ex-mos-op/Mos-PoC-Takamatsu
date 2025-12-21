/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dao.MstTantouMiseDao;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouMise;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.RegistTantouMiseLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * 担当店舗登録ロジック
 * @author xnkusama
 *
 */
public class RegistTantouMiseLogicImpl implements RegistTantouMiseLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA004L05";
    
    /* DAO */
    private MstTantouMiseDao svTantouSibuMstTantouMiseDao;
    
    /**
     * 担当店舗登録
     * @param SvTantouSibuDto 画面DTO
     */
    public void execute(SvTantouSibuDto dto) {
        // チェック処理
        validate(dto);
        
        
        List listData = dto.getListUploadData();
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            MstTantouMise entityRegist = (MstTantouMise) ite.next();
            // 会社コード、店コード、ユーザーIDをキーにDBにレコードが存在するか
            MstTantouMise entityDb = getSvTantouSibuMstTantouMiseDao()
                                        .checkExist(entityRegist.getCompanyCd(),
                                                    entityRegist.getMiseCd());
            
            //レコードが存在する場合は更新モード
            if (entityDb != null) {
                entityDb.setSvCd(entityRegist.getSvCd());
                entityDb.setLastUser(dto.getBirdUserInfo().getUserID());
                entityDb.setLastPgm(SvTantouSibuCommon.VIEW_ID);
                getSvTantouSibuMstTantouMiseDao().updateMstTantouMise(entityDb);
            }
            //レコードが存在しない場合は新規モード
            else {
                MstTantouMise entity = new MstTantouMise();
                entity.setCompanyCd(entityRegist.getCompanyCd());
                entity.setMiseCd(entityRegist.getMiseCd());
                entity.setSvCd(entityRegist.getSvCd());
                entity.setFirstUser(dto.getBirdUserInfo().getUserID());
                entity.setFirstPgm(SvTantouSibuCommon.VIEW_ID);
                entity.setFirstTmsp(DateManager.getCurrentTimestamp());
                entity.setLastUser(dto.getBirdUserInfo().getUserID());
                entity.setLastPgm(SvTantouSibuCommon.VIEW_ID);
                try {
                    getSvTantouSibuMstTantouMiseDao().insertMstTantouMise(entity);
                }
                catch (SQLRuntimeException ex) {
                    throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
                }
            }
        }
    }

    /**
     * チェック処理
     * @param dto
     */
    private void validate(SvTantouSibuDto dto) {
        //登録データの存在チェック
        if (dto.getListUploadData() == null || dto.getListUploadData().isEmpty()) {
            dto.setProcessError(true);
            throw new NotExistException("登録データ");
        }
    }
    
    public MstTantouMiseDao getSvTantouSibuMstTantouMiseDao() {
        return svTantouSibuMstTantouMiseDao;
    }

    public void setSvTantouSibuMstTantouMiseDao(
            MstTantouMiseDao svTantouSibuMstTantouMiseDao) {
        this.svTantouSibuMstTantouMiseDao = svTantouSibuMstTantouMiseDao;
    }
}
