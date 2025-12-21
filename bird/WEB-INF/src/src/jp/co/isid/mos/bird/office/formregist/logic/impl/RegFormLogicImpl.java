package jp.co.isid.mos.bird.office.formregist.logic.impl;

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
import jp.co.isid.mos.bird.office.formregist.dao.UIFormInfoDao;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.RegFormLogic;

/**
 * フォーム情報の登録
 * 
 * 2006/04/03  排他制御対応
 *   
 * @author xytamura
 */
public class RegFormLogicImpl implements RegFormLogic {

    public static final String VIEW_ID = "BOF001";

    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L05";

    private static final int REG_MODE_INSERT = 1;

    private static final int REG_MODE_UPDATE = 2;

    private static final int REG_MODE_DELETE = 3;

    // 情報種別
    private static final String INFO_SHU = "04";

    // LOGIC
    private RegKoukaiTaishoLogic regKoukaiTaishoLogic;

    //【関連文書登録】
    private RegKanrenBunshoLogic regKanrenBunshoLogic;
    //【関連先文書削除】
    private DeleteKanrenSakiBunshoLogic deleteKanrenSakiBunshoLogic;

    // DAO
    private UIFormInfoDao uiFormInfoDao;

    /**
     * フォーム情報の登録
     * @param FormRegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象 共通DTO
     * @param MstUser ユーザー情報
     * @exception ApplicationException
     */
    public void execute(FormRegistFormDto dto, PublicTargetDto publicTargetDto,
            MstUser user) throws ApplicationException {
        // Entity
        UIFormInfo uiFormInfo = dto.getEditEntity();

        UIFormInfo entity = dto.getEditEntity();
        if (dto.getRegMode() == REG_MODE_INSERT) {
            /* 新規 */
            // 現在のMAX SEQ番号 + 1
            int maxSeq = getUiFormInfoDao().getNumber(uiFormInfo.getRegDate()) + 1;

            // SEQをセットしてから登録
            CodeFormatter conv = new CodeFormatter(4, "0000");
            conv.setFormatPattern("0000");
            String strMaxSeq = conv.format(String.valueOf(maxSeq), false);

            entity.setSeq(strMaxSeq);
            entity.setFirstTmsp(DateManager.getCurrentTimestamp());
            entity.setLastTmsp(DateManager.getCurrentTimestamp());
            entity.setFirstPgm(VIEW_ID);
            entity.setLastPgm(VIEW_ID);
            //---2007/08/06 add 企業コードを設定するよう変更
            entity.setRCompanyCd(user.getRCompanyCd());
            
            getUiFormInfoDao().insertForm(entity);

            publicTargetDto.setInfoShu(INFO_SHU);
            publicTargetDto.setRegDate(uiFormInfo.getRegDate());
            publicTargetDto.setSeq(strMaxSeq);

            // 公開対象情報登録
            getRegKoukaiTaishoLogic().execute(publicTargetDto,
                    entity.getLastUser(), entity.getLastPgm());

            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(INFO_SHU, entity.getRegDate(),
                    entity.getSeq(), dto.getListKanrenBunsho(),
                    entity.getLastUser(), entity.getLastPgm());

        } else if (dto.getRegMode() == REG_MODE_UPDATE) {
            /* 更新 */
            entity.setLastPgm(VIEW_ID);
            // 登録者、登録元、登録元名称、企業コードを更新者の情報で置き換える
            entity.setPubUser(user.getUser_id());
            entity.setPubOrg(user.getBumonCd());
            entity.setPubOrgName(user.getBumonName());
            entity.setRCompanyCd(user.getRCompanyCd());
            
            Timestamp beforeUpdate =entity.getLastTmsp();
            getUiFormInfoDao().updateForm(entity);
            // 公開対象情報登録
            getRegKoukaiTaishoLogic().execute(publicTargetDto,
                    entity.getLastUser(), entity.getLastPgm());
            entity.setLastTmsp(beforeUpdate);

            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(INFO_SHU, entity.getRegDate(),
                    entity.getSeq(), dto.getListKanrenBunsho(),
                    entity.getLastUser(), entity.getLastPgm());

        } else if (dto.getRegMode() == REG_MODE_DELETE) {
            /* 削除 */
            // 削除フラグ＝'1'にしてから更新
            entity.setSakujoFlg("1");
            Timestamp beforeUpdate =entity.getLastTmsp();
            getUiFormInfoDao().updateForm(entity);
            entity.setLastTmsp(beforeUpdate);
            
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
            getRegKanrenBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq(), dto.getListKanrenBunsho(),
                    entity.getLastUser(), entity.getLastPgm());
            //関連先から削除
            getDeleteKanrenSakiBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq());

        }
    }

    /**
     * @param uiFormInfoDao uiFormInfoDao を設定。
     */
    public void setUiFormInfoDao(UIFormInfoDao uiFormInfoDao) {
        this.uiFormInfoDao = uiFormInfoDao;
    }

    /**
     * @return uiFormInfoDao を戻します。
     */
    public UIFormInfoDao getUiFormInfoDao() {
        return uiFormInfoDao;
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