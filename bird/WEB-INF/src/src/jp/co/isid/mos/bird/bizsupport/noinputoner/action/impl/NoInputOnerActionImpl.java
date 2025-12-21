/*
 * 作成日: 2006/03/24
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.bizsupport.noinputoner.action.NoInputOnerAction;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dto.NoInputOnerDto;
import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.UITenpoInfo;
import jp.co.isid.mos.bird.bizsupport.noinputoner.logic.GetPLDataStateLogic;
import jp.co.isid.mos.bird.bizsupport.noinputoner.logic.GetSibuCategoryLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * P/L未入力オーナー一覧画面アクション
 * @author xnkusama
 */
public class NoInputOnerActionImpl implements NoInputOnerAction, CommonAction  {

    /* アクションID */
    public static final String initialize_ACTION_ID = "BBS004A01";
    public static final String linkOner_ACTION_ID   = "BBS004A02";
    public static final String linkMise_ACTION_ID   = "BBS004A03";
    public static final String search_ACTION_ID     = "BBS004A04";
    public static final String changePage_ACTION_ID = "BBS004A05";
    public static final String jumpMise_ACTION_ID   = "BBS004A06";
    public static final String jumpOner_ACTION_ID   = "BBS004A07";
    /* ビューID */
    //private static final String VIEWID_NOINPUTONER = "BBS004V01";
    private static final String VIEWID_ONER = "BSI002V02";
    private static final String VIEWID_MISE = "BSI001V02";
    /* １ページ表示件数 */
    private static final int PAGE_MAX_COUNT = 100;
    /* 条件画面 対象年月表示月数 */
    //---2006/04/07 13ヶ月に変更
    //private static final int NENGETU_DISPLAY_MONTH = 26;
    private static final int NENGETU_DISPLAY_MONTH = 13;
    
    /* LOGIC */
    private GetSibuCategoryLogic getSibuCategoryLogic;
    private GetPLDataStateLogic getPLDataStateLogic;
    /* DTO */
    private NoInputOnerDto noInputOnerDto;
    /* ページ遷移 */
    private int selectPageNumber;
    
    /**
     * 検索
     * @return
     */
    public String search() {
        // 検索結果をクリア
        getNoInputOnerDto().setListNoInputOner(null);
        
        // 検索条件保持
        getNoInputOnerDto().setCondInput(getNoInputOnerDto().getCondInputInp());
        getNoInputOnerDto().setCondNengetu(getNoInputOnerDto().getCondNengetuInp());
        getNoInputOnerDto().setCondSibuCd(getNoInputOnerDto().getCondSibuCdInp());
        // 検索
        List listPlData = getGetPLDataStateLogic()
                                .execute(getNoInputOnerDto().getCondCompanyCd(), 
                                         getNoInputOnerDto().getCondNengetu(),
                                         getNoInputOnerDto().getCondSibuCd(),
                                         getNoInputOnerDto().getCondInput(),
                                         getNoInputOnerDto().isCloseMiseFlg());
        
        // 全件データ保持
        getNoInputOnerDto().setListAllData(listPlData);
        // 検索結果件数保持
        getNoInputOnerDto().setCount(listPlData.size());
        // 現在ページ設定
        getNoInputOnerDto().setCurrentPageNumber(1);
        // 検索結果セット
        getNoInputOnerDto().setListNoInputOner(getListViewPlData());
        
        return null;
    }

    /**
     * 初期アクション
     * @return
     */
    public String initialize() {
        // メニューから遷移された場合
        if (getPullDownMenuDto() != null && getPullDownMenuDto().isClearFlg()) {
            // 検索条件項目の初期化
            getNoInputOnerDto().setCondNengetuInp(getCondNengetuDefault());
            getNoInputOnerDto().setCondNengetu(getNoInputOnerDto().getCondNengetuInp());
            getNoInputOnerDto().setCondInputInp("0");
            getNoInputOnerDto().setCondInput(getNoInputOnerDto().getCondInputInp());
            getNoInputOnerDto().setCondSibuCdInp("");
            getNoInputOnerDto().setCondSibuCd(getNoInputOnerDto().getCondSibuCdInp());
            // 検索結果項目の初期化
            getNoInputOnerDto().setListNoInputOner(null);
            getPullDownMenuDto().setClearFlg(false);
            
            // 初期値で検索
//            search();
        }
        
        // 支部プルダウン作成
        List listSibu = getGetSibuCategoryLogic().execute(getNoInputOnerDto().getCondCompanyCd());
        getNoInputOnerDto().setListCondSibu(listSibu);
        // 対象年月プルダウン作成
        List listTargetYM = makeCondListTargetYM();
        getNoInputOnerDto().setCondListTargetYM(listTargetYM);
        return null;
    }
    
