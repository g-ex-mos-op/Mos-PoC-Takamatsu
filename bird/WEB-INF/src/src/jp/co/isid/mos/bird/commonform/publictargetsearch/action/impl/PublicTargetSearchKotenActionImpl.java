/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.action.impl;

import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.commonform.publictargetsearch.action.PublicTargetSearchKotenAction;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodMiseInfo;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.ReadCsvFileLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;

/**
 * 公開対象選択店舗別設定のアクション
 * @author xytamura
 */
public class PublicTargetSearchKotenActionImpl implements PublicTargetSearchKotenAction, CommonAction {

    public static final String cancel_ACTION_ID = "BCO002A18";
    public static final String select_ACTION_ID = "BCO002A17";
    public static final String fileSelect_ACTION_ID = "BCO002A20";
    public static final String clear_ACTION_ID = "BCO002A19";
    
    /**
     * 個別設定画面ページキー
     */
    private static final String KOBETUSET_PAGE = "BCO002V02";
    
    /**
     * 公開対象選択画面が使用するDto
     */
    private PublicTargetSearchConditionDto publicTargetSearchConditionDto;

    /**
     * CSV読み込みロジック
     */
    private ReadCsvFileLogic readCsvFileLogic;

    /**
     * 戻る
     * @return
     */
    public String cancel() {
//        List list = getPublicTargetSearchConditionDto()
//                .getSelectedMstGyotiUneiCompany().getKobetuSetMise();
//        if (list != null && list.size() != 0) {
//            getPublicTargetSearchConditionDto().setListOKMise(list);
//        }

        return KOBETUSET_PAGE;
    }

    /**
     * 決定
     * @return
     */
    public String select() {
        //        List list = getPublicTargetSearchConditionDto().getListOKMise();
        List koten = getPublicTargetSearchConditionDto().getListOKMise();

        MstGyotiUneiCompany gyotai = getPublicTargetSearchConditionDto()
                .getSelectedMstGyotiUneiCompany();

        gyotai.setKobetuSetMise(getNewKobetuSetMise(koten));
        if(koten == null || koten.size() == 0){
            gyotai.setSentakuFlg(false);
            
        }else{
            gyotai.setSentakuFlg(true);
        }

        return KOBETUSET_PAGE;
    }

    /**
     * ファイル読込
     * @return
     */
    public String fileSelect() {
        if (getPublicTargetSearchConditionDto().getUploadedFile() == null) {
            throw new NotSelectedException("ファイル");
        }

        PublicTargetSearchConditionDto dto = getReadCsvFileLogic().execute(
                getPublicTargetSearchConditionDto());
        setPublicTargetSearchConditionDto(dto);
        return null;
    }

    /**
     * クリア
     * @return
     */
    public String clear() {
        if(getPublicTargetSearchConditionDto().getListOKMise() != null){
            getPublicTargetSearchConditionDto().getListOKMise().clear();    
        }
        if(getPublicTargetSearchConditionDto().getListNGMise() != null){
            getPublicTargetSearchConditionDto().getListNGMise().clear();
        }
        return null;
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
     * CSV読み込みロジックを取得します。
     * @return CSV読み込みロジック
     */
    public ReadCsvFileLogic getReadCsvFileLogic() {
        return readCsvFileLogic;
    }

    /**
     * CSV読み込みロジックを設定します。
     * @param readCsvFileLogic CSV読み込みロジック
     */
    public void setReadCsvFileLogic(ReadCsvFileLogic readCsvFileLogic) {
        this.readCsvFileLogic = readCsvFileLogic;
    }

    /**
     * 個店別指定を新規作成します。
     * @param koten
     * @return
     */
    private List getNewKobetuSetMise(List koten) {

        List torikomiTarget = new ArrayList();
        if (koten == null) {
            return torikomiTarget;
        }

        for (int i = 0; i < koten.size(); i++) {
            CodMiseInfo miseNew = new CodMiseInfo();
            CodMiseInfo mise = (CodMiseInfo) koten.get(i);

            miseNew.setCompanyCd(mise.getCompanyCd());
            miseNew.setGyotaiKbn(mise.getGyotaiKbn());
            miseNew.setMiseCd(mise.getMiseCd());
            miseNew.setMiseNameKj(mise.getMiseNameKj());

            torikomiTarget.add(miseNew);
        }
        return torikomiTarget;

    }
    
    
    /**
     * @see jp.co.isid.mos.bird.framework.action.CommonAction#initialize()
     */
    public String initialize() {
        return null;
    }

}