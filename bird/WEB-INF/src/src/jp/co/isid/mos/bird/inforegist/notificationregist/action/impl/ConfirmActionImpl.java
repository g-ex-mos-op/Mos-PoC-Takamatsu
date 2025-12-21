/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.action.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenSakiBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.FileDeleteAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.action.impl.FileDeleteActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.inforegist.notificationregist.action.ConfirmAction;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.ConditionDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.RegistFormDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.RegTutatuLogic;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl.UploadLogicImpl;

/**
 * 通達登録 確認画面アクション
 * @author m.onodera
 */
public class ConfirmActionImpl implements CommonAction, ConfirmAction {
    /* アクションID */
    public static final String init_ACTION_ID       = "BIF002A17";
    public static final String cancel_ACTION_ID     = "BIF002A18";
    public static final String regist_ACTION_ID     = "BIF002A19";
    public static final String upload_ACTION_ID     = "BIF002A20";
    public static final String delete_ACTION_ID     = "BIF002A24";
    /* ビューID */
    private static final String VIEWID_CONDITION= "BIF002V01";
    private static final String VIEWID_EDIT     = "BIF002V02";
    private static final String VIEWID_CONFIRM  = "BIF002V03";
    private static final String VIEWID_PUBLICTARGET_SEARCH = "BCO002V01";

    /* DTO */
    private ConditionDto notificationRegistConditionDto;
    private RegistFormDto notificationRegistFormDto;
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;
    private UITutatuInfo uiTutatuInfo;
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;
    /* ロジック */
    // 通達情報の登録ロジック
    private RegTutatuLogic regTutatuLogic;
    // アップロードロジック
    private UploadLogicImpl notificationRegistUploadLogic;
    //自分を関連先として登録している文書の取得ロジック
    private GetKanrenSakiBunshoInfoLogic getKanrenSakiBunshoInfoLogic;
    // 公開対象
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;
    // 関連文書検索
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    //【カテゴリ一覧の取得】
    private GetCategoryLogic getCategoryLogic;


