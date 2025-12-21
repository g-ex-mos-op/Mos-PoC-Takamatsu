/*
 * 作成日：2012/07/26
 */
package jp.co.isid.mos.bird.common.kaikei.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlKaikeiKbnInfo;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.common.kaikei.entity.GetterMethod;

/**
 * 会計区分系共通静的クラス
 * @author xkawa
 *
 */
public class KaikeiUtil {
	/** Mapキー：コード */
	public static final String MK_CODE = "CODE";
	/** Mapキー：名称 */
	public static final String MK_NAME = "NAME";
	/** Mapキー：件数 */
	public static final String MK_KEN = "KEN";
	/** Mapキー：金額 */
	public static final String MK_KIN = "KIN";
	/** カラムキー：件数 */
	public static final String CLM_KEN = "Ken";
	/** カラムキー：金額 */
	public static final String CLM_KIN = "Kin";
	/**
	 * 画面表示会計区分情報取得処理
	 * 
	 * 画面表示対象の会計区分情報の生成を行います。
	 * 
	 * @param listKaikeiKbn List[[会計区分]]
	 * @param tabNo タブNo
	 * @return
	 */
	public static Map getScreenDispInfo(final List listKaikeiKbn, final String tabNo) {
		//０．リターン値Map[画面表示情報]を生成します。
		Map mapScreenInf = new HashMap();
		//１．パラメータ．タブNoの値を数値に変換し-1した値を、変数．TABインデックスとします。
		int indexTab = Integer.valueOf(tabNo).intValue()-1;
		//２．変数．TABインデックスから表示範囲のインデックスを算出します。（計４つの会計区分です。）
		//　　・変数．表示開始インデックス　＝　変数．TABインデックス　×　4
		int dispIndexFrom = indexTab*4;
		//　　・変数．表示終了インデックス　＝　変数．表示開始インデックス　＋3
		int dispIndexTo = dispIndexFrom+3;
		//３．変数List[[画面表示会計区分]]を生成します。
		List listCd = new ArrayList(0);
		//４．変数List[[画面表示会計区分名称]]を生成します。
		List listName = new ArrayList(0);
		//５．List[[会計区分]]中の処理３のインデックス範囲にある件数分の[会計区分]から値を設定します。
		for(int i=dispIndexFrom; i<listKaikeiKbn.size(); i++) {
			CtlKaikeiKbnInfo eKaikei = (CtlKaikeiKbnInfo)listKaikeiKbn.get(i);
			//  for-1.変数List[[画面表示会計区分]]
			listCd.add(eKaikei.getKkbnCd());
			//  for-2.変数List[[画面表示会計区分名称]]
			listName.add(eKaikei.getKkbnName());
			if(i==dispIndexTo) {
				break;
			}
		}
		//６．リターン値Map[画面表示情報]へ”CD”をキーに変数List[[画面表示会計区分]]を格納します。
		mapScreenInf.put(MK_CODE, listCd);
		//７．リターン値Map[画面表示情報]へ”NAME”をキーに変数List[[画面表示会計区分名称]]を格納します。
		mapScreenInf.put(MK_NAME, listName);
		//８．リターン値Map[画面表示情報]をリターンします。
		return mapScreenInf;
		//９．処理終了。

	}
	/**
	 * 件数・金額メソッド情報取得処理
	 * 
	 * 会計区分コードをキーに対象メソッドを格納したMapを取得できます。
	 * 
	 * @param listKaikeiKbn
	 * @param entityClass
	 * @param clmName
	 * @return
	 */
	public static final Map getMapMethod(
			final List listKaikeiKbn, final Class entityClass, final String clmName, final String functionWord) 
	{
		//１．リターン値Map[Getterメソッド]を生成します。
		Map mapGetterMethod = new HashMap();
		//２．引数．List[[会計区分]]件数分、下記の処理を行います。
		for (int i=0; i<listKaikeiKbn.size(); i++) {
			//for-0．引数．List[[会計区分]]から現行行の[会計区分]を取得します。
			CtlKaikeiKbnInfo eKaikei = (CtlKaikeiKbnInfo)listKaikeiKbn.get(i);
			//for-1. 変数．カラム用会計区分コードへ[会計区分]．会計区分コード(前ゼロ無し処理後）を設定します。
			String clmKaikeiNo = Integer.valueOf(eKaikei.getKkbnCd()).toString();
			//for-2．変数．GetterMethod[Getterメソッド]を生成し、変数．GetterMethod[Getterメソッド].会計区分へ現行行の[会計区分].ます。
			GetterMethod gMethod = getMethod(entityClass, clmName, clmKaikeiNo, functionWord);
			gMethod.setKkbnCd(eKaikei.getKkbnCd());
			// for-4.リターン値Map[Getterメソッド]へ
			//         現行行の[会計区分].会計区分コードをキーに変数．GetterMethod[Getterメソッド]を設定します。
			mapGetterMethod.put(eKaikei.getKkbnCd(), gMethod);
		}
		//３．リターン値Map[Getterメソッド]をリターンします。
		return mapGetterMethod;
		//４．処理終了。
	}
	/**
	 * 件数・金額Getterメソッド情報取得処理
	 * 
	 * 会計区分コードをキーに対象メソッドを格納したMapを取得できます。
	 * 
	 * @param listKaikeiKbn
	 * @param entityClass
	 * @param clmName
	 * @return
	 */
	public static final Map getMapGetterMethod(
			final List listKaikeiKbn, final Class entityClass, final String clmName) 
	{
		//３．リターン値Map[Getterメソッド]をリターンします。
		return getMapMethod(listKaikeiKbn, entityClass, clmName, "get");
		//４．処理終了。
	}
	/**
	 * 件数・金額Getterメソッド情報リスト取得処理
	 * 
	 * 対象メソッドを格納したListを取得できます。
	 * 
	 * @param listKaikeiKbn
	 * @param entityClass
	 * @param clmName
	 * @return List[[GetterMethod]]
	 */
	public static final List getListGetterMethod(
			final List listKaikeiKbn, final Class entityClass, final String clmName) 
	{
		//３．リターン値List[[Getterメソッド]]をリターンします。
		return getListMethod(listKaikeiKbn, entityClass, clmName, "get");
		//４．処理終了。
	}
	/**
	 * 件数・金額メソッド情報リスト取得処理
	 * 
	 * 対象メソッドを格納したListを取得できます。
	 * 
	 * @param listKaikeiKbn
	 * @param entityClass
	 * @param clmName
	 * @return List[[GetterMethod]]
	 */
	public static final List getListMethod(
			final List listKaikeiKbn, final Class entityClass, final String clmName, final String functionWord) 
	{
		//１．リターン値List[[Getterメソッド]]を生成します。
		List listMethod = new ArrayList(0);
		//２．引数．List[[会計区分]]件数分、下記の処理を行います。
		for (int i=0; i<listKaikeiKbn.size(); i++) {
			//for-0．引数．List[[会計区分]]から現行行の[会計区分]を取得します。
			CtlKaikeiKbnInfo eKaikei = (CtlKaikeiKbnInfo)listKaikeiKbn.get(i);
			//for-1. 変数．カラム用会計区分コードへ[会計区分]．会計区分コード(前ゼロ無し処理後）を設定します。
			String clmKaikeiNo = Integer.valueOf(eKaikei.getKkbnCd()).toString();
			//for-2．変数．GetterMethod[Getterメソッド]を生成し、変数．GetterMethod[Getterメソッド].会計区分へ現行行の[会計区分].ます。
			GetterMethod gMethod = getMethod(entityClass, clmName, clmKaikeiNo, functionWord);
			gMethod.setKkbnCd(eKaikei.getKkbnCd());
			
			//for-3.リターン値List[[Getterメソッド]]へ変数．GetterMethod[Getterメソッド]を設定します。
			listMethod.add(gMethod);
		}
		//３．リターン値List[[メソッド]]をリターンします。
		return listMethod;
		//４．処理終了。
	}
	/**
	 * 対象会計区分ゲッターメソッド取得処理
	 * @param entityClass
	 * @param clmName
	 * @param clmKaikeiNo
	 * @return
	 */
	private static final GetterMethod getMethod(final Class entityClass, final String clmName, String clmKaikeiNo, String functionWord) {
		//１．変数．Method配列[会計区分別在高系]へ引数．メソッド対象EntityClassのメソッドオブジェクトを取得し設定します。
		Method[] methods = entityClass.getMethods();
		GetterMethod gMethod = new GetterMethod();
		// for-3．変数．Method配列[会計区分別在高系]中から、下記の条件を満たすメソッドを変数．GetterMethod[Getterメソッド]へ格納します。
		for(int m=0; m<methods.length; m++) {
			Method md = methods[m];
			//for2-1.下記の条件を満たすメソッドを変数．GetterMethod[Getterメソッド].件数メソッドへ設定します。
			if(md.getName().indexOf(functionWord) >= 0 && md.getName().endsWith(clmName+CLM_KEN+clmKaikeiNo)) {
				//条件１．メソッド名に"get"の文字列が含まれる(getter メソッドである）     
				//条件２．メソッド名に引数．カラム共通名+"Ken"＋変数．カラム用会計区分コードの文字列が含まれる
				gMethod.setMethodKen(md);
				continue;
			}
		    //for2-2．for2-1.下記の条件を満たすメソッドを変数．GetterMethod[Getterメソッド].金額メソッドへ設定します。
			if(md.getName().indexOf(functionWord) >= 0 && md.getName().endsWith(clmName+CLM_KIN+clmKaikeiNo)) {
				//　条件１．メソッド名に"get"の文字列が含まれる(getter メソッドである）         
				//　条件２．メソッド名に引数．カラム共通名+"Kin"＋変数．カラム用会計区分コードの文字列が含まれる
				gMethod.setMethodKin(md);
			}
			if(gMethod.getMethodKen() !=null && gMethod.getMethodKin() != null) {
				break;
			}
		}
		return gMethod;
	}
    /**
     * 集計区分別にの会計区分明細情報の生成を行います。
     * @param syukeiKbnList 会計集計区分情報リスト
     * @param kaikeiKbnList 会計区分情報リスト
     * @return 集計区分別会計区分マスタ情報
     */
    public static Map makeKaikeiSyukeiKbnInfo(List syukeiKbnList, List kaikeiKbnList) {
        
        Map map = new HashMap();
        
        for (int i=0; i < syukeiKbnList.size(); i++) {
            CtlSyukeiKbn syukeiKbn = (CtlSyukeiKbn)(syukeiKbnList.get(i));
            List syukeiBetsukaikeiKbnList = new ArrayList();
            
            for(int j = 0; j < kaikeiKbnList.size(); j++) {
                CtlKaikeiKbnInfo kaikeiKbn = (CtlKaikeiKbnInfo)(kaikeiKbnList.get(j));
                if (kaikeiKbn.getSyukeiCd().equals(syukeiKbn.getSyukeiCd())) {
                    // 集計コードが一致した場合、格納する
                    syukeiBetsukaikeiKbnList.add(kaikeiKbn);
                }
            }
            
            // 集計区分別情報の作成
            UILists syukeiKbnMeisaiInfo = new UILists();
            syukeiKbnMeisaiInfo.setKey(syukeiKbn.getSyukeiCd());
            syukeiKbnMeisaiInfo.setKeyName(syukeiKbn.getSyukeiName());
            syukeiKbnMeisaiInfo.setListData(syukeiBetsukaikeiKbnList);
            // 集計区分別情報の格納
            map.put(syukeiKbn.getSyukeiCd(), syukeiKbnMeisaiInfo);
            
        }
        
        
        return map;
    }

}
