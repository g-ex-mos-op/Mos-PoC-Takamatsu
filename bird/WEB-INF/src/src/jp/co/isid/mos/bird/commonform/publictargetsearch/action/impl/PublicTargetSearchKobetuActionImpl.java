/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.action.PublicTargetSearchKobetuAction;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstCompanySibuTorikomi;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 公開対象選択個別設定のアクション
 * @author xytamura
 */
public class PublicTargetSearchKobetuActionImpl implements PublicTargetSearchKobetuAction, CommonAction {

    public static final String cancel_ACTION_ID = "BCO002A13";
    public static final String  select_ACTION_ID = "BCO002A12";
    public static final String kotenSelect_ACTION_ID = "BCO002A14";
    
    /**
     * 公開対象選択画面が使用するDto
     */
    private PublicTargetSearchConditionDto publicTargetSearchConditionDto;

    /**
     * 初期ページキー
     */
    private static final String INIT_PAGE = "BCO002V01";

    /**
     * 個店別設定ページキー
     */
    private static final String KOTENSET_PAGE = "BCO002V03";

    /**
     * 戻る
     * @return
     */
    public String cancel() {
        List sibu = getPublicTargetSearchConditionDto().getListMstCompanySibuTorikomi();
        if(sibu != null){
            sibu.clear();
        }
        return INIT_PAGE;
    }

    /**
     * 決定
     * @return
     */
    public String select() {
        //支部取込コードを取得
        List sibu = getPublicTargetSearchConditionDto()
                .getListMstCompanySibuTorikomi();
        //選択された支部取込コードを取得
        List sibuNew = new ArrayList();
        for (int i = 0; i < sibu.size(); i++) {
            MstCompanySibuTorikomi nowMstCompanySibuTorikomi = (MstCompanySibuTorikomi) sibu
                    .get(i);
            if (nowMstCompanySibuTorikomi.getSentakuFlg()) {
                MstCompanySibuTorikomi newMstCompanySibuTorikomi = new MstCompanySibuTorikomi();
                //                newMstCompanySibuTorikomi.setCompanyCd(nowMstCompanySibuTorikomi.getCompanyCd());
                newMstCompanySibuTorikomi.setSibuCd(nowMstCompanySibuTorikomi
                        .getSibuCd());
                newMstCompanySibuTorikomi.setSibuName(nowMstCompanySibuTorikomi
                        .getSibuName());
                newMstCompanySibuTorikomi.setGyotaiKbn(nowMstCompanySibuTorikomi
                        .getGyotaiKbn());
                newMstCompanySibuTorikomi
                        .setSentakuFlg(nowMstCompanySibuTorikomi
                                .getSentakuFlg());
                sibuNew.add(newMstCompanySibuTorikomi);
            }
        }

        MstGyotiUneiCompany gyotai = getPublicTargetSearchConditionDto()
                .getSelectedMstGyotiUneiCompany();

        
        //選択された支部取込コードがあったら
        if (sibuNew.size() != 0) {
            gyotai.setSentakuFlg(true);
            gyotai.setKobetuSetSibu(sibuNew);
        }else{
            gyotai.setKobetuSetSibu(sibuNew);
            
        }

        return INIT_PAGE;
    }

    /**
     * 個店別指定
     * @return
     */
    public String kotenSelect() {

        //個店別
        List ok = getPublicTargetSearchConditionDto().getListOKMise();
        if (ok != null) {
            ok.clear();
        }
        List ng = getPublicTargetSearchConditionDto().getListNGMise();
        if (ng != null) {
            ng.clear();
        }
        //既存情報のを反映
        List list = getPublicTargetSearchConditionDto()
                .getSelectedMstGyotiUneiCompany().getKobetuSetMise();        
        
        if(list != null && list.size() != 0){

            List newList = new ArrayList();
            for(int i = 0; i < list.size(); i++){
                newList.add(list.get(i));                
            }
            getPublicTargetSearchConditionDto().setListOKMise(newList);            
        }
        return KOTENSET_PAGE;
    }

    /**
     * 公開対象選択画面が使用するDtoを取得します。
     * @return 公開対象選択画面が使用するDto
     */
    public PublicTargetSearchConditionDto getPublicTargetSearchConditionDto() {
        return publicTargetSearchConditionDto;
    }

    /**
     * 公開対象選択画面が使用するDtoを設定します。
     * @param publicTargetSearchDto 公開対象選択画面が使用するDto
     */
    public void setPublicTargetSearchConditionDto(
            PublicTargetSearchConditionDto publicTargetSearchConditionDto) {
        this.publicTargetSearchConditionDto = publicTargetSearchConditionDto;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.action.CommonAction#initialize()
     */
    public String initialize() {
        return null;
    }

}