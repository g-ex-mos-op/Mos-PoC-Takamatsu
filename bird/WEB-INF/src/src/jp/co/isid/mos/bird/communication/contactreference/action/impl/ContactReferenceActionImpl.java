/*
 * 作成日: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.action.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.communication.contactreference.action.ContactReferenceAction;
import jp.co.isid.mos.bird.communication.contactreference.dto.ContactReferenceDto;
import jp.co.isid.mos.bird.communication.contactreference.entity.UIViewRenraku;
import jp.co.isid.mos.bird.communication.contactreference.logic.GetGyoutaiLogic;
import jp.co.isid.mos.bird.communication.contactreference.logic.UIViewRenrakuLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 連絡照会アクション
 * @author xyuchida
 */
public class ContactReferenceActionImpl implements ContactReferenceAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BCM001A01";
    public static final String search_ACTION_ID = "BCM001A02";
    public static final String showDetail_ACTION_ID = "BCM001A03";
    public static final String callForm_ACTION_ID = "BCM001A04";
    public static final String changePage_ACTION_ID = "BCM001A05";
    public static final String changeTab_ACTION_ID = "BCM001A06";

    private static final String INFO_SHU_CONTACT = "01";

    private ContactReferenceDto contactReferenceDto;
//    private BirdUserInfo birdUserInfo;
//    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;

    private GetCategoryLogic getCategoryLogic;
    private GetGyoutaiLogic getGyoutaiLogic;
    private UIViewRenrakuLogic uiViewRenrakuLogic;
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;

    private UIViewRenraku uiViewRenraku;
//    private PublicTargetDto publicTargetDto;
//    private PublicTargetSearchDto publicTargetSearchDto;

    private String selectCateId;
    private int selectContactIndex;
    private int pubDateMonths;
    private int selectPageNumber;
    private int maxPageCount;

    public ContactReferenceDto getContactReferenceDto() {
        return contactReferenceDto;
    }
    public void setContactReferenceDto(ContactReferenceDto contactReferenceDto) {
        this.contactReferenceDto = contactReferenceDto;
    }
    public BirdUserInfo getBirdUserInfo() {
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	return (BirdUserInfo) container.getComponent(BirdUserInfo.class);
    }
//    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
//        this.birdUserInfo = birdUserInfo;
//    }
    public BirdDateInfo getBirdDateInfo() {
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	return (BirdDateInfo) container.getComponent(BirdDateInfo.class);
    }
//    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
//        this.birdDateInfo = birdDateInfo;
//    }
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public GetCategoryLogic getGetCategoryLogic() {
        return getCategoryLogic;
    }
    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
        this.getCategoryLogic = getCategoryLogic;
    }
    public GetGyoutaiLogic getGetGyoutaiLogic() {
        return getGyoutaiLogic;
    }
    public void setGetGyoutaiLogic(GetGyoutaiLogic getGyoutaiLogic) {
        this.getGyoutaiLogic = getGyoutaiLogic;
    }
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }
    public void setGetKoukaiTaishoLogic(
            GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }

    public UIViewRenrakuLogic getUiViewRenrakuLogic() {
        return uiViewRenrakuLogic;
    }
    public void setUiViewRenrakuLogic(UIViewRenrakuLogic uiViewRenrakuLogic) {
        this.uiViewRenrakuLogic = uiViewRenrakuLogic;
    }
