/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dto.MosChickenSaleStateViewDto;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UIDayMenuSumInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UIMiseInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UISaleStateInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UISaleStateTotalInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetDayMenuSumInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetSaleStateInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetSaleStateTotalInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;


/**
 * 一覧情報と合計情報取得ロジック
 * 
 * @author xlee
 */
public class GetSaleStateTotalInfoLogicImpl implements GetSaleStateTotalInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS017L04";

    /** 合計情報ロジック */
    private GetDayMenuSumInfoLogic getDayMenuSumInfoLogic;
 
    /** 一覧情報ロジック */
    private GetSaleStateInfoLogic getSaleStateInfoLogic;
    
    /** 合計部タイトル */
    private static final String goukeiTitle = "合計(換算数)";
    
    /** 合計部区分 */
    private static final String goukeiKubun = "GOUKEI";
    
    /** 予約以外の販売数部タイトル */
    private static final String igaiTitle = "予約以外の販売数(実績)";
    
    /** 予約以外の販売数部区分 */
    private static final String igaiKubun = "IGAI";
    
    /**
     * 一覧情報と合計情報ロジックを取得します<br>
     * @return getDayMenuSumInfoLogic 一覧情報と合計情報取得ロジック
     */
    public GetDayMenuSumInfoLogic getGetDayMenuSumInfoLogic() {
		return getDayMenuSumInfoLogic;
	}

    /**
     * 一覧情報と合計情報取得ロジックを設定します<br>
     * @param GetDayMenuSumInfoLogic getDayMenuSumInfoLogic 一覧情報と合計情報取得ロジック
     */
    public void setGetDayMenuSumInfoLogic(GetDayMenuSumInfoLogic getDayMenuSumInfoLogic) {
		this.getDayMenuSumInfoLogic = getDayMenuSumInfoLogic;
	}

    /**
     * 一覧情報と合計情報取得ロジックを取得します<br>
     * @return getSaleStateInfoLogic 一覧情報と合計情報ロジック
     */
    public GetSaleStateInfoLogic getGetSaleStateInfoLogic() {
		return getSaleStateInfoLogic;
	}

    /**
     * 一覧情報と合計情報取得ロジックを設定します<br>
     * @param GetSaleStateInfoLogic getSaleStateInfoLogic 一覧情報と合計情報ロジック
     */
    public void setGetSaleStateInfoLogic(GetSaleStateInfoLogic getSaleStateInfoLogic) {
		this.getSaleStateInfoLogic = getSaleStateInfoLogic;
	}
    /**
     * 一覧情報と合計情報取得
     * @param mosChickenSaleStateViewDto DTO
     * @param　sysDate　システム日付
     * @param　ckanriNo　管理番号
     * @param　miseCd　店舗コード
     * @param　menuGroup　メニューグループ
     * @param　taishoDtTo　対象期間の開始日
     * @param　taishoDtFrom　対象期間の終了日
     * @return  オーナーコード
     */
    public List execute(MosChickenSaleStateViewDto mosChickenSaleStateViewDto, String sysDate) {
      String ckanriNo = mosChickenSaleStateViewDto.getCondTitleCd();
      String miseCd = mosChickenSaleStateViewDto.getCondTenpoCd();
      
      if(MosChichenSaleStateUtil.isNull(miseCd)) {
      	
      	List tmpTenpoList = mosChickenSaleStateViewDto.getCondListTenpo();
      	UIMiseInfo uiMiseInfo = (UIMiseInfo)tmpTenpoList.get(0);
      	miseCd = uiMiseInfo.getMiseCd();
      	mosChickenSaleStateViewDto.setCondTenpoCd(miseCd);
      }
      String menuGroupCd = mosChickenSaleStateViewDto.getCondShohinGroupCd();
      
      //日付別合計
      String taishoDtTo = mosChickenSaleStateViewDto.getCondTaishoKikanCdTo();
      String taishoDtFrom = mosChickenSaleStateViewDto.getCondTaishoKikanCdFr();
    	
    	///エラー処理：
    	if(MosChichenSaleStateUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	if(MosChichenSaleStateUtil.isNull(ckanriNo)){
            throw new NotNullException("管理番号"); //E0506 管理番号
        }
    	if(MosChichenSaleStateUtil.isNull(miseCd)){
            throw new NotNullException("店舗コード"); //E0506 店舗コード
        }
    	if(MosChichenSaleStateUtil.isNull(menuGroupCd)){
            throw new NotNullException("商品グループコード"); //E0506 メニューグループコード
        }    	
    	if(MosChichenSaleStateUtil.isNull(taishoDtTo)){
            throw new NotNullException("対象期間開始日"); //E0506 対象期間TO
        }
    	if(MosChichenSaleStateUtil.isNull(taishoDtFrom)){
            throw new NotNullException("対象期間終了日"); //E0506 対象期間FROM
        }
    	if(Integer.parseInt(taishoDtFrom) > Integer.parseInt(taishoDtTo)) {
    		throw new NotRelevantException("対象期間開始日","対象期間終了日 以前の日付"); //E0606 日付 
    	}

    	//日付毎の合計
    	List tmpDaySumInfo = getGetDayMenuSumInfoLogic().execute("DAY", sysDate, ckanriNo, miseCd, menuGroupCd, taishoDtFrom, taishoDtTo);
    	//メニュー毎の合計
    	List tmpMenuSumInfo = getGetDayMenuSumInfoLogic().execute("MENU", sysDate, ckanriNo, miseCd, menuGroupCd, taishoDtFrom, taishoDtTo);
    	//各日付・メニュー別の情報
    	List tmpSaleStateInfo = getGetSaleStateInfoLogic().execute(sysDate, ckanriNo, miseCd, menuGroupCd, taishoDtFrom, taishoDtTo);
        
    	//対象期間内の日付がすべて表示されるように
    	List dayList = MosChichenSaleStateUtil.makeDtList(taishoDtFrom,taishoDtTo);
    	
    	//各日付・メニュー別の情報の日付データがない場合
    	boolean zeroResult = false;
    	for(int i = 0; i < tmpSaleStateInfo.size(); i++) {
    		UISaleStateInfo uiSaleStateInfo = (UISaleStateInfo) tmpSaleStateInfo.get(i);
    		
    		if(!(uiSaleStateInfo.getDt() == null || MosChichenSaleStateUtil.isNull(uiSaleStateInfo.getDt()))) {
    			zeroResult = true;
    			break;
    		}
    	}
    	//取得された結果の中で、一つでも日付が存在する場合、データを表示、それ以外の場合はエラー
    	if(!zeroResult) {
    		//正常に結果が取得された場合、既存のリストのデータをクリアする
    		if(mosChickenSaleStateViewDto.getResultDetailListSize() > 0) {
    			mosChickenSaleStateViewDto.setResultDetailList(null);
    		}
    		throw new NoResultException(); //E0103 検索結果がない場合
		}
    	//画面に表示するデータを格納するリストを生成
    	List totalList = new ArrayList();
    	
    	//合計ライン
    	List tempSumList = new ArrayList();
    	BigDecimal kazu = new BigDecimal(0); //販売数期間別合計用
    	BigDecimal reserv = new BigDecimal(0);
    	BigDecimal reservReslt = new BigDecimal(0); //実績
    	
    	List newDaySumInfo = new ArrayList();
    	
    	//対象期間中、DBにデータが存在しない場合、
    	if(tmpDaySumInfo.size() < dayList.size()) {
        	
        	int tmpStartStop = 0;
        	
    		for(int u = 0; u < tmpDaySumInfo.size(); u++) {
			
				for(int y = tmpStartStop; y < dayList.size(); y++) {
					String tmpDt = (String)dayList.get(y);
					
					UIDayMenuSumInfo newUiDaySumInfo = (UIDayMenuSumInfo) tmpDaySumInfo.get(u);
					String dt = newUiDaySumInfo.getDt();
					if(!(dt == null || MosChichenSaleStateUtil.isNull(dt)) && 
						(!tmpDt.equals(dt) &&(Integer.parseInt(tmpDt) < Integer.parseInt(dt)))) {
						//対象期間の開始日がデータが存在する日付より前の日付である場合
						UIDayMenuSumInfo uiTmpDaySumInfo = new UIDayMenuSumInfo();
						uiTmpDaySumInfo.setDt(tmpDt);
						uiTmpDaySumInfo.setKazuKeiSum(new BigDecimal(0)); //販売数
						uiTmpDaySumInfo.setReserveSum(new BigDecimal(0)); //予約数
						newDaySumInfo.add(uiTmpDaySumInfo);
					} else {
						newDaySumInfo.add(newUiDaySumInfo);
				    	tmpStartStop = y+1;
						break;
					}
				}
				if(u == tmpDaySumInfo.size()) {
					break;
				}
    		}
			if(dayList.size() != newDaySumInfo.size()) {
				for(int u = newDaySumInfo.size(); u < dayList.size(); u++) {
					String tmpDt = (String)dayList.get(u);
					UIDayMenuSumInfo uiTmpDaySumInfo = new UIDayMenuSumInfo();
					uiTmpDaySumInfo.setDt(tmpDt);
					uiTmpDaySumInfo.setKazuKeiSum(new BigDecimal(0)); //販売数
					uiTmpDaySumInfo.setReserveSum(new BigDecimal(0)); //予約数
					newDaySumInfo.add(uiTmpDaySumInfo);
				}
			}
    	} else {
    		newDaySumInfo = tmpDaySumInfo;
    	}
    	
    	for(int i = 0; i < tmpMenuSumInfo.size(); i++) {
    		//各日付・メニューラインの各情報を格納するリスト
    		UIDayMenuSumInfo uiMenuSumInfo = (UIDayMenuSumInfo) tmpMenuSumInfo.get(i);
    		kazu = kazu.add(uiMenuSumInfo.getKazuKeiSum());
			reserv = reserv.add(uiMenuSumInfo.getReserveSum());
    	}
    	
    	for(int i = 0; i < tmpDaySumInfo.size(); i++) {
    		//各日付・メニューラインの各情報を格納するリスト
    		UIDayMenuSumInfo uiDaySumInfo = (UIDayMenuSumInfo) tmpDaySumInfo.get(i);
    		if(Integer.parseInt(uiDaySumInfo.getDt()) < Integer.parseInt(sysDate)) {
    			//販売数
    			reservReslt = reservReslt.add(uiDaySumInfo.getReserveSum());
    		}
    	}
    	for(int i = 0; i < newDaySumInfo.size(); i++) {
    		//メニューラインの各情報を格納するリスト
    		UIDayMenuSumInfo uiDaySumInfo = (UIDayMenuSumInfo) newDaySumInfo.get(i);
    		
    		if(i == 0) {
    			//合計部のメニュー名を設定
    			UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
        		totalInfo.setMenuCd(goukeiKubun); 
        		totalInfo.setMenuNm(goukeiTitle);
        		totalInfo.setKazuKeiSum(kazu);
        		totalInfo.setReserveSum(reserv);        		
        		tempSumList.add(totalInfo);
    		}
    		UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
    		
    		String yy = uiDaySumInfo.getDt().substring(0,4);
    		String mm = uiDaySumInfo.getDt().substring(4,6);
    		String dd = uiDaySumInfo.getDt().substring(6,8);
    		
    		BigDecimal kazuSum = uiDaySumInfo.getKazuKeiSum();
    		BigDecimal reservSum = uiDaySumInfo.getReserveSum();
    		//未来日のチェック
    		if(Integer.parseInt(uiDaySumInfo.getDt()) >= Integer.parseInt(sysDate)) {
    			totalInfo.setFutureKbn(true);
    		}
    		totalInfo.setViewDt(yy+mm+dd);
    		totalInfo.setEvyDate(mm+"/"+dd);
    		totalInfo.setDayKnj(MosChichenSaleStateUtil.getDayOFWeek(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd)));
    		totalInfo.setKazuKeiSum(kazuSum);
    		totalInfo.setReserveSum(reservSum);
    		tempSumList.add(totalInfo);
    	}
    	totalList.add(tempSumList);
    	
    	List tempSubrList  = new ArrayList();
    	//予約以外の販売数ライン
    	for(int i = 0; i < newDaySumInfo.size(); i++) {
    		//メニューラインの各情報を格納するリスト
    		UIDayMenuSumInfo uiDaySumInfo = (UIDayMenuSumInfo) newDaySumInfo.get(i);
    		
    		if(i == 0) {
    			//予約以外の販売数のタイトルを設定
    			UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
    			totalInfo.setMenuCd(igaiKubun); 
        		totalInfo.setMenuNm(igaiTitle);
        		totalInfo.setKazuKeiSum(kazu.subtract(reservReslt));
        		tempSubrList.add(totalInfo);
    		}
    		UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
    		
    		BigDecimal kazuSum = uiDaySumInfo.getKazuKeiSum();
    		BigDecimal reservSum = uiDaySumInfo.getReserveSum();
    		//未来日のチェック
    		if(Integer.parseInt(uiDaySumInfo.getDt()) >= Integer.parseInt(sysDate)) {
    			totalInfo.setFutureKbn(true);
    		}
    		totalInfo.setKazuKeiSum(kazuSum.subtract(reservSum));
    		tempSubrList.add(totalInfo);
    	}    	
    	totalList.add(tempSubrList);
    	
    	List tempMenuList = new ArrayList();
    	//メニュー
    	for(int i = 0; i < tmpMenuSumInfo.size(); i++) {
    		//各日付・メニューラインの各情報を格納するリスト
    		UIDayMenuSumInfo uiMenuSumInfo = (UIDayMenuSumInfo) tmpMenuSumInfo.get(i);
    		
    		String rowMenuCd = uiMenuSumInfo.getMenuCd();
            String rowMenuNm = ""; //---2006/12/24 add
    		
    		List tempMenuDiffList = null;
    		//日付
    		for(int k = 0; k < tmpDaySumInfo.size(); k++) { 

    			tempMenuDiffList = new ArrayList();
	    		
	    		boolean tmpKbn = true;
	    		//実際の値
				for(int j = 0; j < tmpSaleStateInfo.size(); j++) {
					UISaleStateInfo uiSaleStateInfo = (UISaleStateInfo) tmpSaleStateInfo.get(j);
					
					if(rowMenuCd.equals(uiSaleStateInfo.getMenuCd())) {
					
						String fndDt = uiSaleStateInfo.getDt();
						rowMenuNm = uiSaleStateInfo.getMenuNm(); //---2006/12/24
                        
						if(!MosChichenSaleStateUtil.isNull(fndDt)) {
							if(tmpKbn) {
								UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
								totalInfo.setMenuNm(uiSaleStateInfo.getMenuNm()); // メニュー名
	
					    		totalInfo.setKazuKeiSum(uiMenuSumInfo.getBaseKazuKeiSum());	//販売数
								totalInfo.setReserveSum(uiMenuSumInfo.getBaseReserveSum());	//予約数
								tempMenuDiffList.add(totalInfo);
							}
							UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
							if(Integer.parseInt(fndDt) >= Integer.parseInt(sysDate)) {
								totalInfo.setFutureKbn(true);
							}
					    	totalInfo.setEvyDate(fndDt);
					    	totalInfo.setKazuKeiSum(uiSaleStateInfo.getKazuKei()); //販売数
					    	totalInfo.setReserveSum(uiSaleStateInfo.getReserveAmt()); //予約数
							tempMenuDiffList.add(totalInfo);
							tmpKbn = false;
						}
					}
				}
    		}
    		//サイズが異なるリストがある場合
    		if(tempMenuDiffList.size()-1 < dayList.size()) {
    			List nMenuDiffList = new ArrayList();
    			//1.メニュー名をセットする
//--2006/12/25 add start
                if (tempMenuDiffList.size() == 0) {
                    UISaleStateTotalInfo totalInfo = new UISaleStateTotalInfo();
                    totalInfo.setMenuNm(rowMenuNm);
                    totalInfo.setKazuKeiSum(uiMenuSumInfo.getBaseKazuKeiSum()); 
                    totalInfo.setReserveSum(uiMenuSumInfo.getBaseReserveSum()); 
                    tempMenuDiffList.add(totalInfo);
                }
//--2006/12/25 add end
    			nMenuDiffList.add(tempMenuDiffList.get(0));
    			//2.リストの各日付と比較しながらデータをセットする
				int  tmpStop = 0;
				
				for(int u = 1; u < tempMenuDiffList.size(); u++) {
					
					for(int y = tmpStop; y < dayList.size(); y++) {
	    				String tmpDt = (String)dayList.get(y);

						UISaleStateTotalInfo totalInfo = (UISaleStateTotalInfo) tempMenuDiffList.get(u);
						String evyDt = totalInfo.getEvyDate();

						if(!tmpDt.equals(evyDt) &&
								(Integer.parseInt(tmpDt) < Integer.parseInt(evyDt))) {
							UISaleStateTotalInfo newTotalInfo = new UISaleStateTotalInfo();
							newTotalInfo.setEvyDate(tmpDt);
					    	if(Integer.parseInt(tmpDt) >= Integer.parseInt(sysDate)) {
					    		newTotalInfo.setFutureKbn(true);
					    	}
					    	newTotalInfo.setKazuKeiSum(new BigDecimal(0)); //販売数
					    	newTotalInfo.setReserveSum(new BigDecimal(0)); //予約数
					    	nMenuDiffList.add(newTotalInfo);
						} else if(tmpDt.equals(evyDt)) {
					    	nMenuDiffList.add(totalInfo);
	    					tmpStop = y+1;
							break;
						}
					}
					if(u == tempMenuDiffList.size()) {
						break;
					}
				}
				if(dayList.size() != nMenuDiffList.size()-1) {
					for(int u = nMenuDiffList.size()-1; u < dayList.size(); u++) {
						String tmpDt = (String)dayList.get(u);
						UISaleStateTotalInfo newTotalInfo = new UISaleStateTotalInfo();
						newTotalInfo.setEvyDate(tmpDt);
				    	if(Integer.parseInt(tmpDt) >= Integer.parseInt(sysDate)) {
				    		newTotalInfo.setFutureKbn(true);
				    	}
				    	newTotalInfo.setKazuKeiSum(new BigDecimal(0)); //販売数
				    	newTotalInfo.setReserveSum(new BigDecimal(0)); //予約数
				    	nMenuDiffList.add(newTotalInfo);
					}
				}
    			tempMenuList.add(nMenuDiffList);
    		} else {
    			tempMenuList.add(tempMenuDiffList);
    		}
    	}
		totalList.add(tempMenuList);
        return totalList;
    }
}