    /**
     * @param regTutatuLogic regTutatuLogic を設定。
     */
    public void setRegTutatuLogic(RegTutatuLogic regTutatuLogic) {
        this.regTutatuLogic = regTutatuLogic;
    }
    /**
     * @return regTutatuLogic を戻します。
     */
    public RegTutatuLogic getRegTutatuLogic() {
        return regTutatuLogic;
    }
    /**
     * @param notificationRegistFormDto notificationRegistFormDto を設定。
     */
    public void setNotificationRegistFormDto(RegistFormDto registFormDto) {
        this.notificationRegistFormDto = registFormDto;
    }
    /**
     * @return notificationRegistFormDto を戻します。
     */
    public RegistFormDto getNotificationRegistFormDto() {
        return notificationRegistFormDto;
    }
    /**
     * @param notificationRegistConditionDto notificationRegistConditionDto を設定。
     */
    public void setNotificationRegistConditionDto(ConditionDto dto) {
        this.notificationRegistConditionDto = dto;
    }
    /**
     * @return notificationRegistConditionDto を戻します。
     */
    public ConditionDto getNotificationRegistConditionDto() {
        return notificationRegistConditionDto;
    }
    /**
     * @param uiTutatuInfo uiTutatuInfo を設定。
     */
    public void setUiTutatuInfo(UITutatuInfo uiTutatuInfo) {
        this.uiTutatuInfo = uiTutatuInfo;
    }
    /**
     * @return uiTutatuInfo を戻します。
     */
    public UITutatuInfo getUiTutatuInfo() {
        return uiTutatuInfo;
    }
    /**
     * @return notificationRegistUploadLogic を戻します。
     */
    public UploadLogicImpl getNotificationRegistUploadLogic() {
        return notificationRegistUploadLogic;
    }
    /**
     * @param notificationRegistUploadLogic notificationRegistUploadLogic を設定。
     */
    public void setNotificationRegistUploadLogic(UploadLogicImpl uploadLogic) {
        this.notificationRegistUploadLogic = uploadLogic;
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
    public void setPublicTargetSearchDto(PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
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
     * 初期処理
     * @return
     */
    public String initialize() {
        if (getNotificationRegistConditionDto().getRegMode() == ConditionDto.REG_MODE_DELETE
                && getNotificationRegistConditionDto().isFlgCondToReg())
        {
            int index = getNotificationRegistConditionDto().getSelectedListIndex();
            UITutatuInfo entity = (UITutatuInfo) getNotificationRegistConditionDto().getListTutatu().get(index);
            getNotificationRegistFormDto().setEditEntity(entity);
            setUiTutatuInfo(entity);

            // 公開対象情報取得
            String regDate = getNotificationRegistConditionDto().getSelectedEntity().getRegDate();
            String seq = getNotificationRegistConditionDto().getSelectedEntity().getSeq();
            getPublicTargetDto().setInfoShu(ConditionActionImpl.INFO_SHU);
            getPublicTargetDto().setRegDate(regDate);
            getPublicTargetDto().setSeq(seq);
            PublicTargetDto publicDto = getKoukaiTaishoLogic.execute(ConditionActionImpl.INFO_SHU, regDate, seq);
            getPublicTargetDto().setListTrnControlCompany(publicDto.getListTrnControlCompany());
            getPublicTargetDto().setListTrnControlGyotai(publicDto.getListTrnControlGyotai());
            getPublicTargetDto().setListTrnControlGyotaiKobetu(publicDto.getListTrnControlGyotaiKobetu());
            getPublicTargetDto().setListTrnControlGyotaiTenpo(publicDto.getListTrnControlGyotaiTenpo());
            getPublicTargetDto().setListTrnControlShozoku(publicDto.getListTrnControlShozoku());

            // 関連文書情報取得
            List listKanren = getGetKanrenBunshoInfoLogic().execute(getBirdUserInfo().getUserID(), ConditionActionImpl.INFO_SHU, regDate, seq, null, null);
            Collections.sort(listKanren, new SortComparator());
            getNotificationRegistFormDto().setListKanrenBunsho(listKanren);

            //リンクファイルとして登録されているか
            List list =  getGetKanrenSakiBunshoInfoLogic().execute(ConditionActionImpl.INFO_SHU, entity.getRegDate(), entity.getSeq());
            getNotificationRegistFormDto().setListKanrenSaki(list);

            getNotificationRegistConditionDto().setFlgCondToReg(false);

            return null;
        }
        // カテゴリ、サブカテゴリ名セット
        setCategoryName(getNotificationRegistFormDto().getEditEntity());

        return null;
    }

    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
        	String infoShu1 = ((UIDocSearch) obj1).getInfoShu();
        	String infoShu2 = ((UIDocSearch) obj2).getInfoShu();

        	int i_infoShu = infoShu1.compareTo(infoShu2);

        	if(i_infoShu != 0) {
        		return i_infoShu;
        	}
            String val1 = ((UIDocSearch) obj1).getRegDate()
                        + ((UIDocSearch) obj1).getSeq();

            String val2 = ((UIDocSearch) obj2).getRegDate()
                        + ((UIDocSearch) obj2).getSeq();
            return val2.compareTo(val1);
        }
    }

    /**
     * 取消
     * @return
     */
    public String cancel() {
        String ret = null;
        if (getNotificationRegistConditionDto().getRegMode() == ConditionDto.REG_MODE_INSERT) {
            ret = VIEWID_EDIT;
        }
        else if (getNotificationRegistConditionDto().getRegMode() == ConditionDto.REG_MODE_UPDATE) {
            ret = VIEWID_EDIT;
        }
        else if (getNotificationRegistConditionDto().getRegMode() == ConditionDto.REG_MODE_DELETE) {
            ret = VIEWID_CONDITION;
        }

        return ret;
    }

