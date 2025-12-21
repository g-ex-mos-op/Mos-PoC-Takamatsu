package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import java.util.Iterator;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.TrnMosChickenInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.TrnMosChickenDetailInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevNewInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.UpdateMosChickenRevLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstCmenuInfo;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
/**
 * モスチキン予約情報登録・更新ロジック
 * @author inazawa
 * 2006/09/19
 */
public class UpdateMosChickenRevLogicImpl implements UpdateMosChickenRevLogic {
    /*モスチキン情報更新Dao*/
    private TrnMosChickenInfoDao trnMosChickenInfoDao;
    /*モスチキン明細情報更新Dao*/
    private TrnMosChickenDetailInfoDao trnMosChickenDetailInfoDao;

    /**マスタ登録商品取得ロジック*/
    private MosChickenRevNewInfoLogic mosChickenRevNewInfoLogic;

    /*キャンセルフラグ0：未、1：済、2:新規登録*/
    public static final String cancelZumi = "1";
    public static final String cancelNot  = "0";
    /* VIEW_ID */
    public static final String VIEW_ID            = "BBS016";
    public static final String condition_VIEW_ID  = "BBS016V01";
    public static final String result_VIEW_ID     = "BBS016V02";
    public static final String menuAdd_VIEW_ID    = "BBS016V03";
    /*新規／修正0：新規、2：修正*/
    public static final String sinki = "1";
    public static final String shusei  = "0";
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L09";
    /** 指定改行文字 */
    private static final String _ENTER_WORD = "`";

    /**
     * 登録
     */
    public void insert(MosChickenRevInfoDto dto){
        getTrnMosChickenInfoDao().insertMosChickenInfo(setEntity(dto));

        // マスタ登録商品取得
        List revList = getMosChickenRevNewInfoLogic().execute(dto);
        if(revList == null || revList.size() ==0){
            throw new NotExistException("登録商品");
        }

        //BT71CRSDの登録
        for(int i = 0;i<dto.getListMosChiCkenDatailInfo().size();i++){
            MstCmenuInfo mstCmenuInfo = (MstCmenuInfo)dto.getListMosChiCkenDatailInfo().get(i);
            //【予約数＝０且つマスタに登録されていない商品】以外の場合、登録する
            if (!(mstCmenuInfo.getReserveAmt() == 0 && !isMstMenuCd(revList, mstCmenuInfo.getMenuCd()))) {
                TrnMosChikenDet trnMosChikenDet = new TrnMosChikenDet();
                trnMosChikenDet.setCkanriNo(dto.getCkanriNo());
                trnMosChikenDet.setCompanyCd(dto.getCompanyCd());
                trnMosChikenDet.setMiseCd(dto.getMiseCd());
                trnMosChikenDet.setSeqNo(dto.getMaxSeqNo()+1);
                trnMosChikenDet.setMenuCd(mstCmenuInfo.getMenuCd());
                trnMosChikenDet.setReserveAmt(mstCmenuInfo.getReserveAmt());
                trnMosChikenDet.setFirstUser(dto.getBirdUserInfo().getUserID());
                trnMosChikenDet.setFirstPgm(VIEW_ID);
                trnMosChikenDet.setFirstTmsp(DateManager.getCurrentTimestamp());
                trnMosChikenDet.setLastUser(dto.getBirdUserInfo().getUserID());
                trnMosChikenDet.setLastPgm(VIEW_ID);
                trnMosChikenDet.setLastTmsp(DateManager.getCurrentTimestamp());
                getTrnMosChickenDetailInfoDao().insertMosChickenDetInfo(trnMosChikenDet);
            }
        }
    }

