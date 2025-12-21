/*
 * 作成日: 2006/04/11

 */
package jp.co.isid.mos.bird.storemanage.staffregist.action.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.staffregist.action.StaffRegistSelectAction;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffDownloadDto;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistDto;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistIFDto;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CreateStaffInfoLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.ExistOwnerCodeLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.GetMiseListLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.GetStaffInfoLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.StaffListLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.util.StaffRegistUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * スタッフ情報登録・変更アクション
 * 
 * @author lee
 * 更新日 2009/09/02 xkinu 一括ダウンロード機能追加と同姓同名確認処理の追加対応
 */
public class StaffRegistSelectActionImpl extends CsvOutput2ActionImpl implements StaffRegistSelectAction {

    /** アクションID */
    public static final String initialize_ACTION_ID =   "BSM004A01";
    public static final String onerSearch_ACTION_ID =   "BSM004A02";
    public static final String insert_ACTION_ID =       "BSM004A03";
    public static final String search_ACTION_ID =       "BSM004A04";
    public static final String edit_ACTION_ID =         "BSM004A05";
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = "BSM004A06";

    private static final String VIEWID_SELECT = "BSM004V01";
    private static final String VIEWID_EDIT = "BSM004V02";
    private static final String VIEWID_OWNER_SEARCH = "BCO006V01";

    private static final String BR11_BUNRUI_LEAVEDT_RETURNDT = "01";

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;

    // DTO
    private StaffRegistDto staffRegistDto;
    private StaffRegistIFDto staffRegistIFDto;
    private PullDownMenuDto pullDownMenuDto;
    private OwnerSearchDto ownerSearchDto;
    private StaffDownloadDto staffRegistStaffDownloadDto;

    // Logic
    private StaffListLogic staffListLogic;
    private GetMiseListLogic getMiseListLogic;
    private CreateStaffInfoLogic createStaffInfoLogic;
    private GetStaffInfoLogic getStaffInfoLogic;
    private ExistOwnerCodeLogic existOwnerCodeLogic;
    private GetGamenRoleLogic gamenRoleLogic;


    public String initialize() {

        // コンテナ取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        StaffRegistDto staffRegistDto = (StaffRegistDto) container.getComponent(StaffRegistDto.class);

        // 画面初期表示
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        if(pullDownMenuDto.isClearFlg()){
            String companyCd = "00";
            String onerCd = null;
            String retireFlg = "0";
            List staffList = null;

            getStaffRegistIFDto().setOtherScreenFlg(false);
            // オーナーユーザのみ初期表示時に検索を行う
            BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
            if(!birdUserInfo.getMstUser().getUserTypeCd().equals("01")){
                // オーナーコード取得
                onerCd = getOnerCd(companyCd, birdUserInfo.getUserOner());
                // 検索
                StaffListLogic staffListLogic = (StaffListLogic) container.getComponent(StaffListLogic.class);
                staffList = staffListLogic.execute(companyCd, onerCd);
            }

            staffRegistDto.setCompanyCd(companyCd);
            staffRegistDto.setOnerCd(onerCd);
            staffRegistDto.setRetireFlg(retireFlg);
            staffRegistDto.setMstStaffList(staffList);
            staffRegistDto.setStaffIndex(selectStaffIndex(staffList));

            // I/F DTO初期化
            StaffRegistIFDto staffRegistIFDto = (StaffRegistIFDto) container.getComponent(StaffRegistIFDto.class);
            staffRegistIFDto.setInitialFlag(false);
            staffRegistIFDto.setEditMode(0);

            //ユーザID
            String userId = getBirdUserInfo().getUserID();
            //--2007/01/29 休職日退職日編集可能ユーザか否かを汎用画面ロール情報から判断します。
            /**
             * 休職日・復職日を表示するユーザか否かを判別する
             * @return true･･･表示可能なユーザ、false･･･表示可能なユーザでない
             */
            getStaffRegistDto().setLeaveReturnFlg(
            		StaffRegistUtil.existGamenRole(getGamenRoleLogic(), userId, BR11_BUNRUI_LEAVEDT_RETURNDT));
            //「163:教育エントリー管理」ロールを所有するユーザが否か判断値を設定します。
            //汎用画面ロール情報の有無から判断します。一括ダウンロードボタン制御の判断値にもします。
            getStaffRegistIFDto().setHaveKyoikuRole(
            		StaffRegistUtil.existGamenRole(getGamenRoleLogic(), userId, "03"));
            pullDownMenuDto.setClearFlg(false);
        }

        // オーナー選択から戻り
        OwnerSearchDto ownerSearchDto = (OwnerSearchDto) container.getComponent(OwnerSearchDto.class);
        if(ownerSearchDto.isActionFlag()){
            // 選択オーナーコード取得
            staffRegistDto.setOnerCd(ownerSearchDto.getOnerCd());
            ownerSearchDto.setActionFlag(false);
        }

        // 自画面へ遷移
        return null;
    }

