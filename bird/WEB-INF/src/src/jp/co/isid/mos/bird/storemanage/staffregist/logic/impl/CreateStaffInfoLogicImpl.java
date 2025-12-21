/*
 * 作成日: 2006/06/07
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CreateStaffInfoLogic;

/**
 * @author xyuchida
 */
public class CreateStaffInfoLogicImpl implements CreateStaffInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM004L04";

    public MstStaff execute(String companyCd, String onerCd) {

        MstStaff mstStaff = new MstStaff();

        // 新規フラグON
        mstStaff.setInsertFlag(true);

        // キー項目設定
        mstStaff.setCompanyCd(companyCd);
        mstStaff.setOnerCd(onerCd);
        mstStaff.setOldOnerCd(onerCd);

        // 画面編集項目初期値設定
        mstStaff.setStaffLNameKj("");
        mstStaff.setStaffFNameKj("");
        mstStaff.setStaffLNameKna("");
        mstStaff.setStaffFNameKna("");
        mstStaff.setSex("0");
        mstStaff.setMiseCd1("");
        mstStaff.setSituationKbn("0");
        mstStaff.setNote("");
        // 日付
        mstStaff.setBirthday("");
        mstStaff.setMoveDt("");
        mstStaff.setRetireDt("");
        mstStaff.setLeaveDt("");
        mstStaff.setReturnDt("");
        mstStaff.setBirthdayYear("");
        mstStaff.setBirthdayMonth("");
        mstStaff.setBirthdayDay("");
        mstStaff.setMoveDtYear("");
        mstStaff.setMoveDtMonth("");
        mstStaff.setMoveDtDay("");
        mstStaff.setRetireDtYear("");
        mstStaff.setRetireDtMonth("");
        mstStaff.setRetireDtDay("");
        mstStaff.setLeaveDtYear("");
        mstStaff.setLeaveDtMonth("");
        mstStaff.setLeaveDtDay("");
        mstStaff.setReturnDtYear("");
        mstStaff.setReturnDtMonth("");
        mstStaff.setReturnDtDay("");

        // 未使用項目設定
        mstStaff.setMiseCd2("");
        mstStaff.setMiseCd3("");
        mstStaff.setMiseCd4("");
        mstStaff.setMiseCd5("");
        mstStaff.setLeaveDt("");
        mstStaff.setReturnDt("");
        mstStaff.setSpareFlg1("");
        mstStaff.setSpareFlg2("");
        mstStaff.setSpareFlg3("");
        mstStaff.setMiseNameKj("");
        mstStaff.setCloseDt("");

        return mstStaff;
    }
}
