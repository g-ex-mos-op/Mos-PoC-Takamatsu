package jp.co.isid.mos.bird.bizreport.campsuiiref.logic.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.campsuiiref.common.CampSuiiRefConst;
import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.ChangeCampLogic;
import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.GetCampMenuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.MstCampDateDao;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 会社対象条件情報設定
 * @author xnkusama
 *
 */
public class ChangeCampLogicImpl implements ChangeCampLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR012L02";

    /** DAO */
    private MstCampDateDao commonCampaignMstCampDateDao;
    /** LOGIC */
    private GetCampMenuInfoLogic campsuiirefGetCampMenuInfoLogic;
    private GetHyojiTaishoLogic commonCampaignGetHyojiTaishoLogic;
    
    
    /**
     * 条件項目の取得設定
     * @param requestDto
     * @param sessionDto
     */
    public void execute(RequestSuiiDto requestDto) {
        SessionDto sessionDto = requestDto.getSelfSessionDto();
        // １．事前条件処理
        validate(requestDto);
        //
        //システム日付
        String sysDate = sessionDto.getBirdDateInfo().getSysDate();
        List listNendo = CommonUtil.creatListNendo(sysDate, 1);
        //１．List[[年度]]をパラメータ.DTO【自画面Session】.List[[年度]]へ設定する。
        sessionDto.setListNendo(requestDto.getCompanyCd(), listNendo);
        String nendo = ((CodKikanSitei)listNendo.get(0)).getCode();

        // ２．DAO【キャンペーン対象日付マスタ】.表示公開対象検索を実行し、
        List listCamp = getCommonCampaignMstCampDateDao()
                            .selectOpen(sessionDto.getBirdDateInfo().getSysDate(),
                                        sessionDto.getBirdUserInfo().getUserID(),
                                        sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                        (UserType.HONBU.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd()) && sessionDto.getBirdUserInfo().isLimit()),
                                        requestDto.getCompanyCd(),
                                        CampSuiiRefConst.CAMP_LIST_SORT_CAMP_FROM);
        // ３．処理２の検索結果List[[キャンペーン]]が0件の場合はリターンする。
        if (listCamp == null || listCamp.isEmpty()) {
            return;
        }
        // ４．処理２の検索結果List[[キャンペーン]]をパラメータ.DTO【自画面Session】.List[[キャンペーン]]へ設定する。
        sessionDto.setListCamp(requestDto.getCompanyCd(), nendo, listCamp);
        // ５．処理２の検索結果List[[キャンペーン]]の件数文、下記の処理を行う。
        doGetCampInfo(requestDto, listCamp);
    }

    private void doGetCampInfo(RequestSuiiDto requestDto, List listCamp) {
        SessionSuiiDto sessionDto = (SessionSuiiDto) requestDto.getSelfSessionDto();
        for (Iterator ite = listCamp.iterator(); ite.hasNext();) {
            MstCampDate entity = (MstCampDate) ite.next();
            
            requestDto.setCampId(entity.getCampId());
            
            getCampsuiirefGetCampMenuInfoLogic().execute(requestDto);
            
            for (Iterator ite2 = sessionDto.getListTaishoJoken(entity.getCompanyCd()).iterator(); ite2.hasNext();) {
                SelectItem selectItem = (SelectItem) ite2.next();
                // キャンペーン共通ロジック【表示対象データ検索ロジック】実行し、List[[表示対象]]を取得する。
                List listHyojiTaisho = null;
                if (!(UserType.HONBU.equals(requestDto.getSelfSessionDto().getBirdUserInfo().getMstUser().getUserTypeCd())
                            && TaishoJoken.CODE_MISE.equals((String) selectItem.getValue()))) {
                    Map mapRet = getCommonCampaignGetHyojiTaishoLogic()
                                                    .execute(sessionDto.getBirdDateInfo(),
                                                             sessionDto.getBirdUserInfo(),
                                                             requestDto.getCompanyCd(),
                                                             entity.getCampId(),
                                                             (String) selectItem.getValue());
                    // 
                    listHyojiTaisho = (List) mapRet.get(GetHyojiTaishoLogic.RK_LIST);
                    if (listHyojiTaisho == null || listHyojiTaisho.isEmpty()) {
                        if (!UserType.HONBU.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
                            if (TaishoJoken.CODE_MISE.equals(selectItem.getValue())) {
                                throw new NotExistException("店舗情報");
                            }
                        }
                    }
                    if (UserType.ONER.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
                        if (TaishoJoken.CODE_MISE.equals(selectItem.getValue())) {
                            // 店舗一覧の先頭に「全店」を追加
                            CodHyojiTaisho codHyojiTaisho = new CodHyojiTaisho();
                            codHyojiTaisho.setHyojitaishoCd("ALL");
                            codHyojiTaisho.setHyojitaishoName("全店");
                            codHyojiTaisho.setMiseOpenFlg("1");
                            listHyojiTaisho.add(0, codHyojiTaisho);

                        }
                    }
                }
                sessionDto.setListHyojiTaisho(entity.getCampId(), (String)selectItem.getValue(), listHyojiTaisho); 
            }
        }
    }
    /**
     * 事前条件処理
     * @param requestDto
     */
    private void validate(RequestSuiiDto requestDto) {
        if (requestDto == null) {
            throw new MissingDataException("Request用DTOデータ");
        }
    }

    public MstCampDateDao getCommonCampaignMstCampDateDao() {
        return commonCampaignMstCampDateDao;
    }

    public void setCommonCampaignMstCampDateDao(
            MstCampDateDao commonCampaignMstCampDateDao) {
        this.commonCampaignMstCampDateDao = commonCampaignMstCampDateDao;
    }

    public GetCampMenuInfoLogic getCampsuiirefGetCampMenuInfoLogic() {
        return campsuiirefGetCampMenuInfoLogic;
    }

    public void setCampsuiirefGetCampMenuInfoLogic(
            GetCampMenuInfoLogic campsuiirefGetCampMenuInfoLogic) {
        this.campsuiirefGetCampMenuInfoLogic = campsuiirefGetCampMenuInfoLogic;
    }

    public GetHyojiTaishoLogic getCommonCampaignGetHyojiTaishoLogic() {
        return commonCampaignGetHyojiTaishoLogic;
    }

    public void setCommonCampaignGetHyojiTaishoLogic(
            GetHyojiTaishoLogic commonCampaignGetHyojiTaishoLogic) {
        this.commonCampaignGetHyojiTaishoLogic = commonCampaignGetHyojiTaishoLogic;
    }

}
