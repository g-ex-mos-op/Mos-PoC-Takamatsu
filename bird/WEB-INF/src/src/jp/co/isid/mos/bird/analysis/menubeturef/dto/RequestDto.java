/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.menubeturef.code.CsvDataKbn;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;

/**
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class RequestDto extends MenuBetuReqDto {
    /**
     * 対象条件プルダウンリスト
     */
    private List listTaishoJoken= new ArrayList(0);
    /**
     * 対象期間プルダウンリスト
     */
    private List listTaishoKikan= new ArrayList(0);
    /**
     * 条件画面：ブロックリスト
     */
    private List blockList = new ArrayList(0);
    /**
     * 期間指定日の前月の年月
     */
    private String kikanSiteiLastMonth;

    /**
     * 期間指定終了日
     */
    private String kikanSiteiTo;

    /**
     * CSV処理フラグ　true：CSV
     */
    private boolean csv = false;
    
	/**
	 * @return listsTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}

	/**
	 * @param listsTaishoJoken 設定する listsTaishoJoken。
	 */
	public void setListTaishoJoken(List listsTaishoJoken) {
		this.listTaishoJoken = listsTaishoJoken;
	}
	/**
	 * 初期化処理
	 * 
	 * 1.ユーザタイプコード設定
	 * 2.ウィンドウIDの生成と設定
	 * 3.条件項目情報の設定
	 * 4.デフォルト値の設定
	 */
	public void initialaze(SessionDto sessonDto) {
		//１．ユーザータイプコードを設定します。
		setViewId(MenuBetuRefUtil.VIEW_ID);
        //２.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定する。
        int windowId = sessonDto.createWindowId();
        //３.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
        setWindowId(windowId);
	}
	/**
     * ブロックリストを戻します。
     * @return ブロックリスト 
     */
    public List getBlockList() {
        return blockList;
    }

    /**
     * ブロックリストを設定します。
     * @param blockList ブロックリスト
     */
    public void setBlockList(List blockList) {
        this.blockList = blockList;
    }

    /**
	 * プルダウンリストの値を設定します。
	 * 
	 * @param sessionDto
	 */
	public void setPullDownLists() {
		setListTaishoJoken(getMenuBetuRefSesDto().getListTaishoJoken());
        setBlockList(getMenuBetuRefSesDto().getBlockList());
		setListTaishoKikan(getMenuBetuRefSesDto().getListTaishoKikan());
		
	}
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto() {
		setTaishoJoken(getMenuBetuRefSesDto().getTaishoJoken(getWindowId()));
		setHyojiTaisho(getMenuBetuRefSesDto().getHyojiTaisho(getWindowId()));
		setTaishoKikan(getMenuBetuRefSesDto().getTaishoKikan(getWindowId()));
        setBlockCd(getMenuBetuRefSesDto().getBlockCd(getWindowId()));
		setKikanSitei(getMenuBetuRefSesDto().getKikanSitei(getWindowId()));
		setKikanSiteiLastMonth(getMenuBetuRefSesDto().getKikanSiteiLastMonth(getWindowId()));
	}
	/**
	 * 検索済み条件値設定処理
	 * @param dto
	 */
	public void setSearchedData(List listSearchData) {
		super.setSearchedData(listSearchData);
		getMenuBetuRefSesDto().setTaishoJoken(getWindowId(), getTaishoJoken());
		getMenuBetuRefSesDto().setHyojiTaisho(getWindowId(), getHyojiTaisho());
		getMenuBetuRefSesDto().setTaishoKikan(getWindowId(), getTaishoKikan());
        getMenuBetuRefSesDto().setBlockCd(getWindowId(), getBlockCd());
		getMenuBetuRefSesDto().setKikanSitei(getWindowId(), getKikanSitei());
		getMenuBetuRefSesDto().setKikanSiteiTo(getWindowId(), getKikanSiteiTo());
		getMenuBetuRefSesDto().setKikanSiteiLastMonth(getWindowId(), getKikanSiteiLastMonth());
	}
	/**
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public void setNoResultSearchedData() {
		super.setNoResultSearchedData();
		getMenuBetuRefSesDto().setTaishoJoken(getWindowId(), null);
		getMenuBetuRefSesDto().setHyojiTaisho(getWindowId(), null);
		getMenuBetuRefSesDto().setTaishoKikan(getWindowId(), null);
        getMenuBetuRefSesDto().setBlockCd(getWindowId(), null);
		getMenuBetuRefSesDto().setKikanSitei(getWindowId(), null);
		getMenuBetuRefSesDto().setKikanSiteiTo(getWindowId(), null);
		getMenuBetuRefSesDto().setKikanSiteiLastMonth(getWindowId(), null);
	}
    /**
     * 検索済み判断処理
     * @return boolean true:検索済み false:検索していない
     */
    public boolean isSearched() {
		return getMenuBetuRefSesDto().isSearched(getWindowId());
    }

	/**
	 * @return listTaishoKikan を戻します。
	 */
	public List getListTaishoKikan() {
		return listTaishoKikan;
	}

	/**
	 * @param listTaishoKikan を クラス変数listTaishoKikanへ設定します。
	 */
	public void setListTaishoKikan(List listTaishoKikan) {
		this.listTaishoKikan = listTaishoKikan;
	}
	/**
	 * CSVダウンロードリスト取得処理
	 * @return
	 */
	public List getListCsvDataKbn() {
		return CsvDataKbn.getPullDownList();
	}
	/**
	 * セッション用DTO取得処理
	 * @return
	 */
	private SessionDto getMenuBetuRefSesDto() {
		return (SessionDto)getMenuBetuSesDto();
	}

	/**
	 * 期間指定日の前月の年月取得処理
	 * @return kikanSiteiLastMonth を戻します。
	 */
	public String getKikanSiteiLastMonth() {
		return kikanSiteiLastMonth;
	}

	/**
	 * 期間指定日の前月の年月設定処理
	 * @param yyyyMM を クラス変数kikanSiteiLastMonthへ設定します。
	 */
	public void setKikanSiteiLastMonth(String yyyyMM) {
		this.kikanSiteiLastMonth = yyyyMM;
	}
	/**
	 * 前年データ種別取得処理
	 * @return
	 */
	public String getZennenDataShubetu() {
		if(TaishoKikan.DAY.equals(getTaishoKikan())) {
			return ZennenDataShubetu.CODE_DOYO;
		}
		else if(TaishoKikan.MONTH.equals(getTaishoKikan())) {
			return ZennenDataShubetu.CODE_DOGETU;
		}
		return null;
	}
	/**
	 * 前年データ種別名称取得処理
	 * @return
	 */
	public String getZennenDataShubetuName() {
		return ZennenDataShubetu.getName(getZennenDataShubetu(), getUserTypeCd());
	}

    public boolean isCsv() {
        return csv;
    }

    public void setCsv(boolean csv) {
        this.csv = csv;
    }
    /**
     * CSV出力対象が集約メニューか否か判断値取得処理
     * 
     * @return true:集約メニュー、false:集約メニュー以外
     */
    public boolean isOutputSmenu() {
    	return RowType.CD_SUM_MENU.equals(getCsvDataKbn());
    }
	/**
	 * @return kikanSiteiTo を戻します。
	 */
	public String getKikanSiteiTo() {
		return kikanSiteiTo;
	}

	/**
	 * @param kikanSiteiTo を クラス変数kikanSiteiToへ設定します。
	 */
	public void setKikanSiteiTo(String kikanSiteiTo) {
		this.kikanSiteiTo = kikanSiteiTo;
	}
}
