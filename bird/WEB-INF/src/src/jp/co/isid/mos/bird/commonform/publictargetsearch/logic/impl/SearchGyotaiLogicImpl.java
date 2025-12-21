/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.MstGyotiUneiCompanyDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.SearchGyotaiLogic;

/**
 * 業態の検索ロジック
 * @author xyuchida
 */
public class SearchGyotaiLogicImpl implements SearchGyotaiLogic  {

    public static final String LOGIC_ID = "BCO002L02";

    /**
     * 業態取得Dao
     */
    private MstGyotiUneiCompanyDao mstGyotiUneiCompanyDao;

    /**
     * 業態を検索します。
     * @param publicTargetSearchConditionDto 公開対象DTO
     * @return
     */
    public PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchConditionDto) {

//        String userid = publicTargetSearchConditionDto.getUserId();
//        List company = publicTargetSearchConditionDto.getListCtlUserCompany();

        List company = getCompanyCd(publicTargetSearchConditionDto.getListCtlUserCompany());
        //選択された会社≠０の場合
        List selectGyoti = new ArrayList();
        if(company.size() != 0){
            selectGyoti = mstGyotiUneiCompanyDao.getGyotai(company);
        }
        
        List nowGyoti = publicTargetSearchConditionDto.getListMstGyotiUneiCompany();        
        setNewGyotaiData(selectGyoti, nowGyoti);
        
        publicTargetSearchConditionDto
                .setListMstGyotiUneiCompany(selectGyoti);

        return publicTargetSearchConditionDto;
    }

    /**
     * 会社コードを抽出
     * @param listUserCompany 会社エンティティ
     * @return 会社コード
     */
    private List getCompanyCd(List listUserCompany) {
        List companyCd = new ArrayList();
        for (int i = 0; i < listUserCompany.size(); i++) {
            CtlCompany company = (CtlCompany)listUserCompany.get(i);
            if(company.getSentakuFlg()){
                companyCd.add(company.getRCompanyCd());
            }
        }
        return companyCd;
    }


    /**
     * 業態取得Daoを設定します。
     * @param mstGyotiUneiCompanyDao 業態取得Dao
     */
    public void setMstGyotiUneiCompanyDao(
            MstGyotiUneiCompanyDao mstGyotiUneiCompanyDao) {
        this.mstGyotiUneiCompanyDao = mstGyotiUneiCompanyDao;
    }

    
    /**
     * 業態を作成
     * @param selectedGyotai 業態
     * @return 業態
     */
    private void setNewGyotaiData(List selectedGyotai, List nowGyotai) {
       
      Map map = new HashMap();
      for (int i = 0; i < nowGyotai.size(); i++) {
          MstGyotiUneiCompany entity = (MstGyotiUneiCompany) nowGyotai
                  .get(i);
          map.put(entity.getGyotaiKbn(), entity);
      }

       for (int i = 0; i < selectedGyotai.size(); i++) {
            MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany)selectedGyotai.get(i);
            if (map.containsKey(mstGyotiUneiCompany.getGyotaiKbn()) ) {
                MstGyotiUneiCompany setData = (MstGyotiUneiCompany)map.get(mstGyotiUneiCompany.getGyotaiKbn());
                
                mstGyotiUneiCompany.setGyotaiKbn(setData.getGyotaiKbn());
                mstGyotiUneiCompany.setGyotaiName(setData.getGyotaiName());
                mstGyotiUneiCompany.setSentakuFlg(setData.getSentakuFlg());
                mstGyotiUneiCompany.setKobetuSetMise(setData.getKobetuSetMise());
                mstGyotiUneiCompany.setKobetuSetSibu(setData.getKobetuSetSibu());
                
            }
        }
    }


}