    /**
     * オーナー照会画面へリンク
     * @return
     */
    public String linkOner() {
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        int linkIndex = 0;
        Object onerLinkIndex = getRequest().getParameter("onerLinkIndex");
        if (onerLinkIndex != null && !onerLinkIndex.toString().trim().equals("")) {
            linkIndex = (new Integer(onerLinkIndex.toString())).intValue();
        }
        UITenpoInfo entity = (UITenpoInfo) getNoInputOnerDto().getListNoInputOner().get(linkIndex);
        commonCodeDto.setOnerCd(entity.getOnerCd());
        commonCodeDto.setCompanyCd(entity.getCompanyCd());
        
        return VIEWID_ONER;
    }

    /**
     * 個店ポータルへリンク
     * @return
     */
    public String linkMise() {
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        int linkIndex = 0;
        Object miseLinkIndex = getRequest().getParameter("miseLinkIndex");
        if (miseLinkIndex != null && !miseLinkIndex.toString().trim().equals("")) {
            linkIndex = (new Integer(miseLinkIndex.toString())).intValue();
        }
        UITenpoInfo entity = (UITenpoInfo) getNoInputOnerDto().getListNoInputOner().get(linkIndex);
        commonCodeDto.setMiseCd(entity.getMiseCd());
        commonCodeDto.setCompanyCd(entity.getCompanyCd());
        
        return VIEWID_MISE;
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {

        NoInputOnerDto dto = getNoInputOnerDto();

        // ページ番号
        dto.setCurrentPageNumber(getSelectPageNumber());
        
        getNoInputOnerDto().setListNoInputOner(getListViewPlData());
        
        return null;
    }
    
    /**
     * 店コード指定ページ移動
     */
    public String jumpMise() {
        if (isNull(getNoInputOnerDto().getCondJumpMiseCd())) {
            throw new NoInputException("店コード", "miseT", null);
        }
        List listAll = getNoInputOnerDto().getListAllData();
        int index = -1;
        // 対象の店コードを探す
        for (int i = 0; i < listAll.size(); i++) {
            UITenpoInfo entity = (UITenpoInfo) listAll.get(i);
            
            if (getNoInputOnerDto().getCondJumpMiseCd().equals(entity.getMiseCd())) {
                index = i;
                break;
            }
        }
        // 見つからない場合は例外
        if (index < 0) {
            throw new NotExistException("対象の店コード", "miseT", null);
        }
        // 表示用のリスト作成
        getNoInputOnerDto().setListNoInputOner(getListViewPlDataForJump(index));
        // ページ番号の設定
        getNoInputOnerDto().setCurrentPageNumber(index / PAGE_MAX_COUNT + 1);
        return null;
    }
    
    /**
     * オーナーコード指定ページ移動
     */
    public String jumpOner() {
        if (isNull(getNoInputOnerDto().getCondJumpOnerCd())) {
            throw new NoInputException("オーナーコード", "onerT", null);
        }
        List listAll = getNoInputOnerDto().getListAllData();
        int index = -1;
        // 対象のオーナーコードを探す
        for (int i = 0; i < listAll.size(); i++) {
            UITenpoInfo entity = (UITenpoInfo) listAll.get(i);
            
            if (getNoInputOnerDto().getCondJumpOnerCd().equals(entity.getOnerCd())) {
                index = i;
                break;
            }
        }
        // 見つからない場合は例外
        if (index < 0) {
            throw new NotExistException("対象のオーナーコード", "onerT", null);
        }
        // 表示用のリスト作成
        getNoInputOnerDto().setListNoInputOner(getListViewPlDataForJump(index));
        // ページ番号の設定
        getNoInputOnerDto().setCurrentPageNumber(index / PAGE_MAX_COUNT + 1);
        return null;
    }
    
    /**
     * 検索結果を全件保持したリストから表示対象のリストを抽出
     * @return
     */
    private List getListViewPlData() {
        List listData = getNoInputOnerDto().getListAllData();
        int pageNoFrom = PAGE_MAX_COUNT * (getNoInputOnerDto().getCurrentPageNumber() - 1);
        int pageNoTo   = PAGE_MAX_COUNT * getNoInputOnerDto().getCurrentPageNumber();
        // pageNoToがデータ件数より大きい場合の対処
        if (pageNoTo > listData.size()) {
            pageNoTo = listData.size();
        }
        return listData.subList(pageNoFrom, pageNoTo);
    }
    
    /**
     * 店、オーナーコード指定時の表示対象リスト作成処理
     * @return
     */
    private List getListViewPlDataForJump(int index) {
        List listData = getNoInputOnerDto().getListAllData();
        int pageNoFrom = index;
        int pageNoTo   = index + PAGE_MAX_COUNT;
        // pageNoToがデータ件数より大きい場合の対処
        if (pageNoTo > listData.size()) {
            pageNoTo = listData.size();
        }
        return listData.subList(pageNoFrom, pageNoTo);
    }
    
    private List makeCondListTargetYM() {
        // 現在日付取得
        DateFormatter formatter = new DateFormatter();
        String sysDate = getBirdDateInfo().getSysDate();
        String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        
        // 現在月から過去？ヶ月分の年月リスト作成(2006/04/03 何カ月分必要か未定)
        List listMonth = new ArrayList();
        for (int i = 1; i < NENGETU_DISPLAY_MONTH + 1; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysMonth, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理");
            }
                                     
            SelectItem item = new SelectItem(
                                        month, 
                                        formatter.format(month, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM)); 
            listMonth.add(item);
        }
        return listMonth;
    }

