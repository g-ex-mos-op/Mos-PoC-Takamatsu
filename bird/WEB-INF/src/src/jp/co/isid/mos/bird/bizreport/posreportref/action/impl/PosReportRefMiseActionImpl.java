package jp.co.isid.mos.bird.bizreport.posreportref.action.impl;

/**
 * POS速報詳細画面アクション
 * @author inazawa
 * 2007/02/07
 */
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.action.PosReportRefMiseAction;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.entity.TrnPosReportRefMenu;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefHourInfoLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefMenuInfoLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefSuiiInfoLogic;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

public class PosReportRefMiseActionImpl implements PosReportRefMiseAction{

    /*VIEW_ID*/
    public static final String VIEW_ID = "BBR006V02";
    
    /*アクションID*/
    public static final String initialize_ACTION_ID      = "BBR006A11";
    public static final String misePosRefSerch_ACTION_ID = "BBR006A12";

    /*DTO[POS速報DTO]**/
    PosReportRefDto posReportRefDto;
    /*LOGIC[店別POS売上推移情報取得]*/
    PosReportRefSuiiInfoLogic posReportRefSuiiMiseInfoLogic;
    /*LOGIC[店別POSメニュー別情報取得]*/
    PosReportRefMenuInfoLogic posReportRefMenuInfoLogic;
    /*LOGIC[店別時間帯別POS情報取得]*/
    PosReportRefHourInfoLogic posReportRefHourInfoLogic;
    /*LOGIC[店名称取得(共通)]**/
    GetMiseLogic getMiseLogic;

    /**
     * 店別POS情報取得
     */
    public String misePosRefSerch() {
        if(getPosReportRefDto() == null){
            throw new NotNullException("POS速報画面DTO");
        }
        //店名称取得
        MstMise mstMise = getGetMiseLogic().execute(
        		getPosReportRefDto().getCompanyCd()
        		,getPosReportRefDto().getMiseCd());
        getPosReportRefDto().setMiseNameKj(mstMise.getMiseNameKj().trim());
        //店別POS情報推移情報取得
        getPosReportRefDto().setListPosReportSuiiInfo(getPosReportRefSuiiMiseInfoLogic().execute(getPosReportRefDto()));
        //店別POS情報メニュー別情報取得
        getPosReportRefDto().setListMiseMenuInfo(getPosReportRefMenuInfoLogic().execute(getPosReportRefDto()));
        //店別POS情報時間帯別情報取得
        getPosReportRefDto().setListMiseHourInfo(getPosReportRefHourInfoLogic().execute(getPosReportRefDto()));
        //店別キャンペーンメニュー情報取得
        List listCampMenu = new ArrayList(0);
        List listMenu = getPosReportRefDto().getListMiseMenuInfo();
        for (int i=0; i<listMenu.size(); i++) {
        	TrnPosReportRefMenu eMenu = (TrnPosReportRefMenu)listMenu.get(i);
        	if(eMenu.isCampMenu()) {
        		listCampMenu.add(eMenu);
        	}
        }
        //店別POS情報キャンペーンメニュー別情報取得
        getPosReportRefDto().setListCampMenu(listCampMenu);
        return VIEW_ID;
    }
    /**
     * 初期処理
     */
    public String initialize() {
        return null;
    }

    /**
     * posReportRefDtoを取得
     * @return posReportRefDto
     */
    public PosReportRefDto getPosReportRefDto() {
        return posReportRefDto;
    }
    /**
     * posReportRefDtoを設定
     * @param posReportRefDto
     */
    public void setPosReportRefDto(PosReportRefDto posReportRefDto) {
        this.posReportRefDto = posReportRefDto;
    }
    /**
     * posReportRefSuiiMiseInfoLogicを取得
     * @return posReportRefSuiiMiseInfoLogic
     */
    public PosReportRefSuiiInfoLogic getPosReportRefSuiiMiseInfoLogic() {
        return posReportRefSuiiMiseInfoLogic;
    }
    /**
     * posReportRefSuiiMiseInfoLogicを設定
     * @param posReportRefSuiiMiseInfoLogic
     */
    public void setPosReportRefSuiiMiseInfoLogic(
            PosReportRefSuiiInfoLogic posReportRefSuiiMiseInfoLogic) {
        this.posReportRefSuiiMiseInfoLogic = posReportRefSuiiMiseInfoLogic;
    }
    /**
     * posReportRefMenuInfoLogicを設定
     * @return posReportRefMenuInfoLogic
     */
    public PosReportRefMenuInfoLogic getPosReportRefMenuInfoLogic() {
        return posReportRefMenuInfoLogic;
    }
    /**
     * posReportRefMenuInfoLogicを取得
     * @param posReportRefMenuInfoLogic
     */
    public void setPosReportRefMenuInfoLogic(
            PosReportRefMenuInfoLogic posReportRefMenuInfoLogic) {
        this.posReportRefMenuInfoLogic = posReportRefMenuInfoLogic;
    }
    /**
     * posReportRefHourInfoLogicを設定
     * @return posReportRefHourInfoLogic
     */
    public PosReportRefHourInfoLogic getPosReportRefHourInfoLogic() {
        return posReportRefHourInfoLogic;
    }
    /**
     * posReportRefHourInfoLogicを取得
     * @param posReportRefHourInfoLogic
     */
    public void setPosReportRefHourInfoLogic(
            PosReportRefHourInfoLogic posReportRefHourInfoLogic) {
        this.posReportRefHourInfoLogic = posReportRefHourInfoLogic;
    }
    /**
     * getMiseLogicを取得
     * @return getMiseLogic
     */
    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }
    /**
     * getMiseLogicを設定
     * @param getMiseLogic
     */
    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }
}
