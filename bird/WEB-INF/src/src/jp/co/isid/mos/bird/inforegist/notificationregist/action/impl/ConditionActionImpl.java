
/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.action.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.inforegist.notificationregist.action.ConditionAction;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.ConditionDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.RegistFormDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.GetTotalCountLogic;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.GetTutatuLogic;

/**
 * 通達登録 条件画面アクション
 * @author m.onodera
 * 
 * 更新日：2011/04/15 ASAPC 「通達」から「お知らせ」へ画面名称変更対応
 */
public class ConditionActionImpl implements ConditionAction {
    /* 情報種別:通達 */
    protected static final String INFO_SHU = "02";
    public static final String SCREEN_ID = "BIF002";
    /* アクションID */
    public static String initialize_ACTION_ID   = "BIF002A01";
    public static String search_ACTION_ID       = "BIF002A02";
    public static String changePage_ACTION_ID   = "BIF002A03";
    public static String goInsert_ACTION_ID     = "BIF002A04";
    public static String goRegist_ACTION_ID     = "BIF002A05";
    public static String goDelete_ACTION_ID     = "BIF002A06";
    /* ビューID */
    private static final String VIEWID_EDIT     = "BIF002V02";
    private static final String VIEWID_CONFIRM  = "BIF002V03";
    
    /* 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 36;
    //FrameworkLOGIC【汎用ロール情報取得】
    private GetGamenRoleLogic getGamenRoleLogic;
    /* DTO */
    private ConditionDto notificationRegistConditionDto;
    
// add start xkhata
    private RegistFormDto notificationRegistFormDto;
//  add end
    // 公開対象DTO
    private PublicTargetDto publicTargetDto;
    //プルダウンメニューDTO
    private PullDownMenuDto pullDownMenuDto;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;
    /* 選択ページ番号 */
    private int selectPageNumber;
    
    /* ロジック */
    // LOGIC【文書の件数取得ロジック】
    private GetTotalCountLogic getTotalCountLogic;
    // LOGIC【文書一覧情報の取得】
    private GetTutatuLogic getTutatuLogic;
    // BIRD共通LOGIC【公開対象の取得】
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;
    /* BIRD共通LOGIC【カテゴリ一覧の取得】 */
    private GetCategoryLogic getCategoryLogic;
    // BIRD共通LOGIC【関連文書検索】
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;

    
	/**
	 * カテゴリプルダウン検索用ロジック設定処理
	 * @return getCategoryLogic
	 */
	public GetCategoryLogic getGetCategoryLogic() {
	    return getCategoryLogic;
	}
	/**
	 *カテゴリプルダウン検索用ロジック設定処理
	 * @param getCategoryLogic
	 */
	public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
	    this.getCategoryLogic = getCategoryLogic;
	}
    /**
     * @return getKoukaiTaishoLogic を戻します。
     */
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }

    /**
     * @param getKoukaiTaishoLogic getKoukaiTaishoLogic を設定。
     */
    public void setGetKoukaiTaishoLogic(
            GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }

    /**
     * 通達の件数取得ロジック設定処理
     * @param countBunshoLogic countBunshoLogic を設定。
     */
    public void setGetTotalCountLogic(GetTotalCountLogic getTotalCountLogic) {
        this.getTotalCountLogic = getTotalCountLogic;
    }

    /**
     * 通達の件数取得ロジック取得処理
     * @return countBunshoLogic を戻します。
     */
    public GetTotalCountLogic getGetTotalCountLogic() {
        return getTotalCountLogic;
    }
    /**
     * 通達一覧情報取得ロジック設定処理
     * @param getTutatuLogic getTutatuLogic を設定。
     */
    public void setGetTutatuLogic(GetTutatuLogic getTutatuLogic) {
        this.getTutatuLogic = getTutatuLogic;
    }
    /**
     * 通達一覧情報取得ロジック取得処理
     * @return getTutatuLogic を戻します。
     */
    public GetTutatuLogic getGetTutatuLogic() {
        return getTutatuLogic;
    }
    /**
     * @return getKanrenBunshoInfoLogic を戻します。
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }
    /**
     * @param getKanrenBunshoInfoLogic getKanrenBunshoInfoLogic を設定。
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
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
    /**
     * 条件画面DTO設定処理
     * @param notificationRegistConditionDto notificationRegistConditionDto を設定。
     */
    public void setNotificationRegistConditionDto(ConditionDto conditionDto) {
        this.notificationRegistConditionDto = conditionDto;
    }
    /**
     * 条件画面DTO取得処理
     * @return notificationRegistConditionDto を戻します。
     */
    public ConditionDto getNotificationRegistConditionDto() {
        return notificationRegistConditionDto;
    }
// add start xkhata 
    public void setNotificationRegistFormDto(RegistFormDto registFormDto) {
        this.notificationRegistFormDto = registFormDto;
    }
    public RegistFormDto getNotificationRegistFormDto() {
        return this.notificationRegistFormDto;
    }
