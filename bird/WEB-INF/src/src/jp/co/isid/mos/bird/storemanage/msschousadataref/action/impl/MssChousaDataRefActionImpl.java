package jp.co.isid.mos.bird.storemanage.msschousadataref.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.msschousadataref.action.MssChousaDataRefAction;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.GetChousaDataLogic;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.MssFileChousaDataLogic;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.ChangeCompanyLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
public class MssChousaDataRefActionImpl implements MssChousaDataRefAction{
    /* アクションID */
    public static final String initialize_ACTION_ID = "BSM011A01";
    public static final String miseSerch_ACTION_ID =  "BSM011A02";
    public static final String onerSearch_ACTION_ID = "BSM011A03";
    public static final String execute_ACTION_ID =    "BSM011A04";

    private MssChousaDataRefDto mssChousaDataRefDto; 
    /* 店舗選択 */
    private MiseSearchDto miseSearchDto;
    /* オーナー選択 */
    private OwnerSearchDto ownerSearchDto;
    /*ファイル取得ロジック*/
    private MssFileChousaDataLogic mssFileChousaDataLogic;
    private GetChousaDataLogic getChousaDataLogicImpl;
    private ConditionLogic conditionLogic;
    private ChangeCompanyLogic mssChousaChangeCompanyLogic;
    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    private static final String ONER_USER = "02";
    private static final String TENPO_USER = "03";
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /**
     * 初期処理
     * @return 画面遷移情報
     * @throws Exception 
     */
    public String initialize() throws Exception{
        // 個店ポータルから遷移時の処理①
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        if (commonCodeDto.getUseCommonDto()) {
            getPullDownMenuDto().setClearFlg(true);
        }
        
        /* 初期化 */
        if(getPullDownMenuDto().isClearFlg()){
            mssChousaDataRefDto.clearList();
            mssChousaDataRefDto.clear();

            //Dtoへシステム日付設定
            
            getMssChousaDataRefDto().setSysDate(getMssChousaDataRefDto().getBirdDateInfo().getSysDate());
            getMssChousaDataRefDto().setUserId(getBirdUserInfo().getMstUser().getUser_id());
            List taishoNendo = new ArrayList();
            for(int nend = Integer.parseInt(DateManager.getCurrentYear(getMssChousaDataRefDto().getSysDate()));nend>=2006;nend--){
                String strNendo = String.valueOf(nend);
                SelectItem taishoSelect = new SelectItem(strNendo);
                taishoNendo.add( taishoSelect );

            }
            getMssChousaDataRefDto().setTaishoNendo(taishoNendo);
            /* 会社コードセット */
            mssChousaDataRefDto.setCompanyCd(COMPANY_CD_MOS);
            /* sessionからユーザタイプ取得 */
            mssChousaDataRefDto.setUsertypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
                    

            if(mssChousaDataRefDto.getUsertypeCd().equals(HONBU_USER)){
                mssChousaDataRefDto.setSelectFlg(1);
            // ユーザタイプオーナ(02)の場合
            }else if(mssChousaDataRefDto.getUsertypeCd().equals(ONER_USER)){
                mssChousaDataRefDto.setOnerCd(getOnerCd());
                mssChousaDataRefDto.setSelectFlg(1);
                getGetChousaDataLogicImpl().execute(mssChousaDataRefDto);
            }
            //店コードをセット(ユーザータイプ03)の場合
            if(mssChousaDataRefDto.getUsertypeCd().equals(TENPO_USER)){
                mssChousaDataRefDto.setMiseCd(getMiseCd());
                mssChousaDataRefDto.setSelectFlg(0);
                
            }            
            // 個店ポータルから遷移時の処理②
            if (commonCodeDto.getUseCommonDto()) {
                mssChousaDataRefDto.setSelectFlg(1);
                mssChousaDataRefDto.setMiseCd(commonCodeDto.getMiseCd());
            }
            getMssChousaConditionLogicImpl().execute(mssChousaDataRefDto);
            mssChousaDataRefDto.getMiseList();
            getPullDownMenuDto().setClearFlg(false);    
        }
        

        
        /* 店情報取得 */
        if (getMiseSearchDto().isActionFlg()) {
            mssChousaDataRefDto.setMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        
        /*オーナーコード取得*/
        if(getOwnerSearchDto().isActionFlag()){
            mssChousaDataRefDto.setOnerCd(getOwnerSearchDto().getOnerCd());
            getOwnerSearchDto().setActionFlag(false);
        }
        if(mssChousaDataRefDto.getUsertypeCd().equals(ONER_USER)){
            getMssChousaChangeCompanyLogic().execute(mssChousaDataRefDto);
        }

        return null;
    }

    /* オーナー選択処理 */
    public String onerSearch(){
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setInitFlag(true);
        List listCompany = new ArrayList();
        listCompany.add(getMssChousaDataRefDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        getOwnerSearchDto().setNavigationCase(mssChousaDataRefDto.condition_VIEW_ID);
        return mssChousaDataRefDto.onerSearch_VIEW_ID;
    }
    
    /* 店舗選択処理 */
    public String miseSrearch(){
        getMiseSearchDto().setActionFlg(true);
        getMiseSearchDto().setInitialFlag(true);
        List listCompany = new ArrayList();
        listCompany.add(getMssChousaDataRefDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        getMiseSearchDto().setNavigationCase(mssChousaDataRefDto.condition_VIEW_ID);
        return mssChousaDataRefDto.miseSearch_VIEW_ID;
    }
    /* Sessionのユーザ対応オーナー.管理会社企業コード='00'のオーナーコード */
    private String getOnerCd(){
        String s_onerCd = null;
        for (Iterator it = getBirdUserInfo().getUserOner().iterator(); it.hasNext();) {
            UIUserOner uiUserOner = (UIUserOner) it.next();
            if (uiUserOner.getCompanyCd().equals(COMPANY_CD_MOS)) {
                s_onerCd = uiUserOner.getOnerCd();
            }
        }
        return s_onerCd;
    }
    /* Sessionのユーザ対応オーナー.ユーザーコード='03'の店コード */
    private String getMiseCd(){
        String s_miseCd = null;
        for (Iterator it = getBirdUserInfo().getUserMise().iterator(); it.hasNext();) {
            UIUserMise uiUseMise = (UIUserMise) it.next();
            if (uiUseMise.getCompanyCd().equals(COMPANY_CD_MOS)) {
                s_miseCd = uiUseMise.getMiseCd();
            }
        }
        return s_miseCd;
    }
    /* 実行 */
    public String execute(){
        mssChousaDataRefDto.clearList();
       
        getGetChousaDataLogicImpl().execute(mssChousaDataRefDto);
        getMssFileChousaDataLogicImpl().execute(mssChousaDataRefDto);
        
        return null;
    }
    /**
     * 会社プルダウン変更処理
     * 
     * 1.対象条件プルダウンリスト内容変更
     * 2.表示対象プルダウンリスト内容変更
     */
    public String changeCompany()  throws Exception {
        // 条件画面-->条件画面の遷移区分を立てる
        /* ロジック【条件画面出力データ検索】*/
        getMssChousaChangeCompanyLogic().execute(getMssChousaDataRefDto());
        return null;
    }

    
    /**
     * @return miseSearchDto を戻します。
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * @param miseSearchDto miseSearchDto を設定。
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
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
     * @return mssChousaDataRefDto を戻します。
     */
    public MssChousaDataRefDto getMssChousaDataRefDto() {
        return mssChousaDataRefDto;
    }
    /**
     * @param mssChousaDataRefDto mssChousaDataRefDto を設定。
     */
    public void setMssChousaDataRefDto(MssChousaDataRefDto mssChousaDataRefDto) {
        this.mssChousaDataRefDto = mssChousaDataRefDto;
    }
    /**
     * pullDownMenuDtoの設定
     * @return pullDownMenuDto を戻します。
     */
    private PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    /**
     * S2Container処理
     * @return CommonCodeDto
     */
    private CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }
    /**
     * mssFileChousaDataLogicImplを取得
     * @return mssFileChousaDataLogicImpl
     */
    public MssFileChousaDataLogic getMssFileChousaDataLogicImpl() {
        return mssFileChousaDataLogic;
    }
    /**
     * mssFileChousaDataLogicImplをセット
     * @param mssFileChousaDataLogicImpl
     */
    public void setMssFileChousaDataLogicImpl(
            MssFileChousaDataLogic mssFileChousaDataLogic) {
        this.mssFileChousaDataLogic = mssFileChousaDataLogic;
    }

    public GetChousaDataLogic getGetChousaDataLogicImpl() {
        return getChousaDataLogicImpl;
    }

    public void setGetChousaDataLogicImpl(
            GetChousaDataLogic getChousaDataLogicImpl) {
        this.getChousaDataLogicImpl = getChousaDataLogicImpl;
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    public BirdUserInfo getBirdUserInfo() {
      return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
  }

    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public ConditionLogic getMssChousaConditionLogicImpl() {
        return conditionLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setMssChousaConditionLogicImpl(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }

    public ChangeCompanyLogic getMssChousaChangeCompanyLogic() {
        return mssChousaChangeCompanyLogic;
    }

    public void setMssChousaChangeCompanyLogic(
            ChangeCompanyLogic mssChousaChangeCompanyLogic) {
        this.mssChousaChangeCompanyLogic = mssChousaChangeCompanyLogic;
    }

}
