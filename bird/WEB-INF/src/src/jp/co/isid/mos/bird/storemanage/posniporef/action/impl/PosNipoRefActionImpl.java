/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.storemanage.posniporef.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.entity.MstOner;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.common.logic.GetOnerLogic;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.posniporef.action.PosNipoRefAction;
import jp.co.isid.mos.bird.storemanage.posniporef.common.PosNipoRefConstants;
import jp.co.isid.mos.bird.storemanage.posniporef.dto.PosNipoRequestDto;
import jp.co.isid.mos.bird.storemanage.posniporef.dto.PosNipoSessionDto;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.PosNipoInfo;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.TenpoInfo;
import jp.co.isid.mos.bird.storemanage.posniporef.logic.MakeMiseListByOnerLogic;
import jp.co.isid.mos.bird.storemanage.posniporef.logic.PosNipoInfoLogic;
import jp.co.isid.mos.bird.storemanage.posniporef.logic.PosNipoTenpoCntLogic;

/**
 * POS日報 画面アクション
 * @author xwatanabe
 */
public class PosNipoRefActionImpl implements PosNipoRefAction {

    /* アクションID定義 */
    public static final String initialize_ACTION_ID    = "BSM014A01";
    public static final String miseSearch_ACTION_ID    = "BSM014A02";
    public static final String onerSearch_ACTION_ID    = "BSM014A03";
    public static final String execute_ACTION_ID       = "BSM014A04";
    public static final String changeTab_ACTION_ID     = "BSM014A05";
    public static final String changeCompany_ACTION_ID = "BSM014A06";
    public static final String downloadCsv_ACTION_ID   = "BSM014A07";

    /* DTO */
    /** POS日報Dto(request) */
    private PosNipoRequestDto posNipoRequestDto;
    /** POS日報Dto(session) */
    private PosNipoSessionDto posNipoSessionDto;
    /** プルダウンメニューDto */
    private PullDownMenuDto pullDownMenuDto;
    /** 店舗選択DTO */
    private MiseSearchDto miseSearchDto;
    /** オーナー選択DTO */
    private OwnerSearchDto ownerSearchDto;

    /* LOGIC */
    /** ユーザー所属会社取得ロジック */
    private GetUserCompanyLogic getUserCompanyLogic;
    /** POS日報 店舗数取得ロジック */
    private PosNipoTenpoCntLogic posNipoTenpoCntLogic;
    /** POS日報情報取得ロジック */
    private PosNipoInfoLogic posNipoInfoLogic;
    /** POS日報 オナー対応店舗取得ロジック */
    private MakeMiseListByOnerLogic makeMiseListByOnerLogic;
    /** 店舗情報取得ロジック */
    private GetMiseLogic getMiseLogic;
    /** オーナー情報取得ロジック */
    private GetOnerLogic getOnerLogic;

    /* ACTION */
    private CsvOutputAction posNipoRefCSVAction;

    /* BIRD共通 */
    /** ユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** 日付情報 */
    private BirdDateInfo birdDateInfo;