//  add end
    /**
     * 初期処理
     * @return
     */
    public String initialize() throws ApplicationException {
        DateFormatter formatter = new DateFormatter();
        
        // メニューより遷移した場合は、セッションで保持している値をクリア
        if (getPullDownMenuDto().isClearFlg()) {
            getPullDownMenuDto().setClearFlg(false);
            
	        // 現在日付取得
	        String sysDate = getBirdDateInfo().getSysDate();
	        String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        
            clearSessionInfoFromMenu();

	        // 共通ロジック【カテゴリの取得】を実行する.
	        List listCategory = getGetCategoryLogic().getCategory(INFO_SHU);
	        // カテゴリ情報をDTOへセット
	        getNotificationRegistConditionDto().setListCategory(listCategory);
	        
	        // 現在月から過去36ヶ月分の年月リスト作成
	        List listMonth = new ArrayList();
	        for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
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
	        getNotificationRegistConditionDto().setListNengetu(listMonth);
        }
        getNotificationRegistFormDto().setListKanrenSaki(null);
        //汎用ロール情報取得設定を行います。
        getNotificationRegistFormDto().setTodayInsertDLBt(existGamenRole());
        return null;
    }

    /**
     * 通達一覧検索処理
     * @return
     */
    public String search() {
        
        ConditionDto dto = getNotificationRegistConditionDto();
        dto.setCurrentPageNumber(1);

        // 検索結果をクリア
        dto.clearCondInsOrSearch();
        
        // ロジック【通達情報の取得】
        List listTutatu = getGetTutatuLogic().getTutatu(dto.getNengetu(),
                                                        dto.getCateId(), 
                                                        dto.getKanriNo(),
                                                        dto.getCurrentPageNumber(),
                                                        getMstUser());

        //指定条件の通達一覧を設定
        dto.setListTutatu(listTutatu);
        
        /* 指定条件での通達件数取得 */
        int countTutatu = getGetTotalCountLogic().countTutatu(dto.getNengetu(),
                                                            dto.getCateId(),
                                                            dto.getKanriNo(),
                                                            getMstUser());
        
        // 指定条件の件数を設定
        dto.setCount(countTutatu);
        
        
        // 選択カテゴリIDを保持
        dto.setSelectedCateId(dto.getCateId());
        // 選択年月を保持
        dto.setSelectedNengetu(dto.getNengetu());
        // 選択管理番号を保持
        dto.setSelectedKanriNo(dto.getKanriNo());
        // 選択中のリストインデックス
        if (dto.getSelectedListIndex() < 0) {
            dto.setSelectedListIndex(0);
        }
        
        return null;
    }
    
    /**
     * 新規登録アクション
     * @return
     */
    public String goInsert() {
        //関連文書情報初期化
        getNotificationRegistConditionDto().setListKanrenBunsho(new ArrayList());
        
        getNotificationRegistConditionDto().setRegMode(ConditionDto.REG_MODE_INSERT);
        getNotificationRegistConditionDto().setFlgCondToReg(true);
// add start xkhata 
        getNotificationRegistFormDto().setAttachName(null);
        getNotificationRegistFormDto().setUploadFileName(null);
// add end
        
        getPublicTargetDto().setInfoShu(INFO_SHU);
        getPublicTargetDto().setRegDate(getBirdDateInfo().getSysDate());
        getPublicTargetDto().setListTrnControlCompany(new ArrayList());
        getPublicTargetDto().setListTrnControlGyotai(new ArrayList());
        getPublicTargetDto().setListTrnControlGyotaiKobetu(new ArrayList());
        getPublicTargetDto().setListTrnControlGyotaiTenpo(new ArrayList());
        getPublicTargetDto().setListTrnControlShozoku(new ArrayList());
        
        // セッション保持データクリア
        
        
        return VIEWID_EDIT;
    }
    /**
     * 削除ボタンアクション
     * @return
     */
    public String goDelete() {
        getNotificationRegistConditionDto().setRegMode(ConditionDto.REG_MODE_DELETE);
        getNotificationRegistConditionDto().setFlgCondToReg(true);
        //xyamauchi add
        getNotificationRegistFormDto().setAttachName(null);
        getNotificationRegistFormDto().setUploadFileName(null);
        getNotificationRegistFormDto().clearFileName();
        //xyamauchi end
        try { 
            String regDate = getNotificationRegistConditionDto().getSelectedEntity().getRegDate();
            String seq = getNotificationRegistConditionDto().getSelectedEntity().getSeq();
        	settingKokaiTaisho(regDate, seq);
        }
        catch (Exception ex) {
            throw new FtlSystemException(InfoShu.OSIRASE_NAME + "登録");
        }
        return VIEWID_CONFIRM;
    }

    /**
     * 変更ボタンアクション  
     *    編集画面へ遷移
     * @return
     */
    public String goRegist() {
        getNotificationRegistConditionDto().setRegMode(ConditionDto.REG_MODE_UPDATE);
        getNotificationRegistConditionDto().setFlgCondToReg(true);

// add start xkhata 
        getNotificationRegistFormDto().setAttachName(null);
        getNotificationRegistFormDto().setUploadFileName(null);
// add end
        // 公開対象情報取得
        String regDate = getNotificationRegistConditionDto().getSelectedEntity().getRegDate();
        String seq = getNotificationRegistConditionDto().getSelectedEntity().getSeq();
        getPublicTargetDto().setInfoShu(INFO_SHU);
        getPublicTargetDto().setRegDate(regDate);
        getPublicTargetDto().setSeq(seq);
        try { 
        	settingKokaiTaisho(regDate, seq);
        }
        catch (Exception ex) {
            throw new FtlSystemException(InfoShu.OSIRASE_NAME + "登録");
        }

        // 関連文書情報取得
        List listKanren = getGetKanrenBunshoInfoLogic().execute(getBirdUserInfo().getUserID(), INFO_SHU, regDate, seq, null, null);
        Collections.sort(listKanren, new SortComparator());
        getNotificationRegistConditionDto().setListKanrenBunsho(listKanren);
        
        
        
        return VIEWID_EDIT;
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {
        ConditionDto dto = getNotificationRegistConditionDto();

        // ページ番号
        dto.setCurrentPageNumber(getSelectPageNumber());
        // 選択中のリストインデックス
        dto.setSelectedListIndex(0);
        // DTOで保持しているリストをクリア
        dto.setListTutatu(null);
        /* ロジック【通達情報の取得】を実行 */
        List listTutatu = getGetTutatuLogic().getTutatu(dto.getSelectedNengetu(),
                                                        dto.getSelectedCateId(), 
                                                        dto.getSelectedKanriNo(),
                                                        dto.getCurrentPageNumber(),
                                                        getMstUser());
        
        //指定条件の通達一覧を設定
        dto.setListTutatu(listTutatu);
        
        return null;
    }
    /**
     * 公開対象情報取得処理
     *
     */
    private void settingKokaiTaisho(String regDate, String seq) {
        // 公開対象情報取得
        PublicTargetDto publicDto = getKoukaiTaishoLogic.execute(
        		INFO_SHU, regDate, seq);
        getPublicTargetDto().setListTrnControlCompany(publicDto.getListTrnControlCompany()); 
        getPublicTargetDto().setListTrnControlGyotai(publicDto.getListTrnControlGyotai()); 
        getPublicTargetDto().setListTrnControlGyotaiKobetu(publicDto.getListTrnControlGyotaiKobetu()); 
        getPublicTargetDto().setListTrnControlGyotaiTenpo(publicDto.getListTrnControlGyotaiTenpo()); 
        getPublicTargetDto().setListTrnControlShozoku(publicDto.getListTrnControlShozoku()); 

    }

    /**
     * @param selectPageNumber selectPageNumber を設定。
     */
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }

    /**
     * @return selectPageNumber を戻します。
     */
    public int getSelectPageNumber() {
        return selectPageNumber;
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
     * MstUser取得処理
     * @return
     */
    private MstUser getMstUser() {
        return getBirdUserInfo().getMstUser();
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
     * セッション情報クリア処理
     */
    private void clearSessionInfoFromMenu() {
        getNotificationRegistConditionDto().setCateId("");
        getNotificationRegistConditionDto().setNengetu("");
        getNotificationRegistConditionDto().clearCondInsOrSearch();
    }
    /**
     * 関連文書ソートオブジェクト
     * 
     * @author xkinu
     *
     */
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
     * 対象汎用画面別ロール設定情報取得処理
     * 
     * @return true･･･表示可能なユーザ、false･･･表示可能なユーザでない
     */
    private boolean existGamenRole() {
        //引数用DTO作成
        GamenRoleDto dto = new GamenRoleDto();
        dto.setUserId(getBirdUserInfo().getUserID());
        dto.setGamenId(SCREEN_ID);
        dto.setBunruiCd("01");
        
        try{
        	getGetGamenRoleLogic().excute(dto);
        }catch(NotExistException ne){
            return false;
        }
        
        return true;
    }
	/**
	 * @return クラス変数getGamenRoleLogic を戻します。
	 */
	public GetGamenRoleLogic getGetGamenRoleLogic() {
		return getGamenRoleLogic;
	}
	/**
	 * @param getGamenRoleLogic を クラス変数getGamenRoleLogicへ設定します。
	 */
	public void setGetGamenRoleLogic(GetGamenRoleLogic getGamenRoleLogic) {
		this.getGamenRoleLogic = getGamenRoleLogic;
	}
}