    private CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }
    
//    private List searchNoInputOnerData() {
//        List listData;
//        listData = getGetPLDataStateLogic().execute(getBirdUserInfo().getMstUser().getRCompanyCd(),
//                                                    getNoInputOnerDto().getCondNengetu(),
//													getNoInputOnerDto().getCondSibuCd(),
//													getNoInputOnerDto().getCondInput(),
//                                                    getNoInputOnerDto().isCloseMiseFlg());
//        return listData;
//    }
    /**
     * 検索条件：対象年月のデフォルト値取得処理
     * @return デフォルト値（システム日付の前月)
     * @throws ApplicationException
     */
    private String getCondNengetuDefault() throws ApplicationException {
        String sysDate = getBirdDateInfo().getSysDate().substring(0, 6);
        String defaultYM = "";
        try {
        	defaultYM = DateManager.getPrevMonth(sysDate, 1);
        }
        catch (Exception ex) {
            throw new FtlSystemException("対象年月デフォルト値取得");
        }
        return defaultYM;
    }
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    
	public NoInputOnerDto getNoInputOnerDto() {
		return noInputOnerDto;
	}
	public void setNoInputOnerDto(NoInputOnerDto noInputOnerDto) {
		this.noInputOnerDto = noInputOnerDto;
	}
	public PullDownMenuDto getPullDownMenuDto() {
		return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
	}
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
	public GetSibuCategoryLogic getGetSibuCategoryLogic() {
		return getSibuCategoryLogic;
	}
	public void setGetSibuCategoryLogic(
			GetSibuCategoryLogic getSibuCategoryLogic) {
		this.getSibuCategoryLogic = getSibuCategoryLogic;
	}
	public GetPLDataStateLogic getGetPLDataStateLogic() {
		return getPLDataStateLogic;
	}
	public void setGetPLDataStateLogic(GetPLDataStateLogic getPLDataStateLogic) {
		this.getPLDataStateLogic = getPLDataStateLogic;
	}
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) getS2Container().getComponent("request");
    }
	public int getSelectPageNumber() {
		return selectPageNumber;
	}
	public void setSelectPageNumber(int selectPageNumber) {
		this.selectPageNumber = selectPageNumber;
	}
}