    /**
     * 登録処理
     * @return String
     */
    public String regist() {
        // 編集モード設定
        getNotificationRegistFormDto().setRegMode(getNotificationRegistConditionDto().getRegMode());
        // 新規時のキー設定
        if (getNotificationRegistFormDto().getRegMode() == ConditionDto.REG_MODE_INSERT) {
            // 登録日
            getUiTutatuInfo().setRegDate(getBirdDateInfo().getSysDate());
        }
        // ロジック【通達の登録】実行
        getRegTutatuLogic().registTutatu(getNotificationRegistFormDto(), getPublicTargetDto(), getBirdUserInfo().getMstUser());

        if (ConditionDto.REG_MODE_UPDATE == getNotificationRegistFormDto().getRegMode()) {
        	//登録済みファイル削除処理
        	deleteServerFiles();
        }
        // アップロード
		upload();

		//「削除」モードファイル削除処理
        if (ConditionDto.REG_MODE_DELETE == getNotificationRegistFormDto().getRegMode()) {
        	delete();
    	}

        // セッションクリア処理
        getNotificationRegistConditionDto().clearRegistToCond();
        return VIEWID_CONDITION;
    }

    /**
     * アップロード
     * @return
     */
    public String upload() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        FileUploadAction action = (FileUploadAction) container.getComponent("notificationRegistCommonUploadAction");

