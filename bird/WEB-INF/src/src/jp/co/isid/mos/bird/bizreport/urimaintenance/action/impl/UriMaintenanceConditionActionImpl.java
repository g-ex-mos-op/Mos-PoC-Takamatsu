package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.CompanyListLogic;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.logic.UriMainteHeaderLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenance.action.UriMaintenanceConditionAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.ChkEditModeLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.UriMainteListLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.UriMainteWorkListLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenance.util.ListCopy;
import jp.co.isid.mos.bird.common.dao.MstHanshaOnerDao;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 売上修正
 * 
 * 更新日:2012/07/27 会計区分追加対応 
 *   1.[仕様番号:SP01021]追加 ＦＣ店舗のみ修正可能にする。
 * 
 */
public class UriMaintenanceConditionActionImpl implements UriMaintenanceConditionAction {
   
    /* アクションID */
    public static final String initialize_ACTION_ID     = "BBR008A01";
    public static final String miseSearch_ACTION_ID     = "BBR008A02";
    public static final String execute_ACTION_ID        = "BBR008A03";
    
    
    /**
     * ユーザ関連情報【共通】
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 日付関連情報【共通】
     */
    private BirdDateInfo birdDateInfo;
    
    
    /**
     * 会社情報取得ロジック【共通】
     */
    private CompanyListLogic companyListLogic;
    
    /**
     * 店舗情報取得ロジック【共通】
     */
    private GetMiseLogic getMiseLogic;
    
    /**
     * 売上修正共通ヘッダー取得ロジック【共通】
     */
    private UriMainteHeaderLogic uriMainteHeaderLogic;
    
    /**
     * 売上修正リスト取得ロジック
     */
    private UriMainteWorkListLogic uriMainteWorkListLogic;
    
    /**
     * 売上リスト(比較用)取得ロジック
     */
    private UriMainteListLogic uriMainteListLogic;
    /**
     * バッチステータスチェックロジック
     */
    private ChkEditModeLogic chkEditModeLogic;
    
    /**
     * 売上修正DTO
     */
    private UriMaintenanceDto uriMaintenanceDto;

    /**
     * メニューDTO【共通】
     */
    private PullDownMenuDto pullDownMenuDto;

    /**
     * 店舗情報DTO【共通】
     */
    private MiseSearchDto miseSearchDto;
    
    /**
     * 販社オーナー情報【共通】
     */
    private MstHanshaOnerDao mstHanshaOnerDao;
    

