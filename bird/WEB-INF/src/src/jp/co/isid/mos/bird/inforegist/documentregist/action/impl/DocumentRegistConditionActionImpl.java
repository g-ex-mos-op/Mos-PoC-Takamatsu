
/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenSakiBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.GetSubCategoryLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetCategoryLogicImpl;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.inforegist.documentregist.action.DocumentRegistConditionAction;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistConditionDto;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.CountBunshoLogic;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.GetBunshoInfoLogic;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.GetDefaultSubCategoryLogic;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.impl.CountBunshoLogicImpl;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.impl.GetBunshoInfoLogicImpl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 文書登録 条件画面アクション
 * @author xnkusama
 */
public class DocumentRegistConditionActionImpl implements CommonAction, DocumentRegistConditionAction {

    /* アクションID */
    public static final String initialize_ACTION_ID   = "BIF003A01";
    public static final String search_ACTION_ID       = "BIF003A02";
    public static final String changeTab_ACTION_ID    = "BIF003A03";
    public static final String changePage_ACTION_ID   = "BIF003A04";
    public static final String goRegist_ACTION_ID     = "BIF003A05";
    public static final String goInsert_ACTION_ID     = "BIF003A06";
    public static final String goDelete_ACTION_ID     = "BIF003A07";
    public static final String registRank_ACTION_ID   = "BIF003A08";
    /* ビューID */
    private static final String VIEWID_EDIT     = "BIF003V02";
    private static final String VIEWID_CONFIRM  = "BIF003V03";
    private static final String VIEWID_RANK     = "BIF003V04";
    
    /* 情報種別 */
    private static String INFO_SHU = "03";
    
    /* 年月出力月数 */
//--- 2007/08/07 3年間に変更    
//    private static int NENGETU_DISPLAY_MONTH = 6;
    private static int NENGETU_DISPLAY_MONTH = 36;
    
//    /* セッション */
//    private HttpSession session;
    /* カテゴリ一覧 */
    private List listCategory;
    /* DTO */
    private DocumentRegistConditionDto documentRegistConditionDto;
    //private UIBunshoInfo uiBunshoInfo;
    private PublicTargetDto publicTargetDto;
    private PullDownMenuDto pullDownMenuDto;

// add start xkhata
    private DocumentRegistRegistFormDto documentRegistRegistFormDto;
// add end
    
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;
    /* 選択サブカテゴリID */
    private String _selectedSubCateId;
    /* 選択ページ番号 */
    private int selectPageNumber;
    /* ロジック */
    // サブカテゴリ取得ロジック
    private GetSubCategoryLogic getSubCategoryLogic;
    // 文書の件数取得ロジック
    private CountBunshoLogic countBunshoLogic;
    // 文書一覧情報の取得
    private GetBunshoInfoLogic getBunshoInfoLogic;
    // デフォルトサブカテゴリの取得
    private GetDefaultSubCategoryLogic getDefaultSubCategoryLogic;
    //
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;
    //【関連文書検索】
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    //自分を関連先として登録している文書の取得ロジック
    private GetKanrenSakiBunshoInfoLogic getKanrenSakiBunshoInfoLogic; 

    /**
     * 初期処理
     * @return
     */
    public String initialize() throws ApplicationException {
    	
// 2006/03/09 e-mosリンク対応 xkhata
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	setPullDownMenuDto( (PullDownMenuDto) container.getComponent(PullDownMenuDto.class) );
// end
        // メニューより遷移した場合は、セッションで保持している値をクリア
        if (getPullDownMenuDto().isClearFlg()) {
            clearSessionInfoFromMenu();
            getPullDownMenuDto().setClearFlg(false);
        }

        if (getDocumentRegistConditionDto().isFlgFromZyuni()) {
            // 再検索
            search();
            getDocumentRegistConditionDto().setFlgFromZyuni(false);
        }
        
        // 共通ロジック【カテゴリの取得】を実行する.
        //S2Container container = SingletonS2ContainerFactory.getContainer();
        GetCategoryLogic logic = (GetCategoryLogic) container.getComponent(GetCategoryLogicImpl.class);
        
        List listCategory = logic.getCategory(INFO_SHU);
        // カテゴリ情報をDTOへセット
        getDocumentRegistConditionDto().setListCategory(listCategory);
        
        getDocumentRegistConditionDto().setListNengetu(getListNengetu());
        getDocumentRegistConditionDto().setListKanrenSaki(null);
                
        return null;
    }

