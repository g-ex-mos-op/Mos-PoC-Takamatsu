package jp.co.isid.mos.bird.storemanage.anshinreport.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.action.AnshinReportAction;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ValidateDataLogic;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.AnshinReportFileDataLogic;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ChangeCompanyLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
public class AnshinReportActionImpl implements AnshinReportAction{
    /* アクションID */
    public static final String initialize_ACTION_ID = "BSM018A01";
    public static final String miseSerch_ACTION_ID =  "BSM018A02";
    public static final String onerSearch_ACTION_ID = "BSM018A03";
    public static final String execute_ACTION_ID =    "BSM018A04";

    private AnshinReportDto anshinReportDto; 
    /* 店舗選択 */
    private MiseSearchDto miseSearchDto;
    /* オーナー選択 */
    private OwnerSearchDto ownerSearchDto;
    /*ファイル取得ロジック*/
    private AnshinReportFileDataLogic anshinReportFileDataLogic;
    private ValidateDataLogic validateDataLogicImpl;
    private ConditionLogic conditionLogic;
    private ChangeCompanyLogic changeCompanyLogic;
    
    /**
     * 初期処理
     * @return 画面遷移情報
     * @throws Exception 
     */
    public String initialize() throws Exception{

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
            anshinReportDto.clearList();
            anshinReportDto.clear();

            //Dtoへシステム日付設定
            getAnshinReportDto().setSysDate(getAnshinReportDto().getBirdDateInfo().getSysDate());

            // 条件部情報取得ロジックを実行
            getConditionLogicImpl().execute(anshinReportDto,getBirdUserInfo(),getAnshinReportDto().getBirdDateInfo());
            
            getPullDownMenuDto().setClearFlg(false);    
        }
        
        /* 店情報検索画面から遷移してきた場合 */
        if (getMiseSearchDto().isActionFlg()) {
            anshinReportDto.setMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        
        /*オーナー検索画面から遷移してきた場合*/
        if(getOwnerSearchDto().isActionFlag()){
            anshinReportDto.setOnerCd(getOwnerSearchDto().getOnerCd());
            getOwnerSearchDto().setActionFlag(false);
        }

        return null;
    }

    /* オーナー選択処理 */
    public String onerSearch(){
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setInitFlag(true);
        List listCompany = new ArrayList();
        listCompany.add(getAnshinReportDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        getOwnerSearchDto().setNavigationCase(anshinReportDto.condition_VIEW_ID);
        return anshinReportDto.onerSearch_VIEW_ID;
    }
    
    /* 店舗選択処理 */
    public String miseSrearch(){
        getMiseSearchDto().setActionFlg(true);
        getMiseSearchDto().setInitialFlag(true);
        List listCompany = new ArrayList();
        listCompany.add(getAnshinReportDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        getMiseSearchDto().setNavigationCase(anshinReportDto.condition_VIEW_ID);
        return anshinReportDto.miseSearch_VIEW_ID;
    }
    /* 実行 */
    public String execute(){
        
        getValidateDataLogicImpl().execute(anshinReportDto);
        getAnshinReportFileDataLogicImpl().execute(anshinReportDto,getBirdUserInfo(),getAnshinReportDto().getBirdDateInfo());
        
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
        getChangeCompanyLogic().execute(getAnshinReportDto(), getBirdUserInfo(),getAnshinReportDto().getBirdDateInfo());
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
     * @return anshinReportDto を戻します。
     */
    public AnshinReportDto getAnshinReportDto() {
        return anshinReportDto;
    }
    /**
     * @param anshinReportDto anshinReportDto を設定。
     */
    public void setAnshinReportDto(AnshinReportDto anshinReportDto) {
        this.anshinReportDto = anshinReportDto;
    }
    /**
     * pullDownMenuDtoの設定
     * @return pullDownMenuDto を戻します。
     */
    private PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    
    /**
     * anshinReportFileDataLogicImplを取得
     * @return anshinReportFileDataLogicImpl
     */
    public AnshinReportFileDataLogic getAnshinReportFileDataLogicImpl() {
        return anshinReportFileDataLogic;
    }
    /**
     * anshinReportFileDataLogicImplをセット
     * @param anshinReportFileDataLogicImpl
     */
    public void setAnshinReportFileDataLogicImpl(
            AnshinReportFileDataLogic anshinReportFileDataLogic) {
        this.anshinReportFileDataLogic = anshinReportFileDataLogic;
    }

    public ValidateDataLogic getValidateDataLogicImpl() {
        return validateDataLogicImpl;
    }

    public void setValidateDataLogicImpl(
            ValidateDataLogic validateDataLogicImpl) {
        this.validateDataLogicImpl = validateDataLogicImpl;
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
     * S2Container処理
     * @return CommonCodeDto
     */
    private CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }
    
    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public ConditionLogic getConditionLogicImpl() {
        return conditionLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setConditionLogicImpl(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }

    public ChangeCompanyLogic getChangeCompanyLogic() {
        return changeCompanyLogic;
    }

    public void setChangeCompanyLogic(
            ChangeCompanyLogic anshinTenkenInfoChangeCompanyLogic) {
        this.changeCompanyLogic = anshinTenkenInfoChangeCompanyLogic;
    }

}
