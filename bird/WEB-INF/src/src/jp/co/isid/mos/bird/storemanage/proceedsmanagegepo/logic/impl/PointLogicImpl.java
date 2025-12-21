package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.PointInfoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.PointInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.ProceedsManageGepoInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.PointLogic;

public class PointLogicImpl implements PointLogic{
	 /** dポイント、株主優待モスポイント情報取得ロジックID */
    public static final String LOGIC_ID = "BSM013L08";

    /** dポイント、株主優待モスポイント情報Dao */
    private PointInfoDao pointInfoDao ;

    /**
     * 売上金の取得日基準のdポイント、株主優待モスポイント情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ
     * @param onerCd	 オーナーコード
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param taishoYM  対象年月
     * @param list 売上金管理月報情報リスト
     * @return List dポイント、株主優待モスポイント情報リスト
	 */
    public List execute (
        String userType,
        String userId,
        boolean limitKbn,
        String onerCd,
        String companyCd,
        String miseCd,
        String taishoYM, List list) {

        // 入力チェック
        validate(userType, userId, companyCd, miseCd, taishoYM, list);

        // Dao【dポイント、株主優待モスポイント情報．検索】を実行する
        List tempList = getPointInfoDao().select
            (userType, userId, limitKbn, onerCd, companyCd, miseCd, taishoYM);


        // 該当レコードが存在しない場合
        if (tempList == null || tempList.isEmpty()) {

        	//dポイント、株主優待モスポイントデータを０でセットする
            tempList = setDefaultPointData(list);

        } else {

        	// dポイント、株主優待モスポイントデータを０でセットする
        	List resultList = setDefaultPointData(list);

        	// 該当レコードが存在する場合
        	PointInfo info = (PointInfo) tempList.get(0);

        	if (!ProceedsCommon.isNull(info.getEigyoDt())) {

        		//売上金の取得した日付を基準にし、dポイント、株主優待モスポイントデータをセット
	        	for (int i = 0; i < resultList.size() -1 ; i++){

	        		PointInfo point = (PointInfo) resultList.get(i);

	        		for (Iterator itData = tempList.iterator(); itData.hasNext();) {

	        			PointInfo tempPoint = (PointInfo) itData.next();

	        			//売上金で取得した日付で、dポイント、株主優待モスポイント情報のデータが存在する場合
	        			if(point.getEigyoDt().equals(tempPoint.getEigyoDt())){

	        				point = tempPoint;
	        			}

	        			resultList.set(i, point);
	        		}

	        	}
	        	// 各項目の合計を算出し、設定する
	        	resultList.set(resultList.size()-1, totalValue(resultList));

	          }

        	tempList = resultList;
        }

        // '営業日'、'合計'を設定します
        PointInfo sumDetail = (PointInfo) tempList.get(tempList.size() - 1);
        sumDetail.setRClass(ProceedsConstants.TR_TOTAL_SUM);
        sumDetail.setEigyoDt(ProceedsConstants.LABEL_SUM);

        return tempList;
    }

    /**
     * dポイント、株主優待モスポイント情報の各項目データを０に設定する。
     * @param list 売上金管理月報情報リスト
     * @return tempList　dポイント、株主優待モスポイント情報リスト
     */
	private List setDefaultPointData(List list) {

		List tempList = new ArrayList();
		// 売上金の取得した日付まで、進呈（単位：ポイント）と株主優待券チャージ金額０をセットする
		for (Iterator it = list.iterator(); it.hasNext();) {

			ProceedsManageGepoInfo info = (ProceedsManageGepoInfo) it.next();

			PointInfo point =  new PointInfo();

			point.setEigyoDt(info.getEigyoDt());
			point.setPointKei(new BigDecimal(ProceedsConstants.ZERO));
// add 2021/09/03 USI戚 begin
			point.setPointNetKei(new BigDecimal(ProceedsConstants.ZERO));
// add 2021/09/03 USI戚 end
// add 2020/06/09 USI張 begin
			point.setYChargeKin(new BigDecimal(ProceedsConstants.ZERO));
// add 2020/06/09 USI張 end
			tempList.add(point);
		 }
		return tempList;
	}

    /**
	 * 入力チェックをする
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param list      売上金管理月報情報
     * @param taishoYM  対象年月
	 */
	private void validate(String userType, String userId, String companyCd,
			String miseCd, String taishoYM, List list) {
        if (ProceedsCommon.isNull(userType)) {
            throw new NotNullException(ProceedsConstants.MSG_USER_TYPE);
        }
        if (ProceedsCommon.isNull(userId)) {
            throw new NotNullException(ProceedsConstants.MSG_USER_ID);
        }
		if (ProceedsCommon.isNull(companyCd)) {
			throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD);
		}
		if (ProceedsCommon.isNull(miseCd)) {
			throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO);
		}
		if (ProceedsCommon.isNull(taishoYM)) {
			throw new NotNullException(ProceedsConstants.MSG_TAISHO_YM);
		}
		if (list == null || list.isEmpty()) {
			throw new NotNullException("売上金管理月報の日付情報");
		}
	}

	/**
	 * dポイント、株主優待モスポイント合計を求める。
	 * @param pointList
	 * @return infoData　dポイント、株主優待モスポイント情報
	 */
    private PointInfo totalValue(List pointList) {

    	BigDecimal totalPointKei = new BigDecimal(ProceedsConstants.ZERO);
// add 2021/09/03 USI戚 begin
    	BigDecimal totalPointNetKei = new BigDecimal(ProceedsConstants.ZERO);
// add 2021/09/03 USI戚 end
// add 2020/06/09 USI張 begin
    	BigDecimal totalKabuPointKei = new BigDecimal(ProceedsConstants.ZERO);
// add 2020/06/09 USI張 end
		//取得したdポイント、株主優待モスポイントデータの値の合計を計算
    	for(Iterator it = pointList.iterator(); it.hasNext();) {

    		PointInfo info = (PointInfo) it.next();
    		totalPointKei = totalPointKei.add(info.getPointKei());
// add 2021/09/03 USI戚 begin
    		totalPointNetKei = totalPointNetKei.add(info.getPointNetKei());
// add 2021/09/03 USI戚 end
// add 2020/06/09 USI張 begin
    		totalKabuPointKei = totalKabuPointKei.add(info.getYChargeKin());
// add 2020/06/09 USI張 end
    	}
    	//合計値をセット
    	PointInfo infoData = new PointInfo();
    	infoData.setEigyoDt(ProceedsConstants.EMPTY);
    	infoData.setPointKei(totalPointKei);
// add 2021/09/03 USI戚 begin
    	infoData.setPointNetKei(totalPointNetKei);
// add 2021/09/03 USI戚 end
// add 2020/06/09 USI張 begin
    	infoData.setYChargeKin(totalKabuPointKei);
// add 2020/06/09 USI張 end
		infoData.setRClass(ProceedsConstants.EMPTY);

    	return infoData;
    }

	public PointInfoDao getPointInfoDao() {
		return pointInfoDao;
	}

	public void setPointInfoDao(PointInfoDao pointInfoDao) {
		this.pointInfoDao = pointInfoDao;
	}

}
