/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campniporef.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.campniporef.entity.UINipoMise;
import jp.co.isid.mos.bird.bizreport.campniporef.entity.UINipoSibu;
import jp.co.isid.mos.bird.bizreport.campniporef.entity.UITabSearchData;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.RowType;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetCampMenuLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * CSVダウンロードロジック
 * 
 * 　更新日：2008/09/09 順位のフォーマットを変更
 * 　　　　　　　修正前：15(1456)　　修正後：15
 * @author xkinu
 */
public class CsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampNipoRefUtil.SCREEN_ID+"L05";
    
    /** LOGIC【検索結果取得】*/
    private SearchLogic campNipoRefSearchLogic;
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        RequestNipoDto reqViewDto = (RequestNipoDto) csvOutputDto;
    	if(CampNipoRefUtil.VIEW_ID_MISE.equals(reqViewDto.getViewId())){
        	return "CAMPKOTEN.csv";
        }
        return "CAMPNIPO.csv";
	}
	private GetCampMenuLogic commonCampaignGetCampMenuLogic;
	/**
	 * CSV出力処理
	 * 
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        // DTO
        RequestNipoDto reqViewDto = (RequestNipoDto) csvOutputDto;
		//１．ロジック【検索結果取得】を実行する。戻り値のList[[タブ別検索結果]]を取得します。
		//DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
        ((SessionNipoDto)(reqViewDto.getSelfSessionDto())).getSearchedDataDto(reqViewDto.getWindowId(), reqViewDto);
		List listTabSearchData = reqViewDto.getListSearchData();
		if(!reqViewDto.isExistSearchedData()) {
			//全タブ情報を取得するように対象期間に空を設定します。
			reqViewDto.setTaishoKikan("");
			listTabSearchData = (List)getCampNipoRefSearchLogic().execute(reqViewDto);
		}
		else {
			BigDecimal decM1 = new BigDecimal("-1");
			for (int i=0; i<listTabSearchData.size(); i++) {
				UITabSearchData tabInfo = (UITabSearchData)listTabSearchData.get(i);
				if(decM1.compareTo(tabInfo.getTaishoTenpoCnt())==0) {
					//検索されていないタブ情報のみを取得するように指定対象期間を設定します。
					reqViewDto.setTaishoKikan(tabInfo.getKey());
					listTabSearchData = (List)getCampNipoRefSearchLogic().execute(reqViewDto);
				}
			}
		}
		//１－１．メニュー集計区分が『メニュー合計』の場合は下記の処理を行います。
		if(MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn())) {
			//1.LOGIC【キャンペーンメニュー取得】を実行します。戻り値List[[単品メニュー]]を取得します。
			List listMenu = getCommonCampaignGetCampMenuLogic().execute(reqViewDto.getCampId(), MenuTotaledKbn.CODE_TANPIN);
			//2.DTO【自画面Session】.List[[メニュー]]へ処理1の戻り値List[[単品メニュー]]を設定します。
			reqViewDto.getSelfSessionDto().setListMenu(reqViewDto.getCampId(), reqViewDto.getMenuTotaledKbn(), listMenu);
		}
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, reqViewDto);
        //４．明細部ヘッダー作成
		if(CampNipoRefUtil.VIEW_ID_SIBU.equals(reqViewDto.getViewId())) {
			listCSV.add(getSibuListHeader(reqViewDto, listTabSearchData));
		}
		else {
			listCSV.add(getMiseListHeader(reqViewDto, listTabSearchData));
		}
		
        //５．明細部データ作成
		if(CampNipoRefUtil.VIEW_ID_SIBU.equals(reqViewDto.getViewId())) {
			addSibuList(listCSV, reqViewDto, listTabSearchData);
		}
		else {
			addMiseList(listCSV, reqViewDto, listTabSearchData);
		}
		//６．List[[CSV出力用リスト]]をリターンします。
		return listCSV;
	}
	/**
	 * 
	 * @param listCSV
	 * @param reqViewDto
	 */
	private void addHeader(List listCSV, RequestNipoDto reqViewDto) {
    	//本部ユーザー判断フラグ
    	boolean isHonbuUser = UserType.HONBU.equals(reqViewDto.getSelfSessionDto().getUserTypeCd());
        //会社情報
//      List listHeaderRowCompany = new ArrayList();
//      listHeaderRowCompany.add("会社");
//      listHeaderRowCompany.add(viewDto.getCompanyName());\
        
//      List listHeaderNendoRow = new ArrayList();
//      listHeaderNendoRow.add("年度");
//      listHeaderNendoRow.add(viewDto.getCompanyName());
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("キャンペーン：");
        listHeader1Row.add(reqViewDto.getCampTitle());
        listHeader1Row.add("");
        //検索対象キャンペーン期間情報
        listHeader1Row.add("対象期間：");
        listHeader1Row.add(((UITabSearchData)reqViewDto.getListSearchData().get(0)).getTaishoKikan());
        listHeader1Row.add("");
        //メニュー情報
        listHeader1Row.add("メニュー：");
       	List listCodCampMenu = reqViewDto.getSelfSessionDto().getListMenu(reqViewDto.getCampId(), reqViewDto.getMenuTotaledKbn());
       	if(listCodCampMenu != null) {
	       	//メニュー合計の場合は、キャンペーン対象全メニュー名称を羅列する。
	       	listHeader1Row.add("合計　（"+CampNipoRefUtil.enumerateMenuList(listCodCampMenu, "、")+"）");
        }
        else {
        	listHeader1Row.add(reqViewDto.getMenuTotaledKbnName());
        }
        listCSV.add(listHeader1Row);
        
        if(isHonbuUser) {
            /** 2行目作成 */
            List listHeader2Row = new ArrayList();
	    	if(TaishoJoken.CODE_ALL.equals(reqViewDto.getTaishoJoken())) {
	            listHeader2Row.add("対象店舗：");
	    		listHeader2Row.add("全店");
	    	}
	    	else{
	            listHeader2Row.add("対象支部：");
	    		listHeader2Row.add(reqViewDto.getHyojiTaishoName());
	    	}
        	listHeader2Row.add("");
        	listHeader2Row.add("店舗種別：");
        	listHeader2Row.add(TenpoShubetu.getName(reqViewDto.getTenpoShubetu()));
            listCSV.add(listHeader2Row);
        }
        
        /** 3行目作成 */
        List listHeader3Row = new ArrayList();
        if(isHonbuUser) {
        	listHeader3Row.add("集計区分：");
        }
        else {
        	listHeader3Row.add("支部：");
        }
        listHeader3Row.add(reqViewDto.getShukeiKbnName());
        listHeader3Row.add("");
        listHeader3Row.add("前年データ種別：");
        listHeader3Row.add(reqViewDto.getZennenDataShubetuName());
        listCSV.add(listHeader3Row);
        
        //空白行
        listCSV.add(new ArrayList());
	}
	/**
	 * 支部一覧用データ項目名称格納処理
	 * 
	 * @param reqViewDto
	 * @param listTabSearchData
	 */
	private List getSibuListHeader(RequestNipoDto reqViewDto, List listTabSearchData) {
		List listHeader = new ArrayList(0);
		if(CommonUtil.COMPANY_CD_MOS.equals(reqViewDto.getCompanyCd()) 
				&& ShukeiKbn.OUT_RC.equals(reqViewDto.getShukeiKbn())) {
			listHeader.add(CommonUtil.CNM_JIGYOU_CD);
			listHeader.add(CommonUtil.CNM_JIGYOU_NAME);
			listHeader.add(CommonUtil.CNM_SLAREA_CD);
			listHeader.add(CommonUtil.CNM_SLAREA_NAME);
		}
		listHeader.add(CommonUtil.CNM_SIBU_CD);
		listHeader.add(CommonUtil.CNM_SIBU_NAME);
		if(!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn())) {
			listHeader.add(CommonUtil.CNM_MENU_NAME);
		}
		int commonKoumokuSize = listHeader.size();
		for(int i=0; i<listTabSearchData.size(); i++) {
			UITabSearchData tabInfo = (UITabSearchData)listTabSearchData.get(i);
			//共通項目個数設定
			tabInfo.setCommonKoumokuSize(commonKoumokuSize);
			
			listHeader.add(CommonUtil.CNM_KAZUKEI);
			listHeader.add(CommonUtil.CNM_MENU_URIAGE);
			listHeader.add(CommonUtil.CNM_KINGAKU_KOSEIHI);
			//売上
			listHeader.add(CommonUtil.CNM_URIAGE);
			listHeader.add(CommonUtil.CNM_URIAGE_ZEN);
			listHeader.add(CommonUtil.CNM_URIAGE_HI);
			//客数
			listHeader.add(CommonUtil.CNM_KYAKUSU);
			listHeader.add(CommonUtil.CNM_KYAKUSU_ZEN);
			listHeader.add(CommonUtil.CNM_KYAKUSU_HI);
			//客単価
			listHeader.add(CommonUtil.CNM_KYAKU_TANKA);
			listHeader.add(CommonUtil.CNM_KYAKU_TANKA_ZEN);
			listHeader.add(CommonUtil.CNM_KYAKU_TANKA_HI);
			//項目個数設定
			tabInfo.setKoumokuSize(listHeader.size()-commonKoumokuSize);
			
		}
		return listHeader;
	}
	/**
	 * 店舗用一覧用データ項目名称格納処理
	 * 
	 * @param reqViewDto
	 * @param listTabSearchData
	 */
	private List getMiseListHeader(RequestNipoDto reqViewDto, List listTabSearchData) {
    	//本部ユーザー判断フラグ
    	boolean isHonbuUser = UserType.HONBU.equals(reqViewDto.getSelfSessionDto().getUserTypeCd());
		List listHeader = new ArrayList(0);
		//集計区分『直営・販社含まない』判断フラグ
		boolean isShukeiKbnSibu = (isHonbuUser && CommonUtil.COMPANY_CD_MOS.equals(reqViewDto.getCompanyCd()) 
									&& ShukeiKbn.OUT_RC.equals(reqViewDto.getShukeiKbn()));
		if(isShukeiKbnSibu) {
			listHeader.add(CommonUtil.CNM_BLOCK_CD);
			listHeader.add(CommonUtil.CNM_BLOCK_NAME);
		}
		listHeader.add(CommonUtil.CNM_MISE_CD);
		listHeader.add(CommonUtil.CNM_MISE_NAME);
		//本部ユーザーの場合『店舗種別』を表示します。
		if(isHonbuUser) {
			listHeader.add(CommonUtil.CNM_TENSHU);
		}
		if(!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn())) {
			listHeader.add(CommonUtil.CNM_MENU_NAME);
		}
		int commonKoumokuSize = listHeader.size();
		for(int i=0; i<listTabSearchData.size(); i++) {
			UITabSearchData tabInfo = (UITabSearchData)listTabSearchData.get(i);
			//共通項目個数設定
			tabInfo.setCommonKoumokuSize(commonKoumokuSize);

			if(TaishoKikan.DAY.equals(tabInfo.getKey())) {
				//クローズ
				listHeader.add(CommonUtil.CNM_CLOSE_MJ);
				//天候
				listHeader.add(CommonUtil.CNM_TENKOU);
			}
			else {
				//営業日数
				listHeader.add(CommonUtil.CNM_EIGYO_DAYS);
			}
			listHeader.add(CommonUtil.CNM_KAZUKEI);
			listHeader.add(CommonUtil.CNM_MENU_URIAGE);
			listHeader.add(CommonUtil.CNM_KINGAKU_KOSEIHI);
			//売上
			listHeader.add(CommonUtil.CNM_URIAGE);
			listHeader.add(CommonUtil.CNM_URIAGE_ZEN);
			listHeader.add(CommonUtil.CNM_URIAGE_HI);
			//客数
			listHeader.add(CommonUtil.CNM_KYAKUSU);
			listHeader.add(CommonUtil.CNM_KYAKUSU_ZEN);
			listHeader.add(CommonUtil.CNM_KYAKUSU_HI);
			//客単価
			listHeader.add(CommonUtil.CNM_KYAKU_TANKA);
			listHeader.add(CommonUtil.CNM_KYAKU_TANKA_ZEN);
			listHeader.add(CommonUtil.CNM_KYAKU_TANKA_HI);
			if(TaishoKikan.DAY.equals(tabInfo.getKey())) {
				//前年クローズ
				listHeader.add(CommonUtil.CNM_CLOSE_MJ_ZEN);
				//前年天候
				listHeader.add(CommonUtil.CNM_TENKOU_ZEN);
			}
			else {
				//前年営業日数
				listHeader.add(CommonUtil.CNM_EIGYO_DAYS_ZEN);
			}
			//支部順位
			listHeader.add(CommonUtil.CNM_RANK_SIBU);
			//全国順位
			listHeader.add(CommonUtil.CNM_RANK_ALL);
			//項目個数設定
			tabInfo.setKoumokuSize(listHeader.size()-commonKoumokuSize);
		}
		return listHeader;
	}
	/**
	 * 支部一覧用データ項目名称格納処理
	 * 
	 * @param listData
	 * @param reqViewDto
	 * @param listTabSearchData
	 */
	private void addSibuList(List listCSV, RequestNipoDto reqViewDto, List listTabSearchData) {
		//集計区分『直営・販社含まない』判断フラグ
		boolean isShukeiKbnSibu = (CommonUtil.COMPANY_CD_MOS.equals(reqViewDto.getCompanyCd()) 
									&& ShukeiKbn.OUT_RC.equals(reqViewDto.getShukeiKbn()));
		//前年データ種別『前年同月営業日補正』判断フラグ
		boolean isHOSEI = ZennenDataShubetu.CODE_HOSEI.equals(reqViewDto.getZennenDataShubetu());
		//メニュー名称出力判断フラグ
		boolean isDispMenu = (!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn()));
		
        // Formatter
        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);

        NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

		int tabMaxIndex = listTabSearchData.size()-1;
		UITabSearchData tabDataInfo = (UITabSearchData)listTabSearchData.get(tabMaxIndex);
		List listSearchData = tabDataInfo.getListData();
		for(int i=0; i<listSearchData.size(); i++) {
			UINipoSibu uiNipo = (UINipoSibu)listSearchData.get(i);
			List rowData = new ArrayList(0);
			if(isShukeiKbnSibu) {
				rowData.add(CommonUtil.changeBlank(uiNipo.getJigyouCd()));
				if(RowType.CD_TOTAL.equals(uiNipo.getRowType())
						|| RowType.CD_HONBU.equals(uiNipo.getRowType())){
					rowData.add(CommonUtil.changeBlank(uiNipo.getSibuName()));
				}
				else {
					rowData.add(CommonUtil.changeBlank(uiNipo.getJigyouName()).trim()+(uiNipo.getRowType().equals(RowType.CD_JIGYOU)?"計":""));
				}
				rowData.add(CommonUtil.changeBlank(uiNipo.getSlareaCd()));
				rowData.add(CommonUtil.changeBlank(uiNipo.getSlareaName()).trim()+(uiNipo.getRowType().equals(RowType.CD_SLAREA)?"計":""));
				rowData.add(CommonUtil.changeBlank(uiNipo.getSibuCd()));
				rowData.add("0".equals(uiNipo.getRowType())?CommonUtil.changeBlank(uiNipo.getSibuName()):"");
			}
			else {
				rowData.add(CommonUtil.changeBlank(uiNipo.getSibuCd()));
				rowData.add(CommonUtil.changeBlank(uiNipo.getSibuName()));
			}
			//『単品』or『単品(集約)』の場合、メニュー名称を表示します。
			if(isDispMenu) {
				rowData.add(CommonUtil.changeBlank(uiNipo.getMenuNameKj()).trim());
			}
			rowData.add(numFmtdgt0.format(uiNipo.getKazuKei()));
			rowData.add(numFmtdgt0.format(uiNipo.getMenuUriage()));
			rowData.add(numFmtdgt2.format(uiNipo.getKingakuKoseiHi()));
			//売上
			rowData.add(numFmtdgt0.format(uiNipo.getUriage()));
			rowData.add(numFmtdgt0.format(uiNipo.getUriageZen()));
			rowData.add(numFmtdgt2.format(isHOSEI?uiNipo.getNetUriageZennenHi():uiNipo.getUriageZennenHi()));
			//客数
			rowData.add(numFmtdgt0.format(uiNipo.getKyakusu()));
			rowData.add(numFmtdgt0.format(uiNipo.getKyakusuZen()));
			rowData.add(numFmtdgt2.format(isHOSEI?uiNipo.getNetKyakusuZennenHi():uiNipo.getKyakusuZennenHi()));
			//客単価
			rowData.add(numFmtdgt0.format(uiNipo.getKyakutanka()));
			rowData.add(numFmtdgt0.format(uiNipo.getKyakutankaZen()));
			rowData.add(numFmtdgt2.format(isHOSEI?uiNipo.getNetKyakutankaZennenHi():uiNipo.getKyakutankaZennenHi()));
			
			//1行分のデータを格納します。
			listCSV.add(rowData);
		}
	}
	/**
	 * データ部格納処理
	 * 
	 * @param listCSV
	 * @param reqViewDto
	 * @param listSearchData
	 */
    private void addMiseList(List listCSV, RequestNipoDto reqViewDto, List listTabSearchData) {
    	//本部ユーザー判断フラグ
    	boolean isHonbuUser = UserType.HONBU.equals(reqViewDto.getSelfSessionDto().getUserTypeCd());
		//集計区分『直営・販社含まない』判断フラグ
		boolean isShukeiKbnSibu = (isHonbuUser
									&& CommonUtil.COMPANY_CD_MOS.equals(reqViewDto.getCompanyCd()) 
									&& ShukeiKbn.OUT_RC.equals(reqViewDto.getShukeiKbn()));
		//前年データ種別『前年同月営業日補正』判断フラグ
		boolean isHOSEI = ZennenDataShubetu.CODE_HOSEI.equals(reqViewDto.getZennenDataShubetu());
		//メニュー名称出力判断フラグ
		boolean isDispMenu = (!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn()));
        // Formatter
        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
        NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);
		int tabMaxIndex = listTabSearchData.size()-1;

		UITabSearchData tabDataInfo = (UITabSearchData)listTabSearchData.get(tabMaxIndex);
		List listSearchData = tabDataInfo.getListData();
		for(int i=0; i<listSearchData.size(); i++) {
			UINipoMise uiNipo = (UINipoMise)listSearchData.get(i);
			List rowData = new ArrayList(0);
			if(isShukeiKbnSibu) {
				rowData.add(CommonUtil.changeBlank(uiNipo.getBlockCd()));
				rowData.add(CommonUtil.changeBlank(uiNipo.getBlockName()));
				rowData.add(CommonUtil.changeBlank(uiNipo.getMiseCd()));
				rowData.add(CommonUtil.changeBlank(uiNipo.getMiseNameKj()));
			}
			else {
				rowData.add(CommonUtil.changeBlank(uiNipo.getMiseCd()));
				rowData.add(CommonUtil.changeBlank(uiNipo.getMiseNameKjForAreaDai()));
			}
			//本部ユーザーの場合『店舗種別』を表示します。
			if(isHonbuUser) {
				rowData.add(CommonUtil.changeBlank(uiNipo.getTenpoShubetuRyakuKj()));
			}
			//『単品』or『単品(集約)』の場合、メニュー名称を表示します。
			if(isDispMenu) {
				rowData.add(CommonUtil.changeBlank(uiNipo.getMenuNameKj()));
			}
			if(TaishoKikan.DAY.equals(tabDataInfo.getKey())) {
				//クローズ
				rowData.add(CommonUtil.changeBlank(CommonUtil.getCloseKj(uiNipo.getOpenKbn())));
				//天候
				rowData.add(CommonUtil.changeBlank(uiNipo.getTenkoKbnKigou()));
			}
			else {
				//営業日数
				rowData.add(numFmtdgt0.format(uiNipo.getEigyoDays()));
			}
			rowData.add(numFmtdgt0.format(uiNipo.getKazuKei()));
			rowData.add(numFmtdgt0.format(uiNipo.getMenuUriage()));
			rowData.add(numFmtdgt2.format(uiNipo.getKingakuKoseiHi()));
			//売上
			rowData.add(numFmtdgt0.format(uiNipo.getUriage()));
			rowData.add(numFmtdgt0.format(uiNipo.getUriageZen()));
			rowData.add(numFmtdgt2.format(isHOSEI?uiNipo.getNetUriageZennenHi():uiNipo.getUriageZennenHi()));
			//客数
			rowData.add(numFmtdgt0.format(uiNipo.getKyakusu()));
			rowData.add(numFmtdgt0.format(uiNipo.getKyakusuZen()));
			rowData.add(numFmtdgt2.format(isHOSEI?uiNipo.getNetKyakusuZennenHi():uiNipo.getKyakusuZennenHi()));
			//客単価
			rowData.add(numFmtdgt0.format(uiNipo.getKyakutanka()));
			rowData.add(numFmtdgt0.format(uiNipo.getKyakutankaZen()));
			rowData.add(numFmtdgt2.format(isHOSEI?uiNipo.getNetKyakutankaZennenHi():uiNipo.getKyakutankaZennenHi()));
			if(TaishoKikan.DAY.equals(tabDataInfo.getKey())) {
				//前年クローズ
				rowData.add(CommonUtil.changeBlank(CommonUtil.getCloseKj(uiNipo.getOpenKbnZen())));
				//前年天候
				rowData.add(CommonUtil.changeBlank(uiNipo.getTenkoKbnZenKigou()));
			}
			else {
				//前年営業日数
				rowData.add(numFmtdgt0.format(uiNipo.getEigyoDaysZen()));
			}
            //支部順位
            rowData.add((uiNipo.getRankInSibu() != null ? (numFmtdgt0.format(uiNipo.getRankInSibu())):""));
            //全国順位
            rowData.add((uiNipo.getRankInAll() != null ? (numFmtdgt0.format(uiNipo.getRankInAll())):""));
			//1行分のデータを格納します。
			listCSV.add(rowData);
		}
    }
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("キャンペーン日報 画面DTO");
        }

	}

	/**
	 * @return campNipoRefSearchLogic を戻します。
	 */
	public SearchLogic getCampNipoRefSearchLogic() {
		return campNipoRefSearchLogic;
	}

	/**
	 * @param campNipoRefSearchLogic 設定する campNipoRefSearchLogic。
	 */
	public void setCampNipoRefSearchLogic(SearchLogic campNipoRefSearchLogic) {
		this.campNipoRefSearchLogic = campNipoRefSearchLogic;
	}
	/**
	 * @return commonCampaignGetCampMenuLogic を戻します。
	 */
	public GetCampMenuLogic getCommonCampaignGetCampMenuLogic() {
		return commonCampaignGetCampMenuLogic;
	}
	/**
	 * @param commonCampaignGetCampMenuLogic 設定する commonCampaignGetCampMenuLogic。
	 */
	public void setCommonCampaignGetCampMenuLogic(
			GetCampMenuLogic commonCampaignGetCampMenuLogic) {
		this.commonCampaignGetCampMenuLogic = commonCampaignGetCampMenuLogic;
	}
}
