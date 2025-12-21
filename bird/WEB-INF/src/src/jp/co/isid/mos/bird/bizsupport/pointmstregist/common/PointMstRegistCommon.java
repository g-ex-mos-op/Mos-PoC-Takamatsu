/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.common;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;

/**
 * ポイントマスタメンテナンス 共通クラス
 * @author yushuncheng
 *
 */
public class PointMstRegistCommon {

	/**
	 * 付与ポイントの会社等級の分割表示フラグを設定します。
	 * @param pointList
	 */
	public static void SetPointMstSplitFlag(List<UIPointMst> pointList) {

		if(pointList == null || pointList.isEmpty()) {
			return;
		}

		String oldKbCompnayCd = pointList.get(0).getKbCompanyCd();
		for(int i = 0; i < pointList.size(); i++) {
			UIPointMst pointInfo = pointList.get(i);
			if(!oldKbCompnayCd.equals(pointInfo.getKbCompanyCd())) {
				pointInfo.setSplitFlag(true);
				oldKbCompnayCd = pointInfo.getKbCompanyCd();
			}else{
				pointInfo.setSplitFlag(false);
			}
		}
	}

	/**
	 * 重複文字の検索
	 * @param str
	 * @param sub
	 * @return
	 */
    public static int getCount(String str,String sub)
    {
        int index = 0;
        int count = 0;
        while((index = str.indexOf(sub,index))!=-1)
        {

            index = index + sub.length();
            count++;
        }
        return count;
    }
}
