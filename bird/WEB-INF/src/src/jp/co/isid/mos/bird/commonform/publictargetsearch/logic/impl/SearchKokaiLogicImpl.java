/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.TrnControlCompany;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotai;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiKobetu;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiTenpo;
import jp.co.isid.mos.bird.common.entity.TrnControlShozoku;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.CodShozokuDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.CtlCompanyDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.CtlUserShozokuDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.MstGyotiUneiCompanyDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodMiseInfo;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodShozoku;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlUserShozoku;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstCompanySibuTorikomi;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.SearchKokaiLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 公開対象の検索
 * @author xyuchida
 */
public class SearchKokaiLogicImpl implements SearchKokaiLogic {

    public static final String LOGIC_ID = "BCO002L01";

    /**
     * 所属Dao
     */
    private CodShozokuDao codShozokuDao;

    /**
     * 会社Dao
     */
    private CtlCompanyDao ctlCompanyDao;

    /**
     * ユーザ所属Dao
     */
    private CtlUserShozokuDao ctlUserShozokuDao;

    /**
     * 業態Do
     */
    private MstGyotiUneiCompanyDao mstGyotiUneiCompanyDao;

    /**
     * 公開対象の検索
     * @param publicTargetSearchConditionDto 公開対象DTO
     * @param publicTargetSearchDto 公開対象パラメータ受け渡し用DTO
     * @return
     */
    public PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchConditionDto,
            PublicTargetSearchDto publicTargetSearchDto) {

        String userid = publicTargetSearchConditionDto.getUserId();

        //会社
        List listUserCompany = ctlCompanyDao.getCompanyList();
        //デフォルトのユーザ会社を設定
        listUserCompany = setDefaultCompany(listUserCompany, publicTargetSearchConditionDto.getBirdUserInfo());
        reflectListCompany(listUserCompany, publicTargetSearchDto);

        //所属
        List listShozoku = codShozokuDao.getShozoku();
        CtlUserShozoku ctlUserShozoku = ctlUserShozokuDao
                .getUserShozoku(userid);
        //デフォルトのユーザ所属を設定
        listShozoku = setDefaultShozoku(listShozoku, ctlUserShozoku);
        reflectListShozoku(listShozoku, publicTargetSearchDto);

        //        List listGyotiUneiCompany = new ArrayList();
        //業態
        List companyCd = getCompanyCd(listUserCompany);
        
        List listGyotiUneiCompany = null;
        if(companyCd.size() == 0){
            listGyotiUneiCompany = new ArrayList();
            
        }else{
            listGyotiUneiCompany = mstGyotiUneiCompanyDao.getGyotai(companyCd);
            
        }
        
        reflectListGyotai(listGyotiUneiCompany, publicTargetSearchDto);

        //公開対象情報の反映

        //        List listGyotiUneiCompany = mstGyotiUneiCompanyDao
        //        .getGyotai(getCompanyCd(listUserCompany));

        publicTargetSearchConditionDto.setListCtlUserCompany(listUserCompany);
        publicTargetSearchConditionDto.setListCodShozoku(listShozoku);
        publicTargetSearchConditionDto
                .setListMstGyotiUneiCompany(listGyotiUneiCompany);

        return publicTargetSearchConditionDto;
    }
    
    /**
     * ユーザの所属をセットします。
     * @param listShozoku 所属情報
     * @param ctlUserShozoku ユーザ所属
     * @return
     */
    private List setDefaultShozoku(List listShozoku, CtlUserShozoku ctlUserShozoku) {
        if (ctlUserShozoku == null) {
            return listShozoku;
        }
        for (int i = 0; i < listShozoku.size(); i++) {
            CodShozoku codShozoku = (CodShozoku) listShozoku.get(i);

            if (codShozoku.getShozokuKbn().equals(
                    ctlUserShozoku.getShozokuKbn())) {
                codShozoku.setSentakuFlg(true);
            }
        }
        return listShozoku;
    }
    

    /**
     * ユーザの会社をセットします。
     * @param listShozoku 所属情報
     * @param ctlUserShozoku ユーザ所属
     * @return
     */
    private List setDefaultCompany(List listCompany, BirdUserInfo birdUserInfo) {
        if (listCompany == null) {
            return listCompany;
        }
        String userComp = birdUserInfo.getMstUser().getRCompanyCd();
        if(userComp == null || userComp.trim().length() == 0){
            return listCompany;
        }
        
        for (int i = 0; i < listCompany.size(); i++) {
            CtlCompany ctlCompany = (CtlCompany) listCompany.get(i);
            if (userComp.equals(ctlCompany.getRCompanyCd())){
                ctlCompany.setSentakuFlg(true);
            }
        }
        return listCompany;
    }

    

    /**
     * 会社コードを抽出
     * @param listUserCompany 会社エンティティ
     * @return 会社コード
     */
    private List getCompanyCd(List listUserCompany) {
        List companyCd = new ArrayList();
        for (int i = 0; i < listUserCompany.size(); i++) {
            CtlCompany company = (CtlCompany) listUserCompany.get(i);
            if (company.getSentakuFlg()) {
                companyCd.add(company.getRCompanyCd());
            }
        }
        return companyCd;
    }

    /**
     * 所属Daoを設定します。
     * @param codShozokuDao codShozokuDao を設定。
     */
    public void setCodShozokuDao(CodShozokuDao codShozokuDao) {
        this.codShozokuDao = codShozokuDao;
    }

    /**
     * 会社Daoを設定します。
     * @param ctlUserCompany ctlUserCompany を設定。
     */
    public void setCtlCompany(CtlCompanyDao ctlCompanyDao) {
        this.ctlCompanyDao = ctlCompanyDao;
    }

    /**
     * ユーザ所属Daoを設定します。
     * @param ctlUserShozokuDao ctlUserShozokuDao を設定。
     */
    public void setCtlUserShozokuDao(CtlUserShozokuDao ctlUserShozokuDao) {
        this.ctlUserShozokuDao = ctlUserShozokuDao;
    }

    /**
     * 業態Daoを設定します。
     * @param mstGyotiUneiCompanyDao mstGyotiUneiCompanyDao を設定。
     */
    public void setMstGyotiUneiCompanyDao(
            MstGyotiUneiCompanyDao mstGyotiUneiCompanyDao) {
        this.mstGyotiUneiCompanyDao = mstGyotiUneiCompanyDao;
    }

    /**
     * 業態Daoを取得します。
     * @param mstGyotiUneiCompanyDao mstGyotiUneiCompanyDao を設定。
     */
    public MstGyotiUneiCompanyDao getMstGyotiUneiCompanyDao() {
        return mstGyotiUneiCompanyDao;
    }

    /**
     * 各画面から受け取った公開対象情報を反映
     * @param listUserCompany 会社
     * @param publicTargetSearchDto 公開対象パラメータ受け渡し用DTO
     */
    private void reflectListCompany(List listUserCompany,
            PublicTargetSearchDto publicTargetSearchDto) {

        List listTrnControlCompany = publicTargetSearchDto
                .getListTrnControlCompany();
        if (listTrnControlCompany == null || listTrnControlCompany.size() == 0) {
            return;
        }

        Map map = new HashMap();
        for (int i = 0; i < listTrnControlCompany.size(); i++) {
            TrnControlCompany entity = (TrnControlCompany) listTrnControlCompany
                    .get(i);
            map.put(entity.getRCompanyCd(), entity);
        }

        for (int i = 0; i < listUserCompany.size(); i++) {
            CtlCompany entity = (CtlCompany) listUserCompany.get(i);
            if (map.containsKey(entity.getRCompanyCd())) {
                entity.setSentakuFlg(true);
            } else {
                entity.setSentakuFlg(false);
            }
        }
    }

    /**
     * 各画面から受け取った公開対象情報を反映
     * @param listShozoku 所属
     * @param publicTargetSearchDto 公開対象パラメータ受け渡し用DTO
     */
    private void reflectListShozoku(List listShozoku,
            PublicTargetSearchDto publicTargetSearchDto) {
        List listTrnControlShozoku = publicTargetSearchDto
                .getListTrnControlShozoku();
        if (listTrnControlShozoku == null || listTrnControlShozoku.size() == 0) {
            return;
        }

        Map map = new HashMap();
        for (int i = 0; i < listTrnControlShozoku.size(); i++) {
            TrnControlShozoku entity = (TrnControlShozoku) listTrnControlShozoku
                    .get(i);
            map.put(entity.getShozokuKbn(), entity);
        }

        for (int i = 0; i < listShozoku.size(); i++) {
            CodShozoku entity = (CodShozoku) listShozoku.get(i);
            if (map.containsKey(entity.getShozokuKbn())) {
                entity.setSentakuFlg(true);
            } else {
                entity.setSentakuFlg(false);

            }
        }

    }

    /**
     * 各画面から受け取った公開対象情報を反映
     * @param listUserGyotai 業態
     * @param publicTargetSearchDto 公開対象パラメータ受け渡し用DTO
     */
    private void reflectListGyotai(List listUserGyotai,
            PublicTargetSearchDto publicTargetSearchDto) {

        List listTrnControlGyotai = publicTargetSearchDto
                .getListTrnControlGyotai();
        List listTrnControlGyotaiKobetu = publicTargetSearchDto
                .getListTrnControlGyotaiKobetu();
        List listTrnControlGyotaiTenpo = publicTargetSearchDto
                .getListTrnControlGyotaiTenpo();

        //業態
        if (listTrnControlGyotai == null || listTrnControlGyotai.size() == 0) {
            return;
        }
        Map mapGyotai = new HashMap();
//        for (int i = 0; i < listTrnControlGyotai.size(); i++) {
//            TrnControlGyotai entity = (TrnControlGyotai) listTrnControlGyotai
//                    .get(i);
//
//            MstGyotiUneiCompany gyotai = new MstGyotiUneiCompany();
//            gyotai.setGyotaiKbn(entity.getGyotaiKbn());
//            gyotai.setGyotaiName(entity.getGyotaiKbnName());
//            gyotai.setSentakuFlg(true);
//            listUserGyotai.add(gyotai);
//        }
        for (int i = 0; i < listTrnControlGyotai.size(); i++) {
          TrnControlGyotai entity = (TrnControlGyotai) listTrnControlGyotai
          .get(i);
            mapGyotai.put(entity.getGyotaiKbn(), entity);
        }
        for (int i = 0; i < listUserGyotai.size(); i++) {
            MstGyotiUneiCompany entity = (MstGyotiUneiCompany) listUserGyotai.get(i);
            if(mapGyotai.containsKey(entity.getGyotaiKbn())){
                entity.setSentakuFlg(true);
            }
        }    
        
        
        //業態個別
        if (listTrnControlGyotaiKobetu != null) {
            Map mapKobetu = new HashMap();
            for (int i = 0; i < listTrnControlGyotaiKobetu.size(); i++) {
                TrnControlGyotaiKobetu entity = (TrnControlGyotaiKobetu) listTrnControlGyotaiKobetu
                        .get(i);
                if (mapKobetu.containsKey(entity.getGyotaiKbn())) {
                    List list = (List) mapKobetu.get(entity.getGyotaiKbn());
                    MstCompanySibuTorikomi sibu = new MstCompanySibuTorikomi();
                    sibu.setGyotaiKbn(entity.getGyotaiKbn());
                    sibu.setSibuCd(entity.getKobetsuCd());
                    sibu.setSentakuFlg(true);
                    list.add(sibu);

                } else {
                    List list = new ArrayList();
                    MstCompanySibuTorikomi sibu = new MstCompanySibuTorikomi();
                    sibu.setGyotaiKbn(entity.getGyotaiKbn());
                    sibu.setSibuCd(entity.getKobetsuCd());
                    sibu.setSentakuFlg(true);

                    list.add(sibu);
                    mapKobetu.put(entity.getGyotaiKbn(), list);
                }
            }
            for (int i = 0; i < listUserGyotai.size(); i++) {
                MstGyotiUneiCompany entity = (MstGyotiUneiCompany) listUserGyotai
                        .get(i);
                if (mapKobetu.containsKey(entity.getGyotaiKbn())) {
                    entity.setKobetuSetSibu((List) mapKobetu.get(entity
                            .getGyotaiKbn()));
                }
            }
        }

        //業態店舗
        if (listTrnControlGyotaiTenpo != null) {
            Map mapMise = new HashMap();
            for (int i = 0; i < listTrnControlGyotaiTenpo.size(); i++) {
                TrnControlGyotaiTenpo entity = (TrnControlGyotaiTenpo) listTrnControlGyotaiTenpo
                        .get(i);
                if (mapMise.containsKey(entity.getGyotaiKbn())) {
                    List list = (List) mapMise.get(entity.getGyotaiKbn());

                    CodMiseInfo mise = new CodMiseInfo();
                    mise.setGyotaiKbn(entity.getGyotaiKbn());
                    mise.setMiseCd(entity.getMiseCd());
                    mise.setMiseNameKj(entity.getMiseNameKj());

                    list.add(mise);
                } else {
                    List list = new ArrayList();
                    CodMiseInfo mise = new CodMiseInfo();
                    mise.setGyotaiKbn(entity.getGyotaiKbn());
                    mise.setMiseCd(entity.getMiseCd());
                    mise.setMiseNameKj(entity.getMiseNameKj());

                    list.add(mise);
                    mapMise.put(entity.getGyotaiKbn(), list);
                }
            }
            for (int i = 0; i < listUserGyotai.size(); i++) {
                MstGyotiUneiCompany entity = (MstGyotiUneiCompany) listUserGyotai
                        .get(i);
                if (mapMise.containsKey(entity.getGyotaiKbn())) {
                    entity.setKobetuSetMise((List) mapMise.get(entity
                            .getGyotaiKbn()));
                }
            }
        }
    }

}