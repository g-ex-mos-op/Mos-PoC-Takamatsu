/*
 * 作成日:2007/01/19
 */
package jp.co.isid.mos.bird.entry.ownerchange.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.entry.ownerchange.action.OwnerChangeConditionAction;
import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dto.OwnerChangeDto;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstOwnerInfo;
import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckInputLogic;
import jp.co.isid.mos.bird.entry.ownerchange.logic.GetOwnerInfoLogic;
import jp.co.isid.mos.bird.entry.ownerchange.logic.GetStaffInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * オーナー間異動 条件画面アクション
 * @author xkonishi
 *
 */
public class OwnerChangeConditionActionImpl implements
        OwnerChangeConditionAction {

    /**
     * アクションID定義
     */
    public static final String initialize_ACTION_ID = "BEN023A01";
    
    public static final String miseSearch_ACTION_ID = "BEN023A02";
    
    public static final String onerSearch_ACTION_ID = "BEN023A03";
    
    public static final String execute_ACTION_ID = "BEN023A04";
    
    /**
     * オーナー間異動Dto
     */
    private OwnerChangeDto ownerChangeDto;
    
    /**
     * オーナー情報取得ロジック
     */
    private GetOwnerInfoLogic getOwnerInfoLogic;
    
    /**
     * 入力値チェックロジック
     */
    private CheckInputLogic checkInputLogic;
    
    /**
     * スタッフ情報取得ロジック
     */
    private GetStaffInfoLogic getStaffInfoLogic;
    
    /**
     * プルダウンメニューDto
     */
    private PullDownMenuDto pullDownMenuDto;
    
    /**
     * ユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 店舗選択Dto
     */
    private MiseSearchDto miseSearchDto;
    
    /**
     * オーナー選択Dto
     */
    private OwnerSearchDto ownerSearchDto;
    
    /**
     * ユーザー所属会社取得ロジック
     */
    private GetUserCompanyLogic getUserCompanyLogic;
    
    /**
     * セッションキー作成クラス
     */
    private MakeSessionKey makeSessionKey = new MakeSessionKey();
    
    
    /**
     * 初期処理
     */
    public String initialize() {
        // 画面遷移情報
        String viewIdInfo = null;
        
        // メニュー画面から遷移してきた場合
        if(pullDownMenuDto.isClearFlg()){
            
            pullDownMenuDto.setClearFlg(false);
            
            // ウインドウID生成。
            ownerChangeDto.updateWindowid();
            
            // セッションキーを生成し、DTOにセット。
            String sessionKey = makeSessionKey._makeSessionKey();
            ownerChangeDto.setSessionKey(sessionKey);
            ownerChangeDto.setNowSessionKey(sessionKey);
            
            // 共通情報取得
            S2Container container = SingletonS2ContainerFactory.getContainer();
            setBirdUserInfo((BirdUserInfo) container.getComponent(BirdUserInfo.class));
            setBirdDateInfo((BirdDateInfo) container.getComponent(BirdDateInfo.class));
            

            // 画面操作しているユーザーのユーザーID取得。
            String userId = birdUserInfo.getUserID();
            
            // ロジック【ユーザー所属会社取得】実行し、結果をDTOにセット。
            ownerChangeDto.setCompanyList(getUserCompanyLogic.execute(userId, null));
            
            // 条件区分を0(店舗)にセット。
            ownerChangeDto.setKbn(OwnerChangeConstants.KBN_MISE);
            
            // 初期化
            ownerChangeDto.setCompanyCd(null);
            ownerChangeDto.setOnerCd(null);
            ownerChangeDto.setMiseCd(null);
        
        // 店検索画面から遷移してきた場合
        } else if(miseSearchDto.getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
            miseSearchDto.setReturnKind(MiseSearchDto.RETURNKIND_INIT);

            // ウインドウID反映
            ownerChangeDto.setWindowId(miseSearchDto.getWindowId());

            // セッションキーチェック
            if(!isValidSessionKey()) {
                miseSearchDto.setActionFlg(false);
                return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
            }
            
            // 店検索画面で、店舗を決定した場合
            if (miseSearchDto.isActionFlg()) {
                miseSearchDto.setActionFlg(false);

                // 取得した店コードをDTOにセット。
                ownerChangeDto.setMiseCd(miseSearchDto.getMiseCd());
                
                // 条件区分を0(店舗)にセットする。
                ownerChangeDto.setKbn(OwnerChangeConstants.KBN_MISE);
                
                // ロジック【入力値チェック】実行。
                checkInputLogic.execute(ownerChangeDto);
                
                // ロジック【オーナー情報取得】実行。
                 MstOwnerInfo onerInfo = getOwnerInfoLogic.execute(ownerChangeDto.getCompanyCd(), 
                                                                   ownerChangeDto.getKbn(),
                                                                   ownerChangeDto.getMiseCd(),
                                                                   ownerChangeDto.getOnerCd());
           
                // オーナーコード、オーナー名称をDtoにセット
                ownerChangeDto.setDispOnerCd(onerInfo.getOnerCd());
                ownerChangeDto.setOnerNameKj(onerInfo.getOnerNameKj());
                
                try {
                    // ロジック【スタッフ情報取得】実行し、結果をDtoにセット。
                    ownerChangeDto.setStaffInfoList(getStaffInfoLogic.execute(ownerChangeDto.getCompanyCd(),
                                                                              ownerChangeDto.getKbn(),
                                                                              ownerChangeDto.getMiseCd(),
                                                                              ownerChangeDto.getOnerCd(),
                                                                              birdDateInfo.getSysDate()));
                
                } catch(NotExistException e) {
                    ownerChangeDto.setMiseCd(null);
                    throw e;
                }
                // 会社名を取得し、DTOにセット。
                ownerChangeDto.setCompanyName(SearchCompanyName(ownerChangeDto.getCompanyCd()));
                
                // 画面遷移情報をセット
                viewIdInfo = OwnerChangeConstants.VIEW_ID_SETUP;
            }

        // オーナー検索画面から遷移してきた場合
        } else if(ownerSearchDto.getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
            ownerSearchDto.setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
            
            // ウインドウID反映
            ownerChangeDto.setWindowId(ownerSearchDto.getWindowId());

            // セッションキーチェック
            if(!isValidSessionKey()) {
                ownerSearchDto.setActionFlag(false);
                return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
            }

            // オーナー検索画面で、オーナーを決定した場合
            if (ownerSearchDto.isActionFlag()) {
                ownerSearchDto.setActionFlag(false);
                
                // 取得したオーナーコードをDTOにセット。
                ownerChangeDto.setOnerCd(ownerSearchDto.getOnerCd());
                ownerChangeDto.setDispOnerCd(ownerSearchDto.getOnerCd());
                
                // オーナー名称をDtoにセット。
                ownerChangeDto.setOnerNameKj(ownerSearchDto.getOnerNameKj());

                // 条件区分を1(オーナー)にセットする。
                ownerChangeDto.setKbn(OwnerChangeConstants.KBN_OWNER);
                
                // ロジック【入力値チェック】実行。 
                checkInputLogic.execute(ownerChangeDto);
                
                try {
                    // ロジック【スタッフ情報取得】実行し、結果をDtoにセット。
                    ownerChangeDto.setStaffInfoList(getStaffInfoLogic.execute(ownerChangeDto.getCompanyCd(),
                                                                              ownerChangeDto.getKbn(),
                                                                              ownerChangeDto.getMiseCd(),
                                                                              ownerChangeDto.getOnerCd(),
                                                                              birdDateInfo.getSysDate()));
                
                } catch(NotExistException e) {
                    ownerChangeDto.setOnerCd(null);
                    throw e;
                }
                
                // 会社名を取得し、DTOにセット。
                ownerChangeDto.setCompanyName(SearchCompanyName(ownerChangeDto.getCompanyCd()));
                
                // 画面遷移情報をセット
                viewIdInfo = OwnerChangeConstants.VIEW_ID_SETUP;
            }
        }
        return viewIdInfo;
    }

    /**
     * 店検索
     */
    public String miseSearch() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        // 初期化
        miseSearchDto.clear();
        miseSearchDto.setInitialFlag(true);

        // 会社コードリスト取得。
        List companyCdList = new ArrayList();
        companyCdList.add(ownerChangeDto.getCompanyCd());
        
        // 店舗選択Dtoにセット。
        miseSearchDto.setRCompanyCdList(companyCdList);
        miseSearchDto.setNavigationCase(OwnerChangeConstants.VIEW_ID_CONDITION);
        miseSearchDto.setNeedReturnKind(true);
        miseSearchDto.setWindowId(ownerChangeDto.getWindowId());

        // 店舗選択画面に移動
        return OwnerChangeConstants.VIEW_ID_MISESEARCH;
    }

    /**
     * オーナー検索
     */
    public String ownerSearch() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        // 初期化
        ownerSearchDto.clear();
        ownerSearchDto.setInitFlag(true);
        
        // 会社コードリスト取得
        List companyCdList = new ArrayList();
        companyCdList.add(ownerChangeDto.getCompanyCd());
        
        // オーナー選択Dtoにセット
        ownerSearchDto.setRCompanyCdList(companyCdList);
        ownerSearchDto.setNavigationCase(OwnerChangeConstants.VIEW_ID_CONDITION);
        ownerSearchDto.setNeedReturnKind(true);
        ownerSearchDto.setWindowId(ownerChangeDto.getWindowId());
        
        // オーナー選択画面へ遷移
        return OwnerChangeConstants.VIEW_ID_OWNERSEARCH;
    }

    /**
     * 実行
     */
    public String execute() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        // ロジック【入力値チェック】実行。 
        checkInputLogic.execute(ownerChangeDto);
         
        // 条件区分が1(オーナー)の場合
        if(ownerChangeDto.getKbn().equals(OwnerChangeConstants.KBN_OWNER)) {
             ownerChangeDto.setDispOnerCd(ownerChangeDto.getOnerCd());
         }
         
        // ロジック【オーナー情報取得】実行し、結果を取得。
        MstOwnerInfo onerInfo = getOwnerInfoLogic.execute(ownerChangeDto.getCompanyCd(), 
                                                          ownerChangeDto.getKbn(),
                                                          ownerChangeDto.getMiseCd(),
                                                          ownerChangeDto.getOnerCd());
        
        // 条件区分が0(店舗)の場合
        if(ownerChangeDto.getKbn().equals(OwnerChangeConstants.KBN_MISE)) {
            // DTOにオーナーコード、オーナー名をセット。
            ownerChangeDto.setDispOnerCd(onerInfo.getOnerCd());
            ownerChangeDto.setOnerNameKj(onerInfo.getOnerNameKj());
        // 条件区分がオーナーの場合    
        } else if(ownerChangeDto.getKbn().equals(OwnerChangeConstants.KBN_OWNER)) {
            // DTOにオーナー名をセット
            ownerChangeDto.setOnerNameKj(onerInfo.getOnerNameKj());

        }
        
        // ロジック【スタッフ情報取得】実行し、結果をDtoにセット。
        ownerChangeDto.setStaffInfoList(getStaffInfoLogic.execute(ownerChangeDto.getCompanyCd(), 
                                                                  ownerChangeDto.getKbn(),
                                                                  ownerChangeDto.getMiseCd(),
                                                                  ownerChangeDto.getOnerCd(),
                                                                  birdDateInfo.getSysDate()));
        // 会社名を取得し、DTOにセット。
        ownerChangeDto.setCompanyName(SearchCompanyName(ownerChangeDto.getCompanyCd()));
        
        // 対象者設定画面に遷移。
        return OwnerChangeConstants.VIEW_ID_SETUP;
    }
    
    /**
     * 会社名取得
     * @param 会社コード
     * @return 会社名
     */
    private String SearchCompanyName(String companyCd) {
        List companyList = ownerChangeDto.getCompanyList();
        String companyName = null;

        for (int i = 0; i < companyList.size(); i++) {
            MstUserCompany mstUserCompany = (MstUserCompany) companyList.get(i);
            if(mstUserCompany.getCompanyCd().equals(companyCd)){
                companyName = mstUserCompany.getCompanyName();
                break;
            }
        }
        return companyName;
    }
    
    /**
     * セッションキーチェック
     * @return セッションキーチェック結果
     */
    private boolean isValidSessionKey(){
         boolean result = makeSessionKey.isValidSessionKey(
                 ownerChangeDto.getNowSessionKey(), ownerChangeDto.getSessionKey());
         
         return result;
    }
    
    public CheckInputLogic getCheckInputLogic() {
        return checkInputLogic;
    }

    public void setCheckInputLogic(CheckInputLogic checkInputLogic) {
        this.checkInputLogic = checkInputLogic;
    }

    public GetOwnerInfoLogic getGetOwnerInfoLogic() {
        return getOwnerInfoLogic;
    }

    public void setGetOwnerInfoLogic(GetOwnerInfoLogic getOwnerInfoLogic) {
        this.getOwnerInfoLogic = getOwnerInfoLogic;
    }

    public GetStaffInfoLogic getGetStaffInfoLogic() {
        return getStaffInfoLogic;
    }

    public void setGetStaffInfoLogic(GetStaffInfoLogic getStaffInfoLogic) {
        this.getStaffInfoLogic = getStaffInfoLogic;
    }

    public OwnerChangeDto getOwnerChangeDto() {
        return ownerChangeDto;
    }

    public void setOwnerChangeDto(OwnerChangeDto ownerChangeDto) {
        this.ownerChangeDto = ownerChangeDto;
    }

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
}