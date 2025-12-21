/*
 * 作成日: 2006/1/31
 */
package jp.co.isid.mos.bird.inforegist.documentregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.inforegist.documentregist.action.RegistRankAction;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistConditionDto;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistRankDto;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.GetBunshoSortLogic;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.RegBunshoSortLogic;

/**
 * 文書登録 文書照会順位設定画面アクション
 * @author xnkusama
 */
public class DocumentRegistRegistRankActionImpl implements CommonAction, RegistRankAction {
    public static final String init_ACTION_ID     = "BIF003A18";
    public static final String cancel_ACTION_ID   = "BIF003A19";
    public static final String regist_ACTION_ID   = "BIF003A20";

    /* DTO */
    private DocumentRegistConditionDto documentRegistConditionDto;
    private DocumentRegistRegistRankDto documentRegistRegistRankDto;
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
//    /* Bird日付情報 */
//    private BirdDateInfo birdDateInfo;
    /* ロジック */
    // 文書照会順序を取得
    private GetBunshoSortLogic getBunshoSortLogic;
    // 文書照会順序の登録
    private RegBunshoSortLogic regBunshoSortLogic;
    
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        /* １．ロジック【文書照会順序を取得】を実行する。 */
        List listBunsho = getGetBunshoSortLogic().getBunshoSort(
                                                    getDocumentRegistConditionDto().getNengetu(),
                                                    getDocumentRegistConditionDto().getSelectedCateId(),
                                                    getDocumentRegistConditionDto().getSelectedSubCateId(),
                                                    getMstUser().getRCompanyCd(),
                                                    getMstUser().getBumonCd());
        
        DocumentRegistRegistRankDto dto = getDocumentRegistRegistRankDto();
        dto.setListBunsho(listBunsho);
        setDocumentRegistRegistRankDto(dto);
        
        return null;
    }
    
    /**
     * 取消  
     * @return
     */
    public String cancel() {
        return "BIF003V01";
    }

    /**
     * 登録処理
     * @return String
     */
    public String regist() {
        /* １．ロジック【文書照会順序の登録】を実行する。 */
        getRegBunshoLogic().registBunsho(getDocumentRegistRegistRankDto().getListBunsho());
        
        // 条件画面で再検索させるためにフラグを立てる
        getDocumentRegistConditionDto().setFlgFromZyuni(true);
        
        return "BIF003V01";
    }

    /**
     * @param conditionDto conditionDto を設定。
     */
    public void setDocumentRegistConditionDto(DocumentRegistConditionDto dto) {
        this.documentRegistConditionDto = dto;
    }

    /**
     * @return conditionDto を戻します。
     */
    public DocumentRegistConditionDto getDocumentRegistConditionDto() {
        return documentRegistConditionDto;
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
     * @return getBunshoSortLogic を戻します。
     */
    public GetBunshoSortLogic getGetBunshoSortLogic() {
        return getBunshoSortLogic;
    }
    /**
     * @param getBunshoSortLogic getBunshoSortLogic を設定。
     */
    public void setGetBunshoSortLogic(GetBunshoSortLogic getBunshoSortLogic) 
    {
        this.getBunshoSortLogic = getBunshoSortLogic;
    }

    /**
     * @param registRankDto registRankDto を設定。
     */
    public void setDocumentRegistRegistRankDto(DocumentRegistRegistRankDto registRankDto) {
        this.documentRegistRegistRankDto = registRankDto;
    }

    /**
     * @return registRankDto を戻します。
     */
    public DocumentRegistRegistRankDto getDocumentRegistRegistRankDto() {
        return documentRegistRegistRankDto;
    }

    /**
     * MstUser取得処理
     * @return
     */
    private MstUser getMstUser() {
        return getBirdUserInfo().getMstUser();
    }
    /**
     * @return regBunshoSortLogic を戻します。
     */
    public RegBunshoSortLogic getRegBunshoLogic() {
        return regBunshoSortLogic;
    }
    /**
     * @param regBunshoSortLogic regBunshoSortLogic を設定。
     */
    public void setRegBunshoLogic(RegBunshoSortLogic regBunshoSortLogic) {
        this.regBunshoSortLogic = regBunshoSortLogic;
    }
}
