/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao.CodCompanyDao;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao.UITaishoKenshuInfoDao;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.MlResultStatusRefDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.YearListDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UITaishoKenshuInfo;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.GetConditionLogic;

/**
 * @author Noh
 */
public class GetConditionLogicImpl implements GetConditionLogic {
    
	/* ロジックID */
    public static final String LOGIC_ID = "BSM007L01";
	
    /*【DAO】会社コード*/
    private CodCompanyDao mlresultstatusrefCodCompanyDao;
    /*【ロジック】支部取込データ検索ロジック */
    private GetSibuTorikomiLogic sibuTorikomiLogic;
	private UITaishoKenshuInfoDao uITaishoKenshuInfoDao;
	/**
	 * 初期化処理実行
	 * 
	 * @param birdUserInfo
	 * @param birdDateInfo
	 * @param mlResultStatusRefDto
	 */
	public void execute(BirdUserInfo birdUserInfo
			, BirdDateInfo birdDateInfo
			, MlResultStatusRefDto dto) {
		//初期化処理で値をすべてクリアします。
		dto.clear();
		//ユーザタイプコードを取得します。
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		//ユーザタイプコードを設定します。
		dto.setUserTypeCd(userTypeCd);
		/* システム日付の年度を取得します*/
		String currentYear = DateManager.getCurrentYear(birdDateInfo.getSysDate().substring(0,6));

		dto.setBandleYear1(currentYear);
		dto.setBandleYear2(String.valueOf((Integer.valueOf(currentYear).intValue())-1));
		dto.setBandleYear3(String.valueOf((Integer.valueOf(currentYear).intValue())-2));

        //ユーザーID
        String userId = birdUserInfo.getUserID();
        // 会社データ取得
        List list = getMlresultstatusrefCodCompanyDao().select(userId);
        if (list == null || list.size() == 0) {
            throw new NotExistException("会社情報");
        }
        dto.setListCompany(list);
        CodCompany entity = (CodCompany)dto.getListCompany().get(0);
		/* 会社コードセット */
        dto.setCompanyCd(entity.getCompanyCd());
		
		if(UserType.HONBU.equals(userTypeCd)) {
            /* 支部取得プルダウン作成 */
            dto.setSibuList(getSibuTorikomiLogic().execute(
                    dto.getCompanyCd(), birdUserInfo.getUserID(), birdUserInfo.isLimit()));
		}
		/*
		■取得したデータをセットする。										
		　　・最新回　 ：取得した最新の[[対象研修情報]].エントリー年[[対象研修情報]].エントリー回										
		　　・年度指定：取得した[[対象研修情報]].エントリー年のdistinctしたものを表示(降順)										
		*/
		List yearKai = getUITaishoKenshuInfoDao().select(
				  birdDateInfo.getSysDate()
				, userTypeCd
				, dto.getCompanyCd());
		dto.setEntryList(yearKai);
        if(yearKai==null || yearKai.size() == 0) {
            throw new NotExistException("対象試験");
        }
		/* 最新回セット */
		UITaishoKenshuInfo uIT = (UITaishoKenshuInfo) yearKai.get(0);
		dto.setEntryYear(uIT.getEntryYear());
		dto.setLatestEntryKai(uIT.getEntryKai());
		// 最新回の生成
		dto.setLatestEntryYear(uIT.getEntryYear());
		dto.setEntryYearKai(uIT.getEntryYear() + "年度　" + "第" + Integer.valueOf(uIT.getEntryKai()).toString() + "回");

        /* 年度指定セット */
		ArrayList yearList = new ArrayList();
        Map yearMap = new HashMap();
        for(int i=0; i<yearKai.size(); i++){
            UITaishoKenshuInfo info = (UITaishoKenshuInfo) yearKai.get(i);
            String year = info.getEntryYear();
            
            if(!yearMap.containsKey(year)){

                //年度リストに追加
                YearListDto yearListDto = new YearListDto();
                yearListDto.setYear(year);
                yearList.add(yearListDto);

                //年度をキーとして保持
                yearMap.put(year,year);
            }
        }

        //DTOに年度リストを格納
        dto.setEntryYearList(yearList);
	}
	

	/**
	 * @return uITaishoKenshuInfoDao を戻します。
	 */
	public UITaishoKenshuInfoDao getUITaishoKenshuInfoDao() {
		return uITaishoKenshuInfoDao;
	}
	/**
	 * @param taishoKenshuInfoDao uITaishoKenshuInfoDao を設定。
	 */
	public void setUITaishoKenshuInfoDao(
			UITaishoKenshuInfoDao taishoKenshuInfoDao) {
		uITaishoKenshuInfoDao = taishoKenshuInfoDao;
	}
	/**
	 * @return getSibuLogic を戻します。
	 */
	public GetSibuTorikomiLogic getSibuTorikomiLogic() {
		return sibuTorikomiLogic;
	}
	/**
	 * @param getSibuLogic getSibuLogic を設定。
	 */
	public void setSibuTorikomiLogic(GetSibuTorikomiLogic logic) {
		this.sibuTorikomiLogic = logic;
	}

	/**
	 * DAO【会社プルダウン用情報】取得処理
	 * @return
	 */
	public CodCompanyDao getMlresultstatusrefCodCompanyDao() {
		return mlresultstatusrefCodCompanyDao;
	}
	/**
	 * DAO【会社プルダウン用情報】設定処理
	 * @param codCompanyDao
	 */
	public void setMlresultstatusrefCodCompanyDao(CodCompanyDao codCompanyDao) {
		this.mlresultstatusrefCodCompanyDao = codCompanyDao;
	}
}