//    public PublicTargetDto getPublicTargetDto() {
//        return publicTargetDto;
//    }
//    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
//        this.publicTargetDto = publicTargetDto;
//    }
//    public PublicTargetSearchDto getPublicTargetSearchDto() {
//        return publicTargetSearchDto;
//    }
//    public void setPublicTargetSearchDto(
//            PublicTargetSearchDto publicTargetSearchDto) {
//        this.publicTargetSearchDto = publicTargetSearchDto;
//    }

    public UIViewRenraku getUiViewRenraku() {
        return uiViewRenraku;
    }
    public void setUiViewRenraku(UIViewRenraku uiViewRenraku) {
        this.uiViewRenraku = uiViewRenraku;
    }

    public String getSelectCateId() {
        return selectCateId;
    }
    public void setSelectCateId(String selectCateId) {
        this.selectCateId = selectCateId;
    }
    public int getSelectContactIndex() {
        return selectContactIndex;
    }
    public void setSelectContactIndex(int selectContactIndex) {
        this.selectContactIndex = selectContactIndex;
    }
    public int getPubDateMonths() {
        return pubDateMonths;
    }
    public void setPubDateMonths(int pubDateMonths) {
        this.pubDateMonths = pubDateMonths;
    }
    public int getSelectPageNumber() {
        return selectPageNumber;
    }
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }
    public int getMaxPageCount() {
        return maxPageCount;
    }
    public void setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
    }

    public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        ContactReferenceDto contactReferenceDto = (ContactReferenceDto) container.getComponent("contactReferenceDto");
        GetGyoutaiLogic getGyoutaiLogic = (GetGyoutaiLogic) container.getComponent("getgyoutaiLogic");
        UIViewRenraku uiViewRenraku = (UIViewRenraku) container.getComponent("uiViewRenraku");

        // 初期処理判定
        if (pullDownMenuDto.isClearFlg()) {

            // DTO初期化
            contactReferenceDto.setInputPubDate(null);
            contactReferenceDto.setInputGyotaiKbn(null);
            contactReferenceDto.setInputTitle(null);
            contactReferenceDto.setCateId(null);
            contactReferenceDto.getCategoryList().clear();
            contactReferenceDto.getContactList().clear();
            uiViewRenraku.setEnabled(false);

            // 公開開始日リスト生成
            contactReferenceDto.setPubDateList(
                    createTargetDateList(birdDateInfo.getSysDate(), getPubDateMonths()));
    
            // 業態リスト取得
            contactReferenceDto.setGyotaiList(
                    getGyoutaiLogic.execute(birdUserInfo.getUserID()));
            
            //今月の連絡取得

            getContactReferenceDto().setInputPubDate(birdDateInfo.getSysDate().substring(0, 6));
            getContactReferenceDto().setInputGyotaiKbn(null);
            getContactReferenceDto().setInputTitle(null);
            try {
                search();
                //詳細表示
                setSelectContactIndex(0);
                setDetailEntity();    
            }catch (NoResultException e) {
            //結果なしのメッセージがでないようにハンドリング
            }    
            getContactReferenceDto().setKongetuRenrakuCount(getContactReferenceDto().getCount());
            
            pullDownMenuDto.setClearFlg(false);
        }

        // 自画面へ遷移
        return null;
    }

    public String search() {

        // 連絡詳細クリア
        getUiViewRenraku().setEnabled(false);
        getContactReferenceDto().setKongetuRenrakuCount(0);

        // ページ番号初期化
        getContactReferenceDto().setCurrentPageNumber(1);
        // 1ページあたりの表示件数設定
        getContactReferenceDto().setMaxPageCount(getMaxPageCount());

        // 入力用一時プロパティから検索条件設定
        getContactReferenceDto().setPubDate(getContactReferenceDto().getInputPubDate());
        getContactReferenceDto().setGyotaiKbn(getContactReferenceDto().getInputGyotaiKbn());
        getContactReferenceDto().setTitle(getContactReferenceDto().getInputTitle());

        boolean initFlag = false;
        int initCount = 0;
        String initCateId = null;

        // カテゴリリスト取得
        List categoryList = getGetCategoryLogic().getCategory(INFO_SHU_CONTACT);
        
        
        createDefaultCateGory(categoryList);
        
        for (Iterator it = categoryList.iterator(); it.hasNext();) {

            MstCategoryInfo mstCategoryInfo = (MstCategoryInfo) it.next();
            // カテゴリID設定
            getContactReferenceDto().setCateId(mstCategoryInfo.getCateId());

            List contactList = getUiViewRenrakuLogic().execute(
                    getBirdUserInfo(), getContactReferenceDto(),getBirdDateInfo().getSysDate());

            if (contactList == null || contactList.size() <= 0) {
                // データ無しカテゴリはリストから除外
                it.remove();
            } else if (!initFlag) {
                // 初期カテゴリ決定
                initCateId = mstCategoryInfo.getCateId();
                getContactReferenceDto().setContactList(contactList);
                initFlag = true;
                initCount = getContactReferenceDto().getCount();
            }
        }
        // 初期カテゴリ情報再設定
        getContactReferenceDto().setCount(initCount);
        getContactReferenceDto().setCateId(initCateId);
        // カテゴリリスト設定
        getContactReferenceDto().setCategoryList(categoryList);

        // 検索結果無し
        if (categoryList == null || categoryList.size() <= 0) {
            throw new NoResultException();
        }
        //詳細表示
        setSelectContactIndex(0);
        setDetailEntity();    

        // 件数をセット
        getContactReferenceDto().setKongetuRenrakuCount(getContactReferenceDto().getCount());
        
        // 自画面へ遷移
        return null;
    }
    /**
     * 詳細エリアへ表示されるデータ(エンティティ)をDTOへ設定します。
     */
    public String showDetail() {
    	setDetailEntity();
        // 自画面へ遷移
        return null;
    }

    public String changePage() {

        // 連絡詳細クリア
        getUiViewRenraku().setEnabled(false);
        getUiViewRenraku().setEnabled(false);

        // 選択されたページ番号を設定
        getContactReferenceDto().setCurrentPageNumber(getSelectPageNumber());

        // 検索
//        List contactList = getUiViewRenrakuLogic().execute(
//                getBirdUserInfo(), getContactReferenceDto());
        List contactList = getUiViewRenrakuLogic().execute(
                getBirdUserInfo(), getContactReferenceDto(),getBirdDateInfo().getSysDate());
        
        getContactReferenceDto().setContactList(contactList);
        if (contactList == null || contactList.size() <= 0) {
            throw new NoResultException();
        }
        //詳細表示
        setSelectContactIndex(0);
        setDetailEntity();    

        // 自画面へ遷移
        return null;
    }

    public String changeTab() {

        // 連絡詳細クリア
        getUiViewRenraku().setEnabled(false);
        getContactReferenceDto().setKongetuRenrakuCount(0);

        // ページ番号初期化
        getContactReferenceDto().setCurrentPageNumber(1);

        // 選択されたカテゴリIDを設定
        getContactReferenceDto().setCateId(getSelectCateId());

        // 検索
        List contactList = getUiViewRenrakuLogic().execute(
                getBirdUserInfo(), getContactReferenceDto(),getBirdDateInfo().getSysDate());
        
        getContactReferenceDto().setContactList(contactList);
        if (contactList == null || contactList.size() <= 0) {
            throw new NoResultException();
        }
        //詳細表示
        setSelectContactIndex(0);
        showDetail();    

        // 件数をセット
        getContactReferenceDto().setKongetuRenrakuCount(getContactReferenceDto().getCount());
        
        // 自画面へ遷移
        return null;
    }
    /**
     * 詳細エリアへ表示されるデータ(エンティティ)をDTOへ設定します。
     *
     */
    private void setDetailEntity() {
    	List listSearchData = getContactReferenceDto().getContactList();
    	if (listSearchData.size() > 0) {
	        // 選択連絡情報詳細設定
	        UIViewRenraku uiViewRenraku = (UIViewRenraku) listSearchData.get(getSelectContactIndex());
	        setUiViewRenraku(uiViewRenraku);
	        getUiViewRenraku().setEnabled(true);
    	}
    }
    /**
     * 対象年月リスト作成
     * 
     * @param baseDate 基準日付
     * @param range 月数
     * @return 対象年月リスト
     */
    private List createTargetDateList(String baseDate, int months) {

        // YYYYMMDD形式の文字列からカレンダー作成
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime((new SimpleDateFormat("yyyyMMdd")).parse(baseDate));
        } catch (ParseException e) {
            throw new FtlSystemException("連絡登録");
        }

        // YYYYMM形式で指定範囲の年月リストを作成
        List targetDateList = new ArrayList();
        DateFormat formatter = new SimpleDateFormat("yyyyMM");
        DateFormatter commonFormatter
                = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        for (int i = 0; i < months; i++) {
            targetDateList.add(new SelectItem(
                    commonFormatter.format(formatter.format(calendar.getTime()), false),
                    commonFormatter.format(formatter.format(calendar.getTime()), true)));
            calendar.add(Calendar.MONTH, -1);
        }

        return targetDateList;
    }
    
    /**
     * 「全て」のカテゴリーの作成
     * @param categoryList
     */
    private void createDefaultCateGory(List categoryList){
        MstCategoryInfo all = new MstCategoryInfo();
        all.setCateId(null);
        all.setCateName("全て");
        categoryList.add(0, all);
    }

}
