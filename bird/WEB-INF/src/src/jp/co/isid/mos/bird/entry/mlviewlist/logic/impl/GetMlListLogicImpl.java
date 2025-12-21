package jp.co.isid.mos.bird.entry.mlviewlist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlviewlist.dao.UIMlListDao;
import jp.co.isid.mos.bird.entry.mlviewlist.dto.MlViewListDto;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlListDataInfo;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.GetMlListLogic;
import jp.co.isid.mos.bird.framework.dao.CtlGamenRoleDao;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;

/**
 * マスターライセンス一覧取得ロジック
 * @author 
 */
public class GetMlListLogicImpl implements GetMlListLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN009L01";
    private static final String VIEW_ID = "BEN009";

    /**
     * マスターライセンス一覧情報（UIMlListDao）
     */
    private UIMlListDao uIMlListDao;
    private CtlGamenRoleDao ctlGamenRoleDao;
    
	/**
	 * マスターライセンス一覧Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIMlListDao getUIMlListDao() {
		return uIMlListDao;
	}
	/**
	 * マスターライセンス一覧Daoの設定
	 * @param UIMlListDao uIMlListDao を設定。
	 */
	public void setUIMlListDao(UIMlListDao uIMlListDao) {
		this.uIMlListDao = uIMlListDao;
	}

	/**
	 * マスターライセンス一覧の検索
	 * @return 検索結果
	 */
	public List execute(MlViewListDto mlViewListCommonDto) {
        
        String sysdate     = mlViewListCommonDto.getSysDate();
        String sysNextDate = mlViewListCommonDto.getSysNextDate();
        String entryCd     = mlViewListCommonDto.getEntryCd();
        
        List mlList;
        
        // マスターライセンス一覧取得
        mlList = uIMlListDao.getMlListInfo(sysdate, sysNextDate, entryCd);
        
        // 受験番号採番機能の使用可否の判断---2006/08/14 add
        if (mlList != null && !mlList.isEmpty()) {
            for (Iterator ite = mlList.iterator(); ite.hasNext();) {
                UIMlListDataInfo uiMlListDataInfo = (UIMlListDataInfo) ite.next();
                uiMlListDataInfo.setExecutableSaiban(false);
                // 本部、オーナー両方の申込期間外の場合のみ、受験番号採番を行える
                if (!(uiMlListDataInfo.getHonbuInputDtFrom().compareTo(sysdate) <= 0 
                            && uiMlListDataInfo.getHonbuInputDtTo().compareTo(sysdate) >= 0)
                    && !(uiMlListDataInfo.getOnerInputDtFrom().compareTo(sysdate) <= 0 
                            && uiMlListDataInfo.getOnerInputDtTo().compareTo(sysdate) >= 0))
                {
                    List listCtlGamenRole = getCtlGamenRoleDao().getGamenRole(mlViewListCommonDto.getUserId(), VIEW_ID, "01");
                    if (listCtlGamenRole != null && !listCtlGamenRole.isEmpty()) {
                        uiMlListDataInfo.setExecutableSaiban(true);
                    }
                }
            }
        }
        
		return mlList;
	}
    
    public CtlGamenRoleDao getCtlGamenRoleDao() {
        return ctlGamenRoleDao;
    }
    public void setCtlGamenRoleDao(CtlGamenRoleDao ctlGamenRoleDao) {
        this.ctlGamenRoleDao = ctlGamenRoleDao;
    }
}
