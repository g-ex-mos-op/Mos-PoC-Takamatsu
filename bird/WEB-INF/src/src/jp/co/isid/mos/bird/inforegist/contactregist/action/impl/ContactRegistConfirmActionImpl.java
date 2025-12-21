/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.action.impl;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.RegKoukaiTaishoLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.inforegist.contactregist.action.ContactRegistConfirmAction;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistListDto;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.ContactRegistFileDeleteLogic;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.RenewalRenrakuLogic;

/**
 * @author xyuchida
 *
 */
public class ContactRegistConfirmActionImpl implements ContactRegistConfirmAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BIF001A12";
    public static final String regist_ACTION_ID = "BIF001A13";
    public static final String cancel_ACTION_ID = "BIF001A14";
    public static final String callForm_ACTION_ID = "BIF001A15";

    private static final String VIEWID_SEARCH = "BIF001V01";
    private static final String VIEWID_EDIT = "BIF001V02";
    private static final String VIEWID_CONFIRM = "BIF001V03";
    private static final String VIEWID_PUBLICTARGET_SEARCH = "BCO002V01";
    private static final int MAX_ATTACH_FILE = 3;

    private ContactRegistDto contactRegistDto;
    private ContactRegistListDto contactRegistListDto;
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;
    private RenewalRenrakuLogic renewalRenrakuLogic;
    private RegKoukaiTaishoLogic regKoukaiTaishoLogic;
    private ContactRegistFileDeleteLogic contactRegistFileDeleteLogic;
    private FileUploadAction fileUploadAction;

    /**
     * @return contactRegistDto を戻します。
     */
    public ContactRegistDto getContactRegistDto() {
        return contactRegistDto;
    }

    /**
     * @param contactRegistDto contactRegistDto を設定。
     */
    public void setContactRegistDto(ContactRegistDto contactRegistDto) {
        this.contactRegistDto = contactRegistDto;
    }

    /**
     * @return contactRegistListDto を戻します。
     */
    public ContactRegistListDto getContactRegistListDto() {
        return contactRegistListDto;
    }

    /**
     * @param contactRegistListDto contactRegistListDto を設定。
     */
    public void setContactRegistListDto(
            ContactRegistListDto contactRegistListDto) {
        this.contactRegistListDto = contactRegistListDto;
    }

    /**
     * @return publicTargetDto を戻します。
     */
    public PublicTargetDto getPublicTargetDto() {
        return publicTargetDto;
    }

    /**
     * @param publicTargetDto publicTargetDto を設定。
     */
    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
        this.publicTargetDto = publicTargetDto;
    }

    /**
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }

    /**
     * @param publicTargetSearchDto publicTargetSearchDto を設定。
     */
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
    }

    /**
     * @return renewalRenrakuLogic を戻します。
     */
    public RenewalRenrakuLogic getRenewalRenrakuLogic() {
        return renewalRenrakuLogic;
    }

    /**
     * @param renewalRenrakuLogic renewalRenrakuLogic を設定。
     */
    public void setRenewalRenrakuLogic(RenewalRenrakuLogic renewalRenrakuLogic) {
        this.renewalRenrakuLogic = renewalRenrakuLogic;
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
     * @return contactRegistFileDeleteLogic を戻します。
     */
    public ContactRegistFileDeleteLogic getContactRegistFileDeleteLogic() {
        return contactRegistFileDeleteLogic;
    }

    /**
     * @param contactRegistFileDeleteLogic contactRegistFileDeleteLogic を設定。
     */
    public void setContactRegistFileDeleteLogic(
            ContactRegistFileDeleteLogic contactRegistFileDeleteLogic) {
        this.contactRegistFileDeleteLogic = contactRegistFileDeleteLogic;
    }

    /**
     * @return fileUploadAction を戻します。
     */
    public FileUploadAction getFileUploadAction() {
        return fileUploadAction;
    }

    /**
     * @param fileUploadAction fileUploadAction を設定。
     */
    public void setFileUploadAction(FileUploadAction fileUploadAction) {
        this.fileUploadAction = fileUploadAction;
    }

    public String initialize() {
        // 自画面へ遷移
        return null;
    }

    public String regist() {

        // 連絡情報更新
        getRenewalRenrakuLogic().execute(contactRegistDto, publicTargetDto);

        // ファイル削除
        getContactRegistFileDeleteLogic().execute(getContactRegistDto().getDeleteFileNameList());

        for (int i = 1; i <= MAX_ATTACH_FILE; i++) {
            getContactRegistDto().setIndex(i);
            if (getContactRegistDto().getTempFileName() != null
                    && getContactRegistDto().getTempFileName().length() > 0) {
                // アップロード
                getFileUploadAction().upload();
            }
        }
        //条件画面データ初期化処理
        getContactRegistListDto().clearConditionData();

        // 検索画面へ遷移
        return VIEWID_SEARCH;
    }

    public String cancel() {

        // 編集モード判定
        String navigation = null;
        if (getContactRegistDto().getEditMode() == ContactRegistDto.EDIT_MODE_DELETE) {
            // 検索画面へ遷移
            navigation = VIEWID_SEARCH;
        } else {
            // 編集画面へ遷移
            navigation = VIEWID_EDIT;
        }
        return navigation;
    }

    public String callForm() {

        // 公開対象選択のパラメータ設定
        publicTargetSearchDto.setReferenceFlg(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_CONFIRM);
        publicTargetSearchDto.setInfoShu(publicTargetDto.getInfoShu());
        publicTargetSearchDto.setRegDate(publicTargetDto.getRegDate());
        publicTargetSearchDto.setSeq(publicTargetDto.getSeq());
        publicTargetSearchDto.getListTrnControlCompany().clear();
        publicTargetSearchDto.getListTrnControlCompany().addAll(publicTargetDto.getListTrnControlCompany());
        publicTargetSearchDto.getListTrnControlShozoku().clear();
        publicTargetSearchDto.getListTrnControlShozoku().addAll(publicTargetDto.getListTrnControlShozoku());
        publicTargetSearchDto.getListTrnControlGyotai().clear();
        publicTargetSearchDto.getListTrnControlGyotai().addAll(publicTargetDto.getListTrnControlGyotai());
        publicTargetSearchDto.getListTrnControlGyotaiKobetu().clear();
        publicTargetSearchDto.getListTrnControlGyotaiKobetu().addAll(publicTargetDto.getListTrnControlGyotaiKobetu());
        publicTargetSearchDto.getListTrnControlGyotaiTenpo().clear();
        publicTargetSearchDto.getListTrnControlGyotaiTenpo().addAll(publicTargetDto.getListTrnControlGyotaiTenpo());

        // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }
}
