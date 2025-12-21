package jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.DeleteKanrenSakiBunshoLogic;
import jp.co.isid.mos.bird.common.logic.RegKanrenBunshoLogic;
import jp.co.isid.mos.bird.common.logic.RegKoukaiTaishoLogic;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import jp.co.isid.mos.bird.inforegist.notificationregist.dao.UITutatuInfoDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.RegistFormDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.RegTutatuLogic;

public class RegTutatuLogicImpl implements RegTutatuLogic {
    
    private static final String VIEW_ID = "BIF002";
    /* ロジックID */
    public static final String LOGIC_ID = "BIF002L05";
    
    private static final int REG_MODE_INSERT = 1;
    private static final int REG_MODE_UPDATE = 2;
    private static final int REG_MODE_DELETE = 3;
    // 情報種別
    private static final String INFO_SHU = "02";
    
    // LOGIC
    private RegKoukaiTaishoLogic regKoukaiTaishoLogic;
    //【関連文書登録】
    private RegKanrenBunshoLogic regKanrenBunshoLogic;
    //【関連先文書削除】
    private DeleteKanrenSakiBunshoLogic deleteKanrenSakiBunshoLogic;

    // DAO
    private UITutatuInfoDao uiTutatuInfoDao;
    
    
    
    /**
     * @param uiBunshoInfoDao uiBunshoInfoDao を設定。
     */
    public void setUiTutatuInfoDao(UITutatuInfoDao uiTutatuInfoDao) {
        this.uiTutatuInfoDao = uiTutatuInfoDao;
    }

    /**
     * @return uiBunshoInfoDao を戻します。
     */
    public UITutatuInfoDao getUiTutatuInfoDao() {
        return uiTutatuInfoDao;
    }
    
    /**
     * @return regKoukaiTaishoLogic を戻します。
     */
    public RegKoukaiTaishoLogic getRegKoukaiTaishoLogic() {
        return regKoukaiTaishoLogic;
    }
    /**
     * @param regKoukaiTaishoLogic regKoukaiTaishoLogic を設定。
     */
    public void setRegKoukaiTaishoLogic(
            RegKoukaiTaishoLogic regKoukaiTaishoLogic) {
        this.regKoukaiTaishoLogic = regKoukaiTaishoLogic;
    }
    /**
     * @return regKanrenBunshoLogic を戻します。
     */
    public RegKanrenBunshoLogic getRegKanrenBunshoLogic() {
        return regKanrenBunshoLogic;
    }
    /**
     * @param regKanrenBunshoLogic regKanrenBunshoLogic を設定。
     */
    public void setRegKanrenBunshoLogic(
            RegKanrenBunshoLogic regKanrenBunshoLogic) {
        this.regKanrenBunshoLogic = regKanrenBunshoLogic;
    }

    
    /**
     * 文書情報の登録
     * 
     * 更新日：2006/03/16 xkinu 排他制御処理追加
     *         １．更新ロジックでは更新するDaoメソッドを呼ぶ前に
     *             lastTmspをメソッド変数へ退避する。
     *         ２．更新ロジック終了時に１で退避したlastTmspを再セットする。
     * 
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象 共通DTO
     * @param MstUser ユーザー情報
     * @exception ApplicationException
     */
    public void registTutatu(RegistFormDto dto, 
                               PublicTargetDto publicTargetDto,
                               MstUser user) 
                    throws ApplicationException 
    {
        // Entity
        UITutatuInfo uiTutatuInfo = dto.getEditEntity();
        
        UITutatuInfo entity = dto.getEditEntity();
        /*
         * 更新ロジックでは更新するDaoメソッドを呼ぶ前に
         * lastTimestampをメソッド変数へ退避する。
         */
        Timestamp lastTimestamp = entity.getLastTmsp();
        
        if (dto.getRegMode() == REG_MODE_INSERT) {
            /* 新規 */
            // 現在のMAX SEQ番号
            int maxSeq = getUiTutatuInfoDao().getNumber(uiTutatuInfo.getRegDate());
            // SEQをセットしてから登録
            CodeFormatter codeFomatter = new CodeFormatter(4);
            codeFomatter.setFormatPattern("0000");
            entity.setSeq(codeFomatter.format(String.valueOf(maxSeq),true));
            entity.setFirstTmsp(DateManager.getCurrentTimestamp());
            entity.setLastTmsp(DateManager.getCurrentTimestamp());
            entity.setFirstPgm(VIEW_ID);
            entity.setLastPgm(VIEW_ID);
            getUiTutatuInfoDao().insertTutatu(entity);
            publicTargetDto.setRegDate(entity.getRegDate());
            publicTargetDto.setSeq(entity.getSeq());
            // 公開対象情報登録
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, entity.getLastUser(), entity.getLastPgm());
            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(
                  INFO_SHU
                , entity.getRegDate()
                , entity.getSeq()
                , dto.getListKanrenBunsho()
                , user.getUser_id()
                , entity.getLastPgm());

        }
        else if (dto.getRegMode() == REG_MODE_UPDATE) {
            /* 更新 */
            entity.setLastPgm(VIEW_ID);
            // 登録者、登録元、登録元名称、企業コードを更新者の情報で置き換える
            entity.setPubUser(user.getUser_id());
            entity.setPubOrg(user.getBumonCd());
            entity.setPubOrgName(user.getBumonName());
            entity.setRCompanyCd(user.getRCompanyCd());
            getUiTutatuInfoDao().updateTutatu(entity);
            // 公開対象情報登録
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, entity.getLastUser(), entity.getLastPgm());
            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(
                  INFO_SHU
                , entity.getRegDate()
                , entity.getSeq()
                , dto.getListKanrenBunsho()
                , user.getUser_id()
                , entity.getLastPgm());

        }
        else if (dto.getRegMode() == REG_MODE_DELETE) {
            /* 削除 */
            // 削除フラグ＝'1'にしてから更新
        	entity.setSakujoFlg("1");
            getUiTutatuInfoDao().updateTutatu(entity);

            // 公開対象情報削除
            publicTargetDto.setListTrnControlCompany(new ArrayList());
            publicTargetDto.setListTrnControlGyotai(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiKobetu(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiTenpo(new ArrayList());
            publicTargetDto.setListTrnControlShozoku(new ArrayList());
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, entity.getLastUser(), entity.getLastPgm());

            //関連文書の削除
            dto.setListKanrenBunsho(new ArrayList());
            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(
                  INFO_SHU
                , entity.getRegDate()
                , entity.getSeq()
                , dto.getListKanrenBunsho()
                , user.getUser_id()
                , entity.getLastPgm());
            //関連先から削除
            getDeleteKanrenSakiBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq());

        }
        /*
         * 更新ロジック終了時に１で退避したlastTmspを再セットする。
         */
        entity.setLastTmsp(lastTimestamp);

        
    }

    /**
     * 関連先文書削除ロジックを取得します。
     * @return 関連先文書削除ロジック 
     */
    public DeleteKanrenSakiBunshoLogic getDeleteKanrenSakiBunshoLogic() {
        return deleteKanrenSakiBunshoLogic;
    }

    /**
     * 関連先文書削除ロジックを設定します。
     * @param 関連先文書削除ロジック
     */
    public void setDeleteKanrenSakiBunshoLogic(
            DeleteKanrenSakiBunshoLogic deleteKanrenSakiBunshoLogic) {
        this.deleteKanrenSakiBunshoLogic = deleteKanrenSakiBunshoLogic;
    }

}