    /**
     * BT70CRSDのエンティティーセット
     * @param dto
     * @return
     */
    private TrnMosChikenInfo setEntity(MosChickenRevInfoDto dto) {
        //BT70CRSVの登録
        DateFormatter formatter = new DateFormatter();
        TrnMosChikenInfo trnMosChikenInfo = new TrnMosChikenInfo();
        if(dto.getNewFlg().equals(shusei)){
            trnMosChikenInfo = (TrnMosChikenInfo)dto.getListMosChiCkenInfo().get(dto.getRevIndex());
        }
        trnMosChikenInfo.setCkanriNo(dto.getCkanriNo());
        trnMosChikenInfo.setCompanyCd(dto.getCompanyCd());
        trnMosChikenInfo.setMiseCd(dto.getMiseCd());
        if(dto.getNewFlg().equals(shusei)){
            trnMosChikenInfo.setSeqNo(dto.getSeqNo());
        }else if(dto.getNewFlg().equals(sinki)){
            trnMosChikenInfo.setSeqNo(dto.getMaxSeqNo()+1);
        }
        trnMosChikenInfo.setReserveDt(String.valueOf(formatter.format(String.valueOf(dto.getReservDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD)));
        trnMosChikenInfo.setAcceptDt(dto.getSysDate());
        trnMosChikenInfo.setReserveHh(dto.getReservHour());
        trnMosChikenInfo.setReserveMm(dto.getReservMin());
        trnMosChikenInfo.setRemark(dto.getBikou());
        if(dto.getMemo() != null){
            trnMosChikenInfo.setMemo(createMemo(dto.getMemo()));
        }else{
            trnMosChikenInfo.setMemo("");
        }
        trnMosChikenInfo.setPaymentFlg(dto.getPaymentFlg());
        trnMosChikenInfo.setPremiumFlg(dto.getPremiumFlg());
        trnMosChikenInfo.setCancelFlg(cancelNot);
        trnMosChikenInfo.setCancelDt("");

        trnMosChikenInfo.setFirstUser(dto.getBirdUserInfo().getUserID());
        trnMosChikenInfo.setFirstPgm(VIEW_ID);
        trnMosChikenInfo.setFirstTmsp(DateManager.getCurrentTimestamp());
        trnMosChikenInfo.setLastUser(dto.getBirdUserInfo().getUserID());
        trnMosChikenInfo.setLastPgm(VIEW_ID);
        if(dto.getNewFlg().equals(sinki)){
            trnMosChikenInfo.setLastTmsp(DateManager.getCurrentTimestamp());
        }
        return trnMosChikenInfo;
    }
    /**
     * 更新
     */
    public void update(MosChickenRevInfoDto dto){
        getTrnMosChickenInfoDao().updateMosChickenInfo(setEntity(dto));

        // マスタ登録商品取得
        List revList = getMosChickenRevNewInfoLogic().execute(dto);
        if(revList == null || revList.size() ==0){
            throw new NotExistException("登録商品");
        }

        //BT71CRSDの登録
        for(int i = 0;i<dto.getListMosChiCkenDatailInfo().size();i++){
            TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)dto.getListMosChiCkenDatailInfo().get(i);
            trnMosChikenDet.setCkanriNo(dto.getCkanriNo());
            trnMosChikenDet.setCompanyCd(dto.getCompanyCd());
            trnMosChikenDet.setMiseCd(dto.getMiseCd());
            trnMosChikenDet.setSeqNo(trnMosChikenDet.getSeqNo());
            trnMosChikenDet.setMenuCd(trnMosChikenDet.getMenuCd());

            //【予約数＝０且つマスタに登録されていない商品】以外の場合、削除する
            if (trnMosChikenDet.getReserveAmt() == 0
                && !isMstMenuCd(revList, trnMosChikenDet.getMenuCd())) {
                getTrnMosChickenDetailInfoDao().deleteMosChickenDetInfo(trnMosChikenDet);
                continue;
            }

            //【予約数＝０且つマスタに登録されていない商品】以外の場合
            trnMosChikenDet.setReserveAmt(trnMosChikenDet.getReserveAmt());
            trnMosChikenDet.setLastUser(dto.getBirdUserInfo().getUserID());
            trnMosChikenDet.setLastPgm(VIEW_ID);
            try {
                // 更新
                getTrnMosChickenDetailInfoDao().updateMosChickenDetInfo(trnMosChikenDet);
            } catch (NotSingleRowUpdatedRuntimeException ne) {
                //新規登録
                trnMosChikenDet.setFirstUser(dto.getBirdUserInfo().getUserID());
                trnMosChikenDet.setFirstPgm(VIEW_ID);
                trnMosChikenDet.setFirstTmsp(DateManager.getCurrentTimestamp());
                getTrnMosChickenDetailInfoDao().insertMosChickenDetInfo(trnMosChikenDet);
            }
        }
    }

    /**
     * マスタに登録されているかを判定する
     * @param list      マスタ登録商品リスト
     * @param menuCd    商品コード
     * @return boolean  true:マスタ商品コード、false:追加された商品コード
     */
    private boolean isMstMenuCd(List list, String menuCd) {
        for (Iterator it = list.iterator(); it.hasNext();) {
            MstCmenuInfo menuInfo = (MstCmenuInfo) it.next();
            // 渡された商品コードがマスタ登録商品の場合、false
            if (menuCd.equals(menuInfo.getMenuCd())) {
                return true;
            }
        }
        return false;
    }

    /**
     * メモ欄の改行処理
     * @param memo
     * @return
     */    
    private String createMemo(String memo) {
        String addBrMemo = "";

        int index = 0;
        boolean endFlg = true;
        
        if(memo != null && !memo.equals("")){
            //掲示板本文 or 備考
            while(endFlg){
                index = memo.indexOf("\n");
                if(index == -1) {
                    endFlg = false;
                }else{
                    String topMemo = memo.substring(0,index-1);
                    String bottomMemo = memo.substring(index+1);
                    memo = topMemo + _ENTER_WORD+ bottomMemo;
                }
            }
            addBrMemo = memo;
        }

        return addBrMemo;
    }

    /**
     * trnMosChickenInfoDaoを返す
     * @return trnMosChickenInfoDao
     */
    public TrnMosChickenInfoDao getTrnMosChickenInfoDao() {
        return trnMosChickenInfoDao;
    }
    /**
     * trnMosChickenInfoDaoを設定
     * @param trnMosChickenInfoDao
     */
    public void setTrnMosChickenInfoDao(TrnMosChickenInfoDao trnMosChickenInfoDao) {
        this.trnMosChickenInfoDao = trnMosChickenInfoDao;
    }
    /**
     * trnMosChickenDetailInfoDaoを返す
     * @return trnMosChickenDetailInfoDao
     */
    public TrnMosChickenDetailInfoDao getTrnMosChickenDetailInfoDao() {
        return trnMosChickenDetailInfoDao;
    }
    /**
     * trnMosChickenDetailInfoDaoを設定
     * @param trnMosChickenDetailInfoDao
     */
    public void setTrnMosChickenDetailInfoDao(
            TrnMosChickenDetailInfoDao trnMosChickenDetailInfoDao) {
        this.trnMosChickenDetailInfoDao = trnMosChickenDetailInfoDao;
    }
    /**
     * mosChickenRevNewInfoLogicを戻す
     * @return mosChickenRevNewInfoLogic
     */
    public MosChickenRevNewInfoLogic getMosChickenRevNewInfoLogic() {
        return mosChickenRevNewInfoLogic;
    }
    /**
     * mosChickenRevNewInfoLogicを設定
     * @param mosChickenRevNewInfoLogic
     */
    public void setMosChickenRevNewInfoLogic(
            MosChickenRevNewInfoLogic mosChickenRevNewInfoLogic) {
        this.mosChickenRevNewInfoLogic = mosChickenRevNewInfoLogic;
    }

}