    /**
     * 文書一覧検索処理
     * @return
     */
    public String search() {
        
        DocumentRegistConditionDto dto = getDocumentRegistConditionDto();
        dto.setCurrentPageNumber(1);

        // 検索結果をクリア
        dto.clearCondInsOrSearch();
        
        // デフォルトサブカテゴリの取得
        String defSubCateId = getGetDefaultSubCategoryLogic().getDefaultSubCategory(dto,getMstUser());
        dto.setSelectedSubCateId(defSubCateId);

        
        // ロジック【文書情報の取得】
        List listBunsho = getGetBunshoInfoLogic()
                                .getBunsho(dto.getNengetu(),
                                            dto.getCateId(), 
                                            dto.getSelectedSubCateId(),
                                            dto.getCurrentPageNumber(),
                                            getMstUser().getRCompanyCd(),
                                            getMstUser().getBumonCd(),
                                            getBirdDateInfo().getSysDate());

        dto.setListBunsho(listBunsho);

        
        /* 共通ロジック【サブカテゴリの取得】を実行 */
        List listSubCategory = getGetSubCategoryLogic().getSubCategory(INFO_SHU, getDocumentRegistConditionDto().getCateId());
        getDocumentRegistConditionDto().setListSubCategory(listSubCategory);

//2006/03/06 xkhata タブ切替対策
        getDocumentRegistConditionDto().setFirstNengetsu( getDocumentRegistConditionDto().getNengetu() );
        getDocumentRegistConditionDto().setFirstCateId( getDocumentRegistConditionDto().getCateId() );
//
        
        /* 指定条件での文書件数取得 */
        int countBunsho = getCountBunshoLogic().countBunsho(dto.getNengetu(),
                                                            dto.getCateId(),
                                                            dto.getSelectedSubCateId(),
                                                            getMstUser().getRCompanyCd(),
                                                            getMstUser().getBumonCd(),
                                                            getBirdDateInfo().getSysDate());
        
        // 指定条件の件数を設定
        dto.setCount(countBunsho);
        
        
        // 選択カテゴリIDを保持
        dto.setSelectedCateId(dto.getCateId());
        // 選択年月を保持
        dto.setSelectedNengetu(dto.getNengetu());
        // 選択サブカテゴリをクリア
//        dto.setSelectedSubCateId(null);
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
        // セッション保持データクリア
        clearSessionInfoFromMenu();
        
        getDocumentRegistConditionDto().setRegMode(DocumentRegistConditionDto.REG_MODE_INSERT);
        getDocumentRegistConditionDto().setFlgCondToReg(true);
        
// add start xkhata 
        getDocumentRegistRegistFormDto().setAttachName(null);
        getDocumentRegistRegistFormDto().setUploadFileName(null);
// add end
        // 公開対象 共通DTOクリア
        getPublicTargetDto().setInfoShu(INFO_SHU);
        getPublicTargetDto().setListTrnControlCompany(new ArrayList());
        getPublicTargetDto().setListTrnControlGyotai(new ArrayList());
        getPublicTargetDto().setListTrnControlGyotaiKobetu(new ArrayList());
        getPublicTargetDto().setListTrnControlGyotaiTenpo(new ArrayList());
        getPublicTargetDto().setListTrnControlShozoku(new ArrayList());
        
        // 関連文書 共通DTOクリア
        getDocumentRegistConditionDto().setListKanrenBunsho(new ArrayList());
        return VIEWID_EDIT;
    }

