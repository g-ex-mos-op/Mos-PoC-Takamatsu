package jp.co.isid.mos.bird.bizreport.campkako.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.entity.UINipoMise;
import jp.co.isid.mos.bird.bizreport.campkako.entity.UINipoSibu;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campkako.util.CampKakoUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.Nipo;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * CSVダウンロードロジック
 * 
 * 
 * 更新日:2011/06/24 xkinu ADD 期間合計時に店舗種別の条件追加対応に伴う店舗種別の出力を設定しました。
 * 更新日：2008/09/09 順位のフォーマットを変更
 * 　　　　　　　修正前：15(1456)　　修正後：15
 * @author xnkusama
 */
public class CsvNipoLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampKakoUtil.SCREEN_ID + "L05";
    
    /** LOGIC【検索結果取得】*/
    private SearchLogic campKakoSearchLogic;
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "CAMPPASTGOKEI.csv";
	}

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
		List listSearchData = reqViewDto.getListSearchData();
        //検索結果が存在しない場合は、再検索
		if(!reqViewDto.isExistSearchedData()) {
			listSearchData = (List)getCampKakoSearchLogic().execute(reqViewDto);
		}
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, reqViewDto);
        //４．明細部ヘッダー作成
		if(CampKakoUtil.VIEW_ID_SIBU.equals(reqViewDto.getViewId())) {
			listCSV.add(getSibuListHeader(reqViewDto, listSearchData));
		}
		else {
			listCSV.add(getMiseListHeader(reqViewDto, listSearchData));
		}
		
        //５．明細部データ作成
		if(CampKakoUtil.VIEW_ID_SIBU.equals(reqViewDto.getViewId())) {
			addSibuList(listCSV, reqViewDto, listSearchData);
		}
		else {
			addMiseList(listCSV, reqViewDto, listSearchData);
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
   
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //年度
        listHeader1Row.add("年度");
        listHeader1Row.add(reqViewDto.getNendo() + "年度");
        listHeader1Row.add("");
        //検索対象キャンペーン期間情報
        listHeader1Row.add("対象期間：");
        listHeader1Row.add(CommonUtil.formattYMDSlash(reqViewDto.getMstCampDate().getCampFrom())
                          + "～"
                          + CommonUtil.formattYMDSlash(reqViewDto.getMstCampDate().getCampTo()));
        if(isHonbuUser) {
            listHeader1Row.add("");
            listHeader1Row.add("店舗種別：");
            listHeader1Row.add(reqViewDto.getTenpoShubetuName());
        }
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //キャンペーン情報
        listHeader2Row.add("キャンペーン：");
        listHeader2Row.add(reqViewDto.getCampTitle());
        listHeader2Row.add("");
        //メニュー情報
        listHeader2Row.add("メニュー：");
       	List listCodCampMenu = reqViewDto.getSelfSessionDto().getListMenu(reqViewDto.getCampId(), reqViewDto.getMenuTotaledKbn());
       	if(listCodCampMenu != null) {
	       	//メニュー合計の場合は、キャンペーン対象全メニュー名称を羅列する。
       	    listHeader2Row.add(reqViewDto.getMenuTotaledKbnName() + "　（" + CampKakoUtil.enumerateMenuList(listCodCampMenu) + "）");
        }
        else {
            listHeader2Row.add(reqViewDto.getMenuTotaledKbnName());
        }
        listCSV.add(listHeader2Row);
        
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
        //対象店舗数（支部別CSVの場合）
        if(CampKakoUtil.VIEW_ID_SIBU.equals(reqViewDto.getViewId())) {
            NumericFormatter numFmt = new NumericFormatter();
            Nipo entity = (Nipo)reqViewDto.getListSearchData().get(reqViewDto.getListSearchData().size()-1);
            listHeader3Row.add("対象店舗数");
            listHeader3Row.add(numFmt.format(String.valueOf(entity.getTaishoTenpoCnt())));
        }
        else if (CampKakoUtil.VIEW_ID_MISE.equals(reqViewDto.getViewId())){
            if (isHonbuUser) {
                listHeader3Row.add("対象支部：");
                listHeader3Row.add(reqViewDto.getHyojiTaishoName());
            }
        }
        //
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
		listHeader.add(CommonUtil.CNM_KAZUKEI);
        listHeader.add(CommonUtil.CNM_MENU_URIAGE);
        listHeader.add(CommonUtil.CNM_KINGAKU_KOSEIHI);
        listHeader.add(CommonUtil.CNM_URIAGE);
        listHeader.add(CommonUtil.CNM_URIAGE_ZEN);
        listHeader.add(CommonUtil.CNM_URIAGE_HI);
        listHeader.add(CommonUtil.CNM_KYAKUSU);
        listHeader.add(CommonUtil.CNM_KYAKUSU_ZEN);
        listHeader.add(CommonUtil.CNM_KYAKUSU_HI);
        listHeader.add(CommonUtil.CNM_KYAKU_TANKA);
        listHeader.add(CommonUtil.CNM_KYAKU_TANKA_ZEN);
        listHeader.add(CommonUtil.CNM_KYAKU_TANKA_HI);
			
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
        listHeader.add(CommonUtil.CNM_EIGYO_DAYS);
		if(!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn())) {
			listHeader.add(CommonUtil.CNM_MENU_NAME);
		}
        listHeader.add(CommonUtil.CNM_KAZUKEI);
        listHeader.add(CommonUtil.CNM_MENU_URIAGE);
        listHeader.add(CommonUtil.CNM_KINGAKU_KOSEIHI);
        listHeader.add(CommonUtil.CNM_URIAGE);
        listHeader.add(CommonUtil.CNM_URIAGE_ZEN);
        listHeader.add(CommonUtil.CNM_URIAGE_HI);
        listHeader.add(CommonUtil.CNM_KYAKUSU);
        listHeader.add(CommonUtil.CNM_KYAKUSU_ZEN);
        listHeader.add(CommonUtil.CNM_KYAKUSU_HI);
        listHeader.add(CommonUtil.CNM_KYAKU_TANKA);
        listHeader.add(CommonUtil.CNM_KYAKU_TANKA_ZEN);
        listHeader.add(CommonUtil.CNM_KYAKU_TANKA_HI);
		//前年営業日数
		listHeader.add(CommonUtil.CNM_EIGYO_DAYS_ZEN);
        listHeader.add(CommonUtil.CNM_RANK_SIBU);
        listHeader.add(CommonUtil.CNM_RANK_ALL);
		return listHeader;
	}
	/**
	 * 支部一覧用データ項目名称格納処理
	 * 
	 * @param listData
	 * @param reqViewDto
	 * @param listTabSearchData
	 */
	private void addSibuList(List listCSV, RequestNipoDto reqViewDto, List listSearchData) {
		//集計区分『直営・販社含まない』判断フラグ
		boolean isShukeiKbnSibu = (CommonUtil.COMPANY_CD_MOS.equals(reqViewDto.getCompanyCd()) 
									&& ShukeiKbn.OUT_RC.equals(reqViewDto.getShukeiKbn()));
		//メニュー名称出力判断フラグ
		boolean isDispMenu = (!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn()));
		
        // Formatter
        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
        NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

		for(int i=0; i<listSearchData.size(); i++) {
			UINipoSibu uiNiop = (UINipoSibu)listSearchData.get(i);
			List rowData = new ArrayList(0);
			if(isShukeiKbnSibu) {
				rowData.add(CommonUtil.changeBlank(uiNiop.getJigyouCd()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getJigyouName()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getSlareaCd()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getSlareaName()));
			}
			rowData.add(CommonUtil.changeBlank(uiNiop.getSibuCd()).trim());
			rowData.add(CommonUtil.changeBlank(uiNiop.getSibuName()));
			//『単品』or『単品(集約)』の場合、メニュー名称を表示します。
			if(isDispMenu) {
				rowData.add(CommonUtil.changeBlank(uiNiop.getMenuNameKj()));
			}
            rowData.add(numFmtdgt0.format(uiNiop.getKazuKei()));
            rowData.add(numFmtdgt0.format(uiNiop.getMenuUriage()));
            rowData.add(numFmtdgt2.format(uiNiop.getKingakuKoseiHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getUriage()));
            rowData.add(numFmtdgt0.format(uiNiop.getUriageZen()));
            rowData.add(numFmtdgt2.format(uiNiop.getUriageZennenHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakusu()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakusuZen()));
            rowData.add(numFmtdgt2.format(uiNiop.getKyakusuZennenHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakutanka()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakutankaZen()));
            rowData.add(numFmtdgt2.format(uiNiop.getKyakutankaZennenHi()));
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
    private void addMiseList(List listCSV, RequestNipoDto reqViewDto, List listSearchData) {
    	//本部ユーザー判断フラグ
    	boolean isHonbuUser = UserType.HONBU.equals(reqViewDto.getSelfSessionDto().getUserTypeCd());
		//集計区分『直営・販社含まない』判断フラグ
		boolean isShukeiKbnSibu = (isHonbuUser
									&& CommonUtil.COMPANY_CD_MOS.equals(reqViewDto.getCompanyCd()) 
									&& ShukeiKbn.OUT_RC.equals(reqViewDto.getShukeiKbn()));
		//メニュー名称出力判断フラグ
		boolean isDispMenu = (!MenuTotaledKbn.CODE_TOTAL.equals(reqViewDto.getMenuTotaledKbn()));
        // Formatter
        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
        NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

		for(int i=0; i<listSearchData.size(); i++) {
			UINipoMise uiNiop = (UINipoMise)listSearchData.get(i);
			List rowData = new ArrayList(0);
			if(isShukeiKbnSibu) {
				rowData.add(CommonUtil.changeBlank(uiNiop.getBlockCd()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getBlockName()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getMiseCd()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getMiseNameKj()));
			}
			else {
				rowData.add(CommonUtil.changeBlank(uiNiop.getMiseCd()));
				rowData.add(CommonUtil.changeBlank(uiNiop.getMiseNameKjForAreaDai()));
			}
			//本部ユーザーの場合『店舗種別』を表示します。
			if(isHonbuUser) {
				rowData.add(CommonUtil.changeBlank(uiNiop.getTenpoShubetuRyakuKj()));
			}
            rowData.add(numFmtdgt0.format(uiNiop.getEigyoDays()));
			//『単品』or『単品(集約)』の場合、メニュー名称を表示します。
			if(isDispMenu) {
				rowData.add(CommonUtil.changeBlank(uiNiop.getMenuNameKj()));
			}
            rowData.add(numFmtdgt0.format(uiNiop.getKazuKei()));
            rowData.add(numFmtdgt0.format(uiNiop.getMenuUriage()));
            rowData.add(numFmtdgt2.format(uiNiop.getKingakuKoseiHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getUriage()));
            rowData.add(numFmtdgt0.format(uiNiop.getUriageZen()));
            rowData.add(numFmtdgt2.format(uiNiop.getUriageZennenHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakusu()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakusuZen()));
            rowData.add(numFmtdgt2.format(uiNiop.getKyakusuZennenHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakutanka()));
            rowData.add(numFmtdgt0.format(uiNiop.getKyakutankaZen()));
            rowData.add(numFmtdgt2.format(uiNiop.getKyakutankaZennenHi()));
            rowData.add(numFmtdgt0.format(uiNiop.getEigyoDaysZen()));
            if (!CommonUtil.isNull(uiNiop.getMiseCd())) {
                //---2008/09/09 update start 順位のフォーマットを変更
//                rowData.add(numFmtdgt0.format(uiNiop.getRankInSibu()) + "(" + numFmtdgt0.format(uiNiop.getSibuTenpoCnt()) + ")");
//                rowData.add(numFmtdgt0.format(uiNiop.getRankInAll()) + "(" + numFmtdgt0.format(uiNiop.getAllTenpoCnt()) + ")");
                rowData.add(numFmtdgt0.format(uiNiop.getRankInSibu()));
                rowData.add(numFmtdgt0.format(uiNiop.getRankInAll()));
                //---2008/09/09 update end
            }
            else {
                rowData.add("");
                rowData.add("");
            }
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
	public SearchLogic getCampKakoSearchLogic() {
		return campKakoSearchLogic;
	}

	/**
	 * @param campNipoRefSearchLogic 設定する campNipoRefSearchLogic。
	 */
	public void setCampKakoSearchLogic(SearchLogic campKakoSearchLogic) {
		this.campKakoSearchLogic = campKakoSearchLogic;
	}
}