    /**
     * 初期表示
     */
    public String initialize(){
        
        // メニューから遷移
        if (getPullDownMenuDto().isClearFlg()) {
            pullDownMenuDto.setClearFlg(false);
            
            // ウインドウID生成
            getUriMaintenanceDto().updateWindowid();
                        
            // ユーザ設定
            String user = getBirdUserInfo().getUserID();
            getUriMaintenanceDto().setUserId(user);
            
            // アプリ日付設定
            String appdate = getBirdDateInfo().getAppDate();
            getUriMaintenanceDto().setAppDate(appdate);
            
            // システム日付設定
            String sysdate = getBirdDateInfo().getSysDate();
            getUriMaintenanceDto().setSysDate(sysdate);
                        
            //会社情報を取得する
            List listCmp = getCompanyListLogic().execute(new HashMap());
            getUriMaintenanceDto().setListCompany(listCmp);
            getUriMaintenanceDto().setCondCompanyCd("00");

            // 現在日付取得
            DateFormatter formatter = new DateFormatter();
            String appDate = getUriMaintenanceDto().getAppDate();
            String appMonth = formatter.format(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
            getUriMaintenanceDto().setAppMonth(appMonth);
            
            //対象期間を取得する
            List listTgt = makeCondListTargetYM();
            getUriMaintenanceDto().setListTargetYm(listTgt);
            getUriMaintenanceDto().setCondTargetYm(appMonth);
            
        }
        
        // 店舗検索
        else if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT){
            // ウインドウID反映
            getUriMaintenanceDto().setWindowId(miseSearchDto.getWindowId());
            
            if (getMiseSearchDto().isActionFlg()) {
                // アクションフラグ設定
                getMiseSearchDto().setActionFlg(false);
                
                //店舗情報を取得
                String miseCd = getMiseSearchDto().getMiseCd();
                
                getUriMaintenanceDto().setCondMiseCd(miseCd);
                
            }
        }
        

        
        return null;
    }
    
    
    /**
     * 店舗検索
     */
    public String miseSearch() {
        
        miseSearchDto.setWindowId(getUriMaintenanceDto().getWindowId());
        
        getMiseSearchDto().setActionFlg(true);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNeedReturnKind(true);
        getMiseSearchDto().setNavigationCase(UriMaintenanceConstants.VIEWID_CONDITION);
        
        return UriMaintenanceConstants.VIEWID_MISESEARCH;
    }
    
    
    /**
     * 実行
     * 更新日:2013/10/14 周春建　201308-003_実装_e-mossless_売上修正画面
     * 更新日:2014/12/23 周春建　(販社おーナーコードが36175以外)という条件の削除
     */
    public String execute() {        
        //１．店舗情報取得
        MstMise mstMise = getGetMiseLogic().execute(
                getUriMaintenanceDto().getCondCompanyCd(),
                getUriMaintenanceDto().getCondMiseCd());
        
        if(mstMise==null) {
            throw new NoResultException();
        }
        //２．検索対象の店舗がRC店舗か確認する。
        if("2".equals(mstMise.getMiseKbn().substring(1,2))) {
            //[仕様番号:SP01021]店がRC店舗の場合、下記のExceptionを発生させます。
            //Exception:エラーメッセージ・「RC店舗の売上修正は直営システムで行ってください。」を表示する。
            //throw new GenericMessageException("RC店舗の売上修正は直営システムで行ってください。");
            
            //Exception:エラーメッセージ・「RC店舗の売上修正はストア管理システムで行ってください。」を表示する。
            throw new GenericMessageException("RC店舗の売上修正はストア管理システムで行ってください。");
        }
        //３．検索対象の店舗がMSC店舗か確認する。
        List hanshaOner = getMstHanshaOnerDao().getHanshaOner(mstMise.getCompanyCd(),mstMise.getOnerCd());
        String errMsg = "";
        if (hanshaOner != null && hanshaOner.size()!=0) {
//            if("36175".equals(mstMise.getOnerCd())) {
//                 //店がＰＦ店舗の場合、下記のExceptionを発生させます。
//                 //Exception:エラーメッセージ・「パートナーズフォー店舗の売上修正はストア管理システムで行ってください。」を表示する。
//                 errMsg = "パートナーズフォー店舗の売上修正はストア管理システムで行ってください。";
//            } else {
//                //店がMSC店舗の場合、下記のExceptionを発生させます。
//                //Exception:エラーメッセージ・「モスストアカンパニー店舗の売上修正はストア管理システムで行ってください。」を表示する。
            errMsg = "モスストアカンパニー店舗の売上修正はストア管理システムで行ってください。";
//            }
            throw new GenericMessageException(errMsg);
        }

        String miseNm = mstMise.getMiseNameKj();
        if(mstMise.getCloseDt().compareTo(getUriMaintenanceDto().getAppDate()) <= 0) {
            miseNm += "(CLOSE)";
        }
        getUriMaintenanceDto().setCondMiseNm(miseNm);
        //会社名称取得
        String companyNm = "";
        String companyCd = getUriMaintenanceDto().getCondCompanyCd();
        List listCompany = getUriMaintenanceDto().getListCompany();
        for (Iterator ite = listCompany.iterator(); ite.hasNext();) {
            CodCompany entity = (CodCompany) ite.next();
            if(entity.getCompanyCd().equals(companyCd)){
                companyNm = entity.getCompanyName();
                break;
            }
        }
        getUriMaintenanceDto().setCondCompanyNm(companyNm);
        
        //-------------------------------
        // ロジック【売上情報取得】実行
        //-------------------------------
        List listUri = getUriMainteWorkListLogic().execute(
                getUriMaintenanceDto().getCondCompanyCd(),
                getUriMaintenanceDto().getCondMiseCd(),
                getUriMaintenanceDto().getCondTargetYm(),
                getUriMaintenanceDto().getSysDate());
        
        if(listUri==null || listUri.size() <= 0) {
            throw new NoResultException();
        }
        
        //------------------------------------
        // 修正前のデータを保持する
        //------------------------------------
        List listUriPre = new ArrayList();
        for (Iterator ite = listUri.iterator(); ite.hasNext();) {
            UIUriMainteWorkInfo workInfo = (UIUriMainteWorkInfo) ite.next();
            listUriPre.add(ListCopy.copyReviseData(workInfo));
        }
        
        
        //-------------------------------------
        // ロジック【比較用売上情報取得】実行
        //-------------------------------------
        List listUriTmp = new ArrayList();
        listUriTmp = getUriMainteListLogic().execute(
                getUriMaintenanceDto().getCondCompanyCd(),
                getUriMaintenanceDto().getCondMiseCd(),
                getUriMaintenanceDto().getCondTargetYm(),
                getUriMaintenanceDto().getSysDate());
        
        //売上修正データと売上元データの件数が一致しない。
        if(listUri.size() != listUriTmp.size()) {
            throw new FtlSystemException("売上修正比較もとデータと売上修正データが一致しません。");
        }
        
        // ウインドウID生成
        getUriMaintenanceDto().updateWindowid();
        getUriMaintenanceDto().clearRegistData();
        //ユーザーオペレーションチェック
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        // セッションキーを生成し、DTOにセット。
        String sessionKey = makeSessionKey._makeSessionKey();
        getUriMaintenanceDto().setSessioniKey(sessionKey);
        getUriMaintenanceDto().setViewSessionKey(sessionKey);

        //-----------------------------------------
        // 売上修正リスト(計算結果・合計行)を生成
        //-----------------------------------------
        //修正データ
        UriMaintenanceCommon.createUriMainteList(listUri);
        getUriMaintenanceDto().setListUri(listUri);
        //修正前データ
        UriMaintenanceCommon.createUriMainteList(listUriPre);
        getUriMaintenanceDto().setListUriPre(listUriPre);
        //元データ
        UriMaintenanceCommon.createUriMainteList(listUriTmp);
        getUriMaintenanceDto().setListUriTmp(listUriTmp);        
        
        
        //----------------------------------------
        // ロジック【売上修正共通ヘッダー取得】実行
        //----------------------------------------
        UriMainteHeader uriMainteHeader = getUriMainteHeaderLogic().execute(companyCd);
        getUriMaintenanceDto().setHeader(uriMainteHeader);

        //-----------------------------------------
        // ロジック【編集モードチェック】実行
        //-----------------------------------------
        getUriMaintenanceDto().setEditMode(getChkEditModeLogic().execute(listUri));
        
        
        //タブ初期化
        getUriMaintenanceDto().setViewIndex(UriMaintenanceConstants.TAB_INDEX_URIAGEKIN);
        getUriMaintenanceDto().setSubIndex(UriMaintenanceConstants.TAB_SUB_INDEX_NO1);

//add 2019/07/19 USI欒 #34 begin
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatter.PATTERN_MONTH);
        String uriageMeisaiDispFlg = "0";
        try {
            Date taisyoYM = sdf.parse(getUriMaintenanceDto().getCondTargetYm());
            //売上と消費税 明細修正表示フラグ設定
            if (taisyoYM.before(sdf.parse(UriMaintenanceConstants.URIAGE_MEISAI_MIN_MONTH))) {
                uriageMeisaiDispFlg = "0";
            } else {
                uriageMeisaiDispFlg = "1";
            }
        } catch (ParseException e) {
            // 必要なし
        }
        getUriMaintenanceDto().setUriageMeisaiDispFlg(uriageMeisaiDispFlg);
//add 2019/07/19 USI欒 #34 end

