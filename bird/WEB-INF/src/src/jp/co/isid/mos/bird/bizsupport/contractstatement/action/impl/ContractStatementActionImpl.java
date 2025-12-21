/*
 * 作成日2007/07/05
 */
package jp.co.isid.mos.bird.bizsupport.contractstatement.action.impl;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.contractstatement.action.ContractStatementAction;
import jp.co.isid.mos.bird.bizsupport.contractstatement.dto.ContractStatementDto;
import jp.co.isid.mos.bird.bizsupport.contractstatement.logic.GetKeisanshoListLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
/**
 * 事業計画予算上期売上置換 アクション
 * @author xnkusama
 *
 */
public class ContractStatementActionImpl implements ContractStatementAction {

    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = "BBS030A01";
    

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private String userTypeCdOner;
    private ContractStatementDto contractStatementDto;
    private GetKeisanshoListLogic contractStatementLogic;
    /**
     * 初期処理
     * 
     */
    public String initialize() {
        // ユーザータイプ：オーナーの場合は、会社コードとオーナーコードをセット
        if (getUserTypeCdOner().equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
            UIUserOner uiUserOner = (UIUserOner) getBirdUserInfo().getUserOner().get(0);
            getContractStatementDto().setCompanyCd(uiUserOner.getCompanyCd());
            getContractStatementDto().setOnerCd(uiUserOner.getOnerCd());
        }
        else if ((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
            getContractStatementDto().setCompanyCd( getCommonCodeDto().getCompanyCd());
            getContractStatementDto().setOnerCd( getCommonCodeDto().getOnerCd());
        }
        else {
            getContractStatementDto().setCompanyCd(null);
            getContractStatementDto().setOnerCd(null);
        }
        // 検索処理
        getContractStatementLogic()
                            .execute(getContractStatementDto(), getBirdDateInfo().getSysDate());
        
        return null;
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
    public ContractStatementDto getContractStatementDto() {
        return contractStatementDto;
    }

    public void setContractStatementDto(ContractStatementDto contractStatementDto) {
        this.contractStatementDto = contractStatementDto;
    }

    public String getUserTypeCdOner() {
        return userTypeCdOner;
    }

    public void setUserTypeCdOner(String userTypeCdOner) {
        this.userTypeCdOner = userTypeCdOner;
    }

    public GetKeisanshoListLogic getContractStatementLogic() {
        return contractStatementLogic;
    }

    public void setContractStatementLogic(
            GetKeisanshoListLogic contractStatementLogic) {
        this.contractStatementLogic = contractStatementLogic;
    }
    /**
     * 共通コードDTO取得
     * @return 共通コードDTO
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) SingletonS2ContainerFactory.getContainer().getComponent(CommonCodeDto.class);
    }


}