    /**
     * @param listCategory listCategory を設定。
     */
    public void setListCategory(List listCategory) {
        this.listCategory = listCategory;
    }

    /**
     * @return listCategory を戻します。
     */
    public List getListCategory() {
        return listCategory;
    }

    /**
     * 条件画面DTO設定処理
     * @param conditionDto conditionDto を設定。
     */
    public void setDocumentRegistConditionDto(DocumentRegistConditionDto documentRegistConditionDto) {
        this.documentRegistConditionDto = documentRegistConditionDto;
    }

    /**
     * 条件画面DTO取得処理
     * @return conditionDto を戻します。
     */
    public DocumentRegistConditionDto getDocumentRegistConditionDto() {
        return documentRegistConditionDto;
    }
    
//    /**
//     * HttpSession設定処理
//     * @param session
//     */
//    public void setSession(HttpSession session) {
//        this.session = session;
//    }

    /**
     * タブ変更処理
     * @return
     */
    public String changeTab() {
        DocumentRegistConditionDto dto = getDocumentRegistConditionDto();
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // セッションからMstUser取得
        // 選択したタブのサブカテゴリIDを保持
        dto.setSelectedSubCateId(getSelectedSubCateId());
        // カテゴリID、年月を検索時のものに戻す
        dto.setCateId(dto.getSelectedCateId());
        // 選択中のリストインデックス
        dto.setSelectedListIndex(0);
        /* 指定条件での文書件数取得 */
        dto.setNengetu(dto.getSelectedNengetu());
        CountBunshoLogic logicCountBunsho = (CountBunshoLogic) container.getComponent(CountBunshoLogicImpl.class);
        int countBunsho = logicCountBunsho.countBunsho(dto.getNengetu(),
                                                        dto.getCateId(),
                                                        dto.getSelectedSubCateId(),
                                                        getMstUser().getRCompanyCd(),
                                                        getMstUser().getBumonCd(),
                                                        getBirdDateInfo().getSysDate());
        dto.setCount(countBunsho);
        dto.setCurrentPageNumber(1);
        /* ロジック【文書一覧情報の取得】を実行 */
        GetBunshoInfoLogic logicGetBunsho = (GetBunshoInfoLogic) container.getComponent(GetBunshoInfoLogicImpl.class);
        // ページ番号
        dto.setCurrentPageNumber(1);
        // DTOで保持しているリストをクリア
        dto.setListBunsho(null);
        List listBunsho = logicGetBunsho.getBunsho(dto.getNengetu(),
                                                   dto.getCateId(), 
                                                   dto.getSelectedSubCateId(),
                                                   dto.getCurrentPageNumber(),
                                                   getMstUser().getRCompanyCd(),
                                                   getMstUser().getBumonCd(),
                                                   getBirdDateInfo().getSysDate());
        
        dto.setListBunsho(listBunsho);
        
        return null;
    }

    /**
     * 選択中サブカテゴリID設定処理
     * @param _selectedSubCateId _selectedSubCateId を設定。
     */
    public void setSelectedSubCateId(String _selectedSubCateId) {
        this._selectedSubCateId = _selectedSubCateId;
    }

    /**
     * 選択中サブカテゴリID取得処理
     * @return _selectedSubCateId を戻します。
     */
    public String getSelectedSubCateId() {
        return _selectedSubCateId;
    }

