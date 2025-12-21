package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dao.KeigenTaxInfoDao;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewMeisaiResultDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.entity.GetKeigenTaxData;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.SearchUriageMeisaiLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 * 売上高ど消費税確認明細検索ロジック
 * @author USI　張
 */
public class SearchUriageMeisaiLogicImpl implements SearchUriageMeisaiLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBR009L04";

    /** 会計集計区分情報 */
    private KeigenTaxInfoDao keigenTaxInfoDao;


    /**
     * 0.事前条件処理
     * @param sessionDto 売上修正セッションDTOクラス
     * @param meisaiRequestJokenDto 売上修正明細照会・条件部DTOクラス
     */
    private void validate(
            UriMainteViewSesDto sessionDto
            , MeisaiRequestDto meisaiRequestJokenDto) {

    // 内部情報チェック
        // 会社コード
        if (CommonUtil.isNull(meisaiRequestJokenDto.getCompanyCd())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_COMPANYCD);
        }

        // 修正日
        if (CommonUtil.isNull(meisaiRequestJokenDto.getSyuseiDate())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_SYUSEIBI);
        }

        // システム日付
        if (CommonUtil.isNull(sessionDto.getSysDate())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_SYSDATE);
        }
    }

    /**
     * 明細検索
     * @param proceedsManageGepoDto 売上修正セッションDTOクラス
     * @param meisaiRequestJokenDto 売上修正明細照会・条件部DTOクラス
     * @param meisaiRequestResultDto 売上修正明細照会・結果部DTOクラス
     */
    public void execute(
            UriMainteViewSesDto sessionDto
          , MeisaiRequestDto meisaiRequestJokenDto
          , UriMainteViewMeisaiResultDto meisaiRequestResultDto) {

        // 事前条件処理
        validate(sessionDto, meisaiRequestJokenDto);

        //売上高ど消費税確認情報取得。
        List listUriage = getKeigenTaxInfoDao().select(sessionDto.getSysDate(),
                meisaiRequestJokenDto.getCompanyCd(), meisaiRequestJokenDto.getSyuseiDate());

        if(listUriage.isEmpty() || listUriage.size() ==0 ) {
            throw new NoResultException();
        }else {
        //対象店舗
            meisaiRequestResultDto.setListTenpo(removeDuplicate(listUriage));

        //合計
            List listToatl = getKeigenTaxInfoDao().selectTotal(meisaiRequestJokenDto.getCompanyCd(),
            		meisaiRequestJokenDto.getSyuseiDate());
            listUriage.add(listToatl.get(0));
        //売上高ど消費税合計確認情報取得
        	meisaiRequestResultDto.setListGetKeigenTaxData(listUriage);
        }
    }

	/**
	 * keigenTaxInfoDaoを取得します。
	 * @return keigenTaxInfoDao
	 */
	public KeigenTaxInfoDao getKeigenTaxInfoDao() {
		return keigenTaxInfoDao;
	}

	/**
	 * keigenTaxInfoDaoを設定します。
	 * @param keigenTaxInfoDao
	 */
	public void setKeigenTaxInfoDao(KeigenTaxInfoDao keigenTaxInfoDao) {
		this.keigenTaxInfoDao = keigenTaxInfoDao;
	}

	/**
	 * 重複データを削除
	 * @param list
	 * @return
	 */
	private String removeDuplicate(List list) {
		List listTemp = new ArrayList();
		String tenpoName = "";
        for(int i=0;i<list.size();i++){
        	if(!listTemp.contains(((GetKeigenTaxData)list.get(i)).getMiseCdNameClose())) {
        		listTemp.add(((GetKeigenTaxData)list.get(i)).getMiseCdNameClose());
        	}
        }
        for(int i=0;i<listTemp.size();i++) {
        	tenpoName = tenpoName + "," + listTemp.get(i).toString();
        }
		return tenpoName.substring(1, tenpoName.length());
	}

}
