/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.office.bbsregist.action.impl;

import org.apache.commons.beanutils.PropertyUtils;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.office.bbsregist.action.KeijibanReferenceAction;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;
import jp.co.isid.mos.bird.office.bbsregist.logic.CheckEditAllowedLogic;
import jp.co.isid.mos.bird.office.bbsregist.logic.RegKeijibanLogic;

/**
 * 掲示板登録 参照画面アクション
 * @author xytamura
 */
public class KeijibanReferenceActionImpl implements CommonAction,
        KeijibanReferenceAction {
    /* アクションID */
    public static final String initialize_ACTION_ID = "BOF003A08";

    public static final String cancel_ACTION_ID = "BOF003A09";

    public static final String regist_ACTION_ID = "BOF003A10";

    public static final String delete_ACTION_ID = "BOF003A11";


    /* DTO */
    private KeijibanConditionDto keijibanConditionDto;

    private KeijibanRegistDto keijibanRegistDto;


    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    // ロジック【掲示板情報の登録】
    private RegKeijibanLogic regKeijibanLogic;
    // ロジック【更新可能ユーザチェック】
    private CheckEditAllowedLogic checkEditAllowedLogic;

    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {

        // 【ロジック】更新可能ユーザチェック
        boolean isEdit = getCheckEditAllowedLogic().execute(
                getKeijibanRegistDto().getRefEntity(), birdUserInfo.getMstUser());
        getKeijibanRegistDto().setEditFlg(isEdit);

        return null;
    }


    /**
     * 取消
     * @return
     */
    public String cancel() {

        return KeijibanConditionDto.VIEWID_CONDITION;
    }

    /**
     * 登録処理
     * @return String
     */
    public String regist() {

        UIKeijiban entity = getKeijibanRegistDto().getRefEntity();
        UIKeijiban editTarget = new UIKeijiban();
        try {
            // 値をコピー
            PropertyUtils.copyProperties(editTarget, entity);

        } catch (Exception ex) {
            throw new FtlSystemException("社内掲示板", ex.toString(), ex);
        }

        getKeijibanRegistDto().setRegMode(KeijibanConditionDto.REG_MODE_UPDATE);
        getKeijibanRegistDto().setEditEntity(editTarget);
        getKeijibanConditionDto().setFlgCondToReg(true);
        return KeijibanConditionDto.VIEWID_EDIT;
    }


    /**
     * @param registFormDto registFormDto を設定。
     */
    public void setKeijibanRegistDto(KeijibanRegistDto keijibanRegistDto) {
        this.keijibanRegistDto = keijibanRegistDto;
    }

    /**
     * @return registFormDto を戻します。
     */
    public KeijibanRegistDto getKeijibanRegistDto() {
        return keijibanRegistDto;
    }

    /**
     * @param conditionDto conditionDto を設定。
     */
    public void setKeijibanConditionDto(
            KeijibanConditionDto keijibanConditionDto) {
        this.keijibanConditionDto = keijibanConditionDto;
    }

    /**
     * @return conditionDto を戻します。
     */
    public KeijibanConditionDto getKeijibanConditionDto() {
        return keijibanConditionDto;
    }



    /**
     * BIRDユーザー情報設定処理
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

//    /**
//     * セッションクリア処理
//     */
//    private void clearSession() {
//        getKeijibanRegistDto().clearData();
//        getKeijibanConditionDto().clearData();
//        setKeijibanRegistDto(null);
//        setKeijibanConditionDto(null);
//        setUIKeijiban(null);
//        // getPublicTargetSearchDto().clearData();
//
//    }

    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo birdDateInfo を設定。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 削除アクション
     * @return String
     */
    public String delete() {
        getKeijibanRegistDto().setRegMode(KeijibanConditionDto.REG_MODE_DELETE);
        getKeijibanRegistDto().setEditEntity(getKeijibanRegistDto().getRefEntity());
        
// add start xkhata 
        getKeijibanRegistDto().setDelToCondFlg(true);
// add end
        // ロジック【掲示板情報の登録】
        getRegKeijibanLogic().execute(getKeijibanRegistDto(), getBirdUserInfo().getMstUser(), getBirdDateInfo());

        return KeijibanConditionDto.VIEWID_CONDITION;
    }

    /**
     * ロジック【掲示板情報の登録】を取得します。
     * @return ロジック【掲示板情報の登録】 
     */
    public RegKeijibanLogic getRegKeijibanLogic() {
        return regKeijibanLogic;
    }

    /**
     * ロジック【掲示板情報の登録】を設定します。
     * @param ロジック【掲示板情報の登録】
     */
    public void setRegKeijibanLogic(RegKeijibanLogic regKeijibanLogic) {
        this.regKeijibanLogic = regKeijibanLogic;
    }

    /**
     * ロジック【更新可能ユーザチェック】を取得します。
     * @return ロジック【更新可能ユーザチェック】 
     */
    public CheckEditAllowedLogic getCheckEditAllowedLogic() {
        return checkEditAllowedLogic;
    }

    /**
     * ロジック【更新可能ユーザチェック】を設定します。
     * @param ロジック【更新可能ユーザチェック】
     */
    public void setCheckEditAllowedLogic(CheckEditAllowedLogic checkEditAllowedLogic) {
        this.checkEditAllowedLogic = checkEditAllowedLogic;
    }
    
}