        action.setUploadDto(getNotificationRegistFormDto());
        action.setUploadLogic(getNotificationRegistUploadLogic());
        for (int i = 1; i <= 4; i++) {
            getNotificationRegistFormDto().setUploadIndex(i);
            switch (i) {
                case 1:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameMain());
                    getNotificationRegistFormDto().setUploadFileName(getNotificationRegistFormDto().getUploadFileNameMain());
                    getNotificationRegistFormDto().setUploadIndex(1);
                    break;
                case 2:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameTenpu1());
                    getNotificationRegistFormDto().setUploadFileName(getNotificationRegistFormDto().getUploadFileNameTenpu1());
                    getNotificationRegistFormDto().setUploadIndex(2);
                    break;
                case 3:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameTenpu2());
                    getNotificationRegistFormDto().setUploadFileName(getNotificationRegistFormDto().getUploadFileNameTenpu2());
                    getNotificationRegistFormDto().setUploadIndex(3);
                    break;
                case 4:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameTenpu3());
                    getNotificationRegistFormDto().setUploadFileName(getNotificationRegistFormDto().getUploadFileNameTenpu3());
                    getNotificationRegistFormDto().setUploadIndex(4);
                    break;
            }

            if (getNotificationRegistFormDto().getTempFileName() != null &&
                    getNotificationRegistFormDto().getTempFileName().trim().length() != 0) {
                action.upload();
            }
        }
        return null;
    }

    /**
     * 削除処理
     * @return
     */
    public String delete() {
    	getNotificationRegistFormDto().setUploadTempFileNameMain("削除対象");
    	getNotificationRegistFormDto().setUploadTempFileNameTenpu1("削除対象");
    	getNotificationRegistFormDto().setUploadTempFileNameTenpu2("削除対象");
    	getNotificationRegistFormDto().setUploadTempFileNameTenpu3("削除対象");
    	deleteServerFiles();
        return null;
    }
    /**
     * 登録済みファイル削除処理
     *
     * @return
     */
    private void deleteServerFiles() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        FileDeleteAction action = (FileDeleteAction) container.getComponent(FileDeleteActionImpl.class);

        action.setUploadDto(getNotificationRegistFormDto());
        action.setDeleteUploadLogic(getNotificationRegistUploadLogic());
        for (int i = 1; i <= 4; i++) {
            getNotificationRegistFormDto().setUploadIndex(i);
            switch (i) {
                case 1:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameMain());
                    getNotificationRegistFormDto().setUploadIndex(1);
                    break;
                case 2:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameTenpu1());
                    getNotificationRegistFormDto().setUploadIndex(2);
                    break;
                case 3:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameTenpu2());
                    getNotificationRegistFormDto().setUploadIndex(3);
                    break;
                case 4:
                    getNotificationRegistFormDto().setTempFileName(getNotificationRegistFormDto().getUploadTempFileNameTenpu3());
                    getNotificationRegistFormDto().setUploadIndex(4);
                    break;
            }

            if (getNotificationRegistFormDto().getTempFileName() != null &&
                    getNotificationRegistFormDto().getTempFileName().trim().length() != 0) {
                action.delete(getNotificationRegistFormDto().getUploadIndex());
            }
        }
    }

    /**
     * 共通フォーム 公開対象を開く
     */
    public String callForm() {

        // 公開対象選択をパラメータを設定(参照モード)
    	publicTargetSearchDto = new PublicTargetSearchDto();
        publicTargetSearchDto.setReferenceFlg(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_CONFIRM);
        publicTargetSearchDto.setInfoShu(publicTargetDto.getInfoShu());
        publicTargetSearchDto.setRegDate(publicTargetDto.getRegDate());
        publicTargetSearchDto.setSeq(publicTargetDto.getSeq());
        publicTargetSearchDto.setListTrnControlCompany(publicTargetDto.getListTrnControlCompany());
        publicTargetSearchDto.setListTrnControlShozoku(publicTargetDto.getListTrnControlShozoku());
        publicTargetSearchDto.setListTrnControlGyotai(publicTargetDto.getListTrnControlGyotai());
        publicTargetSearchDto.setListTrnControlGyotaiKobetu(publicTargetDto.getListTrnControlGyotaiKobetu());
        publicTargetSearchDto.setListTrnControlGyotaiTenpo(publicTargetDto.getListTrnControlGyotaiTenpo());
       // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }
    /**
     * カテゴリ名設定処理
     * @param entity
     */
    private void setCategoryName(UITutatuInfo entity) {
        List listCate = getNotificationRegistFormDto().getListCategory();

        if (listCate == null || listCate.isEmpty()) {
            // ロジック【カテゴリ一覧の取得】
            listCate = getGetCategoryLogic().getCategory(ConditionActionImpl.INFO_SHU);
            getNotificationRegistFormDto().setListCategory(listCate);
        }

        for (int i = 0; i < listCate.size(); i++) {
            MstCategoryInfo mstCate = (MstCategoryInfo) listCate.get(i);
            if (entity.getCateId().equals(mstCate.getCateId())) {
                entity.setCateName(mstCate.getCateName());
            }
        }
    }

    /**
     * 自分を関連先として登録している文書の取得ロジックを取得します。
     * @return 自分を関連先として登録している文書の取得ロジック
     */
    public GetKanrenSakiBunshoInfoLogic getGetKanrenSakiBunshoInfoLogic() {
        return getKanrenSakiBunshoInfoLogic;
    }

    /**
     * 自分を関連先として登録している文書の取得ロジックを設定します。
     * @param 自分を関連先として登録している文書の取得ロジック
     */
    public void setGetKanrenSakiBunshoInfoLogic(
            GetKanrenSakiBunshoInfoLogic getKanrenSakiBunshoInfoLogic) {
        this.getKanrenSakiBunshoInfoLogic = getKanrenSakiBunshoInfoLogic;
    }
    /**
     * 関連文書取得ロジックを取得します。
     * @return 関連文書取得ロジック
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }
    /**
     * 関連文書取得ロジックを設定します。
     * @param 関連文書取得ロジック
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }
    /**
     * 公開対象取得ロジックを取得します。
     * @return 公開対象取得ロジック
     */
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }
    /**
     * 公開対象取得ロジックを設定します。
     * @param 公開対象取得ロジック
     */
    public void setGetKoukaiTaishoLogic(GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }
    public GetCategoryLogic getGetCategoryLogic() {
        return getCategoryLogic;
    }
    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
        this.getCategoryLogic = getCategoryLogic;
    }
}