        //参照モードであれば確認画面へ遷移する
        String viewId = UriMaintenanceConstants.VIEWID_EDIT;
        if(!getUriMaintenanceDto().isEditMode()) {
            viewId = UriMaintenanceConstants.VIEWID_CONFIRM;
        }
        return viewId;
    }

    
    /**
     * 対象年月プルダウン生成
     * @return
     */
    private List makeCondListTargetYM() {
        
        //現在日付取得
        DateFormatter formatter = new DateFormatter();
        String appDate = getUriMaintenanceDto().getAppDate();
        String appMonth = formatter.format(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        
        List listMonth = new ArrayList();
        for (int i = 0; i < UriMaintenanceConstants.NENGETU_DISPLAY_MONTH; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(appMonth, i);
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

    
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    
    public CompanyListLogic getCompanyListLogic() {
        return companyListLogic;
    }
    public void setCompanyListLogic(CompanyListLogic companyListLogic) {
        this.companyListLogic = companyListLogic;
    }    
    
    public UriMaintenanceDto getUriMaintenanceDto() {
        return uriMaintenanceDto;
    }
    public void setUriMaintenanceDto(UriMaintenanceDto uriMaintenanceDto) {
        this.uriMaintenanceDto = uriMaintenanceDto;
    }
    
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }
    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }

    public UriMainteWorkListLogic getUriMainteWorkListLogic() {
        return uriMainteWorkListLogic;
    }
    public void setUriMainteWorkListLogic(UriMainteWorkListLogic uriMainteListLogic) {
        this.uriMainteWorkListLogic = uriMainteListLogic;
    }
    public UriMainteListLogic getUriMainteListLogic() {
        return uriMainteListLogic;
    }
    public void setUriMainteListLogic(UriMainteListLogic uriMainteListLogic) {
        this.uriMainteListLogic = uriMainteListLogic;
    }
    public UriMainteHeaderLogic getUriMainteHeaderLogic() {
        return uriMainteHeaderLogic;
    }
    public void setUriMainteHeaderLogic(UriMainteHeaderLogic uriMainteHeaderLogic) {
        this.uriMainteHeaderLogic = uriMainteHeaderLogic;
    }
    public ChkEditModeLogic getChkEditModeLogic() {
        return chkEditModeLogic;
    }
    public void setChkEditModeLogic(ChkEditModeLogic chkEditModeLogic) {
        this.chkEditModeLogic = chkEditModeLogic;
    }
    /**
     * @return mstHanshaOnerDao を戻します。
     */
    public MstHanshaOnerDao getMstHanshaOnerDao() {
        return mstHanshaOnerDao;
    }
    /**
     * @param mstHanshaOnerDao 設定する mstHanshaOnerDao。
     */
    public void setMstHanshaOnerDao(MstHanshaOnerDao mstHanshaOnerDao) {
        this.mstHanshaOnerDao = mstHanshaOnerDao;
    }
    
}
