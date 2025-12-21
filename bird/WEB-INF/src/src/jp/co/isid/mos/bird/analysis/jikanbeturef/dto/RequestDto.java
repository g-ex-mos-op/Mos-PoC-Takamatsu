/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.CsvDataKbn;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;

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
     * 条件画面：ブロックリスト
     */
    private List blockList = new ArrayList(0);
    
    /**
     * 対象期間プルダウンリスト
     */
    private List listTaishoKikan= new ArrayList(0);
    private List listKikanShitei= new ArrayList(0);
    private String dataKbn;
	/**
	 * CSVモード判別値
	 */
	private boolean csvMode;
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
     * ブロックリスト を戻します。
     * @return ブロックリスト
     */
    public List getBlockList() {
        return blockList;
    }

    /**
     * ブロックリスト を設定します。
     * @param ブロックリスト
     */
    public void setBlockList(List blockList) {
        this.blockList = blockList;
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
		setViewId(JikanBetuRefUtil.VIEW_ID);
        //２.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定する。
        int windowId = sessonDto.createWindowId();
        //３.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
        setWindowId(windowId);
		setDataKbn(CsvDataKbn.CODE_WEEK);
	}
	/**
	 * プルダウンリストの値を設定します。
	 * 
	 * @param sessionDto
	 */
	public void setPullDownLists() {
		setListTaishoJoken(getJikanBetuRefSesDto().getListTaishoJoken());
        setBlockList(getJikanBetuRefSesDto().getBlockList());
		setListTaishoKikan(getJikanBetuRefSesDto().getListTaishoKikan());
		
	}
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto() {
		setTaishoJoken(getJikanBetuRefSesDto().getTaishoJoken(getWindowId()));
		setHyojiTaisho(getJikanBetuRefSesDto().getHyojiTaisho(getWindowId()));
        setBlockCd(getJikanBetuRefSesDto().getBlockCd(getWindowId()));
		setTaishoKikan(getJikanBetuRefSesDto().getTaishoKikan(getWindowId()));
		setKikanSitei(getJikanBetuRefSesDto().getKikanSitei(getWindowId()));
		setDataKbn(getJikanBetuRefSesDto().getDataKbn(getWindowId()));
	}
	/**
	 * 検索済み条件値設定処理
	 * @param dto
	 */
	public void setSearchedData(List listSearchData) {
		super.setSearchedData(listSearchData);
		getJikanBetuRefSesDto().setTaishoJoken(getWindowId(), getTaishoJoken());
		getJikanBetuRefSesDto().setHyojiTaisho(getWindowId(), getHyojiTaisho());
		getJikanBetuRefSesDto().setTaishoKikan(getWindowId(), getTaishoKikan());
        getJikanBetuRefSesDto().setBlockCd(getWindowId(), getBlockCd());
		getJikanBetuRefSesDto().setKikanSitei(getWindowId(), getKikanSitei());
		getJikanBetuRefSesDto().setDataKbn(getWindowId(), getDataKbn());
        getJikanBetuRefSesDto().setKikanSiteiTo(getWindowId(), getKikanSiteiTo());
	}
	/**
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public void setNoResultSearchedData() {
		super.setNoResultSearchedData();
		getJikanBetuRefSesDto().setTaishoJoken(getWindowId(), null);
		getJikanBetuRefSesDto().setHyojiTaisho(getWindowId(), null);
		getJikanBetuRefSesDto().setTaishoKikan(getWindowId(), null);
        getJikanBetuRefSesDto().setBlockCd(getWindowId(), null);
		getJikanBetuRefSesDto().setKikanSitei(getWindowId(), null);
		getJikanBetuRefSesDto().setDataKbn(getWindowId(), null);
        getJikanBetuRefSesDto().setKikanSiteiTo(getWindowId(), null);
	}
    /**
     * 検索済み判断処理
     * @return boolean true:検索済み false:検索していない
     */
    public boolean isSearched() {
		return getJikanBetuRefSesDto().isSearched(getWindowId());
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
	// 山内追加20090430
    /**
     * @return listTaishoKikan を戻します。
     */
    public List getListKikanShitei() {
        return listKikanShitei;
    }

    /**
     * @param listTaishoKikan を クラス変数listTaishoKikanへ設定します。
     */
    public void setListKikanShitei(List listKikanShitei) {
        this.listKikanShitei = listKikanShitei;
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
	private SessionDto getJikanBetuRefSesDto() {
		return (SessionDto)getMenuBetuSesDto();
	}

	/**
	 * @return dataKbn を戻します。
	 */
	public String getDataKbn() {
		return dataKbn;
	}

	/**
	 * @param dataKbn を クラス変数dataKbnへ設定します。
	 */
	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
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
	/**
	 * @return csvMode を戻します。
	 */
	public boolean isCsvMode() {
		return csvMode;
	}

	/**
	 * @param csvMode を クラス変数csvModeへ設定します。
	 */
	public void setCsvMode(boolean csvMode) {
		this.csvMode = csvMode;
	}
}
