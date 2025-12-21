/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.impl;

import java.util.Iterator;
import java.util.List;

import org.seasar.framework.exception.SQLRuntimeException;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dao.MstTantouSibuDao;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouSibu;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.UIAllSibu;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.RegistTantouSibuLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 担当支部登録ロジック
 * @author xnkusama
 *
 */
public class RegistTantouSibuLogicImpl implements RegistTantouSibuLogic {
    
    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA004L04";
    
    private MstTantouSibuDao svTantouSibuMstTantouSibuDao;
    
    /**
     * 担当支部登録
     * @param SvTantouSibuDto 画面DTO
     */
    public void execute(SvTantouSibuDto dto) {
        // チェック処理
        validate(dto);
        
        List listDbData = dto.getListDbData();
        
        for (Iterator ite = dto.getListEditData().iterator(); ite.hasNext();) {
            UIAllSibu uiAllSibu = (UIAllSibu) ite.next();
            String companyCd = uiAllSibu.getCompanyCd();
            String sibuCd = uiAllSibu.getSibuCd();
            
            // 更新モード：画面で選択 かつ DBに存在 
            if (uiAllSibu.isSelCheck() && uiAllSibu.isExistRecord()) {
                int index = searchListIndex(listDbData, companyCd, sibuCd);
                MstTantouSibu entity = (MstTantouSibu) listDbData.get(index);
                entity.setLastUser(dto.getBirdUserInfo().getUserID());
                entity.setLastPgm(SvTantouSibuCommon.VIEW_ID);
                getSvTantouSibuMstTantouSibuDao().updateTantouSibu(entity);
            }
            // 新規モード：画面で選択 かつ DBに存在しない
            else if (uiAllSibu.isSelCheck() && !uiAllSibu.isExistRecord()) 
            {
                MstTantouSibu entity = new MstTantouSibu();
                entity.setCompanyCd(companyCd);
                entity.setSvCd(dto.getUserId());
                entity.setSibuCd(sibuCd);
                entity.setFirstUser(dto.getBirdUserInfo().getUserID());
                entity.setFirstPgm(SvTantouSibuCommon.VIEW_ID);
                entity.setFirstTmsp(DateManager.getCurrentTimestamp());
                entity.setLastUser(dto.getBirdUserInfo().getUserID());
                entity.setLastPgm(SvTantouSibuCommon.VIEW_ID);
                try {
                    getSvTantouSibuMstTantouSibuDao().insertTantouSibu(entity);
                }
                catch (SQLRuntimeException ex) {
                    //新規登録できなかった場合
                    throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
                }
            }
            // 削除モード：画面で見選択 かつ DBに存在する
            else if (!uiAllSibu.isSelCheck() && uiAllSibu.isExistRecord())
            {
                int index = searchListIndex(listDbData, companyCd, sibuCd);
                MstTantouSibu entity = (MstTantouSibu) listDbData.get(index);
                getSvTantouSibuMstTantouSibuDao().deleteTantouSibu(entity);
            }
        }
    }

    /**
     * DBデータリストの中からパラメータのキー情報を持つレコードを探す
     * @param listData
     * @param companyCd
     * @param sibuCd
     * @return リストのIndex  -1の場合は、該当データが見つからない時
     */
    private int searchListIndex(List listData, String companyCd, String sibuCd) {
        int index = -1;
        
        for (int i = 0; i< listData.size(); i++) {
            MstTantouSibu entity = (MstTantouSibu) listData.get(i);
            if (companyCd.equals(entity.getCompanyCd()) && sibuCd.equals(entity.getSibuCd()))
            {
                index = i;
                break;
            }
                
        }
        return index;
    }

    /**
     * チェック処理
     * @param dto
     */
    private void validate(SvTantouSibuDto dto) {
        //登録データの存在確認
        if (dto.getListEditData() == null || dto.getListEditData().isEmpty()) {
            dto.setProcessError(true);
            throw new NotExistException("登録データ");
        }
    }
    
    public MstTantouSibuDao getSvTantouSibuMstTantouSibuDao() {
        return svTantouSibuMstTantouSibuDao;
    }

    public void setSvTantouSibuMstTantouSibuDao(
            MstTantouSibuDao svTantouSibuMstTantouSibuDao) {
        this.svTantouSibuMstTantouSibuDao = svTantouSibuMstTantouSibuDao;
    }
}
