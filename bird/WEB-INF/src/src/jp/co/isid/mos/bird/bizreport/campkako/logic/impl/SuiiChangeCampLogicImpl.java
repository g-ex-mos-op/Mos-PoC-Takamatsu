package jp.co.isid.mos.bird.bizreport.campkako.logic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.campkako.logic.SuiiChangeCampLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SuiiGetCampMenuInfoLogic;
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
import jp.co.isid.mos.bird.bizreport.common.logic.HyojiTaishoMapLogic;
import jp.co.isid.mos.bird.bizreport.common.logic.impl.HyojiTaishoListLogicImpl;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 会社対象条件情報設定
 * @author xnkusama
 *
 */
public class SuiiChangeCampLogicImpl implements SuiiChangeCampLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR014L11";

    /** DAO */
    private MstCampDateDao commonCampaignMstCampDateDao;
    /** LOGIC */
    private SuiiGetCampMenuInfoLogic campKakoSuiiGetCampMenuInfoLogic;
    private GetHyojiTaishoLogic commonCampaignGetHyojiTaishoLogic;
    private HyojiTaishoMapLogic hyojiTaishoListLogic;
    
    /* 年度開始月日 */
    private static final String NENDO_START_MD = "0401";
    /* 年度終了月日 */
    private static final String NENDO_END_MD = "0331";
    /* 年度 表示数 */
    private static final int NENDOSUU = 3;
    
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
        List listNendo = CommonUtil.creatListNendo(sysDate, NENDOSUU);
        //１．List[[年度]]をパラメータ.DTO【自画面Session】.List[[年度]]へ設定する。
        sessionDto.setListNendo(requestDto.getCompanyCd(), listNendo);
        
        for (Iterator ite = listNendo.iterator(); ite.hasNext();) {
            //String nendo = ((CodKikanSitei)listNendo.get(0)).getCode();
            String nendo = ((CodKikanSitei)ite.next()).getCode();
    
            // ２．DAO【キャンペーン対象日付マスタ】.表示公開対象検索を実行し、
            List listCamp = getCommonCampaignMstCampDateDao()
                                .selectClose(sessionDto.getBirdDateInfo().getSysDate(),
                                            sessionDto.getBirdUserInfo().getUserID(),
                                            sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                            (UserType.HONBU.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd()) && sessionDto.getBirdUserInfo().isLimit()),
                                            requestDto.getCompanyCd(),
                                            nendo + NENDO_START_MD,
                                            ((new Integer(nendo)).intValue() + 1) + NENDO_END_MD);
            // ４．処理２の検索結果List[[キャンペーン]]をパラメータ.DTO【自画面Session】.List[[キャンペーン]]へ設定する。
            sessionDto.setListCamp(requestDto.getCompanyCd(), nendo, (listCamp != null && listCamp.isEmpty()) ? null : listCamp);
            // ５．処理２の検索結果List[[キャンペーン]]の件数文、下記の処理を行う。
            if (!(listCamp == null || listCamp.isEmpty())) {
                doGetCampInfo(requestDto, listCamp);
            }
        }
    }

    /**
     * 1年度分のキャンペーンに対するメニュー情報取得
     * @param requestDto
     * @param listCamp
     */
    private void doGetCampInfo(RequestSuiiDto requestDto, List listCamp) {
        SessionSuiiDto sessionDto = (SessionSuiiDto) requestDto.getSelfSessionDto();
//        //本部ユーザーの場合は、年度・キャンペーンごとに作成する必要がないので
//        //セッションDTOに支部リストが存在する場合は、処理を抜ける
//        if (UserType.HONBU.equals(requestDto.getSelfSessionDto().getBirdUserInfo().getMstUser().getUserTypeCd())
//                && !(sessionDto.getListSibu() == null || sessionDto.getListSibu().isEmpty())) {
//            return;
//        }
        
        Map mapHyojiTaisho = new HashMap();
        for (Iterator ite = listCamp.iterator(); ite.hasNext();) {
            MstCampDate entity = (MstCampDate) ite.next();
            
            requestDto.setCampId(entity.getCampId());
            
            getCampKakoSuiiGetCampMenuInfoLogic().execute(requestDto);
            
            for (Iterator ite2 = sessionDto.getListTaishoJoken(entity.getCompanyCd()).iterator(); ite2.hasNext();) {
                SelectItem selectItem = (SelectItem) ite2.next();
                // キャンペーン共通ロジック【表示対象データ検索ロジック】実行し、List[[表示対象]]を取得する。
                List listHyojiTaisho = null;
                if (!(UserType.HONBU.equals(requestDto.getSelfSessionDto().getBirdUserInfo().getMstUser().getUserTypeCd())
                            && TaishoJoken.CODE_MISE.equals((String) selectItem.getValue()))) {
                    Map mapRet;
//                    if (UserType.HONBU.equals(requestDto.getSelfSessionDto().getBirdUserInfo().getMstUser().getUserTypeCd())) {
                        //本部ユーザーの場合
                        String taishoJoken = (String) selectItem.getValue();
//                        if(TaishoJoken.CODE_JIGYOU.equals(taishoJoken)) {
//                            //事業本部の場合
                            listHyojiTaisho = (List) mapHyojiTaisho.get(taishoJoken);
//                        }
//                        else if (TaishoJoken.CODE_SLAREA.equals(taishoJoken)) {
//                            //営業エリアの場合
//                            listHyojiTaisho = sessionDto.getListSlArea();
//                        }
//                        else if (TaishoJoken.CODE_SIBU.equals(taishoJoken)) {
//                            //支部の場合
//                            listHyojiTaisho = sessionDto.getListSibu();
//                        }
//                        else if (TaishoJoken.CODE_AREADAI.equals(taishoJoken)) {
//                            //エリア大の場合
//                            listHyojiTaisho = sessionDto.getListAreaDai();
//                        }
                        if (listHyojiTaisho == null || listHyojiTaisho.isEmpty()) {
                            Map mapParam = new HashMap();
                            mapParam.put(HyojiTaishoListLogicImpl.PM_COMPANY_CD, requestDto.getCompanyCd());
                            mapParam.put(HyojiTaishoListLogicImpl.PM_TAISHOJOKEN, (String) selectItem.getValue());
                            mapParam.put(HyojiTaishoListLogicImpl.PM_APP_DATE, requestDto.getSelfSessionDto().getBirdDateInfo().getAppDate());
                            mapRet = getHyojiTaishoListLogic().execute(mapParam);
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

                            mapHyojiTaisho.put(taishoJoken, listHyojiTaisho);
//                            if(TaishoJoken.CODE_JIGYOU.equals(taishoJoken)) {
//                                //事業本部の場合
//                                sessionDto.setListJigyoHonbu(listHyojiTaisho);
//                            }
//                            else if (TaishoJoken.CODE_SLAREA.equals(taishoJoken)) {
//                                //営業エリアの場合
//                                sessionDto.setListSlArea(listHyojiTaisho);
//                            }
//                            else if (TaishoJoken.CODE_SIBU.equals(taishoJoken)) {
//                                //支部の場合
//                                sessionDto.setListSibu(listHyojiTaisho);
//                            }
//                            else if (TaishoJoken.CODE_AREADAI.equals(taishoJoken)) {
//                                //エリア大の場合
//                                sessionDto.setListAreaDai(listHyojiTaisho);
//                            }
                        }
//                    }
//                    else {
//                        //本部ユーザー以外の場合
//                        mapRet = getCommonCampaignGetHyojiTaishoLogic()
//                                                        .execute(sessionDto.getBirdDateInfo(),
//                                                                 sessionDto.getBirdUserInfo(),
//                                                                 requestDto.getCompanyCd(),
//                                                                 entity.getCampId(),
//                                                                 (String) selectItem.getValue());
//                        listHyojiTaisho = (List) mapRet.get(GetHyojiTaishoLogic.RK_LIST);
//                        if (listHyojiTaisho == null || listHyojiTaisho.isEmpty()) {
//                            if (!UserType.HONBU.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
//                                if (TaishoJoken.CODE_MISE.equals(selectItem.getValue())) {
//                                    throw new NotExistException("店舗情報");
//                                }
//                            }
//                        }
//                        // 
//                        if (UserType.ONER.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
//                            if (TaishoJoken.CODE_MISE.equals(selectItem.getValue())) {
//                                // 店舗一覧の先頭に「全店」を追加
//                                CodHyojiTaisho codHyojiTaisho = new CodHyojiTaisho();
//                                codHyojiTaisho.setHyojitaishoCd("ALL");
//                                codHyojiTaisho.setHyojitaishoName("全店");
//                                codHyojiTaisho.setMiseOpenFlg("1");
//                                listHyojiTaisho.add(0, codHyojiTaisho);
//
//                            }
//                        }
//                    }
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

    public SuiiGetCampMenuInfoLogic getCampKakoSuiiGetCampMenuInfoLogic() {
        return campKakoSuiiGetCampMenuInfoLogic;
    }

    public void setCampKakoSuiiGetCampMenuInfoLogic(
            SuiiGetCampMenuInfoLogic campsuiirefGetCampMenuInfoLogic) {
        this.campKakoSuiiGetCampMenuInfoLogic = campsuiirefGetCampMenuInfoLogic;
    }

    public GetHyojiTaishoLogic getCommonCampaignGetHyojiTaishoLogic() {
        return commonCampaignGetHyojiTaishoLogic;
    }

    public void setCommonCampaignGetHyojiTaishoLogic(
            GetHyojiTaishoLogic commonCampaignGetHyojiTaishoLogic) {
        this.commonCampaignGetHyojiTaishoLogic = commonCampaignGetHyojiTaishoLogic;
    }

    public HyojiTaishoMapLogic getHyojiTaishoListLogic() {
        return hyojiTaishoListLogic;
    }

    public void setHyojiTaishoListLogic(HyojiTaishoMapLogic hyojiTaishoListLogic) {
        this.hyojiTaishoListLogic = hyojiTaishoListLogic;
    }

}
