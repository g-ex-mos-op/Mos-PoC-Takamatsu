/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.action.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.inforegist.contactregist.action.ContactRegistSearchAction;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistListDto;
import jp.co.isid.mos.bird.inforegist.contactregist.entity.UIRenrakuInfo;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.GetRenrakuCountLogic;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.GetRenrakuLogic;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author xyuchida
 * 
 */
public class ContactRegistSearchActionImpl implements ContactRegistSearchAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BIF001A01";

    public static final String startRegist_ACTION_ID = "BIF001A02";

    public static final String search_ACTION_ID = "BIF001A03";

    public static final String startUpdate_ACTION_ID = "BIF001A04";

    public static final String startDelete_ACTION_ID = "BIF001A05";

    private static final String VIEWID = "BIF001";

    private static final String VIEWID_EDIT = "BIF001V02";

    private static final String VIEWID_CONFIRM = "BIF001V03";

    private static final String INFO_SHU_CONTACT = "01";

    private ContactRegistDto contactRegistDto;

    private ContactRegistListDto contactRegistListDto;

    private PublicTargetDto contactRegistPublicTargetDto;

    private GetCategoryLogic getCategoryLogic;

    private GetRenrakuLogic getRenrakuLogic;

    private GetRenrakuCountLogic getRenrakuCountLogic;

    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;

    private BirdUserInfo birdUserInfo;

    private BirdDateInfo birdDateInfo;

    private PullDownMenuDto pullDownMenuDto;

    private int targetDateMonths;

    private int maxPageCount;

    private int selectPageNumber;

    // 関連文書検索ロジック
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;

    //情報種別
    private static final String INFO_SHU = "01";
    
    /**
     * @param contactRegistDto
     *            contactRegistDto を設定。
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
     * @param contactRegistListDto
     *            contactRegistListDto を設定。
     */
    public void setContactRegistListDto(
            ContactRegistListDto contactRegistListDto) {
        this.contactRegistListDto = contactRegistListDto;
    }

    /**
     * @return getCategoryLogic を戻します。
     */
    public GetCategoryLogic getGetCategoryLogic() {
        return getCategoryLogic;
    }

    /**
     * @param getCategoryLogic
     *            getCategoryLogic を設定。
     */
    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
        this.getCategoryLogic = getCategoryLogic;
    }

    /**
     * @return getRenrakuLogic を戻します。
     */
    public GetRenrakuLogic getGetRenrakuLogic() {
        return getRenrakuLogic;
    }

    /**
     * @param getRenrakuLogic
     *            getRenrakuLogic を設定。
     */
    public void setGetRenrakuLogic(GetRenrakuLogic getRenrakuLogic) {
        this.getRenrakuLogic = getRenrakuLogic;
    }

    /**
     * @return getRenrakuCountLogic を戻します。
     */
    public GetRenrakuCountLogic getGetRenrakuCountLogic() {
        return getRenrakuCountLogic;
    }

    /**
     * @param getRenrakuCountLogic
     *            getRenrakuCountLogic を設定。
     */
    public void setGetRenrakuCountLogic(
            GetRenrakuCountLogic getRenrakuCountLogic) {
        this.getRenrakuCountLogic = getRenrakuCountLogic;
    }

    /**
     * @return contactRegistDto を戻します。
     */
    public ContactRegistDto getContactRegistDto() {
        return contactRegistDto;
    }

    /**
     * @return getKoukaiTaishoLogic を戻します。
     */
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }

    /**
     * @param getKoukaiTaishoLogic
     *            getKoukaiTaishoLogic を設定。
     */
    public void setGetKoukaiTaishoLogic(
            GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }

    /**
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * @param birdUserInfo
     *            birdUserInfo を設定。
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
     * @param birdDateInfo
     *            birdDateInfo を設定。
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
     * @param pullDownMenuDto
     *            pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * @return targetDateMonths を戻します。
     */
    public int getTargetDateMonths() {
        return targetDateMonths;
    }

    /**
     * @param targetDateMonths
     *            targetDateMonths を設定。
     */
    public void setTargetDateMonths(int targetDateMonths) {
        this.targetDateMonths = targetDateMonths;
    }

    /**
     * @return maxPageCount を戻します。
     */
    public int getMaxPageCount() {
        return maxPageCount;
    }

    /**
     * @param maxPageCount
     *            maxPageCount を設定。
     */
    public void setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
    }

    /**
     * @return selectPageNumber を戻します。
     */
    public int getSelectPageNumber() {
        return selectPageNumber;
    }

    /**
     * @param selectPageNumber
     *            selectPageNumber を設定。
     */
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }

    public String initialize() {
        // 初期処理判定
        if (pullDownMenuDto.isClearFlg()) {

            // 検索条件クリア
            contactRegistListDto.setInputTargetDate(null);
            contactRegistListDto.setInputCategoryId(null);

            // 検索結果クリア
            contactRegistListDto.setSelectedIndex(0);
            contactRegistListDto.getContactList().clear();

            // 対象年月リスト作成
            contactRegistDto
                    .setTargetDateList(createTargetDateList(birdDateInfo
                            .getSysDate()));

            // カテゴリリスト取得
            contactRegistDto.setCategoryList(getCategoryLogic
                    .getCategory(INFO_SHU_CONTACT));

            // 登録モード初期化
            contactRegistDto.setEditMode(ContactRegistDto.EDIT_MODE_DEFAULT);

            pullDownMenuDto.setClearFlg(false);
        }

        // 自画面へ遷移
        return null;
    }

    public String search() {

        // 入力用一時プロパティから検索条件設定
        getContactRegistListDto().setTargetDate(
                getContactRegistListDto().getInputTargetDate());
        getContactRegistListDto().setCategoryId(
                getContactRegistListDto().getInputCategoryId());

        // ページ番号初期化
        getContactRegistListDto().setCurrentPageNumber(1);
        // 1ページあたりの表示件数設定
        getContactRegistListDto().setMaxPageCount(getMaxPageCount());

        // 全件数取得
        getContactRegistListDto().setCount(
                getGetRenrakuCountLogic().execute(
                        getContactRegistListDto().getTargetDate(),
                        getContactRegistListDto().getCategoryId(),
                        getBirdUserInfo().getMstUser()));

        // 検索
        List contactList = getRenrakuLogic.execute(getContactRegistListDto()
                .getTargetDate(), getContactRegistListDto().getCategoryId(),
                getBirdUserInfo().getMstUser(), getContactRegistListDto()
                        .getMaxPageCount(), getContactRegistListDto()
                        .getPageFirstRecordNumber());
        contactRegistListDto.setContactList(contactList);

        // インデックス初期化
        getContactRegistListDto().setSelectedIndex(0);

        // 検索結果有無判定
        if (contactList == null || contactList.size() <= 0) {
            throw new NoResultException();
        }

        // 自画面へ遷移
        return null;
    }

    public String startRegist() {

        // 編集対象DTO初期化
        try {
            PropertyUtils.copyProperties(contactRegistDto, new UIRenrakuInfo());
        } catch (Exception e) {
            throw new FtlSystemException("連絡登録");
        }

        // 初期値設定
        contactRegistDto.setRegDate(birdDateInfo.getSysDate());
        contactRegistDto.setPubUser(birdUserInfo.getUserID());
        contactRegistDto
                .setUserNameKj(birdUserInfo.getMstUser().getUser_name());
        contactRegistDto.setRCompanyCd(birdUserInfo.getMstUser()
                .getRCompanyCd());
        contactRegistDto.setPubOrg(birdUserInfo.getMstUser().getBumonCd());
        contactRegistDto
                .setPubOrgName(birdUserInfo.getMstUser().getBumonName());
        contactRegistDto.setSakujoFlg("0");
        contactRegistDto.setFirstUser(birdUserInfo.getUserID());
        contactRegistDto.setLastUser(birdUserInfo.getUserID());
        contactRegistDto.setFirstPgm(VIEWID);
        contactRegistDto.setLastPgm(VIEWID);
        // --- add 2006/03/23 発効日のデフォルト追加
        contactRegistDto.setPubDate(birdDateInfo.getSysDate());

        // 添付ファイル情報クリア
        contactRegistDto.getDeleteFileNameList().clear();
        contactRegistDto.setTempFileName1(null);
        contactRegistDto.setTempFileName2(null);
        contactRegistDto.setTempFileName3(null);
        contactRegistDto.setCheckAttachFile1(false);
        contactRegistDto.setCheckAttachFile2(false);
        contactRegistDto.setCheckAttachFile3(false);
        contactRegistDto.setInputTitle(null);
        contactRegistDto.setUploadedFile(null);

        // 公開対象情報設定
        getContactRegistPublicTargetDto().setInfoShu(INFO_SHU_CONTACT);
        getContactRegistPublicTargetDto().setRegDate(contactRegistDto.getRegDate());
        // publicTargetDto.setSeq(contactRegistDto.getSeq());
        getContactRegistPublicTargetDto().getListTrnControlCompany().clear();
        getContactRegistPublicTargetDto().getListTrnControlShozoku().clear();
        getContactRegistPublicTargetDto().getListTrnControlGyotai().clear();
        getContactRegistPublicTargetDto().getListTrnControlGyotaiKobetu().clear();
        getContactRegistPublicTargetDto().getListTrnControlGyotaiTenpo().clear();

        // 関連文書 共通DTOクリア
        contactRegistDto.setListKanrenBunsho(new ArrayList());

        // 登録モードを新規とする
        contactRegistDto.setEditMode(ContactRegistDto.EDIT_MODE_REGIST);

        // 編集画面へ遷移
        return VIEWID_EDIT;
    }

    public String startUpdate() {

        try {
            // 選択された連絡情報を編集対象としてDTOにコピーする
            PropertyUtils.copyProperties(contactRegistDto, contactRegistListDto
                    .getContactList().get(
                            contactRegistListDto.getSelectedIndex()));

            // 公開対象情報取得
            setContactRegistPublicTargetDto(getKoukaiTaishoLogic.execute(
                INFO_SHU_CONTACT, contactRegistDto.getRegDate(),
                contactRegistDto.getSeq()));

            // 関連文書情報取得
            List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU,
                    contactRegistDto.getRegDate(), contactRegistDto.getSeq());
            contactRegistDto.setListKanrenBunsho(listKanren);

        } catch (Exception e) {
            throw new FtlSystemException("連絡登録");
        }

        // 登録に必要なデータをセット
        contactRegistDto.setLastUser(birdUserInfo.getUserID());
        contactRegistDto.setLastPgm(VIEWID);

        // 添付ファイル情報クリア
        contactRegistDto.getDeleteFileNameList().clear();
        contactRegistDto.setTempFileName1(null);
        contactRegistDto.setTempFileName2(null);
        contactRegistDto.setTempFileName3(null);
        contactRegistDto.setCheckAttachFile1(false);
        contactRegistDto.setCheckAttachFile2(false);
        contactRegistDto.setCheckAttachFile3(false);
        contactRegistDto.setInputTitle(null);
        contactRegistDto.setUploadedFile(null);

        // 登録モードを更新とする
        contactRegistDto.setEditMode(ContactRegistDto.EDIT_MODE_UPDATE);

        // 編集画面へ遷移
        return VIEWID_EDIT;
    }

    public String startDelete() {

        // 選択された連絡情報を編集対象としてDTOにコピーする
        try {            
            PropertyUtils.copyProperties(contactRegistDto, contactRegistListDto
                    .getContactList().get(
                            contactRegistListDto.getSelectedIndex()));
            
            // 公開対象情報取得
            setContactRegistPublicTargetDto(getKoukaiTaishoLogic.execute(
                INFO_SHU_CONTACT, contactRegistDto.getRegDate(),
                contactRegistDto.getSeq()));

            // 関連文書情報取得
            List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU,
                    contactRegistDto.getRegDate(), contactRegistDto.getSeq());
            contactRegistDto.setListKanrenBunsho(listKanren);
// add end
        } catch (Exception e) {
            throw new FtlSystemException("連絡登録");
        }

        // 登録モードを削除とする
        contactRegistDto.setEditMode(ContactRegistDto.EDIT_MODE_DELETE);

        // 確認画面へ遷移
        return VIEWID_CONFIRM;
    }

    public String changePage() {

        // 選択されたページ番号を設定
        getContactRegistListDto().setCurrentPageNumber(getSelectPageNumber());

        // 検索
        List contactList = getRenrakuLogic.execute(getContactRegistListDto()
                .getTargetDate(), getContactRegistListDto().getCategoryId(),
                getBirdUserInfo().getMstUser(), getContactRegistListDto()
                        .getMaxPageCount(), getContactRegistListDto()
                        .getPageFirstRecordNumber());
        contactRegistListDto.setContactList(contactList);

        // インデックス初期化
        getContactRegistListDto().setSelectedIndex(0);

        // 検索結果有無判定
        if (contactList == null || contactList.size() <= 0) {
            throw new NoResultException();
        }

        // 自画面へ遷移
        return null;
    }
    /**
     * 対象年月リスト作成
     * 
     * @param baseDate
     *            基準日付
     * @return 対象年月リスト
     */
    private List createTargetDateList(String baseDate) {
        return createTargetDateList(baseDate, getTargetDateMonths());
    }

    /**
     * 対象年月リスト作成
     * 
     * @param baseDate
     *            基準日付
     * @param range
     *            月数
     * @return 対象年月リスト
     */
    private List createTargetDateList(String baseDate, int months) {

        // YYYYMMDD形式の文字列からカレンダー作成
        Calendar calendar = Calendar.getInstance();
        try {
            calendar
                    .setTime((new SimpleDateFormat("yyyyMMdd")).parse(baseDate));
        } catch (ParseException e) {
            throw new FtlSystemException("連絡登録");
        }

        // YYYYMM形式で指定範囲の年月リストを作成
        List targetDateList = new ArrayList();
        DateFormat formatter = new SimpleDateFormat("yyyyMM");
        DateFormatter commonFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        for (int i = 0; i < months; i++) {
            targetDateList.add(new SelectItem(commonFormatter.format(formatter
                    .format(calendar.getTime()), false), commonFormatter
                    .format(formatter.format(calendar.getTime()), true)));
            calendar.add(Calendar.MONTH, -1);
        }

        return targetDateList;
    }

    /**
     * 関連文書検索ロジックを取得します。
     * 
     * @return 関連文書検索ロジック
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }

    /**
     * 関連文書検索ロジックを設定します。
     * 
     * @param 関連文書検索ロジック
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }

	/**
	 * @return contactRegistPublicTargetDto を戻します。
	 */
	public PublicTargetDto getContactRegistPublicTargetDto() {
		return contactRegistPublicTargetDto;
	}

	/**
	 * @param contactRegistPublicTargetDto を クラス変数contactRegistPublicTargetDtoへ設定します。
	 */
	public void setContactRegistPublicTargetDto(
			PublicTargetDto contactRegistPublicTargetDto) {
		this.contactRegistPublicTargetDto = contactRegistPublicTargetDto;
	}
}
