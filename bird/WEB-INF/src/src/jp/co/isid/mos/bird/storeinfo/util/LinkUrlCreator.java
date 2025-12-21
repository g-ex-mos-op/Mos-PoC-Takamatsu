/*
 * 作成日: 2006/03/07
 *
 */
package jp.co.isid.mos.bird.storeinfo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.BirdLinkJohoEntity;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.CodKotenLinkJoho;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.CodOnerLinkJoho;

/**
 * リンク用URL文字列を作成します
 * @author xnkusama
 *
 */
public class LinkUrlCreator {

	/**
	 * e-mossles、birdリンク情報作成処理
	 * @param mapParam  パラメータ情報：${miseCd}等を置換える情報
	 * @param listEntity　DBに登録されているBirdLinkJohoEntity型Entityを格納したList
	 * @return List
	 */
	public static List createLinkUrl(Map mapParam, List listEntity) {
		List listNewLink = new ArrayList();
		String oldKey = "";
		String newKey = "";
		
		// URLの組立て
		for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
			BirdLinkJohoEntity entity = (BirdLinkJohoEntity) ite.next();
			entity.setLinkUrl(makeUrl(mapParam, entity));
		}

		// メニュー、サブメニュー情報の組み立て
		for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
			BirdLinkJohoEntity entity = (BirdLinkJohoEntity) ite.next();
			newKey = entity.getMenuName();
			if (!oldKey.equals(newKey)) {
				BirdLinkJohoEntity newEntity;
				if (entity instanceof CodKotenLinkJoho) {
					newEntity = new CodKotenLinkJoho();
				}
				else {
					newEntity = new CodOnerLinkJoho();
				}
				newEntity.setMenuName(entity.getMenuName());
				listNewLink.add(newEntity);
			}
			oldKey = newKey;
			listNewLink.add(entity);
		}

		return listNewLink;
	}
	
	private static String makeUrl(Map mapParam, BirdLinkJohoEntity entity) {
		String url = entity.getUrl();
		String param = entity.getParam();
		
		if (isNull(param)) {
			return url;
		}
		else {
			url = url + "?";
			while (param.indexOf("${") >= 0) {
				int bIndex = param.indexOf("${") + 2;
				int eIndex = param.indexOf("}");
				if (bIndex >= eIndex) {
					break;
				}
				String key = param.substring(bIndex, eIndex);
				Object value = "";
				if (mapParam.containsKey(key)) {
					value = mapParam.get(key);
				}
				
				url += param.substring(0, bIndex - 2);
				url += value;
				param = param.substring(eIndex + 1);
			}
		}
		if (!"".equals(param)) {
			url += param;
		}
		return url;
	}
	
	private static boolean isNull(String val) {
		return val == null || val.trim().equals("") ? true : false;
	}
}