    /**
     * 削除ボタンアクション
     * @return
     */
    public String goDelete() {
    	
        getDocumentRegistConditionDto().setRegMode(DocumentRegistConditionDto.REG_MODE_DELETE);
        getDocumentRegistConditionDto().setFlgCondToReg(true);
        
        //xyamauchi add
        getDocumentRegistRegistFormDto().setAttachName(null);
        getDocumentRegistRegistFormDto().setUploadFileName(null);
        getDocumentRegistRegistFormDto().clearFileName();
        //xyamauchi end
        
        // 公開対象情報取得
        String regDate = getDocumentRegistConditionDto().getSelectedEntity().getRegDate();
        String seq = getDocumentRegistConditionDto().getSelectedEntity().getSeq();
        getPublicTargetDto().setInfoShu(INFO_SHU);
        getPublicTargetDto().setRegDate(regDate);
        getPublicTargetDto().setSeq(seq);
        try { 
        	settingKokaiTaisho( regDate, seq);
        }
        catch (Exception ex) {
            throw new FtlSystemException("文書登録");
        }
    
        // 関連文書情報取得
        List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU, regDate, seq);
        getDocumentRegistConditionDto().setListKanrenBunsho(listKanren);
        
        //リンクファイルとして登録されているか
        List list =  getGetKanrenSakiBunshoInfoLogic().execute(INFO_SHU, regDate, seq);
        getDocumentRegistConditionDto().setListKanrenSaki(list);

        
        return VIEWID_CONFIRM;
    }

    /**
     * 変更ボタンアクション  
     *    編集画面へ遷移
     * @return
     */
    public String goRegist() {
        getDocumentRegistConditionDto().setRegMode(DocumentRegistConditionDto.REG_MODE_UPDATE);
        getDocumentRegistConditionDto().setFlgCondToReg(true);

//  add start xkhata 
        getDocumentRegistRegistFormDto().setAttachName(null);
        getDocumentRegistRegistFormDto().setUploadFileName(null);
// add end

        // 公開対象情報取得
        String regDate = getDocumentRegistConditionDto().getSelectedEntity().getRegDate();
        String seq = getDocumentRegistConditionDto().getSelectedEntity().getSeq();
        getPublicTargetDto().setInfoShu(INFO_SHU);
        getPublicTargetDto().setRegDate(regDate);
        getPublicTargetDto().setSeq(seq);
        try { 
        	settingKokaiTaisho( regDate, seq);
        }
        catch (Exception ex) {
            throw new FtlSystemException("文書登録");
        }
    
        // 関連文書情報取得
        List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU, regDate, seq);
        getDocumentRegistConditionDto().setListKanrenBunsho(listKanren);
        
        return VIEWID_EDIT;
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {

        S2Container container = SingletonS2ContainerFactory.getContainer();
        DocumentRegistConditionDto dto = getDocumentRegistConditionDto();

        /* ロジック【文書一覧情報の取得】を実行 */
        GetBunshoInfoLogic logicGetBunsho = (GetBunshoInfoLogic) container.getComponent(GetBunshoInfoLogicImpl.class);
        // ページ番号
        dto.setCurrentPageNumber(getSelectPageNumber());
        // 選択カテゴリIDを保持
        dto.setSelectedCateId(dto.getCateId());
        // 選択年月を保持
        dto.setSelectedNengetu(dto.getNengetu());
        // 選択サブカテゴリをクリア
//        dto.setSelectedSubCateId(null);
        
// 2006/03/06 xkhata タブ対応
        dto.setNengetu( getDocumentRegistConditionDto().getFirstNengetsu() );
        dto.setCateId( getDocumentRegistConditionDto().getFirstCateId() );
        
//
//--- 2007/08/07 選択位置をクリア
//        // 選択中のリストインデックス
//        if (dto.getSelectedListIndex() < 0) {
//            dto.setSelectedListIndex(0);
//        }
        dto.setSelectedListIndex(0);
        
        // DTOで保持しているリストをクリア
        dto.setListBunsho(null);
        List listBunsho = logicGetBunsho.getBunsho(dto.getNengetu(),
                                                   dto.getCateId(), 
                                                   dto.getSelectedSubCateId(),
                                                   dto.getCurrentPageNumber(),
                                                   getMstUser().getRCompanyCd(),
                                                   getMstUser().getBumonCd(),
                                                   getBirdDateInfo().getSysDate());
        
        dto.setListBunsho(listBunsho);
        
        return null;
    }

    /**
     * 公開対象情報取得処理
     *
     */
    private void settingKokaiTaisho(String regDate, String seq) {
        // 公開対象情報取得
        PublicTargetDto publicDto = getKoukaiTaishoLogic.execute(
        		INFO_SHU, regDate,seq);
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
     * MstUser取得処理
     * @return
     */
    private MstUser getMstUser() {
        return getBirdUserInfo().getMstUser();
    }

    /**
     * サブカテゴリ取得ロジック設定ロジック
     * @param getSubCategoryLogic getSubCategoryLogic を設定。
     */
    public void setGetSubCategoryLogic(GetSubCategoryLogic getSubCategoryLogic) {
        this.getSubCategoryLogic = getSubCategoryLogic;
    }

    /**
     * サブカテゴリ取得ロジック取得ロジック
     * @return getSubCategoryLogic を戻します。
     */
    public GetSubCategoryLogic getGetSubCategoryLogic() {
        return getSubCategoryLogic;
    }

    /**
     * 文書の件数取得ロジック設定処理
     * @param countBunshoLogic countBunshoLogic を設定。
     */
    public void setCountBunshoLogic(CountBunshoLogic countBunshoLogic) {
        this.countBunshoLogic = countBunshoLogic;
    }

    /**
     * 文書の件数取得ロジック取得処理
     * @return countBunshoLogic を戻します。
     */
    public CountBunshoLogic getCountBunshoLogic() {
        return countBunshoLogic;
    }

    /**
     * 順位設定アクション
     * @return
     */
    public String registRank() {
        return VIEWID_RANK;
    }
    
    
    /**
     * セッション情報クリア処理
     */
    private void clearSessionInfoFromMenu() {
        getDocumentRegistConditionDto().setCateId("");
        getDocumentRegistConditionDto().setCateId("");

        getDocumentRegistConditionDto().setNengetu("");
        getDocumentRegistConditionDto().clearCondInsOrSearch();
    }
    /**
     * @return getBunshoInfoLogic を戻します。
     */
    public GetBunshoInfoLogic getGetBunshoInfoLogic() {
        return getBunshoInfoLogic;
    }
    /**
     * @param getBunshoInfoLogic getBunshoInfoLogic を設定。
     */
    public void setGetBunshoInfoLogic(GetBunshoInfoLogic getBunshoInfoLogic) {
        this.getBunshoInfoLogic = getBunshoInfoLogic;
    }
    /**
     * @return getDefaultSubCategoryLogic を戻します。
     */
    public GetDefaultSubCategoryLogic getGetDefaultSubCategoryLogic() {
        return getDefaultSubCategoryLogic;
    }
    /**
     * @param getDefaultSubCategoryLogic getDefaultSubCategoryLogic を設定。
     */
    public void setGetDefaultSubCategoryLogic(
            GetDefaultSubCategoryLogic getDefaultSubCategoryLogic) {
        this.getDefaultSubCategoryLogic = getDefaultSubCategoryLogic;
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
    
    // 条件画面 年月プルダウン用リスト
    private List getListNengetu() {
        // 現在日付取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        DateFormatter formatter = new DateFormatter();
        //String sysDate = getBirdDateInfo().getSysDate();
        BirdDateInfo date = (BirdDateInfo) container.getComponent( BirdDateInfo.class );
        String sysDate = (String)date.getSysDate();
        String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        
        // 現在月から過去６ヶ月分の年月リスト作成
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
        return listMonth;
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
    
    public void setDocumentRegistRegistFormDto(DocumentRegistRegistFormDto documentRegistRegistFormDto) {
        this.documentRegistRegistFormDto = documentRegistRegistFormDto;
    }
    public DocumentRegistRegistFormDto getDocumentRegistRegistFormDto() {
        return this.documentRegistRegistFormDto;
    }
}