    /**
     * 初期処理
     */
    public String initialize() {

        // 画面操作しているユーザーのユーザー情報取得。
        String userId     = birdUserInfo.getUserID();
        String userType   = birdUserInfo.getMstUser().getUserTypeCd();
        String appDate    = birdDateInfo.getAppDate();
        boolean limitFlg = birdUserInfo.isLimit();

        int windowId;

        //------------------------------------
        // メニュー画面から遷移してきた場合
        //------------------------------------
        if(pullDownMenuDto.isClearFlg()){
            pullDownMenuDto.setClearFlg(false);

            // ウインドウID生成・セット。
            windowId = posNipoSessionDto.createWindowId();
            posNipoRequestDto.setWindowId(windowId);

            //DTOクリア
            posNipoRequestDto.clear();


            //年月リスト作成
            List kikanList = getNengetsuList(appDate);
            String kikan = "";
            if(kikanList != null && kikanList.size() > 0){
                //プルダウンの先頭の年月を取得
                SelectItem a = (SelectItem)kikanList.get(0);
                kikan = (String)a.getValue();
            }

            //SessionDTOにセット
            posNipoSessionDto.setUserId(userId);            //ユーザID
            posNipoSessionDto.setUserTypeCd(userType);      //ユーザタイプ
            posNipoSessionDto.setAppDate(appDate);          //アプリ日付
            posNipoSessionDto.setLimitFlg(limitFlg);        //制限区分
            posNipoSessionDto.setKikanList(kikanList);      //期間プルダウンのリスト

            //本部ユーザの時
            if(userType != null && userType.equals(PosNipoRefConstants.USERTYPE_HONBU)){

                // ロジック【ユーザー所属会社取得】実行。
                List companyList = getUserCompanyLogic.execute(userId, null);

                //プルダウン先頭の会社情報を取得
                MstUserCompany comp = (MstUserCompany)companyList.get(0); 
                String companyCd   = comp.getCompanyCd();

                //SessionDTOにセット
                posNipoSessionDto.setCompanyList(companyList);

                //RequestDTOにセット
                posNipoRequestDto.setCompanyCd(companyCd);
                posNipoRequestDto.setKikan(kikan);              //指定期間
            }
            //オーナーユーザの時
            else if(userType != null && userType.equals(PosNipoRefConstants.USERTYPE_ONER)){

                //会社コードごとのオーナーコードを保持
                Map onerMap = new HashMap();
                List ownerList = birdUserInfo.getUserOner();
                for (Iterator it = ownerList.iterator(); it.hasNext();) {
                    UIUserOner uIUserOner = (UIUserOner) it.next();
                    String compCd = uIUserOner.getCompanyCd();
                    String onerCd = uIUserOner.getOnerCd();
                    onerMap.put(compCd, onerCd);
                }

                // ロジック【ユーザー所属会社取得】実行。
                List companyList = getUserCompanyLogic.execute(userId, null);

                //プルダウン先頭の会社情報を取得
                MstUserCompany comp = (MstUserCompany)companyList.get(0); 
                String companyCd   = comp.getCompanyCd();

                //オーナーコードを取得
                String onerCd = (String)onerMap.get(companyCd);

                //店舗リストマップ作成
                Map miseListMap = makeMiseListMap(appDate);
                List miseList = (List)miseListMap.get(companyCd);

                //プルダウン先頭の店舗情報を取得
                String miseCd = null;
                if(miseList != null && miseList.size() >0){
                    TenpoInfo mise = (TenpoInfo)miseList.get(0);
                    miseCd = mise.getMiseCd();
                }

                //SessionDTOにセット
                posNipoSessionDto.setCompanyList(companyList);
                posNipoSessionDto.setOnerMap(onerMap);
                posNipoSessionDto.setMiseListMap(miseListMap);
                posNipoSessionDto.setMiseList(windowId, miseList);

                //RequestDTOにセット
                posNipoRequestDto.setCompanyCd(companyCd);
                posNipoRequestDto.setOnerCd(onerCd);
                posNipoRequestDto.setMiseList(miseList);
                posNipoRequestDto.setMiseCd(miseCd);
                posNipoRequestDto.setKikan(kikan);          //指定期間
            }
            //店舗ユーザの時
            else if(userType != null && userType.equals(PosNipoRefConstants.USERTYPE_MISE)){

                //ユーザ情報取得
                List miseList = birdUserInfo.getUserMise();
                UIUserMise uIUserMise = (UIUserMise)miseList.get(0);
                String companyCd = uIUserMise.getCompanyCd();
                String miseCd = uIUserMise.getMiseCd();

                //会社名を取得
                String companyName = "";
                List companyList = getUserCompanyLogic.execute(userId, null);
                if(companyList != null && companyList.size() > 0){
                    for(int i=0; i < companyList.size(); i++){
                        MstUserCompany entity = (MstUserCompany)companyList.get(i);
                        if(companyCd.equals(entity.getCompanyCd())){
                            companyName = entity.getCompanyName();
                            break;
                        }
                    }
                }

                //店舗名を取得
                String miseName ="";
                MstMise mise = getMiseLogic.execute(companyCd, miseCd);
                miseName = miseCd + " " + mise.getMiseNameKj();

                //SessionDTOにセット
                posNipoSessionDto.setCompanyCdTenpoUser(companyCd);
                posNipoSessionDto.setCompanyNameTenpoUser(companyName);
                posNipoSessionDto.setMiseCdTenpoUser(miseCd);
                posNipoSessionDto.setMiseNameTenpoUser(miseName);

                //RequestDTOにセット
                posNipoRequestDto.setCompanyCd(companyCd);
                posNipoRequestDto.setMiseCd(miseCd);
                posNipoRequestDto.setKikan(kikan);
            }
        } 
        //------------------------------------
        // 店検索画面から遷移してきた場合
        //------------------------------------
        else if(miseSearchDto.getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
            miseSearchDto.setReturnKind(MiseSearchDto.RETURNKIND_INIT);

            // ウインドウID反映
            windowId = miseSearchDto.getWindowId();
            posNipoRequestDto.setWindowId(windowId);

            //SessionDTOより検索条件取得
            copySessionToRequest();

            //店検索画面で、店舗を決定した場合
            if (miseSearchDto.isActionFlg()) {
                miseSearchDto.setActionFlg(false);
                
                posNipoRequestDto.setMiseCd(miseSearchDto.getMiseCd());
                posNipoRequestDto.setKbn(PosNipoRefConstants.KBN_MISE);
            }
        } 
        //------------------------------------
        // オーナー検索画面から遷移してきた場合
        //------------------------------------
        else if(ownerSearchDto.getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
            ownerSearchDto.setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
            
            // ウインドウID反映
            windowId = ownerSearchDto.getWindowId();
            posNipoRequestDto.setWindowId(windowId);

            //SessionDTOより検索条件取得
            copySessionToRequest();

            //オーナー検索画面で、オーナーを決定した場合
            if (ownerSearchDto.isActionFlag()) {
                ownerSearchDto.setActionFlag(false);
                
                posNipoRequestDto.setOnerCd(ownerSearchDto.getOnerCd());    //取得したオーナーコード
                posNipoRequestDto.setKbn(PosNipoRefConstants.KBN_OWNER);    //条件区分を2(オーナ)
            }
        }
        //------------------------------------
        // 自画面からの遷移の時
        //------------------------------------
        else{
            //SessionDtoよりデータを取得、RequestDtoにセット
            copySessionToRequest();
        }
        
        return null;
    }

