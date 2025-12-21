/*
 * 作成日: 2006/01/27
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.TrnControlCompany;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotai;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiKobetu;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiTenpo;
import jp.co.isid.mos.bird.common.entity.TrnControlShozoku;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.MstGyotiUneiCompanyDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodMiseInfo;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodShozoku;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstCompanySibuTorikomi;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.CreateKokaiTargetLogic;

/**
 * 公開対象データ作成ロジック
 * @author xytamura
 */
public class CreateKokaiTargetLogicImpl implements CreateKokaiTargetLogic {

    public static final String LOGIC_ID = "BCO002L03";

    private static final String KOBETU_SHU = "01";
    private static final String KOBETU_ARI  = "1";
    private static final String KOBETU_NASI = "0";

    /**
     * 業態ＤＡＯ
     */
    private MstGyotiUneiCompanyDao mstGyotiUneiCompanyDao; 
    
    /**
     * 公開対象データ作成します。
     */
    public PublicTargetSearchDto execute(
            PublicTargetSearchConditionDto selectedData,
            PublicTargetSearchDto returnData) {

        //所属をセット
        List selectedShozoku = createNewTrnControlShozoku(selectedData
                .getListCodShozoku(), returnData);
        returnData.setListTrnControlShozoku(selectedShozoku);

        //業態をセット
        //業態選択の場合
        if(PublicTargetSearchConditionDto.MODE_GYOTAI.equals(selectedData.getSelectMode())){
            List selectedGyotai = createNewTrnControlGyotai(selectedData
                    .getListMstGyotiUneiCompany(), returnData);
            returnData.setListTrnControlGyotai(selectedGyotai);
         //支部選択   
        }else if(PublicTargetSearchConditionDto.MODE_SIBU.equals(selectedData.getSelectMode())){
            List selectedGyotai = createNewTrnControlGyotaiModeSibu(selectedData, returnData);
            returnData.setListTrnControlGyotai(selectedGyotai);
            
        }

        //会社をセット
        List selectedCompany = createNewTrnControlCompany(selectedData
                .getListCtlUserCompany(), returnData);
        returnData.setListTrnControlCompany(selectedCompany);

        //個別設定(支部取込)をセット
//        List selectedSibu = createNewTrnControlGyotaiKobetu(selectedData
//                .getListMstGyotiUneiCompany(), returnData);
//        returnData.setListTrnControlGyotaiKobetu(selectedSibu);
        //業態選択の場合
        if(PublicTargetSearchConditionDto.MODE_GYOTAI.equals(selectedData.getSelectMode())){
            List selectedGyotai = createNewTrnControlGyotaiKobetu(selectedData
                    .getListMstGyotiUneiCompany(), returnData);
            returnData.setListTrnControlGyotaiKobetu(selectedGyotai);
         //支部選択   
        }else if(PublicTargetSearchConditionDto.MODE_SIBU.equals(selectedData.getSelectMode())){
            List selectedGyotai = createNewTrnControlGyotaiKobetuModeSibu(selectedData, returnData);
            returnData.setListTrnControlGyotaiKobetu(selectedGyotai);
            
        }

        //個別設定(個店)をセット
        List selectedKoten = createNewTrnControlGyotaiTenpo(selectedData
                .getListMstGyotiUneiCompany(), returnData);
        returnData.setListTrnControlGyotaiTenpo(selectedKoten);

        return returnData;
    }