    /* オーナー選択画面(共通)を起動する */
    public String onerSearch() {

        getOwnerSearchDto().clear();
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setNavigationCase(VIEWID_SELECT);

        // オーナー選択画面へ遷移
        return VIEWID_OWNER_SEARCH;
    }

    public String insert() {

        // 入力値チェック
        validate();

        // 新規スタッフ情報生成
        MstStaff mstStaff = getCreateStaffInfoLogic().execute(
                getStaffRegistDto().getCompanyCd(), getStaffRegistDto().getOnerCd());
        getStaffRegistDto().setMstStaff(mstStaff);
        
        MstStaff mstStaffTmp = new MstStaff();
        mstStaffTmp.setStaffFNameKj(mstStaff.getStaffFNameKj());
        mstStaffTmp.setStaffLNameKj(mstStaff.getStaffLNameKj());
        mstStaffTmp.setStaffFNameKna(mstStaff.getStaffFNameKna());
        mstStaffTmp.setStaffLNameKna(mstStaff.getStaffLNameKna());
        mstStaffTmp.setBirthday(mstStaff.getBirthday());
        mstStaffTmp.setSex(mstStaff.getSex());
        getStaffRegistDto().setMstStaffTmp(mstStaffTmp);

        // 店舗リスト取得
        List mstStaffMiseList = getGetMiseListLogic().execute(
                getStaffRegistDto().getCompanyCd(), getStaffRegistDto().getOnerCd(), getBirdDateInfo().getSysDate());
        getStaffRegistDto().setMstStaffMiseList(mstStaffMiseList);
        /** 同姓同名チェック要に設定 */
        getStaffRegistDto().setDuplicateNameAlert(StaffRegistUtil.ALERT_OFF);
        getStaffRegistDto().setDuplicateName(false);

        // 編集画面へ遷移
        return VIEWID_EDIT;     
    }

    /* 検索 */
    public String search() {

        // 入力値チェック
        validate();

        // 検索
        List staffList = getStaffListLogic().execute(
                getStaffRegistDto().getCompanyCd(), getStaffRegistDto().getOnerCd());
        getStaffRegistDto().setMstStaffList(staffList);

        // 選択スタッフインデックス初期化
        getStaffRegistDto().setStaffIndex(selectStaffIndex(staffList));

        if (staffList == null || staffList.isEmpty()) {
            throw new NoResultException();
        }

        // 自画面へ遷移
        return null;
    }