    /**
     * 店検索
     */
    public String miseSearch() {
        // 初期化
        miseSearchDto.clear();
        miseSearchDto.setInitialFlag(true);

        // 会社コードリスト取得。
        List companyCdList = new ArrayList();
        companyCdList.add(posNipoRequestDto.getCompanyCd());

        // 店舗選択Dtoにセット。
        miseSearchDto.setRCompanyCdList(companyCdList);
        miseSearchDto.setNavigationCase(PosNipoRefConstants.VIEW_ID_POSNIPO);
        miseSearchDto.setNeedReturnKind(true);
        miseSearchDto.setWindowId(posNipoRequestDto.getWindowId());

        int windowId  = posNipoRequestDto.getWindowId();
        boolean existDataFlg = posNipoRequestDto.isExistDataFlg();
        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();

        //-----------------------------------
        // 検索結果データが存在する時
        //-----------------------------------
        if(existDataFlg){

            //現在の検索条件を保持
            posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
            posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
            posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
            posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
            posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());

            //前回検索条件を保持
            posNipoSessionDto.setCompanyCdZen(windowId, posNipoRequestDto.getCompanyCdZen());
            posNipoSessionDto.setKikanZen(windowId, posNipoRequestDto.getKikanZen());
            posNipoSessionDto.setMiseCdZen(windowId, posNipoRequestDto.getMiseCdZen());
            posNipoSessionDto.setOnerCdZen(windowId, posNipoRequestDto.getOnerCdZen());
            posNipoSessionDto.setKbnZen(windowId, posNipoRequestDto.getKbnZen());
            
            //既存データを保持しているか
            if(!posNipoSessionDto.containsWindowId(windowId)){
                //前回検索条件を取得
                String companyCdZen = posNipoRequestDto.getCompanyCdZen();
                String kikanZen     = posNipoRequestDto.getKikanZen();
                String miseCdZen    = posNipoRequestDto.getMiseCdZen();
                String onerCdZen    = posNipoRequestDto.getOnerCdZen();
                String kbnZen       = posNipoRequestDto.getKbnZen();

                //検索
                search(windowId, companyCdZen, kikanZen, miseCdZen, onerCdZen, kbnZen, userId, limitFlg);
            }
        }
        //-----------------------------------
        // 未検索の場合
        //-----------------------------------
        else{
            //現在の検索条件を保持
            posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
            posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
            posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
            posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
            posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());
        }
        
        // 店舗選択画面に移動
        return PosNipoRefConstants.VIEW_ID_MISESEARCH;
    }

    /**
     * オーナー検索
     */
    public String ownerSearch() {
        //初期化
        ownerSearchDto.clear();
        ownerSearchDto.setInitFlag(true);

        // 会社コードリスト取得
        List companyCdList = new ArrayList();
        companyCdList.add(posNipoRequestDto.getCompanyCd());

        // オーナー選択Dtoにセット
        ownerSearchDto.setRCompanyCdList(companyCdList);
        ownerSearchDto.setNavigationCase(PosNipoRefConstants.VIEW_ID_POSNIPO);
        ownerSearchDto.setNeedReturnKind(true);
        ownerSearchDto.setWindowId(posNipoRequestDto.getWindowId());

        int windowId  = posNipoRequestDto.getWindowId();
        boolean existDataFlg = posNipoRequestDto.isExistDataFlg();
        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();

        //-----------------------------------
        // 検索結果データが存在する時
        //-----------------------------------
        if(existDataFlg){
        
            //現在の検索条件を保持
            posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
            posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
            posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
            posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
            posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());

            //前回検索条件を保持
            posNipoSessionDto.setCompanyCdZen(windowId, posNipoRequestDto.getCompanyCdZen());
            posNipoSessionDto.setKikanZen(windowId, posNipoRequestDto.getKikanZen());
            posNipoSessionDto.setMiseCdZen(windowId, posNipoRequestDto.getMiseCdZen());
            posNipoSessionDto.setOnerCdZen(windowId, posNipoRequestDto.getOnerCdZen());
            posNipoSessionDto.setKbnZen(windowId, posNipoRequestDto.getKbnZen());
            
            //既存データを保持しているか
            if(!posNipoSessionDto.containsWindowId(windowId)){
                //前回検索条件を取得
                String companyCdZen = posNipoRequestDto.getCompanyCdZen();
                String kikanZen     = posNipoRequestDto.getKikanZen();
                String miseCdZen    = posNipoRequestDto.getMiseCdZen();
                String onerCdZen    = posNipoRequestDto.getOnerCdZen();
                String kbnZen       = posNipoRequestDto.getKbnZen();

                //検索
                search(windowId, companyCdZen, kikanZen, miseCdZen, onerCdZen, kbnZen, userId, limitFlg);
            }
        }
        //-----------------------------------
        // 検索結果データが存在しない時
        //-----------------------------------
        else{
            //現在の検索条件を保持
            posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
            posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
            posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
            posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
            posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());
        }

        // オーナー選択画面へ遷移
        return PosNipoRefConstants.VIEW_ID_OWNERSEARCH;
    }
    
    /**
     * 実行（検索処理）
     */
    public String execute() {

        int windowId     = posNipoRequestDto.getWindowId();
        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();
        String userType   = posNipoSessionDto.getUserTypeCd();

        //現状の検索条件をSessionDtoに退避
        posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
        posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
        posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
        posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
        posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());
        
        //--------------
        // 検索条件取得
        //--------------
        String companyCd = null;
        String kikan     = null;
        String miseCd    = null;
        String onerCd    = null;
        String kbn       = null;
        if(userType.equals(PosNipoRefConstants.USERTYPE_HONBU)){
            companyCd = posNipoRequestDto.getCompanyCd();
            kikan     = posNipoRequestDto.getKikan();
            miseCd    = posNipoRequestDto.getMiseCd();
            onerCd    = posNipoRequestDto.getOnerCd();
            kbn       = posNipoRequestDto.getKbn();
        }else if(userType.equals(PosNipoRefConstants.USERTYPE_ONER)){
            companyCd = posNipoRequestDto.getCompanyCd();
            kikan     = posNipoRequestDto.getKikan();
            miseCd    = posNipoRequestDto.getMiseCd();
            kbn       = posNipoRequestDto.getKbn();

            //オーナーコードを取得
            Map onerMap = posNipoSessionDto.getOnerMap();
            if(onerMap != null){
                onerCd = (String)onerMap.get(companyCd);
            }
        }else if(userType.equals(PosNipoRefConstants.USERTYPE_MISE)){
            companyCd = posNipoSessionDto.getCompanyCdTenpoUser();
            kikan     = posNipoRequestDto.getKikan();
            miseCd    = posNipoSessionDto.getMiseCdTenpoUser();
            onerCd    = posNipoRequestDto.getOnerCd();
            kbn       = PosNipoRefConstants.KBN_MISE;
        }

        //--------------
        // 入力チェック
        //--------------
        validate(miseCd, onerCd, kbn);

        //-----------------------------
        // 店舗・オーナー存在チェック
        //-----------------------------
        if(userType.equals(PosNipoRefConstants.USERTYPE_HONBU)){
            if(kbn.equals(PosNipoRefConstants.KBN_MISE)){
                checkExistMise(companyCd, miseCd);
            }else if(kbn.equals(PosNipoRefConstants.KBN_MISE)){
                checkExistOner(companyCd, onerCd);
            }
        }

        //--------------
        // 検索
        //--------------
        search(windowId, companyCd, kikan, miseCd, onerCd, kbn, userId, limitFlg);

        //自画面に遷移
        return PosNipoRefConstants.VIEW_ID_POSNIPO;
    }

    /**
     * 表示タブを切替
     */
    public String changeTab() {

        int windowId = posNipoRequestDto.getWindowId();
        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();

        //現状の検索条件をSessionDtoに退避
        posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
        posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
        posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
        posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
        posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());

        int tabIndex = posNipoRequestDto.getTabIndex();     //タブインデックス(メイン)
        int subIndex = posNipoRequestDto.getSubTabIndex();  //タブインデックス(サブ)

        //既存データを保持しているか
        boolean flg = posNipoSessionDto.containsWindowId(windowId);

        //---------------------------
        // 既にデータを保持している時
        //---------------------------
        if(posNipoRequestDto.isExistDataFlg() && flg){
            //表示タブインデックスをセット
            posNipoSessionDto.setTabIndex(windowId, tabIndex);
            posNipoSessionDto.setSubTabIndex(windowId, subIndex);
        }
        //---------------------------
        // データを保持していない時
        //---------------------------
        else{
            //前回検索条件を取得
            String companyCdZen = posNipoRequestDto.getCompanyCdZen();
            String kikanZen     = posNipoRequestDto.getKikanZen();
            String miseCdZen    = posNipoRequestDto.getMiseCdZen();
            String onerCdZen    = posNipoRequestDto.getOnerCdZen();
            String kbnZen       = posNipoRequestDto.getKbnZen();

            //検索
            search(windowId, companyCdZen, kikanZen, miseCdZen, onerCdZen, kbnZen, userId, limitFlg);

            //表示タブインデックスをセット
            posNipoSessionDto.setTabIndex(windowId, tabIndex);
            posNipoSessionDto.setSubTabIndex(windowId, subIndex);
        }

        //自画面へ遷移
        return null;
    }

    /**
     * 会社プルダウンの値が変更された時の処理
     * 選択された会社コードに対応する条件リストを取得し、セットしなおす
     */
    public String changeCompany() {
        
        int windowId  = posNipoRequestDto.getWindowId();
        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();
        String companyCd = posNipoRequestDto.getCompanyCd();

        //現状の検索条件をSessionDtoに退避
        posNipoSessionDto.setCompanyCd(windowId, posNipoRequestDto.getCompanyCd());
        posNipoSessionDto.setKikan(windowId, posNipoRequestDto.getKikan());
        posNipoSessionDto.setMiseCd(windowId, posNipoRequestDto.getMiseCd());
        posNipoSessionDto.setOnerCd(windowId, posNipoRequestDto.getOnerCd());
        posNipoSessionDto.setKbn(windowId, posNipoRequestDto.getKbn());

        //店舗リストを再取得
        Map map   = posNipoSessionDto.getMiseListMap();
        List miseList = (List)map.get(companyCd);
        
        //選択している店コードをセット(リストの先頭)
        String miseCd = null;
        if(miseList != null && miseList.size() >0){
            TenpoInfo mise = (TenpoInfo)miseList.get(0);
            miseCd = mise.getMiseCd();
        }

        //会社コードに対応するオーナーコードを取得
        Map onerMap = posNipoSessionDto.getOnerMap();
        String onerCd = (String)onerMap.get(companyCd);

        //DTOにセット
        posNipoSessionDto.setMiseList(windowId, miseList);
        posNipoSessionDto.setMiseCd(windowId, miseCd);
        posNipoSessionDto.setOnerCd(windowId, onerCd);

        //-----------------------------------
        // 検索結果データが存在する時
        //-----------------------------------
        if(posNipoRequestDto.isExistDataFlg()){

            //既存データを保持しているか
            if(!posNipoSessionDto.containsWindowId(windowId)){
                //前回検索条件を取得
                String companyCdZen = posNipoRequestDto.getCompanyCdZen();
                String kikanZen     = posNipoRequestDto.getKikanZen();
                String miseCdZen    = posNipoRequestDto.getMiseCdZen();
                String onerCdZen    = posNipoRequestDto.getOnerCdZen();
                String kbnZen       = posNipoRequestDto.getKbnZen();

                //再検索
                search(windowId, companyCdZen, kikanZen, miseCdZen, onerCdZen, kbnZen, userId, limitFlg);
            }
        }
        
        //自画面へ遷移
        return null;
    }

    /**
     * CSVダウンロード
     */
    public String downloadCsv() {

        int windowId = posNipoRequestDto.getWindowId();
        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();
     
        //既存データを保持しているか
        boolean flg = posNipoSessionDto.containsWindowId(windowId);

        //---------------------------
        //既にデータを保持している時
        //---------------------------
        if(flg){
            //表示用DTOにセット
            copySessionToRequest();
        }
        //---------------------------
        //データを保持していない時
        //---------------------------
        else{
            //前回検索条件を取得
            String companyCdZen = posNipoRequestDto.getCompanyCdZen();
            String kikanZen     = posNipoRequestDto.getKikanZen();
            String miseCdZen    = posNipoRequestDto.getMiseCdZen();
            String onerCdZen    = posNipoRequestDto.getOnerCdZen();
            String kbnZen       = posNipoRequestDto.getKbnZen();

            search(windowId, companyCdZen, kikanZen, miseCdZen, onerCdZen, kbnZen, userId, limitFlg);

            //表示用DTOにセット
            copySessionToRequest();
        }

        //ロジック実行
        try {
        	//SessionDtoに表示WindowIDをセット
        	posNipoSessionDto.setWindowId(windowId);
        	posNipoRefCSVAction.downloadCsv();
        } catch (Exception e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        
        return null;
    }
    
    /**
     * 指定した検索条件にて検索処理を行い、SessionDTOにwindowIdをキーに格納する。
     * @param windowId
     * @param sessionDto
     * @param companyCd
     * @param kikan
     * @param miseCd
     * @param onerCd
     * @param kbn
     */
    private void search(int windowId, String companyCd,String kikan, String miseCd, String onerCd, String kbn,
            String userId, boolean limitFlg) {

        String userType = posNipoSessionDto.getUserTypeCd();
        miseCd = fomatCode(miseCd);
        onerCd = fomatCode(onerCd);

        boolean existData = false;
        
        //------------------------------
        // ロジック【店舗数取得】実行。
        //------------------------------
        int tenpoCnt = posNipoTenpoCntLogic.execute(companyCd, kikan, miseCd, onerCd, kbn, userId, limitFlg);
        if(tenpoCnt > 0){
            existData = true;
        }

        //---------------------------------
        // ロジック【POS日報情報取得】実行。 
        //---------------------------------
        List nipoList = null;
        if(existData){
            nipoList = posNipoInfoLogic.execute(companyCd, kikan, miseCd, onerCd, kbn, userId, limitFlg);
            if(nipoList == null || nipoList.size()==0){
                existData = false;
            }
        }

        //------------------------------
        // データが取得できた時
        //------------------------------
        if(existData){

            posNipoSessionDto.setExistDataFlg(windowId, true);                                  //データ存在フラグ
            posNipoSessionDto.setTenpoCnt(windowId, tenpoCnt);                                  //店舗数
            posNipoSessionDto.setPosNipoList(windowId, nipoList);                               //日報リスト
            posNipoSessionDto.setTabIndex(windowId, PosNipoRefConstants.TAB_INDEX_NIPO);        //初期表示タブインデックス
            posNipoSessionDto.setSubTabIndex(windowId, PosNipoRefConstants.SUB_TAB_INDEX_JITU); //初期表示サブタブ
            posNipoSessionDto.setResearchFlg(windowId, true);

            posNipoSessionDto.setCompanyCdZen(windowId, companyCd);
            posNipoSessionDto.setKikanZen(windowId, kikan);
            posNipoSessionDto.setMiseCdZen(windowId, miseCd);
            posNipoSessionDto.setOnerCdZen(windowId, onerCd);
            posNipoSessionDto.setKbnZen(windowId, kbn);

            //確認表示部分
            if(userType != null && userType.equals(PosNipoRefConstants.USERTYPE_HONBU)){
                //本部ユーザの時
                setDispStrHonbu(windowId, companyCd, kikan, kbn);
            }else if(userType != null && userType.equals(PosNipoRefConstants.USERTYPE_ONER)){
                //オーナーユーザ時
                setDispStrOner(windowId, companyCd, kikan, kbn, miseCd);
            }else if(userType != null && userType.equals(PosNipoRefConstants.USERTYPE_MISE)){
                //店舗ユーザ時
                setDispStrMise(windowId, kikan);
            }
        }
        //------------------------------
        // データが取得できなかった時
        //------------------------------
        else{
            //結果クリア
            posNipoSessionDto.resultClear(windowId);

            //エラーメッセージ
            throw new NotExistException("該当データ");
        }
    }

    /**
     * 本部ユーザー時、確認部分の表示用文字列作成
     */
    private void setDispStrHonbu(int windowId, String companyCd, String kikan, String kbn){

        //表示用会社名称
        List companyList = posNipoSessionDto.getCompanyList();
        for(int i=0; i<companyList.size(); i++){

            MstUserCompany comp = (MstUserCompany)companyList.get(i);
            if(companyCd.equals(comp.getCompanyCd())){
                posNipoSessionDto.setDispCompanyName(windowId, comp.getCompanyName());  //DTOに会社名をセット
                break;
            }
        }
        
        //表示用期間
        DateFormatter format = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        String dispKikan = format.format(kikan, DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM);
        posNipoSessionDto.setDispKikan(windowId, dispKikan);

        //店舗名称
        if(PosNipoRefConstants.KBN_MISE.equals(kbn)){
            List list = posNipoSessionDto.getPosNipoList(windowId);
            PosNipoInfo info = (PosNipoInfo)list.get(0);
            posNipoSessionDto.setDispMiseName(windowId, info.getMiseCd() + " " + info.getMiseName());
        }else{
            posNipoSessionDto.setDispMiseName(windowId, "全店");
        }

        //取得店舗数
        int cnt = posNipoSessionDto.getTenpoCnt(windowId);
        String dispTenpoCnt = Integer.toString(cnt) + " 店";
        posNipoSessionDto.setDispTenpoCnt(windowId, dispTenpoCnt);
    }

    /**
     * オーナーユーザー時、確認部分の表示用文字列作成
     */
    private void setDispStrOner(int windowId, String companyCd, String kikan, String kbn, String miseCd){

        //表示用会社名称
        List companyList = posNipoSessionDto.getCompanyList();
        for(int i=0; i<companyList.size(); i++){

            MstUserCompany comp = (MstUserCompany)companyList.get(i);
            if(companyCd.equals(comp.getCompanyCd())){
                posNipoSessionDto.setDispCompanyName(windowId, comp.getCompanyName());  //DTOに会社名をセット
                break;
            }
        }

        //表示用期間
        DateFormatter format = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        String dispKikan = format.format(kikan, DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM);
        posNipoSessionDto.setDispKikan(windowId, dispKikan);

        //店舗名称
        if(PosNipoRefConstants.KBN_MISE.equals(kbn)){
            Map map = posNipoSessionDto.getMiseListMap();
            List miseList = (List)map.get(companyCd);
            for(int i=0; i<miseList.size(); i++){
                TenpoInfo info = (TenpoInfo)miseList.get(i);
                
                if(miseCd.equals(info.getMiseCd())){
                    posNipoSessionDto.setDispMiseName(windowId, info.getMiseNameAddCd());
                    break;
                }
            }
        }else{
            posNipoSessionDto.setDispMiseName(windowId, "全店");
        }

        //取得店舗数
        int cnt = posNipoSessionDto.getTenpoCnt(windowId);
        String dispTenpoCnt = Integer.toString(cnt) + " 店";
        posNipoSessionDto.setDispTenpoCnt(windowId, dispTenpoCnt);
    }

    /**
     * 店舗ユーザー時、確認部分の表示用文字列作成
     */
    private void setDispStrMise(int windowId, String kikan){

        //表示用会社名称
        posNipoSessionDto.setDispCompanyName(windowId, posNipoSessionDto.getCompanyNameTenpoUser());

        //表示用期間
        DateFormatter format = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        String dispKikan = format.format(kikan, DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM);
        posNipoSessionDto.setDispKikan(windowId, dispKikan);

        //店舗名を取得
        posNipoSessionDto.setDispMiseName(windowId, posNipoSessionDto.getMiseNameTenpoUser());

        //取得店舗数
        int cnt = posNipoSessionDto.getTenpoCnt(windowId);
        String dispTenpoCnt = Integer.toString(cnt) + " 店";
        posNipoSessionDto.setDispTenpoCnt(windowId, dispTenpoCnt);
    }

    /**
     * SessionDTOより表示内容をRequestDTOにセット
     */
    private void copySessionToRequest() {

        int winId = posNipoRequestDto.getWindowId();

        posNipoRequestDto.setCompanyCd(posNipoSessionDto.getCompanyCd(winId));               //検索条件
        posNipoRequestDto.setKikan(posNipoSessionDto.getKikan(winId));                       //検索条件
        posNipoRequestDto.setMiseCd(posNipoSessionDto.getMiseCd(winId));                     //検索条件
        posNipoRequestDto.setOnerCd(posNipoSessionDto.getOnerCd(winId));                     //検索条件
        posNipoRequestDto.setKbn(posNipoSessionDto.getKbn(winId));                           //検索条件

        posNipoRequestDto.setCompanyCdZen(posNipoSessionDto.getCompanyCdZen(winId));          //前回検索条件：会社コード
        posNipoRequestDto.setKikanZen(posNipoSessionDto.getKikanZen(winId));                  //前回検索条件：期間
        posNipoRequestDto.setMiseCdZen(posNipoSessionDto.getMiseCdZen(winId));                //前回検索条件：店コード
        posNipoRequestDto.setOnerCdZen(posNipoSessionDto.getOnerCdZen(winId));                //前回検索条件：オーナコード
        posNipoRequestDto.setKbnZen(posNipoSessionDto.getKbnZen(winId));                      //前回検索条件：検索区分

        posNipoRequestDto.setDispCompanyName(posNipoSessionDto.getDispCompanyName(winId));    //表示用：会社名
        posNipoRequestDto.setDispKikan(posNipoSessionDto.getDispKikan(winId));                //表示用：期間
        posNipoRequestDto.setDispMiseName(posNipoSessionDto.getDispMiseName(winId));          //表示用：店名称
        posNipoRequestDto.setDispTenpoCnt(posNipoSessionDto.getDispTenpoCnt(winId));          //表示用：店舗数

        posNipoRequestDto.setTenpoCnt(posNipoSessionDto.getTenpoCnt(winId));                  //結果：店舗数
        posNipoRequestDto.setPosNipoList(posNipoSessionDto.getPosNipoList(winId));            //結果：POS日報リスト
        posNipoRequestDto.setTabIndex(posNipoSessionDto.getTabIndex(winId));                  //結果：初期表示タブ
        posNipoRequestDto.setSubTabIndex(posNipoSessionDto.getSubTabIndex(winId));            //結果：初期表示タブ(サブ)

        posNipoRequestDto.setExistDataFlg(posNipoSessionDto.getExistDataFlg(winId));          //データ存在フラグ
        posNipoRequestDto.setResearchFlg(posNipoSessionDto.isResearchFlg(winId));             //再検索フラグ

        if(posNipoSessionDto.getUserTypeCd().equals(PosNipoRefConstants.USERTYPE_ONER)){
            posNipoRequestDto.setMiseList(posNipoSessionDto.getMiseList(winId));
        }
    }

    /**
     * 期間プルダウンを生成する
     */
    private List getNengetsuList(String date){

        DateFormatter formatter = new DateFormatter();

        String sysMonth = formatter.format(date,
                DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        
        // 現在月から過去月分の年月リスト作成
        List listMonth = new ArrayList();
        for (int i = 0; i < PosNipoRefConstants.NENGETU_DISP_MONTH; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysMonth, i);
            } catch (Exception ex) {
                throw new FtlSystemException("初期処理", ex.toString(), ex);
            }

            SelectItem item = new SelectItem(month, formatter.format(month,
                    DateFormatter.PATTERN_MONTH_SLASH,
                    DateFormatter.DATE_TYPE_YM));
            listMonth.add(item);
        }

        return listMonth;
    }

    /**
     * 店コード、オーナーコードのフォーマット
     */
    private String fomatCode(String code) {

        if(code != null && code.length() > 0 && code.length() < 5){
            CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            return cdf.format(code, true);
        }
        return code;
    }

    /**
     * 入力チェック
     */
    private void validate(String miseCd, String onerCd, String kbn) {

        String userType = posNipoSessionDto.getUserTypeCd();

        //--------------------
        // 本部ユーザの時
        //--------------------
        if(PosNipoRefConstants.USERTYPE_HONBU.equals(userType)){

            //検索区分が「店舗」の時、店コードをチェック
            if(PosNipoRefConstants.KBN_MISE.equals(kbn)){
                //必須チェック
                if(miseCd == null || miseCd.trim().length() == 0){
                    throw new NoInputException("店コード");
                }
                
                // 半角数字
                CodeVerifier codeVerifier = new CodeVerifier();
                if (!codeVerifier.validate(miseCd)) {
                    throw new InvalidInputException("店コード", "miseText", 0);
                }
            }

            //検索区分が「オーナー」の時、オーナーコードをチェック
            if(PosNipoRefConstants.KBN_OWNER.equals(kbn)){
                //必須チェック
                if(onerCd == null || onerCd.trim().length() == 0){
                    throw new NoInputException("オーナーコード");
                }
                
                // 半角数字
                CodeVerifier codeVerifier = new CodeVerifier();
                if (!codeVerifier.validate(onerCd)) {
                    throw new InvalidInputException("オーナーコード", "ownerText", 0);
                }
            }
        }
        //--------------------
        // オーナーユーザの時
        //--------------------
        if(PosNipoRefConstants.USERTYPE_ONER.equals(userType)){

            //検索区分が「オーナー」の時、オーナーコードをチェック
            if(PosNipoRefConstants.KBN_OWNER.equals(kbn)){
                //必須チェック
                if(onerCd == null || onerCd.trim().length() == 0){
                    throw new NoInputException("オーナーコード");
                }
                
                // 半角数字
                CodeVerifier codeVerifier = new CodeVerifier();
                if (!codeVerifier.validate(onerCd)) {
                    throw new InvalidInputException("オーナーコード", "ownerText", 0);
                }
            }

            //検索区分が「店舗」の時、店コードをチェック
            if(PosNipoRefConstants.KBN_MISE.equals(kbn)){
                //必須チェック
                if(miseCd == null || miseCd.trim().length() == 0){
                    throw new NoInputException("店コード");
                }
                
                // 半角数字
                CodeVerifier codeVerifier = new CodeVerifier();
                if (!codeVerifier.validate(miseCd)) {
                    throw new InvalidInputException("店コード", "miseText", 0);
                }
            }
        }
    }

    /**
     * オーナー所属の会社コードごとの店舗リストMAPを作成
     * キー：会社コード
     * 値　：店舗リスト
     */
    private Map makeMiseListMap(String appDate){

        String userId     = posNipoSessionDto.getUserId();
        boolean limitFlg = posNipoSessionDto.isLimitFlg();
        
        //会社コードごとのオーナーコードを保持
        Map miseListMap = new HashMap();

        List ownerList = birdUserInfo.getUserOner();
        for (Iterator it = ownerList.iterator(); it.hasNext();) {
            UIUserOner uIUserOner = (UIUserOner) it.next();
            String compCd = uIUserOner.getCompanyCd();
            String onerCd = uIUserOner.getOnerCd();

            // ロジック【オーナー対応店舗取得】実行し、店舗リストを生成
            List miseList = makeMiseListByOnerLogic.execute(compCd, onerCd, appDate, userId, limitFlg);

            //マップに格納
            miseListMap.put(compCd, miseList);
        }
        return miseListMap;
    }

    /**
     * 店舗存在チェック
     */
    private void checkExistMise(String companyCd, String miseCd) {

        //店舗存在確認
        MstMise mstMise = getMiseLogic.execute(companyCd, miseCd);
        if(mstMise == null){
            throw new NotExistException("店コード");
        }
    }

    /**
     * オーナー存在チェック
     */
    private void checkExistOner(String companyCd, String onerCd) {

        //オーナー存在確認
        MstOner mstOner = getOnerLogic.execute(companyCd, onerCd);
        if(mstOner == null){
            throw new NotExistException("オーナーコード");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public GetUserCompanyLogic getGetUserCompanyLogic() {
        return getUserCompanyLogic;
    }

    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
        this.getUserCompanyLogic = getUserCompanyLogic;
    }

    public PosNipoInfoLogic getPosNipoInfoLogic() {
        return posNipoInfoLogic;
    }

    public void setPosNipoInfoLogic(PosNipoInfoLogic posNipoInfoLogic) {
        this.posNipoInfoLogic = posNipoInfoLogic;
    }

    public PosNipoTenpoCntLogic getPosNipoTenpoCntLogic() {
        return posNipoTenpoCntLogic;
    }

    public void setPosNipoTenpoCntLogic(PosNipoTenpoCntLogic posNipoTenpoCntLogic) {
        this.posNipoTenpoCntLogic = posNipoTenpoCntLogic;
    }

    public MakeMiseListByOnerLogic getMakeMiseListByOnerLogic() {
        return makeMiseListByOnerLogic;
    }

    public void setMakeMiseListByOnerLogic(
            MakeMiseListByOnerLogic makeMiseListByOnerLogic) {
        this.makeMiseListByOnerLogic = makeMiseListByOnerLogic;
    }

    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }

    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }

    public PosNipoRequestDto getPosNipoRequestDto() {
        return posNipoRequestDto;
    }

    public void setPosNipoRequestDto(PosNipoRequestDto posNipoRequestDto) {
        this.posNipoRequestDto = posNipoRequestDto;
    }

    public PosNipoSessionDto getPosNipoSessionDto() {
        return posNipoSessionDto;
    }

    public void setPosNipoSessionDto(PosNipoSessionDto posNipoSessionDto) {
        this.posNipoSessionDto = posNipoSessionDto;
    }

    public CsvOutputAction getPosNipoRefCSVAction() {
        return posNipoRefCSVAction;
    }

    public void setPosNipoRefCSVAction(CsvOutputAction posNipoRefCSVAction) {
        this.posNipoRefCSVAction = posNipoRefCSVAction;
    }

    public GetOnerLogic getGetOnerLogic() {
        return getOnerLogic;
    }

    public void setGetOnerLogic(GetOnerLogic getOnerLogic) {
        this.getOnerLogic = getOnerLogic;
    }
}