    /**
     * 業態を作成
     * @param selectedGyotai 業態
     * @return 業態
     */
    private List createNewTrnControlGyotai(List selectedGyotai,
            PublicTargetSearchDto dto) {
        List result = new ArrayList();
        for (int i = 0; i < selectedGyotai.size(); i++) {
            TrnControlGyotai data = new TrnControlGyotai();
            MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany) selectedGyotai
                    .get(i);
            if (mstGyotiUneiCompany.getSentakuFlg()) {
                data.setGyotaiKbn(mstGyotiUneiCompany.getGyotaiKbn());
                data.setGyotaiKbnName(mstGyotiUneiCompany.getGyotaiName());
                data.setInfoShu(dto.getInfoShu());
                data.setRegDate(dto.getRegDate());
                data.setSeq(dto.getSeq());
                //業態別(店)設定
                if (mstGyotiUneiCompany.getKobetuSetMise() == null
                        || mstGyotiUneiCompany.getKobetuSetMise().size() == 0) {
                    data.setMiseFlg(KOBETU_NASI);
                } else {
                    data.setMiseFlg(KOBETU_ARI);
                }
                //業態別(支部)設定
                if (mstGyotiUneiCompany.getKobetuSetSibu() == null
                        || mstGyotiUneiCompany.getKobetuSetSibu().size() == 0) {
                    data.setKobetsuFlg(KOBETU_NASI);
                } else {
                    data.setKobetsuFlg(KOBETU_ARI);
                }
                
                result.add(data);
            }
        }
        return result;
    }

    /**
     * 会社を作成
     * @param selectedCompany 会社
     * @return 会社
     */
    private List createNewTrnControlCompany(List selectedCompany,
            PublicTargetSearchDto dto) {
        List result = new ArrayList();
        for (int i = 0; i < selectedCompany.size(); i++) {
            TrnControlCompany data = new TrnControlCompany();
            CtlCompany ctlCompany = (CtlCompany) selectedCompany.get(i);
            if (ctlCompany.getSentakuFlg()) {
                data.setRCompanyCd(ctlCompany.getRCompanyCd());
                data.setCompanyName(ctlCompany.getCompanyName());
                data.setInfoShu(dto.getInfoShu());
                data.setRegDate(dto.getRegDate());
                data.setSeq(dto.getSeq());

                result.add(data);
            }
        }
        return result;
    }

    /**
     * 個別(業態別)を作成
     * @param selectedGyotai 個別(業態別)
     * @return 個別(業態別)
     */
    private List createNewTrnControlGyotaiKobetu(List selectedGyotai,
            PublicTargetSearchDto dto) {
        List result = new ArrayList();

        for (int i = 0; i < selectedGyotai.size(); i++) {

            MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany) selectedGyotai
                    .get(i);
            if(mstGyotiUneiCompany.getSentakuFlg()){
                List kobetuSetSibu = mstGyotiUneiCompany.getKobetuSetSibu();
                for (int j = 0; j < kobetuSetSibu.size(); j++) {
                    TrnControlGyotaiKobetu data = new TrnControlGyotaiKobetu();
                    MstCompanySibuTorikomi sibu = (MstCompanySibuTorikomi) kobetuSetSibu
                            .get(j);
                    data.setGyotaiKbn(mstGyotiUneiCompany.getGyotaiKbn());
                    data.setKobetsuShu(KOBETU_SHU);
                    data.setKobetsuCd(sibu.getSibuCd());
                    data.setInfoShu(dto.getInfoShu());
                    data.setRegDate(dto.getRegDate());
                    data.setSeq(dto.getSeq());
    
                    result.add(data);
                }
            }
        }
        return result;
    }

    /**
     * 個別(店舗別)を作成
     * @param selectedGyotai 個別(店別別)
     * @return 個別(店別別)
     */
    private List createNewTrnControlGyotaiTenpo(List selectedGyotai,
            PublicTargetSearchDto dto) {
        List result = new ArrayList();

        for (int i = 0; i < selectedGyotai.size(); i++) {

            MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany) selectedGyotai
                    .get(i);
            if(mstGyotiUneiCompany.getSentakuFlg()){
                List kobetuSetMise = mstGyotiUneiCompany.getKobetuSetMise();
                for (int j = 0; j < kobetuSetMise.size(); j++) {
                    TrnControlGyotaiTenpo data = new TrnControlGyotaiTenpo();
                    CodMiseInfo mise = (CodMiseInfo) kobetuSetMise.get(j);
                    data.setGyotaiKbn(mstGyotiUneiCompany.getGyotaiKbn());
                    data.setMiseCd(mise.getMiseCd());
                    data.setMiseNameKj(mise.getMiseNameKj());
                    data.setInfoShu(dto.getInfoShu());
                    data.setRegDate(dto.getRegDate());
                    data.setSeq(dto.getSeq());
                    result.add(data);
                }
            }
        }
        return result;

    }

    /**
     * 所属を作成
     * @param selectedShozoku 所属
     * @return 所属
     */
    private List createNewTrnControlShozoku(List selectedShozoku,
            PublicTargetSearchDto dto) {
        List result = new ArrayList();
        for (int i = 0; i < selectedShozoku.size(); i++) {
            TrnControlShozoku data = new TrnControlShozoku();
            CodShozoku codShozoku = (CodShozoku) selectedShozoku.get(i);
            if (codShozoku.getSentakuFlg()) {

                data.setShozokuKbn(codShozoku.getShozokuKbn());
                data.setShozokuName(codShozoku.getShozokuName());
                data.setInfoShu(dto.getInfoShu());
                data.setRegDate(dto.getRegDate());
                data.setSeq(dto.getSeq());

                result.add(data);
            }
        }
        return result;

    }
    
    /**
     * 公開対象支部を選択した場合の業態を作成します。
     * @return 業態
     */
    private List createNewTrnControlGyotaiModeSibu(PublicTargetSearchConditionDto selectedData, PublicTargetSearchDto dto){
    	Map mapCompanysAreaDai = selectedData.getMapCompanysSibu();
        List autoSelectGyotai= new ArrayList();
        for(Iterator key = mapCompanysAreaDai.keySet().iterator(); key.hasNext();) {
        	String companyCd = (String)key.next();
            List listAreaDai = (List)mapCompanysAreaDai.get(companyCd);
            List selectedAreaDaiCd  = getSibuList(listAreaDai);
            if(selectedAreaDaiCd != null && selectedAreaDaiCd.size() != 0){
            	autoSelectGyotai.addAll(getMstGyotiUneiCompanyDao().getGyotaiFromSibu(companyCd, selectedAreaDaiCd));
            }
        }

        List result = new ArrayList();
        for (int i = 0; i < autoSelectGyotai.size(); i++) {
            TrnControlGyotai data = new TrnControlGyotai();
            MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany) autoSelectGyotai
                    .get(i);
                data.setGyotaiKbn(mstGyotiUneiCompany.getGyotaiKbn());
                data.setGyotaiKbnName(mstGyotiUneiCompany.getGyotaiName());
                data.setInfoShu(dto.getInfoShu());
                data.setRegDate(dto.getRegDate());
                data.setSeq(dto.getSeq());
                data.setMiseFlg(KOBETU_NASI);
                data.setKobetsuFlg(KOBETU_ARI);
                result.add(data);
        }
        return result;
    }
    
    /**
     * 公開対象支部を選択した場合の個別(業態別)を作成します。
     */
    private List createNewTrnControlGyotaiKobetuModeSibu(PublicTargetSearchConditionDto selectedData, PublicTargetSearchDto dto){
        List result = new ArrayList();
    	Map mapCompanysAreaDai = selectedData.getMapCompanysSibu();
        for(Iterator key = mapCompanysAreaDai.keySet().iterator(); key.hasNext();) {
        	String companyCd = (String)key.next();
            List listAreaDai = (List)mapCompanysAreaDai.get(companyCd);
            List selectedAreaDaiCd  = getSibuList(listAreaDai);
            if(selectedAreaDaiCd != null && selectedAreaDaiCd.size() != 0){
            	List autoSelectGyotai = getMstGyotiUneiCompanyDao().getGyotaiFromSibu(companyCd, selectedAreaDaiCd);
                for(int i = 0; i < autoSelectGyotai.size(); i++){
                    MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany) autoSelectGyotai
                    .get(i);
                    
                    for(int j = 0; j < listAreaDai.size(); j++){
                        MstCompanySibuTorikomi sibu = (MstCompanySibuTorikomi)listAreaDai.get(j);
                        if(sibu.getSentakuFlg()){
                            TrnControlGyotaiKobetu data = new TrnControlGyotaiKobetu();
                            data.setInfoShu(dto.getInfoShu());
                            data.setRegDate(dto.getRegDate());
                            data.setSeq(dto.getSeq());
                            data.setKobetsuShu(KOBETU_SHU);
                            data.setGyotaiKbn(mstGyotiUneiCompany.getGyotaiKbn());
                            data.setKobetsuCd(sibu.getSibuCd());
                            result.add(data);
                        }
                        
                    }
                }
            }
        }
        return result;
    }


    
    /**
     * 選択された支部コードのリストを取得します。
     * @param listMstCompanySibuTorikomi 支部 
     * @return 支部コードのリスト
     */
    private List getSibuList(List listMstCompanySibuTorikomi){
        if(listMstCompanySibuTorikomi == null ||  listMstCompanySibuTorikomi.size() == 0){
            return null;
        }
        List returnList = new ArrayList();
        
        for(int i = 0; i < listMstCompanySibuTorikomi.size(); i++){
            MstCompanySibuTorikomi sibu = (MstCompanySibuTorikomi)listMstCompanySibuTorikomi.get(i);
            if(sibu.getSentakuFlg()){
                returnList.add(sibu.getSibuCd());
            }
        }
        return returnList;
    }
    
    
    
    /**
     * 業態ＤＡＯを取得します。
     * @return 業態ＤＡＯ 
     */
    public MstGyotiUneiCompanyDao getMstGyotiUneiCompanyDao() {
        return mstGyotiUneiCompanyDao;
    }

    /**
     * 業態ＤＡＯを設定します。
     * @param 業態ＤＡＯ
     */
    public void setMstGyotiUneiCompanyDao(
            MstGyotiUneiCompanyDao mstGyotiUneiCompanyDao) {
        this.mstGyotiUneiCompanyDao = mstGyotiUneiCompanyDao;
    }
    
    
    
    
    
}