    /* 編集 */
    public String edit() {

        // 選択されたスタッフのスタッフID取得
        String staffId = ((MstStaff) getStaffRegistDto().getMstStaffList().get(getStaffRegistDto().getStaffIndex())).getStaffId();
        // スタッフ情報取得
        MstStaff mstStaff = getGetStaffInfoLogic().execute(staffId);
        getStaffRegistDto().setMstStaff(mstStaff);
        MstStaff mstStaffTmp = new MstStaff();
        mstStaffTmp.setStaffFNameKj(mstStaff.getStaffFNameKj());
        mstStaffTmp.setStaffLNameKj(mstStaff.getStaffLNameKj());
        mstStaffTmp.setStaffFNameKna(mstStaff.getStaffFNameKna());
        mstStaffTmp.setStaffLNameKna(mstStaff.getStaffLNameKna());
        mstStaffTmp.setBirthday(mstStaff.getBirthday());
        mstStaffTmp.setSex(mstStaff.getSex());
        getStaffRegistDto().setMstStaffTmp(mstStaffTmp);

        // 店舗リスト取得
        List mstStaffMiseList = getGetMiseListLogic().execute(
                mstStaff.getCompanyCd(), mstStaff.getOnerCd(), getBirdDateInfo().getSysDate());
        getStaffRegistDto().setMstStaffMiseList(mstStaffMiseList);
        /** 同姓同名チェック要に設定 */
        getStaffRegistDto().setDuplicateNameAlert(StaffRegistUtil.ALERT_OFF);
        getStaffRegistDto().setDuplicateName(false);
        // 編集画面へ遷移
        return VIEWID_EDIT;
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {
    	//1.【自画面共通】表示検索データ設定処理
		getStaffRegistStaffDownloadDto().setSysDate(getBirdDateInfo().getSysDate());
		setCsvOutputDto(getStaffRegistStaffDownloadDto());
		// TODO 自動生成されたメソッド・スタブ
		super.downloadCsv();
	}

    private String getOnerCd(String companyCd, List userOner) {
        String onerCd = null;
        for (Iterator it = userOner.iterator(); it.hasNext();) {
            UIUserOner uiUserOner = (UIUserOner) it.next();
            if (uiUserOner.getCompanyCd().equals(companyCd)) {
                onerCd = uiUserOner.getOnerCd();
                break;
            }
        }
        return onerCd;
    }

    private void validate() {
        String companyCd = getStaffRegistDto().getCompanyCd();
        if (companyCd == null || companyCd.length() == 0) {
            throw new NotNullException("企業コード", "companyCd", null);
        }
        CodeVerifier companyCodeVerifier = new CodeVerifier(2, true);
        if (!companyCodeVerifier.validate(companyCd)) {
            throw new InvalidInputException("企業コード", "companyCd", null);
        }

        String onerCd = getStaffRegistDto().getOnerCd();
        if(onerCd == null || onerCd.length() == 0){
            throw new NotNullException("オーナーコード", "onerCd", null);
        }
        CodeVerifier ownerCodeVerifier = new CodeVerifier(5, false);
        if (!ownerCodeVerifier.validate(onerCd)) {
            throw new InvalidInputException("オーナーコード", "onerCd", null);
        }
        // 存在チェック
        if (!getExistOwnerCodeLogic().execute(companyCd, onerCd)) {
            throw new NotExistException("オーナーコード", "onerCd", null);
        }
    }

    private int selectStaffIndex(List staffList) {
        // 退職者を含まない場合、退職者以外で先頭のスタッフのIndexを検索
        int staffIndex = 0;
        if (staffList != null && staffRegistDto.getRetireFlg().equals("0")) {
            for (int i = 0; i < staffList.size(); i++) {
                MstStaff mstStaff = (MstStaff) staffList.get(i);
                if (!mstStaff.getSituationKbn().equals("2")) {
                    staffIndex = i;
                    break;
                }
            }
        }
        return staffIndex;
    }

    /**
     * @return ownerSearchDto を戻します。
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }
    /**
     * @param ownerSearchDto ownerSearchDto を設定。
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }
    /**
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    /**
     * @param birdDateInfo birdDateInfo を設定。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public StaffRegistDto getStaffRegistDto() {
        return staffRegistDto;
    }
    public void setStaffRegistDto(StaffRegistDto staffRegistDto) {
        this.staffRegistDto = staffRegistDto;
    }
    public StaffRegistIFDto getStaffRegistIFDto() {
        return staffRegistIFDto;
    }
    public void setStaffRegistIFDto(StaffRegistIFDto staffRegistIFDto) {
        this.staffRegistIFDto = staffRegistIFDto;
    }

    public StaffListLogic getStaffListLogic() {
        return staffListLogic;
    }
    public void setStaffListLogic(StaffListLogic staffListLogic) {
        this.staffListLogic = staffListLogic;
    }
    public GetMiseListLogic getGetMiseListLogic() {
        return getMiseListLogic;
    }
    public void setGetMiseListLogic(GetMiseListLogic getMiseListLogic) {
        this.getMiseListLogic = getMiseListLogic;
    }
    public CreateStaffInfoLogic getCreateStaffInfoLogic() {
        return createStaffInfoLogic;
    }
    public void setCreateStaffInfoLogic(
            CreateStaffInfoLogic createStaffInfoLogic) {
        this.createStaffInfoLogic = createStaffInfoLogic;
    }
    public GetStaffInfoLogic getGetStaffInfoLogic() {
        return getStaffInfoLogic;
    }
    public void setGetStaffInfoLogic(GetStaffInfoLogic getStaffInfoLogic) {
        this.getStaffInfoLogic = getStaffInfoLogic;
    }
    public ExistOwnerCodeLogic getExistOwnerCodeLogic() {
        return existOwnerCodeLogic;
    }
    public void setExistOwnerCodeLogic(ExistOwnerCodeLogic existOwnerCodeLogic) {
        this.existOwnerCodeLogic = existOwnerCodeLogic;
    }
    /**
     * @return gamenRoleLogic を戻します。
     */
    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }
    /**
     * @param gamenRoleLogic 設定する gamenRoleLogic。
     */
    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }

	public StaffDownloadDto getStaffRegistStaffDownloadDto() {
		return staffRegistStaffDownloadDto;
	}

	public void setStaffRegistStaffDownloadDto(
			StaffDownloadDto staffRegistStaffDownloadDto) {
		this.staffRegistStaffDownloadDto = staffRegistStaffDownloadDto;
	}

}
